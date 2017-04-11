package experimentals.cryptograph;

import org.junit.Test;

import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Base64;
import java.util.Enumeration;

import static org.junit.Assert.*;

/**
 * @author kevin.wang.cy@gmail.com
 */
public class KeystoreManagerTest {

    @Test
    public void generalTest() throws  Exception {


        KeyStore walletKS = KeystoreManager.loadKeystore(KeystoreManagerTest.class.getResourceAsStream("/keystores/wallet.keystore"), "storepass");

        assertNotNull(walletKS);

       assertTrue( walletKS.containsAlias("wallet-default-test"));
       assertTrue( walletKS.containsAlias("wallet-zhuhai-test"));
       assertTrue( walletKS.containsAlias("wallet-ec-test"));
       assertTrue( walletKS.containsAlias("xup-default-test"));
       assertFalse(walletKS.containsAlias("not-exist-alias"));

       assertEquals(4, walletKS.size());

        Enumeration<String> aliasInWalletKS = walletKS.aliases();

       while (aliasInWalletKS.hasMoreElements()) {
           String alias = aliasInWalletKS.nextElement();

           KeyStore.Entry entry = walletKS.getEntry(alias, walletKS.isKeyEntry(alias) ? getProtParam("wallet.keystore", alias) : null);

           assertNotNull(entry);

           switch (alias) {
               case "wallet-default-test":
               case "wallet-zhuhai-test":
                   assertTrue(entry instanceof KeyStore.PrivateKeyEntry);
                   KeyStore.PrivateKeyEntry privEntry = (KeyStore.PrivateKeyEntry) entry;

                   System.out.println(privEntry.toString());

                   PrivateKey privKey = privEntry.getPrivateKey();
                   Certificate cert = privEntry.getCertificate();
                   PublicKey pubKey = cert.getPublicKey();

                   assertNotNull(privKey);
                   assertNotNull(cert);
                   assertNotNull(pubKey);

                   assertEquals("RSA", privKey.getAlgorithm());
                   assertEquals("RSA", pubKey.getAlgorithm());

                   assertTrue(cert instanceof X509Certificate);

                   X509Certificate x509cert = (X509Certificate) cert;

                   assertEquals("SHA256withRSA", x509cert.getSigAlgName());

                   break;

               case "xup-default-test":
                   assertTrue(entry instanceof KeyStore.TrustedCertificateEntry);
                   KeyStore.TrustedCertificateEntry trustedCertificateEntry = (KeyStore.TrustedCertificateEntry) entry;

                   System.out.println(trustedCertificateEntry.toString());

                   Certificate cert2 = trustedCertificateEntry.getTrustedCertificate();
                   PublicKey pubKey2 = cert2.getPublicKey();

                   assertEquals("RSA", pubKey2.getAlgorithm());

                   assertTrue(cert2 instanceof X509Certificate);

                   X509Certificate x509cert2 = (X509Certificate) cert2;

                   assertEquals("SHA256withRSA", x509cert2.getSigAlgName());

                   break;
               case "wallet-ec-test":
                   assertTrue(entry instanceof KeyStore.PrivateKeyEntry);
                   KeyStore.PrivateKeyEntry privEntry3 = (KeyStore.PrivateKeyEntry) entry;

                   System.out.println(privEntry3.toString());

                   PrivateKey privKey3 = privEntry3.getPrivateKey();
                   Certificate cert3 = privEntry3.getCertificate();
                   PublicKey pubKey3 = cert3.getPublicKey();

                   assertNotNull(privKey3);
                   assertNotNull(cert3);
                   assertNotNull(pubKey3);

                   assertEquals("EC", privKey3.getAlgorithm());
                   assertEquals("EC", pubKey3.getAlgorithm());

                   assertTrue(cert3 instanceof X509Certificate);

                   X509Certificate x509cert3 = (X509Certificate) cert3;

                   assertEquals("SHA256withECDSA", x509cert3.getSigAlgName());

                   break;
               default:
                   fail("Should not be here, only 3 keys in this wallet!");
                   break;
           }

       }

        KeyStore xupKS = KeystoreManager.loadKeystore(KeystoreManagerTest.class.getResourceAsStream("/keystores/xup.keystore"), "storepass");

        assertNotNull(xupKS);

        assertTrue( xupKS.containsAlias("wallet-default-test"));
        assertFalse( xupKS.containsAlias("wallet-zhuhai-test"));
        assertTrue( walletKS.containsAlias("wallet-ec-test"));
        assertTrue( xupKS.containsAlias("xup-default-test"));
        assertFalse(xupKS.containsAlias("not-exist-alias"));

        assertEquals(3, xupKS.size());


        Enumeration<String> aliasInXUPKS = xupKS.aliases();

        while (aliasInXUPKS.hasMoreElements()) {
            String alias = aliasInXUPKS.nextElement();

            KeyStore.Entry entry = xupKS.getEntry(alias, xupKS.isKeyEntry(alias) ? getProtParam("xup.keystore", alias) : null);

            assertNotNull(entry);

            switch (alias) {
                case "xup-default-test":
                    assertTrue(entry instanceof KeyStore.PrivateKeyEntry);
                    KeyStore.PrivateKeyEntry privEntry = (KeyStore.PrivateKeyEntry) entry;

                    System.out.println(privEntry.toString());

                    PrivateKey privKey = privEntry.getPrivateKey();
                    Certificate cert = privEntry.getCertificate();
                    PublicKey pubKey = cert.getPublicKey();

                    assertNotNull(privKey);
                    assertNotNull(cert);
                    assertNotNull(pubKey);

                    assertEquals("RSA", privKey.getAlgorithm());
                    assertEquals("RSA", pubKey.getAlgorithm());


                    assertTrue(cert instanceof X509Certificate);

                    X509Certificate x509cert = (X509Certificate) cert;

                    assertEquals("SHA256withRSA", x509cert.getSigAlgName());


                    break;
                case "wallet-default-test":
                    assertTrue(entry instanceof KeyStore.TrustedCertificateEntry);
                    KeyStore.TrustedCertificateEntry trustedCertificateEntry = (KeyStore.TrustedCertificateEntry) entry;

                    System.out.println(trustedCertificateEntry.toString());

                    Certificate cert2 = trustedCertificateEntry.getTrustedCertificate();
                    PublicKey pubKey2 = cert2.getPublicKey();

                    assertEquals("RSA", pubKey2.getAlgorithm());

                    assertTrue(cert2 instanceof X509Certificate);

                    X509Certificate x509cert2 = (X509Certificate) cert2;

                    assertEquals("SHA256withRSA", x509cert2.getSigAlgName());
                    break;

                case "wallet-ec-test":
                    assertTrue(entry instanceof KeyStore.TrustedCertificateEntry);
                    KeyStore.TrustedCertificateEntry trustedCertificateEntry3 = (KeyStore.TrustedCertificateEntry) entry;

                    System.out.println(trustedCertificateEntry3.toString());

                    Certificate cert3 = trustedCertificateEntry3.getTrustedCertificate();
                    PublicKey pubKey3 = cert3.getPublicKey();

                    assertEquals("EC", pubKey3.getAlgorithm());

                    assertTrue(cert3 instanceof X509Certificate);

                    X509Certificate x509cert3 = (X509Certificate) cert3;

                    assertEquals("SHA256withECDSA", x509cert3.getSigAlgName());
                    break;

                default:
                    fail("Should not be here, only 3 keys in this wallet!");
                    break;
            }
        }


        // Sign with wallet.keystore and verify with xup.keystore
        String[] inputs = {"", "&&&&&&&&&&&&", "a=b&a=c&timestamp=3333"};

        for (String input : inputs) {
            String signed = "";

            signed = sign(input, "wallet-default-test");

            assertNotNull(signed);

            assertTrue(verfiy(input, signed, "wallet-default-test"));

            System.out.println(String.format("%s, %s", signed.length(), signed));


            signed = sign(input, "wallet-ec-test");

            assertNotNull(signed);

            assertTrue(verfiy(input, signed, "wallet-ec-test"));

            System.out.println(String.format("%s, %s", signed.length(), signed));


            signed = sign(input, "wallet-zhuhai-test");

            assertNotNull(signed);

            System.out.println(String.format("%s, %s", signed.length(), signed));

            try {
                verfiy(input, signed, "wallet-zhuhai-test");
                fail("Exception is expected as the alias is not imported into xup.keystore");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
            // sign with xup.keystore and verify with wallet.keystore

    }

    private KeyStore.PasswordProtection getProtParam(String wallet, String alias) {
        String keypass = "keypass";
        return new KeyStore.PasswordProtection(keypass.toCharArray());
    }


    private String sign (String input, String alias) throws Exception {
        KeyStore ks = getKeyStore(alias, true);

            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias, getProtParam("wallet.keystore", alias));


            assertNotNull(privateKeyEntry);

            PrivateKey privateKey = privateKeyEntry.getPrivateKey();

            Signature signature = Signature.getInstance(((X509Certificate)privateKeyEntry.getCertificate()).getSigAlgName());

            signature.initSign(privateKey);

            signature.update(input.getBytes("UTF-8"));

            byte[] signed = signature.sign();

       return Base64.getEncoder().encodeToString(signed);
    }

    private KeyStore getKeyStore(String alias, boolean sign ) throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException {
        KeyStore ks = null;
        String ksFile = null;

        if (alias.startsWith("wallet-")) {
            ksFile = "/keystores/wallet.keystore";
            if (!sign) {
                ksFile = "/keystores/xup.keystore";

            }
        }
        else if (alias.startsWith("xup-")) {
            ksFile = "/keystores/xup.keystore";
            if (!sign) {
                ksFile = "/keystores/wallet.keystore";

            }
        }

        ks = KeystoreManager.loadKeystore(KeystoreManagerTest.class.getResourceAsStream(ksFile), "storepass");

        return ks;
    }

    private boolean verfiy(String input, String signed, String alias) throws Exception {
        KeyStore ks = getKeyStore(alias, false);

        Certificate cert = ks.getCertificate(alias);

        Signature signature = Signature.getInstance(((X509Certificate) cert).getSigAlgName());

        signature.initVerify(cert.getPublicKey());

        signature.update(input.getBytes("UTF-8"));

        return signature.verify(Base64.getDecoder().decode(signed));
    }
}