import axios from 'axios/index';

export default class AxiosClient {

    mainPath = 'http://localhost:7070';

    clockIn = (keycloak) => {
        console.log(keycloak.idTokenParsed.preferred_username);
        axios.post(this.mainPath + "/clockIn", {
            username: keycloak.idTokenParsed.preferred_username
        }).then(response => {
            alert(response.data)
        }).catch(response => {
            alert("Unexpected error: " + response.data)
        })
    };

    clockOut = (keycloak) => {
        axios.post(this.mainPath + "/clockOut", {
            username: keycloak.idTokenParsed.preferred_usrname
        }).then(response => {
            alert(response.data)
        }).catch(response => {
            alert("Unexpected error: " + response.data)
        })
    }


}