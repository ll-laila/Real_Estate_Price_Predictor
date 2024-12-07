import axiosInstance from './axiosInterceptor';

class OffreService {
  saveOffer(offer) {
    return axiosInstance.post("/offres", offer, {
      headers: {
        "Content-Type": "application/json",
      },
    });
  }

  getAllOffres() {
    return axiosInstance.get("/offres/allOffers");
  }

  getOffersByUser(userId) {
    return axiosInstance.get(`/offres/allOffers/${userId}`);
  }

  updateOffer(offer, offerId) {
    return axiosInstance.put(`/offres/${offerId}`, offer, {
      headers: {
        "Content-Type": "application/json",
      },
    });
  }

  deleteOffer(id) {
    return axiosInstance.delete(`/offres/${id}`);
  }

  getOfferById(offerId) {
    return axiosInstance.get(`/offres/${offerId}`);
  }

  getFilteredOffres(filters) {
    const queryString = new URLSearchParams(filters).toString();
    return axiosInstance.get(`/offres/search?${queryString}`);
  }
}

export default new OffreService();