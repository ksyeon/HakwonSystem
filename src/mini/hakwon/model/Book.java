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
		System.out.println(bk_name + ", " + bk_pub + ", " + bk_fee + "�߰��Ǿ���");
		//���� �߰� �޼ҵ�
	}

	public Book(String bk_name) {
		this.bk_name = bk_name;
		System.out.println(bk_name + " �����Ǿ���");
		// ���� �޼ҵ�
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