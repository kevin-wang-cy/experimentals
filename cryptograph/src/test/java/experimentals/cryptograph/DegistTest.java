package experimentals.cryptograph;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Base64;

import static org.junit.Assert.*;

/**
 * @author kevin.wang.cy@gmail.com
 */
public class DegistTest {
    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @Test
    public void generalTest() throws Exception {
        MessageDigest mdSHA = MessageDigest.getInstance("SHA");

        System.out.println(String.format("algo: %s, length: %s", mdSHA.getAlgorithm(), mdSHA.getDigestLength()));

        MessageDigest mdSHA256 = MessageDigest.getInstance("SHA-256");

        System.out.println(String.format("algo: %s, length: %s", mdSHA256.getAlgorithm(), mdSHA256.getDigestLength()));

        MessageDigest mdMD5 = MessageDigest.getInstance("MD5");

        System.out.println(String.format("algo: %s, length: %s", mdMD5.getAlgorithm(), mdMD5.getDigestLength()));


        String[] inputs = {"", "123456789012345678901234567890QWERTYUIOPASDGHJKLZXCVBNMasdfghjklzxcvbnmqwertyuiop!@#$%^&*()_+-=~`<>,.?/:\";'{}[]|\\", "password"};

        for (String input : inputs) {
            System.out.println(String.format("algo: %s, %s => %s, %s, %s", mdSHA.getAlgorithm(), input, this.hashString(input, mdSHA), this.hashString(input, mdSHA), this.hashString(input, mdSHA).length()));
            System.out.println(String.format("algo: %s, %s => %s, %s, %s", mdSHA256.getAlgorithm(), input, this.hashString(input, mdSHA256), this.hashString(input, mdSHA256), this.hashString(input, mdSHA256).length()));
            System.out.println(String.format("algo: %s, %s => %s, %s, %s", mdMD5.getAlgorithm(), input, this.hashString(input, mdMD5), this.hashString(input, mdMD5), this.hashString(input, mdMD5).length()));


            assertEquals(hashString(input, mdMD5), hashString(input, mdMD5));
            //assertEquals(16, hashString(input, mdMD5).length());

            assertEquals(hashString(input, mdSHA), hashString(input, mdSHA));
            //assertEquals(20, hashString(input, mdSHA).length());

            assertEquals(hashString(input, mdSHA256), hashString(input, mdSHA256));
            //assertEquals(32, hashString(input, mdSHA256).length());
        }

    }


    private String hashString(String input, MessageDigest md) throws UnsupportedEncodingException {
        md.update(input.getBytes("UTF-8"));

        byte[] output = md.digest();

        assertEquals("Digent Length", md.getDigestLength(), output.length);

        return Base64.getEncoder().encodeToString(output);

    }
}