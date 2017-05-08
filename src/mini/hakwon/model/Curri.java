package mini.hakwon.model;

public class Curri {
	private int cr_id;
	private String cr_name;
	private int t_id;
	private int cls_id;
	private int cr_fee;
	private String bk_name;	
	private String regi_start;
	private String regi_end;
	private String cr_start;
	private String type;

	/* 생성자 - 오버로딩*/
	public Curri(int cr_id, String cr_name, int t_id, int cls_id, int cr_fee, String bk_name, 
			String regi_start, String regi_end, String cr_start) {
		super();
		this.cr_id = cr_id;
		this.cr_name = cr_name;
		this.t_id = t_id;
		this.cls_id = cls_id;
		this.cr_fee = cr_fee;
		this.bk_name = bk_name;
		this.regi_start = regi_start;
		this.regi_end = regi_end;
		this.cr_start = cr_start;
	}
	
	
	
	public Curri(int cr_id, String cr_name, int t_id, int cls_id, String bk_name) {
		super();
		this.cr_id = cr_id;
		this.cr_name = cr_name;
		this.t_id = t_id;
		this.cls_id = cls_id;
		this.bk_name = bk_name;
	}



	public Curri(int cr_id) {
		this.cr_id = cr_id;
	}
	
	/*  Get 메소드  */
	public int getCr_id() {	return cr_id;	}
	public String getCr_name() {	return cr_name;	}
	public int getT_id() {	return t_id;	}
	public int getCls_id() {	return cls_id;	}
	public int getCr_fee() {	return cr_fee;	}
	public String getBk_name() {	return bk_name;	}
	public String getRegi_start() {		return regi_start;	}
	public String getRegi_end() {	return regi_end;	}
	public String getCr_start() {	return cr_start;	}
	public String getType() {
		return type;
	}

}
