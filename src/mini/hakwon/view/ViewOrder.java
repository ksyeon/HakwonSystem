package mini.hakwon.view;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
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

import mini.hakwon.util.ControllerAction;
import mini.hakwon.util.DbUtil;
import mini.hakwon.util.Query;

//New

public class ViewOrder extends JPanel {

	private JPanel pnl_base;
	public static JTextField tf_Search;
	public static JTable tbl_Order;
		JScrollPane scrollPane;
		JButton btn_Search;
	public static JComboBox comboBox;
	CheckboxGroup g;
	static public Checkbox rad_supply, rad_OrderNum;
	private JPanel panel_1;
	private JLabel lblOrdNo;
	public static JTextField tfOrdNo, tfOrdCnt, tfOrdDate, tfPayDate, tfBook,tfSupplyNo,tfSupplyName;
	private JLabel lblOrdCnt;
	private JLabel lblOrdDate;
	private JLabel lblPayDateLabel;
	private JLabel lblBookLabel;
	private JButton btnOrdAdd;
	private JLabel lblEmpLabel;
	private JLabel pnlEmpName;
	private JButton bntEmpAdd;
	private JPanel panel;
	private JComboBox cbCourseText;
	DefaultComboBoxModel dfCombo;
	
	
	Query q = new Query();
	
	public ViewOrder() {
		DbUtil db = new DbUtil();
		ControllerAction controller = new ControllerAction();
		setLayout(new BorderLayout(0, 0));
		
		// 베이스 판넬
		pnl_base = new JPanel();
		add(pnl_base, BorderLayout.NORTH);
		pnl_base.setLayout(new BoxLayout(pnl_base,1));

		JPanel pnl_manage = new JPanel();
		pnl_base.add(pnl_manage);
		pnl_manage.setLayout(new BorderLayout(0, 0));

		JPanel pnl_Array = new JPanel();
		pnl_manage.add(pnl_Array, BorderLayout.NORTH);

		g = new CheckboxGroup();
		rad_supply = new Checkbox("Supply", g, true);
		pnl_Array.add(rad_supply);

		rad_OrderNum = new Checkbox("Order",g, false);
		pnl_Array.add(rad_OrderNum);
		rad_supply.addItemListener(controller);
		rad_OrderNum.addItemListener(controller);
		
		
		JPanel pnl_Search = new JPanel();
		pnl_Array.add(pnl_Search);

		btn_Search = new JButton("주문검색");
		btn_Search.addActionListener(controller);
		
		pnl_Search.add(btn_Search);

		comboBox = new JComboBox();
		pnl_Search.add(comboBox);
		
		panel = new JPanel();
		pnl_base.add(panel);
		
		lblOrdNo= new JLabel("주문번호");
		panel.add(lblOrdNo);
		
		tfOrdNo = new JTextField();
		panel.add(tfOrdNo);
		tfOrdNo.setColumns(10);
		
		lblOrdCnt= new JLabel("주문량");
		panel.add(lblOrdCnt);
		
		tfOrdCnt = new JTextField();
		panel.add(tfOrdCnt);
		tfOrdCnt.setColumns(10);
		
		lblOrdDate = new JLabel("주문날짜");
		panel.add(lblOrdDate);

		tfOrdDate = new JTextField();
		panel.add(tfOrdDate);
		tfOrdDate.setColumns(10);
		
		panel_1 = new JPanel();
		pnl_base.add(panel_1);
		
		lblPayDateLabel = new JLabel("대금지불날짜");
		panel_1.add(lblPayDateLabel);
		
		tfPayDate = new JTextField();
		panel_1.add(tfPayDate);
		tfPayDate.setColumns(10);
		
		lblBookLabel = new JLabel("교재");
		panel_1.add(lblBookLabel);
		
		cbCourseText = new JComboBox();
		
		
		
		/* 교재명 가져오기 */
		try {
			dfCombo = new DefaultComboBoxModel();
			cbCourseText = new JComboBox(q.getBkName(dfCombo));
			panel_1.add(cbCourseText);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		/*
		 * tfBook = new JTextField();
		panel_1.add(tfBook);
		tfBook.setColumns(10);
		*/
		
		btnOrdAdd= new JButton("주문등록");
		btnOrdAdd.addActionListener(controller);
		panel_1.add(btnOrdAdd);

		JPanel pnl_Table = new JPanel();
		pnl_Table.setBackground(Color.WHITE);
		pnl_base.add(pnl_Table);
		
		lblEmpLabel = new JLabel("공급자ID");
		pnl_Table.add(lblEmpLabel);
		
		tfSupplyNo = new JTextField();
		pnl_Table.add(tfSupplyNo);
		tfSupplyNo.setColumns(10);
		
		pnlEmpName = new JLabel("상호");
		pnl_Table.add(pnlEmpName);
		
		tfSupplyName = new JTextField();
		pnl_Table.add(tfSupplyName);
		tfSupplyName.setColumns(10);
		
		bntEmpAdd = new JButton("공급자등록");
		bntEmpAdd.addActionListener(controller);
		pnl_Table.add(bntEmpAdd);

		tbl_Order = new JTable(db.getTable("order"));
		scrollPane = new JScrollPane(tbl_Order);
		add(scrollPane, BorderLayout.CENTER);
	}

	public JTextField getTfOrdNo() {
		return tfOrdNo;
	}

	public JTextField getTfOrdCnt() {
		return tfOrdCnt;
	}

	public JTextField getTfOrdDate() {
		return tfOrdDate;
	}

	public JTextField getTfPayDate() {
		return tfPayDate;
	}

	public JTextField getTfBook() {
		return tfBook;
	}

	public JTextField getTfSupplyNo() {
		return tfSupplyNo;
	}

	public JTextField getTfSupplyName() {
		return tfSupplyName;
	}

	
	
}
