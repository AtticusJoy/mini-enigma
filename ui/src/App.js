//created by petar.petrov
import React, { Component } from 'react';
import './App.css';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import RaisedButton from 'material-ui/RaisedButton';
import AxiosClient from "./utils/AxiosClient";

class App extends Component {

    logout = () => {
        this.props.keycloak.logout();
    };

    clockIn = () => {
        new AxiosClient().clockIn(this.props.keycloak)
    };

    clockOut = () => {
        new AxiosClient().clockOut(this.props.keycloak)
    };



    render() {
        return (
            <div>
                <MuiThemeProvider>
                    <RaisedButton label="Logout" onClick={this.logout}/>
                    <RaisedButton label="Clock In" onClick={this.clockIn}/>
                    <RaisedButton label="Clock Out" onClick={this.clockOut}/>
                </MuiThemeProvider>
            </div>
        );
    }
}

export default App;
