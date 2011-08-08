/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spring_security;

import java.util.HashSet;
import java.util.Set;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author ПК
 */
public class ChatUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String login)
					throws UsernameNotFoundException, DataAccessException {
		hibernate.UserHelper userHelper = new hibernate.UserHelper();
		db_objects.User user = userHelper.getUserByName(login);
		if (user == null) {
			throw new UsernameNotFoundException("Login " + login
							+ " doesn't exist!");
		}

		String username = user.getName();
		String password = user.getPassword();
		final String role = user.getRole();
		boolean notLocked = true;
		boolean notExpired = true;
		boolean enabled = notLocked && notExpired;
		GrantedAuthority auth = new GrantedAuthority() {

			private static final long serialVersionUID = 1L;

			@Override
			public String getAuthority() {
				return role;
			}
		};
		Set<GrantedAuthority> set = new HashSet<GrantedAuthority>();
		set.add(auth);

		UserDetails details = new User(username, password, enabled,
						notExpired, true, notLocked, set);
		return details;
	}
}
