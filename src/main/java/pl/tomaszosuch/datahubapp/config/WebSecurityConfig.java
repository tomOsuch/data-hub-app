package pl.tomaszosuch.datahubapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import pl.tomaszosuch.datahubapp.enume.Role;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Bean
    public MyBasicAuthenticationEntryPoint getMyBasicAuthenticationEntryPoint() {
        return new MyBasicAuthenticationEntryPoint();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(Role.CUSTOMER.getRoleName()).password(encoder.encode(Role.CUSTOMER.getPassword())).roles(Role.CUSTOMER.getRoleName())
                .and()
                .withUser(Role.CONTENT_MANAGER.getRoleName()).password(encoder.encode(Role.CONTENT_MANAGER.getPassword())).roles(Role.CONTENT_MANAGER.getRoleName());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/h2/**").permitAll()
                .antMatchers(HttpMethod.GET, "/missions/*").permitAll()
                .antMatchers(HttpMethod.GET, "/missions").permitAll()
                .antMatchers(HttpMethod.GET, "/products").permitAll()
                .antMatchers(HttpMethod.GET, "/products/*").permitAll()
                .regexMatchers(HttpMethod.POST, "/missions").hasRole(Role.CONTENT_MANAGER.getRoleName())
                .regexMatchers(HttpMethod.PUT, "/missions").hasRole(Role.CONTENT_MANAGER.getRoleName())
                .regexMatchers(HttpMethod.DELETE, "/missions/*").hasRole(Role.CONTENT_MANAGER.getRoleName())
                .regexMatchers(HttpMethod.POST, "/products").hasRole(Role.CONTENT_MANAGER.getRoleName())
                .regexMatchers(HttpMethod.DELETE, "/products/*").hasRole(Role.CONTENT_MANAGER.getRoleName())
                .regexMatchers(HttpMethod.POST, "/orders").hasRole(Role.CUSTOMER.getRoleName())
                .and()
                .httpBasic()
                .authenticationEntryPoint(getMyBasicAuthenticationEntryPoint())
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }
}
