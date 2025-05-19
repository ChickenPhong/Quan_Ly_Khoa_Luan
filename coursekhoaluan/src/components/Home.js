import { useContext, useEffect, useState } from "react";
import { Alert, Button, Card, Col, Row, Table, Image } from "react-bootstrap";
import { useNavigate, useSearchParams } from "react-router-dom";
import { MyUserContext } from "../config/Contexts";
import MySpinner from "./layout/MySpinner";
import { authApis, endpoints } from "../config/Apis";  // Sửa chỗ này, import authApis

const Home = () => {
    const user = useContext(MyUserContext);
    const [loading, setLoading] = useState(true);
    const [users, setUsers] = useState([]);
    const [q] = useSearchParams();
    const nav = useNavigate();

    useEffect(() => {
        if (!user) {
            nav("/login");
            return;
        }

        const fetchData = async () => {
            if (user.role === "ROLE_ADMIN") {
                try {
                    const res = await authApis().get(endpoints["get-users"]); // gọi authApis()
                    setUsers(res.data);
                } catch (err) {
                    console.error(err);
                }
            }
            setLoading(false);
        };

        fetchData();
    }, [q, user, nav]);

    const deleteUser = async (id) => {
        if (!window.confirm("Bạn có chắc chắn muốn xóa người dùng này?")) return;

        try {
            const formData = new FormData();
            formData.append("userId", id);
            await authApis().post(endpoints["delete-user"], formData); // gọi authApis()
            // Reload danh sách sau khi xóa
            const res = await authApis().get(endpoints["get-users"]);
            setUsers(res.data);
        } catch (err) {
            console.error(err);
        }
    };

    if (!user)
        return <Alert variant="danger">Bạn chưa đăng nhập!</Alert>;

    if (loading)
        return <MySpinner />;

    return (
        <>
            <h1 className="text-primary">Chào mừng {user.lastName} {user.firstName}</h1>

            <Row className="mt-4">
                <Col md={6}>
                    <Card border="info">
                        <Card.Body>
                            <Card.Title>Thông tin vai trò</Card.Title>
                            {user.role === "ROLE_ADMIN" && <Alert variant="info">Bạn là quản trị viên. Hãy vào mục <strong>Quản lý người dùng</strong> để cấp tài khoản.</Alert>}
                            {user.role === "ROLE_GIAOVU" && <Alert variant="success">Bạn là giáo vụ. Hãy vào mục <strong>Khóa luận</strong> để tạo đề tài và phân công phản biện.</Alert>}
                            {user.role === "ROLE_GIANGVIEN" && <Alert variant="warning">Bạn là giảng viên. Vui lòng vào mục <strong>Hội đồng</strong> để xem và chấm điểm khóa luận.</Alert>}
                            {user.role === "ROLE_SINHVIEN" && <Alert variant="secondary">Bạn là sinh viên. Bạn có thể xem điểm khóa luận và lịch bảo vệ của mình.</Alert>}
                        </Card.Body>
                    </Card>
                </Col>

                <Col md={6}>
                    <Card border="light">
                        <Card.Body>
                            <Card.Title>Hướng dẫn nhanh</Card.Title>
                            <p>- Sử dụng thanh điều hướng trên cùng để truy cập chức năng.</p>
                            <p>- Vai trò của bạn sẽ quyết định bạn được truy cập vào phần nào.</p>
                            <p>- Hệ thống sẽ gửi thông báo khi có cập nhật điểm hoặc hội đồng mới.</p>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>

            {/* Nếu là ADMIN thì hiển thị bảng danh sách */}
            {user.role === "ROLE_ADMIN" && (
                <div className="mt-5">
                    <h4>Danh sách người dùng</h4>
                    <Table striped bordered hover responsive>
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Tên đăng nhập</th>
                                <th>Email</th>
                                <th>Vai trò</th>
                                <th>Mật khẩu (mã hóa)</th>
                                <th>Avatar</th>
                                <th>Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            {users.map((u, idx) => (
                                <tr key={u.id}>
                                    <td>{idx + 1}</td>
                                    <td>{u.username}</td>
                                    <td>{u.email}</td>
                                    <td>{u.role}</td>
                                    <td>
                                        <input
                                            className="form-control form-control-sm"
                                            readOnly
                                            value={u.password}
                                        />
                                    </td>
                                    <td>
                                        {u.avatar ? (
                                            <Image src={u.avatar} width={50} height={50} roundedCircle />
                                        ) : (
                                            <span>No avatar</span>
                                        )}
                                    </td>
                                    <td>
                                        <Button
                                            size="sm"
                                            variant="danger"
                                            onClick={() => deleteUser(u.id)}
                                        >
                                            Xóa
                                        </Button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                </div>
            )}
        </>
    );
};

export default Home;
