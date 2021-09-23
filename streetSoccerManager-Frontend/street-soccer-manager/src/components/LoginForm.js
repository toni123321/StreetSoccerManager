import React from 'react';
import { useHistory } from 'react-router-dom';


const LoginForm = () => {

    const history = useHistory();
    const handleSubmit = (e) => {
        e.preventDefault();
        //history.push('/createTeam');

    }

    return (
        <form onSubmit={handleSubmit}>
            <h2>Log in</h2>
            <div className="form-group">
                <label htmlFor="email">Email: </label>
                <input type="email" name="email" id="email" />
            </div>
            <div className="form-group">
                <label htmlFor="password">Password: </label>
                <input type="password" name="password" id="password" />
            </div>
            <input type="submit" value="Log in" />
        </form>
        
    );
}

export default LoginForm;