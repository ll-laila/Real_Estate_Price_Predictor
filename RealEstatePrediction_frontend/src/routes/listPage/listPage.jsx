import React, { useEffect, useState } from "react";
import OffreService from "../../services/offreService";
import Card from "../../components/cardOffre/Card";
import Map from "../../components/map/Map";
import Filter from "../../components/filter/Filter";
import "./listPage.scss";


const ListPage = () => {
  const [offres, setOffres] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchOffres = async () => {
      try {
        const response = await OffreService.getAllOffres();
        console.log(response.data);
        setOffres(response.data);
        setLoading(false);
      } catch (err) {
        setError(err.message);
        setLoading(false);
      }
    };

    fetchOffres();
  }, []);

  if (loading) return <p>Chargement des offres...</p>;
  if (error) return <p>Erreur : {error}</p>;

  return (
    <div className="listPage">
      <div className="listContainer">
        <div className="wrapper">        <Filter/>
          {offres && offres.length > 0 ? (
            offres.map((offre) => {
              const { immobilierResponse } = offre;
              return (
                <Card
                  key={offre.id}
                  item={{
                    id: offre.id,
                    img: immobilierResponse.img || 'https://images.pexels.com/photos/1918291/pexels-photo-1918291.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2', // Default image
                    title: immobilierResponse.title || 'Titre non disponible',
                    address: immobilierResponse.address || 'Adresse non disponible',
                    price: immobilierResponse.price || 'Prix non disponible',
                    bedroom: immobilierResponse.bedroom || 0, // Default for bedrooms
                    bathroom: immobilierResponse.bathroom || 0, // Default for bathrooms
                  }}
                />
              );
            })
          ) : (
            <p>Aucune offre disponible</p>
          )}
        </div>
      </div>

      <div className="mapContainer">
        <Map items={offres}/>
      </div>
    </div>
  );
};

export default ListPage;
