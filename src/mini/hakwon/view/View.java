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
	 * ������ ���� - ����
	 * 
	 * @param viewStudent
	 *            �л����� �г��� �޾ƿ� ViewStudent ��ü
	 * @param viewCourse
	 *            �������� �г��� �޾ƿ� ViewCourse ��ü
	 * @param viewText
	 *            �������� �г��� �޾ƿ� ViewText ��ü
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
		setTitle("�п����� ���α׷�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 660);

		/**
		 * @param menubar
		 *            �޴����� �ν��Ͻ�
		 */
		ViewMenubar menubar = new ViewMenubar();
		setJMenuBar(menubar);
		/**
		 * @param contentPane
		 *            ���� ȭ��
		 * @param tabbedPane
		 *            �� ȭ��
		 */
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		/**
		 * @param viewStudent			�л����� �г�
		 * @param viewCourse			�������� �г�
		 * @param viewApply				������� �г�
		 * @param viewText				�������� �г�
		 * @param viewTeacher			�л����� �г�
		 * @param viewStaff				�������� �г�
		 * @param viewTextOrder			�����ֹ����� �г�
		 * @param viewClassromm			���ǽ����� �г�
		 */
		viewStudent = new ViewStudent();
		viewCourse = new ViewCourse();
		viewText = new ViewText();
		viewApply = new ViewApply();
		viewTeacher	= new ViewTeacher();
		viewStaff= new ViewStaff();
		viewOrder = new ViewOrder();
		viewClassroom = new ViewClassroom();

		tabbedPane.addTab("�л�����", null, viewStudent, null);
		tabbedPane.addTab("��������", null, viewCourse, null);
		tabbedPane.addTab("�������", null, viewApply, null);
		tabbedPane.addTab("��������", null, viewText, null);
		tabbedPane.addTab("��������", null, viewTeacher, null);
		tabbedPane.addTab("��������", null, viewStaff, null);
		tabbedPane.addTab("�ֹ�����", null, viewOrder, null);
		tabbedPane.addTab("���ǽǰ���", null, viewClassroom, null);
	}

}
