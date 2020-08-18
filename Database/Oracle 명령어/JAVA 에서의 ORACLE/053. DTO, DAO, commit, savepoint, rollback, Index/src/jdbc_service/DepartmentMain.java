package jdbc_service;

import java.util.ArrayList;

import DAO.DepartmentDAO;
import DAO.DepartmentDTO;

public class DepartmentMain {
	
	public static void main(String[] args) {
		
		ArrayList<DepartmentDTO> serch = new DepartmentDAO().list();
		System.out.println(serch);
		
	}

}
