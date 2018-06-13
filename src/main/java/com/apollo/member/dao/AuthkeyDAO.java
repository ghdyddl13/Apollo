package com.apollo.member.dao;

import com.apollo.vo.AuthkeyDTO;

public interface AuthkeyDAO {

	public int createApollokey(AuthkeyDTO authkeydto);
	
	public int keycheck(String apollokey);
}
