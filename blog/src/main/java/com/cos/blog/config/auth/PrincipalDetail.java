package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.Users;

import lombok.Getter;

@Getter
public class PrincipalDetail implements UserDetails{
	private Users user;
	
	public PrincipalDetail(Users user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 계정이 갖고 있는 권한 목록 리턴
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()->{return "ROLE_"+user.getRoles();});
		return collectors;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// 계정이 만료되지 않았는지 리턴 (true:만료안됨)
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// 계정이 잠겨있지 않았는지 리턴 (true:잠기지 않음)
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 비밀번호가 만료되지 않았는지 리턴 (true:만료안됨)
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 계정이 ㅗ할성화 되어있는지 리턴 (true:활성화)
		return true;
	}
	
}
