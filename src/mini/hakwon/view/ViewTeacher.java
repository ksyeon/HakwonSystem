package mini.hakwon.view;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import mini.hakwon.util.ControllerAction;
import mini.hakwon.util.ControllerMouse;
import mini.hakwon.util.DbUtil;

//NEW

public class ViewTeacher extends JPanel {

	private JPanel PnlBase;
	private JPanel pnlT_Info;
	
	DbUtil db = new DbUtil();
	
	public static JTable tblTeacher;
	public static JTextField tfT_Id;
	public static JTextField tfT_Name;
	public static JTextField tfT_Phone;
	public static JTextField tf_Sal;
	public static JTextField tf_Addr;
	public static JTextField tfLicenceName;
	public static JTextField tfLicenceNum;
	public static JTextField tf_SSN;
	
	//public static JRadioButton rdbtnFull;
	//public static JRadioButton rdbtnPart;
	
	public static Checkbox rdbtnFull;		//������	
	public static Checkbox rdbtnPart;		//������
	CheckboxGroup g;
	
	public ViewTeacher() {
		ControllerAction CA=new ControllerAction();
		PnlBase = new JPanel();
		add(PnlBase, BorderLayout.NORTH);
		PnlBase.setLayout(new BoxLayout(PnlBase, 1));
		
		JPanel pnl_Infor1 = new JPanel();
		pnl_Infor1.setBackground(Color.LIGHT_GRAY);
		PnlBase.add(pnl_Infor1, BorderLayout.NORTH);
		pnl_Infor1.setLayout(new BorderLayout(0, 0));
		
		JPanel pnl_Infor1_1 = new JPanel();
		pnl_Infor1.add(pnl_Infor1_1, BorderLayout.NORTH);
		pnl_Infor1_1.setBackground(Color.WHITE);
			
			JLabel lblContract = new JLabel("���ӿ���");
			pnl_Infor1_1.add(lblContract);
		
			g = new CheckboxGroup();
			
			//rdbtnFull = new JRadioButton("Y");
			rdbtnFull = new Checkbox("Full",g,false);
			pnl_Infor1_1.add(rdbtnFull);
			rdbtnFull.addItemListener(CA);
			
			//rdbtnPart = new JRadioButton("N");
			rdbtnPart = new Checkbox("Part",g,false);
			pnl_Infor1_1.add(rdbtnPart);
			rdbtnPart.addItemListener(CA);
			
			JLabel lblT_Id = new JLabel("�����ȣ");
			pnl_Infor1_1.add(lblT_Id);
			
			tfT_Id = new JTextField();
			pnl_Infor1_1.add(tfT_Id);
			tfT_Id.setColumns(4);
			
			JLabel lblT_Name = new JLabel("�����");
			pnl_Infor1_1.add(lblT_Name);
			
			tfT_Name = new JTextField();
			pnl_Infor1_1.add(tfT_Name);
			tfT_Name.setColumns(4);
					
			JLabel lblNewLabel_3 = new JLabel("�ñ�");
			pnl_Infor1_1.add(lblNewLabel_3);
			
			tf_Sal = new JTextField();
			pnl_Infor1_1.add(tf_Sal);
			tf_Sal.setColumns(4);
				
					JPanel pnl_Infor1_2 = new JPanel();
					pnl_Infor1.add(pnl_Infor1_2, BorderLayout.CENTER);
					pnl_Infor1_2.setBackground(Color.WHITE);
					
						JLabel lblNewLabel_4 = new JLabel("�ּ�");
						pnl_Infor1_2.add(lblNewLabel_4);
						
						tf_Addr = new JTextField();
						pnl_Infor1_2.add(tf_Addr);
						tf_Addr.setColumns(20);
						
							JLabel lblEmp_Num = new JLabel("����ó");
							pnl_Infor1_2.add(lblEmp_Num);
							
							tfT_Phone = new JTextField();
							pnl_Infor1_2.add(tfT_Phone);
							tfT_Phone.setColumns(11);
						
				JPanel pnl_Infor1_3 = new JPanel();
				pnl_Infor1.add(pnl_Infor1_3, BorderLayout.SOUTH);
				pnl_Infor1_3.setBackground(Color.LIGHT_GRAY);
				pnl_Infor1_3.setLayout(new BorderLayout(0, 0));
				
				JPanel pnl_Licence = new JPanel();
				pnl_Licence.setBackground(Color.WHITE);
				pnl_Infor1_3.add(pnl_Licence, BorderLayout.NORTH);
				
					JLabel lblLicenceNum = new JLabel("�ڰ��� �̸�");
					pnl_Licence.add(lblLicenceNum);
					
					tfLicenceName = new JTextField();
					pnl_Licence.add(tfLicenceName);
					tfLicenceName.setColumns(10);
					
					JLabel lblLicenceName = new JLabel("�ڰ��� ��ȣ");
					pnl_Licence.add(lblLicenceName);
					
					tfLicenceNum = new JTextField();
					pnl_Licence.add(tfLicenceNum);
					tfLicenceNum.setColumns(10);
					
					JPanel pnl_SSN = new JPanel();
					pnl_Infor1_3.add(pnl_SSN, BorderLayout.CENTER);
					
					JLabel lblSSN = new JLabel("�ֹι�ȣ");
					pnl_SSN.add(lblSSN);
					
					tf_SSN = new JTextField();
					pnl_SSN.add(tf_SSN);
					tf_SSN.setColumns(14);
						
		/* ��ư, �׼Ǹ�����.*/
		//�׼Ǹ����ʿ� ���� �޼���� ControllerAction���� �Ѵ�.
		JPanel pnl_Infor2 = new JPanel();
		pnl_Infor2.setBackground(Color.LIGHT_GRAY);
		PnlBase.add(pnl_Infor2, BorderLayout.CENTER);
			pnl_Infor2.setLayout(new BorderLayout(0, 0));
				
				JPanel pnl_Infor2_1 = new JPanel();
				pnl_Infor2.add(pnl_Infor2_1, BorderLayout.NORTH);
				pnl_Infor2_1.setBackground(Color.WHITE);
				
				JButton btnAdd = new JButton("�����߰�");
				btnAdd.addActionListener(CA);
				pnl_Infor2_1.add(btnAdd);
				
				JButton btnDel = new JButton("�������");
				btnDel.addActionListener(CA);
				pnl_Infor2_1.add(btnDel);
				
				JButton btnSearch = new JButton("����˻�");
				btnSearch.addActionListener(CA);
				pnl_Infor2_1.add(btnSearch);
				
				JButton btnClear = new JButton("�Է��ʱ�ȭ");
				btnClear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// �Է³����� ����
						tfT_Id.setText("");
						tfT_Name.setText("");
						tf_Sal.setText("");
						tf_Addr.setText("");
						tfT_Phone.setText("");
						tfLicenceName.setText("");
						tfLicenceNum.setText("");
						tf_SSN.setText("");
						//rdbtnFull.setSelected(false);
						//rdbtnPart.setSelected(false);
						// ù �ؽ�Ʈ�ʵ�� Ŀ�� �̵�
						tfT_Id.requestFocus();
					}
				});
				pnl_Infor2_1.add(btnClear);
				
				JButton btnUpdate = new JButton("�������");
				btnUpdate.addActionListener(CA);
				pnl_Infor2_1.add(btnUpdate);
				
				JPanel pnl_Infor2_2 = new JPanel();
				pnl_Infor2.add(pnl_Infor2_2, BorderLayout.CENTER);
				pnl_Infor2_2.setLayout(new BorderLayout(0, 0));
					
				tblTeacher = new JTable(db.getTable("teacher"));
				tblTeacher.setBackground(Color.WHITE);
				tblTeacher.addMouseListener(new ControllerMouse(tblTeacher, "teacher"));
				JScrollPane scrollPane = new JScrollPane(tblTeacher);
				pnl_Infor2_2.add(scrollPane, BorderLayout.SOUTH);
	}
}
