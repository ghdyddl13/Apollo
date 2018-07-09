/**
 프로젝트 : Apollo
 파일이름 : UploadFileUtils.java 
 날      짜 : 2018. 7. 4.
 작 성  자 : 이 진 우
*/

package com.apollo.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

public class DownloadFileUtils {
	/**
	 * 
	 날      짜 : 2018. 7. 8.
	 기      능 : 파일 다운
	 작성자명 : 김 정 권
	 */
	public static void downFiles(String downloadPath) throws Exception {
		S3Util s3 = new S3Util();
		String bucketName = "projectapollo";
	
		// 파일 다운로드
		s3.getObject(bucketName, downloadPath);
	
	}
}