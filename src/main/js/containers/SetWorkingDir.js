import React from 'react'
import { connect } from 'react-redux'
import { SET_WORKING_DIR_DIALOG, SetWorkingDir } from "../components/SetWorkingDir";
import { setWorkingDir, closeModal } from "../actions";

const mapStateToProps = state => ({
  open: state.modals.indexOf(SET_WORKING_DIR_DIALOG) >= 0
});
const mapDispatchToProps = dispatch => ({
  dispatchWorkingDir: workingDir => dispatch(setWorkingDir(workingDir)),
  dispatchClose: () => dispatch(closeModal(SET_WORKING_DIR_DIALOG))
});

export default connect(mapStateToProps, mapDispatchToProps)(SetWorkingDir)