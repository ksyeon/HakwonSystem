import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import mini.hakwon.view.ViewApply;
import mini.hakwon.view.ViewCourse;
import mini.hakwon.view.ViewStaff;
import mini.hakwon.view.ViewStudent;
import mini.hakwon.view.ViewTeacher;
import mini.hakwon.view.ViewText;


public class ControllerKey implements KeyListener{
	
	/**
	 * View�� Ű�̺�Ʈ���� ó���ϴ� Ŭ����
	 * 
	 * @param student
	 * @param course
	 * @param apply
	 * @param text
	 * @param staff
	 * @param teacher
	 * @param textOrder
	 */
	private ViewStudent		student;
	private ViewCourse		course;
	private ViewApply		apply;
	private ViewText		text;
	private ViewStaff		staff;
	private ViewTeacher		teacher;
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
}
