package br.pucrs.totem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Server {
   
    @GetMapping("/")
    public String redirectToDocs() {
        return "redirect:/docs";
    } 

}
