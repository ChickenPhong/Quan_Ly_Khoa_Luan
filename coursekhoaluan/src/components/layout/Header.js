import { useContext, useState } from "react";
import { Button, Col, Container, Form, Image, Nav, Navbar, Row } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { MyDispatchContext, MyUserContext } from "../../config/Contexts";

const Header = () => {
    const nav = useNavigate();
    const [kw, setKw] = useState();
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatchContext);

    const search = (e) => {
        e.preventDefault();
        nav(`/?kw=${kw}`);
    }

    return (
        <Navbar expand="lg" className="bg-light border-bottom mb-3">
            <Container>
                <Navbar.Brand href="/">Khóa Luận OU</Navbar.Brand>
                <Navbar.Toggle />
                <Navbar.Collapse>
                    <Nav className="me-auto">
                        <Link to="/" className="nav-link">Trang chủ</Link>
                        {user === null ? <>
                            <Link to="/login" className="nav-link text-danger">Đăng nhập</Link>
                        </> : <>
                            <Link to="/" className="nav-link">
                                <Image src={user.avatar} className="rounded" width="40" /> Xin chào, {user.username}
                            </Link>
                            <Button onClick={() => dispatch({ type: "logout" })} variant="outline-danger">Đăng xuất</Button>
                        </>}
                    </Nav>
                    <Form onSubmit={search} className="d-flex">
                        <Form.Control value={kw} onChange={e => setKw(e.target.value)} placeholder="Tìm kiếm..." />
                        <Button type="submit" variant="secondary" className="ms-2">Tìm</Button>
                    </Form>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
};

export default Header;
