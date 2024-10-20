package com.enterprisemanager.backend.security.filter;

import java.io.IOException;

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

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IPersonService personService;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {



        //1. Obtener encabezado http llamado Authorization
        String authorizationHeader = request.getHeader("Authorization");//Bearer jwt
        if(!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        //2. Obtener token JWT desde el encabezado
        String jwt = authorizationHeader.split(" ")[1];

        //3. Obtener el subject/username desde el token
        // esta accion a su vez valida el formato del token, firma y fecha de expiración
        String username = jwtService.extractUsername(jwt);

        //4. Setear objeto authentication dentro de security context holder

        Person user = personService.findOneByUsername(username)
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

}

