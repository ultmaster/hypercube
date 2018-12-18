import React from "react";
import Paper from "@material-ui/core/Paper";
import Table from "@material-ui/core/Table";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import TableBody from "@material-ui/core/TableBody";
import Fab from "@material-ui/core/Fab";
import AddIcon from '@material-ui/icons/Add';
import { withStyles } from "@material-ui/core/styles";

const styles = theme => ({
  fab: {
    position: 'absolute',
    bottom: theme.spacing.unit * 3,
    right: theme.spacing.unit * 3,
  },
});

const mock = [];

for (let i = 1; i <= 50; ++i) {
  let a = Math.floor(Math.random() * 10);
  let b = Math.floor(Math.random() * 20);
  let inputData = a + " " + b;
  mock.push({
    seq: i,
    inputPreview: inputData,
    outputPreview: "" + (a + b),
    length: inputData.length,
    sample: i === 1
  });
}

class Tests extends React.Component {
  constructor(props) {
    super(props);
    this.state = {"tests": mock};
  }

  render() {
    const { classes } = this.props;

    return (
      <React.Fragment>
        <Paper>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell numeric>#</TableCell>
                <TableCell>Input</TableCell>
                <TableCell>Output</TableCell>
                <TableCell numeric>Length</TableCell>
                <TableCell>Sample</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {this.state.tests.map(n => {
                return (
                  <TableRow key={n.seq}>
                    <TableCell numeric component="th" scope="row">
                      {n.seq}
                    </TableCell>
                    <TableCell><pre>{n.inputPreview}</pre></TableCell>
                    <TableCell><pre>{n.outputPreview}</pre></TableCell>
                    <TableCell numeric>{n.length}</TableCell>
                    <TableCell>{n.sample ? "Yes" : "No"}</TableCell>
                  </TableRow>
                );
              })}
            </TableBody>
          </Table>
        </Paper>
        <Fab color="secondary" className={classes.fab}>
          <AddIcon/>
        </Fab>
      </React.Fragment>
    );
  }
}

export default withStyles(styles)(Tests)