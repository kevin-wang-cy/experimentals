package experimentals.cryptograph;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * @author kevin.wang.cy@gmail.com
 */
public class KeystoreManager {
    public static KeyStore loadKeystore(File keystoreFile, String storepass) throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException {
       KeyStore keystore = KeyStore.getInstance("JCEKS");

       try (InputStream in = Files.newInputStream(keystoreFile.toPath())) {
           keystore.load(in, storepass.toCharArray());
       }

       return keystore;
    }



    public static KeyStore loadKeystore(InputStream in, String storepass) throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException {
        KeyStore keystore = KeyStore.getInstance("JCEKS");

        keystore.load(in, storepass.toCharArray());

        return keystore;
    }
}
