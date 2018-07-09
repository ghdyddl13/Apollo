//package com.apollo.utils;
//
//import java.io.File;
//import java.text.DecimalFormat;
//import java.util.Properties;
//
//import com.amazonaws.AmazonClientException;
//import com.amazonaws.ClientConfiguration;
//import com.amazonaws.Protocol;
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.auth.profile.ProfileCredentialsProvider;
//import com.amazonaws.event.ProgressEvent;
//import com.amazonaws.event.ProgressListener;
//import com.amazonaws.regions.Region;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3Client;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//import com.amazonaws.services.s3.transfer.TransferManager;
//import com.amazonaws.services.s3.transfer.Download;
//
//
//public class DownloadFileUtils {
//	
//	private Properties s3key = PropertiesUtil.fetchProperties("Apollo_s3Key");
//	private String accessKey = s3key.getProperty("s3.accessKey"); // 엑세스 키
//	private String secretKey = s3key.getProperty("s3.secretKey"); // 보안 엑세스 키
//	private AmazonS3 s3;
//	
//    private String bucketName;
//    private Download download;
//    private PutObjectRequest request = null;
//    private static TransferManager tx;
//    private double totalSize;
//    
//    public DownloadFileUtils() {
//         
//		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
//		ClientConfiguration clientConfig = new ClientConfiguration();
//		clientConfig.setProtocol(Protocol.HTTP);
//		this.s3 = new AmazonS3Client(credentials, clientConfig);
//		s3.setEndpoint("s3.ap-northeast-2.amazonaws.com"); // 엔드포인트 설정 [ 아시아 태평양 서울 ]
//    	
//        //Region region = Region.getRegion(Regions.US_EAST_1);
//        //s3.setRegion(region);
//         
//        this.bucketName = "projectapollo";
//        this.tx = new TransferManager(s3);
//         
//        File file = new File("업로드할파일");
//        doDownload(file);
//    }
// 
//    public void doDownload(File f) {
//        // 디렉토리/디렉토리/파일명 으로 keyname을 주기위해 파일명을 제외한 디렉토리를 구한다.
//        request = new GetObjectRequest(bucketName, s3KeyName).withGeneralProgressListener(progressListener);
//         
//        download = this.tx.download(request);
//        totalSize = download.getProgress().getTotalBytesToTransfer();
//    }
// 
//    private String numberFormat(Object value) {
//        DecimalFormat df = new DecimalFormat("#.##");
//        return df.format( value );
//    }
//     
//    int prevPer = -1;
//    public ProgressListener progressListener = new ProgressListener() {
//        double trans = 0;
//        public void progressChanged(ProgressEvent progressEvent) {
//            if (download == null) return;
// 
//            synchronized (this) {
//                trans += progressEvent.getBytesTransferred();
//                int currPer = (int)download.getProgress().getPercentTransferred();
//                if(prevPer < currPer) {
//                    System.out.println(request.getKey() + " - " + numberFormat(trans/1000) + " / " + numberFormat(totalSize/1000) + " : " + currPer + "%");
//                }
//                prevPer = currPer;
//            }
//             
//            switch (progressEvent.getEventCode()) {
//            case ProgressEvent.COMPLETED_EVENT_CODE:
//                System.out.println("100%"); // 전송완료
//                trans = 0;
//                break;
//            case ProgressEvent.FAILED_EVENT_CODE:
//                try {
//                    AmazonClientException e = download.waitForException();
//                } catch (InterruptedException e) {}
//                break;
//            }
//        }
//    };
//}
