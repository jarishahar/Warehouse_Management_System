package com.jsp.wms.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jsp.wms.entity.Admin;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailImpl implements UserDetails{

	private Admin admin;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		admin.getAdminType().getPrivileges()
		.stream()
		.map(priviliges ->
		new SimpleGrantedAuthority(priviliges.name())
		).toList();
		
		return null;
	}

	@Override
	public String getPassword() {
		
		return admin.getPassword();
	}

	@Override
	public String getUsername() {
		
		return admin.getEmail();
	}

}