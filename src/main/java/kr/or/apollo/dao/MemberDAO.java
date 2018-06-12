package kr.or.apollo.dao;

import kr.or.apollo.vo.MemberDTO;

public interface MemberDAO {
	
	public int insertMember(MemberDTO memberdto);
	
	public String getlogin(String mid);
	
	public int midcheck(String mid);
	
	public int keycheck(String apollokey);
}
