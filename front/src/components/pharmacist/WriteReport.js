import { Link } from "react-router-dom";

const finishdWritingReport = () => {
  localStorage.setItem("PatientForPharmacistReport", JSON.stringify(null));
};

const getItem = () => {
  console.log(JSON.parse(localStorage.getItem("PatientForPharmacistReport")));
};

const WriteReport = () => {
  return (
    <div>
      WRITING REPORT FOR PATIENT
      <Link to="/pharmacist/scheduleAppointment">Schedule appointnet</Link>
      <button onClick={finishdWritingReport}>FINISH</button>
      <button onClick={getItem}>GET</button>
    </div>
  );
};

export default WriteReport;
