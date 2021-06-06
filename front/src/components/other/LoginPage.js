import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import axios from "axios";
import { Link } from "react-router-dom";
import { URL } from "./components";
import { REACT_URL } from "./components";
import background from "../../images/doctor.jpg";
import ChangePasswordDIalog from "./ChangePasswordDIalog.js";

class Login extends Component {
  constructor(props) {
    super(props);

    this.state = {
      email: "",
      password: "",
      warn: false,
      firstLogin: false,
      openDialog: true,
    };
  }

  async loginClick() {
    await axios
      .post(URL + "/auth/login", {
        email: this.state.email,
        password: this.state.password,
      })
      .then((res) => {
        let tokenDTO = res.data;
        this.setState({
          userId: tokenDTO.userId,
          token: tokenDTO.accessToken,
          userEmail: tokenDTO.email,
          roleUser: tokenDTO.role,
          firstLogin: tokenDTO.isFirstLogin,
          openDialog: true,
        });

        localStorage.setItem("userId", this.state.userId);
        localStorage.setItem("token", this.state.token);
        localStorage.setItem("userEmail", this.state.userEmail);
        localStorage.setItem("roleUser", this.state.roleUser);
        localStorage.setItem("firstLogin", this.state.firstLogin);
      })
      .catch(function (error) {
        if (error.response) {
          alert("Wrong email or password!");
        }
      });


    this.redirect();
  }

  setOpenDialog(state) {
    state.setState({
      openDialog: false,
    });
  }

  onTodoChangeUsername(value) {
    this.setState({
      email: value,
    });
  }

  onTodoChangePassword(value) {
    this.setState({
      password: value,
    });
  }

  render() {
    const inputProps = {
      step: 300,
    };
    return (
      <div
        style={{
          backgroundImage: `url(${background})`,
          height: "753px",
          backgroundSize: "cover",
          backgroundRepeat: "no-repeat",
          backgroundPosition: "center",
        }}
      >
        <br />
        <TextField
          id="outlined-basic"
          label="Username"
          variant="outlined"
          value={this.state.email}
          onChange={(e) => this.onTodoChangeUsername(e.target.value)}
          size="small"
        />
        <br />
        <br />
        <TextField
          id="outlined-basic"
          label="Password"
          variant="outlined"
          type="password"
          value={this.state.password}
          onChange={(e) => this.onTodoChangePassword(e.target.value)}
          size="small"
        />
        {this.state.wrong ? <p>Wrong email or password</p> : null}
        <br />
        <br />
        <Link to="/registerPatient">
          You don't have account? Please, register with click on link!
        </Link>
        <br />
        <br />
        <div>
          <Button
            variant="contained"
            color="primary"
            onClick={this.loginClick.bind(this)}
          >
            Login
          </Button>
        </div>
        {this.state.firstLogin === true &&
          this.state.roleUser !== "Patient" && (
            <ChangePasswordDIalog
              openDialog={this.state.openDialog}
              setOpenDialog={this.setOpenDialog}
              state={this}
              role={this.state.roleUser}
            />
          )}
      </div>
    );
  }

  redirect() {
    var redirection1 = "/system-admin";
    var redirection2 = "/supplier";
    var redirection3 = "/patient";
    var redirection4 = "/pharmacist";
    var redirection5 = "/dermatologist";
    if (this.state.roleUser === "System_admin") {
      if (this.state.firstLogin === true) {
        //stranica za promjenu lozinke
      } else {
        window.location.href = REACT_URL + "/system_admin";
      }
    }
    if (this.state.roleUser === "Supplier") {
      if (this.state.firstLogin === true) {
        //stranica za promjenu lozinke
      } else {
        window.location.href = REACT_URL + "/supplier";
      }
    }
    if (this.state.roleUser === "Patient") {
      window.location.href = REACT_URL + "/patient/home2";
    }
    if (this.state.roleUser === "Dermatologist") {
      if (this.state.firstLogin === true) {
        //stranica za promjenu lozinke
      } else {
        window.location.href = REACT_URL + "/dermatologist/homePage";
      }
    }
    if (this.state.roleUser === "Pharmacist") {
      if (this.state.firstLogin === true) {
        //stranica za promjenu lozinke
      } else {
        window.location.href = REACT_URL + "/pharmacist/homePage";
      }
    }
  }
}

export default Login;
