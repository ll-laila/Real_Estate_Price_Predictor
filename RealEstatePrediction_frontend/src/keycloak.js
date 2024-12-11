// keycloak.js - pour centraliser l'initialisation de Keycloak
import Keycloak from 'keycloak-js';
import { config } from './Constants';

export const keycloak = new Keycloak({
  url: `${config.url.KEYCLOAK_BASE_URL}`,
  realm: 'micro-services',
  clientId: 'micro-services-api',
});

export const initOptions = {
  pkceMethod: 'S256',
  redirectUri: 'http://localhost:5173',
  checkLoginIframe: true,
  onLoad: 'check-sso',
};
