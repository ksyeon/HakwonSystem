package mini.hakwon.view;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import mini.hakwon.util.ControllerAction;
import mini.hakwon.util.DbUtil;
import mini.hakwon.util.Query;

public class ViewApply extends JPanel {

	/**
	 * 수강등록 정보
	 * 
	 * @param pnlApplyInfo
	 */

	JPanel pnlApplyInfo, pnlApplyInfoForm1, pnlApplyInfoBtn;
	JLabel lblStudentName, lblCourseName;
	public static JComboBox<String> cbStudentName, cbCourseName;
	JButton btnApplyAdd, btnApplyDelete, btnApplyModify, btnApplyConfirm, btnApplyClear;
	public static JTable tblApply;
	JScrollPane spApply;

	ControllerAction action = new ControllerAction();
	DbUtil db = new DbUtil();

	public ViewApply() {

		Query query = new Query();

		setLayout(new BorderLayout(0, 0));

		pnlApplyInfo = new JPanel();
		add(pnlApplyInfo, BorderLayout.NORTH);
		pnlApplyInfo.setLayout(new BoxLayout(pnlApplyInfo, 1));

		pnlApplyInfoForm1 = new JPanel();
		pnlApplyInfo.add(pnlApplyInfoForm1);

		lblStudentName = new JLabel("학생명");
		pnlApplyInfoForm1.add(lblStudentName);

		cbStudentName = new JComboBox<String>(db.getList("student"));
		pnlApplyInfoForm1.add(cbStudentName);

		lblCourseName = new JLabel("과정명");
		pnlApplyInfoForm1.add(lblCourseName);

		cbCourseName = new JComboBox<String>(db.getList("course"));
		pnlApplyInfoForm1.add(cbCourseName);

		pnlApplyInfoBtn = new JPanel();
		pnlApplyInfo.add(pnlApplyInfoBtn);

		btnApplyAdd = new JButton("수강등록");
		btnApplyAdd.addActionListener(action);
		pnlApplyInfoBtn.add(btnApplyAdd);

		btnApplyDelete = new JButton("수강철회");
		btnApplyDelete.addActionListener(action);
		pnlApplyInfoBtn.add(btnApplyDelete);

		btnApplyModify = new JButton("수강변경");
		btnApplyModify.addActionListener(action);
		pnlApplyInfoBtn.add(btnApplyModify);
		
		btnApplyConfirm = new JButton("납부확인");
		btnApplyConfirm.addActionListener(action);
		pnlApplyInfoBtn.add(btnApplyConfirm);

		btnApplyClear = new JButton("입력초기화");
		btnApplyClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 콤보박스 첫 아이템을 선택
				cbStudentName.setSelectedIndex(0);
				cbCourseName.setSelectedIndex(0);
			}
		});
		pnlApplyInfoBtn.add(btnApplyClear);

		tblApply = new JTable(db.getTable("apply"));
		tblApply.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		spApply = new JScrollPane(tblApply);
		add(spApply, BorderLayout.CENTER);
	}

}
