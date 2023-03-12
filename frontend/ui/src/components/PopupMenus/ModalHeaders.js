import React from 'react';
import logo from '../../img/logos/bar-logo-transparent.png';
import { Modal } from 'semantic-ui-react';



function ModalHeader() {
    return (
        <Modal.Header className='bar-menu-header'>
            <img src={logo} alt='logo' className='logo-menu'/>
        </Modal.Header>
        
    );
}

export default ModalHeader;
