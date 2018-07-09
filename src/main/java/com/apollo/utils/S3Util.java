/**
 프로젝트 : Apollo
 파일이름 : S3Util.java 
 날      짜 : 2018. 7. 4.
 작 성  자 : 이 진 우
*/

package com.apollo.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Properties;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.iot.model.CannedAccessControlList;
import com.amazonaws.services.mediastoredata.model.GetObjectRequest;
import com.amazonaws.services.mediastoredata.model.PutObjectRequest;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;

public class S3Util {
	private Properties s3key = PropertiesUtil.fetchProperties("Apollo_s3Key");
	
	private String accessKey = s3key.getProperty("s3.accessKey"); // 엑세스 키
	private String secretKey = s3key.getProperty("s3.secretKey"); // 보안 엑세스 키

	private AmazonS3 conn;

	public S3Util() {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		ClientConfiguration clientConfig = new ClientConfiguration();
		clientConfig.setProtocol(Protocol.HTTP);
		this.conn = new AmazonS3Client(credentials, clientConfig);
		conn.setEndpoint("s3.ap-northeast-2.amazonaws.com"); // 엔드포인트 설정 [ 아시아 태평양 서울 ]
	}
	
	/**
	 * 
	 날      짜 : 2018. 7. 5.
	 기      능 : 버킷 리스트 가지고 오는 메서드
	 작성자명 : 이 진 우
	 */
	public List<Bucket> getBucketList() {
		return conn.listBuckets();
	}
	/**
	 * 
	 날      짜 : 2018. 7. 5.
	 기      능 : 버킷 생성 메서드
	 작성자명 : 이 진 우
	 */
	public Bucket createBucket(String bucketName) {
		return conn.createBucket(bucketName);
	}
	/**
	 * 
	 날      짜 : 2018. 7. 5.
	 기      능 : 폴더 생성 메서드 (폴더는 파일명 뒤에 "/"를 붙여야한다)
	 작성자명 : 이 진 우
	 */
	public void createFolder(String bucketName, String folderName) {
		conn.putObject(bucketName, folderName + "/", new ByteArrayInputStream(new byte[0]), new ObjectMetadata());
	}
	/**
	 * 
	 날      짜 : 2018. 7. 5.
	 기      능 : 파일 업로드
	 작성자명 : 이 진 우
	 */
	public void fileUpload(String bucketName, String fileName, byte[] fileData) throws FileNotFoundException {
		String filePath = (fileName).replace(File.separatorChar, '/'); // 파일 구별자를 `/`로 설정(\->/) 이게 기존에 / 였어도 넘어오면서 \로 바뀌는 거같다.
		ObjectMetadata metaData = new ObjectMetadata();

		metaData.setContentLength(fileData.length);   //메타데이터 설정 -->원래는 128kB까지 업로드 가능했으나 파일크기만큼 버퍼를 설정시켰다.
	    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileData); //파일 넣음

		conn.putObject(bucketName, filePath, byteArrayInputStream, metaData);

	}
	/**
	 * 
	 날      짜 : 2018. 7. 5.
	 기      능 : 파일 삭제
	 작성자명 : 이 진 우
	 */
	public void fileDelete(String bucketName, String fileName) {
		String imgName = (fileName).replace(File.separatorChar, '/');
		conn.deleteObject(bucketName, imgName);
		System.out.println("삭제완료");
	}

	
	/**
	 * 
	 날      짜 : 2018. 7. 5.
	 기      능 : 파일 URL 
	 작성자명 : 이 진 우
	 */
	public String getFileURL(String bucketName, String fileName) {
		String imgName = (fileName).replace(File.separatorChar, '/');
		return conn.generatePresignedUrl(new GeneratePresignedUrlRequest(bucketName, imgName)).toString();
	}
	
	
	
	/**
	 * 
	 날      짜 : 2018. 7. 9.
	 기      능 : 파일 다운로드 
	 작성자명 : 김 정 권
	 */
	public void getObject(String bucketName, String downloadPath) {
		
		conn.getObject(bucketName, downloadPath);
		System.out.println("conn에서 다운 완료");
    }
	
}
