package online.alphateam.api.server.util;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;


public class JwtUtil {
	public static String createToken(String header,String payload,String secret) {
		header=Base64.encodeBase64URLSafeString(header.getBytes());
		payload=Base64.encodeBase64URLSafeString(payload.getBytes());		
		String signature=md5(header+"."+payload+"."+secret);		
		String token=header+"."+payload+"."+signature;		
		return token;		
	}
	
	/**
	 * 校验token是否合法
	 * @param token
	 * @param secret
	 * @return
	 * @date 2021-08-07 
	 * @author 梁文正 liangzongc@gmail.com
	 */
	public static boolean authentication(String token , String secret){
		try {
			String[] array=token.split("\\.");		
			String header=array[0];
			String payload=array[1];
			String signature=array[2];			
			String realSignature=md5(header+"."+payload+"."+secret);			
			if( signature.equals(realSignature) ) {
				return true;				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return false;		
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
