/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.controllers;

/**
 *
 * @author Tran Quoc Phong
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NguoiDungController {
    // Trả về giao diện login (HTML/JSP)
    @GetMapping("/login")
    public String loginView() {
        return "login"; // ứng với login.html (Thymeleaf) hoặc login.jsp
    }
}
