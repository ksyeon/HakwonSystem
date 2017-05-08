package mini.hakwon.model;

//New


import javax.swing.JTextField;


public class Teacher {
	
	private int t_Id=-1;
	private String t_Name;
	private String t_Phone;
	private int t_Sal=-1;
	private String addr;
	private String licenceName;
	private int licenceNum=-1;
	private String t_num;
	private char contract;
	private String type;
	

	public Teacher() {}
	public Teacher(int t_Id, String t_Name, int t_Sal, String addr, String licenceName) {
		super();
		this.t_Id = t_Id;
		this.t_Name = t_Name;
		this.t_Sal = t_Sal;
		this.addr = addr;
		this.licenceName = licenceName;
	}

	public Teacher(int _t_id) {
		super();
		this.t_Id = _t_id;
	}	
	
	
	public Teacher(int _t_Id, String _t_Name, String _t_Phone, int _t_Sal, String _addr, String _licenceName, int _licenceNum, String _t_num, char _contract) {
		super();
		this.t_Id = _t_Id;
		this.t_Name = _t_Name;
		this.t_Phone = _t_Phone;
		this.t_Sal = _t_Sal;
		this.addr = _addr;
		this.licenceName = _licenceName;
		this.licenceNum = _licenceNum;
		this.t_num=_t_num;
		this.contract=_contract;
	}



	public Teacher(String t_Name) {
		super();
		this.t_Name = t_Name;
	}



	public Teacher(int t_Id, String t_Name, int t_Sal, String addr, String licenceName, String t_phone, String tnum, char contract) {
		this.t_Id = t_Id;
		this.t_Name = t_Name;
		this.t_Sal = t_Sal;
		this.addr = addr;
		this.licenceName = licenceName;
		this.t_Phone = t_phone;
		this.t_num = tnum;
		this.contract = contract;
	}

	/* Get 메소드. */
	public int getT_Id() {		return t_Id;	}
	public String getT_Name() {		return t_Name;	}
	public int getT_Sal() {		return t_Sal;	}
	public String getAddr() {		return addr;	}
	public String getLicenceName() {		return licenceName;	}
	public int getLicenceNum() {		return licenceNum;	}
	public char getContract() {		return contract;	}
	public String getT_Phone() {		return t_Phone;	}
	public String getT_num() {		return t_num;	}
	
	
	
	public String getType() {		return type;	}
	
	
	/* Set 메소드*/
	public void setLicenceName(String licenceName) {		this.licenceName = licenceName;	}
	public void setAddr(String addr) {		this.addr = addr;	}
	public void setT_Name(String t_Name) {		this.t_Name = t_Name;	}
	public void setT_Id(int t_Id) {		this.t_Id = t_Id;	}
	public void setT_Sal(int t_Sal) {		this.t_Sal = t_Sal;	}
	public void setLicenceNum(int licenceNum) {		this.licenceNum = licenceNum;	}
	public void setContract(char contract) {		this.contract = contract;	}
	public void setType(String type) {		this.type = type;	}
	public void setT_Phone(String t_Phone) {		this.t_Phone = t_Phone;	}
	public void setT_num(String t_num) {		this.t_num = t_num;	}
}
