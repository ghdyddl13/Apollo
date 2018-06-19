package com.apollo.member.dao;

import com.apollo.vo.AuthkeyDTO;

public interface AuthkeyDAO {

	public int createApolloKey(AuthkeyDTO authkeydto);
	
	public int keyCheck(String apollokey);

}
