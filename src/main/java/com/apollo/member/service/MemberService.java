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
		result = dao.getLogin(mid);
		
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
		result = dao.midCheck(mid);
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
		result = dao.keyCheck(apollokey);
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
		result = dao.createApolloKey(authkeydto);
		return result;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 12.
	 기      능 : Password 찾는 mapping method 
	 작성자명 : 이 창 훈
	 */
	public int findpwd(MemberDTO memberdto) {
		int result = 0;
		MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
		result = dao.findPwd(memberdto);
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
		String result = "";
		MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
		result = dao.findPwdIdCheck(mid);
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
	
	/*
	 날      짜 : 2018. 6. 20.
	 기      능 : 프로필 사진 클릭시 프로필모달에 들어갈 멤버정보 불러오기
	 작성자명 : 김 래 영
	 */
	public MemberDTO getProfileInfoMember(String mid) {
		MemberDTO profileinfo = null;
		try {
			MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
			profileinfo = dao.getProfileInfoMember(mid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profileinfo;
		
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 25.
	 기      능 : 개인정보수정을 위한 데이터 불러오기
	 작성자명 : 김 래 영
	 */
	public MemberDTO updateMemberInfo(String mid) {
		MemberDTO result = null;
		try {
			MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
			result = dao.getProfileInfoMember(mid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 25.
	 기      능 : 개인정보수정
	 작성자명 : 김 래 영
	 */
	public int updateMemberInfo(MemberDTO memberdto) {
		int result = 0;
		try {
			MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
			result = dao.updateMemberInfo(memberdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 6. 25.
	 기      능 : 비밀번호 변경
	 작성자명 : 김 래 영
	 */
	public int updatePwd(MemberDTO memberdto) {
		int result = 0;
		try {
			MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
			result = dao.updatePwd(memberdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
}