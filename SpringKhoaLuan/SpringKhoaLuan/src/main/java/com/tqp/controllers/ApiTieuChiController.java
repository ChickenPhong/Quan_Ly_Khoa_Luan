/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.controllers;

import com.tqp.pojo.NguoiDung;
import com.tqp.pojo.TieuChi;
import com.tqp.services.NguoiDungService;
import com.tqp.services.TieuChiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tieuchi")
public class ApiTieuChiController {

    @Autowired
    private TieuChiService tieuChiService;

    @Autowired
    private NguoiDungService nguoiDungService;

    // Lấy danh sách tiêu chí (GET /api/tieuchi)
    @GetMapping
    public ResponseEntity<List<TieuChi>> getAll() {
        List<TieuChi> list = tieuChiService.getAll();
        return ResponseEntity.ok(list);
    }

    // Tạo tiêu chí mới (POST /api/tieuchi/add)
    @PostMapping("/add")
    public ResponseEntity<?> addTieuChi(@RequestBody TieuChi tieuChi, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Chưa đăng nhập hoặc token không hợp lệ");
        }

        try {
            // Lấy user hiện tại
            NguoiDung user = nguoiDungService.getByUsername(principal.getName());

            // Gán người tạo
            tieuChi.setCreatedBy(user.getId());

            // Gán khoa của giáo vụ
            tieuChi.setKhoa(user.getKhoa());

            // Trạng thái mặc định
            tieuChi.setStatus("active");

            TieuChi saved = tieuChiService.addTieuChi(tieuChi);

            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi tạo tiêu chí: " + e.getMessage());
        }
    }
}
