package com.aromero.videoserver.security;


import com.aromero.videoserver.security.jwt.JWTokenEntryPoint;
import com.aromero.videoserver.security.services.AuthenticationUserManager;
import com.aromero.videoserver.security.services.SecurityContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {

    private final AuthenticationUserManager authenticationUserManager;
    private final SecurityContextRepository securityContextRepository;
    private final JWTokenEntryPoint jwTokenEntryPoint;

    public WebSecurityConfig(JWTokenEntryPoint jwTokenEntryPoint,
                             AuthenticationUserManager authenticationUserManager,
                             SecurityContextRepository securityContextRepository) {
        this.jwTokenEntryPoint = jwTokenEntryPoint;
        this.authenticationUserManager = authenticationUserManager;
        this.securityContextRepository = securityContextRepository;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .exceptionHandling()
                .authenticationEntryPoint(this.jwTokenEntryPoint)
                .accessDeniedHandler((swe, e) -> {
                    return Mono.fromRunnable(() -> {
                        swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    });
                }).and()
                .csrf().disable()
                .authenticationManager(authenticationUserManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers("/api/v1/films/**").permitAll()
                .pathMatchers("/api/v1/films/poster/**").permitAll()
                .pathMatchers("/api/v1/films/poster").permitAll()
                .anyExchange().authenticated()
                .and().build();
    }
}
