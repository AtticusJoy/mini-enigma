import React, {Component} from 'react';
import './App.css';
import ManagerPage from './components/ManagerPage';
import EmployeePage from './components/EmployeePage';

class App extends Component {

    render() {
        let isManager = this.props.keycloak.hasRealmRole('MANAGER');
        if(isManager){
            return (
                <div>
                    <ManagerPage keycloak={this.props.keycloak}/>
                </div>
            )
        } else {
            return (
                <div>
                    <EmployeePage keycloak={this.props.keycloak}/>
                </div>
            )
        }
    }
}

export default App;
