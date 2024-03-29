//created by petar.petrov, updated by Abby Turner

/* 
 * This file contains all the description of style, form, and action
 * of the Child react component, DataTable.
 */

import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {withStyles} from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

// Styling for the DataTable component
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
// Child Component of App, which displays the data passed to it from the parent via props
class DataTable extends Component {

    // The render method is called by React whenever the data model updates
    render() {
        console.log("This is the data in the DataTable:")
        console.log(this.props.data);
        const {classes} = this.props;
        return (
            <div>
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
                            {this.props.data.map(row => {
                                return (
                                    <TableRow key={row.id}>
                                        <TableCell>{row.username}</TableCell>
                                        <TableCell>{row.date}</TableCell>
                                        <TableCell>{row.timeIn}</TableCell>
                                        <TableCell>{row.timeOut}</TableCell>
                                        <TableCell>{row.hoursWorked}</TableCell>
                                    </TableRow>
                                );
                            })}
                        </TableBody>
                    </Table>
                </Paper>
            </div>
        );
    }
}

DataTable.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(DataTable);