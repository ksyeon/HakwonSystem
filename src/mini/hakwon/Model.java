package mini.hakwon;
import java.sql.*;

public class Model {

	/**
	 * 데이터베이스 연결
	 * 
	 * @param driver	데이터베이스 드라이버
	 * @param url		데이터베이스 위치
	 * @param id		데이터베이스 ID
	 * @param pw		데이터베이스 비밀번호
	 * @param con		접속 정보를 저장
	 * @param state		명령 정보를 저장
	 * @param rs		조회 결과를 저장
	 */
	private String driver, orcl, xe, id, pw;
	public static Connection con;
	
	public Model() {
		driver = "oracle.jdbc.driver.OracleDriver";
		orcl = "jdbc:oracle:thin:@localhost:1521:orcl";
		xe = "jdbc:oracle:thin:@localhost:1521:xe";
		id = "mc2";
		pw = "1234";
		con = null;
		
		connectDb();
	}



	void connectDb() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(orcl, id, pw);
			
			if(con != null)
			System.out.println("연결되었습니다.");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
