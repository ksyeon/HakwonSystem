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
	 * ��������
	 * 
	 * @param pnlTextInfo
	 *            ���������� �Է¾���� ��ġ�ϴ� �г�
	 * @param pnlTextInfoForm1
	 *            �������� �Է¾�� ù��°�� �г�
	 * @param pnlTextInfoForm2
	 *            �������� �Է¾�� �ι�°�� �г�
	 * @param pnlTextInfoBtn
	 *            �������� �Է¾�� ��ư �г�
	 * @param lblTextTitle
	 *            �������� ���̺�
	 * @param tfTextTitle
	 *            �������� �ؽ�Ʈ�ʵ�
	 * @param lblTextPublisher
	 *            ���ǻ�� ���̺�
	 * @param tfTextPublisher
	 *            ���ǻ�� �ؽ�Ʈ�ʵ�
	 * @param lblTextPrice
	 *            ���簡�� ���̺�
	 * @param tfTextPrice
	 *            ���簡�� �ؽ�Ʈ�ʵ�
	 * @param btnTextAdd
	 *            ������ ��ư
	 * @param btnTextDel
	 *            ������� ��ư
	 * @param btnTextSearch
	 *            ����˻� ��ư
	 * @param btnTextOrder
	 *            �����ֹ��� ���� ��ư
	 * @param btnTextClear
	 *            �Է� �ʱ�ȭ ��ư
	 * @param spText
	 *            �������� ���̺��� ���� ��ũ���г�
	 * @param mdlText
	 *            �������� ���̺��
	 * @param tblText
	 *            �������� ���̺�
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
		 *            �׼� �̺�Ʈ �ν��Ͻ�
		 * @param key
		 *            Ű �̺�Ʈ �ν��Ͻ�
		 * @param item
		 *            ������ �̺�Ʈ �ν��Ͻ�
		 */
		ControllerAction action = new ControllerAction();

		setLayout(new BorderLayout(0, 0));

		pnlTextInfo = new JPanel();
		add(pnlTextInfo, BorderLayout.NORTH);
		pnlTextInfo.setLayout(new BoxLayout(pnlTextInfo, 1));

		pnlTextInfoForm1 = new JPanel();
		pnlTextInfo.add(pnlTextInfoForm1);

		lblTextTitle = new JLabel("��������");
		pnlTextInfoForm1.add(lblTextTitle);

		tfTextTitle = new JTextField();
		pnlTextInfoForm1.add(tfTextTitle);
		tfTextTitle.setColumns(42);

		pnlTextInfoForm2 = new JPanel();
		pnlTextInfo.add(pnlTextInfoForm2);

		lblTextPublisher = new JLabel("���ǻ��");
		pnlTextInfoForm2.add(lblTextPublisher);

		tfTextPublisher = new JTextField();
		pnlTextInfoForm2.add(tfTextPublisher);
		tfTextPublisher.setColumns(27);

		lblTextPrice = new JLabel("���簡��");
		pnlTextInfoForm2.add(lblTextPrice);

		tfTextPrice = new JTextField();
		pnlTextInfoForm2.add(tfTextPrice);
		tfTextPrice.setColumns(8);

		pnlTextInfoBtn = new JPanel();
		pnlTextInfo.add(pnlTextInfoBtn);

		btnTextAdd = new JButton("�����߰�");
		btnTextAdd.addActionListener(new ControllerAction());
		pnlTextInfoBtn.add(btnTextAdd);

		btnTextDel = new JButton("�������");
		btnTextDel.addActionListener(new ControllerAction());
		pnlTextInfoBtn.add(btnTextDel);

		btnTextClear = new JButton("�Է��ʱ�ȭ");
		btnTextClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �Է³����� ����
				tfTextTitle.setText("");
				tfTextPublisher.setText("");
				tfTextPrice.setText("");
				// ù �ؽ�Ʈ�ʵ�� Ŀ�� �̵�
				tfTextTitle.requestFocus();
			}
		});
		pnlTextInfoBtn.add(btnTextClear);

		btnTextSearch = new JButton("����˻�");
		btnTextSearch.addActionListener(new ControllerAction());
		pnlTextInfoBtn.add(btnTextSearch);

		btnTextUpdate = new JButton("�������");
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
