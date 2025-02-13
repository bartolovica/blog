package antun.bart.blog.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static antun.bart.blog.controller.ControllerDefinition.READ_API;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig {

    @Value("${users.one.name}")
    private String userName;
    @Value("${users.one.password}")
    private String password;
    @Value("${users.two.name}")
    private String adminName;
    @Value("${users.two.password}")
    private String adminPassword;
    @Value("${encryptionType}")
    private String encryptionType;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(READ_API + "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        return new InMemoryUserDetailsManager(
                User.withUsername(adminName).password(encryptionType + adminPassword).roles("ADMIN").build(),
                User.withUsername(userName).password(encryptionType + password).roles("VISITOR").build()
        );
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
