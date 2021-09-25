import React, {useState} from 'react';
import { Form, Button } from 'react-bootstrap';
import styles from "../css/Login.css";


const LoginForm = ({Login, error}) => {

    const [details, setDetails] = useState({
        email: "",
        password: ""
    })

    const handleInput = (e) => {
        const {name, value} = e.target;
        setDetails({...details, [name]: value}); 
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        Login(details);

    }

    return (
        // <form className="form-inner" onSubmit={handleSubmit}>
        //     <h2>Log in</h2>
        //     {(error != "") ? (<div className="error">{error}</div>) : ""}
        //     <div className="form-group">
        //         <label htmlFor="email">Email: </label>
        //         <input type="email" name="email" id="email" onChange={handleInput} />
        //     </div>
        //     <div className="form-group">
        //         <label htmlFor="password">Password: </label>
        //         <input type="password" name="password" id="password" onChange={handleInput}/>
        //     </div>
        //     <input type="submit" value="Log in" />
        // </form>

        <Form className="loginForm">
        <Form.Group className="mb-3" controlId="formBasicEmail">
        <Form.Label className="loginForm-lb">Email address</Form.Label>
        <Form.Control name="email" type="email" placeholder="Enter email" onChange={handleInput}/>
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicPassword">
        <Form.Label className="loginForm-lb">Password</Form.Label>
        <Form.Control name="password" type="password" placeholder="Password" onChange={handleInput} />
        </Form.Group>
        <Form.Group className="mb-3" controlId="formBasicCheckbox">
        <Form.Check className="loginForm-rememberMe" type="checkbox" name="rememberMe" label="Remember me" />
        </Form.Group>
        <Button variant="primary" type="submit">
        Log in
        </Button>
        </Form>
        
    );
}

export default LoginForm;