package com.command.write;

import java.util.List;

import org.springframework.ui.Model;

import com.lec.sts13_jdbc.domain.WriteDAOImpl;
import com.lec.sts13_jdbc.domain.WriteDTO;
import com.lec.sts13_jdbc.domain.WriteDAO;

public class BListCommand implements BCommand {

	@Override
	public void excute(Model model) {
	
		WriteDAO dao = new WriteDAOImpl();
		List<WriteDTO> list = dao.select();
		model.addAttribute("list", list);

	}

} // end Command

