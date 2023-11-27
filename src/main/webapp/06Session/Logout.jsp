<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
// 방법1 : 회원인증정보 속성 삭제
session.removeAttribute("UserId");
session.removeAttribute("UserName");

// 방법2 : 모든 속성 한꺼번에 삭제
// - invalidate()를 사용하면 
// - 서버가 세션 정보를 더 이상 유지할 필요가 없으므로
// - 서버 부담이 줄어듭니다. 
// - 따라서 로그아웃 시에는 invalidate()를 사용하는 것이 좋습니다.
session.invalidate();

// 속성 삭제 후 페이지 이동
response.sendRedirect("LoginForm.jsp");
%>