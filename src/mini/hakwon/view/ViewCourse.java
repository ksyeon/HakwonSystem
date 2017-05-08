package mini.hakwon.view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mini.hakwon.model.Curri;
import mini.hakwon.util.ControllerAction;
import mini.hakwon.util.ControllerMouse;
import mini.hakwon.util.DbUtil;
import mini.hakwon.util.Query;

import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

public class ViewCourse extends JPanel {

	/**
	 * 과정정보
	 * 
	 * @param pnlCourseInfo
	 *            과정정보의 입력양식이 위치하는 패널
	 * @param pnlCourseInfoForm1
	 *            과정정보 입력양식 첫번째줄 패널
	 * @param pnlCourseInfoForm2
	 *            과정정보 입력양식 두번째줄 패널
	 * @param pnlCourseInfoForm3
	 *            과정정보 입력양식 세번째줄 패널
	 * @param pnlCourseInfoForm4
	 *            과정정보 입력양식 네번째줄 패널
	 * @param pnlCourseInfoBtn
	 *            과정정보 입력양식 버튼 패널
	 * @param lblCourseNo
	 *            과정번호 레이블
	 * @param tfCourseNo
	 *            과정번호 텍스트필드
	 * @param lblCourseName
	 *            과정명 레이블
	 * @param tfCourseName
	 *            과정명 텍스트필드
	 * @param lblCourseInstName
	 *            강사명 레이블
	 * @param cbCourseInstName
	 *            강사명 콤보박스
	 * @param lblCourseRoomNo
	 *            강의실 레이블
	 * @param cbCourseRoomNo
	 *            강의실 콤보박스
	 * @param lblCourseCharge
	 *            수업료 레이블
	 * @param tfCourseCharge
	 *            수업료 텍스트필드
	 * @param lblCourseRStartDate
	 *            등록시작일 레이블
	 * @param tfCourseRStartDate
	 *            등록시작일 텍스트필드
	 * @param lblCourseREndDate
	 *            등록마감일 레이블
	 * @param tfCourseREndDate
	 *            등록마감일 텍스트필드
	 * @param lblCourseStartDate
	 *            개강일 레이블
	 * @param tfCourseStartDate
	 *            개강일 텍스트필드
	 * @param lblCourseTextNo
	 *            교재명 레이블
	 * @param listCourseText
	 *            교재명 리스트(임시)
	 * @param cbCourseText
	 *            교재명 콤보박스
	 * @param btnCourseAdd
	 *            과정등록 버튼
	 * @param btnCourseDel
	 *            과정삭제 버튼
	 * @param btnCourseSearch
	 *            과정검색 버튼
	 * @param btnCourseClear
	 *            입력 초기화 버튼
	 * @param spCourse
	 *            과정정보 테이블을 위한 스크롤패널
	 * @param mdlCourse
	 *            과정정보 테이블모델
	 * @param tblCourse
	 *            과정정보 테이블
	 */
	private JPanel pnlCourseInfo, pnlCourseInfoForm1, pnlCourseInfoForm2, pnlCourseInfoForm3, pnlCourseInfoForm4,
			pnlCourseInfoBtn;
	private JLabel lblCourseNo, lblCourseName, lblCourseInstName, lblCourseRoomNo, lblCourseCharge, lblCourseRStartDate,
			lblCourseREndDate, lblCourseStartDate, lblCourseText;
	public static JTextField tfCourseNo, tfCourseName, tfCourseCharge, tfCourseRStartDate, tfCourseREndDate, tfCourseStartDate;
	public static JComboBox cbCourseInstName, cbCourseRoomNo, cbCourseText;
	private JList listCourseText;
	private JButton btnCourseUpdate, btnCourseAdd, btnCourseDel, btnCourseSearch, btnCourseClear;
	private JScrollPane spCourse;
	private DefaultTableModel mdlCourse;
	private DefaultComboBoxModel dfCombo;
	public static JTable tblCourse;

	Curri cr; // Curri 객체 생성
	int cr_id;
	String cr_name;
	int t_id;
	int cls_id;
	int cr_fee;
	String bk_name;
	Query q = new Query();
	DbUtil db = new DbUtil();

