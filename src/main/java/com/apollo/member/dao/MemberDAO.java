package com.apollo.member.dao;
import java.util.ArrayList;
import com.apollo.vo.MemberDTO;
import com.apollo.vo.MidpidDTO;

public interface MemberDAO {
	public ArrayList<MemberDTO> getProjectMemberlist(String pid);
	
	public ArrayList<MemberDTO> getMemberlist(String pid);
	
	public int insertMember(MemberDTO memberdto);
	
	public String getlogin(String mid);

	public int midcheck(String mid);

	public ArrayList<MemberDTO> getInviteMemberList(MidpidDTO midpiddto);
	
	public int insertPmember(MidpidDTO midpiddto);

	public int findpwd(MemberDTO memberdto);
	
	public String findpwdidcheck(String mid);
	
	public ArrayList<MemberDTO> getTaskAssignees(String tid);
}
