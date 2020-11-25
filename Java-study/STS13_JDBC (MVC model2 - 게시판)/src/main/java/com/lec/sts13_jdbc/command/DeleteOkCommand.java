package com.lec.sts13_jdbc.command;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class DeleteOkCommand extends Command {

	@Override
	public void excute(Model model) {
		long uid = (long) model.getAttribute("uid");
		int result = super.getDao().delete(uid);
		model.addAttribute("result", result);
	}

}
