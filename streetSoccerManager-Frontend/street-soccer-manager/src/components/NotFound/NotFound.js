import React from 'react';
import notFoundImg from '../../resources/not-found.jpg';
import styles from "../../css/NotFound.scss";

function NotFound(props) {
    return (
        <div className="not-found-parent">
        <div className="not-found-container">
            <div className="not-found-img">
                <img src={notFoundImg} alt="Image not found" />
            </div>
            <div className="not-found-text">
            <h1>404</h1>
            <h2>UH OH! You're lost.</h2>
            <p>
                The page you are looking for does not exist.
                How you got here is a mystery. But you can use the menu on top to go back.
            </p>
            </div>
            
        </div>
        </div>
    );
}

export default NotFound;