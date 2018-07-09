/**
 프로젝트 : Apollo
 파일이름 : DeleteFileUtils.java 
 날      짜 : 2018. 7. 9.
 작 성  자 : 김 정 권
*/

package com.apollo.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

public class DeleteFileUtils {
	
	public static void deleteFile(String filepath) throws Exception {
		S3Util s3 = new S3Util();
		String bucketName = "projectapollo";
		
		s3.fileDelete(bucketName, filepath);
	}
}
