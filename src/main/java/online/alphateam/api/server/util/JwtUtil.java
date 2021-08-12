package online.alphateam.api.server.util;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import online.alphateam.api.server.bean.bo.JwtBO;
import online.alphateam.api.server.bean.po.JwtHeader;
import online.alphateam.api.server.bean.po.JwtPayload;


public class JwtUtil {
	public static final String SEPARATOR=".";
	
	/**
	 * 生成token
	 * @param header
	 * @param payload
	 * @param secret
	 * @return
	 * @date 2021-08-08 
	 * @author Michael liangzongc@gmail.com
	 */
	public static String createToken(String header,String payload,String secret) {
		header=Base64.encodeBase64URLSafeString(header.getBytes());
		payload=Base64.encodeBase64URLSafeString(payload.getBytes());		
		String signature=md5(header+"."+payload+"."+secret);		
		String token=header+"."+payload+"."+signature;		
		return token;		
	}
	
	/**
	 * 生成token
	 * @param header
	 * @param payload
	 * @param secret
	 * @return
	 * @date 2021-08-08 
	 * @author Michael liangzongc@gmail.com
	 */
	public static String createToken(JwtHeader header,JwtPayload payload,String secret) {
		String herderCode= Base64.encodeBase64URLSafeString( XUtil.toJSON(header).getBytes() );
		String payloadCode= Base64.encodeBase64URLSafeString( XUtil.toJSON(payload).getBytes() );
		String signature=md5(herderCode+SEPARATOR+payloadCode+SEPARATOR+secret);		
		String token=herderCode+SEPARATOR+payloadCode+SEPARATOR+signature;		
		return token;		
	}
	
	/**
	 * 校验token是否合法
	 * @param token
	 * @param secret
	 * @return
	 * @date 2021-08-07 
	 * @author Michael liangzongc@gmail.com
	 */
	public static boolean authentication(String token , String secret){
		try {
			String[] array=token.split("\\"+SEPARATOR);		
			String header=array[0];
			String payload=array[1];
			String signature=array[2];			
			String realSignature=md5(header+SEPARATOR+payload+SEPARATOR+secret);			
			if( signature.equals(realSignature) ) {
				return true;				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return false;		
	}
	
	public static JwtBO parseToken(String token) {		
		String[] array=token.split("\\"+SEPARATOR);		
		String headerCode=array[0];
		String payloadCode=array[1];
		String signature=array[2];		
		
		String header=new String( Base64.decodeBase64URLSafe(headerCode) );
		String payload=new String( Base64.decodeBase64URLSafe(payloadCode) );
		
		ObjectMapper mapper=new ObjectMapper();	
		JwtBO bo=new JwtBO();
		try {
			JwtHeader jwtHeader=mapper.readValue(header, JwtHeader.class);
			JwtPayload jwtPayload=mapper.readValue(payload, JwtPayload.class);
			bo.setHeader(jwtHeader);
			bo.setPayload(jwtPayload);
			bo.setSignature(signature);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
			throw new RuntimeException("将token解析成JwtBO失败");
		}		
		return bo;		
	}
	
	
	public static String md5(String src){
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			//MD5加密处理
			byte[] output=md.digest(src.getBytes());
			//Base64处理
			String result=Base64.encodeBase64URLSafeString(output);
			return result;
		} catch (Exception e) {
			throw new RuntimeException("加密失败");
		}
	}
}
