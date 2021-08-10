package com.course2go.authentication;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.course2go.model.user.User;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
public final class TokenUtils {

	private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);
	private static final String secretKey = "giuhawekbh28h78i3Hviu3h";
	
	public static String generateJwtToken(User user) {
		JwtBuilder builder = Jwts.builder()
                .setSubject(user.getUserEmail())
                .setHeader(createHeader())
                .setClaims(createClaims(user))
                .setExpiration(createExpireDateForOneYear())
                .signWith(SignatureAlgorithm.HS256, createSigningKey());

        return builder.compact();
    }

    public static boolean isValidToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            logger.info("expireTime :" + claims.getExpiration());
            logger.info("email :" + claims.get("userEmail"));
            logger.info("nickname :" + claims.get("userNickname"));
            return true;

        } catch (ExpiredJwtException exception) {
            log.error("Token Expired");
            return false;
        } catch (JwtException exception) {
            log.error("Token Tampered");
            return false;
        } catch (NullPointerException exception) {
            log.error("Token is null");
            return false;
        }
    }

    public static String getTokenFromHeader(String header) {
        return header.split(" ")[1];
    }

    private static Date createExpireDateForOneYear() {
        // 토큰 만료시간은 30일으로 설정
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 30);
        return c.getTime();
    }

    private static Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();

        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());

        return header;
    }

    private static Map<String, Object> createClaims(User user) {
        // 공개 클레임에 사용자의 이름과 이메일을 설정하여 정보를 조회할 수 있다.
        Map<String, Object> claims = new HashMap<>();
        logger.debug(user.getUserEmail());
        claims.put("uid", user.getUid());
        claims.put("userEmail", user.getUserEmail());
        claims.put("userNickname", user.getUserNickname());
        claims.put("userImage", user.getUserImage());

        return claims;
    }

    private static Key createSigningKey() {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public static Claims getClaimsFromToken(String token) {
    	logger.info(token);
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(getTokenFromHeader(token)).getBody();
    }

    public static String getUserEmailFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return (String) claims.get("email");
    }

    public static boolean isSameUid(String header, String uid) {
    	logger.info(header);
    	Claims claims = TokenUtils.getClaimsFromToken(header);
    	String tokenUid = (String) claims.get("uid");
    	return tokenUid.equals(uid) ? true : false;
    }
	
    public static boolean isSameNickname(String header, String userNickname) {
    	logger.info(header);
    	Claims claims = TokenUtils.getClaimsFromToken(header);
    	String tokenUserNickname = (String) claims.get("userNickname");
    	return tokenUserNickname.equals(userNickname) ? true : false;
    }
	
    public static String getUidFromToken(String header) {
    	Claims claims = TokenUtils.getClaimsFromToken(header);
    	String tokenUid = (String) claims.get("uid");
    	return tokenUid;
    }
}
