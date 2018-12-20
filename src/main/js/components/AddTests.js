import React from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';

export const ADD_TESTS_DIALOG = "ADD_TESTS_DIALOG";

export class AddTests extends React.Component {

  handleCancel = () => {
    this.props.dispatchClose();
  };

  handleOK = () => {
    this.props.dispatchClose();
  };

  render() {
    return (
      <Dialog
        open={this.props.open}
        onClose={this.handleClose}
      >
        <DialogTitle>Set Working Directory</DialogTitle>
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
    );
  }
}