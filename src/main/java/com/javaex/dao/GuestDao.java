package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestVo;

public class GuestDao {
	//필드 생성자 메소드-gs 
	
			//메소드-일반
			
			//삭제
			public void guestDelete(int no) {
				int count = -1;
				
				// 0. import java.sql.*;
						Connection conn = null;
						PreparedStatement pstmt = null;
						ResultSet rs = null;
						try {
							// 1. JDBC 드라이버 (Oracle) 로딩
							Class.forName("com.mysql.cj.jdbc.Driver");
							
							// 2. Connection 얻어오기
							String url = "jdbc:mysql://localhost:3306/web_db";
							conn = DriverManager.getConnection(url, "web", "web");
							
							// 3. SQL문 준비 / 바인딩 / 실행
								// - sql문 준비
								String query = "";
								query += " delete from guest ";
								query += " where no = ? ";
								
								//바인딩
								pstmt = conn.prepareStatement(query);
								pstmt.setInt(1,no);
							
								//실행
								count = pstmt.executeUpdate();

							// 4.결과처리
								System.out.println(count + "건 삭제 되었습니다.");

						} catch (ClassNotFoundException e) {
						System.out.println("error: 드라이버 로딩 실패 - " + e);
						} catch (SQLException e) {
						System.out.println("error:" + e);
						} finally {
						// 5. 자원정리
						try {
						if (rs != null) {
						rs.close();
						} 
						if (pstmt != null) {
						pstmt.close();
						}
						if (conn != null) {
						conn.close();
						}
						} catch (SQLException e) {
						System.out.println("error:" + e);
						}
						
						}
						
						System.out.println(count);
						
					}
				
			
			//전체 가져오기
			public List<GuestVo> guestSelect() {
				
				List<GuestVo> guestList = new ArrayList<GuestVo>();
				
				// 0. import java.sql.*;
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
				// 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName("com.mysql.cj.jdbc.Driver");
				// 2. Connection 얻어오기
					String url = "jdbc:mysql://localhost:3306/web_db";
					conn = DriverManager.getConnection(url, "web", "web");
				// 3. SQL문 준비 / 바인딩 / 실행
					String query = "";
					query += " select no, ";
					query += " 		  name, ";
					query += " 		  password, ";
					query += " 		  content, ";
					query += " 		  reg_date ";
					query += " from guest ";
					
					//바인딩
					pstmt = conn.prepareStatement(query);
					
					//실행
					rs = pstmt.executeQuery();
					
					// 4.결과처리
					while(rs.next()) {
						int no = rs.getInt("no");
						String name = rs.getString("name");
						String password = rs.getString("password");
						String content = rs.getString("content");
						String reg_date = rs.getString("reg_date");
						
						// db에서 가져온 데이터 vo로 묶기
						GuestVo guestVo = new GuestVo(no, name, password, content, reg_date);
						
						
						//리스트에 주소 추가
						guestList.add(guestVo);
						System.out.println(guestVo);
					}
					
				} catch (ClassNotFoundException e) {
				System.out.println("error: 드라이버 로딩 실패 - " + e);
				} catch (SQLException e) {
				System.out.println("error:" + e);
				} finally {
				// 5. 자원정리
				try {
				if (rs != null) {
				rs.close();
				} 
				if (pstmt != null) {
				pstmt.close();
				}
				if (conn != null) {
				conn.close();
				}
				} catch (SQLException e) {
				System.out.println("error:" + e);
				}
				}
				
				return guestList;
				
				

			}
			
			//등록
			public void guestInsert(GuestVo guestVo) {
				int count = -1;
				
				// 0. import java.sql.*;
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					// 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					// 2. Connection 얻어오기
					String url = "jdbc:mysql://localhost:3306/web_db";
					conn = DriverManager.getConnection(url, "web", "web");
					
					// 3. SQL문 준비 / 바인딩 / 실행
						// - sql문 준비
						String query = "";
						query += " insert into guest ";
						query += " value(null, ?, ?, ?, ?);";
						
						//바인딩
						pstmt = conn.prepareStatement(query);
						pstmt.setString(1, guestVo.getName());
						pstmt.setString(2, guestVo.getPassword());
						pstmt.setString(3, guestVo.getContent());
						pstmt.setString(4, guestVo.getReg_date());
					
						//실행
						count = pstmt.executeUpdate();

					// 4.결과처리
						System.out.println(count + "건 등록 되었습니다.");

				} catch (ClassNotFoundException e) {
				System.out.println("error: 드라이버 로딩 실패 - " + e);
				} catch (SQLException e) {
				System.out.println("error:" + e);
				} finally {
				// 5. 자원정리
				try {
				if (rs != null) {
				rs.close();
				} 
				if (pstmt != null) {
				pstmt.close();
				}
				if (conn != null) {
				conn.close();
				}
				} catch (SQLException e) {
				System.out.println("error:" + e);
				}
				
				}
				
				System.out.println(count);
				
			}
}
