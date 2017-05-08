package mini.hakwon.model;

//New


public class Order {
	//name은 공급자 이름, id는 공급자 번호, value는 텍스트값
	String type; //검색할떄 정렬 설정
	String order; // 공급자인지, 주문인지 설정하는데 받는 값
	String value;
	String name;
	int id;
	
	
	int ordNo;	//주문번호
	int ordCnt; //주문량
	String ordDate; //주문일자
	String payDate; //대금지불일
	String book;	//책이름
	int supplyID;	//공급자ID
	
	//ordNo,ordCnt,ordDate,payDate,book,supplyID)
	
	//주문등록 데이터를 위한 생성자 6(+대금지불일.)
	public Order(int ordNo, int ordCnt, String ordDate, String payDate, String book, int supplyID) {
		super();
		this.ordNo = ordNo;
		this.ordCnt = ordCnt;
		this.ordDate = ordDate;
		this.payDate = payDate;
		this.book = book;
		this.supplyID = supplyID;
	}
	
	//주문등록 데이터를 위한 생성자 5( without대금지불일.)
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
