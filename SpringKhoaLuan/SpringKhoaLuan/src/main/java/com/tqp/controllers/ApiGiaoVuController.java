package com.tqp.controllers;

import com.tqp.dto.BangDiemTongHopDTO;
import com.tqp.pojo.DeTaiKhoaLuan;
import com.tqp.pojo.HoiDong;
import com.tqp.pojo.NguoiDung;
import com.tqp.services.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/giaovu")
public class ApiGiaoVuController {

    @Autowired
    private NguoiDungService nguoiDungService;

    @Autowired
    private DeTaiService deTaiService;

    @Autowired
    private DeTaiSinhVienService deTaiSinhVienService;

    @Autowired
    private DeTaiHuongDanService deTaiGVHuongDanService;

    @Autowired
    private HoiDongService hoiDongService;

    @Autowired
    private DeTaiHoiDongService deTaiHoiDongService;

    @Autowired
    private PhanCongGiangVienPhanBienService phanCongGiangVienPhanBienService;

    @Autowired
    private BangDiemService bangDiemService;

    @Autowired
    private com.tqp.services.EmailService emailService;

    @Autowired
    private PdfExportService pdfExportService;

    // 1. Lấy danh sách đề tài theo vai trò và khoa user
    @GetMapping("/detai")
    public ResponseEntity<List<DeTaiKhoaLuan>> getDeTai(Principal principal) {
        var user = nguoiDungService.getByUsername(principal.getName());
        List<DeTaiKhoaLuan> deTaiList;
        if ("ROLE_GIAOVU".equals(user.getRole())) {
            deTaiList = deTaiService.getByKhoa(user.getKhoa());
        } else {
            deTaiList = deTaiService.getAllDeTai();
        }
        return ResponseEntity.ok(deTaiList);
    }

    // 2. Thêm đề tài mới
    @PostMapping("/detai")
    public ResponseEntity<?> addDeTai(@RequestBody DeTaiKhoaLuan khoaLuan, Principal principal) {
        var user = nguoiDungService.getByUsername(principal.getName());
        if ("ROLE_GIAOVU".equals(user.getRole())) {
            khoaLuan.setKhoa(user.getKhoa());
        }
        deTaiService.addDeTai(khoaLuan);
        return ResponseEntity.ok(Map.of("message", "Thêm đề tài thành công"));
    }

    // 3. Xóa đề tài
    @DeleteMapping("/detai/{id}")
    public ResponseEntity<?> deleteDeTai(@PathVariable int id) {
        deTaiService.deleteDeTai(id);
        return ResponseEntity.ok(Map.of("message", "Xóa đề tài thành công"));
    }

    // 4. Xếp danh sách sinh viên với đề tài theo khóa học
    @PostMapping("/xepdanhSach")
    public ResponseEntity<?> xepDanhSach(@RequestParam("khoaHoc") String khoaHoc, Principal principal) {
        var user = nguoiDungService.getByUsername(principal.getName());
        var svList = nguoiDungService.getSinhVienByKhoaVaKhoaHoc(user.getKhoa(), khoaHoc);
        var deTaiList = deTaiService.getByKhoa(user.getKhoa());
        var giangVienList = nguoiDungService.getGiangVienByKhoa(user.getKhoa());

        boolean daXep = svList.stream().anyMatch(sv -> deTaiSinhVienService.isSinhVienDaXepDeTai(sv.getId()));
        if (daXep) {
            return ResponseEntity.badRequest().body(Map.of("message", "Khóa " + khoaHoc + " đã được xếp danh sách trước đó!"));
        }

        for (int i = 0; i < svList.size(); i++) {
            var sv = svList.get(i);
            var dt = deTaiList.get(i % deTaiList.size());
            var gv = giangVienList.get(i % giangVienList.size());
            deTaiSinhVienService.assign(sv.getId(), dt.getId());
            deTaiGVHuongDanService.assign(dt.getId(), gv.getId());
        }

        return ResponseEntity.ok(Map.of("message", "Đã xếp danh sách thành công cho khóa " + khoaHoc));
    }

    // 5. Lấy danh sách sinh viên theo khoa + khóa học
    @GetMapping("/sinhvien")
    public ResponseEntity<?> getSinhVien(@RequestParam String khoaHoc, Principal principal) {
        var user = nguoiDungService.getByUsername(principal.getName());
        var svList = nguoiDungService.getSinhVienByKhoaVaKhoaHoc(user.getKhoa(), khoaHoc);
        return ResponseEntity.ok(svList);
    }

    // 6. Lấy danh sách các hội đồng
    @GetMapping("/hoidong")
    public ResponseEntity<List<HoiDong>> getAllHoiDong() {
        return ResponseEntity.ok(hoiDongService.getAllHoiDong());
    }

