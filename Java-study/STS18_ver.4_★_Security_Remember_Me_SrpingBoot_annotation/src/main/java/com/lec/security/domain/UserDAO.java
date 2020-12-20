package com.lec.security.domain;

import java.util.List;

import com.lec.security.config.security.config.CustomUserDetails;
import com.lec.security.domain.query.UserSQL;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

	@Select(UserSQL.FIND_BY_USERNAME)
	public List<UserDTO> findByUsername(String username);

	@Select(UserSQL.FIND_BY_USERDETAIL)
	public CustomUserDetails findByUserDetails(String username);

}
