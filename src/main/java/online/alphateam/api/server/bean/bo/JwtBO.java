package online.alphateam.api.server.bean.bo;

import online.alphateam.api.server.bean.po.JwtHeader;
import online.alphateam.api.server.bean.po.JwtPayload;
/**
 * 
 * www.alphateam.online
 * @Description 
 * @date 2021-08-08 
 * @author Michael liangzongc@gmail.com
 */
public class JwtBO {
	private JwtHeader header;
	private JwtPayload payload;
	private String signature;
	public JwtHeader getHeader() {
		return header;
	}
	public void setHeader(JwtHeader header) {
		this.header = header;
	}
	public JwtPayload getPayload() {
		return payload;
	}
	public void setPayload(JwtPayload payload) {
		this.payload = payload;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	@Override
	public String toString() {
		return "JwtBO [header=" + header + ", payload=" + payload + ", signature=" + signature + "]";
	}
	
	
}
