import axios from "axios";
import axiosInstance from './axiosInterceptor';

const USER_API_BASE_URL = "http://localhost:8222/api/v1/users"; // Gateway URL

class UserService {
    // Save user (registration)
    saveUser(user) {
        return axios.post(USER_API_BASE_URL + "/create", user, {
            headers: {
                "Content-Type": "application/json",
            },
        });
    }

     // Get users
     getUsers() {
        return axiosInstance.get("/users");
    }
    
    // Get user by ID
    // New method to get current authenticated user
    getUserById(userId = null) {
        // If no userId is provided, fetch the current authenticated user
        const url = userId 
            ? `/users/${userId}` 
            : "/users/me";  // Assuming your backend has an endpoint to get current user

        return axiosInstance.get(url);
    }

    // Update user
    updateUser(user) {
        return axiosInstance.put("/users", user, {
            headers: {
                'Content-Type': 'application/json',
            },
        });
    }

    // Delete user
    deleteUser(id) {
        return axiosInstance.delete(`/users/${id}`);
    }

    // Login method with token
    login(usernameOrEmail, password) {
        return axios.post(USER_API_BASE_URL + "/login", {
            usernameOrEmail: usernameOrEmail,
            password: password,
        }, {
            headers: {
                "Content-Type": "application/json",
            },
        });
    }

    // Add token to request headers for future authenticated requests
    getAuthHeaders() {
        const token = localStorage.getItem("accessToken"); // Retrieve token from localStorage
        return {
            headers: {
                "Authorization": `Bearer ${token}`, // Add the token to Authorization header
                "Content-Type": "application/json",
            },
        };
    }
}

export default new UserService();
