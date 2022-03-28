package com.javalec.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.BDao;

public class BModifyCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent"); 
		
		
		BDao dao = new BDao();
		dao.modify(bId, bName, bTitle, bContent);
		//얘는 반환값 없으니까 그냥 작동만 하고 끝.
		//반환값 setattribute() 할 거 없다 
	}

}
