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
import com.tqp.services.NguoiDungService;
import com.tqp.utils.JwtUtils;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiNguoiDungController {
    @Autowired
    private NguoiDungService nguoiDungService;

    // Đăng ký người dùng mới (POST /api/users)
    @PostMapping("/users")
    public ResponseEntity<NguoiDung> create(@RequestParam Map<String, String> params,
                                            @RequestParam(name = "avatar", required = false) MultipartFile avatar) {
        NguoiDung u = this.nguoiDungService.addUser(params, avatar);
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

    // Đăng nhập (POST /api/login)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody NguoiDung u) {
        if (this.nguoiDungService.authenticate(u.getUsername(), u.getPassword())) {
            try {
                String token = JwtUtils.generateToken(u.getUsername());
                return ResponseEntity.ok(Collections.singletonMap("token", token));
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Lỗi khi tạo JWT");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai thông tin đăng nhập");
    }

    // Truy cập thông tin người dùng hiện tại (GET /api/secure/profile)
    @RequestMapping("/secure/profile")
    @ResponseBody
    public ResponseEntity<NguoiDung> getProfile(Principal principal) {
        return new ResponseEntity<>(this.nguoiDungService.getByUsername(principal.getName()), HttpStatus.OK);
    }
}
