import React, {Component} from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import RaisedButton from 'material-ui/RaisedButton';
import AxiosClient from "../utils/AxiosClient";

export default class EmployeePage extends Component {

    logout = () => {
        this.props.keycloak.logout();
    };

    clockIn = () => {
        new AxiosClient().clockIn(this.props.keycloak)
    };

    render() {
        return (
            <div>
                <MuiThemeProvider>
                    <RaisedButton label="Logout" onClick={this.logout}/>
                    <RaisedButton label="Clock In" onClick={this.clockIn}/>
                </MuiThemeProvider>
            </div>
        )
    }
}