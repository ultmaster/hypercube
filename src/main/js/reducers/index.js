import { OPEN_MODAL, CLOSE_MODAL, UPDATE_ALL } from '../actions';

const initialState = {
  workingDirectory: null,
  modals: []
};

export default (state = initialState, action) => {
  switch (action.type) {
    case OPEN_MODAL:
      return {
        ...state,
        modals: state.modals.concat(action.obj)
      };
    case CLOSE_MODAL:
      return {
        ...state,
        modals: state.modals.filter(item => item.id !== action.obj.id),
      };
    case UPDATE_ALL:
      return {
        ...state,
        ...action.payload
      };
    default:
      return state;
  }
};
