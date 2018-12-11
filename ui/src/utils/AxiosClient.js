//created by petar.petrov, updated by Abby Turner
import axios from 'axios/index';

/* This file contains all the code to interact with the rest layer
 * It handles getting the correct attributes from keycloak as well as
 * sending and recieving data via the requests and error handling.
 */
export default class AxiosClient {

    // The application root URL
    mainPath = 'http://localhost:7070';

    // The axios call responsible for sending the post request for clock in action
    // It also refreshes the data in the application state and handles any errors
    clockIn = (keycloak, updater) => {
        const config = {
            headers: {'Content-Type': 'text/plain'}
        };
        console.log(keycloak.idTokenParsed.preferred_username);
        axios.post(this.mainPath + "/clockIn", keycloak.idTokenParsed.preferred_username, config
        ).then(response => {
            alert(response.data);
            this.getData(keycloak, updater);
        }).catch(error => {
            if (error != null) {
                alert(error.response.data.message);
            } else {
                alert(error);
            }
            console.log(error);
        })
    };

    // The axios call responsible for sending the post request for clock out action
    // It also refreshes the data in the application state and handles any errors
    clockOut = (keycloak, updater) => {
        const config = {
            headers: {'Content-Type': 'text/plain'}
        };
        axios.post(this.mainPath + "/clockOut", keycloak.idTokenParsed.preferred_username, config
        ).then(response => {
            alert(response.data);
            this.getData(keycloak, updater);
        }).catch(error => {
            if (error != null) {
                alert(error.response.data.message);
            } else {
                alert(error);
            }
            console.log(error);
        })
    };

    // The axios call responsible for sending the post request to
    // refresh the data in the application state and handle any errors
    getData = (keycloak, updater) => {
        axios.post(this.mainPath + "/getData", {
            username: keycloak.idTokenParsed.preferred_username,
            role: this.getRole(keycloak)
        }).then(response => {updater(response.data)
        }).catch(error => {
            if (error != null) {
                alert(error.response.data.message);
            } else {
                alert(error);
            }
            console.log(error);
        })
    };

    // Checking the role in the keycloak object of the current user
    getRole = (keycloak) => {
        if(keycloak.hasRealmRole('MANAGER')){
            return 'MANAGER'
        }
        return 'EMPLOYEE'
    }
}