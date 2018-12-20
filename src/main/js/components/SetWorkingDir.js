import React from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';

export const SET_WORKING_DIR_DIALOG = "SET_WORKING_DIR_DIALOG";

export class SetWorkingDir extends React.Component {

  handleCancel = () => {
    this.props.dispatchClose();
  }

  inputText = React.createRef();
  handleOK = () => {
    const workingDir = this.inputText.current.value;
    this.props.dispatchWorkingDir(workingDir).done(() => {
      this.props.dispatchClose();
    })
  };

  render() {
    return (
      <div>
        <Dialog
          open={this.props.open}
          onClose={this.handleClose}
          aria-labelledby="form-dialog-title"
        >
          <DialogTitle id="form-dialog-title">Set Working Directory</DialogTitle>
          <DialogContent>
            <DialogContentText>
              Set working directory will reload the whole project.
            </DialogContentText>
            <TextField
              autoFocus
              margin="dense"
              id="path"
              label="Directory full path"
              fullWidth
              inputRef={this.inputText}
            />
          </DialogContent>
          <DialogActions>
            <Button onClick={this.handleCancel} color="primary">
              Cancel
            </Button>
            <Button onClick={this.handleOK} color="primary">
              OK
            </Button>
          </DialogActions>
        </Dialog>
      </div>
    );
  }
}