package mini.hakwon.view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mini.hakwon.model.Book;
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
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class ViewText extends JPanel {

	/**
	 * 교재정보
	 * 
	 * @param pnlTextInfo
	 *            교재정보의 입력양식이 위치하는 패널
	 * @param pnlTextInfoForm1
	 *            교재정보 입력양식 첫번째줄 패널
	 * @param pnlTextInfoForm2
	 *            교재정보 입력양식 두번째줄 패널
	 * @param pnlTextInfoBtn
	 *            교재정보 입력양식 버튼 패널
	 * @param lblTextTitle
	 *            교재제목 레이블
	 * @param tfTextTitle
	 *            교재제목 텍스트필드
	 * @param lblTextPublisher
	 *            출판사명 레이블
	 * @param tfTextPublisher
	 *            출판사명 텍스트필드
	 * @param lblTextPrice
	 *            교재가격 레이블
	 * @param tfTextPrice
	 *            교재가격 텍스트필드
	 * @param btnTextAdd
	 *            교재등록 버튼
	 * @param btnTextDel
	 *            교재삭제 버튼
	 * @param btnTextSearch
	 *            교재검색 버튼
	 * @param btnTextOrder
	 *            교재주문서 저장 버튼
	 * @param btnTextClear
	 *            입력 초기화 버튼
	 * @param spText
	 *            교재정보 테이블을 위한 스크롤패널
	 * @param mdlText
	 *            교재정보 테이블모델
	 * @param tblText
	 *            교재정보 테이블
	 */
	private JPanel pnlTextInfo, pnlTextInfoForm1, pnlTextInfoForm2, pnlTextInfoBtn;
	private JLabel lblTextTitle, lblTextPublisher, lblTextPrice;
	public static JTextField tfTextTitle, tfTextPublisher, tfTextPrice;
	private JButton btnTextUpdate, btnTextAdd, btnTextDel, btnTextSearch, btnTextClear, btnTextOrder;
	private JScrollPane spText;
	private DefaultTableModel mdlText;
	public static JTable tblText;

	Book book;
	String bk_name;
	String bk_pub;
	int bk_fee;
	DbUtil db = new DbUtil();
	public ViewText() {
		/**
		 * @param action
		 *            액션 이벤트 인스턴스
		 * @param key
		 *            키 이벤트 인스턴스
		 * @param item
		 *            아이템 이벤트 인스턴스
		 */
		ControllerAction action = new ControllerAction();

		setLayout(new BorderLayout(0, 0));

		pnlTextInfo = new JPanel();
		add(pnlTextInfo, BorderLayout.NORTH);
		pnlTextInfo.setLayout(new BoxLayout(pnlTextInfo, 1));

		pnlTextInfoForm1 = new JPanel();
		pnlTextInfo.add(pnlTextInfoForm1);

		lblTextTitle = new JLabel("교재제목");
		pnlTextInfoForm1.add(lblTextTitle);

		tfTextTitle = new JTextField();
		pnlTextInfoForm1.add(tfTextTitle);
		tfTextTitle.setColumns(42);

		pnlTextInfoForm2 = new JPanel();
		pnlTextInfo.add(pnlTextInfoForm2);

		lblTextPublisher = new JLabel("출판사명");
		pnlTextInfoForm2.add(lblTextPublisher);

		tfTextPublisher = new JTextField();
		pnlTextInfoForm2.add(tfTextPublisher);
		tfTextPublisher.setColumns(27);

		lblTextPrice = new JLabel("교재가격");
		pnlTextInfoForm2.add(lblTextPrice);

		tfTextPrice = new JTextField();
		pnlTextInfoForm2.add(tfTextPrice);
		tfTextPrice.setColumns(8);

		pnlTextInfoBtn = new JPanel();
		pnlTextInfo.add(pnlTextInfoBtn);

		btnTextAdd = new JButton("교재추가");
		btnTextAdd.addActionListener(new ControllerAction());
		pnlTextInfoBtn.add(btnTextAdd);

		btnTextDel = new JButton("교재삭제");
		btnTextDel.addActionListener(new ControllerAction());
		pnlTextInfoBtn.add(btnTextDel);

		btnTextClear = new JButton("입력초기화");
		btnTextClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 입력내용을 지움
				tfTextTitle.setText("");
				tfTextPublisher.setText("");
				tfTextPrice.setText("");
				// 첫 텍스트필드로 커서 이동
				tfTextTitle.requestFocus();
			}
		});
		pnlTextInfoBtn.add(btnTextClear);

		btnTextSearch = new JButton("교재검색");
		btnTextSearch.addActionListener(new ControllerAction());
		pnlTextInfoBtn.add(btnTextSearch);

		btnTextUpdate = new JButton("교재수정");
		btnTextUpdate.addActionListener(action);
		pnlTextInfoBtn.add(btnTextUpdate);
		
		spText = new JScrollPane();
		add(spText, BorderLayout.CENTER);
		tblText = new JTable(db.getTable("book"));
		tblText.addMouseListener(new ControllerMouse(tblText, "book"));
		spText.setViewportView(tblText);

	}

	protected JTextField getTfTextTitle() {
		return tfTextTitle;
	}

	protected void setTfTextTitle(JTextField tfTextTitle) {
		this.tfTextTitle = tfTextTitle;
	}

	protected JTextField getTfTextPublisher() {
		return tfTextPublisher;
	}

	protected void setTfTextPublisher(JTextField tfTextPublisher) {
		this.tfTextPublisher = tfTextPublisher;
	}

	protected JTextField getTfTextPrice() {
		return tfTextPrice;
	}

	protected void setTfTextPrice(JTextField tfTextPrice) {
		this.tfTextPrice = tfTextPrice;
	}

}
