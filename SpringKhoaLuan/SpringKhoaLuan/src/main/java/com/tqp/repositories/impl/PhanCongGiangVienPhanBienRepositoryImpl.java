/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.repositories.impl;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.PhanCongGiangVienPhanBien;
import com.tqp.repositories.PhanCongGiangVienPhanBienRepository;
import jakarta.persistence.Query;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PhanCongGiangVienPhanBienRepositoryImpl implements PhanCongGiangVienPhanBienRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<PhanCongGiangVienPhanBien> getAll() {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM PhanCongGiangVienPhanBien", PhanCongGiangVienPhanBien.class);
        return q.getResultList();
    }

    @Override
    public PhanCongGiangVienPhanBien getById(int id) {
        Session s = factory.getObject().getCurrentSession();
        return s.get(PhanCongGiangVienPhanBien.class, id);
    }

    @Override
    public PhanCongGiangVienPhanBien save(PhanCongGiangVienPhanBien p) {
        Session s = factory.getObject().getCurrentSession();
        s.saveOrUpdate(p);
        return p;
    }

    @Override
    public void delete(int id) {
        Session s = factory.getObject().getCurrentSession();
        PhanCongGiangVienPhanBien p = s.get(PhanCongGiangVienPhanBien.class, id);
        if (p != null)
            s.delete(p);
    }
}
