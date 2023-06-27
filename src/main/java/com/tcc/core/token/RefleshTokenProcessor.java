package com.tcc.core.token;


import com.tcc.core.properties.ChronosApiProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Profile("oauth-security")
@ControllerAdvice
public class RefleshTokenProcessor implements ResponseBodyAdvice<OAuth2AccessToken> {

    @Autowired
    private ChronosApiProperties apiProperty;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return methodParameter.getMethod().getName().equals("postAccessToken");
    }

    @Override
    public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {


        HttpServletRequest request = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
        HttpServletResponse response = ((ServletServerHttpResponse) serverHttpResponse).getServletResponse();


        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) body;

        if (body !=null  && body.getRefreshToken() != null) {

            String refleshToekn = body.getRefreshToken().getValue();
            adicionarRefleshCookie(refleshToekn, request, response);
            removerRefleshTokenBody(token);
        }


        return body;
    }

    private void removerRefleshTokenBody(DefaultOAuth2AccessToken token) {
        token.setRefreshToken(null);
    }

    private void adicionarRefleshCookie(String refleshToekn, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("refreshToken", refleshToekn);
        cookie.setHttpOnly(true);
        cookie.setSecure(apiProperty.getSeguranca().isEnableHttps());
        cookie.setPath(request.getContextPath() + "/oauth/token");
        cookie.setMaxAge(2592000);
        response.addCookie(cookie);
    }
}
