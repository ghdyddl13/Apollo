package com.apollo.member.dao;
import java.util.ArrayList;
import com.apollo.vo.MemberDTO;

public interface MemberDAO {
	public ArrayList<MemberDTO> getProjectMemberlist(String pid);
	
	public ArrayList<MemberDTO> getMemberlist(String pid);
	
	public int insertMember(MemberDTO memberdto);
	
	public String getlogin(String mid);

	public int midcheck(String mid);

	public int findPwd(MemberDTO memberdto);
	
	public String findPwdIdCheck(String mid);
}
