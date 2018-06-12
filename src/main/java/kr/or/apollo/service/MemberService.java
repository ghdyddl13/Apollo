package kr.or.apollo.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.apollo.dao.MemberDAO;
import kr.or.apollo.vo.MemberDTO;


@Service
public class MemberService {
	
	@Autowired
	private SqlSession sqlsession;
	
	
	public int insertMember(MemberDTO memberdto){
		System.out.println("service insertmember");
		int result = 0;
		MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
		result = dao.insertMember(memberdto);
		return result;
	}
	public String getlogin(String mid){
		System.out.println("service getlogin");
		String result = "";
		MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
		result = dao.getlogin(mid);
		
		
		return result;
	}
	public int midcheck(String mid){
		System.out.println("service midcheck");
		int result=0;
		MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
		result = dao.midcheck(mid);
		System.out.println(result);
		return result;
	}
	
	public int keycheck(String apollokey){
		System.out.println("service midcheck");
		int result=0;
		MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
		result = dao.keycheck(apollokey);
		System.out.println(result);
		return result;
	}
	
	
}