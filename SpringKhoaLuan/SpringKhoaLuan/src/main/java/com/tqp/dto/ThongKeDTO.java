/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tqp.dto;

/**
 *
 * @author Tran Quoc Phong
 */
public class ThongKeDTO {
    private String khoa; // Ngành
    private String khoaHoc; // Khóa (năm)
    private Long soLuongDeTai; // Tổng số đề tài
    private Double diemTrungBinh; // Điểm trung bình
    private Long soLuongSinhVien; // Số lượng sinh viên làm khóa luận

    // Constructors
    public ThongKeDTO() {}

    public ThongKeDTO(String khoa, String khoaHoc, Long soLuongDeTai, Double diemTrungBinh, Long soLuongSinhVien) {
        this.khoa = khoa;
        this.khoaHoc = khoaHoc;
        this.soLuongDeTai = soLuongDeTai;
        this.diemTrungBinh = diemTrungBinh;
        this.soLuongSinhVien = soLuongSinhVien;
    }

    // Getters & setters
    public String getkhoa() { return khoa; }
    public void setkhoa(String khoa) { this.khoa = khoa; }

    public String getkhoaHoc() { return khoaHoc; }
    public void setkhoaHoc(String khoaHoc) { this.khoaHoc = khoaHoc; }

    public Long getSoLuongDeTai() { return soLuongDeTai; }
    public void setSoLuongDeTai(Long soLuongDeTai) { this.soLuongDeTai = soLuongDeTai; }

    public Double getDiemTrungBinh() { return diemTrungBinh; }
    public void setDiemTrungBinh(Double diemTrungBinh) { this.diemTrungBinh = diemTrungBinh; }

    public Long getSoLuongSinhVien() { return soLuongSinhVien; }
    public void setSoLuongSinhVien(Long soLuongSinhVien) { this.soLuongSinhVien = soLuongSinhVien; }
}
