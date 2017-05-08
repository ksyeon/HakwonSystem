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
	 * View의 액션이벤트들을 처리하는 클래스 해당 요소 : 버튼, 콤보박스
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
	// orderSta 주문탭에서 나열시 주문인지 공급자인지 확인

	char contract; // 강사페이지 전속여부.

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand().toString();
		int sno = 0;
		System.out.println("버튼 클릭 " + cmd);
		if (cmd.equals("학생등록")) {
			if (!student.tfStudentNo.getText().equals("")) {
				sno = Integer.parseInt(student.tfStudentNo.getText());
			} else {
				JOptionPane.showMessageDialog(null, "학생번호가 입력안되었습니다.");
			}
			confirm = db.SerachId(sno, q.STUDENT);
			String name = student.tfStudentName.getText();
			String ssn1 = student.tfStudentSSN1.getText();
			String ssn2 = new String(student.pfStudentSSN2.getText());
			// 패스워드 수정
			String addr = student.tfStudentAddr.getText();
			String tel = student.tfStudentTel.getText();
			if (sno == 0) {
				System.out.println("학생 번호가 입력되지 않았습니다.");
				return;
			} else if (confirm = false) {
				System.out.println("이미 번호가 있습니다.");
				return;
			} else {
				String peopleN = ssn1 + ssn2;
				people = new People(sno, name, peopleN, addr, tel);
				db = new DbUtil(people, q.STUDENT);
				db.add();
				tableModel = db.getTable("student");
				ViewStudent.tblStudent.setModel(tableModel);
			}
		} else if (cmd.equals("학생삭제")) {
			int selectedR = ViewStudent.tblStudent.getSelectedRow();
			if (selectedR == -1) { // 행을 선택하지 않았을 때
				JOptionPane.showMessageDialog(null, "삭제할 학생을 선택해주세요.");
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
		} else if (cmd.equals("학생검색")) {
			// 여기 교체
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
		else if (cmd.equals("학생수정")) {
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
		else if (cmd.equals("과정추가")) {
			// 레코드가 없으면 INSERT, 레코드가 있으면 UPDATE
			if (course.tfCourseNo.getText().equals("") || course.tfCourseName.getText().equals("")
					|| course.tfCourseCharge.getText().equals("") || course.cbCourseInstName.getSelectedIndex() == 0
					|| course.cbCourseRoomNo.getSelectedIndex() == 0 || course.cbCourseText.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(null, "과정 정보를 모두 입력해주세요.");
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

			cr = new Curri(cr_id, cr_name, t_id, cls_id, cr_fee, bk_name, regi_start, regi_end, cr_start); // 생성자로
			db = new DbUtil(cr, q.CURRI);
			db.add();

			tableModel = db.getTable("curriculum");
			ViewCourse.tblCourse.setModel(tableModel);
		} else if (cmd.equals("과정삭제")) {
			int selectedR = ViewCourse.tblCourse.getSelectedRow();
			if (selectedR == -1) { // 행을 선택하지 않았을 때
				JOptionPane.showMessageDialog(null, "삭제할 과정을 선택해주세요.");
				return;
			}
			int cr_id = Integer.parseInt((String) ViewCourse.tblCourse.getValueAt(selectedR, 0));
			cr = new Curri(cr_id);
			db = new DbUtil(cr, q.CURRI);
			db.del();
			// 추가된 과정 테이블에 갱신
			tableModel = db.getTable("curriculum");
			ViewCourse.tblCourse.setModel(tableModel);
		} else if (cmd.equals("과정검색")) {
			int cr_id, t_id, cls_id;
			String cr_name, bk_name;
			if (course.tfCourseNo.getText().equals("")) // 값이 없다
				cr_id = -1;
			else
				cr_id = Integer.parseInt(course.tfCourseNo.getText());
			cr_name = course.tfCourseName.getText();
			if (course.cbCourseInstName.getSelectedIndex() == 0) // combobox에서
				t_id = -1;
			else
				t_id = Integer.parseInt(course.cbCourseInstName.getSelectedItem().toString().substring(4, 7));

			if (course.cbCourseRoomNo.getSelectedIndex() == 0) // combobox에서
				cls_id = -1;
			else
				cls_id = Integer.parseInt(course.cbCourseRoomNo.getSelectedItem().toString());
			if (course.cbCourseText.getSelectedIndex() == 0) // combobox에서 아무것도
				bk_name = "";

			else
				bk_name = course.cbCourseText.getSelectedItem().toString();

			cr = new Curri(cr_id, cr_name, t_id, cls_id, bk_name);
			db = new DbUtil(cr, q.CURRI);
			db.Search();

			tableModel = db.getTable("curriculum");
			ViewCourse.tblCourse.setModel(tableModel);
		} 
		else if (cmd.equals("과정수정")) {
			int t_id, cls_id;
			String bk_name;
			
			int cr_id = Integer.parseInt(course.tfCourseNo.getText());
			String cr_name = course.tfCourseName.getText();

			if(course.cbCourseInstName.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(null, "강사를 선택하세요.");
				return;
			}
			else 
				t_id = Integer.parseInt(course.cbCourseInstName.getSelectedItem().toString().substring(4, 7));
			
			if(course.cbCourseRoomNo.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(null, "강의실을 선택하세요.");
				return;
			}
			else 
				cls_id = Integer.parseInt(course.cbCourseRoomNo.getSelectedItem().toString());
			
			int cr_fee = Integer.parseInt(course.tfCourseCharge.getText());
			
			if(course.cbCourseText.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(null, "교재를 선택하세요.");
				return;
			}
			else 
				bk_name = course.cbCourseText.getSelectedItem().toString();
			String regi_start = course.tfCourseRStartDate.getText();
			String regi_end = course.tfCourseREndDate.getText();
			String cr_start = course.tfCourseStartDate.getText();

			cr = new Curri(cr_id, cr_name, t_id, cls_id, cr_fee, bk_name, regi_start, regi_end, cr_start); // 생성자로
			db = new DbUtil(cr, q.CURRI);
			db.update();

			tableModel = db.getTable("curriculum");
			ViewCourse.tblCourse.setModel(tableModel);
		}
		else if (cmd.equals("교재추가")) {
			// 레코드가 없으면 INSERT, 레코드가 있으면 UPDATE
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
		} else if (cmd.equals("교재삭제")) {
			// 레코드 삭제
			if (ViewText.tblText.getSelectedRow() == -1) { // 행을 선택하지 않았을 때
				JOptionPane.showMessageDialog(null, "삭제할 교재를 선택해주세요");
				return;
			}
			String bk_name = (String) ViewText.tblText.getValueAt(ViewText.tblText.getSelectedRow(), 0);
			book = new Book(bk_name);
			db = new DbUtil(book, q.BOOK);
			db.del();
			tableModel = db.getTable("book");
			ViewText.tblText.setModel(tableModel);
		} else if (cmd.equals("교재검색")) {
			String bk_name = text.tfTextTitle.getText();
			String bk_pub = text.tfTextPublisher.getText();

			int bk_fee;
			if (text.tfTextPrice.getText().equals("")) // 값이 없다.
				bk_fee = -1;
			else
				bk_fee = Integer.parseInt(text.tfTextPrice.getText());

			book = new Book(bk_name, bk_pub, bk_fee);
			db = new DbUtil(book, q.BOOK);
			db.Search();

			tableModel = db.getTable("book");
			ViewText.tblText.setModel(tableModel);
		} 
		else if (cmd.equals("교재수정")) {
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
		else if (cmd.equals("주문검색")) {
			// 체크박스에서 값 가져오기 수정해야됨
			String s = order.comboBox.getSelectedItem().toString();// 어떤 순으로
																	// 나열할건지
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
		
		else if(cmd.equals("주문등록")){
		int supplyID=-1;
		//주문번호/ 주문량 /주문날짜/ 대금지불날짜(옵션)/ 교재/ 공급자ID
		int ordNo = Integer.parseInt(order.tfOrdNo.getText());
		int ordCnt = Integer.parseInt(order.tfOrdCnt.getText());
		String ordDate = order.tfOrdDate.getText();
		
		String book = course.cbCourseText.getSelectedItem().toString();
		
		//Supply 유무에 따른 조건문 걸어주기.
		if(!order.tfSupplyNo.getText().equals("")){
		supplyID = Integer.parseInt(order.tfSupplyNo.getText());
		}
				
		//Non_대금지불
		if(order.tfPayDate.getText().equals("")){
		System.out.println("대금 xx");
		orderAdd= new Order(ordNo,ordCnt,ordDate,book,supplyID);
		}
		//+ 대금지불
		else{
			System.out.println("대금 ㅇㅇ");
		String payDate = order.tfPayDate.getText();
		orderAdd= new Order(ordNo,ordCnt,ordDate,payDate,book,supplyID);
		}
		//쿼리문 등록해주기.
		db = new DbUtil(orderAdd, q.ORDERADD);
		db.add();
		
		
		//갱신된 테이블 출력
		tableModel = db.Search();
		order.tbl_Order.setModel(tableModel);
		
	}else if (cmd.equals("공급자등록")) {
			int supplyID = Integer.parseInt(order.tfSupplyNo.getText().toString());
			String supplyName = order.tfSupplyName.getText();

			orderlist = new Order(supplyName, supplyID);
			db = new DbUtil(orderlist, q.SUPPLY);
			orderlist.setType("search");
			db.add();
			tableModel = db.Search();
			order.tbl_Order.setModel(tableModel);
			System.out.println(supplyName);
		} else if (cmd.equals("직원추가")) {
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

			try { // ComboBox 갱신
				comboboxModel = q.getEmpId(comboboxModel);
				ViewClassroom.cbClassroomManager.setModel(comboboxModel);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (cmd.equals("직원삭제")) {
			int selectedR = ViewStaff.tblStaff.getSelectedRow();
			if (selectedR == -1) { // 행을 선택하지 않았을 때
				JOptionPane.showMessageDialog(null, "삭제할 직원을 선택해주세요.");
				return;
			}
			int emp_id = Integer.parseInt((String) ViewStaff.tblStaff.getValueAt(selectedR, 0));
			people = new People(emp_id);
			db = new DbUtil(people, q.EMPLOYEE);
			db.del();

			tableModel = db.getTable("employee");
			ViewStaff.tblStaff.setModel(tableModel);

			try { // ComboBox 갱신
				comboboxModel = q.getEmpId(comboboxModel);
				ViewClassroom.cbClassroomManager.setModel(comboboxModel);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (cmd.equals("직원검색")) {
			int emp_id, emp_sal;
			String emp_name, emp_phone;

			if (staff.tfStaffId.getText().equals("")) // 값이 없다
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
		else if(cmd.equals("직원수정")) {
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
		else if(cmd.equals("강사추가")){
			 int t_Id=Integer.parseInt(teacher.tfT_Id.getText());;
			 String t_Name=teacher.tfT_Name.getText();
			 String t_Phone=teacher.tfT_Phone.getText();
			 int t_Sal=Integer.parseInt(teacher.tf_Sal.getText());
			 String addr=teacher.tf_Addr.getText();;
			 String licenceName=teacher.tfLicenceName.getText();
			 int licenceNum=Integer.parseInt(teacher.tfLicenceNum.getText());
			 String t_Num=teacher.tf_SSN.getText();
			 
					 
			 //전속여부는 ItemState Change에서.
			 tea = new Teacher(t_Id, t_Name,t_Phone, t_Sal,addr,licenceName,licenceNum,t_Num,contract); 
			db = new DbUtil(tea, q.TEACHER);
			db.add();
			
			tableModel=db.getTable("teacher");
			ViewTeacher.tblTeacher.setModel(tableModel);
			//갱신된 테이블정보를 다시 출력한다.// add를 눌렀을때.
		} else if (cmd.equals("강사삭제")) {
				int t_Id;
				int selectedR = ViewTeacher.tblTeacher.getSelectedRow();
				if(selectedR == -1) { // 행을 선택하지 않았을 때
					JOptionPane.showMessageDialog(null, "삭제할 과정을 선택해주세요.");
					return;
				}
				else {
					t_Id 
					= Integer.parseInt((String) ViewTeacher.tblTeacher.getValueAt(selectedR, 0));
				}
				
				tea = new Teacher(t_Id);
				db = new DbUtil(tea, q.TEACHER);
				db.del();

				// 추가된 과정 테이블에 갱신
				tableModel = db.getTable("teacher");
				ViewTeacher.tblTeacher.setModel(tableModel);
				
		} else if (cmd.equals("강사검색")) {		//Search버튼 클릭했을때.
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
			
			
			//데이터 초기화
			tea = new Teacher(t_Id,t_Name,t_Sal, addr,licenceName, t_phone, t_num, contract) ;
			db = new DbUtil(tea, q.TEACHER);
			db.Search();			
			
			tableModel = db.getTable("teacher");
			ViewTeacher.tblTeacher.setModel(tableModel);
			
		} else if (cmd.equals("강사수정")) {
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
		 else if (cmd.equals("강의실추가")) {
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

			try { // ComboBox 갱신
				comboboxModel = q.getClsId(comboboxModel);
				ViewCourse.cbCourseRoomNo.setModel(comboboxModel);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (cmd.equals("강의실삭제")) {
			int selectedR = ViewClassroom.tblClassroom.getSelectedRow();
			int cls_id = Integer.parseInt((String) ViewClassroom.tblClassroom.getValueAt(selectedR, 0));
			int emp_id = Integer.parseInt((String) ViewClassroom.tblClassroom.getValueAt(selectedR, 3));
			cls = new Classroom(cls_id, emp_id);
			db = new DbUtil(cls, q.CLASSROOM);
			db.del();

			tableModel = db.getTable("classroom");
			ViewClassroom.tblClassroom.setModel(tableModel);

			try { // ComboBox 갱신
				comboboxModel = q.getClsId(comboboxModel);
				ViewCourse.cbCourseRoomNo.setModel(comboboxModel);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (cmd.equals("강의실검색")) {
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
		else if (cmd.equals("강의실수정")) {
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
		else if (cmd.equals("수강등록")) {
			// 수강등록 
			if (apply.cbStudentName.getSelectedIndex() == 0 && apply.cbCourseName.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(null, "모든 내용을 입력해 주세요.");
				return;
			}
			// 학생번호와 과정명 얻기
			String student = apply.cbStudentName.getSelectedItem().toString();
			int st_id = Integer.parseInt(student.substring(student.indexOf('(') + 1, student.lastIndexOf(')')));
			String cr_name = apply.cbCourseName.getSelectedItem().toString();
			System.out.println(st_id);
			System.out.println(cr_name);
			// DB처리를 위해 학생번호와 과정명 넘겨주기
			db = new DbUtil();
			db.applyCourseAdd(st_id, cr_name);
			// 변경된 DB결과로 테이블 재구성
			tableModel = db.getTable("apply");
			apply.tblApply.setModel(tableModel);
		} else if (cmd.equals("수강철회")) {
			// 수강철회
			// 화면상에서 선택한 행을 바탕으로 작업
			int selRow = apply.tblApply.getSelectedRow();
			if (selRow == -1) {
				JOptionPane.showMessageDialog(apply, "작업할 행을 선택해 주세요.");
				return;
			}
			// 학생명과 과정명 얻기
			String st_name = apply.tblApply.getValueAt(selRow, 0).toString();
			String cr_name = apply.tblApply.getValueAt(selRow, 1).toString();
			// DB처리를 위해 학생명과 과정명 넘겨주기
			db = new DbUtil();
			db.applyCourseDelete(st_name, cr_name);
			// 변경된 DB결과로 테이블 재구성 
			tableModel = db.getTable("apply");
			apply.tblApply.setModel(tableModel);
		} else if (cmd.equals("수강변경")) {
			// 수강변경
			// 화면상에서 선택한 행을 바탕으로 작업
			int selRow = apply.tblApply.getSelectedRow();
			if (selRow == -1) {
				JOptionPane.showMessageDialog(apply, "작업할 행을 선택해 주세요.");
				return;
			}
			// 수강변경은 같은 학생이 다른 수강과목을 듣는 경우만으로 한정
			// 학생번호와 현재과정명, 새과정명 얻기
			String st_name = apply.tblApply.getValueAt(selRow, 0).toString();
			String cr_nameOld = apply.tblApply.getValueAt(selRow, 1).toString();
			String cr_nameNew = apply.cbCourseName.getSelectedItem().toString();
			// 바꾸려는 과정이 기존 과정과 같은지 검사
			if (cr_nameOld.equals(cr_nameNew)) {
				JOptionPane.showMessageDialog(apply, "바꾸려는 값이 이미 있는 값과 같습니다.");
				return;
			}
			// DB처리를 위해 학생명과 새과정명 넘겨주기
			db = new DbUtil();
			db.applyCourseModify(st_name, cr_nameOld, cr_nameNew);
			// 변경된 DB결과로 테이블 재구성
			tableModel = db.getTable("apply");
			apply.tblApply.setModel(tableModel);
		} else if (cmd.equals("납부확인")) {
			// 납부확인
			// 화면상에서 선택한 행을 바탕으로 작업
			int selRow = apply.tblApply.getSelectedRow();
			if (selRow == -1) {
				JOptionPane.showMessageDialog(apply, "작업할 행을 선택해 주세요.");
				return;
			}
			// 학생명과 과정명 얻기
			String st_name = apply.tblApply.getValueAt(selRow, 0).toString();
			String cr_name = apply.tblApply.getValueAt(selRow, 1).toString();
			// DB처리를 위해 학생명과 과정명 넘겨주기
			db = new DbUtil();
			db.applyCourseConfirm(st_name, cr_name);
			// 변경된 DB결과로 테이블 재구성
			tableModel = db.getTable("apply");
			apply.tblApply.setModel(tableModel);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// Contract 전속여부에 대한 라디오버튼
		Checkbox c = (Checkbox) e.getSource();
		tea = new Teacher();
		if (c.getLabel().equals("Supply")) {
			orderSta = c.getLabel().toString();
			order.comboBox.setModel(new DefaultComboBoxModel(new String[] { "공급자순", "상호순" }));
		} else if (c.getLabel().equals("Order")) {
			orderSta = c.getLabel().toString();
			order.comboBox
					.setModel(new DefaultComboBoxModel(new String[] { "낮은수량", "높은수량", "교재명 순서", "주문일자", "공급자순" }));
		} else if (c.getLabel() == "Full") {
			// contract에 y를 저장.
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
