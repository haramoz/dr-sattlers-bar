import React, { useState } from 'react';
import { Button, Modal } from 'semantic-ui-react';
import menu3 from "../../img/menu3.png";

function ModalMenu() {
    const [open, setOpen] = useState(false);

    return (
        <Modal
            onClose={() => setOpen(false)}
            onOpen={() => setOpen(true)}
            open={open}
            trigger={<Button className="ui button" id="menu3btn" data-tooltip="Find Table" data-position="top left" >
                <img src={menu3} id="menu3" alt="menu3" className="menu3"
                />
            </Button>}
        >
            <Modal.Header>Find Table</Modal.Header>
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
