import axios from "axios";
import cookie from "react-cookies";

// Nhớ cập nhật lại URL phù hợp với backend của khóa luận (Spring)
const BASE_URL = 'http://localhost:8080/SpringKhoaLuan/api/';

export const endpoints = {
    'login': '/login',
    'current-user': '/secure/profile',

    // dành cho quản trị
    'users': '/users',

    // dành cho giáo vụ
    'theses': '/theses',
    'assign-reviewers': '/theses/assign',
    'committees': '/committees',

    // dành cho giảng viên chấm điểm
    'scores': '/scores',

    // báo cáo
    'stats': '/stats'
};

export const authApis = () => {
    return axios.create({
        baseURL: BASE_URL,
        headers: {
            'Authorization': `Bearer ${cookie.load('token')}`
        }
    });
};

export default axios.create({
    baseURL: BASE_URL
});
