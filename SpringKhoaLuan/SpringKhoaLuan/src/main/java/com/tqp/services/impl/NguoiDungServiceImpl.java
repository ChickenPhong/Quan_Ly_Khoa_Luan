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
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

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
    public boolean authenticate(String username, String rawPassword) {
        return nguoiDungRepo.authenticate(username, rawPassword);
    }

    // Method Spring Security sẽ gọi khi user submit login
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NguoiDung u = nguoiDungRepo.getByUsername(username);
        if (u == null)
            throw new UsernameNotFoundException("Không tìm thấy user");

        return new User(u.getUsername(), u.getPassword(), Collections.emptyList());
    }
}
