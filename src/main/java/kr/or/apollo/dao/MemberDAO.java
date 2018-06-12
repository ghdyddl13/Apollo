package kr.or.apollo.dao;

import java.util.ArrayList;

import kr.or.apollo.vo.MemberDTO;
import kr.or.apollo.vo.TaskDTO;

public interface MemberDAO {
	
	public ArrayList<MemberDTO> getProjectMemberlist(String pid);
	public ArrayList<MemberDTO> getMemberlist(String pid);
	
	
}
