package com.lec.sts13_jdbc.command;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.lec.sts13_jdbc.beans.BoardDTO;

@Component
public class ListCommand extends Command {

	@Override
	public void excute(Model model) {
		List<BoardDTO> list = super.getDao().selectAll();
		model.addAttribute("list", list);
	}

}
