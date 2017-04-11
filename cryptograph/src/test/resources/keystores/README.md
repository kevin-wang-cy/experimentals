```bash
$ keytool -genkeypair -alias wallet-default-test \
        -dname "CN=default-test, OU=Wallet Gateway API, O=upbchain, C=CN" \
        -keyalg RSA \
        -keysize 2048 \
        -sigalg SHA256withRSA \
        -keypass keypass \
        -validity 180 \
        -storetype JKS \
        -keystore wallet.keystore \
        -storepass storepass
```

```bash
$ keytool -genkeypair -alias xup-default-test \
        -dname "CN=default-test, OU=XUP Interface, O=upbchain, C=CN" \
        -keyalg RSA \
        -keysize 2048 \
        -sigalg SHA256withRSA \
        -keypass keypass \
        -validity 180 \
        -storetype JKS \
        -keystore xup.keystore \
        -storepass storepass
```

```bash

$ keytool -exportcert -alias wallet-default-test \
        -file wallet-default-test.cert \
        -storetype JKS \
        -keystore wallet.keystore \
        -storepass storepass 

```

```bash
$ keytool -exportcert -alias xup-default-test \
        -file xup-default-test.cert \
        -storetype JKS \
        -keystore xup.keystore \
        -storepass storepass 

```

```bash

$ keytool -list -keystore wallet-default-test

```

```bash
$ keytool -genkeypair -alias wallet-zhuhai-test \
        -dname "CN=zhuhai-test, OU=Wallet Gateway API, O=upbchain, C=CN" \
        -keyalg RSA \
        -keysize 2048 \
        -sigalg SHA256withRSA \
        -keypass keypass \
        -validity 180 \
        -storetype JKS \
        -keystore wallet.keystore \
        -storepass storepass
```


```bash

$ keytool -list -keystore wallet-default-test

```

```bash

$ keytool -exportcert -alias wallet-zhuhai-test \
        -file wallet-zhuhai-test.cert \
        -storetype JKS \
        -keystore wallet.keystore \
        -storepass storepass 

```



```bash

$ keytool -printcert -file wallet-zhuhai-test.cert


$ keytool -printcert -file wallet-zhuhai-test.cert -rfc

```



```bash

$ keytool -importcert -alias xup-default-test \
            -file xup-default-test.cert \
            -keystore wallet.keystore \
            -storepass storepass

```

```bash

$ keytool -importcert -alias wallet-default-test \
            -file wallet-default-test.cert \
            -noprompt \
            -keystore xup.keystore \
            -storepass storepass

```


```bash
$ keytool -genkeypair -alias wallet-ec-test \
        -dname "CN=ec-test, OU=Wallet Gateway API, O=upbchain, C=CN" \
        -keyalg EC \
        -keysize 256 \
        -sigalg SHA256withECDSA \
        -keypass keypass \
        -validity 180 \
        -storetype JKS \
        -keystore wallet.keystore \
        -storepass storepass
$ keytool -exportcert -alias wallet-ec-test \
          -file wallet-ec-test.cert \
          -storetype JKS \
          -keystore wallet.keystore \
          -storepass storepass 
$ keytool -importcert -alias wallet-ec-test \
              -file wallet-ec-test.cert \
              -noprompt \
              -keystore xup.keystore \
              -storepass storepass     
```