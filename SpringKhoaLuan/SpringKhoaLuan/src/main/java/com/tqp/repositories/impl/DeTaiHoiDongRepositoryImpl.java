/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.repositories.impl;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.DeTaiKhoaLuan_HoiDong;
import com.tqp.repositories.DeTaiHoiDongRepository;
import org.hibernate.query.Query;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class DeTaiHoiDongRepositoryImpl implements DeTaiHoiDongRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<DeTaiKhoaLuan_HoiDong> getAll() {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM DeTaiKhoaLuan_HoiDong", DeTaiKhoaLuan_HoiDong.class);
        return q.getResultList();
    }

    @Override
    public DeTaiKhoaLuan_HoiDong getById(int id) {
        Session s = factory.getObject().getCurrentSession();
        return s.get(DeTaiKhoaLuan_HoiDong.class, id);
    }

    @Override
    public DeTaiKhoaLuan_HoiDong save(DeTaiKhoaLuan_HoiDong dthd) {
        Session s = factory.getObject().getCurrentSession();
        s.saveOrUpdate(dthd);
        return dthd;
    }

    @Override
    public void delete(int id) {
        Session s = factory.getObject().getCurrentSession();
        DeTaiKhoaLuan_HoiDong dthd = s.get(DeTaiKhoaLuan_HoiDong.class, id);
        if (dthd != null)
            s.delete(dthd);
    }
    
    @Override
    public void assignHoiDong(int deTaiId, int hoiDongId) {
        Session session = sessionFactory.getCurrentSession();
        DeTaiKhoaLuan_HoiDong dtHd = new DeTaiKhoaLuan_HoiDong();
        dtHd.setDeTaiKhoaLuanId(deTaiId);
        dtHd.setHoiDongId(hoiDongId);
        session.save(dtHd);
    }

    @Override
    public boolean isDeTaiAssigned(int deTaiId) {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("SELECT COUNT(*) FROM DeTaiKhoaLuan_HoiDong WHERE deTaiKhoaLuanId=:id");
        q.setParameter("id", deTaiId);
        long count = (Long) q.getSingleResult();
        return count > 0;
    }
    
    @Override
    public DeTaiKhoaLuan_HoiDong findByDeTaiId(int deTaiId) {
        Session session = this.factory.getObject().getCurrentSession();
        Query<DeTaiKhoaLuan_HoiDong> q = 
            session.createQuery("FROM DeTaiKhoaLuan_HoiDong WHERE deTaiKhoaLuanId = :id", DeTaiKhoaLuan_HoiDong.class);
        q.setParameter("id", deTaiId);
        List<DeTaiKhoaLuan_HoiDong> result = q.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
    @Override
    public long countDeTaiByHoiDongId(int hoiDongId) {
        Session session = this.factory.getObject().getCurrentSession();
        Query<Long> q = session.createQuery("SELECT COUNT(*) FROM DeTaiKhoaLuan_HoiDong WHERE hoiDongId = :id", Long.class);
        q.setParameter("id", hoiDongId);
        return q.getSingleResult();
    }
}
