import React, { useState } from 'react';
import { Button, Modal } from 'semantic-ui-react';
import menu3 from "../../img/menu3.png";
import ModalHeader from "./ModalHeaders.js"

function ModalMenu() {
    const [open, setOpen] = useState(false);

    return (
        <Modal
            onClose={() => setOpen(false)}
            onOpen={() => setOpen(true)}
            open={open}
            trigger={<Button className="img-button" id="menu3btn" data-tooltip="Find Table" data-inverted="" data-position="bottom center" >
                <img src={menu3} id="menu3" alt="menu3" className="menu3"
                />
            </Button>}
        >
            <ModalHeader/>
            <Modal.Content>
                <p>List of tables goes here.</p>
            </Modal.Content>
            <Modal.Actions>
                <Button onClick={() => setOpen(false)}>Close</Button>
            </Modal.Actions>
        </Modal>
    );
}

export default ModalMenu;
