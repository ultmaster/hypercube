import React from 'react'
import {connect} from 'react-redux'
import Dashboard from '../components/Dashboard';
import {updateDashboard} from "../actions";

const mapStateToProps = state => ({
  problem: state.problem
});
const mapDispatchToProps = dispatch => ({
  save: data => dispatch(updateDashboard(data))
});

export default connect(mapStateToProps, mapDispatchToProps)(Dashboard)