package com.command.write;

import org.springframework.ui.Model;

import com.lec.sts13_jdbc.domain.WriteDAOImpl;

public class BDeleteCommand implements BCommand {

	@Override
	public void excute(Model model) {
		model.addAttribute("result", new WriteDAOImpl().deleteByUid((long)(model.getAttribute("uid"))));
	}

}