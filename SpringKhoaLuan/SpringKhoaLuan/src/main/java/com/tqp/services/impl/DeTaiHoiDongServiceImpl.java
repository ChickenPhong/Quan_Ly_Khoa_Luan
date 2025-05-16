/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.services.impl;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.DeTaiKhoaLuan_HoiDong;
import com.tqp.repositories.DeTaiHoiDongRepository;
import com.tqp.services.DeTaiHoiDongService;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class DeTaiHoiDongServiceImpl implements DeTaiHoiDongService{
    @Autowired
    private DeTaiHoiDongRepository repo;
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<DeTaiKhoaLuan_HoiDong> getAll() {
        return repo.getAll();
    }

    @Override
    public DeTaiKhoaLuan_HoiDong getById(int id) {
        return repo.getById(id);
    }

    @Override
    public DeTaiKhoaLuan_HoiDong add(DeTaiKhoaLuan_HoiDong dthd) {
        return repo.save(dthd);
    }

    @Override
    public void delete(int id) {
        repo.delete(id);
    }
    
    @Override
    public void assignHoiDong(int deTaiId, int hoiDongId) {
        repo.assignHoiDong(deTaiId, hoiDongId);
    }

    @Override
    public boolean isDeTaiAssigned(int deTaiId) {
        return repo.isDeTaiAssigned(deTaiId);
    }
    
    @Override
    public DeTaiKhoaLuan_HoiDong findByDeTaiId(int deTaiId) {
        return repo.findByDeTaiId(deTaiId);
    }
    
    @Override
    public long countDeTaiByHoiDongId(int hoiDongId) {
        return repo.countDeTaiByHoiDongId(hoiDongId);
    }
    
     @Override
    public List<DeTaiKhoaLuan_HoiDong> findByHoiDongId(int hoiDongId) {
        Session s = factory.getObject().getCurrentSession();
        String hql = "FROM DeTaiKhoaLuan_HoiDong WHERE hoiDong_id = :hoiDongId";
        Query q = s.createQuery(hql, DeTaiKhoaLuan_HoiDong.class);
        q.setParameter("hoiDongId", hoiDongId);
        return q.getResultList();
    }
}
