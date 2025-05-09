/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.repositories.impl;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.ThanhVienHoiDong;
import com.tqp.repositories.ThanhVienHoiDongRepository;
import jakarta.persistence.Query;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional

public class ThanhVienHoiDongRepositoryImpl implements ThanhVienHoiDongRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<ThanhVienHoiDong> getAll() {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM ThanhVienHoiDong", ThanhVienHoiDong.class);
        return q.getResultList();
    }

    @Override
    public ThanhVienHoiDong getById(int id) {
        Session s = factory.getObject().getCurrentSession();
        return s.get(ThanhVienHoiDong.class, id);
    }

    @Override
    public ThanhVienHoiDong save(ThanhVienHoiDong tv) {
        Session s = factory.getObject().getCurrentSession();
        s.saveOrUpdate(tv);
        return tv;
    }

    @Override
    public void delete(int id) {
        Session s = factory.getObject().getCurrentSession();
        ThanhVienHoiDong tv = s.get(ThanhVienHoiDong.class, id);
        if (tv != null)
            s.delete(tv);
    }
}
