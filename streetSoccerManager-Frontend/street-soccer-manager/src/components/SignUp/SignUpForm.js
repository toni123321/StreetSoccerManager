import React, {useState, useRef} from 'react';
import { Nav } from 'react-bootstrap';
import {LinkContainer} from "react-router-bootstrap";
import styles from "../../css/Login.scss";
import { useForm } from "react-hook-form";

const SignUpForm = ({handleRegister}) => {
    const detailsState = {
        email: "",
        password: "",
        firstName: "",
        lastName: "",
        nickname: "",
        role: "USER"
    }

    const [details, setDetails] = useState(detailsState)

    const {
        register,
        handleSubmit,
        watch,
        formState: { errors }
    } = useForm();
    
    const onSubmit = (data) => {
        //alert(JSON.stringify(data));
        handleRegister(details);
        setDetails(detailsState);
    };


    const handleInput = (e) => {
        const {name, value} = e.target;
        setDetails({...details, [name]: value}); 
    }

    return (
        <div className="login-wrap">
            <div className="login-html">
                <LinkContainer to="/loginPage">
                    <Nav.Link className="singInUp-links">Sign in</Nav.Link>
                </LinkContainer>

                <LinkContainer to="/signUp">
                    <Nav.Link className="singInUp-links">Sign up</Nav.Link>
                </LinkContainer>
                
                <div className="login-form">
                <form className="sign-up-htm" id="sign-up" onSubmit={handleSubmit(onSubmit)}>
                    <div className="group">
                        <label htmlFor="email" className="label">Email Address</label>
                        <input id="email" placeholder='email...' type="text" className="input" name="email"
                        {...register("email", {
                            required: true,
                            maxLength: 50,
                            pattern: /^[^@]+@[^@]+\.[^@]+$/i
                            })}
                            onChange={handleInput}
                        />
                        {errors?.email?.type === "required" && <p>This field is required</p>}
                        {errors?.email?.type === "pattern" && (
                            <p>Email is not valid</p>
                        )}
                    </div>
                    <div className="group">
                        <label htmlFor="nickname" className="label">Nickname</label>
                        <input id="nickname" placeholder='nickname...' type="text" className="input" name="nickname"
                            {...register("nickname", {
                            required: true,
                            maxLength: 20,
                            minLength: 4,
                            pattern: /^[A-Za-z0-9]+$/i
                            })}
                            onChange={handleInput}
                        />
                        {errors?.nickname?.type === "required" && <p>This field is required</p>}
                        {errors?.nickname?.type === "minLength" && <p>The nickname is too short</p>}
                        {errors?.nickname?.type === "pattern" && (
                            <p>Number or letters only</p>
                        )}
                    </div>
                    <div className="group">
                        <label htmlFor="firstName" className="label">First name</label>
                        <input id="firstName" placeholder='first name...' type="text" className="input" name="firstName"
                            {...register("firstName", {
                            required: true,
                            maxLength: 20,
                            pattern: /^[A-Za-z]+$/i
                            })}
                            onChange={handleInput}
                        />
                        {errors?.firstName?.type === "required" && <p>This field is required</p>}
                        {errors?.firstName?.type === "pattern" && (
                            <p>Alphabetical characters only</p>
                        )} 
                    </div>

                    <div className="group">
                        <label htmlFor="lastName" className="label">Last name</label>
                        <input id="lastName" placeholder='last name...' type="text" className="input" name="lastName"
                            {...register("lastName", {
                            required: true,
                            maxLength: 20,
                            pattern: /^[A-Za-z]+$/i
                            })}
                            onChange={handleInput}
                        />
                        {errors?.lastName?.type === "required" && <p>This field is required</p>}
                        {errors?.lastName?.type === "pattern" && (
                            <p>Alphabetical characters only</p>
                        )} 
                    </div>

                    <div className="group">
                        <label htmlFor="password" className="label">Password</label>
                        <input id="password" placeholder='password...' type="password" className="input" data-type="password" name="password"
                        {...register("password", {
                            required: true,
                            minLength: 8,
                            maxLength: 50,
                            pattern: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/i
                        })}
                        onChange={handleInput}
                        />
                        {errors?.password?.type === "required" && <p>This field is required</p>}
                        {(errors?.password?.type === "pattern" || 
                        errors?.password?.type === "minLength" || 
                        errors?.password?.type === "maxLength")  && (
                            
                            <p>Password should inlude at least:
                            8 characters, one number, one small letter, one capital letter, one symbol
                            </p>
                        )}
                    </div>

                    <div className="group">
                        <input type="submit" className="button sign-in-up" value="Sign Up"/>
                    </div>
                    <div className="hr"></div>
                    <div className="foot-lnk">
                    <LinkContainer to="/loginPage">
                        <Nav.Link id="link-already-member">Already a member? </Nav.Link>
                    </LinkContainer>
                    </div>
                </form>
                </div>    
            </div>
        </div>
        
    );
}

export default SignUpForm;