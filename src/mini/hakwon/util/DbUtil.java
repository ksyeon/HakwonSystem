package mini.hakwon.util;
//NEw
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import mini.hakwon.Model;
import mini.hakwon.model.Book;
import mini.hakwon.model.Classroom;
import mini.hakwon.model.Curri;
import mini.hakwon.model.Order;
import mini.hakwon.model.People;
import mini.hakwon.model.Teacher;

public class DbUtil {

	int idNum;
	String name;
	int p_num;
	String addr;
	String phone;

	Object obj;
	Curri c;
	Query q = new Query();
	Book b;
	Classroom cr;
	Order o;
	Teacher t;

	String sql = null;
	People p = new People();
	Vector<String> vector = null;
	DefaultTableModel tableModel = new DefaultTableModel();
	Boolean isSearch = false; // 현재 '검색'중인지 아닌지 확인하기 위한 용도

	private PreparedStatement stmt = null;
	private ResultSet rs;
	String type = null;
	ResultSetMetaData meta = null;

	public DbUtil() {
	}

	public DbUtil(Object o, String type) {
		super();
		this.type = type;
		this.obj = o;
	}

	// 각 텝에서 데이터 베이스에 추가할때 거치는 메서드
	void add() {
		if (type.equals("학생")) {
			int i = q.add(obj, q.STUDENT);
			if (i > 0)
				System.out.println("데이터가 추가됐습니다.");
		} else if (type.equals("교재")) {
			int i = q.add(obj, q.BOOK);
			if (i > 0)
				System.out.println("데이터가 추가됐습니다.");
		} else if (type.equals("강사")) {
			int i = q.add(obj, q.TEACHER);
			if (i > 0)
				System.out.println("강사가 추가됐습니다.");
		} else if (type.equals("직원")) {
			int i = q.add(obj, q.EMPLOYEE);
			if (i > 0)
				System.out.println("데이터가 추가됐습니다.");
		} else if (type.equals("과정")) {
			int i = q.add(obj, q.CURRI);
			if (i > 0)
				System.out.println("데이터가 추가됐습니다.");
		} else if (type.equals("강의실")) {
			int i = q.add(obj, q.CLASSROOM);
			if (i > 0)
				System.out.println("데이터가 추가됐습니다.");
		} else if (type.equals("공급자")) {
			int i = q.add(obj, type);
			if (i > 0)
				System.out.println("데이터가 추가됐습니다.");
		}
		 else if (type.equals("주문추가")) {//쿼리에서 TYPE 확인할것.
				int i = q.add(obj, type);
				if (i > 0)
					System.out.println("데이터가 추가됐습니다.");
			}
			
		

	}

	// 각 텝에서 데이터베이스에 데이터를 조회할때 거치는 메서드
	DefaultTableModel Search() {
		int i = 0;
		if (type.equals("학생")) {
			p = (People) obj;
			rs = q.searchQ(p, q.STUDENT);
			isSearch = true;
		} else if (type.equals("교재")) {
			b = (Book) obj;
			rs = q.searchQ(b, q.BOOK);
			isSearch = true;
			// 수정 해야됨
		} else if (type.equals("강사")) {
			t = (Teacher) obj;
			rs = q.searchQ(t, q.TEACHER);
			isSearch = true;
		}else if (type.equals("직원")) {
			p = (People) obj;
			rs = q.searchQ(p, q.EMPLOYEE);
			isSearch = true;
		} else if (type.equals("과정")) {
			c = (Curri) obj;
			rs = q.searchQ(c, q.CURRI);
			isSearch = true;
		} else if (type.equals("강의실")) {
			cr = (Classroom) obj;
			rs = q.searchQ(cr, q.CLASSROOM);
			isSearch = true;
		} else if (type.equals("주문")) {
			o = (Order) obj;
			String state = o.getOrder();
			System.out.println("db유틸에 넘어옴" + state);
			if (state.equals("Supply")) {
				rs = q.searchQ(obj, "Supply");
				tableModel = getTable("Supply");
			} else {
				rs = q.searchQ(obj, "Order");
				tableModel = getTable("Order");
			}
		}
		return tableModel;

	}

