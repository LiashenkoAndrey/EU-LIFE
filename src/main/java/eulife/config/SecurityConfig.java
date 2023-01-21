package eulife.config;

import eulife.repositories.UserRepository;
import eulife.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity,
                                                UserDetailsService userDetailsService,
                                                BCryptPasswordEncoder encoder) throws Exception {

        var authManager = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authManager
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder);
        return authManager.build();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String[] staticResources  =  {
                "/styles/**",
                "/img/**",
                "/scripts/**",
        };

        http
                .authorizeHttpRequests()
                .requestMatchers(staticResources).permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll();
        http.cors().and().csrf().disable();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(userRepository);
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
