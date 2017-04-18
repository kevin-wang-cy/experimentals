package experimentals.oauth2.sample1.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * @author kevin.wang.cy@gmail.com
 */

@Configuration
public class AuthorizationServerConfiguration {

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            super.configure(security);

        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory()
                    .withClient("svc-account-1")
                    .secret("svc-account-1-secret")
                    .accessTokenValiditySeconds(300)
                    .refreshTokenValiditySeconds(1800)
                    .authorizedGrantTypes("client_credentials", "password")
                    .scopes("resource-server-read", "resource-server-write")
                    .and()
                    .withClient("svc-account-2")
                    .secret("svc-account-2-secret")
                    .accessTokenValiditySeconds(300)
                    .refreshTokenValiditySeconds(1800)
                    .authorizedGrantTypes("client_credentials", "password", "refresh_token", "authorization_code", "implicit")
                    .scopes("scope-1", "scope-2")
                    .additionalInformation("addinfo1:info1", "addinfo2:info2")
                    .authorities("ROLE_RS_READ", "authority1", "authority2")
                    .resourceIds("resoruce-id-1", "resource-id-2")
                    .autoApprove("auto-approve-1", "auto-approve-2")
                    .redirectUris("http://redirect-uri-1", "http://redirect-uri-2")
                    .and()
                    .withClient("svc-account-3")
                    .secret("svc-account-3-secret")
                    .accessTokenValiditySeconds(300)
                    .refreshTokenValiditySeconds(1800)
                    .authorizedGrantTypes("client_credentials", "password", "refresh_token")
                    .scopes("resource-server-read", "resource-server-write")
                    .authorities("ROLE_RS_WRITE")
                    .and()
                    .withClient("svc-account-4")
                    .secret("svc-account-4-secret")
                    .accessTokenValiditySeconds(300)
                    .refreshTokenValiditySeconds(1800)
                    .authorizedGrantTypes("client_credentials", "password", "refresh_token", "authorization_code", "implicit")
                    .scopes("scope-1", "scope-2")
                    .additionalInformation("addinfo1:info1", "addinfo2:info2")
                    .authorities("ROLE_RS_READ", "authority1", "authority2")
                    .resourceIds("oauth2-resource", "resoruce-id-1", "resource-id-2")
                    .autoApprove("auto-approve-1", "auto-approve-2")
                    .redirectUris("http://redirect-uri-1", "http://redirect-uri-2")
                    .and()
                    .withClient("svc-account-5")
                    .secret("svc-account-5-secret")
                    .accessTokenValiditySeconds(300)
                    .refreshTokenValiditySeconds(1800)
                    .authorizedGrantTypes("client_credentials")
                    .scopes("resource-server-read", "resource-server-write")
                    .authorities("ROLE_RS_WRITE", "ROLE_RS_READ");

        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            super.configure(endpoints);

            endpoints.authenticationManager(authenticationManager);


        }

    }


    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            super.configure(resources);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/me").authorizeRequests().anyRequest().authenticated();
        }
    }

}