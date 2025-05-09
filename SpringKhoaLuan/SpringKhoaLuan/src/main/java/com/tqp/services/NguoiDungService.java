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
import org.springframework.security.core.userdetails.UserDetailsService;

public interface NguoiDungService extends UserDetailsService{
    NguoiDung getByUsername(String username);
    NguoiDung addUser(NguoiDung user);
    boolean authenticate(String username, String rawPassword);
}
