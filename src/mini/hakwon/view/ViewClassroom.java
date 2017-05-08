package mini.hakwon.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import mini.hakwon.util.ControllerAction;
import mini.hakwon.util.ControllerMouse;
//import mini.hakwon.util.ControllerMouse;
import mini.hakwon.util.DbUtil;
import mini.hakwon.util.Query;

public class ViewClassroom extends JPanel {
	
	/**
	 * ���ǽ� ���� ����
	 */
	private JPanel pnlClassroomInfo, pnlClassroomInfoForm1, pnlClassroomInfoBtn;
	private JLabel lblClassroomNo, lblClassroomDesk, lblClassroomProjector, lblClassroomManager;
	public static JTextField tfClassroomNo, tfClassroomDesk, tfClassroomProjector;
	public static JComboBox cbClassroomManager;
	public JComboBox<String> cbCourseName, cbSortOption;
	private JButton btnClassroomAdd, btnClassroomDelete, btnClassroomModify, btnClassroomSearch, btnClassroomClear;
	public DefaultTableModel mdlClassroom;
	public DefaultComboBoxModel dfCombo;
	public static JTable tblClassroom;
	private JScrollPane spClassroom;

	static ControllerAction action = new ControllerAction();
	static DbUtil db = new DbUtil();
	static Query q = new Query();
	
	ViewClassroom() {		
		setLayout(new BorderLayout(0, 0));

		pnlClassroomInfo = new JPanel();
		add(pnlClassroomInfo, BorderLayout.NORTH);
		pnlClassroomInfo.setLayout(new BoxLayout(pnlClassroomInfo, 1));

		pnlClassroomInfoForm1 = new JPanel();
		pnlClassroomInfo.add(pnlClassroomInfoForm1);
		
		lblClassroomNo = new JLabel("���ǽǹ�ȣ");
		pnlClassroomInfoForm1.add(lblClassroomNo);

		tfClassroomNo = new JTextField();
		pnlClassroomInfoForm1.add(tfClassroomNo);
		tfClassroomNo.setColumns(6);

		lblClassroomDesk = new JLabel("å���");
		pnlClassroomInfoForm1.add(lblClassroomDesk);

		tfClassroomDesk = new JTextField();
		pnlClassroomInfoForm1.add(tfClassroomDesk);
		tfClassroomDesk.setColumns(6);

		lblClassroomProjector = new JLabel("�������ͼ�");
		pnlClassroomInfoForm1.add(lblClassroomProjector);

		tfClassroomProjector = new JTextField();
		pnlClassroomInfoForm1.add(tfClassroomProjector);
		tfClassroomProjector.setColumns(6);
		
		lblClassroomManager = new JLabel("�������� ��ȣ");
		pnlClassroomInfoForm1.add(lblClassroomManager);

		/* ���� ��ȣ �������� */	
		try {
			dfCombo = new DefaultComboBoxModel();
			cbClassroomManager = new JComboBox(q.getEmpId(dfCombo));
			pnlClassroomInfoForm1.add(cbClassroomManager);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		pnlClassroomInfoBtn = new JPanel();
		pnlClassroomInfo.add(pnlClassroomInfoBtn);

		btnClassroomAdd = new JButton("���ǽ��߰�");
		btnClassroomAdd.addActionListener(new ControllerAction());
		pnlClassroomInfoBtn.add(btnClassroomAdd);

		btnClassroomDelete = new JButton("���ǽǻ���");
		btnClassroomDelete.addActionListener(new ControllerAction());
		pnlClassroomInfoBtn.add(btnClassroomDelete);

		btnClassroomModify = new JButton("���ǽǼ���");
		btnClassroomModify.addActionListener(new ControllerAction());
		pnlClassroomInfoBtn.add(btnClassroomModify);
		
		btnClassroomSearch = new JButton("���ǽǰ˻�");
		btnClassroomSearch.addActionListener(new ControllerAction());
		pnlClassroomInfoBtn.add(btnClassroomSearch);

		
		btnClassroomClear = new JButton("�Է��ʱ�ȭ");
		btnClassroomClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �Է³����� ����
				tfClassroomNo.setText("");
				tfClassroomDesk.setText("");
				tfClassroomProjector.setText("");
				cbClassroomManager.setSelectedIndex(0);
				// ù �ؽ�Ʈ�ʵ�� Ŀ�� �̵�
				tfClassroomNo.requestFocus();
			}
		});
		pnlClassroomInfoBtn.add(btnClassroomClear);
		
		mdlClassroom = db.getTable("classroom");
		tblClassroom = new JTable(mdlClassroom);
		
		// table���� �� ���ý� textField�� ���� ������
		tblClassroom.addMouseListener(new ControllerMouse(tblClassroom, "classroom"));
		
		tblClassroom.setCellSelectionEnabled(true);
		tblClassroom.setColumnSelectionAllowed(false);
		tblClassroom.setDragEnabled(false);
		spClassroom = new JScrollPane(tblClassroom);
		spClassroom.setViewportView(tblClassroom);
		add(spClassroom, BorderLayout.CENTER);
		
	}
	
}
