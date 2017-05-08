package mini.hakwon.model;

//New


public class Order {
	//name�� ������ �̸�, id�� ������ ��ȣ, value�� �ؽ�Ʈ��
	String type; //�˻��ҋ� ���� ����
	String order; // ����������, �ֹ����� �����ϴµ� �޴� ��
	String value;
	String name;
	int id;
	
	
	int ordNo;	//�ֹ���ȣ
	int ordCnt; //�ֹ���
	String ordDate; //�ֹ�����
	String payDate; //���������
	String book;	//å�̸�
	int supplyID;	//������ID
	
	//ordNo,ordCnt,ordDate,payDate,book,supplyID)
	
	//�ֹ���� �����͸� ���� ������ 6(+���������.)
	public Order(int ordNo, int ordCnt, String ordDate, String payDate, String book, int supplyID) {
		super();
		this.ordNo = ordNo;
		this.ordCnt = ordCnt;
		this.ordDate = ordDate;
		this.payDate = payDate;
		this.book = book;
		this.supplyID = supplyID;
	}
	
	//�ֹ���� �����͸� ���� ������ 5( without���������.)
	public Order(int ordNo, int ordCnt, String ordDate, String book, int supplyID) {
		super();
		this.ordNo = ordNo;
		this.ordCnt = ordCnt;
		this.ordDate = ordDate;
		this.book = book;
		this.supplyID = supplyID;
	}
	
	public Order(String type, String order, String value) {
		super();
		this.type = type;
		this.order = order;
		this.value = value;
	}

	public Order(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}
	
	public Order(String s, String orderSta) {
		this.type = s;
		this.order = orderSta;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public String getOrder() {
		return order;
	}

	public String getValue() {
		return value;
	}
	
	public int getOrdNo() {
		return ordNo;
	}

	public void setOrdNo(int ordNo) {
		this.ordNo = ordNo;
	}

	public int getOrdCnt() {
		return ordCnt;
	}

	public void setOrdCnt(int ordCnt) {
		this.ordCnt = ordCnt;
	}

	public String getOrdDate() {
		return ordDate;
	}

	public void setOrdDate(String ordDate) {
		this.ordDate = ordDate;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public int getSupplyID() {
		return supplyID;
	}

	public void setSupplyID(int supplyID) {
		this.supplyID = supplyID;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
