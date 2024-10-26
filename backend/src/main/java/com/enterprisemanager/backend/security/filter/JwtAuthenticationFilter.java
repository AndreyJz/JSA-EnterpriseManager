package com.enterprisemanager.backend.security.filter;

import java.io.IOException;
import java.util.Optional;

import com.enterprisemanager.backend.domain.entities.security.JwtToken;
import com.enterprisemanager.backend.infrastructure.repositories.jwttoken.JwtTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.enterprisemanager.backend.application.services.IPersonService;
import com.enterprisemanager.backend.application.services.auth.JwtService;
import com.enterprisemanager.backend.domain.entities.Person;
import com.enterprisemanager.backend.infrastructure.utils.exceptions.ObjectNotFoundException;

import org.springframework.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IPersonService userService;

    @Autowired
    private JwtTokenRepository jwtRepository;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = jwtService.extractJwtFromRequest(request);
        if(jwt == null || !StringUtils.hasText(jwt)){
            filterChain.doFilter(request, response);
            return;
        }

        Optional<JwtToken> token = jwtRepository.findByToken(jwt);
        boolean isValid = validateToken(token);

        if(!isValid){
            filterChain.doFilter(request, response);
            return;
        }

        //3. Obtener el subject/username desde el token
        // esta accion a su vez valida el formato del token, firma y fecha de expiración
        String username = jwtService.extractUsername(jwt);

        //4. Setear objeto authentication dentro de security context holder

        Person user = userService.findOneByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User not found. Username: " + username));

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                username, null, user.getAuthorities()
        );
        authToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
        System.out.println("Se acaba de setear el authentication");

        //5. Ejecutar el registro de filtros
        filterChain.doFilter(request, response);
    }

    private boolean validateToken(Optional<JwtToken> optionalJwtToken) {

        if(!optionalJwtToken.isPresent()){
            System.out.println("Token no existe o no fue generado en nuestro sistema");
            return false;
        }

        JwtToken token = optionalJwtToken.get();
        Date now = new Date(System.currentTimeMillis());
        boolean isValid = token.isValid() && token.getExpiration().after(now);

        if(!isValid){
            System.out.println("Token inválido");
            updateTokenStatus(token);
        }

        return isValid;
    }
    private void updateTokenStatus(JwtToken token) {
        token.setValid(false);
        jwtRepository.save(token);
    }


}