	/**
	 * 프레임 구성
	 */
	public ViewCourse() {
		/**
		 * @param action
		 *            액션 이벤트 인스턴스
		 * @param key
		 *            키 이벤트 인스턴스
		 */

		ControllerAction action = new ControllerAction();

		setLayout(new BorderLayout(0, 0));

		pnlCourseInfo = new JPanel();
		add(pnlCourseInfo, BorderLayout.NORTH);
		pnlCourseInfo.setLayout(new BoxLayout(pnlCourseInfo, 1));

		pnlCourseInfoForm1 = new JPanel();
		pnlCourseInfo.add(pnlCourseInfoForm1);

		lblCourseNo = new JLabel("과정번호");
		pnlCourseInfoForm1.add(lblCourseNo);

		tfCourseNo = new JTextField();
		pnlCourseInfoForm1.add(tfCourseNo);
		tfCourseNo.setColumns(8);

		lblCourseName = new JLabel("과정명");
		pnlCourseInfoForm1.add(lblCourseName);

		tfCourseName = new JTextField();
		pnlCourseInfoForm1.add(tfCourseName);
		tfCourseName.setColumns(32);

		pnlCourseInfoForm2 = new JPanel();
		pnlCourseInfo.add(pnlCourseInfoForm2);

		lblCourseInstName = new JLabel("강사명");
		pnlCourseInfoForm2.add(lblCourseInstName);
		
		/* 강사이름(번호) 가져오기 */
		try {
			dfCombo = new DefaultComboBoxModel();
			cbCourseInstName = new JComboBox(q.getTId(dfCombo));
			pnlCourseInfoForm2.add(cbCourseInstName);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		

		JLabel lblCourseRoomNo = new JLabel("강의실");
		pnlCourseInfoForm2.add(lblCourseRoomNo);

		/* 강의실 번호 가져오기 */
		try {
			dfCombo = new DefaultComboBoxModel();
			cbCourseRoomNo = new JComboBox(q.getClsId(dfCombo));
			pnlCourseInfoForm2.add(cbCourseRoomNo);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		JLabel lblCourseCharge = new JLabel("수업료");
		pnlCourseInfoForm2.add(lblCourseCharge);

		tfCourseCharge = new JTextField();
		pnlCourseInfoForm2.add(tfCourseCharge);
		tfCourseCharge.setColumns(8);

		pnlCourseInfoForm3 = new JPanel();
		pnlCourseInfo.add(pnlCourseInfoForm3);

		lblCourseRStartDate = new JLabel("등록시작일");
		pnlCourseInfoForm3.add(lblCourseRStartDate);

		tfCourseRStartDate = new JTextField();
		pnlCourseInfoForm3.add(tfCourseRStartDate);
		tfCourseRStartDate.setColumns(8);

		lblCourseREndDate = new JLabel("등록마감일");
		pnlCourseInfoForm3.add(lblCourseREndDate);

		tfCourseREndDate = new JTextField();
		pnlCourseInfoForm3.add(tfCourseREndDate);
		tfCourseREndDate.setColumns(8);

		lblCourseStartDate = new JLabel("개강일");
		pnlCourseInfoForm3.add(lblCourseStartDate);

		tfCourseStartDate = new JTextField();
		pnlCourseInfoForm3.add(tfCourseStartDate);
		tfCourseStartDate.setColumns(8);

		pnlCourseInfoForm4 = new JPanel();
		pnlCourseInfo.add(pnlCourseInfoForm4);

		lblCourseText = new JLabel("교재명");
		pnlCourseInfoForm4.add(lblCourseText);
		
		/* 교재명 가져오기 */
		try {
			dfCombo = new DefaultComboBoxModel();
			cbCourseText = new JComboBox(q.getBkName(dfCombo));
			pnlCourseInfoForm4.add(cbCourseText);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		pnlCourseInfoBtn = new JPanel();
		pnlCourseInfo.add(pnlCourseInfoBtn);

		btnCourseAdd = new JButton("과정추가");
		btnCourseAdd.addActionListener(new ControllerAction());
		pnlCourseInfoBtn.add(btnCourseAdd);

		btnCourseDel = new JButton("과정삭제");
		btnCourseDel.addActionListener(new ControllerAction());
		pnlCourseInfoBtn.add(btnCourseDel);

		btnCourseClear = new JButton("입력초기화");
		btnCourseClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 입력내용을 지움
				tfCourseNo.setText("");
				tfCourseName.setText("");
				tfCourseCharge.setText("");
				tfCourseRStartDate.setText("");
				tfCourseREndDate.setText("");
				tfCourseStartDate.setText("");				
				cbCourseInstName.setSelectedIndex(0);
				cbCourseRoomNo.setSelectedIndex(0);
				cbCourseText.setSelectedIndex(0);
				// 첫 텍스트필드로 커서 이동
				tfCourseNo.requestFocus();
			}
		});
		pnlCourseInfoBtn.add(btnCourseClear);

		btnCourseSearch = new JButton("과정검색");
		btnCourseSearch.addActionListener(new ControllerAction());
		pnlCourseInfoBtn.add(btnCourseSearch);

		btnCourseUpdate = new JButton("과정수정");
		btnCourseUpdate.addActionListener(new ControllerAction());
		pnlCourseInfoBtn.add(btnCourseUpdate);
		
		JScrollPane spCourse = new JScrollPane();
		add(spCourse, BorderLayout.CENTER);

		// jTable
		tblCourse = new JTable(db.getTable("curriculum"));
		tblCourse.addMouseListener(new ControllerMouse(tblCourse, "curriculum"));
		spCourse.setViewportView(tblCourse);
	}

	protected JTextField getTfCourseNo() {
		return tfCourseNo;
	}

	protected void setTfCourseNo(JTextField tfCourseNo) {
		this.tfCourseNo = tfCourseNo;
	}

	protected JTextField getTfCourseName() {
		return tfCourseName;
	}

	protected void setTfCourseName(JTextField tfCourseName) {
		this.tfCourseName = tfCourseName;
	}

	protected JComboBox getCbCourseInstName() {
		return cbCourseInstName;
	}

	protected void setCbCourseInstName(JComboBox getCbCourseInstName) {
		this.cbCourseInstName = cbCourseInstName;
	}

	protected JComboBox getCbCourseRoomNo() {
		return cbCourseRoomNo;
	}

	protected void setTfCourseRoomNo(JComboBox cbCourseRoomNo) {
		this.cbCourseRoomNo = cbCourseRoomNo;
	}

	protected JTextField getTfCourseCharge() {
		return tfCourseCharge;
	}

	protected void setTfCourseCharge(JTextField tfCourseCharge) {
		this.tfCourseCharge = tfCourseCharge;
	}

	protected JComboBox getCbCourseText() {
		return cbCourseText;
	}

	protected void setCbCourseText(JComboBox cbCourseText) {
		this.cbCourseText = cbCourseText;
	}

}
