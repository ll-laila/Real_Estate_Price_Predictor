import axios from "axios";

const OFFRE_API_BASE_URL = "http://localhost:8222/api/v1/offres";

class OffreService {
  saveOffer(offer) {
    return axios.post(OFFRE_API_BASE_URL , offer, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
  }

  predictPrice(PredictionRequest) {
    return axios.post(OFFRE_API_BASE_URL +"/PredictHousePrice", PredictionRequest, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
  }

  getAllOffres() {
      return axios.get(OFFRE_API_BASE_URL + "/allOffers");
    }

  getOffersByUser(userId) {
      return axios.get(OFFRE_API_BASE_URL + "/allOffers/" + userId);
  }

  updateOffer(offer, offerId) {
      return axios.put(OFFRE_API_BASE_URL, offer, offerId);
  }

  deleteOffer(id) {
      return axios.delete(OFFRE_API_BASE_URL + "/" + id);
  }
  getOfferById(offerId) {
    return axios.get(OFFRE_API_BASE_URL + "/" + offerId);
  }
}

export default new OffreService ();
