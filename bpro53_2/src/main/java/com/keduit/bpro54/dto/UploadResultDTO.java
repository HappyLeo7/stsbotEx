package com.keduit.bpro54.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UploadResultDTO {

	private String fileName;
	private String uuid;
	private String folderPath;
	
	/*
	 * private String getImageURL() throws UnsupportedEncodingException{ return
	 * URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName, "UTF-8"); };
	 */
	private String getImageURL(){
		try {
			return URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	};
	
	public String getThumbnailURL(){
		try {
			return URLEncoder.encode(folderPath+"/s_"+uuid+"_"+fileName,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	
	
	
	
}
