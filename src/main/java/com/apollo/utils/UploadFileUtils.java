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

public class UploadFileUtils {
	/**
	 * 
	 날      짜 : 2018. 7. 5.
	 기      능 : 파일 업로드 할 시에 파일이름을 만들어 S3에 보낸다
	 작성자명 : 이 진 우
	 */
	public static String uploadFile(String uploadPath,int pid, String originalName, byte[] byteData) throws Exception {
		S3Util s3 = new S3Util();
		String bucketName = "projectapollo";
		//랜덤의 uid 를 만들어준다.
		UUID uid = UUID.randomUUID();

		//savedName : 570d570a-7af1-4afe-8ed5-391d660084b7_g.JPG 같은 형식으로 만들어준다=> 이름을 만든다
		String savedName = uid.toString() + "_" + originalName;

		// 프로필 업로드 경로 '/resources/member_profile/1529888132496_image.jpg' => 받아온 파일 프로필 업로드 패스를 업로드하게 이쁘게 만들어줌
		String profileUploadPath = (uploadPath + "/" + savedName).replace(File.separatorChar, '/');
		
		// '/p11/20180628' => 저장 날짜를 보여주기위해 다음과 같은 폴더 구조를 만들엇음
		String savedPath = calcPath(uploadPath, pid) + "/";
		
		// '/p11/20180628/1529888132496_lee.ppt' 저장 구조와 파일 이름 합체!
		String uploadedFileName = (savedPath + savedName).replace(File.separatorChar, '/');
		
		// 파일 업로드 경로 '/resources/upload_files/p11/20180628/1529888132496_lee.ppt' 파일 경로 완전히합체!!
		String fileUploadPath = (uploadPath + uploadedFileName).replace(File.separatorChar, '/');
		
		// 프로필 업로드 pid =0으로 집어넣어줌
		if(pid == 0) {
			s3.fileUpload(bucketName, profileUploadPath, byteData);
			return savedName;
		}
		
		// 파일 업로드
		s3.fileUpload(bucketName, fileUploadPath, byteData);
		return uploadedFileName;
	}
	private static String calcPath(String uploadPath, int pid) {
		Calendar cal = Calendar.getInstance();

		int yearPath = cal.get(Calendar.YEAR);
		String monthPath = yearPath + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		// '/p11/20180628' 식으로 경로 설정 
		String sortedPath = "p" + pid + "/" + datePath;
		makeDir(uploadPath, pid, sortedPath);

		return sortedPath;
	}
	
	private static void makeDir(String uploadPath, int pid, String sortedPath) {
		if (new File(sortedPath).exists()) {
			return;
		}

		File dirPath = new File(uploadPath + sortedPath);

		if (!dirPath.exists()) {
			dirPath.mkdir();
		}
	}
}
