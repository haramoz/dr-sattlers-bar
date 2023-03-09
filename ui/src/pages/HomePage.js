import React, { useRef, useState } from "react";
import maiden from "../img/bar-maiden.png";
import menu1 from "../img/menu1.png";
import menu2 from "../img/menu2.png";
import menu3 from "../img/menu3.png";
import menu5 from "../img/menu5.png";
import menu6 from "../img/menu6.png";
import soundFile from '../sounds/medieval-life.mp3';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faVolumeUp, faVolumeMute } from '@fortawesome/free-solid-svg-icons';
import ReactTooltip from 'react-tooltip';
//import Modal from "react-modal";
import { Button, Modal } from 'semantic-ui-react'

function exampleReducer(state, action) {
  switch (action.type) {
    case 'OPEN_MODAL':
      return { open: true, dimmer: action.dimmer }
    case 'CLOSE_MODAL':
      return { open: false }
    default:
      throw new Error()
  }
}

function HomePage() {
  const audioRef = useRef(null);
  const [isMuted, setIsMuted] = useState(false);
  const [showSubMenu, setShowSubMenu] = useState(false);

  const [state, dispatch] = React.useReducer(exampleReducer, {
    open: false,
    dimmer: undefined,
  })
  const { open, dimmer } = state

  const handleSubMenuClick = () => {
    setShowSubMenu(!showSubMenu);
  };

  const handleClickOutside = (e) => {
    setShowSubMenu(false);
  };


  const toggleSound = () => {
    if (!audioRef.current.paused) {
      audioRef.current.pause();
    } else {
      audioRef.current.play();
    }
    setIsMuted(!isMuted);
  }


  return (
    <div className="menu-container" onClick={handleClickOutside}>
      <div className="welcome-text"> Welcome! </div>

      {showSubMenu && (
        <div className="sub-menu">
          <img src={menu3} id="menu1" alt="menu1" className="menu1" data-tip="Pay Bill"
            onClick={() => dispatch({ type: 'OPEN_MODAL', dimmer: 'blurring' })} />       

          <Modal
            dimmer={dimmer}
            open={open}
            onClose={() => dispatch({ type: 'CLOSE_MODAL' })}
          >
            <Modal.Header>Use Google's location service?</Modal.Header>
            <Modal.Content>
              Let Google help apps determine location. This means sending anonymous
              location data to Google, even when no apps are running.
            </Modal.Content>
            <Modal.Actions>
              <Button negative onClick={() => dispatch({ type: 'CLOSE_MODAL' })}>
                Disagree
              </Button>
              <Button positive onClick={() => dispatch({ type: 'CLOSE_MODAL' })}>
                Agree
              </Button>
            </Modal.Actions>
          </Modal>

          <img src={menu2} id="menu2" alt="menu2" className="menu2" data-tip="Order Status" />
          <img src={menu1} id="menu3" alt="menu3" className="menu3" data-tip="Find Table" />
          <img src={menu5} id="menu5" alt="menu4" className="menu5" data-tip="Place Order" />
          <img src={menu6} id="menu6" alt="menu5" className="menu6" data-tip="Get Menu" />
          <ReactTooltip effect="solid" place="bottom" type="info" />

        </div>
      )}
      <img src={maiden} alt="Hey!" className="maiden" onMouseEnter={handleSubMenuClick} />

      <FontAwesomeIcon icon={isMuted ? faVolumeMute : faVolumeUp} onClick={toggleSound} className="music-home-page" />
      <audio ref={audioRef} src={soundFile} />

    </div>
  );
}

export default HomePage