    // 7. Giao đề tài cho hội đồng (assign)
    @PostMapping("/giaodetai/assign")
    public ResponseEntity<?> assignDeTaiHoiDong(@RequestParam int deTaiId,
                                                @RequestParam int hoiDongId) {
        if (!deTaiHoiDongService.isDeTaiAssigned(deTaiId)) {
            deTaiHoiDongService.assignHoiDong(deTaiId, hoiDongId);
            return ResponseEntity.ok(Map.of("message", "Giao đề tài thành công!"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Đề tài đã được giao hội đồng!"));
        }
    }

    // 8. Giao đề tài ngẫu nhiên cho hội đồng theo khóa học
    @PostMapping("/giaodetai/giao")
    public ResponseEntity<?> giaoDeTaiNgauNhien(@RequestParam String khoaHoc, Principal principal) {
        var user = nguoiDungService.getByUsername(principal.getName());

        var deTais = deTaiService.getByKhoa(user.getKhoa()).stream()
            .filter(dt -> {
                var dtsv = deTaiSinhVienService.findByDeTaiId(dt.getId());
                return dtsv != null && khoaHoc.equals(nguoiDungService.getById(dtsv.getSinhVienId()).getKhoaHoc());
            })
            .collect(Collectors.toList());

        var hoiDongs = hoiDongService.getAllHoiDong();
        int hdIndex = 0;

        for (var dt : deTais) {
            if (deTaiHoiDongService.isDeTaiAssigned(dt.getId()))
                continue;

            boolean assigned = false;
            for (int i = 0; i < hoiDongs.size(); i++) {
                var hd = hoiDongs.get(hdIndex % hoiDongs.size());
                var soLuongDeTai = deTaiHoiDongService.countDeTaiByHoiDongId(hd.getId());

                if (soLuongDeTai < 5) {
                    deTaiHoiDongService.assignHoiDong(dt.getId(), hd.getId());

                    var thanhVien = hoiDongService.getThanhVienHoiDong(hd.getId());
                    if (!thanhVien.isEmpty()) {
                        NguoiDung randomGv = thanhVien.stream()
                            .filter(tv -> "phan_bien".equals(tv.getRole()))
                            .findFirst()
                            .orElse(null);

                        if (randomGv != null) {
                            phanCongGiangVienPhanBienService.assignPhanBien(randomGv.getId(), hd.getId());
                        }
                    }

                    assigned = true;
                    hdIndex++;
                    break;
                }
            }

            if (!assigned) {
                return ResponseEntity.badRequest().body(Map.of("error", "Không đủ hội đồng để giao tất cả đề tài."));
            }
        }
        return ResponseEntity.ok(Map.of("message", "Đã giao đề tài ngẫu nhiên cho hội đồng."));
    }

    // 9. Khóa hội đồng (lock)
    @PostMapping("/khoahoidong")
    public ResponseEntity<?> khoaHoiDong(@RequestParam int hoiDongId) {
        deTaiHoiDongService.lockAllByHoiDongId(hoiDongId);

        var deTaiHoiDongs = deTaiHoiDongService.findByHoiDongId(hoiDongId);
        for (var dthd : deTaiHoiDongs) {
            int deTaiId = dthd.getDeTaiKhoaLuanId();
            var dtsv = deTaiSinhVienService.findByDeTaiId(deTaiId);
            if (dtsv != null) {
                int sinhVienId = dtsv.getSinhVienId();
                var sinhVien = nguoiDungService.getById(sinhVienId);

                Double diemTrungBinh = bangDiemService.tinhDiemTrungBinhByDeTaiId(deTaiId);
                if (sinhVien != null && sinhVien.getEmail() != null) {
                    String subject = "Thông báo điểm trung bình khoá luận";
                    String content = String.format(
                        "Chào %s,\n\nBạn đã hoàn thành bảo vệ khoá luận. Điểm trung bình chính thức của bạn do hội đồng chấm là: %.2f.\nVui lòng kiểm tra lại kết quả trên hệ thống.\n\nTrân trọng.",
                        sinhVien.getUsername(), diemTrungBinh != null ? diemTrungBinh : 0.0
                    );
                    emailService.sendEmail(sinhVien.getEmail(), subject, content);
                }
            }
        }

        return ResponseEntity.ok(Map.of("message", "Đã khóa hội đồng thành công và gửi email cho sinh viên!"));
    }

    // 10. Xuất bảng điểm hội đồng (PDF)
    @GetMapping("/xuat-bangdiem-hoidong/{hoiDongId}")
    public void xuatBangDiemTongHop(@PathVariable int hoiDongId, HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=bangdiem_hoidong_" + hoiDongId + ".pdf");

        List<BangDiemTongHopDTO> bangDiemList = bangDiemService.layBangDiemTongHopTheoHoiDong(hoiDongId);
        pdfExportService.exportBangDiemTongHop(bangDiemList, response.getOutputStream());
    }

    // 11. Xuất bảng điểm tất cả hội đồng (PDF)
    @GetMapping("/xuat-bangdiem-tatca")
    public void xuatTatCaBangDiem(HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=bangdiem_tatca.pdf");

        List<HoiDong> hoiDongs = hoiDongService.getAllHoiDong();
        List<BangDiemTongHopDTO> tongHop = new ArrayList<>();
        for (HoiDong hd : hoiDongs) {
            tongHop.addAll(bangDiemService.layBangDiemTongHopTheoHoiDong(hd.getId()));
        }
        pdfExportService.exportBangDiemTongHop(tongHop, response.getOutputStream());
    }
}
