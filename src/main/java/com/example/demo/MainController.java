package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyuyun.lyy
 * @date 2023/6/12 11:00
 */
@RestController
public class MainController {

    @GetMapping("/checkpreload.htm")
    public String checkPreload() {
        return "success";
    }
}
