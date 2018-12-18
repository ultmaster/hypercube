import React from "react";
import Grid from "@material-ui/core/Grid";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Checkbox from "@material-ui/core/Checkbox";
import TextField from "@material-ui/core/TextField";
import Typography from "@material-ui/core/Typography";
import SetChecker from "./SetChecker";

class DashboardForm extends React.Component {
  render() {
    return (
      <Grid container spacing={24}>
        <Grid item xs={12} md={6}>
          <TextField required
                     id="timeLimit"
                     label="Time limit"
                     helperText="Between 500 ms and 30000 ms"
                     fullWidth/>
        </Grid>
        <Grid item xs={12} md={6}>
          <TextField required
                     id="memoryLimit"
                     label="Memory limit"
                     helperText="Between 4 MB and 2048 MB"
                     fullWidth/>
        </Grid>
        <Grid item xs={12} md={6}>
          <FormControlLabel
            control={<Checkbox color="secondary" name="interactive" value="yes"/>}
            label="Is problem interactive"
          />
        </Grid>
        <Grid item xs={12} md={6}>
          <FormControlLabel
            control={<Checkbox color="secondary" name="interactive" value="yes"/>}
            label="Points enabled"
          />
        </Grid>
        <Grid item xs={12} md={6}>
          <SetChecker/>
        </Grid>
      </Grid>
    )
  }
}

export default DashboardForm
