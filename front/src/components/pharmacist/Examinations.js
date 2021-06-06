import { useState } from "react";
import { Button } from "@material-ui/core";
import FeatureExaminations from "./FutureExaminations.js";
import PastExaminations from "./ExaminedPatients.js";

const Examinations = () => {
  const [future, setFuture] = useState(true);

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
