import { SIGN_IN, SIGN_OUT } from './types';

export const signIn = userId => {
  return {
    type: SIGN_IN,
    payload: userId
  };
};

export const signOut = () => {
  return {
    type: SIGN_OUT
  };
};

export function addNotification(notification) {
  return {
    type: 'ADD_NOTIFICATION',
    payload: notification
  }
}

export function updateNotification(notification) {
  return {
    type: 'UPDATE_NOTIFICATION',
    payload: notification
  }
}

export function deleteNotification(notification) {
  return {
    type: 'DELETE_NOTIFICATION',
    payload: notification
  }
}

