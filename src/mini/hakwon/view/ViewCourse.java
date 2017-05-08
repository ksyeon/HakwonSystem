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
	 * ��������
	 * 
	 * @param pnlCourseInfo
	 *            ���������� �Է¾���� ��ġ�ϴ� �г�
	 * @param pnlCourseInfoForm1
	 *            �������� �Է¾�� ù��°�� �г�
	 * @param pnlCourseInfoForm2
	 *            �������� �Է¾�� �ι�°�� �г�
	 * @param pnlCourseInfoForm3
	 *            �������� �Է¾�� ����°�� �г�
	 * @param pnlCourseInfoForm4
	 *            �������� �Է¾�� �׹�°�� �г�
	 * @param pnlCourseInfoBtn
	 *            �������� �Է¾�� ��ư �г�
	 * @param lblCourseNo
	 *            ������ȣ ���̺�
	 * @param tfCourseNo
	 *            ������ȣ �ؽ�Ʈ�ʵ�
	 * @param lblCourseName
	 *            ������ ���̺�
	 * @param tfCourseName
	 *            ������ �ؽ�Ʈ�ʵ�
	 * @param lblCourseInstName
	 *            ����� ���̺�
	 * @param cbCourseInstName
	 *            ����� �޺��ڽ�
	 * @param lblCourseRoomNo
	 *            ���ǽ� ���̺�
	 * @param cbCourseRoomNo
	 *            ���ǽ� �޺��ڽ�
	 * @param lblCourseCharge
	 *            ������ ���̺�
	 * @param tfCourseCharge
	 *            ������ �ؽ�Ʈ�ʵ�
	 * @param lblCourseRStartDate
	 *            ��Ͻ����� ���̺�
	 * @param tfCourseRStartDate
	 *            ��Ͻ����� �ؽ�Ʈ�ʵ�
	 * @param lblCourseREndDate
	 *            ��ϸ����� ���̺�
	 * @param tfCourseREndDate
	 *            ��ϸ����� �ؽ�Ʈ�ʵ�
	 * @param lblCourseStartDate
	 *            ������ ���̺�
	 * @param tfCourseStartDate
	 *            ������ �ؽ�Ʈ�ʵ�
	 * @param lblCourseTextNo
	 *            ����� ���̺�
	 * @param listCourseText
	 *            ����� ����Ʈ(�ӽ�)
	 * @param cbCourseText
	 *            ����� �޺��ڽ�
	 * @param btnCourseAdd
	 *            ������� ��ư
	 * @param btnCourseDel
	 *            �������� ��ư
	 * @param btnCourseSearch
	 *            �����˻� ��ư
	 * @param btnCourseClear
	 *            �Է� �ʱ�ȭ ��ư
	 * @param spCourse
	 *            �������� ���̺��� ���� ��ũ���г�
	 * @param mdlCourse
	 *            �������� ���̺��
	 * @param tblCourse
	 *            �������� ���̺�
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

	Curri cr; // Curri ��ü ����
	int cr_id;
	String cr_name;
	int t_id;
	int cls_id;
	int cr_fee;
	String bk_name;
	Query q = new Query();
	DbUtil db = new DbUtil();

	/**
	 * ������ ����
	 */
	public ViewCourse() {
		/**
		 * @param action
		 *            �׼� �̺�Ʈ �ν��Ͻ�
		 * @param key
		 *            Ű �̺�Ʈ �ν��Ͻ�
		 */

		ControllerAction action = new ControllerAction();

		setLayout(new BorderLayout(0, 0));

		pnlCourseInfo = new JPanel();
		add(pnlCourseInfo, BorderLayout.NORTH);
		pnlCourseInfo.setLayout(new BoxLayout(pnlCourseInfo, 1));

		pnlCourseInfoForm1 = new JPanel();
		pnlCourseInfo.add(pnlCourseInfoForm1);

		lblCourseNo = new JLabel("������ȣ");
		pnlCourseInfoForm1.add(lblCourseNo);

		tfCourseNo = new JTextField();
		pnlCourseInfoForm1.add(tfCourseNo);
		tfCourseNo.setColumns(8);

		lblCourseName = new JLabel("������");
		pnlCourseInfoForm1.add(lblCourseName);

		tfCourseName = new JTextField();
		pnlCourseInfoForm1.add(tfCourseName);
		tfCourseName.setColumns(32);

		pnlCourseInfoForm2 = new JPanel();
		pnlCourseInfo.add(pnlCourseInfoForm2);

		lblCourseInstName = new JLabel("�����");
		pnlCourseInfoForm2.add(lblCourseInstName);
		
		/* �����̸�(��ȣ) �������� */
		try {
			dfCombo = new DefaultComboBoxModel();
			cbCourseInstName = new JComboBox(q.getTId(dfCombo));
			pnlCourseInfoForm2.add(cbCourseInstName);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		

		JLabel lblCourseRoomNo = new JLabel("���ǽ�");
		pnlCourseInfoForm2.add(lblCourseRoomNo);

		/* ���ǽ� ��ȣ �������� */
		try {
			dfCombo = new DefaultComboBoxModel();
			cbCourseRoomNo = new JComboBox(q.getClsId(dfCombo));
			pnlCourseInfoForm2.add(cbCourseRoomNo);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		JLabel lblCourseCharge = new JLabel("������");
		pnlCourseInfoForm2.add(lblCourseCharge);

		tfCourseCharge = new JTextField();
		pnlCourseInfoForm2.add(tfCourseCharge);
		tfCourseCharge.setColumns(8);

		pnlCourseInfoForm3 = new JPanel();
		pnlCourseInfo.add(pnlCourseInfoForm3);

		lblCourseRStartDate = new JLabel("��Ͻ�����");
		pnlCourseInfoForm3.add(lblCourseRStartDate);

		tfCourseRStartDate = new JTextField();
		pnlCourseInfoForm3.add(tfCourseRStartDate);
		tfCourseRStartDate.setColumns(8);

		lblCourseREndDate = new JLabel("��ϸ�����");
		pnlCourseInfoForm3.add(lblCourseREndDate);

		tfCourseREndDate = new JTextField();
		pnlCourseInfoForm3.add(tfCourseREndDate);
		tfCourseREndDate.setColumns(8);

		lblCourseStartDate = new JLabel("������");
		pnlCourseInfoForm3.add(lblCourseStartDate);

		tfCourseStartDate = new JTextField();
		pnlCourseInfoForm3.add(tfCourseStartDate);
		tfCourseStartDate.setColumns(8);

		pnlCourseInfoForm4 = new JPanel();
		pnlCourseInfo.add(pnlCourseInfoForm4);

		lblCourseText = new JLabel("�����");
		pnlCourseInfoForm4.add(lblCourseText);
		
		/* ����� �������� */
		try {
			dfCombo = new DefaultComboBoxModel();
			cbCourseText = new JComboBox(q.getBkName(dfCombo));
			pnlCourseInfoForm4.add(cbCourseText);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		pnlCourseInfoBtn = new JPanel();
		pnlCourseInfo.add(pnlCourseInfoBtn);

		btnCourseAdd = new JButton("�����߰�");
		btnCourseAdd.addActionListener(new ControllerAction());
		pnlCourseInfoBtn.add(btnCourseAdd);

		btnCourseDel = new JButton("��������");
		btnCourseDel.addActionListener(new ControllerAction());
		pnlCourseInfoBtn.add(btnCourseDel);

		btnCourseClear = new JButton("�Է��ʱ�ȭ");
		btnCourseClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �Է³����� ����
				tfCourseNo.setText("");
				tfCourseName.setText("");
				tfCourseCharge.setText("");
				tfCourseRStartDate.setText("");
				tfCourseREndDate.setText("");
				tfCourseStartDate.setText("");				
				cbCourseInstName.setSelectedIndex(0);
				cbCourseRoomNo.setSelectedIndex(0);
				cbCourseText.setSelectedIndex(0);
				// ù �ؽ�Ʈ�ʵ�� Ŀ�� �̵�
				tfCourseNo.requestFocus();
			}
		});
		pnlCourseInfoBtn.add(btnCourseClear);

		btnCourseSearch = new JButton("�����˻�");
		btnCourseSearch.addActionListener(new ControllerAction());
		pnlCourseInfoBtn.add(btnCourseSearch);

		btnCourseUpdate = new JButton("��������");
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
