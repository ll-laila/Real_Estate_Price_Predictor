import "./register.scss";
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import UserService from "../../services/UserService"; // Import the UserService

function Register() {
  const navigate = useNavigate(); // Hook for redirection
  const [formData, setFormData] = useState({
    username: "",
    email: "",
    password: "",
    firstName: "",
    lastName: "",
  });
  const [error, setError] = useState(""); // Error state
  const [successMessage, setSuccessMessage] = useState(""); // Success state

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(""); // Reset error messages
    setSuccessMessage(""); // Reset success messages

    try {
      // Call the saveUser method from UserService
      await UserService.saveUser(formData);

      // If successful, set success message and navigate to login
      setSuccessMessage("Registration successful. Redirecting to login...");
      setTimeout(() => {
        navigate("/login"); // Redirect to login page
      }, 2000); // Redirect after 2 seconds
    } catch (error) {
      console.error("Registration failed:", error);
      setError("Failed to register. Please check your inputs or try again.");
    }
  };

  return (
    <div className="register">
      <div className="formContainer">
        <form onSubmit={handleSubmit}>
          <h1>Create Your Account</h1>
          <input
            name="username"
            type="text"
            placeholder="Username"
            value={formData.username}
            onChange={handleChange}
            required
          />
          <input
            name="email"
            type="email"
            placeholder="Email"
            value={formData.email}
            onChange={handleChange}
            required
          />
          <input
            name="firstName"
            type="text"
            placeholder="First Name"
            value={formData.firstName}
            onChange={handleChange}
            required
          />
          <input
            name="lastName"
            type="text"
            placeholder="Last Name"
            value={formData.lastName}
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
          {successMessage && <p className="success">{successMessage}</p>} {/* Display success message */}
          <button type="submit">Register</button>
          <Link to="/login">Already have an account?</Link>
        </form>
      </div>
      <div className="imgContainer">
        <img src="/bg.png" alt="Background" />
      </div>
    </div>
  );
}

export default Register;
