package com.servico_de_usuario.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${security.jwt.signing-key}")
    private String signingKey;

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signingKey); // usa a mesma chave secreta do auth server
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // Endpoints públicos
                .antMatchers("/auth/login", "/auth/register").permitAll()

                // Endpoint restrito apenas a ADMIN
                .antMatchers("/usuario-autenticado/cadastrar").hasRole("ADMIN")

                // Endpoints de documentação do Swagger
                .antMatchers(
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**",
                        "/v3/api-docs.yaml",
                        "/webjars/**"
                ).permitAll()

                // Endpoints de consulta e adição restritos a ADMIN
                .antMatchers("/usuario/consultar", "/cartao/adicionar", "/cartao/listar")
                .hasRole("ADMIN")

                // Endpoints que qualquer usuário autenticado pode acessar
                .antMatchers(
                        "/usuario/auto/consulta",
                        "/usuario/alterar",
                        "/usuario/deletar",
                        "/cartao/remover",
                        "/cartao/listar/by/email",
                        "/cartao/ativar/inativar/**"
                ).authenticated()

                // Bloqueia qualquer outro endpoint não listado
                .anyRequest().denyAll();
    }
}