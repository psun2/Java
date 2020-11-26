package com.command.write;

import java.util.List;

import org.springframework.ui.Model;

import com.lec.sts13_jdbc.domain.WriteDAOImpl;
import com.lec.sts13_jdbc.domain.WriteDTO;

public class BViewCommand implements BCommand {

	@Override
	public void excute(Model model) {

		long uid = (long) model.getAttribute("uid");
		List<WriteDTO> dto = new WriteDAOImpl().readByUid(uid);
		model.addAttribute("result", dto);
	}

}