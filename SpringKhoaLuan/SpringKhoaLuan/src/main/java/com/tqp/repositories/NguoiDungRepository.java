/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.repositories;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.NguoiDung;

public interface NguoiDungRepository {
    NguoiDung getByUsername(String username);
    NguoiDung addUser(NguoiDung u);
    boolean authenticate(String username, String rawPassword);
}
