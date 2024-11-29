import { useState } from "react";
import React from "react";
import Pracing from "../../components/payment/Pracing";
import "./Prediction.scss";


function Prediction() {
  const [city, setCity] = useState("");
  const [price, setPrice] = useState("");
  const [dateToPredict, setDateToPredict] = useState("");
  const [show, setShow] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();

    const predictionRequest = {
      city: city,
      date_to_predict: dateToPredict,
      current_price: parseFloat(price),
    };

    console.log(predictionRequest);
  };

  const getIsFormValid = () => {
    return city && price && dateToPredict;
  };

  return (
    <>
      {show ? (
        <div className="App">
          <form onSubmit={handleSubmit}>
            <fieldset>
              <h2>Predict real estate price</h2>
              <div className="Field">
                <label>
                  real estate city <sup>*</sup>
                </label>
                <input
                  value={city}
                  onChange={(e) => {
                    setCity(e.target.value);
                  }}
                  placeholder="House city"
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
                  placeholder="Current House Price"
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
        </div>
      ) : (
        <Pracing />
      )}
    </>
  );
}

export default Prediction;
