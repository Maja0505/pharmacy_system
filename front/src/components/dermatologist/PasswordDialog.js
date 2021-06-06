import { useState } from "react";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import TextField from "@material-ui/core/TextField";
import DialogActions from "@material-ui/core/DialogActions";
import axios from "axios";
import { URL } from "../other/components";
import { Redirect } from "react-router-dom";

const PasswordDialog = ({
  openDialog,
  id,
  setOpenAlert,
  setOpenDialog,
  setAlertText,
}) => {
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");
  const [redirection, setRedirection] = useState(false);
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmedNewPassword, setConfirmedNewPassword] = useState("");
  const [error, setError] = useState("");
  const closeDialog = () => {
    setError("");
    setOpenDialog(false);
  };

  const saveChanges = () => {
    var passwordDTO = {
      id: id,
      confirmedPassword: currentPassword,
      newPassword: newPassword,
      confirmedNewPassword: confirmedNewPassword,
    };
    changePassword(passwordDTO);
  };

  const changePassword = (passwordDTO) => {
    setError("");
    axios
      .put(URL + "/api/dermatologist/changePassword", passwordDTO, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        setAlertText("Success change passowrd!");
        setOpenAlert(true);
        setOpenDialog(false);
      })
      .catch((error) => {
        if (error.response.status === 401) {
          setRedirection(true);
        }

        console.log(error);
        setError("wrong current or confirmed password");
      });
  };

  return (
    <>
      {redirection === true && <Redirect to="/login"></Redirect>}
      <Dialog
        onClose={closeDialog}
        aria-labelledby="customized-dialog-title"
        open={openDialog}
      >
        <DialogTitle id="customized-dialog-title" onClose={closeDialog}>
          Change Password
        </DialogTitle>
        <DialogContent dividers>
          <table>
            <tbody>
              <tr>
                <td>Current Password:</td>
                <td>
                  <TextField
                    id="outlined-basic"
                    variant="outlined"
                    size="small"
                    onChange={(e) => setCurrentPassword(e.target.value)}
                    type="password"
                  />
                </td>
              </tr>
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
          <Button autoFocus color="primary" onClick={saveChanges}>
            Save changes
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
};

PasswordDialog.defaultProps = {
  openDialog: false,
};

export default PasswordDialog;
