package mini.hakwon.model;

public class Book {
	String bk_name;
	String bk_pub;
	int bk_fee;
	
	

	public Book(String bk_name, String bk_pub, int bk_fee) {
		super();
		this.bk_name = bk_name;
		this.bk_pub = bk_pub;
		this.bk_fee = bk_fee;
		System.out.println(bk_name + ", " + bk_pub + ", " + bk_fee + "추가되었음");
		//쿼리 추가 메소드
	}

	public Book(String bk_name) {
		this.bk_name = bk_name;
		System.out.println(bk_name + " 삭제되었음");
		// 삭제 메소드
	}

	public String getBk_name() {
		return bk_name;
	}

	public void setBk_name(String bk_name) {
		this.bk_name = bk_name;
	}

	public String getBk_pub() {
		return bk_pub;
	}

	public int getBk_fee() {
		return bk_fee;
	}
	
	
}