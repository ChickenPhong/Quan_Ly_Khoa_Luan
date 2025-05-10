/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.services.impl;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.DeTaiKhoaLuan_SinhVien;
import com.tqp.repositories.DeTaiSinhVienRepository;
import com.tqp.services.DeTaiSinhVienService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeTaiSinhVienServiceImpl implements DeTaiSinhVienService{
    @Autowired
    private DeTaiSinhVienRepository repo;

    @Override
    public List<DeTaiKhoaLuan_SinhVien> getAll() {
        return repo.getAll();
    }

    @Override
    public DeTaiKhoaLuan_SinhVien getById(int id) {
        return repo.getById(id);
    }

    @Override
    public DeTaiKhoaLuan_SinhVien add(DeTaiKhoaLuan_SinhVien dtsv) {
        return repo.save(dtsv);
    }

    @Override
    public void delete(int id) {
        repo.delete(id);
    }
}
