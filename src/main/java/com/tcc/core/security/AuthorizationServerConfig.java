package com.tcc.core.security;



import com.tcc.core.token.CustomTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;



@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Qualifier("appUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String PASSWORD = "password";
        clients.inMemory()

                //usuario e senha do cliente( da aplicação que está fazendo a requisição)
                .withClient("appweb").secret("$2a$10$RTrtYuKqgyCtnRjSrvqd8e/UQeH4Gqn3IuhONH/Fk9HWGO6VKG5SC") //w3bcl13nt
                // escopo usado para o acesso a api
                .scopes("read", "write")
                //
                .authorizedGrantTypes(PASSWORD, "refresh_token")
                // tempo definido para o token 1800
                // 1hr = 1 * 60 * 60
                .accessTokenValiditySeconds(4 * 60 * 60)
                // tempo de vida do reflesh token 3600 * 24
                .refreshTokenValiditySeconds(4 * 60 * 60)
                .and()
                //usuario e senha do cliente( da aplicação que está fazendo a requisição)
                .withClient("pdv").secret("$2a$10$8U8BPXlDJ6s3C0zhn2IO0.M95MarWvNWkYnBLD1Sqs9fBlAR9YUHy")//pdvcl13nt
                // escopo usado para o acesso a api
                .scopes("read", "write")
                //
                .authorizedGrantTypes(PASSWORD, "refresh_token")
                // tempo definido para o token 1800
                .accessTokenValiditySeconds(1800)
                // tempo de vida do reflesh token 3600 * 24
                .refreshTokenValiditySeconds(3600 * 24)
                .and()
                //usuario e senha do cliente( da aplicação que está fazendo a requisição)
                .withClient("mobile").secret("$2a$10$uo9dlF2wJyCsw7aCMdUit.RquAwzV6VrmM2OiejgX4zvaFEPU9tzW")//m0b1l3
                // escopo usado para o acesso a api
                .scopes("read")
                //
                .authorizedGrantTypes(PASSWORD, "refresh_token")
                // tempo definido para o token
                .accessTokenValiditySeconds(1800)
                // tempo de vida do reflesh token
                .refreshTokenValiditySeconds(3600 * 24)
                .and()
                // usuario e senha do cliente( da aplicação que está fazendo a requisição)
                .withClient("integracao").secret("$2a$10$8U8BPXlDJ6s3C0zhn2IO0.M95MarWvNWkYnBLD1Sqs9fBlAR9YUHy")//pdvcl13nt
                // escopo usado para o acesso a api
                .scopes("read", "write")
                //
                .authorizedGrantTypes("client_credentials");


    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancerChain(), accessTokenConverter()));
        endpoints
                .tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancerChain)
                .reuseRefreshTokens(false)
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager);
    }


    @Bean
    public TokenEnhancer tokenEnhancerChain() {
        return new CustomTokenEnhancer();
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey("#Chr0n0s1nf0_s1gn1ng_K31_f0r_jwt#");
        return accessTokenConverter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());

    }


}
