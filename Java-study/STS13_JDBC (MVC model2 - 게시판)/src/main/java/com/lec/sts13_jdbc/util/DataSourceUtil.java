package com.lec.sts13_jdbc.util;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component("dataSource")
public class DataSourceUtil extends DriverManagerDataSource{

	public DataSourceUtil() {
		super();
		setDriverClassName("oracle.jdbc.driver.OracleDriver");
		setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		setUsername("scott_b");
		setPassword("tiger_b");
	}
	
}
