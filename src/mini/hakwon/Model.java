package mini.hakwon;
import java.sql.*;

public class Model {

	/**
	 * �����ͺ��̽� ����
	 * 
	 * @param driver	�����ͺ��̽� ����̹�
	 * @param url		�����ͺ��̽� ��ġ
	 * @param id		�����ͺ��̽� ID
	 * @param pw		�����ͺ��̽� ��й�ȣ
	 * @param con		���� ������ ����
	 * @param state		��� ������ ����
	 * @param rs		��ȸ ����� ����
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
			System.out.println("����Ǿ����ϴ�.");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
