import axios from 'axios/index';

export default class AxiosClient {

    mainPath = 'http://localhost:7070';

    clockIn = (keycloak) => {
        console.log(keycloak.idTokenParsed.preferred_username);
        axios.post(this.mainPath + "/clockIn", {
            username: keycloak.idTokenParsed.preferred_username
        }).then(response => {
            alert(response.data)
        }).catch(error => {
            alert("Unexpected error: " + error.data)
        })
    };

    clockOut = (keycloak) => {
        axios.post(this.mainPath + "/clockOut", {
            username: keycloak.idTokenParsed.preferred_usrname
        }).then(response => {
            alert(response.data)
        }).catch(error => {
            alert("Unexpected error: " + error.data)
        })
    };

    getData = (keycloak, updater) => {
        console.log(keycloak);
        console.log(keycloak.tokenParsed.realm_access.roles.pop());
        axios.post(this.mainPath + "/getData", {
            username: keycloak.idTokenParsed.preferred_usrname,
            role: keycloak.tokenParsed.realm_access.roles.pop()
        }).then(response => {updater(response.data)
        }).catch(error => {
            alert(error)
        })
    }
}