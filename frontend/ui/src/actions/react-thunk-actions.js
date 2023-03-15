import { addNotification } from ".";


export function loadNotifications() {
    return async function(dispatch, getState) {
      const response = await fetch('/api/notifications')
      const notifications = await response.json()
      notifications.forEach(notification => {
        dispatch(addNotification(notification))
      })
    }
  }