/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.repositories.impl;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.BangDiem;
import com.tqp.repositories.BangDiemRepository;
import jakarta.persistence.Query;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BangDiemRepositoryImpl implements BangDiemRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<BangDiem> getAll() {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM BangDiem", BangDiem.class);
        return q.getResultList();
    }

    @Override
    public BangDiem getById(int id) {
        Session s = factory.getObject().getCurrentSession();
        return s.get(BangDiem.class, id);
    }

    @Override
    public BangDiem save(BangDiem diem) {
        Session s = factory.getObject().getCurrentSession();
        s.saveOrUpdate(diem);
        return diem;
    }

    @Override
    public void delete(int id) {
        Session s = factory.getObject().getCurrentSession();
        BangDiem diem = s.get(BangDiem.class, id);
        if (diem != null)
            s.delete(diem);
    }
}
