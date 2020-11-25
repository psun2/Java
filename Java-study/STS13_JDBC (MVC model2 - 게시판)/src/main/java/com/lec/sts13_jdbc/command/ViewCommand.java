package com.lec.sts13_jdbc.command;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.lec.sts13_jdbc.beans.BoardDTO;

@Component
public class ViewCommand extends Command {

	@Override
	public void excute(Model model) {
		long uid = (long) model.getAttribute("uid");
		List<BoardDTO> result = super.getDao().selectById(uid);
		model.addAttribute("result", result);
	}

}
