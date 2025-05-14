import { useContext, useEffect, useState } from "react";
import { Alert, Button, Card, Col, Row, Spinner } from "react-bootstrap";
import {useNavigate, useSearchParams } from "react-router-dom";
import { MyUserContext } from "../config/Contexts";
import MySpinner from "./layout/MySpinner";

const Home = () => {
    const user = useContext(MyUserContext);
    const [loading, setLoading] = useState(true);
    const [q] = useSearchParams();
    const nav = useNavigate();

    useEffect(() => {
        // Nếu chưa đăng nhập thì chuyển hướng sang /login
        if (!user) {
            nav("/login");
            return;
        }

        const timer = setTimeout(() => setLoading(false), 1000);
        return () => clearTimeout(timer);
    }, [q, user, nav]);

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
                            {user.role === "admin" && <Alert variant="info">Bạn là quản trị viên. Hãy vào mục <strong>Quản lý người dùng</strong> để cấp tài khoản.</Alert>}

                            {user.role === "giaovu" && <Alert variant="success">Bạn là giáo vụ. Hãy vào mục <strong>Khóa luận</strong> để tạo đề tài và phân công phản biện.</Alert>}

                            {user.role === "giangvien" && <Alert variant="warning">Bạn là giảng viên. Vui lòng vào mục <strong>Hội đồng</strong> để xem và chấm điểm khóa luận.</Alert>}

                            {user.role === "sinhvien" && <Alert variant="secondary">Bạn là sinh viên. Bạn có thể xem điểm khóa luận và lịch bảo vệ của mình.</Alert>}
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
        </>
    );
};

export default Home;
