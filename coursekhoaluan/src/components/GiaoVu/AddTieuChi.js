import React, { useState, useEffect } from "react";
import { Button, Form, Table, Alert } from "react-bootstrap";
import { authApis } from "../../config/Apis";

const AddTieuChi = () => {
  const [tieuChiList, setTieuChiList] = useState([]);
  const [newTieuChi, setNewTieuChi] = useState("");
  const [msg, setMsg] = useState("");

  // Load danh sách tiêu chí
  const loadTieuChi = async () => {
    try {
      const res = await authApis().get("/tieuchi");
      setTieuChiList(res.data);
      setMsg("");
    } catch (error) {
      setMsg("Lỗi tải danh sách tiêu chí: " + error.message);
    }
  };

  useEffect(() => {
    loadTieuChi();
  }, []);

  // Tạo tiêu chí mới
  const handleCreateTieuChi = async () => {
    if (!newTieuChi.trim()) {
      setMsg("Vui lòng nhập tên tiêu chí");
      return;
    }

    try {
      const res = await authApis().post("/tieuchi/add", {
        tenTieuChi: newTieuChi,
      });
      // res.data là tiêu chí mới tạo được backend trả về
      setTieuChiList([...tieuChiList, res.data]); // thêm vào danh sách
      setMsg("Tạo tiêu chí thành công");
      setNewTieuChi("");
    } catch (error) {
      setMsg("Tạo tiêu chí thất bại: " + (error.response?.data || error.message));
    }
  };

  return (
    <div className="container mt-3">
      <h3>Quản lý Tiêu chí</h3>

      {msg && <Alert variant="info">{msg}</Alert>}

      <Form
        onSubmit={(e) => {
          e.preventDefault();
          handleCreateTieuChi();
        }}
        className="mb-3"
      >
        <Form.Group>
          <Form.Label>Tên tiêu chí</Form.Label>
          <Form.Control
            type="text"
            value={newTieuChi}
            onChange={(e) => setNewTieuChi(e.target.value)}
            placeholder="Nhập tên tiêu chí"
          />
        </Form.Group>
        <Button variant="primary" type="submit" className="mt-2">
          Tạo tiêu chí
        </Button>
      </Form>

      <Table striped bordered hover>
        <thead>
          <tr>
            <th>ID</th>
            <th>Tên tiêu chí</th>
            <th>Người tạo</th>
            <th>Trạng thái</th>
            <th>Khoa</th>
          </tr>
        </thead>
        <tbody>
          {tieuChiList.length > 0 ? (
            tieuChiList.map((tc) => (
              <tr key={tc.id}>
                <td>{tc.id}</td>
                <td>{tc.tenTieuChi}</td>
                <td>{tc.createdBy}</td>
                <td>{tc.status}</td>
                <td>{tc.khoa}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="5" className="text-center">
                Chưa có tiêu chí nào
              </td>
            </tr>
          )}
        </tbody>
      </Table>
    </div>
  );
};

export default AddTieuChi;
