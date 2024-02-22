package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UserController");
		
		//user에서 업무구분
		String action = request.getParameter("action");
		System.out.println(action);
		
		if("joinform".equals(action)) {
			System.out.println("user>joinform");
			
			//회원가입 포워드
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinform.jsp");
		}else if("join".equals(action)) {
			System.out.println("user>join");
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			
			System.out.println(id);
			System.out.println(password);
			System.out.println(name);
			System.out.println(gender);
			
			//데이터를 vo 묶어준다
			UserVo userVo = new UserVo(id, password, name, gender);
			System.out.println(userVo);
			
			//dao의 메소드로 회원가입
			
			//db 관련 업무
			UserDao userDao = new UserDao();
			
			//db에 저장
			userDao.insertUser(userVo);
			
			//리다이랙트
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinOk.jsp");
			
		} else if("loginForm".equals(action)) {
			//리다이랙트
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginForm.jsp");
		} else if("login".equals(action)) {
			System.out.println("user>login");
			
			String id = request.getParameter("id");
			String password = request.getParameter("pw");
			
			System.out.println(id);
			System.out.println(password);
			
			UserVo userVo = new UserVo(id, password);
			
			UserDao userDao = new UserDao();
			UserVo authUser = userDao.selectUserByIdPw(userVo); //id pw
			
			System.out.println(authUser);
			System.out.println(userVo);
			
			if(authUser != null ) { // 로그인 성공
				HttpSession session = request.getSession();
				session.setAttribute("userVo", userVo);
				session.setAttribute("authUser", authUser);
				WebUtil.redirect(request, response, "/mysite3/main");
			} else { //로그인실패
				System.out.println("로그인실패");
				WebUtil.redirect(request, response, "/mysite3/user?action=loginform");
			}
			//no name
			
		} else if("logout".equals(action))  {
			System.out.println("user>logout");
			
			HttpSession session = request.getSession();
			session.invalidate();
			
			WebUtil.redirect(request, response, "/mysite3/main");
		} else if("modifyForm".equals(action)) {
			System.out.println("user>modifyForm");
			
			WebUtil.forward(request, response, "/WEB-INF/views/user/modifyForm.jsp");
			
		} else if("modify".equals(action)) {
			System.out.println("user>modify");
			//파라미터 가져오기
			int no = Integer.parseInt(request.getParameter("no"));
			String id = request.getParameter("id"); 
			String name = request.getParameter("name"); 
			String password = request.getParameter("password");
		    String gender = request.getParameter("gender");
		    
		    //파라미터 출력해보기
		    System.out.println(no);
		    System.out.println(id);
		    System.out.println(name);
		    System.out.println(password);
		    System.out.println(gender);
		    
		    UserDao userDao = new UserDao();
		    userDao.userModify(no, id, password, name, gender);
		    
		    // 수정된 정보로 사용자 객체 업데이트
		    UserVo modifiedUser = new UserVo(no, id, password, name, gender);

		    // 세션에서 기존 사용자 정보 가져오기
		    HttpSession session = request.getSession();
		    UserVo authUser = (UserVo) session.getAttribute("authUser");

		    // 세션에 수정된 사용자 정보 업데이트
		    session.setAttribute("authUser", modifiedUser);
			
			WebUtil.forward(request, response, "/WEB-INF/views/main/index.jsp");
		}
		
		else {
			System.out.println("action값을 다시 확인해주세요");
		}
		
	}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
