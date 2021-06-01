import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import axios from "axios";
import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import {URL} from "./components"


const ConfirmationPage = () => {

  const [textHeader, setTextHeader] = useState("")


  useEffect(() => {
    var splitID = []
    splitID = window.location.href.split("/");
    const token = splitID[4];
    alert(token)
    axios
        .put(URL + '/auth/confirm_account/'+token, {}
        )
      .then((res) => {
        alert("Token founded!")
        setTextHeader("Successfuly confirmed account! You can now login on our system!")
      }).catch(function(error){
        alert("Token not founded!")
        setTextHeader("Your token is invalid or token not founded! Please, contact our system admins!")
        
        
  
      });
  }, []);

    return (
      <div>
        <br />
        <h1>{textHeader}</h1>
      </div>
    );
  
      /*
  const redirect = ();
    */  
};

export default ConfirmationPage;
