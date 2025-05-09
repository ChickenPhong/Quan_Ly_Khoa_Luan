/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.repositories;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.DeTaiKhoaLuan;
import java.util.List;

public interface DeTaiRepository {
    List<DeTaiKhoaLuan> getAll();
    DeTaiKhoaLuan getById(int id);
    DeTaiKhoaLuan save(DeTaiKhoaLuan deTai);
    void delete(int id);
}
