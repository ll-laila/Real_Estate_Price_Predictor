import React, { useEffect, useState } from "react";
import Card from "../../components/cardOffre/Card";
import Map from "../../components/map/Map";
import "./listPage.scss";
import { getAuthUser } from "../../helpers/apiService"; 
import { request } from "../../helpers/apiService"; 


const cities = [
  "New York",
  "Los Angeles",
  "Chicago",
  "Houston",
  "Phoenix",
  "Philadelphia",
  "San Antonio",
  "San Diego",
  "Dallas",
  "San Francisco",
  "Miami",
  "Seattle",
  "Las Vegas",
  "Atlanta",
  "Boston",
  "Washington, D.C.",
  "Denver",
  "Orlando",
  "Detroit",
  "Austin",
  "Nashville",
  "Portland",
  "Baltimore",
  "Salt Lake City",
  "Charlotte",
];

const ListPage = () => {
  const [user, setUser] = useState(null);
  const [offres, setOffres] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [filters, setFilters] = useState({ city: "" }); 

  const fetchOffres = async () => {
    setLoading(true);
    setError(null);
    try {
      let response;
      if (filters.city) {
        const queryString = new URLSearchParams(filters).toString();
        response = await request("GET", `/api/v1/users/search?${queryString}`);
        console.log("Réponse après filtre city:", response); 
        setOffres(response.data);
      } else {
        response = await request("GET", "/api/v1/users/allOffers");
        setOffres(response.data);
      }
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };


  useEffect(() => {
    const authUser = getAuthUser();
    if (authUser) {
      setUser(authUser);
      console.log(user);
    }
  }, []);


  useEffect(() => {
    fetchOffres();
  }, [filters]); 

  const handleFilterChange = (newFilters) => {
    setFilters(newFilters); 
  };

  if (loading) return <p>Chargement des offres...</p>;
  if (error) return <p>Erreur : {error}</p>;

  return (
    <div className="listPage">
      <div className="listContainer">
        <div className="wrapper">
          {/* Section des filtres */}
          <div className="filter">
            <h1>Search Filters</h1>
            <div className="top">
              <div className="item">
                <label htmlFor="city">Location</label>
                <select
                  id="city"
                  name="city"
                  value={filters.city}
                  onChange={(e) => handleFilterChange({ city: e.target.value })}
                >
                  <option value="">Select a City</option>
                  {cities.map((city, index) => (
                    <option key={index} value={city}>
                      {city}
                    </option>
                  ))}
                </select>
              </div>
            </div>
          </div>

          {/* Affichage des offres */}
          {offres && offres.length > 0 ? (
            offres.map((offre) => {
              const { immobilierResponse } = offre;
              console.log(immobilierResponse, "je suis immobiler response");
              const { title, address, price, bedroom, bathroom, images } =
                immobilierResponse || {};

              const imageUrl =
                images?.length > 0
                  ? images[0]
                  : "https://images.pexels.com/photos/1918291/pexels-photo-1918291.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2";

              return (
                <Card
                  key={offre.id}
                  item={{
                    id: offre.id,
                    img: imageUrl,
                    title: title || "Titre non disponible",
                    address: address || "Adresse non disponible",
                    price: price || "Prix non disponible",
                    bedroom: bedroom || 0,
                    bathroom: bathroom || 0,
                  }}
                />
              );
            })
          ) : (
            <p>Aucune offre disponible</p>
          )}
        </div>
      </div>

      {/* Section de la carte */}
      <div className="mapContainer">
        <Map />
      </div>
    </div>
  );
};

export default ListPage;
