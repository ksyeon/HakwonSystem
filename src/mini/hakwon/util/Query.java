package mini.hakwon.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import mini.hakwon.Model;
import mini.hakwon.model.Book;
import mini.hakwon.model.Classroom;
import mini.hakwon.model.Curri;
import mini.hakwon.model.Order;
import mini.hakwon.model.People;
import mini.hakwon.model.Teacher;

// New


public class Query {
	// Query문을 여기서 작성후 DbUtil에 옮김
	int idNum;
	String name;
	int p_num;
	String addr;
	String phone;
	int confirm = 0;

	static final String STUDENT = "학생";
	static final String EMPLOYEE = "직원";
	static final String TEACHER = "강사";
	static final String CURRI = "과정";
	static final String BOOK = "교재";
	static final String ORDER = "주문";
	static final String CLASSROOM = "강의실";
	static final String SUPPLY = "공급자";
	static final String ORDERADD = "주문추가";

	String sql = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	People p;
	Curri c;
	Book b;
	Order order;
	Classroom cls;
	Teacher t;

	/**
	 * 콤보박스에 사용할 이름 목록을 DB에 질의
	 * 
	 * @param arg
	 *            질의할 항목을 정하는 문자열 매개변수<br>
	 *            {course|teacher|classroom|book}
	 * @param stmt
	 *            질의문을 준비하는 PreparedStatement 인스턴스
	 * @param rs
	 *            질의문을 실행한 결과를 저장하는 ResultSet 인스턴스
	 * @param e
	 *            예외 상태를 저장하는 변수
	 */

