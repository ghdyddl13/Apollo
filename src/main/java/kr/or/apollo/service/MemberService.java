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
		int result = 0;
		MemberDAO dao = sqlsession.getMapper(MemberDAO.class);
		result = dao.insertMember(memberdto);
		return result;
	}
	
}