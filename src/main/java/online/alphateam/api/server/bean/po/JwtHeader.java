package online.alphateam.api.server.bean.po;

public class JwtHeader {
	private String typ;
	private String alg;
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	public String getAlg() {
		return alg;
	}
	public void setAlg(String alg) {
		this.alg = alg;
	}
	@Override
	public String toString() {
		return "JwtHeader [typ=" + typ + ", alg=" + alg + "]";
	}
	
	
}
