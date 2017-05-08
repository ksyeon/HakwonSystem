package mini.hakwon.model;

public class Classroom {
	private int cls_id; // 강의실 번호
	private int desk; // 책상 수
	private int project; // 프로젝트 수
	
	private int emp_id; // 관리하는 직원의 번호
	
	public Classroom() {}
	
	public Classroom(int cls_id, int desk, int project, int emp_id) {
		this.cls_id = cls_id;
		this.desk = desk;
		this.project = project;
		this.emp_id = emp_id;
	}
		
	public Classroom(int cls_id, int emp_id) {
		this.cls_id = cls_id;
		this.emp_id = emp_id;
	}

	public Classroom(int cls_id, int desk, int emp_id) {
		this.cls_id = cls_id;
		this.desk = desk;
		this.emp_id = emp_id;
	}

	/* Get 메소드 */		
	public int getCls_id() {	return cls_id;	}
	public int getDesk() {	return desk;	}
	public int getProject() {	return project;	}
	public int getEmp_id() {	return emp_id;	}
	
	
}
