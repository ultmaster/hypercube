import React from "react";
import {withStyles} from "@material-ui/core";
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Checkbox from "@material-ui/core/Checkbox";
import SetChecker from "../containers/SetChecker";
import Button from "@material-ui/core/Button";

class Dashboard extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      timeLimit: props.problem.timeLimit,
      memoryLimit: props.problem.memoryLimit,
      pointsEnabled: props.problem.pointsEnabled
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange = (event) => {
    console.log(event.target.name);
    console.log(event.target.value);
    this.setState({[event.target.name]: event.target.value});
  };

  handleSubmit = () => {
    console.log(this.state);
    this.props.save(this.state);
  };

  render() {
    const {classes, problem} = this.props;

    return (
      <Grid container spacing={24}>
        <Grid item xs={12} md={6}>
          <TextField required
                     name="timeLimit"
                     label="Time limit"
                     helperText="Between 500 ms and 30000 ms"
                     onChange={this.handleChange.bind(this)}
                     fullWidth
                     type="number"
                     defaultValue={problem.timeLimit}/>
        </Grid>
        <Grid item xs={12} md={6}>
          <TextField required
                     name="memoryLimit"
                     label="Memory limit"
                     fullWidth
                     type="number"
                     helperText="Between 4 MB and 2048 MB"
                     onChange={this.handleChange.bind(this)}
                     defaultValue={problem.memoryLimit}/>
        </Grid>
        <Grid item xs={12} md={6}>
          <FormControlLabel
            control={<Checkbox color="secondary" name="pointsEnabled" value={true}
                               onChange={this.handleChange.bind(this)}
                               defaultChecked={problem.pointsEnabled}/>}
            label="Points enabled"
          />
        </Grid>
        <Grid item xs={12} md={6}>
          <SetChecker/>
        </Grid>
        <Grid item xs={12} md={12}>
          <Button variant="contained" color="primary" onClick={this.handleSubmit.bind(this)}>
            Save
          </Button>
        </Grid>
      </Grid>
    );
  }
}

const styles = theme => ({
  chartContainer: {
    marginLeft: -22,
  },
  tableContainer: {
    height: 320,
  }
});

export default withStyles(styles)(Dashboard);