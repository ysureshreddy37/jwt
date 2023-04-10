package JWTtoken.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import JWTtoken.jwt.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

	public Users findByUsername(String userName);
}
