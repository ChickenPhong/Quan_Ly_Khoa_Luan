/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.services;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.BangDiem;
import java.util.List;

public interface BangDiemService {
    List<BangDiem> getAll();
    BangDiem getById(int id);
    BangDiem add(BangDiem diem);
    void delete(int id);
    
    BangDiem findByDeTaiIdAndGiangVienIdAndTieuChi(int deTaiId, int giangVienId, String tieuChi);
}
