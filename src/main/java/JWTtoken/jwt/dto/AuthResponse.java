package JWTtoken.jwt.dto;

public class AuthResponse {
	
	public AuthResponse() {
		super();
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMsg() {
		return msg;
	}
	public AuthResponse(String token, String msg) {
		super();
		this.token = token;
		this.msg = msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	private String token;
	private String msg;
}
