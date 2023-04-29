package com.neto.curso.securityJwt;

import com.auth0.jwt.algorithms.Algorithm;
import com.neto.curso.data.vo.v1.security.TokenVO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key: secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-length: 3600000}")
    private long validityInMilliseconds = 3600000;

    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }
    
    public TokenVO createAccessToken(String username, List<String> roles) {

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        
        String accessToken = getAccessToken(username, roles, now, validity);
        String refreshToken = getAccessToken(username, roles, now);
        return new TokenVO(username, true, now, validity, accessToken, refreshToken);
    }

    private String getAccessToken(String username, List<String> roles, Date now) {
        return "";
    }

    private String getAccessToken(String username, List<String> roles, Date now, Date validity) {
        return "";
    }

}