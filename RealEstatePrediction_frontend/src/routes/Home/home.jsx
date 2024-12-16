import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; // Importer useNavigate
import "./home.scss";

function Home() {
    const [offres] = useState([
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
    ]);

    const navigate = useNavigate(); // Initialiser useNavigate

    const handleOfferClick = () => {
        navigate("/login"); // Rediriger vers la page login
    };

    return (
        <div className="homePage">
            <div className="textContainer">
                <div className="wrapper">
                    <h1 className="title">Find Real Estate & Get Your Dream Place</h1>
                    <p>
                        Our platform is not just about browsing or listing properties.
                        We leverage advanced AI technology to predict real estate prices,
                        empowering you with insights to make smarter investment decisions.
                        Whether you are buying, selling, or simply exploring, we are here to revolutionize your real estate journey.
                    </p>
                    <button className="getStartedButton" onClick={() => navigate("/login")}>
                        Get Started
                    </button>
                    <div className="offersSection">
                        <h2 className="sectionTitle">Top Offers</h2>
                        <div className="offersContainer">
                            {offres.map((offre) => (
                                <div
                                    key={offre.id}
                                    className="offer"
                                    onClick={handleOfferClick}
                                >
                                    <img src={offre.immobilierResponse.img} alt={offre.immobilierResponse.title} />
                                    <h3>{offre.immobilierResponse.title}</h3>
                                    <p>{offre.immobilierResponse.address}</p>
                                    <p>Price: {offre.immobilierResponse.price}</p>
                                    <p>Bedrooms: {offre.immobilierResponse.bedroom}</p>
                                    <p>Bathrooms: {offre.immobilierResponse.bathroom}</p>
                                </div>
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
