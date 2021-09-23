import React, {useState} from "react";
import LoginForm from "./LoginForm";

function Login() {

    const [user, setUser] = useState({
        email: "test@gmail.com",
        password: "test"
    });

    const [error, SetError] = useState("");

    const LogIn = () => {
        console.log("log in");
    }

    const LogOut = () => {
        console.log("log out");
    }


    return (
        <div>
            <LoginForm LogIn={LogIn} error={error}/>
        </div>
    );
}
export default Login;