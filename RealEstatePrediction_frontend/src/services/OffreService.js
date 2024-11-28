import axios from "axios";

const OFFRE_API_BASE_URL = "http://localhost:8222/api/v1/offres";

class OffreService {
  // Récupérer toutes les offres
  getAllOffres() {
    return axios.get(`${OFFRE_API_BASE_URL}/allOffers`);
  }

  // Créer une nouvelle offre
  createOffre(offreData) {
    return axios.post(OFFRE_API_BASE_URL, offreData);
  }

  // Récupérer une offre par ID
  getOffreById(offreId) {
    return axios.get(`${OFFRE_API_BASE_URL}/${offreId}`);
  }

  // Mettre à jour une offre
  updateOffre(offreId, offreData) {
    return axios.put(`${OFFRE_API_BASE_URL}/${offreId}`, offreData);
  }

  // Supprimer une offre
  deleteOffre(offreId) {
    return axios.delete(`${OFFRE_API_BASE_URL}/${offreId}`);
  }
}

export default new OffreService();
