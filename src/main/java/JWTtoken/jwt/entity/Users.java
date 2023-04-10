package JWTtoken.jwt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_dt")
public class Users {

	@Id
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "user_name")
	private String username;
	@Column(name = "user_pwd")
	private String userPwd;
	@Column(name = "user_role")
	private String userRole;
	@Column(name = "user_enable")
	private boolean userEnabled;

	public Integer getUserId() {
		return userId;
	}

	public Users(Integer userId, String username, String userPwd, String userRole, boolean userEnabled) {
		super();
		this.userId = userId;
		this.username = username;
		this.userPwd = userPwd;
		this.userRole = userRole;
		this.userEnabled = userEnabled;
	}

	public Users() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public String getUserRole() {
		return userRole;
	}

	public boolean isUserEnabled() {
		return userEnabled;
	}

}
