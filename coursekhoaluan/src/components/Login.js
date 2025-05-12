import { useContext, useState } from "react";
import { Alert, Button, Form } from "react-bootstrap";
import Apis, { authApis, endpoints } from "../../config/Apis";
import { useNavigate } from "react-router-dom";
import cookie from "react-cookies";
import { MyDispatchContext } from "../../config/Contexts";
import MySpinner from "../layout/MySpinner";

const Login = () => {
    const [user, setUser] = useState({});
    const [msg, setMsg] = useState();
    const [loading, setLoading] = useState(false);
    const nav = useNavigate();
    const dispatch = useContext(MyDispatchContext);

    const login = async (e) => {
        e.preventDefault();
        try {
            setLoading(true);
            let res = await Apis.post(endpoints["login"], user);
            cookie.save("token", res.data.token);
            let u = await authApis().get(endpoints["current-user"]);
            dispatch({ type: "login", payload: u.data });
            nav("/");
        } catch (ex) {
            console.error(ex);
            setMsg("Đăng nhập thất bại!");
        } finally {
            setLoading(false);
        }
    };

    return (
        <>
            <h1 className="text-center text-primary">ĐĂNG NHẬP</h1>
            {msg && <Alert variant="danger">{msg}</Alert>}
            <Form onSubmit={login}>
                <Form.Group className="mb-3">
                    <Form.Control type="text" placeholder="Tên đăng nhập"
                        value={user.username || ""} onChange={e => setUser({ ...user, username: e.target.value })} required />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Control type="password" placeholder="Mật khẩu"
                        value={user.password || ""} onChange={e => setUser({ ...user, password: e.target.value })} required />
                </Form.Group>
                {loading ? <MySpinner /> : <Button type="submit" variant="primary">Đăng nhập</Button>}
            </Form>
        </>
    );
};

export default Login;
