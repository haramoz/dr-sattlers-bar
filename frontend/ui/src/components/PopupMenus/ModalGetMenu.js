import React, { useState } from 'react';
import { Button, Modal } from 'semantic-ui-react';
import menu5 from "../../img/menu5.png";
import Getmenu from "../Getmenu.js"

function ModalMenu() {
    const [open, setOpen] = useState(false);

    return (
        <Modal
            onClose={() => setOpen(false)}
            onOpen={() => setOpen(true)}
            open={open}
            trigger={<Button className="img-button" id="menu5btn" data-tooltip="Get Menu" data-inverted="" data-position="bottom center" >
                <img src={menu5} id="menu5" alt="menu5" className="menu5"
                />
            </Button>}
        >
            <Modal.Header>Get Menu</Modal.Header>
            <Modal.Content>
                <Getmenu/>
            </Modal.Content>
            <Modal.Actions>
                <Button onClick={() => setOpen(false)}>Close</Button>
            </Modal.Actions>
        </Modal>
    );
}

export default ModalMenu;
