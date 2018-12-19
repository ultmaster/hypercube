import React from 'react'
import { connect } from 'react-redux'
import Dashboard from '../components/Dashboard';
import { SET_WORKING_DIR_DIALOG, SetWorkingDir } from "../components/SetWorkingDir";
import { setWorkingDir, closeModal } from "../actions";

const mapStateToProps = state => ({
  problem: state.problem
});
const mapDispatchToProps = dispatch => ({
});

export default connect(mapStateToProps, mapDispatchToProps)(Dashboard)