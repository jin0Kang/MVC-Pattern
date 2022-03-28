package com.javalec.bbs.dto;

import java.sql.Timestamp;

public class BDto {
	
	//database 가져오는 틀(자바에서 빈이라고 하던걸 여기서 mvc 패턴에서 디티오라고 하는거. 어차피 다 클라) 
	//테이블 하나 당 dto 하나. 내가 검색해오려는 테이블 틀에 맞게.
	//조인 해서 가져오는 거면 그것도 따로 만드는 거고. 
	//데이터를 어떤 거 어느 자리에 넣어서 갖고 올건지 틀 만드는 거임!!!!!!!!!!! 
	
	
	//Field 
	// db에서 가져오는 거니까 당연히 칼럼이름 맞춰야 함. 
	int bId; 
	String bName; 
	String bTitle; 
	String bContent; 
	Timestamp bDate; 

	
	//Constructor 
	
	public BDto() {
		// TODO Auto-generated constructor stub
	}


	public BDto(int bId, String bName, String bTitle, String bContent, Timestamp bDate) {
		super();
		this.bId = bId;
		this.bName = bName;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bDate = bDate;
	}


	//Method 
	
	public int getbId() {
		return bId;
	}


	public void setbId(int bId) {
		this.bId = bId;
	}


	public String getbName() {
		return bName;
	}


	public void setbName(String bName) {
		this.bName = bName;
	}


	public String getbTitle() {
		return bTitle;
	}


	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}


	public String getbContent() {
		return bContent;
	}


	public void setbContent(String bContent) {
		this.bContent = bContent;
	}


	public Timestamp getbDate() {
		return bDate;
	}


	public void setbDate(Timestamp bDate) {
		this.bDate = bDate;
	}
	
	
	
	

	
	
	
	
	
}//
