package mini.hakwon.view;
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mini.hakwon.model.People;
import mini.hakwon.util.ControllerAction;
import mini.hakwon.util.ControllerMouse;
import mini.hakwon.util.DbUtil;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class ViewStudent extends JPanel {
	People people;

	/**
	 * �л�����
	 * 
	 * @param pnlStudentInfo		�л������� �Է¾���� ��ġ�ϴ� �г�
	 * @param pnlStudentInfoForm1	�л����� �Է¾�� ù��°�� �г�
	 * @param pnlStudentInfoForm2	�л����� �Է¾�� �ι�°�� �г�
	 * @param pnlStudentInfoBtn		�л����� �Է¾�� ��ư �г�
	 * @param lblStudentNo			�л���ȣ ���̺�
	 * @param tfStudentNo			�л���ȣ �ؽ�Ʈ�ʵ�
	 * @param lblStudentName		�л��̸� ���̺�
	 * @param tfStudentName			�л��̸� �ؽ�Ʈ�ʵ�
	 * @param lblStudentSSN			�л��ֹι�ȣ ���̺�
	 * @param tfStudentSSN1			�л��ֹι�ȣ �� 6�ڸ� �ؽ�Ʈ�ʵ�
	 * @param pfStudentSSN2			�л��ֹι�ȣ �� 7�ڸ� �н����� �ؽ�Ʈ�ʵ�
	 * @param lblStudentAddr		�л��ּ� ���̺�
	 * @param tfStudentAddr			�л��ּ� �ؽ�Ʈ�ʵ�
	 * @param lblStudentTel			�л�����ó ���̺�
	 * @param tfStudentTel			�л�����ó �ؽ�Ʈ�ʵ�
	 * @param btnStudentAdd			�л���� ��ư
	 * @param btnStudentDel			�л����� ��ư
	 * @param btnStudentSrch		�л��˻� ��ư
	 * @param btnStudentClear		�Է� �ʱ�ȭ ��ư
	 * @param spStudent				�л����� ���̺��� ���� ��ũ���г�
	 * @param mdlStudent			�л����� ���̺��
	 * @param tblStudent			�л����� ���̺�
	 */
	private JPanel				pnlStudentInfo, pnlStudentInfoForm1, pnlStudentInfoForm2, pnlStudentInfoBtn;
	private JLabel				lblStudentNo, lblStudentName, lblStudentSSN, lblStudentAddr, lblStudentTel;
	public static JTextField			tfStudentNo;

	public static JTextField tfStudentName;

	public static JTextField tfStudentSSN1;

	public static JTextField tfStudentAddr;

	public static JTextField tfStudentTel;
   	public static JPasswordField		pfStudentSSN2;
	private JButton				btnStudentAdd, btnStudentDel, btnStudentSearch, btnStudentClear;
	private JScrollPane			spStudent;
	public static DefaultTableModel	 mdlStudent;
	public static JTable				tblStudent;
	
	
	
	/**
	 * ������ ����
	 */
	public ViewStudent() {
		ControllerAction controller = new ControllerAction();
		DbUtil db = new DbUtil();
		/**
		 * @param action 			�׼� �̺�Ʈ �ν��Ͻ�
		 * @param key				Ű �̺�Ʈ �ν��Ͻ�
		 */
		
		setLayout(new BorderLayout(0, 0));

		pnlStudentInfo = new JPanel();
		add(pnlStudentInfo, BorderLayout.NORTH);
		pnlStudentInfo.setLayout(new BoxLayout(pnlStudentInfo, 1));

		pnlStudentInfoForm1 = new JPanel();
		pnlStudentInfo.add(pnlStudentInfoForm1);

		lblStudentNo = new JLabel("�л���ȣ");
		pnlStudentInfoForm1.add(lblStudentNo);

		tfStudentNo = new JTextField();
		pnlStudentInfoForm1.add(tfStudentNo);
		tfStudentNo.setColumns(6);

		lblStudentName = new JLabel("�л���");
		pnlStudentInfoForm1.add(lblStudentName);

		tfStudentName = new JTextField();
		pnlStudentInfoForm1.add(tfStudentName);
		tfStudentName.setColumns(10);

		lblStudentSSN = new JLabel("�ֹε�Ϲ�ȣ");
		pnlStudentInfoForm1.add(lblStudentSSN);

		tfStudentSSN1 = new JTextField();
		pnlStudentInfoForm1.add(tfStudentSSN1);
		tfStudentSSN1.setColumns(6);

		pnlStudentInfoForm1.add(new JLabel("-"));

		pfStudentSSN2 = new JPasswordField();
		tfStudentSSN1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				// 6�ڸ��� ä��� �ڵ����� ���� �ؽ�Ʈ�ʵ�� Ŀ�� �̵�
				if( tfStudentSSN1.getText().length() == 5 ) {
					pfStudentSSN2.requestFocus();
				}
			}
		});
		pfStudentSSN2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				// 7�ڸ��� �Ѿ�� ���� ó��
				if( pfStudentSSN2.getPassword().length == 6 ) {
					pfStudentSSN2.requestFocus(false);
				}
			}
		});
		pnlStudentInfoForm1.add(pfStudentSSN2);
		pfStudentSSN2.setColumns(7);
		
		pnlStudentInfoForm2 = new JPanel();
		pnlStudentInfo.add(pnlStudentInfoForm2);

		lblStudentAddr = new JLabel("�ּ�");
		pnlStudentInfoForm2.add(lblStudentAddr);

		tfStudentAddr = new JTextField();
		pnlStudentInfoForm2.add(tfStudentAddr);
		tfStudentAddr.setColumns(30);

		lblStudentTel = new JLabel("����ó");
		pnlStudentInfoForm2.add(lblStudentTel);

		tfStudentTel = new JTextField();
		pnlStudentInfoForm2.add(tfStudentTel);
		tfStudentTel.setColumns(12);

		pnlStudentInfoBtn = new JPanel();
		pnlStudentInfo.add(pnlStudentInfoBtn);

		btnStudentAdd = new JButton("�л����");
		btnStudentAdd.addActionListener(controller);
	
	
		pnlStudentInfoBtn.add(btnStudentAdd);

		JButton btnStudentDel = new JButton("�л�����");
		btnStudentDel.addActionListener(controller);
		pnlStudentInfoBtn.add(btnStudentDel);
		
		btnStudentClear = new JButton("�Է��ʱ�ȭ");
		btnStudentClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �Է³����� ����
				tfStudentNo.setText("");
				tfStudentName.setText("");
				tfStudentSSN1.setText("");
				pfStudentSSN2.setText("");
				tfStudentAddr.setText("");
				tfStudentTel.setText("");
				// ù �ؽ�Ʈ�ʵ�� Ŀ�� �̵�
				tfStudentNo.requestFocus();
			}
		});
		pnlStudentInfoBtn.add(btnStudentClear);

		JButton btnStudentSrch = new JButton("�л��˻�");
		pnlStudentInfoBtn.add(btnStudentSrch);
		btnStudentSrch.addActionListener(controller);

		JButton btnStudentUpdate = new JButton("�л�����");
		pnlStudentInfoBtn.add(btnStudentUpdate);
		btnStudentUpdate.addActionListener(controller);
		
		tblStudent = new JTable(db.getTable("student"));
		tblStudent.addMouseListener(new ControllerMouse(tblStudent, "student"));
		spStudent = new JScrollPane(tblStudent);
		add(spStudent, BorderLayout.CENTER);
		
	}

	protected JTextField getTfStudentNo() {
		return tfStudentNo;
	}

	protected void setTfStudentNo(JTextField tfStudentNo) {
		this.tfStudentNo = tfStudentNo;
	}

	protected JTextField getTfStudentName() {
		return tfStudentName;
	}

	protected void setTfStudentName(JTextField tfStudentName) {
		this.tfStudentName = tfStudentName;
	}

	protected JTextField getTfStudentSSN1() {
		return tfStudentSSN1;
	}

	protected void setTfStudentSSN1(JTextField tfStudentSSN1) {
		this.tfStudentSSN1 = tfStudentSSN1;
	}

	protected JTextField getTfStudentAddr() {
		return tfStudentAddr;
	}

	protected void setTfStudentAddr(JTextField tfStudentAddr) {
		this.tfStudentAddr = tfStudentAddr;
	}

	protected JTextField getTfStudentTel() {
		return tfStudentTel;
	}

	protected void setTfStudentTel(JTextField tfStudentTel) {
		this.tfStudentTel = tfStudentTel;
	}

	protected JPasswordField getPfStudentSSN2() {
		return pfStudentSSN2;
	}

	protected void setPfStudentSSN2(JPasswordField pfStudentSSN2) {
		this.pfStudentSSN2 = pfStudentSSN2;
	}

	protected JTable getTblStudent() {
		return tblStudent;
	}

	protected void setTblStudent(JTable tblStudent) {
		this.tblStudent = tblStudent;
	}
	
}
