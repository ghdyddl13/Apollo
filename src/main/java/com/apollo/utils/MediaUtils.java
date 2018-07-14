/**
 프로젝트 : Apollo
 파일이름 : MediaUtils.java 
 날      짜 : 2018. 7. 10.
 작 성  자 : 이 진 우
*/

package com.apollo.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class MediaUtils {
	private static Map<String, MediaType> mediaMap;

	static{

		mediaMap = new HashMap<String, MediaType>();		
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
	}

	public static MediaType getMediaType(String type){

		return mediaMap.get(type.toUpperCase());
	}
}
