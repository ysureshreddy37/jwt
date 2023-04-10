package JWTtoken.jwt.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import JWTtoken.jwt.service.CustomeUsersDetails;
import JWTtoken.jwt.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	CustomeUsersDetails customeUsersDetails;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String header = request.getHeader("Authorization");
		String token=null;
		String username=null;
		if(header!=null&&header.startsWith("Bearer ")) {
			token=header.substring(7);
			username=jwtService.extractuserName(token);
		}
		if(username!=null&&SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails loadUserByUsername = customeUsersDetails.loadUserByUsername(username);
			boolean validToken = jwtService.isValidToken(token, loadUserByUsername);
			if(validToken) {
				UsernamePasswordAuthenticationToken authTokem=new UsernamePasswordAuthenticationToken(loadUserByUsername, null,loadUserByUsername.getAuthorities());
				authTokem.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authTokem);
			}
		}
		filterChain.doFilter(request, response);
	}

}
