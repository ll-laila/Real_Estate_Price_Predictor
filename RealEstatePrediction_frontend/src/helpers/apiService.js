// apiService.js
import axios from 'axios';

export const getAuthToken = () => {
    return window.localStorage.getItem('auth_token');
};

export const setAuthHeader = (token) => {
    if (token !== null) {
        window.localStorage.setItem("auth_token", token);
    } else {
        window.localStorage.removeItem("auth_token");
    }
};

axios.defaults.baseURL = 'http://localhost:8222';
axios.defaults.headers.post['Content-Type'] = 'application/json';

export const request = (method, url, data = {}) => {
    let headers = {};
    const token = getAuthToken();
    if (token) {
        headers = { 'Authorization': `Bearer ${token}` };
    }

    return axios({
        method,
        url,
        headers,
        data,
    });
};