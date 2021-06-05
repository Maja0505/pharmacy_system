import { FormLabel, Grid, TextField, Box } from "@material-ui/core";

import { makeStyles } from "@material-ui/core/styles";

import React, { useEffect, useState } from "react";

const useStyles = makeStyles({
  formLabel: {
    color: "black",
  },
});

const WriteReportSecondStep = ({ appointment, report, setReport }) => {
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");

  const classes = useStyles();

  const [nowTime, setDate] = useState(new Date());

  useEffect(() => {
    const interval = setInterval(() => {
      setDate(new Date());
    }, 1000);

    return () => clearInterval(interval);
  }, []);

  const writeReportInfo = (text) => {
    setReport({ ...report, reportInfo: text });
  };

  return (
    <div>
      {appointment.FirstName !== undefined && (
        <>
          <Grid container spacing={0} style={{ marginTop: "1%" }}>
            <Grid item xs={1} />
            <Grid item xs={6} style={{ margin: "auto" }}>
              <TextField
                id="outlined-multiline-static"
                label="Report info"
                variant="outlined"
                multiline
                rows={12}
                fullWidth
                value={report.reportInfo}
                onChange={(e) => writeReportInfo(e.target.value)}
              />
            </Grid>
            <Grid item xs={4}>
              <Box
                border={1}
                borderRadius={5}
                style={{
                  background: "#bed5e7",
                  borderColor: "#b8b8b8",
                  width: "65%",
                }}
              >
                <table>
                  <tbody>
                    <tr>
                      <td>
                        <FormLabel className={classes.formLabel}>
                          Patient :
                        </FormLabel>
                      </td>
                      <td style={{ padding: "5%" }}>
                        <FormLabel className={classes.formLabel}>
                          {appointment.FirstName} {appointment.LastName}
                        </FormLabel>
                      </td>
                    </tr>
                    <tr>
                      <td style={{ padding: "5%" }}>
                        <FormLabel className={classes.formLabel}>
                          Email :
                        </FormLabel>
                      </td>
                      <td>
                        <FormLabel className={classes.formLabel}>
                          {appointment.Email}
                        </FormLabel>
                      </td>
                    </tr>
                    <tr>
                      <td style={{ padding: "5%" }}>
                        <FormLabel className={classes.formLabel}>
                          Phone number :
                        </FormLabel>
                      </td>
                      <td>
                        <FormLabel className={classes.formLabel}>
                          {appointment.PhoneNumber}
                        </FormLabel>
                      </td>
                    </tr>
                    <tr>
                      <td style={{ padding: "5%" }}>
                        <FormLabel className={classes.formLabel}>
                          Now time :
                        </FormLabel>
                      </td>
                      <td>
                        <FormLabel className={classes.formLabel}>
                          {nowTime.toString().split(" ")[4]} h
                        </FormLabel>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </Box>
            </Grid>
          </Grid>
        </>
      )}
    </div>
  );
};

export default WriteReportSecondStep;
