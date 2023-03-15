import { createStore } from 'redux'

const initialState = {
  notifications: []
}

function reducer(state = initialState, action) {
  switch (action.type) {
    case 'ADD_NOTIFICATION':
      return {
        ...state,
        notifications: [...state.notifications, action.payload]
      }
    case 'UPDATE_NOTIFICATION':
      return {
        ...state,
        notifications: state.notifications.map(notification => {
          if (notification.id === action.payload.id) {
            return {
              ...notification,
              ...action.payload
            }
          }
          return notification
        })
      }
    case 'DELETE_NOTIFICATION':
      return {
        ...state,
        notifications: state.notifications.filter(notification => notification.id !== action.payload.id)
      }
    default:
      return state
  }
}

const store = createStore(reducer)
