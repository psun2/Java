package DAO;

public class DepartmentDTO {

	public String id, name;

	public DepartmentDTO(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public DepartmentDTO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "DepartmentDTO [id=" + id + ", name=" + name + "]";
	}

}
