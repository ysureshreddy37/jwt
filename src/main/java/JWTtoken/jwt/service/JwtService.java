package JWTtoken.jwt.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	
	@Value("${app.secret.key}")
	private String secret_key;
	
	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims,userName);
	}
	
	
	private String createToken(Map<String,Object> claims, String userName){
		return Jwts.builder().setClaims(claims).setSubject(userName).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*60))
				.signWith(getSignKey(),SignatureAlgorithm.HS256).compact();				
		
	}
	
	private Key getSignKey() {
		
		byte[] decode = Decoders.BASE64.decode(secret_key);
		return Keys.hmacShaKeyFor(decode);
		
	}
	
	public String extractuserName(String token) {
		return extractClaim(token,  Claims::getSubject);
	}
	
	public <T> T extractClaim(String token,Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	
	public Claims extractAllClaims(String token) {
		
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
		
	}
	
	public Date extactExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	public boolean isValidToken(String token, UserDetails userDetails) {
		String userName = extractuserName(token);
		return (userName.equals(userDetails.getUsername()) && ! isTokenExpired(token));
		
	}
	
	public boolean isTokenExpired(String token) {
		return extactExpiration(token).before(new Date());
	}
	
}
