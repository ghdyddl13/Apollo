package com.apollo.member.dao;
import java.util.ArrayList;
import com.apollo.vo.MemberDTO;
import com.apollo.vo.MidpidDTO;

public interface MemberDAO {
	public ArrayList<MemberDTO> getProjectMemberlist(String pid);
	
	public ArrayList<MemberDTO> getMemberlist(String pid);
	
	public int insertMember(MemberDTO memberdto);
	
	public String getLogin(String mid);

	public int midCheck(String mid);

	public ArrayList<MemberDTO> getInviteMemberList(MidpidDTO midpiddto);
	
	public int insertPmember(MidpidDTO midpiddto);

	public int findPwd(MemberDTO memberdto);
	
	public String findPwdIdCheck(String mid);
	
	public ArrayList<MemberDTO> getTaskAssignees(String tid);

}
