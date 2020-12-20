package com.ltns.rest_area.config.jdbc;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class DBInfo {

    @Value("${db.driverClassName}")
    private String driverClassName;

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

}
