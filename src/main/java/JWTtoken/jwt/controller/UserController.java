package JWTtoken.jwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import JWTtoken.jwt.dto.AuthRequest;
import JWTtoken.jwt.entity.Users;
import JWTtoken.jwt.repository.UsersRepository;
import JWTtoken.jwt.service.JwtService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	UsersRepository userRepository;

	@Autowired
	JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping(value = "/all")
	public String getUsers() {

		List<Users> findAll = userRepository.findAll();
		System.out.println(findAll);
		return "got all";
	}

	@GetMapping(value = "/getuser/{userId}")
	public String getUser(@PathVariable String userId) {
		return "fetchs user successfully:" + userId;
	}

	@PostMapping(value = "/add")
	public String addUser() {
		return "user added successfully";
	}

	@PutMapping(value = "/update")
	public String updateUser() {
		return "user updated successfully";
	}

	@DeleteMapping(value = "/delete/{userId}")
	public String deleteUser(@PathVariable String userId) {
		return "user updated successfully:" + userId;
	}

	@PostMapping(value = "/authenticate")
	public String getAuthenticateToken(@RequestBody AuthRequest authRequest) {
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if (authenticate.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getUsername());
		} else {
			throw new UsernameNotFoundException("Invalid User");
		}

	}

	@GetMapping(value = "/saved")
	public String savedDefeautlt() {
		String pwd = new BCryptPasswordEncoder().encode("abc123");
		String pwd2 = new BCryptPasswordEncoder().encode("suresh123");
		String pwd3 = new BCryptPasswordEncoder().encode("naveen123");
		Users u = new Users(13, "abc", pwd, "USER", true);
		Users u1 = new Users(11, "naveen", pwd3, "ADMIN", true);
		Users u2 = new Users(12, "suresh", pwd2, "USER,ADMIN", true);
		userRepository.save(u);
		userRepository.save(u1);
		userRepository.save(u2);
		return "saved success fully";
	}

}
