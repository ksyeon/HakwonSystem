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
	// Query���� ���⼭ �ۼ��� DbUtil�� �ű�
	int idNum;
	String name;
	int p_num;
	String addr;
	String phone;
	int confirm = 0;

	static final String STUDENT = "�л�";
	static final String EMPLOYEE = "����";
	static final String TEACHER = "����";
	static final String CURRI = "����";
	static final String BOOK = "����";
	static final String ORDER = "�ֹ�";
	static final String CLASSROOM = "���ǽ�";
	static final String SUPPLY = "������";
	static final String ORDERADD = "�ֹ��߰�";

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
	 * �޺��ڽ��� ����� �̸� ����� DB�� ����
	 * 
	 * @param arg
	 *            ������ �׸��� ���ϴ� ���ڿ� �Ű�����<br>
	 *            {course|teacher|classroom|book}
	 * @param stmt
	 *            ���ǹ��� �غ��ϴ� PreparedStatement �ν��Ͻ�
	 * @param rs
	 *            ���ǹ��� ������ ����� �����ϴ� ResultSet �ν��Ͻ�
	 * @param e
	 *            ���� ���¸� �����ϴ� ����
	 */

	// DBUtil�� add���� ���� �����͸� ���⼭ ���ǹ� ó���ϴ� �޼���
	int add(Object o, String type) {
		if (type.equals("�л�")) {
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
				JOptionPane.showMessageDialog(null, "�л������� ��� �Է��ϼ���");
				e.printStackTrace();
			}
			return confirm;
		} else if (type.equals("����")) {
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
				System.out.println("���� �߰��Ǿ���");
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "���� ������ ��� �Է����ּ��� !");
			}
		}
		else if (type.equals("����")) {
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
				System.out.println("���� �߰��Ǿ���");
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "���� ������ ��� �Է����ּ��� !");
			}
		}else if (type.equals("����")) {
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
				System.out.println("���� �߰��Ǿ���");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}  else if (type.equals("����")) {
			Book b = (Book) o;

			try {
				stmt = Model.con.prepareStatement("select bk_name from book");
				rs = stmt.executeQuery();
				while (rs.next()) {
					if (b.getBk_name().equals(rs.getString(1))) {
						System.out.println("�̹� �ִ� �����Դϴ�.");
						JOptionPane.showMessageDialog(null, "�̹� �ִ� �����Դϴ�.");
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
					JOptionPane.showMessageDialog(null, "���� ������ ��� �Է����ּ��� !");
				} else{
					stmt.executeUpdate();
					System.out.println("�߰��Ǿ���");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (type.equals("������")) {
			Order ord = (Order) o;
			String sql = "insert into SUPPLIER(sp_id, sp_name) values(?,?)";
			try {
				System.out.println("������" + ord.getId());
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, ord.getId());
				stmt.setString(2, ord.getName());
				confirm = stmt.executeUpdate();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "�̹� �ִ� ������ ��ȣ�Դϴ�.");
			}
		} 
		else if (type.equals("�ֹ��߰�")) {

			Order order = (Order) o;

			// �ֹ���ȣ DB��ȸ
			try {
				stmt = Model.con.prepareStatement("select OD_ID from ORDERLIST");
				rs = stmt.executeQuery();
				while (rs.next()) {
					if (order.getOrdNo() == (rs.getInt(1))) { // �ߺ������Ͱ� �ִٸ�?
						System.out.println("�̹� �ֹ��Ǿ����ϴ�.");
						JOptionPane.showMessageDialog(null, "�̹� �ֹ��Ǿ����ϴ�.");
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			// �ֹ���ȣ�� ���ٸ�. (��ȸ��Ʈ�� if������ �ɾ���ϴ°� �ƴ���?
			// System.out.println("ddd"+order.getPayDate());
			if (!order.getPayDate().equals("")) // ��������� ��.
			{
				System.out.println("�����ִ�.." + order.getPayDate());
				String sql = "insert into ORDERLIST(OD_ID, OD_COUNT, OD_DATE, OD_PAY, BK_NAME, SP_ID) "
						+ "values(?,?,?,?,1212,600)";

				// ������,å�̸�

				System.out.println(sql);
				try {
					System.out.println("������ = " + order.getSupplyID());
					stmt = Model.con.prepareStatement(sql);
					stmt.setInt(1, order.getOrdNo()); // �ֹ���ȣ
					stmt.setInt(2, order.getOrdCnt()); // �ֹ���
					stmt.setString(3, order.getOrdDate()); // �ֹ���¥
					stmt.setString(4, order.getPayDate()); // od_Pay ���������
					// stmt.setString(3, order.getBook()); //����
					// stmt.setInt(4, order.getSupplyID()); //������ID
					System.out.println("��� ��");
					System.out.println(sql);
					confirm = stmt.executeUpdate();
					System.out.println("�Է�Ȯ��" + confirm);
					System.out.println();

					System.out.println("�߰��Ǿ���");
				} catch (SQLException e) {
					System.out.println("����" + e);
				}
			} else {
				System.out.println("���� ��");

				// ��������� ��.
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
					System.out.println("�߰��Ǿ���");
				} catch (SQLException e) {
				}
			}
		}

		else if (type.equals("�ֹ�")) {

			
			
			
		} 
		
		else if(type.equals("���ǽ�")){
			cls = (Classroom) o;
			try {
				sql = "insert into classroom(cls_id, desk, project) values(?, ?, ?)";
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, cls.getCls_id());
				stmt.setInt(2, cls.getDesk());
				stmt.setInt(3, cls.getProject());
				stmt.executeUpdate();
				System.out.println("classroom ���̺� �߰��Ǿ���");
				
				sql = "insert into classroom_employee(cls_id, emp_id) values(?, ?)";
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, cls.getCls_id());
				stmt.setInt(2, cls.getEmp_id());
				stmt.executeUpdate();
				System.out.println("classroom_employee ���̺� �߰��Ǿ���");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return confirm;
	}

	// DBUtil�� Search() �޼��忡�� ���� �����͸� ó���ϴ� �޼���, ���⼭ ���ǹ��� ���� ���� ���� ��ȯ
	ResultSet searchQ(Object obj, String type) {
		String sql = "";
		if (type.equals("�л�")) {
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
		} else if (type.equals("����")) {
			Curri c = (Curri) obj;
			sql = "select c.cr_id, c.cr_name, t.t_name, c.cls_id, c.cr_fee, c.bk_name, "
					+ "to_char(regi_start, 'YY/MM/DD'), to_char(regi_end, 'YY/MM/DD'), to_char(cr_start, 'YY/MM/DD')"
					+ " from curriculum c, teacher t" + " where c.t_id = t.t_id and";
			try {
				// �˻� ���� �߰��ϱ�
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
				// ������ ¥����
				sql = sql.substring(0, sql.length() - 3); // ���� 'and' �߶󳻰� �˻�
				sql += " order by cr_id";
				System.out.println(sql);
				stmt = Model.con.prepareStatement(sql);
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (type.equals("����")) {
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

				// ������ ¥����
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
		}else if (type.equals("����")) {
			
			Teacher t = (Teacher) obj;
	         sql = "select T_ID, T_NAME, T_NUM, T_ADDR, T_LIC_NAME, T_LIC_NUM, PHONE, CONTRACT, T_SAL "
	               + "from teacher where";
	         try {
	        	 System.out.println(t.getContract());
	            // �˻� ���� �߰��ϱ�
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
		
		else if (type.equals("����")) {
			Book b = (Book) obj;
			sql = "select bk_name, bk_pub, bk_fee from book where";
			try {
				// �˻� ���� �߰�
				if (!b.getBk_name().equals("")) {
					sql += " bk_name LIKE '%" + b.getBk_name() + "%' and";
				}
				if (!b.getBk_pub().equals("")) {
					sql += " bk_pub LIKE '%" + b.getBk_pub() + "%' and";
				}
				if (b.getBk_fee() != -1) {
					sql += " bk_fee = " + b.getBk_fee() + " and";
				}

				// ������ �ڸ���
				if (b.getBk_name().equals("") && b.getBk_pub().equals("") && b.getBk_fee() == -1) {
					sql = sql.substring(0, sql.length() - 5);
				} else {
					sql = sql.substring(0, sql.length() - 3);
				}
				sql += " order by bk_name ";
				System.out.println(sql);
				stmt = Model.con.prepareStatement(sql);
				// stmt.executeQuery();
				// �ֿܼ� �˻� ��� Ȯ��
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}  else if (type.equals("Order")) {
			try {
				order = (Order) obj;
				sql = "select * from orderlist";
				 if (order.getType().equals("��������")) {
					sql = sql + " order by OD_COUNT asc";
				} else if (order.getType().equals("��������")) {
					sql = sql + " order by OD_COUNT desc";
				} else if (order.getType().equals("������")) {
					sql = sql + " order by bk_name desc";
				} else if (order.getType().equals("�ֹ�����")) {
					sql = sql + " order by od_date desc";
				} else {
					sql = sql + " order by sp_id asc";
				}
				System.out.println("���ǹ��� �ִ°�?"+sql);
				stmt = Model.con.prepareStatement(sql);
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (type.equals("Supply")) {
			try {
				// ���� �ֹ��� �Ǹ� ���⵵ �����Ұ�,
				order = (Order) obj;
				String sql1 = "";
				sql = "Select * from supplier";
				if (order.getType().equals("�����ڼ�")) {
					sql1 = sql + " order by sp_id asc";
					System.out.println(sql1);
				} else if (order.getType().equals("��ȣ��")) {
					sql1 = sql + " order by sp_name asc";
					System.out.println(sql1);
				}
				stmt = Model.con.prepareStatement(sql1);
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (type.equals("���ǽ�")) {
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
				
				sql = sql.substring(0, sql.length() - 3); // and �� �߶󳻱�
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

	// DBUtil��DefaultTableModel���� ����ϴ� �޼���� ���̺� �̸� �����͸� �Ѹ��� ����ϴ� �޼���
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

	// DBUtil�� SerachId()���� ���� �����͸� ó���ϴ� �޼���
	ResultSet serachIdQ(int num, String type) {
		if (type.equals("�л�")) {
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

	// DBUtil�� SerachName()���� ���� �����͸� ó���ϴ� �޼���
	ResultSet serachName(String name, String type) {
		if (type.equals("�л�")) {
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
		// ������
		case "course":
			try {
				stmt = Model.con.prepareStatement("SELECT cr_name FROM curriculum");
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		// �л���ȣ
		case "student":
			try {
				stmt = Model.con.prepareStatement("SELECT st_id FROM student");
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		// �����
		case "teacher":
			try {
				stmt = Model.con.prepareStatement("SELECT t_name, t_id FROM teacher");
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		// ���ǽ�
		case "classroom":
			try {
				stmt = Model.con.prepareStatement("SELECT cls_id FROM classroom");
				rs = stmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		// �����
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

	// DBUtil�� del()���� ���� �����͸� ó���ϴ� �޼���, ���Ǿ ���� ���⼭ �����͸� ó���ϰ� ���� ���ڵ��� ���� ��ȯ
	int delQ(Object obj, String type) {
		if (type.equals("�л�")) {
			String sql = "delete from student where";
			p = (People) obj;
			idNum = p.getIdNum();
			try {
				sql = sql + " st_id = ?";
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, idNum);
				confirm = stmt.executeUpdate();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "���� �Ұ� : �� �������� �п��� ������ ��� �ֽ��ϴ�.");
			}

		} else if (type.equals("����")) {
			c = (Curri) obj;
			sql = "delete from curriculum where cr_id = ?";
			try {
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, c.getCr_id());
				confirm = stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("���� �Ұ� : �̹� ������ �����ϰ��ֽ��ϴ�.");
				JOptionPane.showMessageDialog(null, "���� �Ұ� : ������ �����ϴ� �л��� �ֽ��ϴ�.");
			}
		} else if (type.equals("����")) {
			p = (People) obj;
			sql = "DELETE FROM employee WHERE emp_id = ?";
			try {
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, p.getIdNum());
				confirm = stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "���� �Ұ� : ������ �����ϰ� �ִ� ���ǽ��� �ֽ��ϴ�.");
			}
		} else if (type.equals("����")) {
			t=(Teacher) obj;
			sql = "delete from teacher where t_id = ?";
			try {
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, t.getT_Id());
				confirm = stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("���� �Ұ� : �̹� ��ϵ� ID�Դϴ�..");
				JOptionPane.showMessageDialog(null, "���� �Ұ� : �̹� ��ϵ� ID�Դϴ�..");
			}
		} 
		else if (type.equals("����")) {
			b = (Book) obj;
			String sql = "delete from book where bk_name = ?";
			try {
				stmt = Model.con.prepareStatement(sql);
				stmt.setString(1, b.getBk_name());
				confirm = stmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "���� �Ұ� : �� ���縦 ����ϰ� �ִ� ������ �ֽ��ϴ�.");
			}
		}else if (type.equals("���ǽ�")) {
			cls = (Classroom) obj;
			try {
				sql = "DELETE FROM classroom_employee WHERE cls_id = ?"; // 1. Classroom_Employee ���� ������
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, cls.getCls_id());
				stmt.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			try {
				sql = "DELETE FROM classroom WHERE cls_id = ?"; // 2. Classroom ���� ������
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, cls.getCls_id());
				stmt.executeUpdate();
				confirm = stmt.executeUpdate();
			} catch (SQLException e) {  // 3. ������ ���� ������ Curriculum���� �����ϱ� ���� => 1���� ������ �� �ٽ� insert���ֱ�
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "���� �Ұ� : �� ���ǽ��� ����ϰ� �ִ� ������ �ֽ��ϴ�.");
				
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
	
	// �� �����ϴ� �޼ҵ�
	void updateQ(Object obj, String type) {
		if(type.equals("���ǽ�")) {
			cls = (Classroom) obj;
			try {
				// classroom ���̺�
				sql = "UPDATE classroom SET cls_id = ?, desk = ?, project = ? WHERE cls_id = ?";
				stmt = Model.con.prepareStatement(sql);
				stmt.setInt(1, cls.getCls_id());
				stmt.setInt(2, cls.getDesk());
				stmt.setInt(3, cls.getProject());
				stmt.setInt(4, cls.getCls_id());
				stmt.executeUpdate();
				// classroom_employee ���̺�
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
		else if (type.equals("����")) {
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
		else if (type.equals("�л�")) {
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
		else if (type.equals("����")) {
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
		else if (type.equals("����")) {
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
		else if (type.equals("����")) {
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

	// ������ �޺��ڽ��� �̸� ����, ������� �����ϴ� �޼����
		/* ���� Query */
	public DefaultComboBoxModel getTId(DefaultComboBoxModel tid) throws SQLException {
		tid = new DefaultComboBoxModel();
		sql = "select t_name, t_id from teacher order by t_id";
		stmt = Model.con.prepareStatement(sql);
		rs = stmt.executeQuery();
		tid.addElement("���� ����");
		while (rs.next()) {
			tid.addElement(rs.getString("t_name") + "(" + rs.getInt("t_id") + ")");
		}
		return tid;
	}
	/* ���ǽ� Query*/
	public DefaultComboBoxModel getClsId(DefaultComboBoxModel clsid) throws SQLException {
		clsid = new DefaultComboBoxModel();
		sql = "select cls_id from classroom order by cls_id";
		stmt = Model.con.prepareStatement(sql);
		rs = stmt.executeQuery();
		clsid.addElement("���ǽ� ����");
		while (rs.next()) {
			clsid.addElement(rs.getInt("cls_id"));
		}
		return clsid;
	}

	/* �� Query*/
	public DefaultComboBoxModel getBkName(DefaultComboBoxModel bkname) throws SQLException {
		bkname = new DefaultComboBoxModel();
		sql = "select bk_name from book order by bk_name";
		stmt = Model.con.prepareStatement(sql);
		rs = stmt.executeQuery();
		bkname.addElement("���� ����");
		while (rs.next()) {
			bkname.addElement(rs.getString("bk_name"));
		}
		return bkname;
	}

	/* ���� Query */
	public DefaultComboBoxModel getEmpId(DefaultComboBoxModel empid) throws SQLException {
		empid = new DefaultComboBoxModel();
		sql = "select emp_id, emp_name from employee order by emp_id";
		stmt = Model.con.prepareStatement(sql);
		rs = stmt.executeQuery();
		empid.addElement("���� ����");
		while(rs.next()){
			empid.addElement(rs.getString("emp_name") + "(" + rs.getInt("emp_id") + ")");
		}
		return empid;
	}

	/**
	 * ������� �߰�
	 * 
	 * @param _st_id
	 *            �л���ȣ
	 * @param _cr_name
	 *            ������
	 * @return �۾� �Ϸ� �� ���ŵ� ����� ��ȸ�� ResultSet
	 */
	ResultSet applyCourseAdd(int _st_id, String _cr_name) {
		int st_id = _st_id;
		String cr_name = _cr_name;
		try {
			// �Ѱܹ��� ��������κ��� �����ȣ�� ����
			int cr_id = getCurriculumNo(cr_name);
			// �ߺ�����
			stmt = Model.con.prepareStatement("SELECT st_id, cr_id FROM student_curriculum WHERE st_id=?");
			stmt.setInt(1, st_id);
			rs = stmt.executeQuery();
			System.out.println("���� ��ȸ");
			boolean flag = false;
			while (rs.next()) {
				// �л��� �̹� ������ ������ �� ����������� �ʵ���
				if (st_id == rs.getInt(1) && cr_id == rs.getInt(2)) {
					JOptionPane.showMessageDialog(null, "��û�� �ߺ��Ǿ����ϴ�.");
				} else {
					System.out.println("�ߺ����� �÷��� Ȱ��ȭ");
					flag = true;
				}
			}
			// �����ȣ�� ����Ͽ� DB�� �������
			if (flag == true) {
				stmt = Model.con.prepareStatement(
						"INSERT INTO student_curriculum(st_id, cr_id, regi_cr) VALUES(?, ?, sysdate)");
				stmt.setInt(1, st_id);
				stmt.setInt(2, cr_id);
				// ���� �ð����� ���
				stmt.executeUpdate();
				System.out.println("���� ����");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// JTable ������ ���� �ٽ� ������ �޾ƿ���
		rs = searchList("apply");
		return rs;
	}

	/**
	 * ������� öȸ
	 * 
	 * @param _st_name
	 *            �л���
	 * @param _cr_name
	 *            ������
	 * @return �۾� �Ϸ� �� ���ŵ� ����� ��ȸ�� ResultSet
	 */
	ResultSet applyCourseDelete(String _st_name, String _cr_name) {
		String st_name = _st_name;
		String cr_name = _cr_name;
		try {
			// �Ѱܹ��� �̸����κ��� ��ȣ�� ����
			int cr_id = getCurriculumNo(cr_name);
			int st_id = getStudentNo(st_name);
			// �����۾� ����
			stmt = Model.con.prepareStatement("DELETE FROM student_curriculum WHERE st_id=? AND cr_id=?");
			stmt.setInt(1, st_id);
			stmt.setInt(2, cr_id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// JTable ������ ���� �ٽ� ������ �޾ƿ���
		rs = searchList("apply");
		return rs;
	}

	/**
	 * ������� ����
	 * 
	 * @param _st_name
	 *            �л���
	 * @param _cr_name
	 *            ������
	 * @return �۾� �Ϸ� �� ���ŵ� ����� ��ȸ�� ResultSet
	 */
	ResultSet applyCourseModify(String _st_name, String _cr_nameOld, String _cr_nameNew) {
		String st_name = _st_name;
		String cr_nameOld = _cr_nameOld;
		String cr_nameNew = _cr_nameNew;
		try {
			// �Ѱܹ��� �̸����κ��� ��ȣ�� ����
			int cr_idOld = getCurriculumNo(cr_nameOld);
			int cr_idNew = getCurriculumNo(cr_nameNew);
			int st_id = getStudentNo(st_name);
			// �����۾� ����
			stmt = Model.con.prepareStatement("UPDATE student_curriculum SET cr_id=? WHERE st_id=" + st_id + " AND cr_id=" + cr_idOld);
			stmt.setInt(1, cr_idNew);
			stmt.executeUpdate();
			System.out.println("���� ����");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// JTable ������ ���� �ٽ� ������ �޾ƿ���
		rs = searchList("apply");
		return rs;
	}

	/**
	 * ���� ����Ȯ��
	 * 
	 * @param _st_name
	 *            �л���
	 * @param _cr_name
	 *            ������
	 * @return �۾� �Ϸ� �� ���ŵ� ����� ��ȸ�� ResultSet
	 */
	ResultSet applyCourseConfirm(String _st_name, String _cr_name) {
		String st_name = _st_name;
		String cr_name = _cr_name;
		try {
			// �Ѱܹ��� �̸����κ��� ��ȣ�� ����
			int st_id = getStudentNo(st_name);
			int cr_id = getCurriculumNo(cr_name);
			// ���� �ð����� �����Ͻø� ���
			stmt = Model.con
					.prepareStatement("UPDATE student_curriculum SET regi_pay=sysdate WHERE st_id=? AND cr_id=?");
			stmt.setInt(1, st_id);
			stmt.setInt(2, cr_id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// JTable ������ ���� �ٽ� ������ �޾ƿ���
		rs = searchList("apply");
		return rs;
	}

	/**
	 * ������û ���̺��� ��������� �����ȣ ���
	 * @param cr_name ������
	 * @return ������ȣ�� int������ ��ȯ 
	 */
	int getCurriculumNo(String cr_name) {
		int cr_id = 0;
		try {
			stmt = Model.con.prepareStatement("SELECT cr_id FROM curriculum WHERE cr_name=?");
			stmt.setString(1, cr_name);
			rs = stmt.executeQuery();
			rs.next();
			cr_id = rs.getInt(1);
			System.out.println("�����ȣ ����");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cr_id;
	}

	/**
	 * ������û ���̺��� �л������� �л���ȣ ���
	 * @param st_name �л���
	 * @return �л���ȣ�� int������ ��ȯ
	 */
	int getStudentNo(String st_name) {
		int st_id = 0;
		try {
			stmt = Model.con.prepareStatement("SELECT st_id FROM student WHERE st_name=?");
			stmt.setString(1, st_name);
			rs = stmt.executeQuery();
			rs.next();
			st_id = rs.getInt(1);
			System.out.println("�����ȣ ����");
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
