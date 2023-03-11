import React, { useState } from 'react';
import { Button, Modal } from 'semantic-ui-react';
import menu2 from "../../img/menu2.png";

function ModalMenu() {
    const [open, setOpen] = useState(false);

    return (
        <Modal
            onClose={() => setOpen(false)}
            onOpen={() => setOpen(true)}
            open={open}
            trigger={<Button className="img-button" id="menu2btn" data-tooltip="Order Status" data-inverted="" data-position="bottom center">
                <img src={menu2} id="menu2" alt="menu2" className="menu2"
                />
            </Button>}
        >
            <Modal.Header>Order Status</Modal.Header>
            <Modal.Content>
                <p>Order Status goes here.</p>
            </Modal.Content>
            <Modal.Actions>
                <Button onClick={() => setOpen(false)}>Close</Button>
            </Modal.Actions>
        </Modal>
    );
}

export default ModalMenu;
