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
import com.tqp.services.DeTaiService;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class DeTaiServiceImpl implements DeTaiService{
    @Override
    public List<DeTaiKhoaLuan> getAllDeTais() {
        // Tạm thời mock dữ liệu, sau này bạn gắn Hibernate/JPA
        List<DeTaiKhoaLuan> list = new ArrayList<>();
        list.add(new DeTaiKhoaLuan(1, "Khóa luận về AI"));
        list.add(new DeTaiKhoaLuan(2, "Nghiên cứu Spring Boot"));
        return list;
    }
}
