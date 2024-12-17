import { useState, useEffect } from "react";
import React from "react";
import Pracing from "../../components/payment/Pracing";
import "./Prediction.scss";
import { useParams } from "react-router-dom";
import { getAuthUser } from "../../helpers/apiService";
import { request } from "../../helpers/apiService"; 


function Prediction() {
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

  const userId = authUser.id;
  const { id } = useParams();
  const [city, setCity] = useState("");
  const [price, setPrice] = useState("");
  const [dateToPredict, setDateToPredict] = useState("");
  const [show, setShow] = useState(true);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [predictionResult, setPredictionResult] = useState(null);
  const [subscription, setSubscription] = useState(null);

  useEffect(() => {
    // Récupérer la souscription de l'utilisateur
    const fetchSubscription = async () => {
      try {
        const response = await request("GET", "/api/v1/users/allSubscriptions");
        const allSubscriptions = response.data;
        const userSubscription = allSubscriptions.find(
          (sub) => sub.userId === userId
        );
        if (!userSubscription || userSubscription.nbrPrediction === 0) {
          setShow(false);
        } else {
          setShow(true);
        }

        setSubscription(userSubscription);
      } catch (err) {
        setError("Erreur lors de la récupération de la souscription");
      }
    };

    // Récupérer l'offre immobilière
    const fetchOffer = async () => {
      try {
        const response = await request("GET", `/api/v1/users/getOffre/${id}`);
        const offer = response.data;
        setCity(offer.immobilierResponse?.city || "");
        setPrice(offer.immobilierResponse?.price || "");
        setLoading(false);
      } catch (err) {
        setError("Erreur lors de la récupération de l'offre");
        setLoading(false);
      }
    };

    fetchSubscription();
    fetchOffer();
  }, [id, userId]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const predictionRequest = {
      city: city,
      date_to_predict: dateToPredict,
      current_price: parseFloat(price),
    };

    try {
      const response = await request(
        "POST",
        "/api/v1/users/PredictHousePrice",
        predictionRequest
      );
      setPredictionResult(response.data);
      setCity("");
      setPrice("");
      setDateToPredict("");

      // dicrémentation du nbrPrediction du subscription de l'utilisateur
      const response2 = await request("GET", "/api/v1/users/allSubscriptions");
      const allSubscriptions = response2.data;
      const userSubscription = allSubscriptions.find(
        (sub) => sub.userId === user
      );

      const UpdateSubscription = {
        namePlan: userSubscription.namePlan,
        userId: userSubscription.userId,
        nbrPrediction: userSubscription.nbrPrediction - 1,
      };
      const response3 = await request(
        "PUT",
        "/api/v1/users/updateSubscription",
        UpdateSubscription
      );
      const updatedSubscription = response3.data;
      setSubscription(updatedSubscription);
    } catch (err) {
      setError("Erreur lors de la prédiction du prix");
    }
  };

  const getIsFormValid = () => {
    return city && price && dateToPredict;
  };

  const closeModal = () => {
    setPredictionResult(null);
  };

  if (loading) {
    return <p>Chargement de l'offre...</p>;
  }

  if (error) {
    return <p>{error}</p>;
  }

  return (
    <>
      {show ? (
        <div className="App">
          <form onSubmit={handleSubmit} className="form22">
            <fieldset>
              <h2>Predict real estate price</h2>
              <div className="Field">
                <label>
                  Real estate city <sup>*</sup>
                </label>
                <input
                  value={city}
                  onChange={(e) => {
                    setCity(e.target.value);
                  }}
                  placeholder="City"
                />
              </div>
              <div className="Field">
                <label>
                  Current real estate Price<sup>*</sup>{" "}
                </label>
                <input
                  value={price}
                  onChange={(e) => {
                    setPrice(e.target.value);
                  }}
                  placeholder="Current Price"
                />
              </div>
              <div className="Field">
                <label>
                  Date to Predict <sup>*</sup>
                </label>
                <input
                  value={dateToPredict}
                  type="date"
                  onChange={(e) => {
                    setDateToPredict(e.target.value);
                  }}
                  placeholder="Date to Predict"
                />
              </div>
              <button type="submit" disabled={!getIsFormValid()}>
                Predict
              </button>
            </fieldset>
          </form>
          <div className="imgContainer pdn">
            <img src="/imagePredict.webp" alt="" />
          </div>
          {predictionResult && (
            <div className="predictionResultModal">
              <div className="modalContent">
                <a className="close-btn" onClick={closeModal}>
                  ×
                </a>
                <h3 className="h32">
                  The price of this real estate will become{" "}
                  <span className="predicted-price">
                    <b className="b1">${predictionResult.predicted_price} </b>
                  </span>
                  in{" "}
                  <span className="predicted-price">
                    {predictionResult.date_to_predict}
                  </span>
                  .
                </h3>
              </div>
            </div>
          )}
        </div>
      ) : (
        <Pracing usreId={userId} />
      )}

      {error && <p className="error">{error}</p>}
    </>
  );
}

export default Prediction;
