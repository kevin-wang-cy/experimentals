package experimentals.oauth2.sample2.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.servlet.Filter;

/**
 * @author kevin.wang.cy@gmail.com
 */

@Configuration
public class SecurityConfiguration {

    @Configuration
    @EnableOAuth2Client
    /**
     * There are 2 features behind @EnableOAuth2Sso: the OAuth2 client, and the authentication.
     *  The client is re-usable, so you can also use it to interact with the OAuth2 resources that your Authorization Server provides.
     *  The authentication piece aligns your app with the rest of Spring Security.
     * The client piece is provided by Spring Security OAuth2 and switched on by a different annotation @EnableOAuth2Client.
     */
    protected static class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        /**
         * With EnableOAuth2Client annotation on, we can inject an OAuth2ClientContext
         * and use it to build an authentication filter #ssoFilter()
         */
        private OAuth2ClientContext oauth2ClientContext;

        @Bean
        @ConfigurationProperties("github.oauth2.client")
        public AuthorizationCodeResourceDetails githubClient() {
            return new AuthorizationCodeResourceDetails();
        }

        @Bean
        @ConfigurationProperties("github.oauth2.resource")
        public ResourceServerProperties githubResource() {
            return new ResourceServerProperties();
        }

        /**
         * acquire an OAuth2 access token from an authorization server, and load an
         * authentication object into the SecurityContext
         */
        private Filter ssoFilter() {
            OAuth2ClientAuthenticationProcessingFilter githubFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/github");
            OAuth2RestTemplate githubTemplate = new OAuth2RestTemplate(githubClient(), oauth2ClientContext);
            githubFilter.setRestTemplate(githubTemplate);
            UserInfoTokenServices tokenServices = new UserInfoTokenServices(githubResource().getUserInfoUri(), githubClient().getClientId());
            tokenServices.setRestTemplate(githubTemplate);
            githubFilter.setTokenServices(tokenServices);
            return githubFilter;
        }

        @Bean
        /**
         * With EnableOAuth2Client annotaiton on, OAuth2ClientContextFilter is created automatically,
         * But need to wire the filter up so that it gets called in the right order in our Spring Boot application.
         * We register it with a sufficiently low order that it comes before the main Spring Security filter.
         * In this way we can use it to handle redirects signaled by expceptions (UserRedirectRequiredException) in authentication requests.
         */
        public FilterRegistrationBean oauth2ClientFilterRegistration(@Autowired OAuth2ClientContextFilter filter) {
            FilterRegistrationBean registration = new FilterRegistrationBean();
            registration.setFilter(filter);
            registration.setOrder(-100);
            return registration;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/**")
                    .authorizeRequests()
                        .antMatchers("/", "/login/**", "/webjars/**").permitAll()
                        .anyRequest().authenticated()
                    .and() /*Spring Security has built in support for a /logout endpoint which will do the right thing for us (clear the session and invalidate the cookie).*/
                        .logout().logoutSuccessUrl("/").permitAll()
                    .and()  /*The /logout endpoint requires us to POST to it, and to protect the user from Cross Site Request Forgery (CSRF, pronounced "sea surf"), it requires a token to be included in the request. The value of the token is linked to the current session, which is what provides the protection, so we need a way to get that data into our JavaScript app.*/
                            /*AngularJS also has built in support for CSRF (they call it XSRF), but it is implemented in a slightly different way than the out-of-the box behaviour of Spring Security. What Angular would like is for the server to send it a cookie called "XSRF-TOKEN" and if it sees that, it will send the value back as a header named "X-XSRF-TOKEN". To teach Spring Security about this we need to add a filter that creates the cookie and also we need to tell the existing CRSF filter about the header name*/
                        .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    .and()
                        .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
        }
    }
}
