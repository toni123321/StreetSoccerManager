import React, {useState} from "react";
import Collapse from 'react-bootstrap/Collapse'
import Button from 'react-bootstrap/Button'


function ReservesPanel() {
    const [open, setOpen] = useState(false);
  
    return (
      <div style={open ? {position: "absolute"}: {position: "unset"}}>
        <Button
          onClick={() => setOpen(!open)}
          aria-controls="example-collapse-text"
          aria-expanded={open}
          
        >
          click
        </Button>
        <div >
          <Collapse in={open} dimension="width">
            <div id="example-collapse-text">
              <div style={{width: '100%'}}>
                  Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus
                  terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer
                  labore wes anderson cred nesciunt sapiente ea proident.
              </div>
            </div>
          </Collapse>
        </div>
        
        
      </div>
    );
}

export default ReservesPanel;
