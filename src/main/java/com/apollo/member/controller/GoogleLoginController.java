
package com.apollo.member.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.plus.PlusOperations;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/*
  @project Final_Picsion
  @package kr.or.picsion.utils.controller 
  @className GoogleLoginController
  @date 2018. 6. 18.*/

import com.apollo.member.service.MemberService;
import com.apollo.vo.GoogleDTO;
import com.apollo.vo.MemberDTO;
	

@Controller
public class GoogleLoginController {

   private final String GOOGLE = "google";
   
   @Autowired
	private MemberService memberservice;
   
   @Autowired
   private GoogleConnectionFactory googleConnectionFactory;
   
   @Autowired
   private OAuth2Parameters googleOAuth2Parameters;

   /**
    * 
    날      짜 : 2018. 6. 29.
    기      능 : google login 하기위해 토큰 생성하기 
    작성자명 : 이 창 훈
    */
   @RequestMapping("/google.htm")
   public String login(Model model) {
      OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
      String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
      model.addAttribute("url",url);
      return "google";
   }
   
   /**
    * 
    날      짜 : 2018. 6. 29.
    기      능 : google login 각 토큰값 가져와서 DTO 값으로 저장해 처리하기 
    작성자명 : 이 창 훈
    */
   @RequestMapping(value = "/googlelogin.htm", method = RequestMethod.GET)
   public String doSessionAssignActionPage(HttpServletRequest request, HttpSession session, GoogleDTO googledto, Model model){
     String code = request.getParameter("code");
     OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
     
     AccessGrant accessGrant = oauthOperations.exchangeForAccess(code ,googleOAuth2Parameters.getRedirectUri() , null);
     String accessToken = accessGrant.getAccessToken();
     Long expireTime = accessGrant.getExpireTime();
 
     if (expireTime != null && expireTime < System.currentTimeMillis()) {
       accessToken = accessGrant.getRefreshToken();
     }
     Connection<Google> connection = googleConnectionFactory.createConnection(accessGrant);
     Google google = connection == null ? new GoogleTemplate(accessToken) : connection.getApi();

     PlusOperations plusOperations = google.plusOperations();
     Person profile = plusOperations.getGoogleProfile();
     String id = profile.getId();
     String name = profile.getDisplayName();
     String mid = profile.getAccountEmail();
     String viewpage="";
     
     googledto.setGemail(mid);
     googledto.setGid(id);
     googledto.setGname(name);
    
 
     
     int result = memberservice.googleLogin(mid);
     if(result == 1) {
    	 session.setAttribute("mid", mid);
    	 viewpage = "redirect:/main.htm";
    	 
     }else {
    	 memberservice.googleIdInsert(googledto);
    	 memberservice.emailcheck(mid);
    	 viewpage = "redirect:/google.htm";
     }
     
     
     return viewpage;
   }   
   
   
   
}