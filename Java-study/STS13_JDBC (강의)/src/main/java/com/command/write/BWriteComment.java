package com.command.write;

import org.springframework.ui.Model;

import com.lec.sts13_jdbc.domain.WriteDAOImpl;
import com.lec.sts13_jdbc.domain.WriteDTO;

public class BWriteComment implements BCommand {

	@Override
	public void excute(Model model) {
		WriteDTO dto = (WriteDTO) model.getAttribute("dto");

		int result = new WriteDAOImpl().insert(dto);

		model.addAttribute("result", result);

	}

}
