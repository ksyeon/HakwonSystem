package mini.hakwon.util;

import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

import mini.hakwon.model.Book;
import mini.hakwon.model.Classroom;
import mini.hakwon.model.Curri;
import mini.hakwon.model.Order;
import mini.hakwon.model.People;
import mini.hakwon.model.Teacher;
import mini.hakwon.view.ViewApply;
import mini.hakwon.view.ViewClassroom;
import mini.hakwon.view.ViewCourse;
import mini.hakwon.view.ViewOrder;
import mini.hakwon.view.ViewStaff;
import mini.hakwon.view.ViewStudent;
import mini.hakwon.view.ViewTeacher;
import mini.hakwon.view.ViewText;

//NEw

public class ControllerAction implements ActionListener, ItemListener {

	/**
	 * View�� �׼��̺�Ʈ���� ó���ϴ� Ŭ���� �ش� ��� : ��ư, �޺��ڽ�
	 * 
	 * @param student
	 * @param course
	 * @param apply
	 * @param text
	 * @param staff
	 * @param teacher
	 * @param textOrder
	 */
	private ViewStudent student;
	private ViewCourse course;
	private ViewApply apply;
	private ViewText text;
	private ViewStaff staff;
	private ViewTeacher teacher;
	private ViewOrder order;
	
	private ViewClassroom classroom;

	People people;
	Book book;
	Curri cr;
	Order orderlist;
	Order orderAdd;
	Teacher tea;
	Classroom cls;

	Query q = new Query();
	DbUtil db = new DbUtil();
	DefaultTableModel tableModel;
	DefaultComboBoxModel comboboxModel;

	PrintWriter pw = null;
	boolean confirm;
	String type, orderSta;
	// orderSta �ֹ��ǿ��� ������ �ֹ����� ���������� Ȯ��

