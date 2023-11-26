package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

public class DBConnPool {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	// * 커넥션 풀이란?! : 
	// - Connection 객체를 미리 생성에 Pool에 넣어놓고
	// - 요청이 있을 때, 이미 생성된 Connection 객체를 가져다 사용하는 기법
	
	// * JNDI(Java Naming and Directory Interface)
	// - 객체나 데이터를 '이름'만으로 찾아쓸 수 있는 디렉토리 서비스 입니다.
	// - 대부분의 WAS는 커넥션 풀을 비롯한 여러 자원을 JNDI 서비스로 제공합니다.
	
	// 1. server.xml 파일에서 "dbcp_myoracle"이라는 이름으로 커넥션풀을 가지고 있는 DataSource를 전역자원으로 등록
	// 2. context.xml 파일에서 "dbcp_myoracle"이라는 이름에 전역자원 "dbcp_myoracle" 매핑
	// 3. 그럼 JNDI 서비스를 사용해서 "dbcp_myoracle"를 검색하면 커넥션풀을 가지고 있는 DataSource 객체를 가지고 올 수 있다.
	// 4. 가져온 DataSource 객체에 getConnection 요청을 하면 대기중인 Connection 얻을 수 있음
	
	// 기본 생성자
	public DBConnPool() {
		try {
			// 커넥션 풀(DataSource 얻기)
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env"); // "java:comp/env"는 루트 디렉토리로 생각
			DataSource source = (DataSource) ctx.lookup("dbcp_myoracle");
			
			// 커넥션 풀을 통해 연결 얻기
			con = source.getConnection();
			
			System.out.println("DB 커넥션 풀 연결 성공");
		} catch (Exception e) {
			System.out.println("DB 커넥션 풀 연결 실패");
			e.printStackTrace();
		}
	}

	// 연결 해제(자원 반납)
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (psmt != null)
				psmt.close();
			if (con != null)
				con.close();

			System.out.println("DB 커넥션 풀 자원 반납");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
