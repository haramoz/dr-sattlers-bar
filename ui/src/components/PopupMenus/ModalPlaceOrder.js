import React, { useState } from 'react';
import { Button, Modal } from 'semantic-ui-react';
import menu4 from "../../img/menu4.png"

function ModalPlaceOrder() {
    const [open, setOpen] = useState(false);

    return (
        <Modal
            onClose={() => setOpen(false)}
            onOpen={() => setOpen(true)}
            open={open}
            trigger={<Button className="img-button" id="menu4btn" data-tooltip="Place Order" data-inverted="" data-position="bottom center" >
                <img src={menu4} id="menu4" alt="menu4" className="menu4"
                />
            </Button>}
        >
            <Modal.Header>Place Order</Modal.Header>
            <Modal.Content>
                <p>Place Order Service goes here.</p>
            </Modal.Content>
            <Modal.Actions>
                <Button onClick={() => setOpen(false)}>Close</Button>
            </Modal.Actions>
        </Modal>
    );
}

export default ModalPlaceOrder;
