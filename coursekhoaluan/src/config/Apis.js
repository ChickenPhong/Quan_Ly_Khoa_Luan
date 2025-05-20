import axios from "axios";
import cookie from "react-cookies";

// Nhớ cập nhật lại URL phù hợp với backend của khóa luận
const BASE_URL = 'http://localhost:8080/SpringKhoaLuan/api';

export const endpoints = {
    // 🔐 Authentication
    login: '/login',
    'current-user': '/secure/profile',

    // 👤 Quản trị viên
    'get-users': '/users/',             // GET - lấy danh sách
    'add-user': '/users/',              // POST - thêm user (multipart/form-data)
    'delete-user': '/users/delete',    // POST - xóa user (form userId)

    // 🎓 Giáo vụ
    theses: '/theses',
    'assign-reviewers': '/theses/assign',
    committees: '/committees',
    getTieuChi: () => `${BASE_URL}/tieuchi`,
    addTieuChi: () => `${BASE_URL}/tieuchi/add`,
    getKhoaHocList: () => `${BASE_URL}/giaovu/khoahoc`,
    getSinhVienByKhoaHoc: (khoaHoc) => `${BASE_URL}/giaovu/sinhvien?khoaHoc=${khoaHoc}`,
    getDeTaiByKhoa: () => `${BASE_URL}/giaovu/detai`,
    xepDeTai: (khoaHoc) => `${BASE_URL}/giaovu/xepdetai?khoaHoc=${khoaHoc}`,

    // 🧑‍🏫 Giảng viên
    scores: '/scores',

    // 📊 Báo cáo thống kê
    stats: '/stats'
};

// Gọi API có kèm token (auth required)
export const authApis = () => {
    return axios.create({
        baseURL: BASE_URL,
        headers: {
            'Authorization': `Bearer ${cookie.load('token')}`
        }
    });
}; 

// Gọi API không cần token
export default axios.create({
    baseURL: BASE_URL
});
