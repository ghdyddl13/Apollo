package com.apollo.member.dao;
import java.util.ArrayList;
import com.apollo.vo.MemberDTO;
import com.apollo.vo.MidpidDTO;
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
	public int updatePwd(int mid);
}
