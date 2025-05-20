import { useState, useEffect } from "react";
import { Button, Form, Table, Alert } from "react-bootstrap";
import { authApis, endpoints } from "../../config/Apis";

const TieuChi = () => {
  const [tieuChiList, setTieuChiList] = useState([]);
  const [newTieuChi, setNewTieuChi] = useState("");
  const [msg, setMsg] = useState("");

  useEffect(() => {
    loadTieuChi();
  }, []);

  const loadTieuChi = async () => {
    try {
      const res = await authApis().get(endpoints.getTieuChi());
      setTieuChiList(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  const handleAdd = async (e) => {
    e.preventDefault();
    if (!newTieuChi) return;
    try {
      await authApis().post(endpoints.addTieuChi(), {
        tenTieuChi: newTieuChi, // hoặc trường đúng theo backend
      });
      setMsg("Thêm tiêu chí thành công!");
      setNewTieuChi("");
      loadTieuChi();
    } catch (err) {
      setMsg("Thêm tiêu chí thất bại.");
      console.error(err);
      if (err.response) {
        console.error('Lỗi phản hồi API:', err.response.data);
      } else {
        console.error('Lỗi khác:', err.message);
      }
    }
  };

  return (
    <div>
      <h3>Quản lý Tiêu chí</h3>
      {msg && <Alert variant="info">{msg}</Alert>}
      <Form onSubmit={handleAdd} className="mb-3">
        <Form.Control
          placeholder="Tên tiêu chí"
          value={newTieuChi}
          onChange={(e) => setNewTieuChi(e.target.value)}
        />
        <Button type="submit" className="mt-2">
          Thêm Tiêu chí
        </Button>
      </Form>

      <Table bordered hover>
        <thead>
          <tr>
            <th>ID</th>
            <th>Tên Tiêu chí</th>
            <th>Trạng thái</th>
            <th>Khoa</th>
          </tr>
        </thead>
        <tbody>
          {tieuChiList.map((tc) => (
            <tr key={tc.id}>
              <td>{tc.id}</td>
              <td>{tc.tenTieuChi}</td>
              <td>{tc.status}</td>
              <td>{tc.khoa}</td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};

export default TieuChi;
