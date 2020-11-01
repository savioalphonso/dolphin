package ca.sitkalabs.radar.AppStores.net;

import ca.sitkalabs.radar.GooglePlayStore.GooglePlayConstants;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.conscrypt.OpenSSLProvider;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.security.Security;
import java.util.LinkedHashSet;
import java.util.Set;

public class GooglePlayWebClient{

    public static WebClient getClient()  {

        Security.insertProviderAt(new OpenSSLProvider(), 1);
        HttpClient httpConnector;
        try {
            SslContext context = SslContextBuilder.forClient()
                    .ciphers(getCiphers())
                    .applicationProtocolConfig(null)
                    .build();

            httpConnector = HttpClient.create().secure(spec -> spec.sslContext(context));
            return WebClient.builder()
                    .baseUrl(GooglePlayConstants.AUTH_URL)
                    .clientConnector(new ReactorClientHttpConnector(httpConnector))
                    .build();

        } catch (SSLException e) {
            e.printStackTrace();
        }

        return null;


    }

    //pixel 3 Android 11 Ciphers
    private static Set<String> getCiphers() {
        Set<String> ciphers = new LinkedHashSet<>();
        ciphers.add("TLS_AES_128_GCM_SHA256");
        ciphers.add("TLS_AES_256_GCM_SHA384");
        ciphers.add("TLS_CHACHA20_POLY1305_SHA256");
        ciphers.add("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256");
        ciphers.add("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384");
        ciphers.add("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256");
        ciphers.add("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
        ciphers.add("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384");
        ciphers.add("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256");
        ciphers.add("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA");
        ciphers.add("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA");
        ciphers.add("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA");
        ciphers.add("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA");
        ciphers.add("TLS_RSA_WITH_AES_128_GCM_SHA256");
        ciphers.add("TLS_RSA_WITH_AES_256_GCM_SHA384");
        ciphers.add("TLS_RSA_WITH_AES_128_CBC_SHA");
        ciphers.add("TLS_RSA_WITH_AES_256_CBC_SHA");
        return ciphers;
    }
}
