package mini.hakwon.view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JMenuItem;

public class View extends JFrame {

	/**
	 * 프레임 구성 - 공통
	 * 
	 * @param viewStudent
	 *            학생정보 패널을 받아올 ViewStudent 객체
	 * @param viewCourse
	 *            과정정보 패널을 받아올 ViewCourse 객체
	 * @param viewText
	 *            교재정보 패널을 받아올 ViewText 객체
	 */
	private ViewStudent viewStudent;
	private ViewCourse viewCourse;
	private ViewText viewText;
	private ViewApply viewApply;
	private ViewTeacher viewTeacher;
	private ViewStaff viewStaff;
	private ViewOrder viewOrder;
	public static ViewClassroom viewClassroom;

	public View() {
		setTitle("학원관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 660);

		/**
		 * @param menubar
		 *            메뉴막대 인스턴스
		 */
		ViewMenubar menubar = new ViewMenubar();
		setJMenuBar(menubar);
		/**
		 * @param contentPane
		 *            내용 화면
		 * @param tabbedPane
		 *            탭 화면
		 */
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		/**
		 * @param viewStudent			학생정보 패널
		 * @param viewCourse			과정정보 패널
		 * @param viewApply				수강등록 패널
		 * @param viewText				교재정보 패널
		 * @param viewTeacher			학생정보 패널
		 * @param viewStaff				과정정보 패널
		 * @param viewTextOrder			교재주문정보 패널
		 * @param viewClassromm			강의실정보 패널
		 */
		viewStudent = new ViewStudent();
		viewCourse = new ViewCourse();
		viewText = new ViewText();
		viewApply = new ViewApply();
		viewTeacher	= new ViewTeacher();
		viewStaff= new ViewStaff();
		viewOrder = new ViewOrder();
		viewClassroom = new ViewClassroom();

		tabbedPane.addTab("학생정보", null, viewStudent, null);
		tabbedPane.addTab("과정정보", null, viewCourse, null);
		tabbedPane.addTab("수강등록", null, viewApply, null);
		tabbedPane.addTab("교재정보", null, viewText, null);
		tabbedPane.addTab("강사정보", null, viewTeacher, null);
		tabbedPane.addTab("직원정보", null, viewStaff, null);
		tabbedPane.addTab("주문관리", null, viewOrder, null);
		tabbedPane.addTab("강의실관리", null, viewClassroom, null);
	}

}
