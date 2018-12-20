import React from "react";
import {withStyles} from "@material-ui/core";
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Checkbox from "@material-ui/core/Checkbox";
import SetChecker from "../containers/SetChecker";
import Button from "@material-ui/core/Button";

const styles = theme => ({
  chartContainer: {
    marginLeft: -22,
  },
  tableContainer: {
    height: 320,
  }
});

class Dashboard extends React.Component {

  constructor(props) {
    super(props);
    this.state = {...props.problem};
  }

  update = (event) => {
    const path = event.target.name.split('.');
    const depth = path.length;
    const oldstate = this.state;
    const newstate = { ...oldstate };
    let newStateLevel = newstate;
    let oldStateLevel = oldstate;

    for (let i = 0; i < depth; i += 1) {
      if (i === depth - 1) {
        newStateLevel[path[i]] = event.target.value;
      } else {
        newStateLevel[path[i]] = { ...oldStateLevel[path[i]] };
        oldStateLevel = oldStateLevel[path[i]];
        newStateLevel = newStateLevel[path[i]];
      }
    }
    this.setState(newstate);
  };

  handleSave = () => {
    console.log(this.state);
    this.props.save(this.state);
  };

  render() {
    const {classes, problem} = this.props;

    return (
      <Grid container spacing={24}>
        <Grid item xs={12} md={6}>
          <TextField required
                     onChange={this.update.bind(this)}
                     name="timeLimit"
                     label="Time limit"
                     helperText="Between 500 ms and 30000 ms"
                     fullWidth
                     type="number"
                     defaultValue={problem.timeLimit}/>
        </Grid>
        <Grid item xs={12} md={6}>
          <TextField required
                     name="memoryLimit"
                     onChange={this.update.bind(this)}
                     label="Memory limit"
                     fullWidth
                     type="number"
                     helperText="Between 4 MB and 2048 MB"
                     defaultValue={problem.memoryLimit}/>
        </Grid>
        <Grid item xs={12} md={6}>
          <FormControlLabel
            control={<Checkbox color="secondary" name="interaction.enabled" value="yes" defaultChecked={problem.interaction.enabled}/>}
            label="Is problem interactive"
          />
        </Grid>
        <Grid item xs={12} md={6}>
          <FormControlLabel
            control={<Checkbox color="secondary" name="points.enabled" value="yes" defaultChecked={problem.points.enabled}/>}
            label="Points enabled"
          />
        </Grid>
        <Grid item xs={12} md={6}>
          <SetChecker/>
        </Grid>
        <Grid item xs={12} md={12}>
          <Button variant="contained" color="primary" onClick={this.handleSave}>
            Save
          </Button>
        </Grid>
      </Grid>
    );
  }
}

export default withStyles(styles)(Dashboard);