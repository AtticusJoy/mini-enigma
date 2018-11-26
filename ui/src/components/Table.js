import React, {Component} from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import AxiosClient from "../utils/AxiosClient";

const styles = theme => ({
    root: {
        width: '100%',
        marginTop: theme.spacing.unit * 3,
        overflowX: 'auto',
    },
    table: {
        minWidth: 700,
    },
});

class UserTable extends Component {

    state = {
        data: []
    };

    componentWillMount = () => {
        new AxiosClient().getData(this.props.keycloak, (data) => {
            this.setState({data: data})
        })
    };

    render() {
        const {classes} = this.props;
        return (
            <Paper className={classes.root}>
                <Table className={classes.table}>
                    <TableHead>
                        <TableRow>
                            <TableCell>Username</TableCell>
                            <TableCell>Date</TableCell>
                            <TableCell>Time In</TableCell>
                            <TableCell>Time Out</TableCell>
                            <TableCell>Hours Worked</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {this.state.data.map(data => {
                            return (
                                <TableRow key={data.id}>
                                    <TableCell>{data.username}</TableCell>
                                    <TableCell>{data.date}</TableCell>
                                    <TableCell >{data.timeIn}</TableCell>
                                    <TableCell >{data.timeOut}</TableCell>
                                    <TableCell >{data.hoursWorked}</TableCell>
                                </TableRow>
                            );
                        })}
                    </TableBody>
                </Table>
            </Paper>
        );
    }
}

UserTable.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(UserTable);