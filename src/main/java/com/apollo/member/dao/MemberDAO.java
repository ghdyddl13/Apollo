package com.apollo.member.dao;
import java.util.ArrayList;
import com.apollo.vo.MemberDTO;

public interface MemberDAO {
	public ArrayList<MemberDTO> getProjectMemberlist(String pid);
<<<<<<< HEAD
	
	public ArrayList<MemberDTO> getMemberlist(String pid);
	
	public int insertMember(MemberDTO memberdto);
	
	public String getlogin(String mid);
=======
>>>>>>> 06ffad74364a14508020d3850774fb6d9ca45b03

	public int midcheck(String mid);

	public int findpwd(MemberDTO memberdto);
	
	public String findpwdidcheck(String mid);
}
