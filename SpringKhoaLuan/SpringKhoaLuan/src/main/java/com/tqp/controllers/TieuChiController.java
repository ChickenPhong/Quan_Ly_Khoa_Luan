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
import com.tqp.pojo.TieuChi;
import com.tqp.services.NguoiDungService;
import com.tqp.services.TieuChiService;
import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tieuchi")
public class TieuChiController {
    @Autowired
    private TieuChiService tieuChiService;
    
    @Autowired
    private NguoiDungService nguoiDungService;


    @GetMapping
    public String index(Model model) {
        model.addAttribute("tieuchis", tieuChiService.getAll());
        return "tieuchi";  // View hiển thị danh sách tiêu chí + form thêm
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTieuChi(@RequestBody TieuChi tieuChi, Principal principal) {
        // Kiểm tra principal (người dùng đã đăng nhập chưa)
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .body(Map.of("message", "Chưa đăng nhập"));
        }

        // Lấy user từ principal
        NguoiDung user = nguoiDungService.getByUsername(principal.getName());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(Map.of("message", "Người dùng không tồn tại"));
        }

        // Gán thông tin user tạo tiêu chí
        tieuChi.setCreatedBy(user.getId());
        tieuChi.setKhoa(user.getKhoa());
        tieuChi.setStatus("active");

        // Thêm tiêu chí
        try {
            tieuChiService.addTieuChi(tieuChi);
        } catch (Exception e) {
            // Bắt lỗi nếu có, trả về thông báo lỗi
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("message", "Lỗi khi thêm tiêu chí: " + e.getMessage()));
        }

        // Trả về thành công
        return ResponseEntity.ok(Map.of("message", "Thêm tiêu chí thành công"));
    }

}
