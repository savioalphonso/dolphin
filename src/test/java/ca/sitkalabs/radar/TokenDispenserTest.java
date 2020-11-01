package ca.sitkalabs.radar;

import ca.sitkalabs.radar.GooglePlayStore.net.FluxTokenDispenser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class TokenDispenserTest {

    @Test
    void testTokenDispenser() throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException, IOException {
        FluxTokenDispenser dispenser = new FluxTokenDispenser("playstoretesting094@gmail.com", "bluemoose2019");

        String token = dispenser.getToken();

        System.out.println(token);
    }
}
