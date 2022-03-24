package com.example.freemoneynoscam.controllers;

import com.example.freemoneynoscam.services.ValidateEmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;


@Controller
public class IndexController {
    ValidateEmailService validationService = new ValidateEmailService();

    public IndexController() {
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/test")
    public String test(WebRequest dataFromForm) {
        String email = dataFromForm.getParameter("email");
        boolean duplicated = validationService.checkDuplicate(email);
        if (duplicated) {
            return "redirect:/duplicate";
        } else {
            boolean isValid = validationService.isEmailValid(email);
            if (isValid) {
                return "redirect:/succes";
            } else {
                return "redirect:/fail";
            }
        }
    }

    @GetMapping("/fail")
    public String fail() {
        return "fail";
    }

    @GetMapping("/succes")
    public String succes() {
        return "succes";
    }

    @GetMapping("/duplicate")
    public String duplicate() {
        return "duplicate";
    }
}
