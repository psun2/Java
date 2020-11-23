package com.lec.spring.property.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DBConn {

    private String id;
    private String pw;
    private String url;
    private String port;

}
