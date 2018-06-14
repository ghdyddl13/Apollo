package com.apollo.member.dao;

import java.util.ArrayList;

import com.apollo.vo.MemberDTO;

public interface MemberDAO {
	
	public ArrayList<MemberDTO> getProjectMemberlist(String pid);
	public ArrayList<MemberDTO> getMemberlist(String pid);

}
