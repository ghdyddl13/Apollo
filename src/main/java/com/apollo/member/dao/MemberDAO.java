package com.apollo.member.dao;
import java.util.ArrayList;
import java.util.HashMap;

import com.apollo.vo.GoogleDTO;
import com.apollo.vo.MemberDTO;
import com.apollo.vo.MidpidDTO;
import com.apollo.vo.ProjectDTO;
import com.apollo.vo.StepDTO;
import com.apollo.vo.TidpidDTO;

public interface MemberDAO {
	public ArrayList<MemberDTO> getProjectMemberlist(int pid);
	
	public ArrayList<MemberDTO> getMemberlist(String pid);
	
	public int insertMember(MemberDTO memberdto);
	
	public String getLogin(String mid);

	public int midCheck(String mid);

	public ArrayList<MemberDTO> getInviteMemberList(MidpidDTO midpiddto);
	
	public int insertPmember(MidpidDTO midpiddto);

	public int findPwd(MemberDTO memberdto);
	
	public String findPwdIdCheck(String mid);
	
	public ArrayList<MemberDTO> getTaskAssignees(String tid);
	/*
	 날      짜 : 2018. 6. 18.
	 기      능 : Step 생성시 프로젝트 참여자 명단 불러오기 
	 작성자명 : 김 래 영
	 */
	public ArrayList<MemberDTO> getProjectMemberlist2(int pid);
	/*
	 날      짜 : 2018. 6. 20.
	 기      능 : 프로필 사진 클릭시 프로필모달에 들어갈 멤버정보 불러오기
	 작성자명 : 김 래 영
	 */
	public MemberDTO getProfileInfoMember(String mid);
	

	
	public String getTaskModifierName(String mid);
	public ArrayList<MemberDTO> getSameTaskMemberList(int tid);
	public ArrayList<MemberDTO> getSameProjectButNotSameTaskMemberList(TidpidDTO dto);
	

	/**
	 * 
	 날      짜 : 2018. 6. 24.
	 기      능 : 스텝에 속한 테스트의 어사이니 정보 불러오기 (for filter)
	 작성자명 : 박 민 식
	 */
	public ArrayList<MemberDTO> selectAssigneesBySid(int sid);
	
	/**
	 * 
	 날      짜 : 2018. 6. 25.
	 기      능 : 개인정보수정을 위한 데이터 불러오기
	 작성자명 : 김 래 영
	 */
	public MemberDTO updateMemberInfo(String mid);
	/**
	 * 
	 날      짜 : 2018. 6. 25.
	 기      능 : 개인정보수정
	 작성자명 : 김 래 영
	 */
	public int updateMemberInfo(MemberDTO memberdto);
	
	/**
	 * 
	 날      짜 : 2018. 6. 25.
	 기      능 : 비밀번호 변경
	 작성자명 : 김 래 영
	 */
	public int updatePwd(MemberDTO memberdto);
	/**
	 * 
	 날      짜 : 2018. 6. 26.
	 기      능 : 같은 인증키를 가진 사원목록 가져오기
	 작성자명 : 김 래 영
	 */
	public ArrayList<MemberDTO> selectMemberList(String mid);

	/**
	 * 
	 날      짜 : 2018. 6. 25.
	 기      능 : 멤버 검색결과
	 작성자명 : 박 민 식
	 */
	public ArrayList<MemberDTO> getSearchMembers(HashMap<String, String> map);
/**
 * 
 날      짜 : 2018. 6. 29.
 기      능 : 인증키 없으면 2주후 강퇴
 작성자명 : 신 호 용
 */
	public int freeTrialCheck(String mid);

	
	/**
	 * 
	 날      짜 : 2018. 6. 28.
	 기      능 : 구글아디로 로그인 
	 작성자명 : 이 창 훈
	 */
	public int googleLogin(String email);
	
	/**
	 * 
	 날      짜 : 2018. 6. 28.
	 기      능 : 구글아이디가 member 테이블에 존재하지 않을경우 insert 해주기 
	 작성자명 : 이 창 훈
	 */
	public int googleIdInsert(GoogleDTO googledto);
	
	/**
	 * 
	 날      짜 : 2018. 7. 1.
	 기      능 : mid로 해당 멤버가 속한 프로젝트들 가져오기
	 작성자명 : 김 정 권
	 */
	public ArrayList<ProjectDTO> gerUserProjects(String mid);

	/**
	 * 
	 날      짜 : 2018. 7. 5.
	 기      능 : mid로 해당 맴버에게 이미지 이름을 집어 넣어준다 
	 작성자명 : 이 진 우
	 */
	public int updateImageName(MemberDTO member);

	
	/**
	 * 
	 날      짜 : 2018. 7. 4.
	 기      능 : email 인증키가 인증되면 인증확인 컬럼을 n->y로 변경
	 작성자명 : 이 창 훈
	 */
	public int emailcheck(String mid);
	
	/**
	 * 
	 날      짜 : 2018. 7. 4.
	 기      능 : 입력한 각 이메일에 해당되는 인증키 가져오기
	 작성자명 : 이 창 훈
	 */
	public String emailcheckbymid(String mid);
	
	/**
	 * 
	 날      짜 : 2018. 7. 4.
	 기      능 : 로그인할때 인증확인 컬럼 값 확인하기 (n이면 인증 안됨, y면 인증됨)
	 작성자명 : 이 창 훈
	 */
	public String ischecked(String mid);
	
	/**
	 * 
	 날      짜 : 2018. 7. 5.
	 기      능 : 가장 최근에 만든 프로젝트의 pid를 가져온다
	 작성자명 : 김 정 권
	 */
	public int getminprojectid(String mid);
	
	/**
	 * 
	 날      짜 : 2018. 7. 5.
	 기      능 : step list로 step 담당자 가져오기 
	 작성자명 : 김 래 영
	 */
	public ArrayList<MemberDTO> selectStepAssignees (ArrayList<StepDTO> steplist);
	/**
	 * 
	 날      짜 : 2018. 7. 10.
	 기      능 : MID로 맴버의 이미지 이름을 가지고 온다
	 작성자명 : 이 진 우
	 */
	public String findMemberImage(String mid);
}
