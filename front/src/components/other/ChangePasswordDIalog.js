import { useState } from "react";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import TextField from "@material-ui/core/TextField";
import DialogActions from "@material-ui/core/DialogActions";
import axios from "axios";
import { URL } from "./components";
import { REACT_URL } from "./components";
import {Redirect} from "react-router-dom"


const ChangePasswordDIalog = ({ openDialog, setOpenDialog, state, role }) => {
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");
  const [redirection,setRedirection] = useState(false)
  const [newPassword, setNewPassword] = useState("");
  const [confirmedNewPassword, setConfirmedNewPassword] = useState("");
  const [error, setError] = useState("");

  const closeDialog = () => {
    setError("");
    setOpenDialog(state);
  };

  const logIn = () => {
    setError("");
    axios
      .put(
        URL + "/api/user/changePassword/firstSingUp",
        {
          id: userId,
          newPassword: newPassword,
          confirmationOfNewPassword: confirmedNewPassword,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then((res) => {
        if (role === "Pharmacist") {
          window.location.href = REACT_URL + "/pharmacist/homePage";
        } else if (role === "Dermatologist") {
          window.location.href = REACT_URL + "/dermatologist/homePage";
        } else {
          window.location.href = REACT_URL + "/login";
        }
      }).catch((error) => {
          if(error.response.status === 401){
            setRedirection(true)
          }
        setError("New pasword is empty or confirmed password is wrong");
      });
  };

  return (
    <>
      {redirection === true && <Redirect to="/login"></Redirect>}

      {openDialog !== undefined && (
        <Dialog
          onClose={closeDialog}
          aria-labelledby="customized-dialog-title"
          open={openDialog}
        >
          <DialogTitle id="customized-dialog-title" onClose={closeDialog}>
            Update your password
          </DialogTitle>
          <DialogContent dividers>
            <table>
              <tbody>
                <tr>
                  <td colSpan={2}>
                    <b>NEED TO CHANGE PASSWORD WHEN FIRST TIME LOG IN</b>
                  </td>
                </tr>
                <br />
                <br />
                <tr>
                  <td>New Password:</td>
                  <td>
                    <TextField
                      id="outlined-basic"
                      variant="outlined"
                      size="small"
                      onChange={(e) => setNewPassword(e.target.value)}
                      type="password"
                    />
                  </td>
                </tr>
                <tr>
                  <td>Confirm Password:</td>
                  <td>
                    <TextField
                      id="outlined-basic"
                      variant="outlined"
                      size="small"
                      onChange={(e) => setConfirmedNewPassword(e.target.value)}
                      type="password"
                    />
                  </td>
                </tr>
              </tbody>
            </table>
            <p style={{ color: "red" }}>{error}</p>
          </DialogContent>
          <DialogActions>
            <Button autoFocus color="secondary" onClick={closeDialog}>
              Close
            </Button>
            <Button autoFocus color="primary" onClick={logIn}>
              Log in
            </Button>
          </DialogActions>
        </Dialog>
      )}
    </>
  );
};

export default ChangePasswordDIalog;