	// 각 텝에서 DB에 데이터를 삭제하고자 할때 거치는 메서드
	void del() {
		int i = -1;
		if (type.equals("학생")) {
			int r = q.delQ(obj, type);
		} else if (type.equals("교재")) {
			i = q.delQ(obj, type);
		}
		else if (type.equals("강사")) {
			Teacher tea = (Teacher) obj;
			i = q.delQ(tea, q.TEACHER);
		} else if (type.equals("직원")) {
			p = (People) obj;
			i = q.delQ(p, q.EMPLOYEE);
		} else if (type.equals("과정")) {
			Curri cr = (Curri) obj;
			i = q.delQ(cr, q.CURRI);
		} else if (type.equals("강의실")) {
			cr = (Classroom) obj;
			i = q.delQ(cr, q.CLASSROOM);

		}
	}
	// 정보 수정!!!!
	void update() {
		if (type.equals("강의실")) {
			cr = (Classroom) obj;
			q.updateQ(cr, q.CLASSROOM);
		}
		else if (type.equals("직원")) {
			p = (People) obj;
			q.updateQ(p, q.EMPLOYEE);
		}
		else if (type.equals("학생")) {
			p = (People) obj;
			q.updateQ(p, q.STUDENT);
		}
		else if (type.equals("교재")) {
			b = (Book) obj;
			q.updateQ(b, q.BOOK);
		}
		else if (type.equals("과정")) {
			c = (Curri) obj;
			q.updateQ(c, q.CURRI);	
		}
		else if (type.equals("강사")) {
			t = (Teacher) obj;
			q.updateQ(t, q.TEACHER);
		}
	}
	// id를 이용하여 DB에 데이터가 있는지 확인하는 메서드
	// 주로 추가를 할때 미리 검색을 해야함으로 먼저 사용하는 메서드
	boolean SerachId(int idnum, String type) {
		boolean confirm = false;
		if (type.equals("학생")) {
			System.out.println("dbutil에서 아이디 검색을 시작합니다.");
			rs = q.serachIdQ(idnum, type);
			try {
				rs.next();
				if (rs != null) {
					confirm = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return confirm;
	}

	// 위의 SerachId메서드와 같은 기능으로 여기서는 이름을 받을때
	// 예) 학생이름, 강좌이름, 교재이름 등등
	boolean SerachName(String name, String type) {
		boolean confirm = false;
		if (type.equals("학생")) {
			rs = q.serachName(name, type);
			try {
				rs.next();
				if (rs != null) {
					confirm = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return confirm;
	}

	/**
	 * 데이터베이스로부터 콤보박스에 사용할 벡터를 구성
	 * 
	 * @param arg
	 *            질의할 항목을 정하는 문자열 매개변수<br>
	 *            {course|student|teacher|classroom|book}
	 */
	public Vector<String> getNameList(String arg) {
		try {
			rs = q.searchNameList(arg);
			vector = new Vector<String>();
			vector.addElement("----");
			while (rs.next()) {
				vector.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vector;
	}

	/* 콤보박스를 만들 벡터 생성 */
	public Vector<String> getList(String arg) {
		vector = new Vector<String>();
		vector.add("선택하세요");
		switch (arg) {
		case "course":
			try {
				rs = q.searchList(arg);
				while (rs.next()) {
					vector.add(rs.getString(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "student": // 수강등록 - 학생명 콤보박스
			try {
				stmt = Model.con.prepareStatement("SELECT st_name, st_id FROM student");
				rs = stmt.executeQuery();
				while (rs.next()) {
					vector.add(rs.getString(1)+"("+rs.getString(2)+")");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		return vector;
	}

	/* 데이터베이스로부터 테이블모델 생성 */
	public DefaultTableModel getTable(String arg) {
		switch (arg) {
		// 학생정보
		case "student":
			try {
				if (isSearch) { // 검색할 때는 rs에 밑의 쿼리문을 저장함
					rs = q.searchQ(p, q.STUDENT);
				} else {
					rs = q.searchList(arg);
				}
				if (rs != null) {
					meta = rs.getMetaData();
					int i = 1;
					// 테이블 헤더
					tableModel.setColumnIdentifiers(new Object[] { "학생번호", "등록일", "연락처", "이름", "거주지", "주민등록번호" });
					// 테이블 본문
					Object[] tblApplyBody = new Object[meta.getColumnCount()];
					while (rs.next()) {
						for (i = 1; i <= meta.getColumnCount(); i++) {
							tblApplyBody[i - 1] = rs.getString(i);
						}
						tableModel.addRow(tblApplyBody);
					}
				} else {
					tableModel.setColumnIdentifiers(new Object[] { "학생번호", "등록일", "연락처", "이름", "거주지", "주민등록번호" });
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// 수강등록 정보
			break;
		case "apply":
			try {
				if (isSearch) { // 검색할 때는 rs에 밑의 쿼리문을 저장함
					rs = q.searchQ(cr, q.CLASSROOM);
				} else {
					rs = q.searchList(arg);
				}
				rs = q.searchList(arg);
				if (rs != null) {
					meta = rs.getMetaData();
					int i = 1;
					// 테이블 헤더
					tableModel.setColumnIdentifiers(new String[] { "학생명", "과정명", "등록시작일", "등록마감일", "등록일", "납부일" });
					// 테이블 본문
					Object[] tblApplyBody = new Object[meta.getColumnCount()];
					while (rs.next()) {
						for (i = 1; i <= meta.getColumnCount(); i++) {
							tblApplyBody[i - 1] = rs.getString(i);
						}
						tableModel.addRow(tblApplyBody);
					}
				} else {
					tableModel.setColumnIdentifiers(new String[] { "학생명", "과정명", "등록시작일", "등록마감일", "등록일", "납부일" });
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;
		case "order":
			try {
				if (isSearch) { // 검색할 때는 rs에 밑의 쿼리문을 저장함
					rs = q.searchQ(o, q.ORDER);
				} else {
					rs = q.searchList(arg);
				}
				rs = q.searchList(arg);
				if (rs != null) {
					meta = rs.getMetaData();
					int i = 1;
					// 테이블 헤더
					tableModel.setColumnIdentifiers(
							new String[] { "주문번호", "주문량", "주문날짜", "대금지불날짜",  "교재", "공급자 번호" });
					// 테이블 본문
					Object[] tblApplyBody = new Object[meta.getColumnCount()];
					while (rs.next()) {
						for (i = 1; i <= meta.getColumnCount(); i++) {
							tblApplyBody[i - 1] = rs.getString(i);
						}
						tableModel.addRow(tblApplyBody);
					}
				} else {
					tableModel.setColumnIdentifiers(
							new String[] { "주문번호", "주문량", "주문날짜", "대금지불날짜","교재", "공급자 번호" });
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;
		case "curriculum":
			try {
				if (isSearch) { // 검색할 때는 rs에 밑의 쿼리문을 저장함
					rs = q.searchQ(c, q.CURRI);
				} else {
					rs = q.searchList(arg);
				}
				if (rs != null) {
					// DB에 아무런 값이 없을때 나오는 오류를 방지하기위해
					meta = rs.getMetaData();
					int i = 1;
					tableModel.setColumnIdentifiers(
							new Object[] { "과정번호", "과정명", "강사명", "강의실", "수업료", "교재", "등록시작일", "등록 마감일", "개강일" });
					Object[] tblApplyBody = new Object[meta.getColumnCount()];
					while (rs.next()) {
						for (i = 1; i <= meta.getColumnCount(); i++) {
							tblApplyBody[i - 1] = rs.getString(i);
						}
						tableModel.addRow(tblApplyBody);
					}
				} else {
					tableModel.setColumnIdentifiers(
							new Object[] { "과정번호", "과정명", "강사명", "강의실", "수업료", "교재", "등록시작일", "등록 마감일", "개강일" });
				}
				isSearch = false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		// 직원 정보 목록
		case "employee":
			try {
				if (isSearch)
					rs = q.searchQ(p, q.EMPLOYEE);
				else
					rs = q.searchList(arg);

				meta = rs.getMetaData();
				int i = 1;
				tableModel.setColumnIdentifiers(new Object[] { "직원번호", "직원명", "주민번호", "월급", "연락처", "주소" });
				Object[] tblApplyBody = new Object[meta.getColumnCount()];
				while (rs.next()) {
					for (i = 1; i <= meta.getColumnCount(); i++) {
						tblApplyBody[i - 1] = rs.getString(i);
					}
					tableModel.addRow(tblApplyBody);
				}

				isSearch = false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "book":
			try {
				if (isSearch) {
					rs = q.searchQ(b, q.BOOK);

				} else {
					rs = q.searchList(arg);
				}
				if (rs != null) {
					meta = rs.getMetaData();
					int i = 1;
					// 테이블 헤더
					tableModel.setColumnIdentifiers(new Object[] { "교재제목", "출판사명", "교재가격" });
					// 테이블 본문
					Object[] tblBookBody = new Object[meta.getColumnCount()];
					while (rs.next()) {
						for (i = 1; i <= meta.getColumnCount(); i++) {
							tblBookBody[i - 1] = rs.getString(i);
						}
						tableModel.addRow(tblBookBody);
					}
				} else {
					tableModel.setColumnIdentifiers(new Object[] { "교재제목", "출판사명", "교재가격" });
				}
				isSearch = false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			
		case "teacher":
			try {
				if(isSearch)
					rs = q.searchQ(t, q.TEACHER);
				else
					rs = q.searchList(arg);
				
				meta = rs.getMetaData();
				int i = 1;
				// 테이블 헤더
				tableModel.setColumnIdentifiers(new Object[] {"강사번호", "강사이름","주민번호", "거주지", "자격증 이름", "자격증 번호", "연락처", "전속여부", "페이"});
				
				// 테이블 본문
				Object[] tblTeacherBody = new Object[meta.getColumnCount()];
				while (rs.next()) {
					for (i = 1; i <= meta.getColumnCount(); i++) {
						tblTeacherBody[i - 1] = rs.getString(i);
					}
					tableModel.addRow(tblTeacherBody);
				}
				
				isSearch = false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;			
		case "classroom":
			try {
				if (isSearch) {
					rs = q.searchQ(cr, q.CLASSROOM);
				} else {
					rs = q.searchList(arg);
				}
				if (rs != null) {
					meta = rs.getMetaData();
					int i = 1;
					// 테이블 헤더
					tableModel.setColumnIdentifiers(new Object[] { "강의실번호", "책상수", "프로젝터수", "담당직원", "직원이름" });
					// 테이블 본문
					Object[] tblApplyBody = new Object[meta.getColumnCount()];
					while (rs.next()) {
						for (i = 1; i <= meta.getColumnCount(); i++) {
							tblApplyBody[i - 1] = rs.getString(i);
						}
						tableModel.addRow(tblApplyBody);
					}
				} else {
					tableModel.setColumnIdentifiers(new Object[] { "강의실번호", "책상수", "프로젝터수", "담당직원", "직원이름"});
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case "Supply":
			try {
				if (isSearch) {
					rs = q.searchQ(o, "Supply");
				} else {
					rs = q.searchList(arg);
				}
				if (rs != null) {
					meta = rs.getMetaData();
					int i = 1;
					// 테이블 헤더
					tableModel.setColumnIdentifiers(new Object[] { "공급자id", "상호" });
					// 테이블 본문
					Object[] tblApplyBody = new Object[meta.getColumnCount()];
					while (rs.next()) {
						for (i = 1; i <= meta.getColumnCount(); i++) {
							tblApplyBody[i - 1] = rs.getString(i);
						}
						tableModel.addRow(tblApplyBody);
					}
				} else {
					tableModel.setColumnIdentifiers(new Object[] { "공급자id", "상호" });
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "Order":
			try {
				if (isSearch) {
					rs = q.searchQ(o, "Order");
				} else {
					rs = q.searchList(arg);
				}
				if (rs != null) {
					meta = rs.getMetaData();
					int i = 1;
					// 테이블 헤더
					tableModel.setColumnIdentifiers(new Object[] { "주문번호", "주문량","주문날짜","대금지불날짜","교재","공급자번호" });
					// 테이블 본문
					Object[] tblApplyBody = new Object[meta.getColumnCount()];
					while (rs.next()) {
						for (i = 1; i <= meta.getColumnCount(); i++) {
							tblApplyBody[i - 1] = rs.getString(i);
						}
						tableModel.addRow(tblApplyBody);
					}
				} else {
					tableModel.setColumnIdentifiers(new Object[] { "주문번호", "주문량","주문날짜","대금지불날짜","교재","공급자번호" });
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		
		}
		return tableModel;
	}

	/**
	 * 수강등록 쿼리를 실행
	 * @param _st_id 학생번호
	 * @param _cr_name 과정명
	 */
	void applyCourseAdd(int _st_id, String _cr_name) {
		q.applyCourseAdd(_st_id, _cr_name);
	}

	/**
	 * 수강철회 쿼리 실행
	 * @param _st_name 학생명
	 * @param _cr_name 과정명
	 */
	void applyCourseDelete(String _st_name, String _cr_name) {
		q.applyCourseDelete(_st_name, _cr_name);
	}

	/**
	 * 수강변경 쿼리 실행
	 * @param _st_name 학생명
	 * @param _cr_name 과정명
	 */
	void applyCourseModify(String _st_name, String _cr_nameOld, String _cr_nameNew) {
		q.applyCourseModify(_st_name, _cr_nameOld, _cr_nameNew);
	}

	/**
	 * 수강료 납부 확인 쿼리 실행
	 * @param _st_name 학생명
	 * @param _cr_name 과정명
	 */
	void applyCourseConfirm(String _st_name, String _cr_name) {
		q.applyCourseConfirm(_st_name, _cr_name);
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
