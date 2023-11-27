package membership;

import common.JDBConnect;

// DAO(Data Transfer Object)
// - 데이터베이스의 데이터에 접근하기 위한 객체입니다.
// - 보통 JDBC를 통해 구현하며, 하나의 테이블에서 수행할 수 있는 CRUD를 전담합니다.
// - 데이터베이스로 부터 얻어온 결과를 DTO 객체에 담아서 반환합니다.  

public class MemberDAO extends JDBConnect{
	// 명시한 데이터베이스로의 연결이 완료된 MemberDAO 객체를 생성합니다.
	public MemberDAO(String drv, String url, String id, String pw) {
		super(drv, url, id, pw);
	}
	
	// 명시한 아이디/패스워드와 일치하는 회원 정보를 반환합니다.
	public MemberDTO getMemberDTO(String uid, String upass) {
		MemberDTO dto = new MemberDTO();
		String query = "SELECT * FROM member WHERE id = ? AND pass = ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, uid);
			psmt.setString(2, upass);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
	
}
