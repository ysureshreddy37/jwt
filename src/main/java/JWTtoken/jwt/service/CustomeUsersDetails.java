package JWTtoken.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import JWTtoken.jwt.dto.UserInfoDetails;
import JWTtoken.jwt.entity.Users;
import JWTtoken.jwt.repository.UsersRepository;

@Service
public class CustomeUsersDetails implements UserDetailsService {

	@Autowired
	UsersRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users findByUserName = userRepository.findByUsername(username);
		// TODO Auto-generated method stub
		
		return new UserInfoDetails(findByUserName);
	}

}
