package com.chill.table.football.infrastructure.authentication;

import com.chill.table.football.application.user.User;
import com.chill.table.football.application.user.UserFinder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;

public class TokenAuthenticationService {

    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "Chill";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";


    public static void addAuthentication(HttpServletResponse res, String username) {
        String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + jwt);

    }

    public static Authentication getAuthentication(HttpServletRequest request, UserFinder userFinder) {
        String token = request.getHeader(HEADER_STRING);
        if (token == null) {
            return null;
        } else {
                String userName = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();

            final User user = userFinder.getUserByName(userName);
            return user != null
                    ? new UsernamePasswordAuthenticationToken(userName, null, Collections.emptyList())
                    : null;
        }
    }
}
