package com.lec.security.domain;

import java.util.List;

import com.lec.security.config.CustomUserDetails;

public interface DAO {

	public List<DTO> findByUsername(String username);

	public CustomUserDetails findByUserDetail(String username);

}
