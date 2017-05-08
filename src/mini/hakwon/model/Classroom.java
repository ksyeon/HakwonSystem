package mini.hakwon.model;

public class Classroom {
	private int cls_id; // ���ǽ� ��ȣ
	private int desk; // å�� ��
	private int project; // ������Ʈ ��
	
	private int emp_id; // �����ϴ� ������ ��ȣ
	
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

	/* Get �޼ҵ� */		
	public int getCls_id() {	return cls_id;	}
	public int getDesk() {	return desk;	}
	public int getProject() {	return project;	}
	public int getEmp_id() {	return emp_id;	}
	
	
}
