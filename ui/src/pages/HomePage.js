import React, { useRef, useState } from "react";
import maiden from "../img/bar-maiden.png";
import soundFile from '../sounds/medieval-life.mp3';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faVolumeUp, faVolumeMute } from '@fortawesome/free-solid-svg-icons';
import ModalPlaceOrder from "../components/PopupMenus/ModalPlaceOrder.js";
import ModalOrderStatus from "../components/PopupMenus/ModalOrderStatus.js";
import ModalPayBill from "../components/PopupMenus/ModalPayBill";
import ModalFindTable from "../components/PopupMenus/ModalFindTable";
import ModalGetMenu from "../components/PopupMenus/ModalGetMenu";

function HomePage() {
  const audioRef = useRef(null);
  const [isMuted, setIsMuted] = useState(false);
  const [showSubMenu, setShowSubMenu] = useState(false);

  const handleSubMenuClick = () => {
    setShowSubMenu(!showSubMenu);
  };

  const handleClickOutside = (e) => {

    console.log("handle click outside is called");
    // Get a reference to the element that triggered the event
    const clickedElement = e.target;

    console.log(clickedElement);

    // Check if the clicked element should be ignored
    if (clickedElement.classList.contains("menu-container")) {
      console.log("inside");
      setShowSubMenu(false);
    }
    
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
      {!showSubMenu && (<div className="welcome-text"> Welcome! </div>)}

      {showSubMenu && (
        <div className="sub-menu ignore-click-outside">
          <ModalPayBill />
          <ModalOrderStatus />
          <ModalFindTable />
          <ModalPlaceOrder />
          <ModalGetMenu />
        </div>
      )}

      <img src={maiden} alt="Hey!" className="maiden" onMouseEnter={handleSubMenuClick} />

      <FontAwesomeIcon icon={isMuted ? faVolumeMute : faVolumeUp} onClick={toggleSound} className="music-home-page" />
      <audio ref={audioRef} src={soundFile} />

    </div>
  );
}

export default HomePage