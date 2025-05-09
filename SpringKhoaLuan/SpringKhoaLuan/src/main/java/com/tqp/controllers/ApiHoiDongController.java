/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.controllers;

/**
 *
 * @author Tran Quoc Phong
 */
import com.tqp.pojo.HoiDong;
import com.tqp.services.HoiDongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hoidong")
public class ApiHoiDongController {
    @Autowired
    private HoiDongService hoiDongService;

    @GetMapping("/")
    public ResponseEntity<List<HoiDong>> getAllHoiDong() {
        return ResponseEntity.ok(hoiDongService.getAllHoiDong());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HoiDong> getHoiDongById(@PathVariable int id) {
        return ResponseEntity.ok(hoiDongService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity<HoiDong> createHoiDong(@RequestBody HoiDong hd) {
        return ResponseEntity.ok(hoiDongService.addHoiDong(hd));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHoiDong(@PathVariable int id) {
        hoiDongService.deleteHoiDong(id);
        return ResponseEntity.ok().build();
    }
}
