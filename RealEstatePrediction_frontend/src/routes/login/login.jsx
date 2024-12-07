import "./login.scss";
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import UserService from "../../services/UserService"; // Import the UserService

function Login() {
  const navigate = useNavigate(); // Hook for redirection
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });
  const [error, setError] = useState(""); // Error state

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(""); // Reset any previous error messages

    try {
      const response = await UserService.login(formData.username, formData.password);

      // Check if response contains access_token
      if (response && response.data.access_token) {
        // Store the access token in localStorage
        console.log("Access Token:", response.data.access_token);
        localStorage.setItem("accessToken", response.data.access_token);

        // Redirect to HomePage after successful login
        navigate("/HomePage");
      } else {
        setError("Login failed. Please check your credentials.");
      }
    } catch (error) {
      console.error("Login failed:", error);
      // Handle server-side or network error
      setError("Invalid username or password. Please try again.");
    }
  };

  return (
    <div className="login">
      <div className="formContainer">
        <form onSubmit={handleSubmit}>
          <h1>Welcome back</h1>
          <input
            name="username"
            type="text"
            placeholder="Username or Email"
            value={formData.username}
            onChange={handleChange}
            required
          />
          <input
            name="password"
            type="password"
            placeholder="Password"
            value={formData.password}
            onChange={handleChange}
            required
          />
          {error && <p className="error">{error}</p>} {/* Display error message */}
          <button type="submit">Login</button>
          <Link to="/register">{"Don't"} have an account?</Link>
        </form>
      </div>
      <div className="imgContainer">
        <img src="/bg.png" alt="Background" />
      </div>
    </div>
  );
}

export default Login;
