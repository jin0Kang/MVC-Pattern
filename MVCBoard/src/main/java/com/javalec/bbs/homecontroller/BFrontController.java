package com.javalec.bbs.homecontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.command.BCommand;
import com.javalec.bbs.command.BContentCommand;
import com.javalec.bbs.command.BDeleteCommand;
import com.javalec.bbs.command.BListCommand;
import com.javalec.bbs.command.BModifyCommand;
import com.javalec.bbs.command.BWriteCommand;

/**
 * Servlet implementation class BFrontController
 */

// .do로 끝나는 건 모두 다 나한테 오라는 의미 
@WebServlet("*.do") 
public class BFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet");
		actionDo(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doPost");
		actionDo(request, response);
		
		
	}
	
	//http~~~.do로 끝나면 위에 겟방식이든 포스트든 액션두로 오게 만들어놨음
	//사용자가 뭘 하든 가장 먼저 여기로 오게 되어있음. 여기서 이제 그 뒤에 뭘 할건지 설정해주면 됨
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("actionDo");
		request.setCharacterEncoding("utf-8");
	
		// -> 어느 페이지로 갈지에 따라 switch문 써서 넘겨줘야 하니까 그에 해당하는 파일 이름을 알아야 함.(아래 com 찾는 이유) 
		String viewPage = null; 
		BCommand command = null; 
		//클래스 임포트 하는데 new ~ 이렇게 안하는 이유는 command라는 이름으로 다른 쪽 constructor 사용하려고! (이러는데 이름이 복잡하면 안좋으니까 통일하려고 INterface)
		//아래서 list.do에다가 new BListCommand() 했고, 또 다른 기능 할 때 새로 new 하겠지 
		
		String uri = request.getRequestURI(); //uri: /project name/file name 
		String conPath = request.getContextPath(); //내가 필요한건 file name -> file name에 따라 수행하는 기능이 다를 거니
		String com = uri.substring(conPath.length()); //문자 빼오기 
		
//		System.out.println(uri);
//		System.out.println(conPath); 
//		System.out.println(com);
		
		switch(com) {
		case("/list.do"): //list.do: 전체 내용 보여주기 
			//다오 메소드 부르는 command import  
			command = new BListCommand();
			//다오에 있는 메소드(전체내용 가져오기) 실행하라는 명령  
			command.execute(request, response);
			//viewPage 가서 보여줘라 
			viewPage = "list.jsp";
			break;
		
		case("/write_view.do"): 	
			viewPage = "write_view.jsp";
			break;
			
		case("/write.do"):
			command = new BWriteCommand();
			command.execute(request,response);
			viewPage = "list.do"; //list.jsp는 빈화면. 다시 db 읽어와야 하기 때문에 list.do로 가라
			break;
		
		case("/content_view.do"):
			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "content_view.jsp";
			break;
		
		case("/modify.do"):
			command = new BModifyCommand();
			command.execute(request, response);
			viewPage = "list.do"; 
			break; 
		
		case("/delete.do"): 
			command = new BDeleteCommand();
			command.execute(request, response);
			viewPage = "list.do";
			break; 
			
		}//switch
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}	
	
	
	
	
}//
