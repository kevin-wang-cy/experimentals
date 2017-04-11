package experimentals.cryptograph;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.security.*;
import java.util.Base64;

import static org.junit.Assert.*;

/**
 * @author kevin.wang.cy@gmail.com
 */
public class RSATest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void RSATest() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

        keyGen.initialize(2048, new SecureRandom());

        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();

        /*
         * Create a Signature object and initialize it with the private key
         */

        Signature rsa = Signature.getInstance("SHA256withRSA");

        rsa.initSign(priv);

        String str = "This is string to sign";
        byte[] strByte = str.getBytes("UTF-8");
        rsa.update(strByte);

        /*
         * Now that all the data to be signed has been read in, generate a
         * signature for it
         */

        byte[] realSig = rsa.sign();
        String realSigHex = new BigInteger(1, realSig).toString(16);


        Signature rsa2 = Signature.getInstance("SHA256withRSA");

        rsa2.initVerify(pub);

        rsa2.update(strByte);

        boolean realSigVerify = rsa2.verify(realSig);

        System.out.println(String.format("Signature: %s, %s => %s; %s", realSigHex, realSig.length, realSigHex.length(), realSigVerify));

    }

    @Test
    public void RSATest2() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

        keyGen.initialize(2048, new SecureRandom());

        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();

        /*
         * Create a Signature object and initialize it with the private key
         */

        Signature rsa = Signature.getInstance("SHA256withRSA");

        rsa.initSign(priv);

        String str = "This is string to sign";
        byte[] strByte = str.getBytes("UTF-8");
        rsa.update(strByte);

        /*
         * Now that all the data to be signed has been read in, generate a
         * signature for it
         */

        byte[] realSig = rsa.sign();
        String realSigHex = Base64.getEncoder().encodeToString(realSig);


        Signature rsa2 = Signature.getInstance("SHA256withRSA");

        rsa2.initVerify(pub);

        rsa2.update(strByte);

        boolean realSigVerify = rsa2.verify(Base64.getDecoder().decode(realSigHex));

        System.out.println(String.format("Signature: %s, %s => %s; %s", realSigHex, realSig.length, realSigHex.length(), realSigVerify));


        assertTrue(realSigVerify);
    }

}