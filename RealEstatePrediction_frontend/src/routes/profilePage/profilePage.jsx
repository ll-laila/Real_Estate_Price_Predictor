import "./profilePage.scss";
import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import UserService from "../../services/UserService.js";
import { FaUser, FaEnvelope, FaPhone } from "react-icons/fa"; // Importation des icônes
import { getAuthUser } from "../../helpers/apiService"; 

function ProfilePage() {

  const [user, setUser] = useState(null);

  useEffect(() => {
    const authUser = getAuthUser();
    if (authUser) {
      setUser(authUser);
      console.log(user);
    }
  }, []);
    
  if (!user) {
    return <div className="loading">Loading user data...</div>;
  }

  return (
      <div className="profilePage">
        <div className="welcome">
          <h2>Welcome back, {user.firstName}!</h2> {/* Message de bienvenue */}
        </div>

        <div className="details">
          <div className="wrapper">
            {/* Section de l'en-tête du profil */}
            <div className="profile-header">
              <img
                  src={`https://ui-avatars.com/api/?name=${user.firstName}+${user.lastName}&background=random&size=100`}
                  alt="User Avatar"
                  className="profile-avatar"
              />
              <h1 className="profile-name">{user.firstName} {user.lastName}</h1> {/* Nom sous l'avatar */}
            </div>

            {/* Informations de l'utilisateur avec des icônes */}
            <div className="info">
              <div className="info-item">
                <FaUser /> <span className="label">First name:</span>
                <span className="value">{user.firstName}</span>
              </div>
              <div className="info-item">
                <FaUser /> <span className="label">Last name:</span>
                <span className="value">{user.lastName}</span>
              </div>
              <div className="info-item">
                <FaEnvelope /> <span className="label">Email:</span>
                <span className="value">{user.email}</span>
              </div>
              <div className="info-item">
                <FaPhone /> <span className="label">Phone:</span>
                <span className="value">{user.phone}</span>
              </div>
            </div>

            <Link to={`/UpdateProfile/${user.id}`}>
              <button className="update-button">Update Profile</button>
            </Link>
          </div>
        </div>
      </div>
  );
}

export default ProfilePage;
