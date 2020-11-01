package ca.sitkalabs.radar.GooglePlayStore.net;

import ca.sitkalabs.radar.AppStores.net.WebClientFactory;
import ca.sitkalabs.radar.Encryptor.Encryptor;
import ca.sitkalabs.radar.Encryptor.GoogleEncryptor;
import ca.sitkalabs.radar.GooglePlayStore.GooglePlayConstants;
import com.dragons.aurora.playstoreapiv2.GooglePlayAPI;
import com.dragons.aurora.playstoreapiv2.HttpClientAdapter;
import com.dragons.aurora.playstoreapiv2.TokenDispenserClient;
import io.netty.handler.ssl.*;
import org.conscrypt.Conscrypt;
import org.conscrypt.OpenSSLProvider;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.*;
import java.util.stream.Collectors;

public class FluxTokenDispenser {

    private String email;
    private String encryptedPasswd;
    private final WebClient client;

    public FluxTokenDispenser(String email, String password) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException, SSLException {
        Encryptor encryptor = new GoogleEncryptor(GooglePlayConstants.GOOGLE_PUBKEY);
        encryptedPasswd = encryptor.encrypt(email + "\u0000" + password);
        client = WebClientFactory.getWebClient("Google Play");
        this.email = email;
    }



    public String getToken() throws IOException{

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("Email", email);
        params.add("EncryptedPasswd", encryptedPasswd);
        params.add("add_account", "1");
        params.add("accountType", "HOSTED_OR_GOOGLE");
        params.add("google_play_services_version", "11951438");
        params.add("has_permission", "1");
        params.add("source","android");
        params.add("device_country","US");
        params.add("lang", "en");
        params.add("client_sig", "38918a453d07199354f8b19af05ec6562ced5788");
        params.add("callerSig", "38918a453d07199354f8b19af05ec6562ced5788");
        params.add("service", "sj");
        params.add("callerPkg", "com.google.android.gms");


        Map<String, String> response = client.post()
                .body(BodyInserters.fromFormData(params))
                .exchange()
                .doOnError(t -> System.out.println(t.getMessage()))
                .flatMap(t -> t.bodyToMono(String.class))
                .flatMapMany(s -> Flux.fromArray(s.split("\n")))
                .map(s -> s.split("="))
                .collect(Collectors.toMap(s -> s[0], s-> s[1]))
                .block();


        if (response.containsKey("Token"))
            return response.get("Token");
        else
            return null;
    }
}
