/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.services;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.PhanCongGiangVienPhanBien;
import java.util.List;

public interface PhanCongGiangVienPhanBienService {
    List<PhanCongGiangVienPhanBien> getAll();
    PhanCongGiangVienPhanBien getById(int id);
    PhanCongGiangVienPhanBien add(PhanCongGiangVienPhanBien p);
    void delete(int id);
    
    void addPhanBien(int deTaiId, int hoiDongId, int giangVienId);
    void assignPhanBien(int deTaiId, int giangVienId, int hoiDongId);
}
