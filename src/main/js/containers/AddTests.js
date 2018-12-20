import React from 'react'
import {connect} from 'react-redux'
import {ADD_TESTS_DIALOG, AddTests} from "../components/AddTests";
import {closeModal} from "../actions";

const mapStateToProps = state => ({
  open: state.modals.indexOf(ADD_TESTS_DIALOG) >= 0
});
const mapDispatchToProps = dispatch => ({
  // dispatchWorkingDir: workingDir => dispatch(setWorkingDir(workingDir)),
  dispatchClose: () => dispatch(closeModal(ADD_TESTS_DIALOG))
});

export default connect(mapStateToProps, mapDispatchToProps)(AddTests)