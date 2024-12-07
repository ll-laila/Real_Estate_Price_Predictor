import './Mylist.scss';
import { useState, useEffect } from "react";
import CardMyOffre from "../cardMyOffre/cardMyOffre";
import OffreService from "../../services/OffreService";
import UserService from "../../services/UserService";
import { useNavigate } from "react-router-dom";

function MyList() {
  const [offers, setOffers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchUserOffres = async () => {
      try {
        // Get the authenticated user's ID
        const response = await UserService.getUserById(null); // Passing null will fetch the currently authenticated user
        const userId = response.data.id;
        console.log(userId);

        // Fetch offers for this user
        const offersResponse = await OffreService.getOffersByUser(userId);
        setOffers(offersResponse.data);
        setLoading(false);
      } catch (err) {
        console.error("Error fetching user offers:", err);
        setError("Could not fetch offers. Please log in again.");
        setLoading(false);
        
        // If authentication fails, redirect to login
        if (err.response && err.response.status === 401) {
          localStorage.removeItem('accessToken');
          navigate('/login');
        }
      }
    };

    // Check if user is authenticated
    const token = localStorage.getItem('accessToken');
    if (token) {
      fetchUserOffres();
    } else {
      navigate('/login');
    }
  }, [navigate]);

  if (loading) {
    return <div>Loading offers...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div className="my-list-container">
      {offers.length > 0 ? (
        offers.map((item) => (
          <CardMyOffre key={item.id} item={item} />
        ))
      ) : (
        <div>No offers available.</div>
      )}
    </div>
  );
}

export default MyList;