//created by petar.petrov, updated by Abby Turner

/*
 * This file sets up the Keycloak properties for the application, 
 * handles the keycloak authentication, and rendering the React app.
 */
import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import Keycloak from 'keycloak-js';

// Keycloak properties that need to match the configuration setup in Keycloak GUI
const keycloakJson = {
    "realm": "Cronos",
    "clientId": "frontend-app",
    "url": "http://localhost:8080/auth",
    "ssl-required": "external",
    "resource": "frontend-app",
    "public-client": true
};

const keycloak = Keycloak(keycloakJson);

const getKeycloak = () => {
    return keycloak;
};

// Initialize the keycloak service and handle authentication, then render the React app.
keycloak.init({onLoad: 'login-required'})
    .then(authenticated => {
        if (authenticated) {
            ReactDOM.render(<App keycloak={getKeycloak()}/>, document.getElementById('root'));
        } else {
            keycloak.login();
        }
    });
