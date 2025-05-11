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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.tqp.services.NguoiDungService;
import com.tqp.services.DeTaiService;
import com.tqp.pojo.DeTaiKhoaLuan;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class NguoiDungController {
    @Autowired
    private NguoiDungService nguoiDungService;
    
    @Autowired
    private DeTaiService deTaiService;
    
    // Giao diện đăng nhập
    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    // Trang quản trị dành cho admin
    @GetMapping("/admin")
    public String adminView(Model model) {
        model.addAttribute("users", nguoiDungService.getAllUsers());
        return "admin"; // Trả về admin.html
    }
    
    // Giao diện dành cho GIAOVU
    @GetMapping("/khoaluan")
    public String giaoVuView(Model model) {
        model.addAttribute("khoaLuan", new DeTaiKhoaLuan()); // <- DeTai là entity của bạn
        model.addAttribute("allDeTai", deTaiService.getAllDeTai()); // Truyền danh sách đề tài vào model
        return "khoaluan";
    }


    // Giao diện dành cho GIANGVIEN
    @GetMapping("/detai")
    public String giangVienView() {
        return "detai"; // Nhớ tạo thêm file detai.html nếu chưa có
    }
    
    @GetMapping("/admin/add-user")
    public String addUserForm() {
        return "add-user"; // Trả về file add-user.html để hiển thị form
    }

    @PostMapping("/admin/add-user")
    public String addUser(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("email") String email,
                          @RequestParam("role") String role,
                          @RequestParam("avatar") MultipartFile avatar,
                          RedirectAttributes redirectAttrs) {
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("email", email);
        params.put("role", role);
        
        nguoiDungService.addUser(params, avatar);

        redirectAttrs.addFlashAttribute("message", "Thêm người dùng thành công!");
        return "redirect:/admin";
    }
    
    @PostMapping("/admin/delete-user")
    public String deleteUser(@RequestParam("userId") int id, RedirectAttributes redirectAttrs) {
        boolean result = nguoiDungService.deleteUser(id);
        if (result)
            redirectAttrs.addFlashAttribute("message", "Xóa người dùng thành công!");
        else
            redirectAttrs.addFlashAttribute("message", "Xóa người dùng thất bại!");
        return "redirect:/admin";
    }
    
    @PostMapping("/khoaluan/add")
    public String addDeTai(@ModelAttribute("khoaLuan") DeTaiKhoaLuan khoaLuan) {
        deTaiService.addDeTai(khoaLuan);  // Bạn cần inject service này
        return "redirect:/khoaluan";  // Redirect về danh sách
    }
    
     // Xóa đề tài
    @PostMapping("/khoaluan/delete")
    public String deleteDeTai(@RequestParam("id") int id, RedirectAttributes redirectAttrs) {
        boolean result = deTaiService.deleteDeTai(id);
        if (result) {
            redirectAttrs.addFlashAttribute("message", "Xóa đề tài thành công!");
        } else {
            redirectAttrs.addFlashAttribute("message", "Xóa đề tài thất bại!");
        }
        return "redirect:/khoaluan";  // Quay lại trang danh sách đề tài
    }
    
    public String showAddUserForm() {
        return "add-user"; // Trả về file add-user.html
    }
}


