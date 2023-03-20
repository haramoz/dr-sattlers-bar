import React, { useState } from 'react';
import { Button, Modal } from 'semantic-ui-react';
import menu1 from "../../img/menu1.png";
import ModalHeader from "./ModalHeaders.js"
import GetTablesDropdown from "../SelectTable"

function ModalMenu() {
    const [open, setOpen] = useState(false);

    return (
        <Modal
            onClose={() => setOpen(false)}
            onOpen={() => setOpen(true)}
            open={open}
            trigger={<Button className="img-button" id="menu1btn" data-tooltip="Pay Bill" data-inverted="" data-position="bottom center">
                <img src={menu1} id="menu1" alt="menu1" className="menu1"
                />
            </Button>}
        >
            <ModalHeader/>
            <Modal.Content>
                <p>Please select your table to pay the Bill.</p>
                <GetTablesDropdown/>
            </Modal.Content>
            <Modal.Actions>
                <Button onClick={() => setOpen(false)}>Close</Button>
            </Modal.Actions>
        </Modal>
    );
}

export default ModalMenu;
