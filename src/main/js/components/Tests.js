import React from "react";
import Paper from "@material-ui/core/Paper";
import Table from "@material-ui/core/Table";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import TableBody from "@material-ui/core/TableBody";
import Fab from "@material-ui/core/Fab";
import AddIcon from '@material-ui/icons/Add';
import {withStyles} from "@material-ui/core/styles";
import {closeModal} from "../actions";
import {ADD_TESTS_DIALOG} from "../components/AddTests";
import AddTests from "../containers/AddTests";
import {Link} from "react-router-dom";

const styles = theme => ({
  fab: {
    position: 'absolute',
    bottom: theme.spacing.unit * 3,
    right: theme.spacing.unit * 3,
  },
});

class Tests extends React.Component {

  render() {
    const {classes, tests} = this.props;

    return (
      <React.Fragment>
        <Paper>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell numeric>#</TableCell>
                <TableCell>Input</TableCell>
                <TableCell>Output</TableCell>
                <TableCell>Length</TableCell>
                <TableCell>Description</TableCell>
                {/*<TableCell>Sample</TableCell>*/}
              </TableRow>
            </TableHead>
            <TableBody>
              {tests.map((test) => {
                return (
                  <TableRow key={test.fileName}>
                    <TableCell numeric component="th" scope="row">
                      {parseInt(test.fileName)}
                    </TableCell>
                    <TableCell>
                      <pre>{test.inputPreview}</pre>
                    </TableCell>
                    <TableCell>
                      <pre>{test.outputPreview}</pre>
                    </TableCell>
                    <TableCell numeric>{test.length}</TableCell>
                    <TableCell>{test.description}</TableCell>
                    {/*<TableCell>{test.sample ? "Yes" : "No"}</TableCell>*/}
                  </TableRow>
                );
              })}
            </TableBody>
          </Table>
        </Paper>
        <Fab color="secondary" className={classes.fab} component={props => <Link {...props} to="/tests/add"/>}>
          <AddIcon/>
        </Fab>
      </React.Fragment>
    );
  }
}

export default withStyles(styles)(Tests)