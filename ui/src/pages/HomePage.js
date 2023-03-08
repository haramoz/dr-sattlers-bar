import React, { useRef, useState } from "react";
import maiden from "../img/bar-maiden.png"
import soundFile from '../sounds/medieval-life.mp3';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faVolumeUp, faVolumeMute } from '@fortawesome/free-solid-svg-icons';

function HomePage() {
  const audioRef = useRef(null);
  const [isMuted, setIsMuted] = useState(false);
  


  const toggleSound = () => {
    if (!audioRef.current.paused) {
      audioRef.current.pause();
    } else {
      audioRef.current.play();
    }
    setIsMuted(!isMuted);
  }


  return (
    <div>
      <div className="welcome-text"> Welcome! </div>
      <img src={maiden} alt="Hey!" className="maiden" />
      <FontAwesomeIcon icon={isMuted ? faVolumeMute : faVolumeUp} onClick={toggleSound} className="music-home-page" />
      <audio ref={audioRef} src={soundFile} />

    </div>
  );
}

export default HomePage