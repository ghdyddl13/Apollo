/**
 프로젝트 : Apollo
 파일이름 : PropertiesUtil.java 
 날      짜 : 2018. 7. 5.
 작 성  자 : 이 진 우
*/

package com.apollo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.util.ResourceUtils;

public class PropertiesUtil {
	/**
	 * 
	 날      짜 : 2018. 7. 5.
	 기      능 : AWS S3 ACCESS KEY 와 SECRET KEY를 쓰기위해서 Properties file 읽어오는 함수
	 작성자명 : 이 진 우
	 */
    public static Properties fetchProperties(String propertiesFileName) {
        Properties properties = new Properties();
        try {
            File file = ResourceUtils.getFile("classpath:properties/" + propertiesFileName + ".properties");
            InputStream in = new FileInputStream(file);
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
