import React, { useEffect, useState } from "react";
import "./Pracing.scss";
import { Elements } from "@stripe/react-stripe-js";
import CheckoutForm from "./CheckoutForm";
import { loadStripe } from "@stripe/stripe-js";

function Pracing() {
  const [showForm, setShowForm] = useState(true);
  const [pricePlan, setPricePlan] = useState(0);

  const handleSubscribe = (price) => {
    setPricePlan(price);
    setShowForm(false);
  };

  const [stripePromise, setStripePromise] = useState(null);
  const [clientSecret, setClientSecret] = useState("");

  useEffect(() => {
    fetch("http://localhost:5252/config").then(async (r) => {
      const { publishableKey } = await r.json();
      setStripePromise(loadStripe(publishableKey));
    });
  }, []);

  useEffect(() => {
    if (pricePlan) {
      fetch(`http://localhost:5252/create-payment-intent/${pricePlan * 100}`, {
        method: "POST",
      }).then(async (result) => {
        const { clientSecret } = await result.json();
        setClientSecret(clientSecret);
      });
    }
  }, [pricePlan]);

  return (
    <>
      {showForm ? (
        <>
          <div className="pricing-header">
            <h1>Pricing Plan for Real Estate Price Prediction</h1>
            <p>
              Choose a plan to predict real estate prices and make informed
              investment decisions.
            </p>
          </div>

          <div className="columns">
            <ul className="price">
              <li className="header">Basic</li>
              <li className="grey">$ 9.99 / month</li>
              <li>5 predictions</li>
              <li className="grey">
                <a
                  href="#"
                  className="button"
                  onClick={() => handleSubscribe(9.99)} // Passe le prix en argument
                >
                  Subscribe
                </a>
              </li>
            </ul>
          </div>

          <div className="columns">
            <ul className="price">
              <li className="header">Pro</li>
              <li className="grey">$ 24.99 / month</li>
              <li>10 predictions</li>
              <li className="grey">
                <a
                  href="#"
                  className="button"
                  onClick={() => handleSubscribe(24.99)}
                >
                  Subscribe
                </a>
              </li>
            </ul>
          </div>

          <div className="columns">
            <ul className="price">
              <li className="header">Premium</li>
              <li className="grey">$ 49.99 / month</li>
              <li>20 predictions</li>
              <li className="grey">
                <a
                  href="#"
                  className="button"
                  onClick={() => handleSubscribe(49.99)}
                >
                  Subscribe
                </a>
              </li>
            </ul>
          </div>
        </>
      ) : (
        <>
          <h1 className="titreh2">Pay for your subscription plan</h1>
          <div className="payment-container">
          <div>
            {clientSecret && stripePromise && (
              <Elements stripe={stripePromise} options={{ clientSecret }}>
                <CheckoutForm />
              </Elements>
            )}
            </div>
            <div className="payment-image">
              <img src="/payment1.png" alt="Payment Image" />
            </div>
          </div>
        </>
      )}
    </>
  );
}

export default Pracing;
