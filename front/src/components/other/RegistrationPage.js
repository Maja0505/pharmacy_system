import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import TextField from "@material-ui/core/TextField";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import {URL} from "./components"



import axios from "axios";

class RegistrationPage extends Component {

    constructor(props){
      super(props);
      this.state = {
        'firstName': props.firstName,
        'lastName':'',
        'email':'',
        'phoneNumber': '',
        'city': "",
        'country': '',
        'latitude': 0.0,
        'longitude': 0.0,
        'streetName': '',
        'streetNumber': '',
        'password': '',
        'confirmPassword': '',
        'id': 0,
        disable : true,
        showEditButton: true,
        showSaveButton: false,
        showCancelButton: false,
        userAddress:null,
        regexp: /^[0-9\b]+$/,
        goBack:false,
        goToLogin: false,


      }

      
    }

    RegisterUser()
    {
        const user = {
            email: this.state.email,
		    firstName: this.state.firstName,
		    lastName: this.state.lastName,
		    residentialAddress: {
                streetName: this.state.streetName,
                streetNumber: this.state.streetNumber,
                city: this.state.city,
                country: this.state.country,
                longitude: 0.0,
                latitude: 0.0,
              },
		    phoneNumber: this.state.phoneNumber,
		    password: this.state.password,
            confirmPassword: this.state.confirmPassword
        };
        alert("Check your email and confirm account " + user.email);
        if(this.state.password != this.state.confirmPassword){
            alert("Passwords don't match");
        }
        else{
        axios
        .post(URL + "/auth/signup",
        user, {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
        }
        ).catch(function(error){
            if(error.response){
              alert("Wrong input!")
            }
            
      
        })
        this.setState({
            goBack: true,
        });
    }
    }

    handleCancelClick()
    {
        this.setState({
            goToLogin: true,
            
        });
       
    }



    handleEditClick(){
        this.saveFirstInfo();
        this.setState({
            disable : false,
            showEditButton: false,
            showSaveButton: true,
            showCancelButton: true,
            
        })

        
        
    }
    
    
    onTodoChangeStreetName(value) {
        this.setState({
          streetName: value,
        });
      }
    
      onTodoChangeStreetNumber(value) {
        this.setState({
          streetNumber: value,
        });
      }
    
      onTodoChangeCity(value) {
        this.setState({
          city: value,
        });
      }
    
      onTodoChangeCountry(value) {
        this.setState({
          country: value,
        });
      }

    onTodoChangeFirstName(value){
        this.setState({
            firstName: value
        });
    }

    onTodoChangePassword(value){
        this.setState({
            password: value
        });
    }

    onTodoChangeConfirmPassword(value){
        this.setState({
            confirmPassword: value
        });
    }

    onTodoChangeLastName(value){
        this.setState({
            lastName: value
        });
    }

    onTodoChangeEmail(value){
        this.setState({
            email: value
        });
    }

    onTodoChangePhoneNumber(value){
        this.setState({
            phoneNumber: value
        });
    }
   

    render(){
       const address = this.state.country +' '+ this.state.city;
       var isDisabled = (this.state.disable === true)? true : false;
      

      return(
        <div>
        <h2 align="center">Registration</h2>
        <br/>
        <br/>
        <table align="center">
            <tbody>
                <tr></tr>
                <tr>
                    <td>Email:</td>
                    <td><TextField  value={this.state.email}  onChange={e=> this.onTodoChangeEmail(e.target.value)} id="outlined-basic" variant="outlined"  size="small"/></td>
                    
                </tr>
                <tr></tr>
                <tr></tr>
                <tr>
                    <td>First name:</td>
                    <td><TextField  value={this.state.firstName}  onChange={e=> this.onTodoChangeFirstName(e.target.value)} id="outlined-basic" variant="outlined"  size="small"/></td>
                    
                </tr>
                <tr></tr>
                <tr>
                    <td>Last name:</td>
                    <td><TextField  value={this.state.lastName}  onChange={e=> this.onTodoChangeLastName(e.target.value)} id="outlined-basic" variant="outlined"  size="small"/></td>
                </tr>
                <tr></tr>
                <tr>
                    <td>Street name:</td>
                    <td><TextField  value={this.state.streetName}  onChange={e=> this.onTodoChangeStreetName(e.target.value)} id="outlined-basic" variant="outlined"  size="small"/></td>
                </tr>
                <tr></tr>
                <tr>
                    <td>Street number:</td>
                    <td><TextField  value={this.state.streetNumber}  onChange={e=> this.onTodoChangeStreetNumber(e.target.value)} id="outlined-basic" variant="outlined"  size="small"/></td>
                </tr>
                <tr></tr>
                <tr>
                    <td>City:</td>
                    <td><TextField  value={this.state.city}  onChange={e=> this.onTodoChangeCity(e.target.value)} id="outlined-basic" variant="outlined"  size="small"/></td>
                </tr>
                <tr></tr>
                <tr>
                    <td>Country:</td>
                    <td><TextField  value={this.state.country}  onChange={e=> this.onTodoChangeCountry(e.target.value)} id="outlined-basic" variant="outlined"  size="small"/></td>
                </tr>
                <tr></tr>
                <tr>
                    <td>Phone number:</td>
                    <td><TextField  value={this.state.phoneNumber}  onChange={e=> this.onTodoChangePhoneNumber(e.target.value)} id="outlined-basic" variant="outlined"  size="small"/></td>
                </tr>
                <tr></tr>
                <tr>
                    <td>Password:</td>
                    <td><TextField type="password"  value={this.state.password}  onChange={e=> this.onTodoChangePassword(e.target.value)} id="outlined-basic" variant="outlined"  size="small"/></td>
                </tr>
                <tr></tr>
                <tr>
                    <td>Confirm Password:</td>
                    <td><TextField type="password" value={this.state.confirmPassword}  onChange={e=> this.onTodoChangeConfirmPassword(e.target.value)} id="outlined-basic" variant="outlined"  size="small"/></td>
                </tr>
                <tr></tr>
                <tr>
                <td><br></br> </td>
                <td> </td>
                </tr>
                {this.redirect()}
                <tr>
                    <td>
                        <Button variant="contained" color="primary" onClick={this.handleCancelClick.bind(this)}>CANCEL</Button>
                    </td>
                    <td>
                        <Button variant="contained" color="primary" onClick={this.RegisterUser.bind(this)}>REGISTER</Button>
                    </td>
                    
                </tr>
                


            </tbody>
        

        

        </table>

        </div>
      );
    }

    redirect() {
         if(this.state.goBack==true)
         {
             return  <Redirect to="/login" />;
         } else if (this.state.goToLogin==true){
             return  <Redirect to="/login" />
         }
    }
 

  
}

export default RegistrationPage;