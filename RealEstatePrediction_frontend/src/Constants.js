const prod = {
  url: {
    KEYCLOAK_BASE_URL: "https://keycloak.herokuapp.com",// ces url a modifier lors du deploiement
    API_BASE_URL: 'https://myapp.herokuapp.com'
  }
}

const dev = {
  url: {
    KEYCLOAK_BASE_URL: "http://localhost:9090",
    API_BASE_URL: 'http://localhost:8222'
  }
}

export const config = process.env.NODE_ENV === 'development' ? dev : prod