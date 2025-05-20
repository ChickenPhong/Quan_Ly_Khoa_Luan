package com.tqp.controllers;

import com.tqp.pojo.NguoiDung;
import com.tqp.pojo.TieuChi;
import com.tqp.services.NguoiDungService;
import com.tqp.services.TieuChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/tieuchi")
public class ApiTieuChiController {

    @Autowired
    private TieuChiService tieuChiService;

    @Autowired
    private NguoiDungService nguoiDungService;

    // Lấy danh sách tiêu chí
    @GetMapping
    public List<TieuChi> getAll() {
        return tieuChiService.getAll();
    }

    // Thêm tiêu chí mới
    @PostMapping("/add")
    public ResponseEntity<?> addTieuChi(@RequestBody TieuChi tieuChi, Principal principal) {
        //if (principal == null) {
        //    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Chưa đăng nhập"));
        //}
        NguoiDung user = nguoiDungService.getByUsername(principal.getName());
        
        //tieuChi.setCreatedBy(5); // ví dụ ID admin mặc định
        //tieuChi.setKhoa("Công nghệ thông tin");
        //tieuChi.setStatus("active");

        tieuChi.setCreatedBy(user.getId());
        tieuChi.setKhoa(user.getKhoa());
        tieuChi.setStatus("active");

        tieuChiService.addTieuChi(tieuChi);

        return ResponseEntity.ok(Map.of("message", "Thêm tiêu chí thành công"));
    }
}
