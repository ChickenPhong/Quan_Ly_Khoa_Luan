/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.services.impl;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.NguoiDung;
import com.tqp.repositories.NguoiDungRepository;
import com.tqp.services.NguoiDungService;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class NguoiDungServiceImpl implements NguoiDungService{
    @Autowired
    private NguoiDungRepository nguoiDungRepo;

    @Override
    public NguoiDung getByUsername(String username) {
        return nguoiDungRepo.getByUsername(username);
    }

    @Override
    public NguoiDung addUser(NguoiDung user) {
        return nguoiDungRepo.addUser(user);
    }

    @Override
    public NguoiDung addUser(Map<String, String> params, MultipartFile avatar) {
        NguoiDung u = new NguoiDung();
        u.setUsername(params.get("username"));
        u.setPassword(params.get("password")); // sẽ mã hóa ở repo
        u.setEmail(params.get("email")); // nếu có thêm trường email
        u.setRole("giaovu"); // hoặc ROLE_ADMIN nếu cần

        // Nếu bạn dùng avatar cloudinary thì xử lý upload tại đây (có thể để sau)
        return nguoiDungRepo.addUser(u);
    }

    @Override
    public boolean authenticate(String username, String rawPassword) {
        return nguoiDungRepo.authenticate(username, rawPassword);
    }

    // Method Spring Security sẽ gọi khi user submit login
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(">>> loadUserByUsername: " + username); // 👈 THÊM DÒNG NÀY
        NguoiDung u = nguoiDungRepo.getByUsername(username);
        if (u == null)
            throw new UsernameNotFoundException("Không tìm thấy user");

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + u.getRole())); // VD: ROLE_giaovu

        return new User(u.getUsername(), u.getPassword(), authorities);
    }
}
