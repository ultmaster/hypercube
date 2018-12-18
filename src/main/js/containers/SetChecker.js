import React from "react";
import MenuItem from "@material-ui/core/MenuItem";
import Select from "@material-ui/core/Select";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";

class SetChecker extends React.Component {
  constructor(props) {
    super(props);
    this.state = {setChecker: ""};
  }

  handleChange = event => {
    this.setState({[event.target.name]: event.target.value});
  };

  render() {
    return (
      <FormControl fullWidth={true}>
        <InputLabel htmlFor="idSetChecker">Checker</InputLabel>
        <Select
          autoWidth={true}
          value={this.state.setChecker}
          onChange={this.handleChange}
          inputProps={{
            name: "setChecker",
            id: "idSetChecker",
          }}
        >
          <MenuItem value="">
            <em>None</em>
          </MenuItem>
          <MenuItem value={10}>Ten</MenuItem>
          <MenuItem value={20}>Twenty</MenuItem>
          <MenuItem value={30}>Thirty</MenuItem>
        </Select>
      </FormControl>
    )
  }
}

export default SetChecker
