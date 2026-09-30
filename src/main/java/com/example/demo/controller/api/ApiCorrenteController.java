package com.example.demo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CorrenteService;

@RestController
@RequestMapping({"/corrente", "/corrente/"})
public class ApiCorrenteController {

    @Autowired
    private CorrenteService correnteService;

    @PostMapping("/depositar")
    public String depositar() {
        return "saque";
    }
}