	char contract; // ���������� ���ӿ���.

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand().toString();
		int sno = 0;
		System.out.println("��ư Ŭ�� " + cmd);
		if (cmd.equals("�л����")) {
			if (!student.tfStudentNo.getText().equals("")) {
				sno = Integer.parseInt(student.tfStudentNo.getText());
			} else {
				JOptionPane.showMessageDialog(null, "�л���ȣ�� �Է¾ȵǾ����ϴ�.");
			}
			confirm = db.SerachId(sno, q.STUDENT);
			String name = student.tfStudentName.getText();
			String ssn1 = student.tfStudentSSN1.getText();
			String ssn2 = new String(student.pfStudentSSN2.getText());
			// �н����� ����
			String addr = student.tfStudentAddr.getText();
			String tel = student.tfStudentTel.getText();
			if (sno == 0) {
				System.out.println("�л� ��ȣ�� �Էµ��� �ʾҽ��ϴ�.");
				return;
			} else if (confirm = false) {
				System.out.println("�̹� ��ȣ�� �ֽ��ϴ�.");
				return;
			} else {
				String peopleN = ssn1 + ssn2;
				people = new People(sno, name, peopleN, addr, tel);
				db = new DbUtil(people, q.STUDENT);
				db.add();
				tableModel = db.getTable("student");
				ViewStudent.tblStudent.setModel(tableModel);
			}
		} else if (cmd.equals("�л�����")) {
			int selectedR = ViewStudent.tblStudent.getSelectedRow();
			if (selectedR == -1) { // ���� �������� �ʾ��� ��
				JOptionPane.showMessageDialog(null, "������ �л��� �������ּ���.");
				return;
			}
			int st_id = Integer.parseInt((String) ViewStudent.tblStudent.getValueAt(selectedR, 0));
			System.out.println(st_id);
			people = new People(st_id);
			System.out.println("1" + people.getIdNum());

			db = new DbUtil(people, q.STUDENT);
			db.del();
			tableModel = db.getTable("student");
			ViewStudent.tblStudent.setModel(tableModel);
		} else if (cmd.equals("�л��˻�")) {
			// ���� ��ü
			int id;
			if (student.tfStudentNo.getText().equals(""))
				id = -1;
			else
				id = Integer.parseInt(student.tfStudentNo.getText());

			String name = student.tfStudentName.getText();
			String phone = student.tfStudentTel.getText();
			String pn = student.tfStudentSSN1.getText();

			People p = new People(id, name, pn, phone);
			db = new DbUtil(p, q.STUDENT);
			db.Search();

			tableModel = db.getTable("student");
			ViewStudent.tblStudent.setModel(tableModel);

		} 
		else if (cmd.equals("�л�����")) {
			int st_id;
			String st_name, st_num, st_addr, st_phone, ssn1, ssn2;

			st_id = Integer.parseInt(student.tfStudentNo.getText());
			st_name = student.tfStudentName.getText();
			ssn1 = student.tfStudentSSN1.getText();
			ssn2 = new String(student.pfStudentSSN2.getText());
			st_addr = student.tfStudentAddr.getText();
			st_phone = student.tfStudentTel.getText();
			
			st_num = ssn1 + ssn2;
			people = new People(st_id, st_name, st_num, st_addr, st_phone);
			db = new DbUtil(people, q.STUDENT);
			db.update();
			
			tableModel = db.getTable("student");
			ViewStudent.tblStudent.setModel(tableModel);
		}		
		else if (cmd.equals("�����߰�")) {
			// ���ڵ尡 ������ INSERT, ���ڵ尡 ������ UPDATE
			if (course.tfCourseNo.getText().equals("") || course.tfCourseName.getText().equals("")
					|| course.tfCourseCharge.getText().equals("") || course.cbCourseInstName.getSelectedIndex() == 0
					|| course.cbCourseRoomNo.getSelectedIndex() == 0 || course.cbCourseText.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(null, "���� ������ ��� �Է����ּ���.");
				return;
			}

			int cr_id = Integer.parseInt(course.tfCourseNo.getText());
			String cr_name = course.tfCourseName.getText();
			int t_id = Integer.parseInt(course.cbCourseInstName.getSelectedItem().toString().substring(4, 7));
			int cls_id = Integer.parseInt(course.cbCourseRoomNo.getSelectedItem().toString());
			int cr_fee = Integer.parseInt(course.tfCourseCharge.getText());
			String bk_name = course.cbCourseText.getSelectedItem().toString();
			String regi_start = course.tfCourseRStartDate.getText();
			String regi_end = course.tfCourseREndDate.getText();
			String cr_start = course.tfCourseStartDate.getText();

			cr = new Curri(cr_id, cr_name, t_id, cls_id, cr_fee, bk_name, regi_start, regi_end, cr_start); // �����ڷ�
			db = new DbUtil(cr, q.CURRI);
			db.add();

			tableModel = db.getTable("curriculum");
			ViewCourse.tblCourse.setModel(tableModel);
		} else if (cmd.equals("��������")) {
			int selectedR = ViewCourse.tblCourse.getSelectedRow();
			if (selectedR == -1) { // ���� �������� �ʾ��� ��
				JOptionPane.showMessageDialog(null, "������ ������ �������ּ���.");
				return;
			}
			int cr_id = Integer.parseInt((String) ViewCourse.tblCourse.getValueAt(selectedR, 0));
			cr = new Curri(cr_id);
			db = new DbUtil(cr, q.CURRI);
			db.del();
			// �߰��� ���� ���̺� ����
			tableModel = db.getTable("curriculum");
			ViewCourse.tblCourse.setModel(tableModel);
		} else if (cmd.equals("�����˻�")) {
			int cr_id, t_id, cls_id;
			String cr_name, bk_name;
			if (course.tfCourseNo.getText().equals("")) // ���� ����
				cr_id = -1;
			else
				cr_id = Integer.parseInt(course.tfCourseNo.getText());
			cr_name = course.tfCourseName.getText();
			if (course.cbCourseInstName.getSelectedIndex() == 0) // combobox����
				t_id = -1;
			else
				t_id = Integer.parseInt(course.cbCourseInstName.getSelectedItem().toString().substring(4, 7));

			if (course.cbCourseRoomNo.getSelectedIndex() == 0) // combobox����
				cls_id = -1;
			else
				cls_id = Integer.parseInt(course.cbCourseRoomNo.getSelectedItem().toString());
			if (course.cbCourseText.getSelectedIndex() == 0) // combobox���� �ƹ��͵�
				bk_name = "";

			else
				bk_name = course.cbCourseText.getSelectedItem().toString();

			cr = new Curri(cr_id, cr_name, t_id, cls_id, bk_name);
			db = new DbUtil(cr, q.CURRI);
			db.Search();

			tableModel = db.getTable("curriculum");
			ViewCourse.tblCourse.setModel(tableModel);
		} 
		else if (cmd.equals("��������")) {
			int t_id, cls_id;
			String bk_name;
			
			int cr_id = Integer.parseInt(course.tfCourseNo.getText());
			String cr_name = course.tfCourseName.getText();

			if(course.cbCourseInstName.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(null, "���縦 �����ϼ���.");
				return;
			}
			else 
				t_id = Integer.parseInt(course.cbCourseInstName.getSelectedItem().toString().substring(4, 7));
			
			if(course.cbCourseRoomNo.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(null, "���ǽ��� �����ϼ���.");
				return;
			}
			else 
				cls_id = Integer.parseInt(course.cbCourseRoomNo.getSelectedItem().toString());
			
			int cr_fee = Integer.parseInt(course.tfCourseCharge.getText());
			
			if(course.cbCourseText.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(null, "���縦 �����ϼ���.");
				return;
			}
			else 
				bk_name = course.cbCourseText.getSelectedItem().toString();
			String regi_start = course.tfCourseRStartDate.getText();
			String regi_end = course.tfCourseREndDate.getText();
			String cr_start = course.tfCourseStartDate.getText();

			cr = new Curri(cr_id, cr_name, t_id, cls_id, cr_fee, bk_name, regi_start, regi_end, cr_start); // �����ڷ�
			db = new DbUtil(cr, q.CURRI);
			db.update();

			tableModel = db.getTable("curriculum");
			ViewCourse.tblCourse.setModel(tableModel);
		}
		else if (cmd.equals("�����߰�")) {
			// ���ڵ尡 ������ INSERT, ���ڵ尡 ������ UPDATE
			String bk_name = text.tfTextTitle.getText();
			String bk_pub = text.tfTextPublisher.getText();
			int bk_fee;
			if (text.tfTextPrice.getText().equals(""))
				bk_fee = -1;
			else
				bk_fee = Integer.parseInt(text.tfTextPrice.getText());
			book = new Book(bk_name, bk_pub, bk_fee);
			db = new DbUtil(book, q.BOOK);
			db.add();

			tableModel = db.getTable("book");
			ViewText.tblText.setModel(tableModel);
		} else if (cmd.equals("�������")) {
			// ���ڵ� ����
			if (ViewText.tblText.getSelectedRow() == -1) { // ���� �������� �ʾ��� ��
				JOptionPane.showMessageDialog(null, "������ ���縦 �������ּ���");
				return;
			}
			String bk_name = (String) ViewText.tblText.getValueAt(ViewText.tblText.getSelectedRow(), 0);
			book = new Book(bk_name);
			db = new DbUtil(book, q.BOOK);
			db.del();
			tableModel = db.getTable("book");
			ViewText.tblText.setModel(tableModel);
		} else if (cmd.equals("����˻�")) {
			String bk_name = text.tfTextTitle.getText();
			String bk_pub = text.tfTextPublisher.getText();

			int bk_fee;
			if (text.tfTextPrice.getText().equals("")) // ���� ����.
				bk_fee = -1;
			else
				bk_fee = Integer.parseInt(text.tfTextPrice.getText());

			book = new Book(bk_name, bk_pub, bk_fee);
			db = new DbUtil(book, q.BOOK);
			db.Search();

			tableModel = db.getTable("book");
			ViewText.tblText.setModel(tableModel);
		} 
		else if (cmd.equals("�������")) {
			String bk_name = text.tfTextTitle.getText();
			String bk_pub = text.tfTextPublisher.getText();
			int bk_fee;
			if (text.tfTextPrice.getText().equals(""))
				bk_fee = -1;
			else
				bk_fee = Integer.parseInt(text.tfTextPrice.getText());
			book = new Book(bk_name, bk_pub, bk_fee);
			db = new DbUtil(book, q.BOOK);
			db.update();
			
			tableModel = db.getTable("book");
			ViewText.tblText.setModel(tableModel);
		}
		else if (cmd.equals("�ֹ��˻�")) {
			// üũ�ڽ����� �� �������� �����ؾߵ�
			String s = order.comboBox.getSelectedItem().toString();// � ������
																	// �����Ұ���
			String value;
			orderlist = new Order(s, orderSta);
			db = new DbUtil(orderlist, q.ORDER);
			db.Search();
			if (orderSta.equals("Supply")) {
				value = "Supply";
			} else {
				value = "order";
			}
			tableModel = db.getTable(value);
			order.tbl_Order.setModel(tableModel);
		} 
		
