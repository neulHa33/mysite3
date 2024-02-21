package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestVo;

@WebServlet("/gbc")
public class guestbookController extends HttpServlet {
	//필드
		private static final long serialVersionUID = 1L;
	       
	    //생성자 -기본 생성자 사용
	    public guestbookController() {
	    
	    }
	    
	    //메소드 -gs
	    
	    //메소드 -일반
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("guestbookController.goGet()");
			
			String action = request.getParameter("action");
			System.out.println(action);
			
			if("insert".equals(action)) {
				System.out.println("insert:등록");
				
				String name = request.getParameter("name");
				String password = request.getParameter("password");
				String content = request.getParameter("content");
				
				//현재 날짜 가져오기
				var now = java.time.LocalDate.now();

				// LocalDate를 문자열로 변환
				var dateString = now.toString();

				// 출력
				System.out.print(dateString);

			    
				//vo로 묶기
				GuestVo guestVo = new GuestVo(name, password, content, dateString);
				System.out.println(guestVo.toString());
				
				//db 관련 업무
				GuestDao guestDao = new GuestDao();
				
				//db에 저장
				guestDao.guestInsert(guestVo);
				
				//리다이랙트
				response.sendRedirect("http://localhost:8080/mysite3/gbc?action=list");
				
				/*
				//db에서 전체 데이터 가져오기
				List<PersonVo> personList = phoneDao.personSelect();
				
				//request에 담기
				request.setAttribute("personList", personList);
				
				//포워드
				RequestDispatcher rd=request.getRequestDispatcher("/list.jsp");
			    rd.forward(request, response);
				*/
			} else if("list".equals(action)) {
				System.out.println("list: 리스트");
				
				//db 사용
				GuestDao guestDao = new GuestDao();
				
				// 리스트 가져오기
				List<GuestVo> guestList = guestDao.guestSelect();
				System.out.println(guestList);
				
				//데이터 담기 포워드
				request.setAttribute("guestList", guestList);
				
				/*
				RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
				rd.forward(request, response);
				*/
				
				WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
			} else if("delete".equals(action)) {
				System.out.println("delete:삭제");
				int no = Integer.parseInt(request.getParameter("no"));
				String password = request.getParameter("password");
				String inputPassword = request.getParameter("inputPassword");
				System.out.println(no);
				System.out.println(password);
				System.out.println(inputPassword);
				
				if(inputPassword.equals(password)) {
					System.out.println("비밀번호 일치");
					//db 사용
					GuestDao guestDao = new GuestDao();
					guestDao.guestDelete(no);
					//리다이랙트
					response.sendRedirect("http://localhost:8080/mysite3/gbc?action=list");
				} else {
					System.out.println("비밀번호 불일치");
					//리다이랙트
					response.sendRedirect("http://localhost:8080/mysite3/gbc?action=list");
				}
				
				/*
				//db 사용
				GuestDao guestDao = new GuestDao();
				guestDao.guestDelete(no);
				
				//리다이랙트
				response.sendRedirect("http://localhost:8080/mysite3/gbc?action=list");
				*/
				
				
			} else if("dform".equals(action)) {
				System.out.println("dform: 삭제폼");
	
				WebUtil.forward(request, response, "/WEB-INF/views/guestbook/delete.jsp");
			}
		   }

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}
}
