package com.lec.spring.profile.java;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerInfo {

    private String ipNum;
    private String portNum;

}
