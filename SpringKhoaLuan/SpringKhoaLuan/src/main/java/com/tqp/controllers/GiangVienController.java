/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.controllers;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.BangDiem;
import com.tqp.services.BangDiemService;
import com.tqp.services.DeTaiHoiDongService;
import com.tqp.services.DeTaiHuongDanService;
import com.tqp.services.DeTaiService;
import com.tqp.services.NguoiDungService;
import com.tqp.services.DeTaiSinhVienService;
import com.tqp.services.PhanCongGiangVienPhanBienService;
import com.tqp.services.TieuChiService;
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
    
    @Autowired
    private PhanCongGiangVienPhanBienService phanCongGiangVienPhanBienService;

    @Autowired
    private DeTaiHoiDongService deTaiHoiDongService;

    @Autowired
    private TieuChiService tieuChiService;

    @Autowired
    private BangDiemService bangDiemService;

    
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
    
    @GetMapping("/giangvien/chamdiem")
    public String hienThiFormChamDiem(@RequestParam(value = "hoiDongId", required = false) Integer hoiDongId,
                                     Principal principal,
                                     Model model) {
        var user = nguoiDungService.getByUsername(principal.getName());

        var giangVienPhanBienList = phanCongGiangVienPhanBienService.findByUserId(user.getId());
        if (giangVienPhanBienList == null || giangVienPhanBienList.isEmpty()) {
            model.addAttribute("message", "Bạn không phải giảng viên phản biện hoặc chưa được phân công.");
            return "error";
        }
        var giangVienPhanBien = giangVienPhanBienList.get(0); // Lấy phần tử đầu tiên tạm thời

        var deTaiHoiDongs = deTaiHoiDongService.findByHoiDongId(hoiDongId);
        var tieuchis = tieuChiService.findByKhoa(user.getKhoa());

        Map<Integer, Map<String, Float>> diemDaCham = new HashMap<>();
        for (var dthd : deTaiHoiDongs) {
            var dsDiem = bangDiemService.getAll().stream()
                .filter(d -> d.getGiangVienPhanBienId().equals(giangVienPhanBien.getGiangVienPhanBienId())
                        && d.getDeTaiKhoaLuanId().equals(dthd.getDeTaiKhoaLuanId()))
                .collect(Collectors.toList());

            Map<String, Float> diemTheoTieuChi = new HashMap<>();
            for (var d : dsDiem) {
                diemTheoTieuChi.put(d.getTieuChi(), d.getDiem());
            }
            diemDaCham.put(dthd.getDeTaiKhoaLuanId(), diemTheoTieuChi);
        }

        model.addAttribute("deTaiHoiDongs", deTaiHoiDongs);
        model.addAttribute("tieuchis", tieuchis);
        model.addAttribute("giangVienPhanBien", giangVienPhanBien);
        model.addAttribute("diemDaCham", diemDaCham);
        model.addAttribute("hoiDongId", hoiDongId);

        return "chamdiem_phanbien";

    }
    
    @PostMapping("/giangvien/chamdiem/save")
    public String luuDiemCham(@RequestParam Map<String, String> params,
                              @RequestParam int giangVienPhanBienId,
                              @RequestParam int hoiDongId,
                              Model model) {

        var tieuchis = tieuChiService.getAll(); // hoặc findByKhoa(...)
        var deTaiHoiDongs = deTaiHoiDongService.findByHoiDongId(hoiDongId);

        for (var dthd : deTaiHoiDongs) {
            for (var tc : tieuchis) {
                String paramKey = "diem_" + dthd.getDeTaiKhoaLuanId() + "_" + tc.getTenTieuChi();
                if (params.containsKey(paramKey) && !params.get(paramKey).isEmpty()) {
                    float diem = Float.parseFloat(params.get(paramKey));
                    bangDiemService.add(new BangDiem(null, dthd.getDeTaiKhoaLuanId(), giangVienPhanBienId, tc.getTenTieuChi(), diem));
                }
            }
        }

        return "redirect:/giangvien/chamdiem?hoiDongId=" + hoiDongId;
    }

}
