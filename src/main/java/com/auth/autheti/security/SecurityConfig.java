package com.auth.autheti.security;


import com.auth.autheti.models.SecuModels.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig { //Escificar filtro


    //1

    private final UserDetailsServiceImpl userDetailsServiceImp;

@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsServiceImp);
        authProvider.setPasswordEncoder(passwordEncoder());  // Asegúrate de usar el BCrypt encoder
        return authProvider;
    }

    @Bean //objeto de espeificacion
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/user/UserViews").permitAll()
                        .requestMatchers("/v1/home").authenticated()
                        .requestMatchers("/v1/admin").hasAnyAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsServiceImp)  // Usar tu servicio para cargar los usuarios
                .formLogin(Customizer.withDefaults())    // Habilitar el formulario de login por defecto
                .build();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "12345";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println(encodedPassword);
    }
}
