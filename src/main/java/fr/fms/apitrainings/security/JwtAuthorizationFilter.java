package fr.fms.apitrainings.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        String token = request.getHeader(SecuityConstants.HEADER_STRING); // récupère le token

        if(token != null && token.startsWith(SecuityConstants.TOKEN_PREFIX)){
            try {
                String jwtToken = token.substring(7);
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SecuityConstants.SECRET)).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);

                String username = decodedJWT.getSubject();
                String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                for(String role : roles) authorities.add(new SimpleGrantedAuthority(role));

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } catch (Exception e){
                response.setHeader(SecuityConstants.ERROR_MSG, e.getMessage());
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        }
        filterChain.doFilter(request,response);
    }
}
