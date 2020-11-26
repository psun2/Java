package com.command.write;

import java.util.List;

import org.springframework.ui.Model;

import com.lec.sts13_jdbc.domain.WriteDAOImpl;
import com.lec.sts13_jdbc.domain.WriteDTO;

public class BUpdateOkCommand implements BCommand {

	@Override
	public void excute(Model model) {
		WriteDTO dto = (WriteDTO) model.getAttribute("dto");
		
		System.out.println(dto.getUid());

		new WriteDAOImpl().update(dto);
		
		List<WriteDTO> list = new WriteDAOImpl().selectByUid(dto.getUid());

		model.addAttribute("result", list);

	}

}
