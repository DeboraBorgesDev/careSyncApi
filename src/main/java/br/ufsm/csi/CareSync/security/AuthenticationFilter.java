package br.ufsm.csi.CareSync.security;


import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.ufsm.csi.CareSync.service.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private final TokenServiceJWT tokenServiceJWT;
  private final AuthenticationService authenticationService;

  public AuthenticationFilter(TokenServiceJWT tokenJWT, AuthenticationService authentication) {
    this.tokenServiceJWT = tokenJWT;
    this.authenticationService = authentication;
  }
 

  @Override
  protected void doFilterInternal( HttpServletRequest request,
      HttpServletResponse response,
       FilterChain filterChain)
      throws ServletException, IOException {
    System.out.println("Filtro para autenticação e autorização");

    String tokenJWT = recuperarToken(request);

    if (tokenJWT != null) {
      String subject = this.tokenServiceJWT.getSubject(tokenJWT);

      UserDetails userDetails = this.authenticationService.loadUserByUsername(subject);
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
          null, userDetails.getAuthorities());

      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    filterChain.doFilter(request, response);
  }

  private String recuperarToken(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    System.out.println("token: " + token);
    if (token != null) {
      return token.replace("Bearer", "").trim();
    }
    return null;
  }
}