	// DBUtil의 add에서 보낸 데이터를 여기서 질의문 처리하는 메서드
	int add(Object o, String type) {
		if (type.equals("학생")) {
			p = (People) o;
			String sql = "insert Into student(st_id, regi_date, phone, st_name, st_addr, st_num)"
					+ "values(?,sysdate,?,?,?,?)";
			try {
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, p.idNum);
				stmt.setString(2, p.getPhone());
				stmt.setString(3, p.getName());
				stmt.setString(4, p.getAddr());
				stmt.setString(5, p.getP_num());
				confirm = stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "학생정보를 모두 입력하세요");
				e.printStackTrace();
			}
			return confirm;
		} else if (type.equals("과정")) {
			c = (Curri) o;
			sql = "insert into curriculum(cr_id, cr_name, t_id, cls_id, cr_fee, regi_start, regi_end, cr_start, bk_name)"
					+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try {
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, c.getCr_id());
				stmt.setString(2, c.getCr_name());
				stmt.setInt(3, c.getT_id());
				stmt.setInt(4, c.getCls_id());
				stmt.setInt(5, c.getCr_fee());
				stmt.setString(6, c.getRegi_start());
				stmt.setString(7, c.getRegi_end());
				stmt.setString(8, c.getCr_start());
				stmt.setString(9, c.getBk_name());

				confirm = stmt.executeUpdate();
				System.out.println("과정 추가되었음");
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "과정 정보를 모두 입력해주세요 !");
			}
		}
		else if (type.equals("직원")) {
			p = (People) o;
			sql = "insert into employee(emp_id, emp_sal, emp_name, emp_num, emp_addr, phone) "
					+ "values(?, ?, ?, ?, ?, ?)";
			try {
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, p.getIdNum());
				stmt.setInt(2, p.getSal());
				stmt.setString(3, p.getName());
				stmt.setString(4, p.getP_num());
				stmt.setString(5, p.getAddr());
				stmt.setString(6, p.getPhone());
				confirm = stmt.executeUpdate();
				System.out.println("직원 추가되었음");
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "직원 정보를 모두 입력해주세요 !");
			}
		}else if (type.equals("강사")) {
			t = (Teacher) o;
			sql = "insert into teacher(T_ID,T_NAME,T_NUM,T_ADDR,T_LIC_NAME,T_LIC_NUM,PHONE,CONTRACT,T_SAL)"
					+ " values(?, ?, ?, ?, ?, ?, ?, ?,?)";
			
			try {
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, t.getT_Id());
				stmt.setString(2, t.getT_Name());
				stmt.setString(3, t.getT_num());
				stmt.setString(4, t.getAddr());
				stmt.setString(5, t.getLicenceName());
				stmt.setInt(6, t.getLicenceNum());
				stmt.setString(7, t.getT_Phone());
				stmt.setString(8, String.valueOf(t.getContract()));		
				stmt.setInt(9, t.getT_Sal());

				stmt.executeUpdate();
				System.out.println("강사 추가되었음");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}  else if (type.equals("교재")) {
			Book b = (Book) o;

			try {
				stmt = Model.con.prepareStatement("select bk_name from book");
				rs = stmt.executeQuery();
				while (rs.next()) {
					if (b.getBk_name().equals(rs.getString(1))) {
						System.out.println("이미 있는 교재입니다.");
						JOptionPane.showMessageDialog(null, "이미 있는 교재입니다.");
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String sql = "insert into book(bk_name, bk_pub, bk_fee) values(?,?,?)";
			try {
				stmt = Model.con.prepareStatement(sql);
				stmt.setString(1, b.getBk_name());
				stmt.setString(2, b.getBk_pub());
				stmt.setInt(3, b.getBk_fee());
				if(b.getBk_name().equals("") || b.getBk_pub().equals("") || b.getBk_fee()==-1){
					JOptionPane.showMessageDialog(null, "교재 정보를 모두 입력해주세요 !");
				} else{
					stmt.executeUpdate();
					System.out.println("추가되었음");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (type.equals("공급자")) {
			Order ord = (Order) o;
			String sql = "insert into SUPPLIER(sp_id, sp_name) values(?,?)";
			try {
				System.out.println("공급자" + ord.getId());
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, ord.getId());
				stmt.setString(2, ord.getName());
				confirm = stmt.executeUpdate();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "이미 있는 공급자 번호입니다.");
			}
		} 
		else if (type.equals("주문추가")) {

			Order order = (Order) o;

			// 주문번호 DB조회
			try {
				stmt = Model.con.prepareStatement("select OD_ID from ORDERLIST");
				rs = stmt.executeQuery();
				while (rs.next()) {
					if (order.getOrdNo() == (rs.getInt(1))) { // 중복데이터가 있다면?
						System.out.println("이미 주문되었습니다.");
						JOptionPane.showMessageDialog(null, "이미 주문되었습니다.");
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			// 주문번호가 없다면. (조회파트를 if문으로 걸어야하는게 아닌지?
			// System.out.println("ddd"+order.getPayDate());
			if (!order.getPayDate().equals("")) // 대금지불일 유.
			{
				System.out.println("뭐가있니.." + order.getPayDate());
				String sql = "insert into ORDERLIST(OD_ID, OD_COUNT, OD_DATE, OD_PAY, BK_NAME, SP_ID) "
						+ "values(?,?,?,?,1212,600)";

				// 공급자,책이름

				System.out.println(sql);
				try {
					System.out.println("지불일 = " + order.getSupplyID());
					stmt = Model.con.prepareStatement(sql);
					stmt.setInt(1, order.getOrdNo()); // 주문번호
					stmt.setInt(2, order.getOrdCnt()); // 주문량
					stmt.setString(3, order.getOrdDate()); // 주문날짜
					stmt.setString(4, order.getPayDate()); // od_Pay 대금지불일
					// stmt.setString(3, order.getBook()); //교재
					// stmt.setInt(4, order.getSupplyID()); //공급자ID
					System.out.println("대금 유");
					System.out.println(sql);
					confirm = stmt.executeUpdate();
					System.out.println("입력확인" + confirm);
					System.out.println();

					System.out.println("추가되었음");
				} catch (SQLException e) {
					System.out.println("에러" + e);
				}
			} else {
				System.out.println("쿼리 무");

				// 대금지불일 무.
				String sql = "insert into ORDERLIST(OD_ID, OD_COUNT, OD_DATE, BK_NAME, SP_ID) values(?,?,?,?,?)";

				try {
					stmt = Model.con.prepareStatement(sql);
					stmt.setInt(1, order.getOrdNo());
					stmt.setInt(2, order.getOrdCnt());
					stmt.setString(3, order.getOrdDate());
					stmt.setString(4, order.getBook());
					stmt.setInt(5, order.getSupplyID());
					stmt.executeUpdate();
					confirm = stmt.executeUpdate();
					System.out.println("추가되었음");
				} catch (SQLException e) {
				}
			}
		}

		else if (type.equals("주문")) {

			
			
			
		} 
		
		else if(type.equals("강의실")){
			cls = (Classroom) o;
			try {
				sql = "insert into classroom(cls_id, desk, project) values(?, ?, ?)";
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, cls.getCls_id());
				stmt.setInt(2, cls.getDesk());
				stmt.setInt(3, cls.getProject());
				stmt.executeUpdate();
				System.out.println("classroom 테이블에 추가되었음");
				
				sql = "insert into classroom_employee(cls_id, emp_id) values(?, ?)";
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, cls.getCls_id());
				stmt.setInt(2, cls.getEmp_id());
				stmt.executeUpdate();
				System.out.println("classroom_employee 테이블에 추가되었음");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return confirm;
	}

	// DBUtil의 Search() 메서드에서 보낸 데이터를 처리하는 메서드, 여기서 질의문과 합쳐 나온 값을 반환
	ResultSet searchQ(Object obj, String type) {
		String sql = "";
		if (type.equals("학생")) {
			p = (People) obj;
			try {
				sql = "SELECT * FROM student where";
				
				if(p.getIdNum() != -1)
					sql += " st_id = " + p.getIdNum() + " and";
				if(!p.getName().equals(""))
					sql += " st_name like '%" + p.getName() + "%' and";
				if(!p.getPhone().equals(""))
					sql += " phone like '%" + p.getPhone() + "%' and";
				if(!p.getP_num().equals(""))
					sql += " st_num like '%" + p.getP_num() + "%' and";
				
				if(p.getIdNum()==-1 && p.getName().equals("") && p.getPhone().equals("") && p.getP_num().equals(""))
					sql = sql.substring(0, sql.length() - 5);
				else 
					sql = sql.substring(0, sql.length() - 3);
				
				sql += " ORDER BY st_id";
				stmt = Model.con.prepareStatement(sql);
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		} else if (type.equals("과정")) {
			Curri c = (Curri) obj;
			sql = "select c.cr_id, c.cr_name, t.t_name, c.cls_id, c.cr_fee, c.bk_name, "
					+ "to_char(regi_start, 'YY/MM/DD'), to_char(regi_end, 'YY/MM/DD'), to_char(cr_start, 'YY/MM/DD')"
					+ " from curriculum c, teacher t" + " where c.t_id = t.t_id and";
			try {
				// 검색 조건 추가하기
				if (c.getCr_id() != -1) {
					sql += " c.cr_id = " + c.getCr_id() + " and";
				}
				if (!c.getCr_name().equals("")) {
					sql += " c.cr_name LIKE '%" + c.getCr_name() + "%' and";
				}
				if (c.getT_id() != -1) {
					sql += " c.t_id = " + c.getT_id() + " and";
				}
				if (c.getCls_id() != -1) {
					sql += " c.cls_id = " + c.getCls_id() + " and";
				}
				if (!c.getBk_name().equals("")) {
					sql += " c.bk_name LIKE '%" + c.getBk_name() + "%' and";
				}
				// 쿼리문 짜르기
				sql = sql.substring(0, sql.length() - 3); // 끝에 'and' 잘라내고 검색
				sql += " order by cr_id";
				System.out.println(sql);
				stmt = Model.con.prepareStatement(sql);
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (type.equals("직원")) {
			try {
				p = (People) obj;
				sql = "SELECT emp_id, emp_name, emp_num, emp_sal, phone, emp_addr FROM employee WHERE";

				if (p.getIdNum() != -1)
					sql += " emp_id = " + p.getIdNum() + " and";
				if (!p.getName().equals(""))
					sql += " emp_name like '%" + p.getName() + "%' and";
				if (p.getSal() != -1)
					sql += " emp_sal = " + p.getSal() + " and";
				if (!p.getPhone().equals(""))
					sql += " phone like '%" + p.phone + "%' and";

				// 쿼리문 짜르기
				if (p.getIdNum() == -1 && p.getName().equals("") && p.getSal() == -1 && p.getPhone().equals(""))
					sql = sql.substring(0, sql.length() - 5);
				else
					sql = sql.substring(0, sql.length() - 3);

				sql += " ORDER BY emp_id";
				stmt = Model.con.prepareStatement(sql);
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if (type.equals("강사")) {
			
			Teacher t = (Teacher) obj;
	         sql = "select T_ID, T_NAME, T_NUM, T_ADDR, T_LIC_NAME, T_LIC_NUM, PHONE, CONTRACT, T_SAL "
	               + "from teacher where";
	         try {
	        	 System.out.println(t.getContract());
	            // 검색 조건 추가하기
	        	if(t.getContract() == 'Y')
	        		sql += " contract = '" + t.getContract() + "' and";
	        	if(t.getContract() == 'N')
	        		sql += " contract = '" + t.getContract() + "' and";
	        	if(t.getT_Id() != -1){	
	        		sql += " T_ID= " + t.getT_Id()+ " and";	        	
	        		}
	        	if(!t.getT_num().equals("")){	
	        		sql += " T_NUM like '%" + t.getT_num()+ "%' and";
	        	}
	        	if(t.getT_Sal() != -1){
	        		sql += " T_SAL= " + t.getT_Sal()+ " and";
	        	}
	        	if(t.getLicenceNum() != -1){
	        		sql += " T_LIC_NUM= " + t.getLicenceNum()+ " and";
	        	}
	        	if(!t.getT_Phone().equals("")){
	        		sql += " PHONE LIKE '%" + t.getT_Phone()+ "%' and";
	        	}
	            if (!t.getT_Name().equals("")) {
	               sql += " T_NAME LIKE '%" + t.getT_Name() + "%' and";
	            }
	            if (!t.getAddr().equals("")) {
	            	sql += " T_ADDR LIKE '%" + t.getAddr() + "%' and";
	            }
	            if (!t.getLicenceName().equals("")) {
	               sql += " T_LIC_NAME LIKE '%" + t.getLicenceName() + "%' and";
	            }
	            
	            sql = sql.substring(0, sql.length() - 3);	//and
	            sql += " order by t_id";
	            System.out.println(sql);
	            stmt = Model.con.prepareStatement(sql);
	            rs = stmt.executeQuery();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
		}
		
		else if (type.equals("교재")) {
			Book b = (Book) obj;
			sql = "select bk_name, bk_pub, bk_fee from book where";
			try {
				// 검색 조건 추가
				if (!b.getBk_name().equals("")) {
					sql += " bk_name LIKE '%" + b.getBk_name() + "%' and";
				}
				if (!b.getBk_pub().equals("")) {
					sql += " bk_pub LIKE '%" + b.getBk_pub() + "%' and";
				}
				if (b.getBk_fee() != -1) {
					sql += " bk_fee = " + b.getBk_fee() + " and";
				}

				// 쿼리문 자르기
				if (b.getBk_name().equals("") && b.getBk_pub().equals("") && b.getBk_fee() == -1) {
					sql = sql.substring(0, sql.length() - 5);
				} else {
					sql = sql.substring(0, sql.length() - 3);
				}
				sql += " order by bk_name ";
				System.out.println(sql);
				stmt = Model.con.prepareStatement(sql);
				// stmt.executeQuery();
				// 콘솔에 검색 결과 확인
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}  else if (type.equals("Order")) {
			try {
				order = (Order) obj;
				sql = "select * from orderlist";
				 if (order.getType().equals("낮은수량")) {
					sql = sql + " order by OD_COUNT asc";
				} else if (order.getType().equals("높은수량")) {
					sql = sql + " order by OD_COUNT desc";
				} else if (order.getType().equals("교재명순")) {
					sql = sql + " order by bk_name desc";
				} else if (order.getType().equals("주문일자")) {
					sql = sql + " order by od_date desc";
				} else {
					sql = sql + " order by sp_id asc";
				}
				System.out.println("질의문이 있는가?"+sql);
				stmt = Model.con.prepareStatement(sql);
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (type.equals("Supply")) {
			try {
				// 위의 주문이 되면 여기도 수정할것,
				order = (Order) obj;
				String sql1 = "";
				sql = "Select * from supplier";
				if (order.getType().equals("공급자순")) {
					sql1 = sql + " order by sp_id asc";
					System.out.println(sql1);
				} else if (order.getType().equals("상호순")) {
					sql1 = sql + " order by sp_name asc";
					System.out.println(sql1);
				}
				stmt = Model.con.prepareStatement(sql1);
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (type.equals("강의실")) {
			try {
				cls = (Classroom) obj;
				sql = "SELECT cls.cls_id, cls.desk, cls.project, ce.emp_id, e.emp_name "
						+ "FROM classroom cls, classroom_employee ce, employee e "
						+ " WHERE cls.cls_id = ce.cls_id and ce.emp_id = e.emp_id and";
				
				if(cls.getCls_id() != -1) 
					sql += " cls.cls_id = " + cls.getCls_id() + " and";
				if(cls.getDesk() != -1)
					sql += " cls.desk = " + cls.getDesk() + " and";
				if(cls.getEmp_id() != -1)
					sql += " ce.emp_id = " + cls.getEmp_id() + " and";
				
				sql = sql.substring(0, sql.length() - 3); // and 만 잘라내기
				sql += "ORDER BY cls.cls_id";
				System.out.println(sql);
				stmt = Model.con.prepareStatement(sql);
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return rs;
	}

	// DBUtil의DefaultTableModel에서 사용하는 메서드로 테이블에 미리 데이터를 뿌릴때 사용하는 메서드
	ResultSet searchList(String type) {
		switch (type) {
		case "course":
			try {
				stmt = Model.con.prepareStatement("SELECT cr_name FROM curriculum");
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "student":
			try {
				stmt = Model.con.prepareStatement("SELECT * FROM student");
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "teacher":
			try {
				stmt = Model.con.prepareStatement("SELECT * FROM teacher ORDER BY t_id");
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "apply":
			try {
				sql = "SELECT st.st_name, cr.cr_name, regi_start, regi_end, regi_cr, regi_pay FROM student_curriculum sc JOIN student st ON st.st_id=sc.st_id INNER JOIN curriculum cr ON sc.cr_id=cr.cr_id";
				stmt = Model.con.prepareStatement(sql);
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "curriculum":
			try {
				sql = "SELECT cr_id, cr_name, t_name, cls_id, cr_fee, bk_name, "
						+ "to_char(regi_start, 'YY/MM/DD'), to_char(regi_end, 'YY/MM/DD'), to_char(cr_start, 'YY/MM/DD')"
						+ " FROM curriculum c, teacher t WHERE c.t_id = t.t_id ORDER BY cr_id";
				stmt = Model.con.prepareStatement(sql);
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "book":
			try {
				stmt = Model.con.prepareStatement("SELECT bk_name, bk_pub, bk_fee FROM book ORDER BY bk_name");
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "classroom":
			try {
				sql = "SELECT cls.cls_id, desk, project, ce.emp_id, e.emp_name "
						+ "FROM classroom cls, classroom_employee ce, employee e"
						+ " WHERE cls.cls_id = ce.cls_id and ce.emp_id = e.emp_id ORDER BY cls_id";
				stmt = Model.con.prepareStatement(sql);
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "employee":
			try {
				sql = "SELECT emp_id, emp_name, emp_num, emp_sal, phone, emp_addr FROM employee ORDER BY emp_id";
				stmt = Model.con.prepareStatement(sql);
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}

		return rs;
	}

	// DBUtil의 SerachId()에서 받은 데이터를 처리하는 메서드
	ResultSet serachIdQ(int num, String type) {
		if (type.equals("학생")) {
			String sql = "select st_id from student where st_id = ?";
			try {
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, num);
				rs = stmt.executeQuery();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return rs;
	}

	// DBUtil의 SerachName()에서 받은 데이터를 처리하는 메서드
	ResultSet serachName(String name, String type) {
		if (type.equals("학생")) {
			String sql = "select st_name from student where st_name = ?";
			try {
				stmt = Model.con.prepareStatement(sql);
				stmt.setString(1, name);
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

	ResultSet searchNameList(String arg) {
		switch (arg) {
		// 과정명
		case "course":
			try {
				stmt = Model.con.prepareStatement("SELECT cr_name FROM curriculum");
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		// 학생번호
		case "student":
			try {
				stmt = Model.con.prepareStatement("SELECT st_id FROM student");
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		// 강사명
		case "teacher":
			try {
				stmt = Model.con.prepareStatement("SELECT t_name, t_id FROM teacher");
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		// 강의실
		case "classroom":
			try {
				stmt = Model.con.prepareStatement("SELECT cls_id FROM classroom");
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		// 교재명
		case "book":
			try {
				stmt = Model.con.prepareStatement("SELECT bk_name FROM book");
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		return rs;
	}

	// DBUtil의 del()에서 보낸 데이터를 처리하는 메서드, 질의어를 합쳐 여기서 데이터를 처리하고 나온 레코드의 수를 반환
	int delQ(Object obj, String type) {
		if (type.equals("학생")) {
			String sql = "delete from student where";
			p = (People) obj;
			idNum = p.getIdNum();
			try {
				sql = sql + " st_id = ?";
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, idNum);
				confirm = stmt.executeUpdate();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "삭제 불가 : 이 수강생은 학원의 과정을 듣고 있습니다.");
			}

		} else if (type.equals("과정")) {
			c = (Curri) obj;
			sql = "delete from curriculum where cr_id = ?";
			try {
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, c.getCr_id());
				confirm = stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("삭제 불가 : 이미 과정을 수강하고있습니다.");
				JOptionPane.showMessageDialog(null, "삭제 불가 : 과정을 수강하는 학생이 있습니다.");
			}
		} else if (type.equals("직원")) {
			p = (People) obj;
			sql = "DELETE FROM employee WHERE emp_id = ?";
			try {
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, p.getIdNum());
				confirm = stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "삭제 불가 : 직원이 관리하고 있는 강의실이 있습니다.");
			}
		} else if (type.equals("강사")) {
			t=(Teacher) obj;
			sql = "delete from teacher where t_id = ?";
			try {
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, t.getT_Id());
				confirm = stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("삭제 불가 : 이미 등록된 ID입니다..");
				JOptionPane.showMessageDialog(null, "삭제 불가 : 이미 등록된 ID입니다..");
			}
		} 
		else if (type.equals("교재")) {
			b = (Book) obj;
			String sql = "delete from book where bk_name = ?";
			try {
				stmt = Model.con.prepareStatement(sql);
				stmt.setString(1, b.getBk_name());
				confirm = stmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "삭제 불가 : 이 교재를 사용하고 있는 과정이 있습니다.");
			}
		}else if (type.equals("강의실")) {
			cls = (Classroom) obj;
			try {
				sql = "DELETE FROM classroom_employee WHERE cls_id = ?"; // 1. Classroom_Employee 에서 지워줌
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, cls.getCls_id());
				stmt.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			try {
				sql = "DELETE FROM classroom WHERE cls_id = ?"; // 2. Classroom 에서 지워줌
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, cls.getCls_id());
				stmt.executeUpdate();
				confirm = stmt.executeUpdate();
			} catch (SQLException e) {  // 3. 에러가 나는 이유는 Curriculum에서 참조하기 때문 => 1에서 삭제한 행 다시 insert해주기
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "삭제 불가 : 이 강의실을 사용하고 있는 과정이 있습니다.");
				
				try {
					sql = "INSERT INTO classroom_employee(cls_id, emp_id) VALUES(?, ?)";
					stmt = Model.con.prepareStatement(sql);
					stmt.setInt(1, cls.getCls_id());
					stmt.setInt(2, cls.getEmp_id());
					stmt.executeUpdate();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
						
			}
		}
		return confirm;
	}
	
	// 값 수정하는 메소드
	void updateQ(Object obj, String type) {
		if(type.equals("강의실")) {
			cls = (Classroom) obj;
			try {
				// classroom 테이블
				sql = "UPDATE classroom SET cls_id = ?, desk = ?, project = ? WHERE cls_id = ?";
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, cls.getCls_id());
				stmt.setInt(2, cls.getDesk());
				stmt.setInt(3, cls.getProject());
				stmt.setInt(4, cls.getCls_id());
				stmt.executeUpdate();
				// classroom_employee 테이블
				sql = "UPDATE classroom_employee SET cls_id = ?, emp_id = ? WHERE cls_id = ?";
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, cls.getCls_id());
				stmt.setInt(2, cls.getEmp_id());
				stmt.setInt(3, cls.getCls_id());
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		else if (type.equals("직원")) {
			p = (People) obj;
			try {
				sql = "UPDATE employee SET emp_id = ?, emp_sal = ?, emp_name = ?, emp_num = ?, emp_addr = ?,"
						+ " phone = ? WHERE emp_id = ?";
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, p.getIdNum());
				stmt.setInt(2, p.getSal());
				stmt.setString(3, p.getName());
				stmt.setString(4, p.getP_num());
				stmt.setString(5, p.getAddr());
				stmt.setString(6, p.getPhone());
				stmt.setInt(7, p.getIdNum());
				stmt.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if (type.equals("학생")) {
			p = (People) obj;
			try {
				sql = "UPDATE student SET st_id = ?, phone = ?, st_name = ?, st_addr = ?,"
						+ " st_num = ? WHERE st_id = ?";
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, p.getIdNum());
				stmt.setString(2, p.getPhone());
				stmt.setString(3, p.getName());
				stmt.setString(4, p.getAddr());
				stmt.setString(5, p.getP_num());
				stmt.setInt(6, p.getIdNum());
				stmt.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if (type.equals("교재")) {
			b = (Book) obj;
			try {
				sql = "UPDATE book SET bk_name = ?, bk_pub = ?, bk_fee = ? WHERE bk_name = ?";
				stmt = Model.con.prepareStatement(sql);
				stmt.setString(1, b.getBk_name());
				stmt.setString(2, b.getBk_pub());
				stmt.setInt(3, b.getBk_fee());
				stmt.setString(4, b.getBk_name());
				stmt.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if (type.equals("과정")) {
			c = (Curri) obj;
			try {
				sql = "UPDATE curriculum SET cr_name = ?, t_id = ?, cls_id = ?, cr_fee = ?, bk_name = ? "
						+ "WHERE cr_id = ?";
				stmt = Model.con.prepareStatement(sql);
				stmt.setString(1, c.getCr_name());
				stmt.setInt(2, c.getT_id());
				stmt.setInt(3, c.getCls_id());
				stmt.setInt(4, c.getCr_fee());
				stmt.setString(5, c.getBk_name());
				stmt.setInt(6, c.getCr_id());
				stmt.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if (type.equals("강사")) {
			t = (Teacher) obj;
			try {
				sql = "UPDATE teacher SET t_name = ?, t_num = ?, t_addr = ?, t_lic_name = ?, t_lic_num = ?,"
						+ " phone = ?, contract = ?, t_sal = ? WHERE t_id = ?";
				stmt = Model.con.prepareStatement(sql);
				stmt.setString(1, t.getT_Name());
				stmt.setString(2, t.getT_num());
				stmt.setString(3, t.getAddr());
				stmt.setString(4, t.getLicenceName());
				stmt.setInt(5, t.getLicenceNum());
				stmt.setString(6, t.getT_Phone());
				
				stmt.setString(7, String.valueOf(t.getContract()));
				//stmt.setString(7, t.getContract());
				stmt.setInt(8, t.getT_Sal());
				stmt.setInt(9, t.getT_Id());
				stmt.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 과정의 콤보박스에 미리 과정, 강사등을 설정하는 메서드들
		/* 과정 Query */
	public DefaultComboBoxModel getTId(DefaultComboBoxModel tid) throws SQLException {
		tid = new DefaultComboBoxModel();
		sql = "select t_name, t_id from teacher order by t_id";
		stmt = Model.con.prepareStatement(sql);
		rs = stmt.executeQuery();
		tid.addElement("강사 선택");
		while (rs.next()) {
			tid.addElement(rs.getString("t_name") + "(" + rs.getInt("t_id") + ")");
		}
		return tid;
	}
	/* 강의실 Query*/
	public DefaultComboBoxModel getClsId(DefaultComboBoxModel clsid) throws SQLException {
		clsid = new DefaultComboBoxModel();
		sql = "select cls_id from classroom order by cls_id";
		stmt = Model.con.prepareStatement(sql);
		rs = stmt.executeQuery();
		clsid.addElement("강의실 선택");
		while (rs.next()) {
			clsid.addElement(rs.getInt("cls_id"));
		}
		return clsid;
	}

	/* 북 Query*/
	public DefaultComboBoxModel getBkName(DefaultComboBoxModel bkname) throws SQLException {
		bkname = new DefaultComboBoxModel();
		sql = "select bk_name from book order by bk_name";
		stmt = Model.con.prepareStatement(sql);
		rs = stmt.executeQuery();
		bkname.addElement("교재 선택");
		while (rs.next()) {
			bkname.addElement(rs.getString("bk_name"));
		}
		return bkname;
	}

	/* 직원 Query */
	public DefaultComboBoxModel getEmpId(DefaultComboBoxModel empid) throws SQLException {
		empid = new DefaultComboBoxModel();
		sql = "select emp_id, emp_name from employee order by emp_id";
		stmt = Model.con.prepareStatement(sql);
		rs = stmt.executeQuery();
		empid.addElement("직원 선택");
		while(rs.next()){
			empid.addElement(rs.getString("emp_name") + "(" + rs.getInt("emp_id") + ")");
		}
		return empid;
	}

	/**
	 * 수강등록 추가
	 * 
	 * @param _st_id
	 *            학생번호
	 * @param _cr_name
	 *            과정명
	 * @return 작업 완료 후 갱신된 결과를 조회한 ResultSet
	 */
	ResultSet applyCourseAdd(int _st_id, String _cr_name) {
		int st_id = _st_id;
		String cr_name = _cr_name;
		try {
			// 넘겨받은 과목명으로부터 과목번호를 산출
			int cr_id = getCurriculumNo(cr_name);
			// 중복방지
			stmt = Model.con.prepareStatement("SELECT st_id, cr_id FROM student_curriculum WHERE st_id=?");
			stmt.setInt(1, st_id);
			rs = stmt.executeQuery();
			System.out.println("쿼리 조회");
			boolean flag = false;
			while (rs.next()) {
				// 학생이 이미 수강한 과목을 또 수강등록하지 않도록
				if (st_id == rs.getInt(1) && cr_id == rs.getInt(2)) {
					JOptionPane.showMessageDialog(null, "신청이 중복되었습니다.");
				} else {
					System.out.println("중복방지 플래그 활성화");
					flag = true;
				}
			}
			// 과목번호를 사용하여 DB에 수강등록
			if (flag == true) {
				stmt = Model.con.prepareStatement(
						"INSERT INTO student_curriculum(st_id, cr_id, regi_cr) VALUES(?, ?, sysdate)");
				stmt.setInt(1, st_id);
				stmt.setInt(2, cr_id);
				// 현재 시각으로 등록
				stmt.executeUpdate();
				System.out.println("쿼리 실행");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// JTable 갱신을 위해 다시 데이터 받아오기
		rs = searchList("apply");
		return rs;
	}

	/**
	 * 수강등록 철회
	 * 
	 * @param _st_name
	 *            학생명
	 * @param _cr_name
	 *            과정명
	 * @return 작업 완료 후 갱신된 결과를 조회한 ResultSet
	 */
	ResultSet applyCourseDelete(String _st_name, String _cr_name) {
		String st_name = _st_name;
		String cr_name = _cr_name;
		try {
			// 넘겨받은 이름으로부터 번호를 산출
			int cr_id = getCurriculumNo(cr_name);
			int st_id = getStudentNo(st_name);
			// 삭제작업 수행
			stmt = Model.con.prepareStatement("DELETE FROM student_curriculum WHERE st_id=? AND cr_id=?");
			stmt.setInt(1, st_id);
			stmt.setInt(2, cr_id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// JTable 갱신을 위해 다시 데이터 받아오기
		rs = searchList("apply");
		return rs;
	}

	/**
	 * 수강등록 수정
	 * 
	 * @param _st_name
	 *            학생명
	 * @param _cr_name
	 *            과정명
	 * @return 작업 완료 후 갱신된 결과를 조회한 ResultSet
	 */
	ResultSet applyCourseModify(String _st_name, String _cr_nameOld, String _cr_nameNew) {
		String st_name = _st_name;
		String cr_nameOld = _cr_nameOld;
		String cr_nameNew = _cr_nameNew;
		try {
			// 넘겨받은 이름으로부터 번호를 산출
			int cr_idOld = getCurriculumNo(cr_nameOld);
			int cr_idNew = getCurriculumNo(cr_nameNew);
			int st_id = getStudentNo(st_name);
			// 수정작업 수행
			stmt = Model.con.prepareStatement("UPDATE student_curriculum SET cr_id=? WHERE st_id=" + st_id + " AND cr_id=" + cr_idOld);
			stmt.setInt(1, cr_idNew);
			stmt.executeUpdate();
			System.out.println("쿼리 실행");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// JTable 갱신을 위해 다시 데이터 받아오기
		rs = searchList("apply");
		return rs;
	}

	/**
	 * 수강 납부확인
	 * 
	 * @param _st_name
	 *            학생명
	 * @param _cr_name
	 *            과정명
	 * @return 작업 완료 후 갱신된 결과를 조회한 ResultSet
	 */
	ResultSet applyCourseConfirm(String _st_name, String _cr_name) {
		String st_name = _st_name;
		String cr_name = _cr_name;
		try {
			// 넘겨받은 이름으로부터 번호를 산출
			int st_id = getStudentNo(st_name);
			int cr_id = getCurriculumNo(cr_name);
			// 현재 시각으로 납부일시를 기록
			stmt = Model.con
					.prepareStatement("UPDATE student_curriculum SET regi_pay=sysdate WHERE st_id=? AND cr_id=?");
			stmt.setInt(1, st_id);
			stmt.setInt(2, cr_id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// JTable 갱신을 위해 다시 데이터 받아오기
		rs = searchList("apply");
		return rs;
	}

	/**
	 * 수강신청 테이블의 과목명으로 과목번호 얻기
	 * @param cr_name 과정명
	 * @return 과정번호를 int형으로 반환 
	 */
	int getCurriculumNo(String cr_name) {
		int cr_id = 0;
		try {
			stmt = Model.con.prepareStatement("SELECT cr_id FROM curriculum WHERE cr_name=?");
			stmt.setString(1, cr_name);
			rs = stmt.executeQuery();
			rs.next();
			cr_id = rs.getInt(1);
			System.out.println("과목번호 얻음");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cr_id;
	}

	/**
	 * 수강신청 테이블의 학생명으로 학생번호 얻기
	 * @param st_name 학생명
	 * @return 학생번호를 int형으로 반환
	 */
	int getStudentNo(String st_name) {
		int st_id = 0;
		try {
			stmt = Model.con.prepareStatement("SELECT st_id FROM student WHERE st_name=?");
			stmt.setString(1, st_name);
			rs = stmt.executeQuery();
			rs.next();
			st_id = rs.getInt(1);
			System.out.println("과목번호 얻음");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return st_id;
	}
	public int getIdNum() {
		return idNum;
	}

	public String getName() {
		return name;
	}

	public int getP_num() {
		return p_num;
	}

	public String getAddr() {
		return addr;
	}

	public String getPhone() {
		return phone;
	}

}
