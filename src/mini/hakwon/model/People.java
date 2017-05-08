package mini.hakwon.model;

public class People {
	public int idNum;
	public String name;
	public String p_num;
	public String addr;
	public String phone;
	public String value;
	public int sal;


	public People(int idNum) {
		super();
		this.idNum = idNum;
	}
	
	
	
	public People(int idNum, String name, String p_num, String addr, String phone) {
		super();
		this.idNum = idNum;
		this.name = name;
		this.p_num = p_num;
		this.addr = addr;
		this.phone = phone;
	}

	public People(int idNum, String name, String p_num, String addr, String phone, int sal) {
		super();
		this.idNum = idNum;
		this.name = name;
		this.p_num = p_num;
		this.addr = addr;
		this.phone = phone;
		this.sal = sal;
	}

	public People(int idNum, String name, String p_num, String phone) {
		super();
		this.idNum = idNum;
		this.name = name;
		this.p_num = p_num;
		this.phone = phone;
	}

	public People(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public People(String name, int idNum) {
		super();
		this.idNum = idNum;
		this.name = name;
	}

	public People(int idNum, String name, String phone) {
		super();
		this.idNum = idNum;
		this.name = name;
		this.phone = phone;
	}

	public People(String value) {
		super();
		this.value = value;
	}

	public People() {
		// TODO Auto-generated constructor stub
	}

	public People(int emp_id, String emp_name, int emp_sal, String emp_phone) {
		this.idNum = emp_id;
		this.name = emp_name;
		this.sal = emp_sal;
		this.phone = emp_phone;
	}

	public int getIdNum() {
		return idNum;
	}

	public void setIdNum(int idNum) {
		this.idNum = idNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getP_num() {
		return p_num;
	}

	public void setP_num(String p_num) {
		this.p_num = p_num;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

}
