package com.apollo.member.dao;

<<<<<<< HEAD
import java.util.ArrayList;

=======
>>>>>>> feature/yy2
import com.apollo.vo.MemberDTO;

public interface MemberDAO {
	
<<<<<<< HEAD
	public ArrayList<MemberDTO> getProjectMemberlist(String pid);
	public ArrayList<MemberDTO> getMemberlist(String pid);

=======
	public int insertMember(MemberDTO memberdto);
	
	public String getlogin(String mid);

	public int midcheck(String mid);

	public int findpwd(MemberDTO memberdto);
	
	public String findpwdidcheck(String mid);
>>>>>>> feature/yy2
}
