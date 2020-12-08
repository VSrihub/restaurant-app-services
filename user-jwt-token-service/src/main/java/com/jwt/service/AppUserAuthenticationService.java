/**
 * 
 */
package com.jwt.service;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author nsanda
 *
 */

@Service
public class AppUserAuthenticationService {

	@Value("${appUser.token.expiry}")
	private long tokenExpiry;
	
	@Value("${appUser.token.secreteKey}")
	private String secretKey;
	
	@Value("${appUser.token.header.name}")
	private String headerName;
	
	@Value("${appUser.token.header.value.prefix}")
	private String headerValuePrefix;
	
	private static final String SECRET_USER_KEY = Base64.getEncoder().encodeToString("APP-USER".getBytes());
	
	@Autowired
	private JWTUserDetails jwtUserDetails;
	
	public String createToken(String userName, Collection<? extends GrantedAuthority> roles) {
		Claims claims = Jwts.claims().setSubject(userName);
		claims.put("authorities", roles);
		
		Date now = new Date();
		claims.setIssuedAt(now);
		
		Date validity = new Date(now.getTime()+tokenExpiry);
		
		claims.setExpiration(validity);
		
		return Jwts.builder()
				.addClaims(claims)
				.signWith(SignatureAlgorithm.HS256, SECRET_USER_KEY)
				.compact();
	}

	public Authentication authentication(HttpServletRequest request) throws Exception {
		String token = resolveToken(request);
		if(token != null && !token.isEmpty()) {
			boolean isTokenValid = validateToken(token);
			if(isTokenValid) {
				String userName = getUserName(token);
				UserDetails userDetails = jwtUserDetails.loadUserByUsername(userName);
				return new UsernamePasswordAuthenticationToken(userName, 
						userDetails.getPassword(),
						userDetails.getAuthorities());
			}
		}
		return null;
	}
	
	public UserDetails getUserByToken(HttpServletRequest request) throws Exception {
		String token = resolveToken(request);
		if(token != null && !token.isEmpty()) {
			//boolean isTokenValid = validateToken(token);
				String userName = getUserName(token);
				UserDetails userDetails = jwtUserDetails.loadUserByUsername(userName);
				return userDetails;
		}
		return null;
	}
	
	public String resolveToken(HttpServletRequest request ) {
		String headerVal = request.getHeader(headerName);
		if(headerVal != null && headerVal.startsWith(headerValuePrefix)) {
			headerVal = headerVal.replace(headerValuePrefix, "");
		}
		return headerVal;
	}
	
	public boolean validateToken(String token) throws Exception {
		boolean isTokenValid = false;
		Jws<Claims> userClaims = parseAppUserClaims(token);;
		
		if(!userClaims.getBody().getExpiration().before(new Date())){
			isTokenValid =true;
		}
		return isTokenValid;
	}
	
	public String getUserName(String token) throws Exception {
		Jws<Claims> userClaims = parseAppUserClaims(token);
		
		return userClaims.getBody().getSubject();
	}
	
	private Jws<Claims> parseAppUserClaims(String token) throws Exception{
		Jws<Claims> userClaims = Jwts.parser().setSigningKey(SECRET_USER_KEY)
                .parseClaimsJws(token);
		return userClaims;
	}
	
}
