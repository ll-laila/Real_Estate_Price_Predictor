import React, { useEffect, useState } from "react";
import Card from "../../components/cardOffre/Card";
import OffreService from "../../services/OffreService";
import "./home.scss";

function Home() {
    const [offres, setOffres] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // Données statiques à utiliser si la base de données est vide
    const staticOffres = [
        {
            id: 1,
            immobilierResponse: {
                img: 'https://images.pexels.com/photos/1918291/pexels-photo-1918291.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2',
                title: 'Beautiful Modern House',
                address: '123 Main St, Cityville',
                price: '$350,000',
                bedroom: 3,
                bathroom: 2
            }
        },
        {
            id: 2,
            immobilierResponse: {
                img: 'https://images.pexels.com/photos/1918291/pexels-photo-1918291.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2',
                title: 'Spacious Apartment',
                address: '456 Elm St, Townsville',
                price: '$250,000',
                bedroom: 2,
                bathroom: 1
            }
        },
        {
            id: 3,
            immobilierResponse: {
                img: 'https://images.pexels.com/photos/1918291/pexels-photo-1918291.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2',
                title: 'Cozy Cottage',
                address: '789 Oak St, Suburbia',
                price: '$150,000',
                bedroom: 1,
                bathroom: 1
            }
        }
    ];

    useEffect(() => {
        const fetchOffres = async () => {
            try {
                const response = await OffreService.getAllOffres();
                if (response.data && response.data.length > 0) {
                    setOffres(response.data.slice(0, 3)); // Limite à 3 offres
                } else {
                    setOffres(staticOffres); // Utilisation des données statiques si aucune offre n'est trouvée
                }
                setLoading(false);
            } catch (err) {
                setError(err.message);
                setLoading(false);
            }
        };

        fetchOffres();
    }, []);

    return (
        <div className="homePage">
            <div className="textContainer">
                <div className="wrapper">
                    <h1 className="title">Find Real Estate & Get Your Dream Place</h1>
                    <p>
                        Explore our wide selection of real estate options, whether you're looking
                        to buy or rent, and discover unique opportunities tailored to your lifestyle.
                        Trust our expertise to guide you through every step of your real estate journey.
                    </p>
                    <div className="offersSection">
                        <h2 className="sectionTitle">Top Offers</h2>
                        {loading && <p>Loading offers...</p>}
                        {error && <p>Error: {error}</p>}
                        <div className="offersContainer">
                            {offres.map((offre) => (
                                <Card
                                    key={offre.id}
                                    item={{
                                        id: offre.id,
                                        img: offre.immobilierResponse?.img || 'https://images.pexels.com/photos/1918291/pexels-photo-1918291.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2',
                                        title: offre.immobilierResponse?.title || 'No Title Available',
                                        address: offre.immobilierResponse?.address || 'No Address Available',
                                        price: offre.immobilierResponse?.price || 'No Price Available',
                                        bedroom: offre.immobilierResponse?.bedroom || 0,
                                        bathroom: offre.immobilierResponse?.bathroom || 0,
                                    }}
                                />
                            ))}
                        </div>
                    </div>
                </div>
            </div>
            <div className="imgContainer">
                <img src="/bg.png" alt="Real Estate"/>
            </div>
        </div>
    );
}

export default Home;
