package online.alphateam.api.server.bean.po;

public class JwtPayload {
	private String iss;
	private String sub;
	private Long iat;
	private Long exp;
	public String getIss() {
		return iss;
	}
	public void setIss(String iss) {
		this.iss = iss;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public Long getIat() {
		return iat;
	}
	public void setIat(Long iat) {
		this.iat = iat;
	}
	public Long getExp() {
		return exp;
	}
	public void setExp(Long exp) {
		this.exp = exp;
	}
	@Override
	public String toString() {
		return "JwtPayload [iss=" + iss + ", sub=" + sub + ", iat=" + iat + ", exp=" + exp + "]";
	}

}
