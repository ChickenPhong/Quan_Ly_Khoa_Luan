/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.controllers;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.NguoiDung;
import com.tqp.services.DeTaiService;
import com.tqp.services.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @Autowired
    private DeTaiService deTaiService;
    
    @Autowired
    private NguoiDungService nguoiDungService;

    @RequestMapping("/")
    public String index(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            NguoiDung user = nguoiDungService.getByUsername(username);

            if (user != null && "ROLE_ADMIN".equals(user.getRole())) {
                return "redirect:/admin"; // Chuyển hướng nếu là admin
            }
        }

        model.addAttribute("deTais", deTaiService.getAllDeTai());
        return "index"; // Giao diện mặc định cho user khác
    }
}
