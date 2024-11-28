import axios from "axios";

const PAYMENT_API_BASE_URL = "http://localhost:8222/api/v1/payments";

class PaymentService {

    getAllPans(){
        return axios.post(PAYMENT_API_BASE_URL+"/allplans");
    }

    getPlanById(id) {
        return axios.get(PAYMENT_API_BASE_URL + "/exists/" + id);
    }

    saveSubscription(subscription) {
        return axios.post(PAYMENT_API_BASE_URL + "/createSubscription",subscription);
    }

    getUserSubscription(id) {
        return axios.get(PAYMENT_API_BASE_URL + "/" + id);
    }


    
    updateSubscription(subscription) {
        return axios.put(PAYMENT_API_BASE_URL, subscription);
    }

}

export default new PaymentService ();