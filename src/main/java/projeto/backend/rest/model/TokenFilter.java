package projeto.backend.rest.model;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TokenFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String header = req.getHeader("Authorization");

        if(header == null || !header.startsWith("Bearer ")) {
            throw new ServletException("Token inexistente ou mal formatado!");
        }

        // Extraindo apenas o token do cabecalho.
        String authorization = header.substring(7);

        try {
            Jwts.parser().setSigningKey("ninja").parseClaimsJws(authorization).getBody();
        }catch(SignatureException e) {
            throw new ServletException("Token invalido ou expirado!");
        }

        chain.doFilter(request, response);
    }

    public String getAuth( String auth)  throws ServletException{


        if (auth == null || !auth.startsWith("Bearer ")) {
            throw new InternalError("Token inexistente");
        }

        // Extraindo o token do cabecalho.
        String authorization = auth.substring(7);

        try {
            return Jwts.parser().setSigningKey("ninja").parseClaimsJws(authorization).getBody().getSubject();
        } catch (SignatureException e) {
            throw new ServletException("Token invalido ou expirado!");
        }
    }
}
