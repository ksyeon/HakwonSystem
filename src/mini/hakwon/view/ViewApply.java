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
	 * ������� ����
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

		lblStudentName = new JLabel("�л���");
		pnlApplyInfoForm1.add(lblStudentName);

		cbStudentName = new JComboBox<String>(db.getList("student"));
		pnlApplyInfoForm1.add(cbStudentName);

		lblCourseName = new JLabel("������");
		pnlApplyInfoForm1.add(lblCourseName);

		cbCourseName = new JComboBox<String>(db.getList("course"));
		pnlApplyInfoForm1.add(cbCourseName);

		pnlApplyInfoBtn = new JPanel();
		pnlApplyInfo.add(pnlApplyInfoBtn);

		btnApplyAdd = new JButton("�������");
		btnApplyAdd.addActionListener(action);
		pnlApplyInfoBtn.add(btnApplyAdd);

		btnApplyDelete = new JButton("����öȸ");
		btnApplyDelete.addActionListener(action);
		pnlApplyInfoBtn.add(btnApplyDelete);

		btnApplyModify = new JButton("��������");
		btnApplyModify.addActionListener(action);
		pnlApplyInfoBtn.add(btnApplyModify);
		
		btnApplyConfirm = new JButton("����Ȯ��");
		btnApplyConfirm.addActionListener(action);
		pnlApplyInfoBtn.add(btnApplyConfirm);

		btnApplyClear = new JButton("�Է��ʱ�ȭ");
		btnApplyClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �޺��ڽ� ù �������� ����
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
