//created by petar.petrov, updated by Cody Penta and Abby Turner
import React, {Component} from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import RaisedButton from 'material-ui/RaisedButton';
import DataTable from './components/Table';
import AxiosClient from "./utils/AxiosClient";
import Typography from "@material-ui/core/Typography/Typography";
import Grid from "@material-ui/core/Grid/Grid";
import logo from './images/Cronos_logo.png';

/* 
 * This file is the heart of the Front end React app.
  * It stores and manages the application state on the front end
 */

class App extends Component {

    // App constructor, which sets the state with the user's keycloak data 
    // and provides an empty data member for holding the table data
    constructor(props) {
        super(props);
        this.state = {
            keycloak: this.props.keycloak,
            data: []
        }
    }

    // This method triggers a logout, which is handled by the keycloak service
    logout = () => {
        this.props.keycloak.logout();
    };

    // This method calls the clockIn method in the AxiosClient 
    // and sets the returned table data in the state
    clockIn = () => {
        new AxiosClient().clockIn(this.props.keycloak, (data) => {
            this.setState({data: data})
        })
    };

    // This method calls the clockOut method in the AxiosClient 
    // and sets the returned table data in the state
    clockOut = () => {
        new AxiosClient().clockOut(this.props.keycloak, (data) => {
            this.setState({data: data})
        })
    };

    // This method is called when the component is mounted to the parent component in index.js
    // and serves to initialize the data in the table.
    componentWillMount = () => {
        new AxiosClient().getData(this.props.keycloak, (data) => {
            this.setState({data: data})
        })
    };


    render() {
        return (
            <div>
                {/* The Application name and logo component*/}
                <Typography variant="h3" color="primary" style={{display:"inline", marginLeft:"30px", marginTop:"10px"}}>
                    Cronos
                    <img src={logo} alt="logo" width="50px"/>
                </Typography>
                <div style={{
                    backgroundImage: 'linear-gradient(#add8e6, #00f',
                    display: "flex",
                    flexGrow: 1,
                    justifyContent: "center",
                    margin: "30px",
                    paddingTop: "50px"
                }}>
                    {/* The Grid Below contains the buttons and table of the GUI.
                        It also binds the onClick event handlers to their associated action methods*/}
                    <MuiThemeProvider>
                        <Grid container column justify="center" spacing={32}>
                            <Grid container spacing={8} justify="right" alignItems="center" style={{marginLeft:"30px"}}>
                                <Grid item xs={3}>
                                    <RaisedButton label="Clock In" onClick={this.clockIn}/>
                                </Grid>
                                <Grid item xs={3}>
                                    <RaisedButton label="Clock Out" onClick={this.clockOut}/>
                                </Grid>
                                <Grid item xs={3} >
                                    <span></span>
                                </Grid>
                                <Grid item xs={3}>
                                    <RaisedButton label={"Logout - " + this.props.keycloak.idTokenParsed.preferred_username} onClick={this.logout}/>
                                </Grid>
                            </Grid>
                            <Grid item xs={12}>
                                <div>
                                <DataTable keycloak={this.props.keycloak} data={this.state.data}/>
                                </div>
                            </Grid>
                        </Grid>
                    </MuiThemeProvider>
                </div>
            </div>
        );
    }
}

export default App;
