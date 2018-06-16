package com.apollo.member.dao;

import java.util.ArrayList;

import com.apollo.vo.MemberDTO;
import com.apollo.vo.MidpidDTO;

public interface MemberDAO {
	
	public ArrayList<MemberDTO> getProjectMemberlist(String pid);
	public ArrayList<MemberDTO> getInviteMemberList(MidpidDTO midpiddto);
	public int insertPmember(MidpidDTO midpiddto);

}
