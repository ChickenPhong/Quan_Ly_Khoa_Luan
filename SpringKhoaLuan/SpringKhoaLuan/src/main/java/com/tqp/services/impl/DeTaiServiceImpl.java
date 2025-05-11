/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.services.impl;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.DeTaiKhoaLuan;
import com.tqp.repositories.DeTaiRepository;
import com.tqp.services.DeTaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class DeTaiServiceImpl implements DeTaiService{
    @Autowired
    private DeTaiRepository deTaiRepo;

    @Override
    public List<DeTaiKhoaLuan> getAllDeTai() {
        return deTaiRepo.getAll();
    }

    @Override
    public DeTaiKhoaLuan getDeTaiById(int id) {
        return deTaiRepo.getById(id);
    }

    @Override
    public DeTaiKhoaLuan addDeTai(DeTaiKhoaLuan deTai) {
        return deTaiRepo.save(deTai);
    }

    @Override
    public boolean deleteDeTai(int id) {
        try {
            deTaiRepo.delete(id);  // Xóa theo id
            return true;  // Nếu thành công, trả về true
        } catch (Exception e) {
            return false;  // Nếu có lỗi, trả về false
        }
    }
}
