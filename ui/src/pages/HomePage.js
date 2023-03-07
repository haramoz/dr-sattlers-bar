import React from "react";
import maiden from "../img/bar-maiden.png"

const HomePage = () => {
    return (
        <div>
            <div className="welcome-text"> Ciao!</div>
            <img src={maiden} alt="Hey!" className="maiden"/>
        </div>
    )
}

export default HomePage