package com.niuzj.springboot_https;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
    @RequestMapping("/getTime")
    public String getTime(){
        return String.valueOf(System.currentTimeMillis());
    }
}
