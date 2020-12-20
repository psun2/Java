package com.ltns.rest_area.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/member/autherror")
    public void autherror() {
    }

}
