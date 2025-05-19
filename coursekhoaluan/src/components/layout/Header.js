import { useContext } from "react";
import { Button, Container, Image, Navbar } from "react-bootstrap";
import { Link, useLocation } from "react-router-dom";
import { MyDispatchContext, MyUserContext } from "../../config/Contexts";

const Header = () => {
    const location = useLocation();
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatchContext);

    const isLoginPage = location.pathname === "/login";

    return (
        <Navbar expand="lg" className="bg-light border-bottom mb-3">
            <Container className="justify-content-between">
                <div className="d-flex align-items-center gap-3">
                    <Navbar.Brand as={Link} to="/">Khóa Luận OU</Navbar.Brand>
                    {!isLoginPage && <Link to="/" className="nav-link">Trang chủ</Link>}

                    {/* ✅ Nếu là admin thì hiện menu Quản lý người dùng */}
                    {!isLoginPage && user?.role === "ROLE_ADMIN" && (
                        <Link to="/admin/users" className="nav-link">Quản lý người dùng</Link>
                    )}
                </div>

                {!isLoginPage && (
                    <div className="d-flex align-items-center gap-3">
                        {user === null ? (
                            <Link to="/login" className="nav-link text-danger">Đăng nhập</Link>
                        ) : (
                            <>
                                <div className="d-flex align-items-center gap-2">
                                    <Image
                                        src={user?.avatar || "https://via.placeholder.com/40"}
                                        roundedCircle
                                        width={40}
                                        height={40}
                                        alt="Avatar"
                                    />
                                    <span>Xin chào, {user?.username}</span>
                                </div>
                                <Button onClick={() => dispatch({ type: "logout" })} variant="outline-danger">
                                    Đăng xuất
                                </Button>
                            </>
                        )}
                    </div>
                )}
            </Container>
        </Navbar>
    );
};

export default Header;
