"use client";

import { resolve } from 'path';
import {useState} from 'react';
import { CustomPromisifyLegacy } from 'util';


export default function userRegistration(){
    const[userName,setUserName]=useState(null);
    const[email,setEmail]=useState(null);
    const[error,setError]=useState(null);
    const[status, setStatus] = useState('typing');


    async function handleSubmit(e) {
    e.preventDefault();
    setStatus('submitting');
    try {
        await submitForm(userName);
        setStatus('success');
    } catch (err) {
        setStatus('typing');
        setError(err);
    }
}

    if(status==='success'){
        return <h1>success!!</h1>
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
                />
                <h4 className="mb-1">Email</h4>
                <input
                    type="text"
                    placeholder="Password"
                    className="w-full placeholder-gray-400 border-2 rounded-md p-1"
                />
            </div>

                <button className="w-full bg-green-300 border-2 border-green-600 text-black rounded-xl py-1 mb-2">
                Login
                </button>
        </form>

            <div className="text-center">
            </div>
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
