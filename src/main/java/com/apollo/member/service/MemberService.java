package com.apollo.member.service;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.ibatis.session.SqlSession;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.amazonaws.util.IOUtils;
import com.apollo.member.dao.AuthkeyDAO;
import com.apollo.member.dao.MemberDAO;
import com.apollo.utils.S3Util;
import com.apollo.utils.UploadFileUtils;
import com.apollo.vo.AuthkeyDTO;
import com.apollo.vo.GoogleDTO;
import com.apollo.vo.MemberDTO;
import com.apollo.vo.filedataDTO;



@Service
public class MemberService {
	
	@Autowired
	private SqlSession sqlsession;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private VelocityEngine velocityEngine;
	
	S3Util s3 = new S3Util();
	String bucketName = "projectapollo";
	
	/**
	 * 
	 날      짜 : 2018. 6. 12.
	 기      능 : 회원가입
	 작성자명 : 신 호 용
	 */
	public int insertMember(MemberDTO memberdto){
		int result = 0;
		MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
		result = dao.insertMember(memberdto);
		
		return result;
	}
	
	/**
	 * 
	 날      짜 : 2018. 7. 3.
	 기      능 : 가입시 작성한 email을 통해 인증키를 받아오는 서비스 
	 작성자명 : 이 창 훈
	 */
	public String emailcheckbymid(String mid) {
		String result = "";
		try {
			MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
			result=dao.emailcheckbymid(mid);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 
	 날      짜 : 2018. 7. 3.
	 기      능 : email 인증키가 인증이 된다면 ischecked부분을 n->y로 바꿔줌
	 작성자명 : 이 창 훈
	 */
    public int emailcheck(String mid){
		int result = 0;
        try {
            MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
            result=dao.emailcheck(mid);
        }catch (Exception e) {
			System.out.println(e.getMessage());
		}
        return result;
    }
    
    /**
     * 
     날      짜 : 2018. 7. 3.
     기      능 : ischecked 의 값이 n 인지 y 인지 확인 하는 서비스
     작성자명 : 이 창 훈
     */
	public String ischecked(String mid) {
		String result = "";
		try {
			MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
			result=dao.ischecked(mid);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
     
	
	/**
	 * 
	 날      짜 : 2018. 6. 12.
	 기      능 : 로그인
	 작성자명 : 신 호 용
	 */
	public String getlogin(String mid){
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
		int result=0;
		MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
		result = dao.midCheck(mid);
		return result;
	}
	/**
	 * 
	 날      짜 : 2018. 6. 12.
	 기      능 : 키 인증 
	 작성자명 : 신 호 용
	 */
	public int keycheck(String apollokey){
		int result=0;
		AuthkeyDAO dao = sqlsession.getMapper(AuthkeyDAO.class);
		result = dao.keyCheck(apollokey);
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
	/**
	 날      짜 : 2018. 6. 28.
	 기      능 : 구글 아이디가 가입되어 있다면 구글 아이디로 로그인하기 
	 작성자명 : 이 창 훈
	 */
	public int googleLogin(String email) {
		int result = 0;
		try {
			MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
			result = dao.googleLogin(email);
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return result;
	}
	
	/**
	 * 
	 날      짜 : 2018. 6. 26.
	 기      능 : 같은 인증키를 가진 사원목록 가져오기
	 작성자명 : 김 래 영
	 */
	public ArrayList<MemberDTO> selectMemberList(String mid) {
		ArrayList<MemberDTO> result = null;
		try {
			MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
			result = dao.selectMemberList(mid);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return result;
	}
	/**
	 * 
	 날      짜 : 2018. 6. 28.
	 기      능 : 시험판 기간 제한
	 작성자명 : 신 호 용
	 */
	public int freeTrialCheck(String mid){
		int result = 0;
		MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
		result = dao.freeTrialCheck(mid);
		return result;
	}
	/**
	 날      짜 : 2018. 6. 29.
	 기      능 : 구글아이디로 가입된 아이디가 없으면 해당아이디로 회원가입하기 
	 작성자명 : 이 창 훈
	 */
	public int googleIdInsert(GoogleDTO googledto) {
		int result = 0;
			try {
				MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
				result = dao.googleIdInsert(googledto);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		return result;
	}
	/**
	 * 
	 날      짜 : 2018. 7. 5.
	 기      능 : 프로필 수정하는 함수 
	 작성자명 : 이 진 우
	 */
	public LinkedList<filedataDTO> memberProfileUpdate(String mid, MultipartHttpServletRequest request){
		System.out.println("프로필 수정하는 함수 도착");
		String savepath= "resources/member_profile";
		LinkedList<filedataDTO> files = new LinkedList<filedataDTO>();
		filedataDTO filedata = null;
		
		Iterator<String> itr =request.getFileNames();
		MultipartFile mpf = null;
		while(itr.hasNext()) {
			mpf = request.getFile(itr.next());
			
			//파일 정보가 없는 경우
			if(mpf ==null || mpf.getSize()<=0) {
				return null;
			}
			//fileMetaDTO에 파일정보 입력
			filedata = new filedataDTO();
			System.out.println("파일 이름이에요: "+mpf.getOriginalFilename());
			filedata.setFilename(mpf.getOriginalFilename());
			filedata.setFileSize(mpf.getSize()/1024+"kb");
			filedata.setFileType(mpf.getContentType());
			
			//경로 설정
			String filename =null;
			String originalName = mpf.getOriginalFilename();
			
			//SQL 파일 입력
			MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
			MemberDTO member = new MemberDTO();
			try {
				//FILE DATADTO에 바이트 정보 입력
				filedata.setBytes(mpf.getBytes());
				
				//AWS S3에 파일 업로드
				filename = UploadFileUtils.uploadFile(savepath, 0, originalName, mpf.getBytes());
				
				//DB에 파일 정보 입력
				member.setMid(mid);
				member.setImage(filename);
				dao.updateImageName(member);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			files.add(filedata);
		}
		
		return files;
	}

	
	/**
	 * 
	 날      짜 : 2018. 7. 5.
	 기      능 : 가장 최근에 만든 프로젝트의 pid를 가져온다
	 작성자명 : 김 정 권
	 */
	public int getMinProjectid(String mid) {
		
		System.out.println("getMinProjectid 서비스 실행");
		
		MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
		int pid = dao.getminprojectid(mid);
		
		return pid;
	}
	
	
	
	public ResponseEntity<byte[]> getMemberImage(String fileName) throws Exception{
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		HttpURLConnection uCon = null;

		String inputDirectory = "resources/member_profile/";




		try {
			HttpHeaders headers = new HttpHeaders();
			URL url;
			try {
				url = new URL(s3.getFileURL(bucketName, inputDirectory+fileName));
				uCon = (HttpURLConnection) url.openConnection();
				in = uCon.getInputStream(); // 이미지를 불러옴
			} catch (Exception e) {
				url = new URL(s3.getFileURL(bucketName, "default.jpg"));
				uCon = (HttpURLConnection) url.openConnection();
				in = uCon.getInputStream();
			}

			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}
	

}