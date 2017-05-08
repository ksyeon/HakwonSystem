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
	Boolean isSearch = false; // ���� '�˻�'������ �ƴ��� Ȯ���ϱ� ���� �뵵

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

	// �� �ܿ��� ������ ���̽��� �߰��Ҷ� ��ġ�� �޼���
	void add() {
		if (type.equals("�л�")) {
			int i = q.add(obj, q.STUDENT);
			if (i > 0)
				System.out.println("�����Ͱ� �߰��ƽ��ϴ�.");
		} else if (type.equals("����")) {
			int i = q.add(obj, q.BOOK);
			if (i > 0)
				System.out.println("�����Ͱ� �߰��ƽ��ϴ�.");
		} else if (type.equals("����")) {
			int i = q.add(obj, q.TEACHER);
			if (i > 0)
				System.out.println("���簡 �߰��ƽ��ϴ�.");
		} else if (type.equals("����")) {
			int i = q.add(obj, q.EMPLOYEE);
			if (i > 0)
				System.out.println("�����Ͱ� �߰��ƽ��ϴ�.");
		} else if (type.equals("����")) {
			int i = q.add(obj, q.CURRI);
			if (i > 0)
				System.out.println("�����Ͱ� �߰��ƽ��ϴ�.");
		} else if (type.equals("���ǽ�")) {
			int i = q.add(obj, q.CLASSROOM);
			if (i > 0)
				System.out.println("�����Ͱ� �߰��ƽ��ϴ�.");
		} else if (type.equals("������")) {
			int i = q.add(obj, type);
			if (i > 0)
				System.out.println("�����Ͱ� �߰��ƽ��ϴ�.");
		}
		 else if (type.equals("�ֹ��߰�")) {//�������� TYPE Ȯ���Ұ�.
				int i = q.add(obj, type);
				if (i > 0)
					System.out.println("�����Ͱ� �߰��ƽ��ϴ�.");
			}
			
		

	}

	// �� �ܿ��� �����ͺ��̽��� �����͸� ��ȸ�Ҷ� ��ġ�� �޼���
	DefaultTableModel Search() {
		int i = 0;
		if (type.equals("�л�")) {
			p = (People) obj;
			rs = q.searchQ(p, q.STUDENT);
			isSearch = true;
		} else if (type.equals("����")) {
			b = (Book) obj;
			rs = q.searchQ(b, q.BOOK);
			isSearch = true;
			// ���� �ؾߵ�
		} else if (type.equals("����")) {
			t = (Teacher) obj;
			rs = q.searchQ(t, q.TEACHER);
			isSearch = true;
		}else if (type.equals("����")) {
			p = (People) obj;
			rs = q.searchQ(p, q.EMPLOYEE);
			isSearch = true;
		} else if (type.equals("����")) {
			c = (Curri) obj;
			rs = q.searchQ(c, q.CURRI);
			isSearch = true;
		} else if (type.equals("���ǽ�")) {
			cr = (Classroom) obj;
			rs = q.searchQ(cr, q.CLASSROOM);
			isSearch = true;
		} else if (type.equals("�ֹ�")) {
			o = (Order) obj;
			String state = o.getOrder();
			System.out.println("db��ƿ�� �Ѿ��" + state);
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

	// �� �ܿ��� DB�� �����͸� �����ϰ��� �Ҷ� ��ġ�� �޼���
	void del() {
		int i = -1;
		if (type.equals("�л�")) {
			int r = q.delQ(obj, type);
		} else if (type.equals("����")) {
			i = q.delQ(obj, type);
		}
		else if (type.equals("����")) {
			Teacher tea = (Teacher) obj;
			i = q.delQ(tea, q.TEACHER);
		} else if (type.equals("����")) {
			p = (People) obj;
			i = q.delQ(p, q.EMPLOYEE);
		} else if (type.equals("����")) {
			Curri cr = (Curri) obj;
			i = q.delQ(cr, q.CURRI);
		} else if (type.equals("���ǽ�")) {
			cr = (Classroom) obj;
			i = q.delQ(cr, q.CLASSROOM);

		}
	}
	// ���� ����!!!!
	void update() {
		if (type.equals("���ǽ�")) {
			cr = (Classroom) obj;
			q.updateQ(cr, q.CLASSROOM);
		}
		else if (type.equals("����")) {
			p = (People) obj;
			q.updateQ(p, q.EMPLOYEE);
		}
		else if (type.equals("�л�")) {
			p = (People) obj;
			q.updateQ(p, q.STUDENT);
		}
		else if (type.equals("����")) {
			b = (Book) obj;
			q.updateQ(b, q.BOOK);
		}
		else if (type.equals("����")) {
			c = (Curri) obj;
			q.updateQ(c, q.CURRI);	
		}
		else if (type.equals("����")) {
			t = (Teacher) obj;
			q.updateQ(t, q.TEACHER);
		}
	}
	// id�� �̿��Ͽ� DB�� �����Ͱ� �ִ��� Ȯ���ϴ� �޼���
	// �ַ� �߰��� �Ҷ� �̸� �˻��� �ؾ������� ���� ����ϴ� �޼���
	boolean SerachId(int idnum, String type) {
		boolean confirm = false;
		if (type.equals("�л�")) {
			System.out.println("dbutil���� ���̵� �˻��� �����մϴ�.");
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

	// ���� SerachId�޼���� ���� ������� ���⼭�� �̸��� ������
	// ��) �л��̸�, �����̸�, �����̸� ���
	boolean SerachName(String name, String type) {
		boolean confirm = false;
		if (type.equals("�л�")) {
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
	 * �����ͺ��̽��κ��� �޺��ڽ��� ����� ���͸� ����
	 * 
	 * @param arg
	 *            ������ �׸��� ���ϴ� ���ڿ� �Ű�����<br>
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

	/* �޺��ڽ��� ���� ���� ���� */
	public Vector<String> getList(String arg) {
		vector = new Vector<String>();
		vector.add("�����ϼ���");
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
		case "student": // ������� - �л��� �޺��ڽ�
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

	/* �����ͺ��̽��κ��� ���̺�� ���� */
	public DefaultTableModel getTable(String arg) {
		switch (arg) {
		// �л�����
		case "student":
			try {
				if (isSearch) { // �˻��� ���� rs�� ���� �������� ������
					rs = q.searchQ(p, q.STUDENT);
				} else {
					rs = q.searchList(arg);
				}
				if (rs != null) {
					meta = rs.getMetaData();
					int i = 1;
					// ���̺� ���
					tableModel.setColumnIdentifiers(new Object[] { "�л���ȣ", "�����", "����ó", "�̸�", "������", "�ֹε�Ϲ�ȣ" });
					// ���̺� ����
					Object[] tblApplyBody = new Object[meta.getColumnCount()];
					while (rs.next()) {
						for (i = 1; i <= meta.getColumnCount(); i++) {
							tblApplyBody[i - 1] = rs.getString(i);
						}
						tableModel.addRow(tblApplyBody);
					}
				} else {
					tableModel.setColumnIdentifiers(new Object[] { "�л���ȣ", "�����", "����ó", "�̸�", "������", "�ֹε�Ϲ�ȣ" });
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// ������� ����
			break;
		case "apply":
			try {
				if (isSearch) { // �˻��� ���� rs�� ���� �������� ������
					rs = q.searchQ(cr, q.CLASSROOM);
				} else {
					rs = q.searchList(arg);
				}
				rs = q.searchList(arg);
				if (rs != null) {
					meta = rs.getMetaData();
					int i = 1;
					// ���̺� ���
					tableModel.setColumnIdentifiers(new String[] { "�л���", "������", "��Ͻ�����", "��ϸ�����", "�����", "������" });
					// ���̺� ����
					Object[] tblApplyBody = new Object[meta.getColumnCount()];
					while (rs.next()) {
						for (i = 1; i <= meta.getColumnCount(); i++) {
							tblApplyBody[i - 1] = rs.getString(i);
						}
						tableModel.addRow(tblApplyBody);
					}
				} else {
					tableModel.setColumnIdentifiers(new String[] { "�л���", "������", "��Ͻ�����", "��ϸ�����", "�����", "������" });
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;
		case "order":
			try {
				if (isSearch) { // �˻��� ���� rs�� ���� �������� ������
					rs = q.searchQ(o, q.ORDER);
				} else {
					rs = q.searchList(arg);
				}
				rs = q.searchList(arg);
				if (rs != null) {
					meta = rs.getMetaData();
					int i = 1;
					// ���̺� ���
					tableModel.setColumnIdentifiers(
							new String[] { "�ֹ���ȣ", "�ֹ���", "�ֹ���¥", "������ҳ�¥",  "����", "������ ��ȣ" });
					// ���̺� ����
					Object[] tblApplyBody = new Object[meta.getColumnCount()];
					while (rs.next()) {
						for (i = 1; i <= meta.getColumnCount(); i++) {
							tblApplyBody[i - 1] = rs.getString(i);
						}
						tableModel.addRow(tblApplyBody);
					}
				} else {
					tableModel.setColumnIdentifiers(
							new String[] { "�ֹ���ȣ", "�ֹ���", "�ֹ���¥", "������ҳ�¥","����", "������ ��ȣ" });
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;
		case "curriculum":
			try {
				if (isSearch) { // �˻��� ���� rs�� ���� �������� ������
					rs = q.searchQ(c, q.CURRI);
				} else {
					rs = q.searchList(arg);
				}
				if (rs != null) {
					// DB�� �ƹ��� ���� ������ ������ ������ �����ϱ�����
					meta = rs.getMetaData();
					int i = 1;
					tableModel.setColumnIdentifiers(
							new Object[] { "������ȣ", "������", "�����", "���ǽ�", "������", "����", "��Ͻ�����", "��� ������", "������" });
					Object[] tblApplyBody = new Object[meta.getColumnCount()];
					while (rs.next()) {
						for (i = 1; i <= meta.getColumnCount(); i++) {
							tblApplyBody[i - 1] = rs.getString(i);
						}
						tableModel.addRow(tblApplyBody);
					}
				} else {
					tableModel.setColumnIdentifiers(
							new Object[] { "������ȣ", "������", "�����", "���ǽ�", "������", "����", "��Ͻ�����", "��� ������", "������" });
				}
				isSearch = false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		// ���� ���� ���
		case "employee":
			try {
				if (isSearch)
					rs = q.searchQ(p, q.EMPLOYEE);
				else
					rs = q.searchList(arg);

				meta = rs.getMetaData();
				int i = 1;
				tableModel.setColumnIdentifiers(new Object[] { "������ȣ", "������", "�ֹι�ȣ", "����", "����ó", "�ּ�" });
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
					// ���̺� ���
					tableModel.setColumnIdentifiers(new Object[] { "��������", "���ǻ��", "���簡��" });
					// ���̺� ����
					Object[] tblBookBody = new Object[meta.getColumnCount()];
					while (rs.next()) {
						for (i = 1; i <= meta.getColumnCount(); i++) {
							tblBookBody[i - 1] = rs.getString(i);
						}
						tableModel.addRow(tblBookBody);
					}
				} else {
					tableModel.setColumnIdentifiers(new Object[] { "��������", "���ǻ��", "���簡��" });
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
				// ���̺� ���
				tableModel.setColumnIdentifiers(new Object[] {"�����ȣ", "�����̸�","�ֹι�ȣ", "������", "�ڰ��� �̸�", "�ڰ��� ��ȣ", "����ó", "���ӿ���", "����"});
				
				// ���̺� ����
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
					// ���̺� ���
					tableModel.setColumnIdentifiers(new Object[] { "���ǽǹ�ȣ", "å���", "�������ͼ�", "�������", "�����̸�" });
					// ���̺� ����
					Object[] tblApplyBody = new Object[meta.getColumnCount()];
					while (rs.next()) {
						for (i = 1; i <= meta.getColumnCount(); i++) {
							tblApplyBody[i - 1] = rs.getString(i);
						}
						tableModel.addRow(tblApplyBody);
					}
				} else {
					tableModel.setColumnIdentifiers(new Object[] { "���ǽǹ�ȣ", "å���", "�������ͼ�", "�������", "�����̸�"});
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
					// ���̺� ���
					tableModel.setColumnIdentifiers(new Object[] { "������id", "��ȣ" });
					// ���̺� ����
					Object[] tblApplyBody = new Object[meta.getColumnCount()];
					while (rs.next()) {
						for (i = 1; i <= meta.getColumnCount(); i++) {
							tblApplyBody[i - 1] = rs.getString(i);
						}
						tableModel.addRow(tblApplyBody);
					}
				} else {
					tableModel.setColumnIdentifiers(new Object[] { "������id", "��ȣ" });
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
					// ���̺� ���
					tableModel.setColumnIdentifiers(new Object[] { "�ֹ���ȣ", "�ֹ���","�ֹ���¥","������ҳ�¥","����","�����ڹ�ȣ" });
					// ���̺� ����
					Object[] tblApplyBody = new Object[meta.getColumnCount()];
					while (rs.next()) {
						for (i = 1; i <= meta.getColumnCount(); i++) {
							tblApplyBody[i - 1] = rs.getString(i);
						}
						tableModel.addRow(tblApplyBody);
					}
				} else {
					tableModel.setColumnIdentifiers(new Object[] { "�ֹ���ȣ", "�ֹ���","�ֹ���¥","������ҳ�¥","����","�����ڹ�ȣ" });
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		
		}
		return tableModel;
	}

	/**
	 * ������� ������ ����
	 * @param _st_id �л���ȣ
	 * @param _cr_name ������
	 */
	void applyCourseAdd(int _st_id, String _cr_name) {
		q.applyCourseAdd(_st_id, _cr_name);
	}

	/**
	 * ����öȸ ���� ����
	 * @param _st_name �л���
	 * @param _cr_name ������
	 */
	void applyCourseDelete(String _st_name, String _cr_name) {
		q.applyCourseDelete(_st_name, _cr_name);
	}

	/**
	 * �������� ���� ����
	 * @param _st_name �л���
	 * @param _cr_name ������
	 */
	void applyCourseModify(String _st_name, String _cr_nameOld, String _cr_nameNew) {
		q.applyCourseModify(_st_name, _cr_nameOld, _cr_nameNew);
	}

	/**
	 * ������ ���� Ȯ�� ���� ����
	 * @param _st_name �л���
	 * @param _cr_name ������
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
