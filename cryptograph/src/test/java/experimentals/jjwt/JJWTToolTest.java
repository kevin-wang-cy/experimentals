package experimentals.jjwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.RsaProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.security.Key;
import java.security.KeyPair;
import java.security.Signature;
import java.time.ZonedDateTime;
import java.util.Base64;

import static org.junit.Assert.*;

/**
 * @author kevin.wang.cy@gmail.com
 */
public class JJWTToolTest {
    KeyPair defaultRsaKP = null;
    @Before
    public void setUp() throws Exception {
        System.out.println(ZonedDateTime.now());
        defaultRsaKP = RsaProvider.generateKeyPair(2048);
        System.out.println(ZonedDateTime.now());
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void generalTest() throws Exception {
        System.out.println(ZonedDateTime.now());

        String jwts = Jwts.builder().setSubject("Kevin Wang")
                .claim("department", "Java")
                .claim("company", "my company")
                .setHeaderParam("header1", "header1 value1")
                .setHeaderParam("header1", "header1 value2")
                .signWith(SignatureAlgorithm.RS256, defaultRsaKP.getPrivate())
                .compact();

        System.out.println(ZonedDateTime.now());

        System.out.println(jwts);

        String[] jwtsa = jwts.split("\\.");

        assertEquals(3, jwtsa.length);

        System.out.println(String.format("%s, %s, %s", jwtsa[0].length(), jwtsa[1].length(), jwtsa[2].length()));

        System.out.println(new String(Base64.getUrlDecoder().decode((jwtsa[0]))));
        System.out.println(new String(Base64.getUrlDecoder().decode((jwtsa[1]))));

        System.out.println(ZonedDateTime.now());
        Signature signature = Signature.getInstance("SHA256withRSA");

        signature.initVerify(defaultRsaKP.getPublic());
        System.out.println(ZonedDateTime.now());

        signature.update((jwtsa[0] + "." + jwtsa[1]).getBytes());

        System.out.println(ZonedDateTime.now());

        assertTrue(signature.verify(Base64.getUrlDecoder().decode(jwtsa[2])));

        System.out.println(ZonedDateTime.now());

        Jwt<Header, Claims> jwt = null;

        // using private key for verify
        jwt= Jwts.parser().setSigningKey(defaultRsaKP.getPrivate()).parse(jwts);

        assertNotNull(jwt);

        assertNotNull(jwt.getHeader());
        assertEquals(SignatureAlgorithm.RS256.getValue(), jwt.getHeader().get("alg"));
        assertEquals("header1 value2", jwt.getHeader().get("header1"));
        assertEquals("Kevin Wang", jwt.getBody().getSubject());
        assertEquals("my company", jwt.getBody().get("company"));
        assertEquals("Java", jwt.getBody().get("department"));



        // using public key for verify
        jwt= Jwts.parser().setSigningKey(defaultRsaKP.getPublic()).parse(jwts);

        assertNotNull(jwt);

        assertNotNull(jwt.getHeader());
        assertEquals(SignatureAlgorithm.RS256.getValue(), jwt.getHeader().get("alg"));
        assertEquals("header1 value2", jwt.getHeader().get("header1"));
        assertEquals("Kevin Wang", jwt.getBody().getSubject());
        assertEquals("my company", jwt.getBody().get("company"));
        assertEquals("Java", jwt.getBody().get("department"));
    }
}