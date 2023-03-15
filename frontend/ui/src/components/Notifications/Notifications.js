import React, { useEffect } from 'react'
import { connect } from 'react-redux'
import { loadNotifications } from './actions'

function NotificationList({ notifications, loadNotifications }) {
  useEffect(() => {
    loadNotifications()
  }, [loadNotifications])

  return (
    <div>
      {notifications.map(notification => (
        <div key={notification.id}>
          {notification.text}
        </div>
      ))}
    </div>
  )
}

export default  NotificationList;
