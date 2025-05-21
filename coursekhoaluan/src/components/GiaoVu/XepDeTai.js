import React, { useState, useEffect } from "react";
import { Button, Table, Form, Alert } from "react-bootstrap";
import { authApis } from "../../config/Apis";

const XepDeTai = () => {
  const [khoaHocList, setKhoaHocList] = useState([]);          // Danh sách các khóa học
  const [selectedKhoaHoc, setSelectedKhoaHoc] = useState("");  // Khóa học đã chọn
  const [sinhVienList, setSinhVienList] = useState([]);        // Danh sách sinh viên lọc được
  const [msg, setMsg] = useState("");                          // Thông báo

  // 1. Load danh sách khóa học khi load trang
  useEffect(() => {
    const loadKhoaHoc = async () => {
      try {
        const res = await authApis().get("/giaovu/khoahoc");
        setKhoaHocList(res.data || []);
      } catch (error) {
        setMsg("Lỗi tải danh sách khóa học: " + error.message);
      }
    };
    loadKhoaHoc();
  }, []);

  // 2. Lọc danh sách sinh viên theo khóa học đã chọn
  const handleLocDanhSach = async (e) => {
    e.preventDefault();
    if (!selectedKhoaHoc) {
      setMsg("Vui lòng chọn khóa học");
      setSinhVienList([]);
      return;
    }
    setMsg("");
    try {
      // Ví dụ API: /giaovu/sinhvien?khoaHoc=2022
      const res = await authApis().get(`/giaovu/sinhvien?khoaHoc=${selectedKhoaHoc}`);
      setSinhVienList(res.data || []);
    } catch (error) {
      setMsg("Lỗi tải danh sách sinh viên: " + error.message);
      setSinhVienList([]);
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="text-center text-info mb-4">
        Xếp danh sách sinh viên thực hiện khóa luận
      </h2>

      {msg && <Alert variant="warning">{msg}</Alert>}

      <Form className="row mb-4" onSubmit={handleLocDanhSach}>
        <div className="col-md-4">
          <Form.Label>Chọn khóa</Form.Label>
          <Form.Select
            value={selectedKhoaHoc}
            onChange={(e) => setSelectedKhoaHoc(e.target.value)}
            required
          >
            <option value="">-- Chọn khóa học --</option>
            {khoaHocList.map((khoa) => (
              <option key={khoa} value={khoa}>
                Khóa {khoa}
              </option>
            ))}
          </Form.Select>
        </div>
        <div className="col-md-2 align-self-end">
          <Button className="btn btn-primary w-100" type="submit">
            Lọc danh sách
          </Button>
        </div>
      </Form>

      {/* Hiển thị bảng sinh viên nếu có danh sách */}
      {sinhVienList.length > 0 && (
        <div>
          <h5 className="mb-3">
            Danh sách sinh viên thuộc khoa Công nghệ thông tin - Khóa {selectedKhoaHoc}:
          </h5>
          <Table striped bordered hover>
            <thead>
              <tr>
                <th>#</th>
                <th>Tên đăng nhập</th>
                <th>Email</th>
              </tr>
            </thead>
            <tbody>
              {sinhVienList.map((sv, idx) => (
                <tr key={sv.id || idx}>
                  <td>{idx + 1}</td>
                  <td>{sv.username}</td>
                  <td>{sv.email}</td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      )}
    </div>
  );
};

export default XepDeTai;
