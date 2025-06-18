"use client";


import {useEffect, useState} from 'react';

export default function userRegistration(){
    const[userName,setUserName]=useState('');
    const[email,setEmail]=useState('');
    const[password,setPassword]=useState('');
    const[userNameError,setUserNameError]=useState<string|null>(null);
    const[emailError,setEmailError]=useState<string|null>(null);
    const[passwordError,setPasswordError]=useState<string|null>(null);
    const[status, setStatus] = useState('typing');


    async function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    setStatus('submitting');
    setUserNameError(null);
    setEmailError(null);
    setPasswordError(null);
    let errorStatus=false;

    try {
        await validateUserName(userName);
    } catch (error:any) {
        errorStatus=true;
        setUserNameError(error.message);
    }

    try{
        await validateEmail(email);
    }catch(error:any){
        errorStatus=true;
        setEmailError(error.message);
    }

    try{
        await validatePassword(password);
    }catch(error:any){
        errorStatus=true;
        setPasswordError(error.message);
    }


    if(!errorStatus){
        const res = await fetch("http://localhost:8080/users",{
            method:"POST",
            body:JSON.stringify({name:userName,email:email,password:password}),
            headers:{
                "Content-Type":"application/json",
            },
        });
        if(res.ok){
            console.log("user registration");
            setStatus("success");
        }else{
            setStatus("error");
        }
    }else{
        setStatus('typing');
    }



    }


    return(

    <div className="h-screen w-screen flex justify-center items-center bg-green-50">
        <form onSubmit={handleSubmit}>
            <div className="bg-green-100 p-6 rounded-xl w-80">
                <div className="mb-4">
                <h4 className="mb-1">User Name</h4>
                <input
                    type="text"
                    placeholder="User Name"
                    className="w-full placeholder-gray-400 border-2 rounded-md p-1 mb-2"
                    name='userName'
                    value={userName}
                    onChange={e=>setUserName(e.target.value)}
                />
                <div id="userNameError" className="text-red-500 text-sm mt-1">{userNameError}</div>
                <h4 className="mb-1">Email</h4>
                <input
                    type="text"
                    placeholder="e-Mail"
                    className="w-full placeholder-gray-400 border-2 rounded-md p-1"
                    name='email'
                    value={email}
                    onChange={e=>setEmail(e.target.value)}
                />
                <div id="emailError" className="text-red-500 text-sm mt-1">{emailError}</div>
                <h4 className="mb-1">Password</h4>
                <input
                    type="text"
                    placeholder="Password"
                    className="w-full placeholder-gray-400 border-2 rounded-md p-1 mb-2"
                    name='Password'
                    value={password}
                    onChange={e=>setPassword(e.target.value)}
                />
                <div id="passwordError" className="text-red-500 text-sm mt-1">{passwordError}</div>
                </div>
            </div>

            <button className="w-full bg-green-300 border-2 border-green-600 text-black rounded-xl py-1 mb-2">
            Login
            </button>
        </form>

            <div className="text-center">
            </div>
        </div>
    );
}

function validateUserName(userName:string):Promise<boolean>{
    return new Promise((resolve,reject)=>{
        const regex = new RegExp("[!#\"'%&()=^|<>?]");
        if(!userName){return reject(new Error("user name is too short"));}
        if(userName.length > 20){return reject(new Error("user name is too long"))}
        if(regex.test(userName)){return reject(new Error("The symbols that cannot be used are included"));}
        return resolve(true);
        });
}

function validateEmail(email: string): Promise<boolean> {
    return new Promise((resolve, reject) => {
    const regex = /^[\w.-]+@[\w.-]+\.[a-zA-Z]{2,}$/;
    if (!regex.test(email)) {
        return reject(new Error("Please use a valid email format (example@gmail.com)"));
    }
    return resolve(true);
    });
}
function validatePassword(password: string): Promise<boolean> {
    return new Promise((resolve, reject) => {
    const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+[\]{};':"\\|,.<>/?]).{8,20}$/;
    if (!regex.test(password)) {
        return reject(
        new Error("Password must be 8-20 characters long and include at least one uppercase letter, one lowercase letter, one digit, and one special character."));
    }
    return resolve(true);
    });
}
