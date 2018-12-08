//created by petar.petrov
import React, {Component} from 'react';
import './App.css';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import RaisedButton from 'material-ui/RaisedButton';
import DataTable from './components/Table';
import AxiosClient from "./utils/AxiosClient";
import Typography from "@material-ui/core/Typography/Typography";
import Grid from "@material-ui/core/Grid/Grid";
import logo from './images/Cronos_logo.png';

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            keycloak: this.props.keycloak
        }
    }

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
                <Typography variant="h3" color="primary" style={{display:"inline"}}>
                    Chronos
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
                    {/* <Particles
                    params={{
                            particles: {
                                line_linked: {
                                    shadow: {
                                        enable: true,
                                        color: "#3CA9D1",
                                        blur: 5
                                    }
                                }
                            }
                        }}
                    style={{
                        width: '100%',

                    }}
                    /> */}
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
                                    <RaisedButton label="Logout" onClick={this.logout}/>
                                </Grid>
                            </Grid>
                            <Grid item xs={12}>
                                <div>
                                <DataTable keycloak={this.props.keycloak}/>
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
