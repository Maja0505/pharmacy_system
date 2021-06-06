import { useState } from "react";
import { Button } from "@material-ui/core";
import FeatureExaminations from "./FutureExaminations.js";
import PastExaminations from "./ExaminedPatients.js";
import {Redirect} from "react-router-dom"
const Examinations = () => {
  const [future, setFuture] = useState(true);
  const [redirection,setRedirection] = useState(false)
  return (
    <div style={{ marginTop: "3%" }}>
      <Button
        variant={future !== true ? "contained" : "outlined"}
        color="primary"
        onClick={() => setFuture(true)}
      >
        Feature examinations
      </Button>
      <Button
        variant={future === true ? "contained" : "outlined"}
        color="primary"
        onClick={() => setFuture(false)}
      >
        Past examinations
      </Button>
      <>{future === true && <FeatureExaminations />}</>
      <>{future !== true && <PastExaminations />}</>
    </div>
  );
};

export default Examinations;
