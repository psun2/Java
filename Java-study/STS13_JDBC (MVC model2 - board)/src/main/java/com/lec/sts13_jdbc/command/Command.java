package com.lec.sts13_jdbc.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.lec.sts13_jdbc.beans.BoardDAO;

import lombok.Data;

@Component
@Data
public abstract class Command {

	@Autowired
	@Qualifier("boardDAO")
	 private BoardDAO dao;
	
	public abstract void excute(Model model);

	
}
