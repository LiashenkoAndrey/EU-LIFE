package eulife.config;

import eulife.repositories.UserRepository;
import eulife.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;


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

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests().requestMatchers("/**").hasRole("USER").and()
//                .formLogin()
//                .loginPage("/custom_login_page")
//                .defaultSuccessUrl("/", true)
//                .loginProcessingUrl("/login")
//                .permitAll();
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String[] staticResources  =  {
                "/styles/**",
                "/img/**",
                "/scripts/**",
        };

        http
                .authorizeHttpRequests()
//                .requestMatchers("/question/new").authenticated()
//                .requestMatchers("/article/new").authenticated()
//                .requestMatchers("/comment/new").authenticated()
//                .requestMatchers("/profile/**").authenticated()
                .requestMatchers(staticResources).permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll();
        return http.build();
    }

//    @Bean
//    public ViewControllerRegistry addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login").setViewName("custom_login_page");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    }


    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(userRepository);
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }







}
