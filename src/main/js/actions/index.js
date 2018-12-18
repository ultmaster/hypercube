import client from "../utils/client";

export const SET_WORKING_DIR = "SET_WORKING_DIR";
export const OPEN_MODAL = "OPEN_MODAL";
export const CLOSE_MODAL = "CLOSE_MODAL";
export const UPDATE_ALL = "UPDATE_ALL";

export const openModal = (obj) => ({
  type: OPEN_MODAL,
  obj,
});

export const closeModal = (obj) => ({
  type: CLOSE_MODAL,
  obj,
});

export const updateAll = () => {
  return dispatch => {
    client({
      method: "GET",
      path: "/api/config",
      headers: {"Content-Type": "application/json"}
    }).then((response) => {
      dispatch({
        type: UPDATE_ALL,
        payload: response.entity
      });
    })
  }
};

export const setWorkingDir = (workingDir) => {
  return dispatch => {
    // dispatch(requestPosts(subreddit))

    return client({
      method: "POST",
      path: "/api/config",
      entity: {"workingDirectory": workingDir},
      headers: {"Content-Type": "application/json"}
    }).then(() => {
      dispatch(updateAll());
    })
  }
};