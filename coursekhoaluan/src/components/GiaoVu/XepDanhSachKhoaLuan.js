import { useState, useEffect } from "react";
import { Form, Button, Table, Alert } from "react-bootstrap";
import { authApis, endpoints } from "../../config/Apis";

const XepDanhSachKhoaLuan = () => {
  const [yearOptions, setYearOptions] = useState([]);
  const [selectedKhoaHoc, setSelectedKhoaHoc] = useState("");
  const [sinhViens, setSinhViens] = useState([]);
  const [message, setMessage] = useState("");

  useEffect(() => {
    const fetchKhoaHoc = async () => {
      try {
        const res = await authApis().get(endpoints.getKhoaHocList());
        setYearOptions(res.data);
      } catch (err) {
        console.error(err);
      }
    };
    fetchKhoaHoc();
  }, []);

  // Gọi API lấy danh sách sinh viên theo khóa học sau khi bấm nút
  const handleFilter = async () => {
    if (!selectedKhoaHoc) {
      alert("Vui lòng chọn khóa học");
      return;
    }
    try {
      const res = await authApis().get(endpoints.getSinhVienByKhoaHoc(selectedKhoaHoc));
      setSinhViens(res.data);
    } catch (err) {
      console.error(err);
      setSinhViens([]);
    }
  };

  // Xếp danh sách đề tài cho sinh viên
  const handleXepDanhSach = async () => {
    if (!selectedKhoaHoc) {
      alert("Vui lòng chọn khóa học");
      return;
    }
    if (sinhViens.length === 0) {
      alert("Chưa có sinh viên để xếp đề tài");
      return;
    }
    try {
      const res = await authApis().post(endpoints.xepDeTai(selectedKhoaHoc));
      setMessage(res.data);
    } catch (err) {
      setMessage(err.response?.data || "Lỗi khi xếp danh sách.");
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="text-center text-info mb-4">Xếp danh sách sinh viên thực hiện khóa luận</h2>

      <Form.Group className="mb-3" controlId="formKhoaHoc">
        <Form.Label>Chọn khóa học</Form.Label>
        <Form.Select
          value={selectedKhoaHoc}
          onChange={(e) => setSelectedKhoaHoc(e.target.value)}
        >
          <option value="">-- Chọn khóa học --</option>
          {yearOptions.map((year) => (
            <option key={year} value={year}>
              Khóa {year}
            </option>
          ))}
        </Form.Select>
      </Form.Group>

      <Button variant="primary" onClick={handleFilter}>
        Lọc danh sách
      </Button>

      {sinhViens.length > 0 && (
        <>
          <h5 className="mt-3">Danh sách sinh viên khóa {selectedKhoaHoc}:</h5>
          <Table bordered striped>
            <thead>
              <tr>
                <th>#</th>
                <th>Tên đăng nhập</th>
                <th>Email</th>
              </tr>
            </thead>
            <tbody>
              {sinhViens.map((sv, idx) => (
                <tr key={sv.id || idx}>
                  <td>{idx + 1}</td>
                  <td>{sv.username}</td>
                  <td>{sv.email}</td>
                </tr>
              ))}
            </tbody>
          </Table>

          <Button variant="success" onClick={handleXepDanhSach}>
            Xếp danh sách
          </Button>
        </>
      )}

      {message && <Alert className="mt-3" variant="info">{message}</Alert>}
    </div>
  );
};

export default XepDanhSachKhoaLuan;
