/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.services;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.DeTaiKhoaLuan_SinhVien;
import java.util.List;

public interface DeTaiSinhVienService {
    List<DeTaiKhoaLuan_SinhVien> getAll();
    DeTaiKhoaLuan_SinhVien getById(int id);
    DeTaiKhoaLuan_SinhVien add(DeTaiKhoaLuan_SinhVien dtsv);
    void delete(int id);
}
