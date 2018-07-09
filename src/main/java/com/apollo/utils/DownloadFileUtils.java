/**
 프로젝트 : Apollo
 파일이름 : DownloadFileUtils.java 
 날      짜 : 2018. 7. 9.
 작 성  자 : 김 정 권
*/

package com.apollo.utils;

import javax.servlet.http.HttpServletResponse;

public class DownloadFileUtils {
	
	public static void downloadFileUtils(String filepath, HttpServletResponse response) throws Exception {
		S3Util s3 = new S3Util();
		String bucketName = "projectapollo";
		
		s3.getObject(bucketName, filepath);
	}
}
