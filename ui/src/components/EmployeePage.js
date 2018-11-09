import React, {Component} from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import RaisedButton from 'material-ui/RaisedButton';
import AxiosClient from "../Utils/AxiosClient";

export default class EmployeePage extends Component {

    logout = () => {
        this.props.keycloak.logout();
    };

    clockIn = () => {
        let clockIn = "ClockIn";
        new AxiosClient().clockInOut(this.props.keycloak, clockIn)
    };

    clockOut = () => {
        let clockOut = "ClockOut";
        new AxiosClient().clockInOut(this.props.keycloak, clockOut)
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
        )
    }
}