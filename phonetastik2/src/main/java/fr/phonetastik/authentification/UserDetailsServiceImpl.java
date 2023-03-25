package fr.phonetastik.authentification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	// Utilisé par le login Spring
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userService.findUserById(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		} else {
			// classe User de Spring Security
			return org.springframework.security.core.userdetails.User.builder().username(user.getEmail())
					.password(user.getPassword()).authorities(List.of(new SimpleGrantedAuthority("ROLE_USER"))).build();
		}
	}

}