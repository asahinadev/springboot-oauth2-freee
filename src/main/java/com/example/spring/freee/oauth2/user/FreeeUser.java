package com.example.spring.freee.oauth2.user;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class FreeeUser
		implements OAuth2User, Serializable {

	@JsonAnySetter
	Map<String, Object> extra = new HashMap<>();

	@Override
	public String getName() {
		return String.valueOf(getExtra().get("id"));
	}

	@Override
	public List<GrantedAuthority> getAuthorities() {
		return Arrays.asList(
				new OAuth2UserAuthority("USER", getAttributes()),
				new SimpleGrantedAuthority("USER"));
	}

	@Override
	public Map<String, Object> getAttributes() {

		Map<String, Object> attributes = new HashMap<>();
		attributes.putAll(getExtra());
		return Collections.unmodifiableMap(attributes);
	}

}
