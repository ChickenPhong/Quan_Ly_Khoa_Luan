/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.services;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.NguoiDung;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

public interface NguoiDungService extends UserDetailsService{
    NguoiDung getByUsername(String username);
    NguoiDung addUser(NguoiDung user);
    NguoiDung addUser(Map<String, String> params, MultipartFile avatar);
    boolean authenticate(String username, String rawPassword);
}
