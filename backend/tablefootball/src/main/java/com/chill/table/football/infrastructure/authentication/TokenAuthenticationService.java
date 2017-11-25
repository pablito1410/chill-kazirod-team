package com.chill.table.football.infrastructure.authentication;

import com.chill.table.football.application.user.Player;
import com.chill.table.football.application.user.PlayerRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static sun.audio.AudioPlayer.player;

class TokenAuthenticationService {

    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "Chill";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";


    static void addAuthentication(HttpServletResponse res, String username) {
        String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + jwt);

    }

    static Authentication getAuthentication(HttpServletRequest request, PlayerRepository playerRepository) {
        String token = request.getHeader(HEADER_STRING);
        if (token == null) {
            return null;
        } else {
                String userName = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();

            Optional<Player> optPlayer = playerRepository.findByUserName(userName);
            if (optPlayer.isPresent()) {
                return new UsernamePasswordAuthenticationToken(userName, null, Collections.emptyList());
            } else {
                return null;
            }
        }
    }
}
