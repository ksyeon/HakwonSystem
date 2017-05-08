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
	
	public static Checkbox rdbtnFull;		//정직원	
	public static Checkbox rdbtnPart;		//비정규
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
			
			JLabel lblContract = new JLabel("전속여부");
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
			
			JLabel lblT_Id = new JLabel("강사번호");
			pnl_Infor1_1.add(lblT_Id);
			
			tfT_Id = new JTextField();
			pnl_Infor1_1.add(tfT_Id);
			tfT_Id.setColumns(4);
			
			JLabel lblT_Name = new JLabel("강사명");
			pnl_Infor1_1.add(lblT_Name);
			
			tfT_Name = new JTextField();
			pnl_Infor1_1.add(tfT_Name);
			tfT_Name.setColumns(4);
					
			JLabel lblNewLabel_3 = new JLabel("시급");
			pnl_Infor1_1.add(lblNewLabel_3);
			
			tf_Sal = new JTextField();
			pnl_Infor1_1.add(tf_Sal);
			tf_Sal.setColumns(4);
				
					JPanel pnl_Infor1_2 = new JPanel();
					pnl_Infor1.add(pnl_Infor1_2, BorderLayout.CENTER);
					pnl_Infor1_2.setBackground(Color.WHITE);
					
						JLabel lblNewLabel_4 = new JLabel("주소");
						pnl_Infor1_2.add(lblNewLabel_4);
						
						tf_Addr = new JTextField();
						pnl_Infor1_2.add(tf_Addr);
						tf_Addr.setColumns(20);
						
							JLabel lblEmp_Num = new JLabel("연락처");
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
				
					JLabel lblLicenceNum = new JLabel("자격증 이름");
					pnl_Licence.add(lblLicenceNum);
					
					tfLicenceName = new JTextField();
					pnl_Licence.add(tfLicenceName);
					tfLicenceName.setColumns(10);
					
					JLabel lblLicenceName = new JLabel("자격증 번호");
					pnl_Licence.add(lblLicenceName);
					
					tfLicenceNum = new JTextField();
					pnl_Licence.add(tfLicenceNum);
					tfLicenceNum.setColumns(10);
					
					JPanel pnl_SSN = new JPanel();
					pnl_Infor1_3.add(pnl_SSN, BorderLayout.CENTER);
					
					JLabel lblSSN = new JLabel("주민번호");
					pnl_SSN.add(lblSSN);
					
					tf_SSN = new JTextField();
					pnl_SSN.add(tf_SSN);
					tf_SSN.setColumns(14);
						
		/* 버튼, 액션리스너.*/
		//액션리스너에 대한 메서드는 ControllerAction에서 한다.
		JPanel pnl_Infor2 = new JPanel();
		pnl_Infor2.setBackground(Color.LIGHT_GRAY);
		PnlBase.add(pnl_Infor2, BorderLayout.CENTER);
			pnl_Infor2.setLayout(new BorderLayout(0, 0));
				
				JPanel pnl_Infor2_1 = new JPanel();
				pnl_Infor2.add(pnl_Infor2_1, BorderLayout.NORTH);
				pnl_Infor2_1.setBackground(Color.WHITE);
				
				JButton btnAdd = new JButton("강사추가");
				btnAdd.addActionListener(CA);
				pnl_Infor2_1.add(btnAdd);
				
				JButton btnDel = new JButton("강사삭제");
				btnDel.addActionListener(CA);
				pnl_Infor2_1.add(btnDel);
				
				JButton btnSearch = new JButton("강사검색");
				btnSearch.addActionListener(CA);
				pnl_Infor2_1.add(btnSearch);
				
				JButton btnClear = new JButton("입력초기화");
				btnClear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 입력내용을 지움
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
						// 첫 텍스트필드로 커서 이동
						tfT_Id.requestFocus();
					}
				});
				pnl_Infor2_1.add(btnClear);
				
				JButton btnUpdate = new JButton("강사수정");
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
