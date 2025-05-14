/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.controllers;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.services.DeTaiHuongDanService;
import com.tqp.services.DeTaiService;
import com.tqp.services.NguoiDungService;
import com.tqp.services.DeTaiSinhVienService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GiangVienController {
    
    @Autowired
    private NguoiDungService nguoiDungService;

    @Autowired
    private DeTaiService deTaiService;

    @Autowired
    private DeTaiHuongDanService deTaiGVHuongDanService;

    @Autowired
    private DeTaiSinhVienService deTaiSinhVienService;
    
    // Giao diện dành cho giảng viên
    @GetMapping("/giangvien")
    public String danhSachThucHien(@RequestParam(value = "khoaHoc", required = false) String khoaHoc,
            Principal principal,
            Model model) {
        var user = nguoiDungService.getByUsername(principal.getName());

        // Nạp danh sách năm
        int currentYear = java.time.Year.now().getValue();
        List<String> years = new ArrayList<>();
        for (int y = 2020; y <= currentYear; y++) {
            years.add(String.valueOf(y));
        }
        model.addAttribute("yearOptions", years);
        model.addAttribute("khoaHoc", khoaHoc);

        if (khoaHoc != null) {
            var sinhViens = nguoiDungService.getSinhVienByKhoaVaKhoaHoc(user.getKhoa(), khoaHoc);
            model.addAttribute("sinhViens", sinhViens);

            Map<Integer, String> deTaiMap = new HashMap<>();
            Map<Integer, String> giangVienMap = new HashMap<>();

            for (var sv : sinhViens) {
                var dtsv = deTaiSinhVienService.findBySinhVienId(sv.getId());
                if (dtsv != null) {
                    var dt = deTaiService.getDeTaiById(dtsv.getDeTaiKhoaLuanId());
                    var gvs = deTaiGVHuongDanService.findAllByDeTaiId(dt.getId());

                    // Thêm vào map đề tài
                    deTaiMap.put(sv.getId(), dt != null ? dt.getTitle() : "Chưa có");

                    // Lấy giảng viên từ danh sách giảng viên hướng dẫn
                    String tenGVs = gvs.stream()
                            .map(gv -> {
                                var giangVien = nguoiDungService.getById(gv.getGiangVienHuongDanId());
                                return giangVien != null ? giangVien.getUsername() : "Chưa có";
                            })
                            .collect(Collectors.joining(", "));

                    // Thêm vào map giảng viên
                    giangVienMap.put(sv.getId(), tenGVs.isEmpty() ? "Chưa có" : tenGVs);
                }
            }

            model.addAttribute("deTaiMap", deTaiMap);
            model.addAttribute("giangVienMap", giangVienMap);
            model.addAttribute("khoa", user.getKhoa());
        }

        model.addAttribute("khoa", user.getKhoa());
        return "giangvien";
    }
}
