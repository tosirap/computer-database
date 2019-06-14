package com.excilys.cdb.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.excilys.cdb.dao.DAOUser;
import com.excilys.cdb.model.CdbUserDetails;
import com.excilys.cdb.model.User;

@Component
public class ServiceUser implements UserDetailsService {
	private DAOUser daoUser;

	public ServiceUser(DAOUser daoUser) {
		super();
		this.daoUser = daoUser;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = daoUser.findbyName(username);
		if(user == null ) {
			 throw new UsernameNotFoundException("user not found !");
		}
		
		return new CdbUserDetails(user);
	}

}
