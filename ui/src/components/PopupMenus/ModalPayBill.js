import React, { useState } from 'react';
import { Button, Modal } from 'semantic-ui-react';
import menu1 from "../../img/menu1.png";

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
            <Modal.Header>Pay Bill</Modal.Header>
            <Modal.Content>
                <p>Pay Bill goes here.</p>
            </Modal.Content>
            <Modal.Actions>
                <Button onClick={() => setOpen(false)}>Close</Button>
            </Modal.Actions>
        </Modal>
    );
}

export default ModalMenu;
