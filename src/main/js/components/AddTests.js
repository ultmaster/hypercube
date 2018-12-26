import React from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Grid from "@material-ui/core/Grid";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Checkbox from "@material-ui/core/Checkbox";
import SetChecker from "../containers/SetChecker";
import Dropzone from "react-dropzone";
import Axios from "axios";

export const ADD_TESTS_DIALOG = "ADD_TESTS_DIALOG";

export class AddTests extends React.Component {

  state = {
    files: []
  };

  handleChange = (event) => {
    console.log(event.target.name);
    console.log(event.target.value);
    this.setState({[event.target.name]: event.target.value});
  };

  onDrop(files) {
    this.setState({files});
  }

  onCancel() {
    this.setState({
      files: []
    });
  }

  formRef = React.createRef();

  onSubmit = () => {
    let data = new FormData(this.formRef.current);
    console.log(data);
    let config = {
      onUploadProgress: function(progressEvent) {
        let percentCompleted = Math.round( (progressEvent.loaded * 100) / progressEvent.total );
        console.log(percentCompleted);
      }
    };
    Axios.post('/api/tests/add', data, config)
      .then(function (res) {
        console.log("then");
        console.log(res);
        // output.className = 'container';
        // output.innerHTML = res.data;
      })
      .catch(function (err) {
        console.log("error");
        console.log(err);
      });
  };

  render() {
    const files = this.state.files.map(file => (
      <li key={file.name}>
        {file.name} - {file.size} bytes
      </li>
    ));

    return (
      <form ref={this.formRef}>
        <Dropzone
          onDrop={this.onDrop.bind(this)}
          onFileDialogCancel={this.onCancel.bind(this)}
        >
          {({getRootProps, getInputProps}) => {
            return (
              <div {...getRootProps()}>
                <input {...getInputProps()} name="files"/>
                <p>Drop files here, or click to select files</p>
              </div>
            )
          }}
        </Dropzone>
        <aside>
          <h4>Files</h4>
          <ul>{files}</ul>
        </aside>
        <Button variant={"contained"} onClick={this.onSubmit.bind(this)}>
          Upload
        </Button>
      </form>
    );
  }
}