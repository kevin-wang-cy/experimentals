package experimentals.cryptograph;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.security.*;

import static org.junit.Assert.*;

import static org.junit.Assert.*;

/**
 * @author kevin.wang.cy@gmail.com
 */
public class ECDSATest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void ECDSATest() throws Exception {
        /*
         * Generate an ECDSA signature
         */

        /*
         * Generate a key pair
         */

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

        keyGen.initialize(256, random);

        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();

        /*
         * Create a Signature object and initialize it with the private key
         */

        Signature dsa = Signature.getInstance("SHA1withECDSA");

        dsa.initSign(priv);

        String str = "This is string to sign";
        byte[] strByte = str.getBytes("UTF-8");
        dsa.update(strByte);

        /*
         * Now that all the data to be signed has been read in, generate a
         * signature for it
         */

        byte[] realSig = dsa.sign();
        String realSigHex = new BigInteger(1, realSig).toString(16);


        Signature dsa2 = Signature.getInstance("SHA1withECDSA");

        dsa2.initVerify(pub);
        dsa2.update(strByte);

        boolean realSigVerify = dsa2.verify(realSig);

        System.out.println(String.format("Signature: %s, %s => %s; %s", realSigHex, realSig.length, realSigHex.length(), realSigVerify));


        assertTrue(realSigVerify);
    }
}