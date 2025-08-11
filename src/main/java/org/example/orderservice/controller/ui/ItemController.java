package org.example.orderservice.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/items")
@Controller
public class ItemController {

    @GetMapping
    public String getItemMain(){
        return "item-main";
    }
}
