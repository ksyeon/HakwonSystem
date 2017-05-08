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
	 * 학생정보
	 * 
	 * @param pnlStudentInfo		학생정보의 입력양식이 위치하는 패널
	 * @param pnlStudentInfoForm1	학생정보 입력양식 첫번째줄 패널
	 * @param pnlStudentInfoForm2	학생정보 입력양식 두번째줄 패널
	 * @param pnlStudentInfoBtn		학생정보 입력양식 버튼 패널
	 * @param lblStudentNo			학생번호 레이블
	 * @param tfStudentNo			학생번호 텍스트필드
	 * @param lblStudentName		학생이름 레이블
	 * @param tfStudentName			학생이름 텍스트필드
	 * @param lblStudentSSN			학생주민번호 레이블
	 * @param tfStudentSSN1			학생주민번호 앞 6자리 텍스트필드
	 * @param pfStudentSSN2			학생주민번호 앞 7자리 패스워드 텍스트필드
	 * @param lblStudentAddr		학생주소 레이블
	 * @param tfStudentAddr			학생주소 텍스트필드
	 * @param lblStudentTel			학생연락처 레이블
	 * @param tfStudentTel			학생연락처 텍스트필드
	 * @param btnStudentAdd			학생등록 버튼
	 * @param btnStudentDel			학생삭제 버튼
	 * @param btnStudentSrch		학생검색 버튼
	 * @param btnStudentClear		입력 초기화 버튼
	 * @param spStudent				학생정보 테이블을 위한 스크롤패널
	 * @param mdlStudent			학생정보 테이블모델
	 * @param tblStudent			학생정보 테이블
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
	 * 프레임 구성
	 */
	public ViewStudent() {
		ControllerAction controller = new ControllerAction();
		DbUtil db = new DbUtil();
		/**
		 * @param action 			액션 이벤트 인스턴스
		 * @param key				키 이벤트 인스턴스
		 */
		
		setLayout(new BorderLayout(0, 0));

		pnlStudentInfo = new JPanel();
		add(pnlStudentInfo, BorderLayout.NORTH);
		pnlStudentInfo.setLayout(new BoxLayout(pnlStudentInfo, 1));

		pnlStudentInfoForm1 = new JPanel();
		pnlStudentInfo.add(pnlStudentInfoForm1);

		lblStudentNo = new JLabel("학생번호");
		pnlStudentInfoForm1.add(lblStudentNo);

		tfStudentNo = new JTextField();
		pnlStudentInfoForm1.add(tfStudentNo);
		tfStudentNo.setColumns(6);

		lblStudentName = new JLabel("학생명");
		pnlStudentInfoForm1.add(lblStudentName);

		tfStudentName = new JTextField();
		pnlStudentInfoForm1.add(tfStudentName);
		tfStudentName.setColumns(10);

		lblStudentSSN = new JLabel("주민등록번호");
		pnlStudentInfoForm1.add(lblStudentSSN);

		tfStudentSSN1 = new JTextField();
		pnlStudentInfoForm1.add(tfStudentSSN1);
		tfStudentSSN1.setColumns(6);

		pnlStudentInfoForm1.add(new JLabel("-"));

		pfStudentSSN2 = new JPasswordField();
		tfStudentSSN1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				// 6자리를 채우면 자동으로 다음 텍스트필드로 커서 이동
				if( tfStudentSSN1.getText().length() == 5 ) {
					pfStudentSSN2.requestFocus();
				}
			}
		});
		pfStudentSSN2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				// 7자리를 넘어가는 글자 처리
				if( pfStudentSSN2.getPassword().length == 6 ) {
					pfStudentSSN2.requestFocus(false);
				}
			}
		});
		pnlStudentInfoForm1.add(pfStudentSSN2);
		pfStudentSSN2.setColumns(7);
		
		pnlStudentInfoForm2 = new JPanel();
		pnlStudentInfo.add(pnlStudentInfoForm2);

		lblStudentAddr = new JLabel("주소");
		pnlStudentInfoForm2.add(lblStudentAddr);

		tfStudentAddr = new JTextField();
		pnlStudentInfoForm2.add(tfStudentAddr);
		tfStudentAddr.setColumns(30);

		lblStudentTel = new JLabel("연락처");
		pnlStudentInfoForm2.add(lblStudentTel);

		tfStudentTel = new JTextField();
		pnlStudentInfoForm2.add(tfStudentTel);
		tfStudentTel.setColumns(12);

		pnlStudentInfoBtn = new JPanel();
		pnlStudentInfo.add(pnlStudentInfoBtn);

		btnStudentAdd = new JButton("학생등록");
		btnStudentAdd.addActionListener(controller);
	
	
		pnlStudentInfoBtn.add(btnStudentAdd);

		JButton btnStudentDel = new JButton("학생삭제");
		btnStudentDel.addActionListener(controller);
		pnlStudentInfoBtn.add(btnStudentDel);
		
		btnStudentClear = new JButton("입력초기화");
		btnStudentClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 입력내용을 지움
				tfStudentNo.setText("");
				tfStudentName.setText("");
				tfStudentSSN1.setText("");
				pfStudentSSN2.setText("");
				tfStudentAddr.setText("");
				tfStudentTel.setText("");
				// 첫 텍스트필드로 커서 이동
				tfStudentNo.requestFocus();
			}
		});
		pnlStudentInfoBtn.add(btnStudentClear);

		JButton btnStudentSrch = new JButton("학생검색");
		pnlStudentInfoBtn.add(btnStudentSrch);
		btnStudentSrch.addActionListener(controller);

		JButton btnStudentUpdate = new JButton("학생수정");
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
