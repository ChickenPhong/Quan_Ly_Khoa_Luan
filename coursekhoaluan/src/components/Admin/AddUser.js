import { useEffect, useState } from "react";
import { Button, Form, Image, Table } from "react-bootstrap";
import { authApis, endpoints } from "../../config/Apis";  // sửa import

const AddUser = () => {
    const [users, setUsers] = useState([]);
    const [form, setForm] = useState({
        username: "",
        password: "",
        email: "",
        role: "ROLE_ADMIN",
        khoa: "",
        khoaHoc: "",
        avatar: null
    });

    const loadUsers = async () => {
        try {
            const res = await authApis().get(endpoints["get-users"]); // gọi authApis()
            setUsers(res.data);
        } catch (err) {
            console.error(err);
        }
    };

    useEffect(() => {
        loadUsers();
    }, []);

    const handleChange = (e) => {
        const { name, value, files } = e.target;
        if (files) {
            setForm({ ...form, [name]: files[0] });
        } else {
            setForm({ ...form, [name]: value });
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData = new FormData();
        for (let key in form) {
            if (form[key]) formData.append(key, form[key]);
        }
        try {
            await authApis().post(endpoints["add-user"], formData); // gọi authApis()
            loadUsers();
        } catch (err) {
            console.error(err);
        }
    };

    const deleteUser = async (id) => {
        if (!window.confirm("Bạn có chắc muốn xóa?")) return;

        try {
            // Gọi DELETE method tới /users/{id}
            await authApis().delete(`users/${id}`);

            // Tải lại danh sách người dùng
            loadUsers();
        } catch (err) {
            console.error("Lỗi khi xóa người dùng:", err);
        }
    };

    return (
        <div className="container mt-4">
            <h2>Thêm người dùng mới</h2>
            <Form onSubmit={handleSubmit}>
                <Form.Group className="mb-2">
                    <Form.Label>Tên đăng nhập</Form.Label>
                    <Form.Control name="username" onChange={handleChange} required />
                </Form.Group>
                <Form.Group className="mb-2">
                    <Form.Label>Mật khẩu</Form.Label>
                    <Form.Control type="password" name="password" onChange={handleChange} required />
                </Form.Group>
                <Form.Group className="mb-2">
                    <Form.Label>Email</Form.Label>
                    <Form.Control name="email" onChange={handleChange} />
                </Form.Group>
                <Form.Group className="mb-2">
                    <Form.Label>Vai trò</Form.Label>
                    <Form.Select name="role" onChange={handleChange}>
                        <option value="ROLE_ADMIN">Admin</option>
                        <option value="ROLE_GIAOVU">Giáo vụ</option>
                        <option value="ROLE_GIANGVIEN">Giảng viên</option>
                        <option value="ROLE_SINHVIEN">Sinh viên</option>
                    </Form.Select>
                </Form.Group>
                <Form.Group className="mb-2">
                    <Form.Label>Khoa</Form.Label>
                    <Form.Control name="khoa" onChange={handleChange} />
                </Form.Group>
                <Form.Group className="mb-2">
                    <Form.Label>Khóa học</Form.Label>
                    <Form.Control name="khoaHoc" onChange={handleChange} />
                </Form.Group>
                <Form.Group className="mb-2">
                    <Form.Label>Avatar</Form.Label>
                    <Form.Control type="file" name="avatar" onChange={handleChange} />
                </Form.Group>
                <Button type="submit">Thêm</Button>
            </Form>

            <h4 className="mt-5">Danh sách người dùng</h4>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Tên đăng nhập</th>
                        <th>Email</th>
                        <th>Vai trò</th>
                        <th>Mật khẩu</th>
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
                                <Form.Control size="sm" readOnly value={u.password} />
                            </td>
                            <td>
                                {u.avatar ? (
                                    <Image src={u.avatar} width={50} height={50} roundedCircle />
                                ) : (
                                    "No avatar"
                                )}
                            </td>
                            <td>
                                <Button variant="danger" size="sm" onClick={() => deleteUser(u.id)}>
                                    Xóa
                                </Button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </Table>
        </div>
    );
};

export default AddUser;
