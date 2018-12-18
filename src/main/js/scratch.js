const { applyMiddleware, createStore, combineReducers, bindActionCreators } = Redux;
const { Provider, connect } = ReactRedux;
const { render } = ReactDOM;
const { Component } = React;

/* --- CONSTANTS --- */
const OPEN_MODAL = 'OPEN_MODAL';
const CLOSE_MODAL = 'CLOSE_MODAL';

/* --- REDUCERS --- */
const initialState = {
  modals: [],
}

function reducer(state = initialState, action) {
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
    default:
      return state;
  }
};

/* --- ACTIONS --- */
const openModal = (obj) => {
  return {
    type: OPEN_MODAL,
    obj,
  }
}
const closeModal = (obj) => {
  return {
    type: CLOSE_MODAL,
    obj,
  }
}

/* --- COMPONENTS --- */
class Modal extends Component {
  onClose(){
    if(this.props.item.onClose){
      this.props.item.onClose();
      this.props.onClose(this.props.item);
    } else {
      this.props.onClose(this.props.item);
    }
  }
  onConfirm(){
    if(this.props.item.onConfirm){
      this.props.item.onConfirm();
      this.props.onClose(this.props.item);
    }
  }
  render() {
    const { zIndex } = this.props;
    const { type } = this.props.item;
    if (type === 'confirmation') {
      const { text } = this.props.item;
      return (
        <div className="modal-wrapper" style={{zIndex: (zIndex+1)*10}}>
          <div className="modal">
            <div className="text">{text}</div>
            <div className="buttons">
              <button className="modal-button" onClick={() => this.onConfirm()}>Confirm</button>
              <button className="modal-button" onClick={() => this.onClose()}>Close</button>
            </div>
          </div>
        </div>
      )
    } else if (type === 'custom') {
      const { content } = this.props.item;
      return (
        <div className="modal-wrapper" style={{zIndex: (zIndex+1)*10}}>
          <div className="modal">
            <button className="close" onClick={() => this.onClose()}>&times;</button>
            {content}
          </div>
        </div>
      )
    }
    return (
      <div></div>
    );
  }
}
class Modals extends Component {
  render() {
    const modals = this.props.modals.map((item,i) => <Modal item={item} key={i} zIndex={i} onClose={(item) => this.props.dispatch(closeModal(item))}/>)
    return (
      <div className={modals.length>0 ? "modals" : ""}>
        {modals}
      </div>
    );
  }
}
const ModalContainer = connect(
  function mapStateToProps(state) {
    return {
      modals: state.modals
    };
  },
  function mapDispatchToProps(dispatch) {
    return {
      dispatch
    }
  }
)(Modals);


class CustomModalContent extends Component {
  render() {
    return (
      <div className="modal-content">Custom Modal Content</div>
    )
  }
}
class App extends Component {
  render() {
    return (
      <div className="App">
        <button className="test-button" onClick={() => this.props.dispatch(openModal({
          id: uuid.v4(),
          type: 'confirmation',
          text: 'Are you sure to do this?',
          onClose: () => console.log("fire at closing event"),
          onConfirm: () => console.log("fire at confirming event"),
        }))}>Open confirmation modal</button>

        <button className="test-button" onClick={() => this.props.dispatch(openModal({
          id: uuid.v4(),
          type: 'custom',
          content: <CustomModalContent />
        }))}>Open custom modal</button>

        <ModalContainer />
      </div>
    );
  }
}

const AppContainer = connect(
  null,
  function mapDispatchToProps(dispatch) {
    return {
      dispatch,
    }
  }
)(App);

/* --- OTHER --- */

/* --- STORE --- */
const store = createStore(reducer);

// Render the app
render(
  <Provider store={store}>
    <AppContainer />
  </Provider>,
  document.getElementById('app')
);