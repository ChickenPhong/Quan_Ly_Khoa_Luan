/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.services.impl;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.PhanCongGiangVienPhanBien;
import com.tqp.repositories.PhanCongGiangVienPhanBienRepository;
import com.tqp.services.PhanCongGiangVienPhanBienService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhanCongGiangVienPhanBienServiceImpl implements PhanCongGiangVienPhanBienService{
    @Autowired
    private PhanCongGiangVienPhanBienRepository repo;

    @Override
    public List<PhanCongGiangVienPhanBien> getAll() {
        return repo.getAll();
    }

    @Override
    public PhanCongGiangVienPhanBien getById(int id) {
        return repo.getById(id);
    }

    @Override
    public PhanCongGiangVienPhanBien add(PhanCongGiangVienPhanBien p) {
        return repo.save(p);
    }

    @Override
    public void delete(int id) {
        repo.delete(id);
    }
}
