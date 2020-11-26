package com.lec.sts13_jdbc.util;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("template")
public class Template extends JdbcTemplate{

	@Autowired
	@Qualifier("dataSource")
	@Override
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}
	
}
