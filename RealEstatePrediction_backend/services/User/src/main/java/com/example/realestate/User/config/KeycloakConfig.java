package com.example.realestate.User.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration
public class KeycloakConfig {

    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    @Value("${keycloak.admin.username}")
    private String keycloakAdminUsername;

    @Value("${keycloak.admin.password}")
    private String keycloakAdminPassword;

    @Value("${keycloak.admin.client-id}")
    private String keycloakAdminClientId;
    @Value("${keycloak.admin.client-secret}")
    private String keycloakAdminClientSecret;

    @Bean
    public Keycloak keycloakClient() {
        return KeycloakBuilder.builder()
                .serverUrl(keycloakServerUrl)
                .realm(keycloakRealm)
                .username(keycloakAdminUsername)
                .password(keycloakAdminPassword)
                .clientId(keycloakAdminClientId)
                // .grantType("password")
                .clientSecret(keycloakAdminClientSecret)
                .grantType(OAuth2Constants.PASSWORD) // Make sure to use PASSWORD grant type
                .build();
    }
}
