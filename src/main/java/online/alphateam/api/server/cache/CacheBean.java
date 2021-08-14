package online.alphateam.api.server.cache;

public class CacheBean<T> {
	private T data;
	private long expiryTime;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public long getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(long expiryTime) {
		this.expiryTime = expiryTime;
	}
	
}
