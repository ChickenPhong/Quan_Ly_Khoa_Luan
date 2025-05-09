/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.controllers;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.services.NguoiDungService;
import com.tqp.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller

public class NguoiDungController {
    @Autowired
    private NguoiDungService nguoiDungService;

    // Trả về giao diện login (login.jsp)
    @GetMapping("/login")
    public String loginView() {
        return "login"; // => /WEB-INF/jsp/login.jsp hoặc tương đương
    }

    // API: Đăng nhập và trả về JWT
    @PostMapping("/api/login")
    @ResponseBody
    public ResponseEntity<?> apiLogin(@RequestParam String username, @RequestParam String password) {
        if (nguoiDungService.authenticate(username, password)) {
            try {
                String token = JwtUtils.generateToken(username);
                Map<String, String> result = new HashMap<>();
                result.put("token", token);
                return ResponseEntity.ok(result);
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body("Lỗi tạo token");
            }
        }

        return ResponseEntity.status(401).body("Sai thông tin đăng nhập");
    }
}
