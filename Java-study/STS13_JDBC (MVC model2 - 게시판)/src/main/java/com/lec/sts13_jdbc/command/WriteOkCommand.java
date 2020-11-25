package com.lec.sts13_jdbc.command;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.lec.sts13_jdbc.beans.BoardDTO;

@Component
public class WriteOkCommand extends Command {

	@Override
	public void excute(Model model) {
		BoardDTO dto = (BoardDTO) model.getAttribute("result");
		int result = super.getDao().write(dto);
		model.addAttribute("result", result);
	}

}
