/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.services.impl;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.BangDiem;
import com.tqp.repositories.BangDiemRepository;
import com.tqp.services.BangDiemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BangDiemServiceImpl implements BangDiemService{
    @Autowired
    private BangDiemRepository bangDiemRepo;

    @Override
    public List<BangDiem> getAll() {
        return bangDiemRepo.getAll();
    }

    @Override
    public BangDiem getById(int id) {
        return bangDiemRepo.getById(id);
    }

    @Override
    public BangDiem add(BangDiem diem) {
        return bangDiemRepo.save(diem);
    }

    @Override
    public void delete(int id) {
        bangDiemRepo.delete(id);
    }
}
