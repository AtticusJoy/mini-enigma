import axios from 'axios/index';

export default class AxiosClient {

    mainPath = 'http://localhost:7070';

    clockInOut = (keycloak, clockInOut) => {
        console.log(keycloak.idTokenParsed.preferred_username);
        axios.post(this.mainPath + "/clockIn", {
            username: keycloak.idTokenParsed.preferred_username,
            action: clockInOut
        }).then(response => {
            if(response.status === 200){
                alert(response.data)
            } else if(response.status === 400){
                alert(response.data)
            } else {
                alert("Unexpected Error: " + response.data)
            }
        })
    }
}