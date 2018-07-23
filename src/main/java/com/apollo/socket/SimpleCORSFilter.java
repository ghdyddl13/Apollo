package com.apollo.socket;
/**
 프로젝트 : Apollo
 파일이름 : SimpleCORSFilter.java 
 날      짜 : 2018. 7. 23.
 작 성  자 : 이 진 우
*/


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;



@Component
public class SimpleCORSFilter implements Filter {

   public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

       HttpServletResponse response = (HttpServletResponse) res;


       response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
       response.setHeader("Access-Control-Max-Age", "3600");
       response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
       
       /*response.setHeader("Access-Control-Allow-Origin", "*");*/
       response.addHeader("Access-Control-Allow-Origin", "http://projectapollo.site/Apollo");

    


       chain.doFilter(req, res);
   }

   public void init(FilterConfig filterConfig) {}

   public void destroy() {}

}