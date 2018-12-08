//created by petar.petrov, updated by Abby Turner
import axios from 'axios/index';

export default class AxiosClient {

    mainPath = 'http://localhost:7070';

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
            alert(error.response.data.message)
        })
    };

    clockOut = (keycloak, updater) => {
        const config = {
            headers: {'Content-Type': 'text/plain'}
        };
        axios.post(this.mainPath + "/clockOut", keycloak.idTokenParsed.preferred_username, config
        ).then(response => {
            alert(response.data);
            this.getData(keycloak, updater);
        }).catch(error => {
            alert(error.response.data.message)
        })
    };

    getData = (keycloak, updater) => {
        axios.post(this.mainPath + "/getData", {
            username: keycloak.idTokenParsed.preferred_username,
            role: this.getRole(keycloak)
        }).then(response => {updater(response.data)
        }).catch(error => {
            alert(error.response.data.message)
        })
    }

    getRole = (keycloak) => {
        if(keycloak.hasRealmRole('MANAGER')){
            return 'MANAGER'
        }
        return 'EMPLOYEE'
    }
}