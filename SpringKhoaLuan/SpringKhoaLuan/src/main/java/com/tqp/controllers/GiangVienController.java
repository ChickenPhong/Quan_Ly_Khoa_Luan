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
public class GiangVienController {
    // Giao diện dành cho giảng viên
    @GetMapping("/giangvien")
    public String giangVienView() {
        return "giangvien"; // Trả về file giangvien.html (nhớ tạo nếu chưa có)
    }
}
