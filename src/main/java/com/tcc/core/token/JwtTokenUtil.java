package com.tcc.core.token;



import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcc.api.dto.NomeDTO;
import com.tcc.doman.model.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;


@Service
public class JwtTokenUtil {

    static final String CLAIM_KEY_TENANT = "tenant";
    static final String CLAIM_KEY_TENANT_ID = "idtenant";


    /**
     * Obtém o tenant contido no token JWT.
     *
     * @param
     * @return String
     */
    public NomeDTO getTenantFromToken() {
        NomeDTO tenant;
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null) {
                return null;

            }

            Map<String, Object> claims = getClaimsFromToken(authentication);
            var nome = claims.get(CLAIM_KEY_TENANT) == null ? null : claims.get(CLAIM_KEY_TENANT).toString();
            var id = claims.get(CLAIM_KEY_TENANT_ID) == null ? null : Integer.parseInt(claims.get(CLAIM_KEY_TENANT_ID).toString());
            tenant = new NomeDTO(id, nome);
        } catch (Exception e) {
            tenant = null;
        }
        return tenant;
    }

    public Usuario getUsuario() {
        Usuario user = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return null;

        }


        try {
            Map<String, Object> claims = getClaimsFromToken(authentication);
           // String id = claims.get("idempresa") == null ? null : claims.get("idempresa").toString();

        } catch (Exception ex) {

        }

        return user;
    }

    public Boolean isTokenExpired(String token) throws IOException {
        final LocalDateTime expiration = getExpirationDateFromToken(token);
        return expiration.isBefore(LocalDateTime.now());
    }

    public LocalDateTime getExpirationDateFromToken(String token) throws IOException {

        var claims = getClaimsFromToken(token);

        if (claims.get("exp") == null) {
            return null;
        }

        String expiration = claims.get("exp").toString();
        var millis = Long.parseLong(expiration) * 1000L;

        LocalDateTime dateExpiration = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());

        return dateExpiration;
    }

    public Map<String, Object> getClaimsFromToken(String token) throws IOException {


        Jwt jwt = JwtHelper.decode(token);
        ObjectMapper objectMapper = new ObjectMapper();
        var claims = objectMapper.readValue(jwt.getClaims(), Map.class);

        return claims;

    }

    /**
     * Realiza o parse do token JWT para extrair as informações contidas no
     * corpo dele.
     *
     * @return Claims
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    private Map<String, Object> getClaimsFromToken(Authentication authentication) throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(authentication.getDetails(), Map.class);

        // create a token object to represent the token that is in use.
        String token = map.get("tokenValue") == null ? "" : map.get("tokenValue").toString();
        Map<String, Object> claims = new HashMap<>();
        if (!StringUtils.isEmpty(token)) {
            Jwt jwt = JwtHelper.decode(token);
            claims = objectMapper.readValue(jwt.getClaims(), Map.class);
        }
        return claims;

    }


}
