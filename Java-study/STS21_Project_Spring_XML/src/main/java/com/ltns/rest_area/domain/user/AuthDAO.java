package com.ltns.rest_area.domain.user;

import com.ltns.rest_area.domain.DAO;

public interface AuthDAO extends DAO {

    public int deleteByUserAuth(UserAuthDTO userAuthDTO);

}