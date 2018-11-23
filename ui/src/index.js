//created by petar.petrov

import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import Keycloak from 'keycloak-js';

const keycloakJson = {
    "realm": "demo",
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

keycloak.init({onLoad: 'login-required'})
    .then(authenticated => {
        if (authenticated) {
            ReactDOM.render(<App keycloak={getKeycloak()}/>, document.getElementById('root'));
        } else {
            keycloak.login();
        }
    });
