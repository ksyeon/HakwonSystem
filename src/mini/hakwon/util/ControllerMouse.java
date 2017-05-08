package mini.hakwon.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.JTextField;

import mini.hakwon.view.ViewClassroom;
import mini.hakwon.view.ViewCourse;
import mini.hakwon.view.ViewStaff;
import mini.hakwon.view.ViewStudent;
import mini.hakwon.view.ViewTeacher;
import mini.hakwon.view.ViewText;

public class ControllerMouse implements MouseListener {
	private JTable table;
	private String type;
	
	public JTable getTable() {	return table;}
	public String getType() {	return type;}

	public ControllerMouse(JTable table, String type) {
		this.table = table;
		this.type = type;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		getSelectedData(getTable(), getType());
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		getSelectedData(getTable(), getType());
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
public void getSelectedData(JTable table, String type) {
		
		int selectedR = table.getSelectedRow();
		
		if(type.equals("classroom")) {
			ViewClassroom.tfClassroomNo.setText((String)table.getValueAt(selectedR, 0));
			ViewClassroom.tfClassroomDesk.setText((String)table.getValueAt(selectedR, 1));
			ViewClassroom.tfClassroomProjector.setText((String)table.getValueAt(selectedR, 2));
			
			boolean find = false;
			for(int i=1; i<ViewClassroom.cbClassroomManager.getItemCount(); i++) { 
				String combobox = ViewClassroom.cbClassroomManager.getItemAt(i).toString().substring(0, 3);
				String selectedEmpName = (String) table.getValueAt(selectedR, 4);
				find = combobox.equals(selectedEmpName);
				
				if(find) {
					ViewClassroom.cbClassroomManager.setSelectedIndex(i);
					break;
				}
			}
		}
		else if(type.equals("employee")) {
			ViewStaff.tfStaffId.setText((String) table.getValueAt(selectedR, 0));
			ViewStaff.tfStaffName.setText((String) table.getValueAt(selectedR, 1));
			ViewStaff.tfStaffSal.setText((String) table.getValueAt(selectedR, 3));
			ViewStaff.tfStaffNum.setText((String) table.getValueAt(selectedR, 2));
			ViewStaff.tfStaffAddr.setText((String) table.getValueAt(selectedR, 5));
			ViewStaff.tfStaffPhone.setText((String) table.getValueAt(selectedR, 4));
		}
		else if (type.equals("student")) {
			ViewStudent.tfStudentNo.setText((String) table.getValueAt(selectedR, 0));
			ViewStudent.tfStudentName.setText((String) table.getValueAt(selectedR, 3));
			ViewStudent.tfStudentSSN1.setText((String) table.getValueAt(selectedR, 5));
			ViewStudent.tfStudentAddr.setText((String) table.getValueAt(selectedR, 4));
			ViewStudent.tfStudentTel.setText((String) table.getValueAt(selectedR, 2));
		}
		else if (type.equals("book")) {
			ViewText.tfTextTitle.setText((String) table.getValueAt(selectedR, 0));
			ViewText.tfTextPublisher.setText((String) table.getValueAt(selectedR, 1));
			ViewText.tfTextPrice.setText((String) table.getValueAt(selectedR, 2));
		}
		else if (type.equals("curriculum")) {
			ViewCourse.tfCourseNo.setText((String) table.getValueAt(selectedR, 0));
			ViewCourse.tfCourseName.setText((String) table.getValueAt(selectedR, 1));
			ViewCourse.tfCourseCharge.setText((String) table.getValueAt(selectedR, 4));
			ViewCourse.tfCourseRStartDate.setText((String) table.getValueAt(selectedR, 6));
			ViewCourse.tfCourseREndDate.setText((String) table.getValueAt(selectedR, 7));
			ViewCourse.tfCourseStartDate.setText((String) table.getValueAt(selectedR, 8));
			
			boolean find = false;
			for(int i=1; i<ViewCourse.cbCourseInstName.getItemCount(); i++) { 
				String combobox = ViewCourse.cbCourseInstName.getItemAt(i).toString().substring(0, 3);
				String selectedTName = (String) table.getValueAt(selectedR, 2);
				find = combobox.equals(selectedTName);
				if(find) {
					ViewCourse.cbCourseInstName.setSelectedIndex(i);
					break;
				}
			}
			find = false;
			for(int i=1; i<ViewCourse.cbCourseRoomNo.getItemCount(); i++) { 
				String combobox = ViewCourse.cbCourseRoomNo.getItemAt(i).toString();
				String selectedCls = (String) table.getValueAt(selectedR, 3);
				find = combobox.equals(selectedCls);
				if(find) {
					ViewCourse.cbCourseRoomNo.setSelectedIndex(i);
					break;
				}
			}
			find = false;
			for(int i=1; i<ViewCourse.cbCourseText.getItemCount(); i++) { 
				String combobox = ViewCourse.cbCourseText.getItemAt(i).toString();
				String selectedBName = (String) table.getValueAt(selectedR, 5);
				find = combobox.equals(selectedBName);
				if(find) {
					ViewCourse.cbCourseText.setSelectedIndex(i);
					break;
				}
			}
		}
		else if (type.equals("teacher")) {
			ViewTeacher.tfT_Id.setText((String) table.getValueAt(selectedR, 0));
			ViewTeacher.tfT_Name.setText((String)table.getValueAt(selectedR, 1));
			ViewTeacher.tf_Addr.setText((String)table.getValueAt(selectedR, 3));
			ViewTeacher.tfT_Phone.setText((String)table.getValueAt(selectedR, 6));
			ViewTeacher.tfLicenceName.setText((String)table.getValueAt(selectedR, 4));
			ViewTeacher.tfLicenceNum.setText((String)table.getValueAt(selectedR, 5));
			ViewTeacher.tf_SSN.setText((String)table.getValueAt(selectedR, 2));
			ViewTeacher.tf_Sal.setText((String)table.getValueAt(selectedR, 8));
			
			if(table.getValueAt(selectedR, 7).equals('Y')){ // 傈加咯何 'Y'
				ViewTeacher.rdbtnFull.setState(true);
			}
			else { //傈加咯何 'N'
				ViewTeacher.rdbtnPart.setState(true);
			}
		
		}
	}
			

	
}
