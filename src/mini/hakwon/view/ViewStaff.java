package mini.hakwon.view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import mini.hakwon.util.ControllerAction;
import mini.hakwon.util.ControllerMouse;
import mini.hakwon.util.DbUtil;

import javax.swing.BoxLayout;
import javax.swing.JButton;

public class ViewStaff extends JPanel {

	private JPanel PnlBase;
	//private JPanel pnlStaffInfo;
	public static JTable tblStaff;
	public static JTextField tfStaffId, tfStaffName, tfStaffNum,  tfStaffSal, tfStaffAddr, tfStaffPhone;
	
	DbUtil db = new DbUtil();

	public ViewStaff() {
		//베이스 판넬 
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
		
			JLabel lblEmp_Id = new JLabel("직원 번호");
			pnl_Infor1_1.add(lblEmp_Id);
		
			tfStaffId = new JTextField();
			pnl_Infor1_1.add(tfStaffId);
			tfStaffId.setColumns(4);
			
			JLabel lblEmp_Name = new JLabel("직원 이름");
			pnl_Infor1_1.add(lblEmp_Name);
		
			tfStaffName = new JTextField();
			pnl_Infor1_1.add(tfStaffName);
			tfStaffName.setColumns(6);
			
			JLabel lblEmp_Sal = new JLabel("월급");
			pnl_Infor1_1.add(lblEmp_Sal);
				
			tfStaffSal = new JTextField();
			pnl_Infor1_1.add(tfStaffSal);
			tfStaffSal.setColumns(5);
		
			JLabel lblEmp_Num = new JLabel("주민번호");
			pnl_Infor1_1.add(lblEmp_Num);
				
			tfStaffNum = new JTextField();
			pnl_Infor1_1.add(tfStaffNum);
			tfStaffNum.setColumns(13);
			
			JPanel pnl_Infor1_2 = new JPanel();
			pnl_Infor1.add(pnl_Infor1_2, BorderLayout.CENTER);
			pnl_Infor1_2.setBackground(Color.LIGHT_GRAY);
			pnl_Infor1_2.setLayout(new BorderLayout(0, 0));
			
				JPanel pnl_Infor1_2_1 = new JPanel();
				pnl_Infor1_2_1.setBackground(Color.WHITE);
				pnl_Infor1_2.add(pnl_Infor1_2_1, BorderLayout.NORTH);
					
					JLabel lblEmp_Addr = new JLabel("주소");
					pnl_Infor1_2_1.add(lblEmp_Addr);
					
					tfStaffAddr = new JTextField();
					pnl_Infor1_2_1.add(tfStaffAddr);
					tfStaffAddr.setColumns(20);
			
					JLabel lblEmp_Phone = new JLabel("연락처");
					pnl_Infor1_2_1.add(lblEmp_Phone);
				
					tfStaffPhone = new JTextField();
					pnl_Infor1_2_1.add(tfStaffPhone);
					tfStaffPhone.setColumns(11);
				
		JPanel pnl_Infor1_3 = new JPanel();
		pnl_Infor1.add(pnl_Infor1_3, BorderLayout.SOUTH);
		pnl_Infor1_3.setBackground(Color.WHITE);
		
				JButton btnAdd = new JButton("직원추가");
				btnAdd.addActionListener(new ControllerAction());
				pnl_Infor1_3.add(btnAdd);
				
				JButton btnDelete = new JButton("직원삭제");
				btnDelete.addActionListener(new ControllerAction());
				pnl_Infor1_3.add(btnDelete);
				
				JButton btnSearch = new JButton("직원검색");
				btnSearch.addActionListener(new ControllerAction());
				pnl_Infor1_3.add(btnSearch);
				
				JButton btnClear = new JButton("입력초기화");
				btnClear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						tfStaffId.setText("");
						tfStaffName.setText("");
						tfStaffSal.setText("");
						tfStaffNum.setText("");
						tfStaffAddr.setText("");
						tfStaffPhone.setText("");
						// 첫 텍스트필드로 커서 이동
						tfStaffId.requestFocus();
					}
				});
				pnl_Infor1_3.add(btnClear);
				
				JButton btnUpdate = new JButton("직원수정");
				btnUpdate.addActionListener(new ControllerAction());
				pnl_Infor1_3.add(btnUpdate);
				
				
		JPanel pnl_Table = new JPanel();
		pnl_Table.setBackground(Color.WHITE);
		PnlBase.add(pnl_Table, BorderLayout.CENTER);
		
		JScrollPane spStaff = new JScrollPane();
		pnl_Table.add(spStaff, BorderLayout.CENTER);
			
		tblStaff = new JTable(db.getTable("employee"));
		tblStaff.setBackground(Color.WHITE);
		spStaff.setViewportView(tblStaff);
		tblStaff.addMouseListener(new ControllerMouse(tblStaff, "employee"));
		
		
		
		/*// 필드 크기
		tblStaff.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblStaff.getColumnModel().getColumn(1).setPreferredWidth(80);
		tblStaff.getColumnModel().getColumn(2).setPreferredWidth(120);
		tblStaff.getColumnModel().getColumn(4).setPreferredWidth(150);
		tblStaff.getColumnModel().getColumn(5).setPreferredWidth(250);
		*/
	}

}

