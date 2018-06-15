package com.apollo.member.service;


import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apollo.member.dao.AuthkeyDAO;
import com.apollo.member.dao.MemberDAO;
import com.apollo.vo.AuthkeyDTO;
import com.apollo.vo.MemberDTO;


@Service
public class MemberService {
	
	@Autowired
	private SqlSession sqlsession;
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 12.
	 기      능 : 회원가입
	 작성자명 : 신 호 용
	 */
	public int insertMember(MemberDTO memberdto){
		System.out.println("service insertmember");
		int result = 0;
		MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
		result = dao.insertMember(memberdto);
		return result;
	}
	/**
	 * 
	 날      짜 : 2018. 6. 12.
	 기      능 : 로그인
	 작성자명 : 신 호 용
	 */
	public String getlogin(String mid){
		System.out.println("service getlogin");
		String result = "";
		MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
		result = dao.getlogin(mid);
		
		return result;
	}
	/**
	 * 
	 날      짜 : 2018. 6. 12.
	 기      능 : 아이디 중복체크
	 작성자명 : 신 호 용
	 */
	public int midcheck(String mid){
		System.out.println("service midcheck");
		System.out.println(mid);
		int result=0;
		MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
		result = dao.midcheck(mid);
		System.out.println(result);
		return result;
	}
	/**
	 * 
	 날      짜 : 2018. 6. 12.
	 기      능 : 키 인증 
	 작성자명 : 신 호 용
	 */
	public int keycheck(String apollokey){
		System.out.println("service midcheck");
		int result=0;
		AuthkeyDAO dao = sqlsession.getMapper(AuthkeyDAO.class);
		result = dao.keycheck(apollokey);
		System.out.println(result);
		return result;

	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 12.
	 기      능 : 인증키 생성하는 mapping method
	 작성자명 : 이 창 훈
	 */
	public int createApollokey(AuthkeyDTO authkeydto) {
		int result = 0;
		AuthkeyDAO dao = sqlsession.getMapper(AuthkeyDAO.class);
		result = dao.createApollokey(authkeydto);
		return result;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 12.
	 기      능 : Password 찾는 mapping method 
	 작성자명 : 이 창 훈
	 */
	public int findpwd(MemberDTO memberdto) {
		System.out.println("memberdto :" + memberdto.getPwd());
		System.out.println("memberdto2 : " + memberdto.getMid());
		System.out.println("memberdto3 : " + memberdto.getMname());
		int result = 0;
		MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
		result = dao.findpwd(memberdto);
		System.out.println("result : " + result);
		return result;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 12.
	 기      능 : Password 찾는 모달에서 id 체크 mapping method 
	 작성자명 : 이 창 훈
	 */
	public String findpwdidcheck(String mid) {
		System.out.println("mid : " + mid);
		String result = "";
		MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
		result = dao.findpwdidcheck(mid);
		//System.out.println("result2 : " + result);
		return result;
		
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 14.
	 기      능 : 태스크 담당자 구하기
	 작성자명 : 박 민 식
	 */
	public ArrayList<MemberDTO> getTaskAssignees(String tid){
		
		ArrayList<MemberDTO> result = null;
		try {
			MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
			result = dao.getTaskAssignees(tid);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return result;
	}
	
}