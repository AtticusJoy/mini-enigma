import axios from 'axios/index';
import qs from 'qs';

export default class AxiosClient {

    mainPath = 'http://localhost:7070';

    clockIn = (keycloak) => {
        axios.post(this.mainPath + "/clockIn", {
            username: keycloak.idToken.preferred_username
        }).then(response => {
            if(response.status === 200){
                alert("Login Successful")
            } else if(response.status === 400){
                alert("Bad Request")
            } else {
                alert("Unexpected Error")
            }
        })
    }
}