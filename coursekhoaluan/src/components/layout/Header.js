import { useContext, useState } from "react";
import { Button, Container, Form, Image, Nav, Navbar } from "react-bootstrap";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { MyDispatchContext, MyUserContext } from "../../config/Contexts";

const Header = () => {
    const nav = useNavigate();
    const location = useLocation(); // Để biết đang ở đường dẫn nào
    const [kw, setKw] = useState("");
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatchContext);

    const search = (e) => {
        e.preventDefault();
        nav(`/?kw=${kw}`);
    };

    const isLoginPage = location.pathname === "/login"; // Kiểm tra nếu đang ở trang login

    return (
        <Navbar expand="lg" className="bg-light border-bottom mb-3">
            <Container>
                <Navbar.Brand as={Link} to="/">Khóa Luận OU</Navbar.Brand>
                <Navbar.Toggle />
                <Navbar.Collapse>
                    <Nav className="me-auto">
                        {/* Hiện "Trang chủ" ngoài trang login */}
                        {!isLoginPage && <Link to="/" className="nav-link">Trang chủ</Link>}

                        {/* Hiện "Đăng nhập" nếu người dùng chưa đăng nhập và không phải ở trang login */}
                        {!isLoginPage && user === null && (
                            <Link to="/login" className="nav-link text-danger">Đăng nhập</Link>
                        )}

                        {/* Hiển thị tên và avatar người dùng khi đã đăng nhập */}
                        {!isLoginPage && user !== null && (
                            <>
                                <Link to="/" className="nav-link d-flex align-items-center gap-2">
                                    <Image
                                        src={user?.avatar || "https://via.placeholder.com/40"}
                                        roundedCircle
                                        width={40}
                                        height={40}
                                        alt="Avatar"
                                    />
                                    <span>Xin chào, {user?.username}</span>
                                </Link>
                                <Button onClick={() => dispatch({ type: "logout" })} variant="outline-danger">
                                    Đăng xuất
                                </Button>
                            </>
                        )}
                    </Nav>

                    {/* Ẩn thanh tìm kiếm nếu đang ở trang login */}
                    {!isLoginPage && (
                        <Form onSubmit={search} className="d-flex">
                            <Form.Control
                                value={kw}
                                onChange={e => setKw(e.target.value)}
                                placeholder="Tìm kiếm..."
                            />
                            <Button type="submit" variant="secondary" className="ms-2">Tìm</Button>
                        </Form>
                    )}
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
};

export default Header;