		else if(cmd.equals("�ֹ����")){
		int supplyID=-1;
		//�ֹ���ȣ/ �ֹ��� /�ֹ���¥/ ������ҳ�¥(�ɼ�)/ ����/ ������ID
		int ordNo = Integer.parseInt(order.tfOrdNo.getText());
		int ordCnt = Integer.parseInt(order.tfOrdCnt.getText());
		String ordDate = order.tfOrdDate.getText();
		
		String book = course.cbCourseText.getSelectedItem().toString();
		
		//Supply ������ ���� ���ǹ� �ɾ��ֱ�.
		if(!order.tfSupplyNo.getText().equals("")){
		supplyID = Integer.parseInt(order.tfSupplyNo.getText());
		}
				
		//Non_�������
		if(order.tfPayDate.getText().equals("")){
		System.out.println("��� xx");
		orderAdd= new Order(ordNo,ordCnt,ordDate,book,supplyID);
		}
		//+ �������
		else{
			System.out.println("��� ����");
		String payDate = order.tfPayDate.getText();
		orderAdd= new Order(ordNo,ordCnt,ordDate,payDate,book,supplyID);
		}
		//������ ������ֱ�.
		db = new DbUtil(orderAdd, q.ORDERADD);
		db.add();
		
		
		//���ŵ� ���̺� ���
		tableModel = db.Search();
		order.tbl_Order.setModel(tableModel);
		
	}else if (cmd.equals("�����ڵ��")) {
			int supplyID = Integer.parseInt(order.tfSupplyNo.getText().toString());
			String supplyName = order.tfSupplyName.getText();

			orderlist = new Order(supplyName, supplyID);
			db = new DbUtil(orderlist, q.SUPPLY);
			orderlist.setType("search");
			db.add();
			tableModel = db.Search();
			order.tbl_Order.setModel(tableModel);
			System.out.println(supplyName);
		} else if (cmd.equals("�����߰�")) {
			int emp_id, emp_sal;
			String emp_name, emp_num, emp_addr, emp_phone;

			emp_id = Integer.parseInt(staff.tfStaffId.getText());
			emp_name = staff.tfStaffName.getText();
			emp_num = staff.tfStaffNum.getText();
			emp_sal = Integer.parseInt(staff.tfStaffSal.getText());
			emp_addr = staff.tfStaffAddr.getText();
			emp_phone = staff.tfStaffPhone.getText();

			people = new People(emp_id, emp_name, emp_num, emp_addr, emp_phone, emp_sal);
			db = new DbUtil(people, q.EMPLOYEE);
			db.add();

			tableModel = db.getTable("employee");
			ViewStaff.tblStaff.setModel(tableModel);

			try { // ComboBox ����
				comboboxModel = q.getEmpId(comboboxModel);
				ViewClassroom.cbClassroomManager.setModel(comboboxModel);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (cmd.equals("��������")) {
			int selectedR = ViewStaff.tblStaff.getSelectedRow();
			if (selectedR == -1) { // ���� �������� �ʾ��� ��
				JOptionPane.showMessageDialog(null, "������ ������ �������ּ���.");
				return;
			}
			int emp_id = Integer.parseInt((String) ViewStaff.tblStaff.getValueAt(selectedR, 0));
			people = new People(emp_id);
			db = new DbUtil(people, q.EMPLOYEE);
			db.del();

			tableModel = db.getTable("employee");
			ViewStaff.tblStaff.setModel(tableModel);

			try { // ComboBox ����
				comboboxModel = q.getEmpId(comboboxModel);
				ViewClassroom.cbClassroomManager.setModel(comboboxModel);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (cmd.equals("�����˻�")) {
			int emp_id, emp_sal;
			String emp_name, emp_phone;

			if (staff.tfStaffId.getText().equals("")) // ���� ����
				emp_id = -1;
			else
				emp_id = Integer.parseInt(staff.tfStaffId.getText());

			emp_name = staff.tfStaffName.getText();

			if (staff.tfStaffSal.getText().equals(""))
				emp_sal = -1;
			else
				emp_sal = Integer.parseInt(staff.tfStaffSal.getText());

			emp_phone = staff.tfStaffPhone.getText();

			people = new People(emp_id, emp_name, emp_sal, emp_phone);
			db = new DbUtil(people, q.EMPLOYEE);
			db.Search();

			tableModel = db.getTable("employee");
			ViewStaff.tblStaff.setModel(tableModel);

		} 
		else if(cmd.equals("��������")) {
			int emp_id, emp_sal;
			String emp_name, emp_num, emp_addr, emp_phone;

			emp_id = Integer.parseInt(staff.tfStaffId.getText());
			emp_name = staff.tfStaffName.getText();
			emp_num = staff.tfStaffNum.getText();
			emp_sal = Integer.parseInt(staff.tfStaffSal.getText());
			emp_addr = staff.tfStaffAddr.getText();
			emp_phone = staff.tfStaffPhone.getText();

			people = new People(emp_id, emp_name, emp_num, emp_addr, emp_phone, emp_sal);
			db = new DbUtil(people, q.EMPLOYEE);
			db.update();
			
			tableModel = db.getTable("employee");
			ViewStaff.tblStaff.setModel(tableModel);
		}
		else if(cmd.equals("�����߰�")){
			 int t_Id=Integer.parseInt(teacher.tfT_Id.getText());;
			 String t_Name=teacher.tfT_Name.getText();
			 String t_Phone=teacher.tfT_Phone.getText();
			 int t_Sal=Integer.parseInt(teacher.tf_Sal.getText());
			 String addr=teacher.tf_Addr.getText();;
			 String licenceName=teacher.tfLicenceName.getText();
			 int licenceNum=Integer.parseInt(teacher.tfLicenceNum.getText());
			 String t_Num=teacher.tf_SSN.getText();
			 
					 
			 //���ӿ��δ� ItemState Change����.
			 tea = new Teacher(t_Id, t_Name,t_Phone, t_Sal,addr,licenceName,licenceNum,t_Num,contract); 
			db = new DbUtil(tea, q.TEACHER);
			db.add();
			
			tableModel=db.getTable("teacher");
			ViewTeacher.tblTeacher.setModel(tableModel);
			//���ŵ� ���̺������� �ٽ� ����Ѵ�.// add�� ��������.
		} else if (cmd.equals("�������")) {
				int t_Id;
				int selectedR = ViewTeacher.tblTeacher.getSelectedRow();
				if(selectedR == -1) { // ���� �������� �ʾ��� ��
					JOptionPane.showMessageDialog(null, "������ ������ �������ּ���.");
					return;
				}
				else {
					t_Id 
					= Integer.parseInt((String) ViewTeacher.tblTeacher.getValueAt(selectedR, 0));
				}
				
				tea = new Teacher(t_Id);
				db = new DbUtil(tea, q.TEACHER);
				db.del();

				// �߰��� ���� ���̺� ����
				tableModel = db.getTable("teacher");
				ViewTeacher.tblTeacher.setModel(tableModel);
				
		} else if (cmd.equals("����˻�")) {		//Search��ư Ŭ��������.
			int t_Id,t_Sal;
			String t_Name,addr,licenceName, t_phone, t_num;
				
			if (teacher.tfT_Id.getText().equals(""))
				t_Id = -1;
			else	
				t_Id = Integer.parseInt(teacher.tfT_Id.getText());
			
			t_num = teacher.tf_SSN.getText();
			t_Name=teacher.tfT_Name.getText();
				
			if(teacher.tf_Sal.getText().equals(""))
				t_Sal = -1;
			else
				t_Sal = Integer.parseInt(teacher.tf_Sal.getText());
				
			addr=teacher.tf_Addr.getText();
			licenceName=teacher.tfLicenceName.getText();
			t_phone = teacher.tfT_Phone.getText();
			
			
			//������ �ʱ�ȭ
			tea = new Teacher(t_Id,t_Name,t_Sal, addr,licenceName, t_phone, t_num, contract) ;
			db = new DbUtil(tea, q.TEACHER);
			db.Search();			
			
			tableModel = db.getTable("teacher");
			ViewTeacher.tblTeacher.setModel(tableModel);
			
		} else if (cmd.equals("�������")) {
			int t_Id=Integer.parseInt(teacher.tfT_Id.getText());
			String t_Name=teacher.tfT_Name.getText();
			String t_Phone=teacher.tfT_Phone.getText();
			int t_Sal=Integer.parseInt(teacher.tf_Sal.getText());
			String addr=teacher.tf_Addr.getText();;
			String licenceName=teacher.tfLicenceName.getText();
			int licenceNum=Integer.parseInt(teacher.tfLicenceNum.getText());
			String t_Num=teacher.tf_SSN.getText();
					
			tea = new Teacher(t_Id, t_Name,t_Phone, t_Sal,addr,licenceName,licenceNum,t_Num,contract); 
			db = new DbUtil(tea, q.TEACHER);
			db.update();  
			
			tableModel=db.getTable("teacher");
			ViewTeacher.tblTeacher.setModel(tableModel);
		}
		 else if (cmd.equals("���ǽ��߰�")) {
			int cls_id = Integer.parseInt(classroom.tfClassroomNo.getText());
			int desk = Integer.parseInt(classroom.tfClassroomDesk.getText());
			int project = Integer.parseInt(classroom.tfClassroomProjector.getText());

			int emp_id;
			if (classroom.cbClassroomManager.getSelectedIndex() == 0)
				emp_id = -1;
			else
				emp_id = Integer.parseInt(classroom.cbClassroomManager.getSelectedItem().toString().substring(4, 7));

			cls = new Classroom(cls_id, desk, project, emp_id);
			db = new DbUtil(cls, q.CLASSROOM);
			db.add();

			tableModel = db.getTable("classroom");
			ViewClassroom.tblClassroom.setModel(tableModel);

			try { // ComboBox ����
				comboboxModel = q.getClsId(comboboxModel);
				ViewCourse.cbCourseRoomNo.setModel(comboboxModel);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (cmd.equals("���ǽǻ���")) {
			int selectedR = ViewClassroom.tblClassroom.getSelectedRow();
			int cls_id = Integer.parseInt((String) ViewClassroom.tblClassroom.getValueAt(selectedR, 0));
			int emp_id = Integer.parseInt((String) ViewClassroom.tblClassroom.getValueAt(selectedR, 3));
			cls = new Classroom(cls_id, emp_id);
			db = new DbUtil(cls, q.CLASSROOM);
			db.del();

			tableModel = db.getTable("classroom");
			ViewClassroom.tblClassroom.setModel(tableModel);

			try { // ComboBox ����
				comboboxModel = q.getClsId(comboboxModel);
				ViewCourse.cbCourseRoomNo.setModel(comboboxModel);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (cmd.equals("���ǽǰ˻�")) {
			int cls_id, emp_id, desk;

			if (classroom.tfClassroomNo.getText().equals(""))
				cls_id = -1;
			else
				cls_id = Integer.parseInt(classroom.tfClassroomNo.getText());

			if (classroom.cbClassroomManager.getSelectedIndex() == 0)
				emp_id = -1;
			else
				emp_id = Integer.parseInt(classroom.cbClassroomManager.getSelectedItem().toString().substring(4, 7));

			if (classroom.tfClassroomDesk.getText().equals(""))
				desk = -1;
			else
				desk = Integer.parseInt(classroom.tfClassroomDesk.getText());

			cls = new Classroom(cls_id, desk, emp_id);
			db = new DbUtil(cls, q.CLASSROOM);
			db.Search();

			tableModel = db.getTable("classroom");
			ViewClassroom.tblClassroom.setModel(tableModel);
		}
		else if (cmd.equals("���ǽǼ���")) {
			int cls_id = Integer.parseInt(classroom.tfClassroomNo.getText());
			int desk = Integer.parseInt(classroom.tfClassroomDesk.getText());
			int project = Integer.parseInt(classroom.tfClassroomProjector.getText());
			
			int emp_id;
			if (classroom.cbClassroomManager.getSelectedIndex() == 0)
				emp_id = -1;
			else
				emp_id = Integer.parseInt(classroom.cbClassroomManager.getSelectedItem().toString().substring(4, 7));
			
			cls = new Classroom(cls_id, desk, project, emp_id);
			db = new DbUtil(cls, q.CLASSROOM);
			db.update();
			
			tableModel = db.getTable("classroom");
			ViewClassroom.tblClassroom.setModel(tableModel);
		}
		else if (cmd.equals("�������")) {
			// ������� 
			if (apply.cbStudentName.getSelectedIndex() == 0 && apply.cbCourseName.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(null, "��� ������ �Է��� �ּ���.");
				return;
			}
			// �л���ȣ�� ������ ���
			String student = apply.cbStudentName.getSelectedItem().toString();
			int st_id = Integer.parseInt(student.substring(student.indexOf('(') + 1, student.lastIndexOf(')')));
			String cr_name = apply.cbCourseName.getSelectedItem().toString();
			System.out.println(st_id);
			System.out.println(cr_name);
			// DBó���� ���� �л���ȣ�� ������ �Ѱ��ֱ�
			db = new DbUtil();
			db.applyCourseAdd(st_id, cr_name);
			// ����� DB����� ���̺� �籸��
			tableModel = db.getTable("apply");
			apply.tblApply.setModel(tableModel);
		} else if (cmd.equals("����öȸ")) {
			// ����öȸ
			// ȭ��󿡼� ������ ���� �������� �۾�
			int selRow = apply.tblApply.getSelectedRow();
			if (selRow == -1) {
				JOptionPane.showMessageDialog(apply, "�۾��� ���� ������ �ּ���.");
				return;
			}
			// �л���� ������ ���
			String st_name = apply.tblApply.getValueAt(selRow, 0).toString();
			String cr_name = apply.tblApply.getValueAt(selRow, 1).toString();
			// DBó���� ���� �л���� ������ �Ѱ��ֱ�
			db = new DbUtil();
			db.applyCourseDelete(st_name, cr_name);
			// ����� DB����� ���̺� �籸�� 
			tableModel = db.getTable("apply");
			apply.tblApply.setModel(tableModel);
		} else if (cmd.equals("��������")) {
			// ��������
			// ȭ��󿡼� ������ ���� �������� �۾�
			int selRow = apply.tblApply.getSelectedRow();
			if (selRow == -1) {
				JOptionPane.showMessageDialog(apply, "�۾��� ���� ������ �ּ���.");
				return;
			}
			// ���������� ���� �л��� �ٸ� ���������� ��� ��츸���� ����
			// �л���ȣ�� ���������, �������� ���
			String st_name = apply.tblApply.getValueAt(selRow, 0).toString();
			String cr_nameOld = apply.tblApply.getValueAt(selRow, 1).toString();
			String cr_nameNew = apply.cbCourseName.getSelectedItem().toString();
			// �ٲٷ��� ������ ���� ������ ������ �˻�
			if (cr_nameOld.equals(cr_nameNew)) {
				JOptionPane.showMessageDialog(apply, "�ٲٷ��� ���� �̹� �ִ� ���� �����ϴ�.");
				return;
			}
			// DBó���� ���� �л���� �������� �Ѱ��ֱ�
			db = new DbUtil();
			db.applyCourseModify(st_name, cr_nameOld, cr_nameNew);
			// ����� DB����� ���̺� �籸��
			tableModel = db.getTable("apply");
			apply.tblApply.setModel(tableModel);
		} else if (cmd.equals("����Ȯ��")) {
			// ����Ȯ��
			// ȭ��󿡼� ������ ���� �������� �۾�
			int selRow = apply.tblApply.getSelectedRow();
			if (selRow == -1) {
				JOptionPane.showMessageDialog(apply, "�۾��� ���� ������ �ּ���.");
				return;
			}
			// �л���� ������ ���
			String st_name = apply.tblApply.getValueAt(selRow, 0).toString();
			String cr_name = apply.tblApply.getValueAt(selRow, 1).toString();
			// DBó���� ���� �л���� ������ �Ѱ��ֱ�
			db = new DbUtil();
			db.applyCourseConfirm(st_name, cr_name);
			// ����� DB����� ���̺� �籸��
			tableModel = db.getTable("apply");
			apply.tblApply.setModel(tableModel);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// Contract ���ӿ��ο� ���� ������ư
		Checkbox c = (Checkbox) e.getSource();
		tea = new Teacher();
		if (c.getLabel().equals("Supply")) {
			orderSta = c.getLabel().toString();
			order.comboBox.setModel(new DefaultComboBoxModel(new String[] { "�����ڼ�", "��ȣ��" }));
		} else if (c.getLabel().equals("Order")) {
			orderSta = c.getLabel().toString();
			order.comboBox
					.setModel(new DefaultComboBoxModel(new String[] { "��������", "��������", "����� ����", "�ֹ�����", "�����ڼ�" }));
		} else if (c.getLabel() == "Full") {
			// contract�� y�� ����.
			System.out.println(c.getLabel().toString());
			//tea.setContract('Y');
			contract = 'Y';
			System.out.println(contract);
		} else {
			//tea.setContract('N');
			contract = 'N';
			System.out.println(contract);
		}
	}

}
