import React from "react";
import {withStyles} from "@material-ui/core";
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Checkbox from "@material-ui/core/Checkbox";
import SetChecker from "../containers/SetChecker";

const styles = theme => ({
  chartContainer: {
    marginLeft: -22,
  },
  tableContainer: {
    height: 320,
  }
});

class Dashboard extends React.Component {

  render() {
    const {classes, problem} = this.props;

    return (
      <Grid container spacing={24}>
        <Grid item xs={12} md={6}>
          <TextField required
                     id="timeLimit"
                     label="Time limit"
                     helperText="Between 500 ms and 30000 ms"
                     fullWidth
                     defaultValue={problem.timeLimit}/>
        </Grid>
        <Grid item xs={12} md={6}>
          <TextField required
                     id="memoryLimit"
                     label="Memory limit"
                     helperText="Between 4 MB and 2048 MB"
                     defaultValue={problem.memoryLimit}
                     fullWidth/>
        </Grid>
        <Grid item xs={12} md={6}>
          <FormControlLabel
            control={<Checkbox color="secondary" name="interactive" value="yes" defaultChecked={problem.interaction.enabled}/>}
            label="Is problem interactive"
          />
        </Grid>
        <Grid item xs={12} md={6}>
          <FormControlLabel
            control={<Checkbox color="secondary" name="interactive" value="yes" defaultChecked={problem.points.enabled}/>}
            label="Points enabled"
          />
        </Grid>
        <Grid item xs={12} md={6}>
          <SetChecker/>
        </Grid>
      </Grid>
    );
  }
}

export default withStyles(styles)(Dashboard);