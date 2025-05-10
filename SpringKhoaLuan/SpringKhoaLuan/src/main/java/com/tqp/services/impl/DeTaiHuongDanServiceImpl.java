/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.services.impl;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.DeTaiKhoaLuan_GiangVienHuongDan;
import com.tqp.repositories.DeTaiHuongDanRepository;
import com.tqp.services.DeTaiHuongDanService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeTaiHuongDanServiceImpl implements DeTaiHuongDanService{
    @Autowired
    private DeTaiHuongDanRepository repo;

    @Override
    public List<DeTaiKhoaLuan_GiangVienHuongDan> getAll() {
        return repo.getAll();
    }

    @Override
    public DeTaiKhoaLuan_GiangVienHuongDan getById(int id) {
        return repo.getById(id);
    }

    @Override
    public DeTaiKhoaLuan_GiangVienHuongDan add(DeTaiKhoaLuan_GiangVienHuongDan d) {
        return repo.save(d);
    }

    @Override
    public void delete(int id) {
        repo.delete(id);
    }
}
