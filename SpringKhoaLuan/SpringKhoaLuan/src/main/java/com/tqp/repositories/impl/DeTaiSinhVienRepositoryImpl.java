/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.repositories.impl;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.DeTaiKhoaLuan_SinhVien;
import com.tqp.repositories.DeTaiSinhVienRepository;
import jakarta.persistence.Query;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class DeTaiSinhVienRepositoryImpl implements DeTaiSinhVienRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<DeTaiKhoaLuan_SinhVien> getAll() {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM DeTaiKhoaLuan_SinhVien", DeTaiKhoaLuan_SinhVien.class);
        return q.getResultList();
    }

    @Override
    public DeTaiKhoaLuan_SinhVien getById(int id) {
        Session s = factory.getObject().getCurrentSession();
        return s.get(DeTaiKhoaLuan_SinhVien.class, id);
    }

    @Override
    public DeTaiKhoaLuan_SinhVien save(DeTaiKhoaLuan_SinhVien dtsv) {
        Session s = factory.getObject().getCurrentSession();
        s.saveOrUpdate(dtsv);
        return dtsv;
    }

    @Override
    public void delete(int id) {
        Session s = factory.getObject().getCurrentSession();
        DeTaiKhoaLuan_SinhVien dtsv = s.get(DeTaiKhoaLuan_SinhVien.class, id);
        if (dtsv != null)
            s.delete(dtsv);
    }
}
