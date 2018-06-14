package com.apollo.step.dao;

import java.util.ArrayList;

import com.apollo.vo.ProjectDTO;
import com.apollo.vo.StepDTO;

public interface StepDAO {
	/*
	 ë‚       ì§œ : 2018. 6. 12.
	 ê¸°      ëŠ¥ : í”„ë¡œì íŠ¸ ìƒì„±
	 ì‘ì„±ìëª… : 
	 */
	public ArrayList<StepDTO> getSteps(String pid);
<<<<<<< HEAD
	
	/**
	 * 
	 ³¯      Â¥ : 2018. 6. 14.
	 ±â      ´É : StepÅ¬¸¯½Ã SessionÀ¸·Î °ü¸®ÇÏ´Â ProjectÀÇ Id¸¦ °»½ÅÇØÁÖ±â À§ÇÔ.
	 ÀÛ¼ºÀÚ¸í : ¹Ú ¹Î ½Ä
	 */
	public int getProjectIdByStepId(int sid);
=======

>>>>>>> develop
}
