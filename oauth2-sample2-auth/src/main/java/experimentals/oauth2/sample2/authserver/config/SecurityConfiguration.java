package experimentals.oauth2.sample2.authserver.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * @author kevin.wang.cy@gmail.com
 */

@Configuration
public class SecurityConfiguration {

    @Configuration
    @EnableOAuth2Sso // Spring Boot attaches a special meaning to a WebSecurityConfigurer on the class that carries the @EnableOAuth2Sso annotation: it uses it to configure the security filter chain that carries the OAuth2 authentication processor. So all we need to do to make our home page visible is to explicitly authorizeRequests() to the home page and the static resources it contains (we also include access to the login endpoints which handle the authentication). All other requests (e.g. to the /user endpoint) require authentication.
    protected static class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
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
                        .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        }
    }
}
