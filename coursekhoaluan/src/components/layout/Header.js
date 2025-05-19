import { useContext } from "react";
import { Button, Container, Image, Nav, Navbar as RBNavbar } from "react-bootstrap";
import { Link, useLocation } from "react-router-dom";
import { MyDispatchContext, MyUserContext } from "../../config/Contexts";

const Header = () => {
    const location = useLocation();
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatchContext);

    const isLoginPage = location.pathname === "/login";

    return (
        <RBNavbar bg="dark" variant="dark" expand="lg" className="mb-3">
            <Container>
                <RBNavbar.Brand as={Link} to="/">Quản lý Khóa Luận</RBNavbar.Brand>
                {!isLoginPage && (
                    <>
                        <RBNavbar.Toggle aria-controls="basic-navbar-nav" />
                        <RBNavbar.Collapse id="basic-navbar-nav">
                            <Nav className="me-auto">
                                <Nav.Link as={Link} to="/">Trang chủ</Nav.Link>

                                {/* Menu cho giáo vụ */}
                                {user?.role === "ROLE_GIAOVU" && (
                                    <>
                                        <Nav.Link as={Link} to="/giaovu/tieuchi">Tiêu chí</Nav.Link>
                                        <Nav.Link as={Link} to="/giaovu/xep_detai">Xếp Đề tài</Nav.Link>
                                        <Nav.Link as={Link} to="/giaovu/danhsach_thuchien">Danh sách thực hiện</Nav.Link>
                                        <Nav.Link as={Link} to="/giaovu/hoidong">Hội đồng</Nav.Link>
                                        <Nav.Link as={Link} to="/giaovu/giaodetai">Giao đề tài</Nav.Link>
                                        <Nav.Link as={Link} to="/giaovu/khoa_hoidong">Khóa Hội đồng</Nav.Link>
                                    </>
                                )}

                                {/* Menu cho admin */}
                                {user?.role === "ROLE_ADMIN" && (
                                    <>
                                        <Nav.Link as={Link} to="/admin/add-user">Thêm người dùng</Nav.Link>
                                        
                                    </>
                                )}

                                {/* Menu cho giáo vụ và admin chung */}
                                {(user?.role === "ROLE_ADMIN" || user?.role === "ROLE_GIAOVU") && (
                                    <Nav.Link as={Link} to="/thongke">Thống kê</Nav.Link>
                                )}

                                {/* Menu cho giảng viên */}
                                {user?.role === "ROLE_GIANGVIEN" && (
                                    <Nav.Link
                                        as={Link}
                                        to={`/giangvien/chamdiem?giangVienPhanBienId=${user.id}`}
                                    >
                                        Chấm điểm
                                    </Nav.Link>
                                )}

                                {/* Thông tin cá nhân chung cho tất cả */}
                                <Nav.Link as={Link} to="/profile">Thông tin cá nhân</Nav.Link>
                            </Nav>

                            {/* Phần đăng nhập/đăng xuất bên phải */}
                            <Nav className="d-flex align-items-center gap-3">
                                {user === null ? (
                                    <Nav.Link as={Link} to="/login" className="text-danger">
                                        Đăng nhập
                                    </Nav.Link>
                                ) : (
                                    <>
                                        <div className="d-flex align-items-center gap-2 text-white navbar-text">
                                            <Image
                                                src={user.avatar || "https://via.placeholder.com/40"}
                                                roundedCircle
                                                width={40}
                                                height={40}
                                                alt="Avatar"
                                            />
                                            <span title={user.username}>Xin chào, {user.username}</span>
                                        </div>
                                        <Button
                                            variant="outline-danger"
                                            size="sm"
                                            onClick={() => dispatch({ type: "logout" })}
                                        >
                                            Đăng xuất
                                        </Button>
                                    </>
                                )}
                            </Nav>
                        </RBNavbar.Collapse>
                    </>
                )}
            </Container>
        </RBNavbar>
    );
};

export default Header;
