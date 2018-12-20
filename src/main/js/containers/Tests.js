import Tests from "../components/Tests";
import {openModal} from "../actions";
import {ADD_TESTS_DIALOG} from "../components/AddTests";
import {connect} from "react-redux";

const mapDispatchToProps = dispatch => ({
  dispatchAdd: () => dispatch(openModal(ADD_TESTS_DIALOG))
});

export default connect(null, mapDispatchToProps)(Tests)
