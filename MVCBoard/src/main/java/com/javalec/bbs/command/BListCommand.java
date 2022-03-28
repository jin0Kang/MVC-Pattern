package com.javalec.bbs.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.BDao;
import com.javalec.bbs.dto.BDto;

public class BListCommand implements BCommand {

	//interface에서 만든거 오버라이드 해서 가져옴 (extends와 implemets의 차이? 여러개 쓸 수 있음)
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		//BListCommand가 할 일이 뭔지 해줘야 
		//sql 문장은 모두 dao에 있으니까 db 부르려면 dao에 파일 있을거고 그거 불러와야지  
		
		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list();
		request.setAttribute("list", dtos);
		
	}

}
