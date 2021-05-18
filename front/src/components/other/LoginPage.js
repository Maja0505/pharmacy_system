import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import axios from "axios";
import { Link } from "react-router-dom";

class Login extends Component {

  constructor(props){
    super(props);
    
    this.state = {
    
      "email": "",
      "password": "",
      warn:false,
      firstLogin:false
    
  };
}

  async loginClick(){
    await axios
    .post('http://localhost:8080/auth/login',
    {

      "email":this.state.email,
      "password":this.state.password
    }).then(res => {
      let tokenDTO = res.data;
      alert(tokenDTO.isFirstLogin)
      this.setState({
          token: tokenDTO.accessToken,
          userEmail: tokenDTO.email,
          roleUser: tokenDTO.role,
          firstLogin: tokenDTO.isFirstLogin
      })
      localStorage.setItem('token',this.state.token);
      localStorage.setItem('userEmail',this.state.userEmail);
      localStorage.setItem('roleUser', this.state.roleUser);
      localStorage.setItem('firstLogin', this.state.firstLogin);
      console.log("TOKEN " + this.state.token);
    
     
    }).catch(function(error){
      if(error.response){
        alert("Wrong email or password!");
      }
      

    })

    console.log("email: "+ this.state.email)
    console.log("pass: "+ this.state.password)

    this.redirect()
  }





/*
  login = (p) => {
    if (p.role === "") {
      alert("Unesite korisnicko ime i lozinku");
    } else if (p.role === "") {
      this.setState({
        redirectionUser: p.username,
      });
    } else if (p.username === "pharm" && p.password === "pharm") {
      this.setState({
        redirectionUser: p.username,
      });
    } else if (p.username === "trki" && p.password === "trki") {
      this.setState({
        redirectionUser: p.username,
      });
    } else if (p.username === "tasa" && p.password === "tasa") {
      this.setState({
        redirectionUser: p.username,
      });
    } else if (p.username === "derm" && p.password === "derm") {
      this.setState({
        redirectionUser: p.username,
      });
    } else {
      alert("Ne postoji");
    }
  };*/

  onTodoChangeUsername(value){
    this.setState({
      email: value
    });
   

}

onTodoChangePassword(value){
  this.setState({
    password: value
  });
 
}

  render() {
    const inputProps = {
      step: 300,
    };
    return (
      <div>
        <br />
        <TextField
          id="outlined-basic"
          label="Username"
          variant="outlined"
          value={this.state.email} 
          onChange={e=> this.onTodoChangeUsername(e.target.value)}
          size="small"
        />
        <br />
        <br />
        <TextField
          id="outlined-basic"
          label="Password"
          variant="outlined"
          value={this.state.password} 
          onChange={e=> this.onTodoChangePassword(e.target.value)}
          size="small"
        />
        {this.state.wrong ?
          <p>Wrong email or password</p> : null
        }
        <br />
        <br />
        <Link to="/registerPatient">You don't have account? Please, register with click on link!</Link>
        <br />
        <br />
        <div>
        <Button
          variant="contained"
          color="primary"
          onClick={this.loginClick.bind(this)}>
          Login
        </Button>
        </div>
      </div>
    );
  }

  redirect() {
    var redirection1 = "/system-admin";
    var redirection2 = "/supplier";
    var redirection3 = "/patient";
    var redirection4 = "/pharmacist";
    var redirection5 = "/dermatologist";
    alert(this.state.roleUser + " " + this.state.firstLogin);
    if(this.state.roleUser === "System_admin"){
        if(this.state.firstLogin === true){
            //stranica za promjenu lozinke
        } else {
          window.location.href = "http://localhost:3000/system_admin"
        }
    }
    if(this.state.roleUser === "Supplier"){
        if(this.state.firstLogin === true){
            //stranica za promjenu lozinke
        } else {
            window.location.href = "http://localhost:3000/supplier"
        } 
    }
    if(this.state.roleUser === "Patient"){
        if(this.state.firstLogin === true){
            //stranica za promjenu lozinke
        } else {
            alert("treba da redirektuje");
            window.location.href = "http://localhost:3000/patient"
        } 
    }
    if(this.state.roleUser === "Dermatologist"){
        if(this.state.firstLogin === true){
            //stranica za promjenu lozinke
        } else {
            window.location.href = "http://localhost:3000/dermatologist"
        } 
    }
    if(this.state.roleUser === "Pharmacist"){
        if(this.state.firstLogin === true){
            //stranica za promjenu lozinke
        } else {
            window.location.href = "http://localhost:3000/pharmacist"
        } 
    }
  }
}

export default Login;
