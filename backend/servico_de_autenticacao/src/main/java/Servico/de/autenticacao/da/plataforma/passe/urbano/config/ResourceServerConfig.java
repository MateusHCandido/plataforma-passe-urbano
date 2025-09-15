package Servico.de.autenticacao.da.plataforma.passe.urbano.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // Endpoints públicos
                .antMatchers("/auth/login", "/auth/register", "/auth/token").permitAll()
                // Endpoint restrito apenas a ADMIN
                .antMatchers("/usuario-autenticado/cadastrar").permitAll()
                // Endpoints de documentação do Swagger
                .antMatchers(
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**",
                        "/v3/api-docs.yaml",
                        "/webjars/**"
                ).permitAll()
                // Qualquer outro endpoint não configurado será negado
                .anyRequest().denyAll();
    }


}
