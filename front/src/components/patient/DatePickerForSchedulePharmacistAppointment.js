import * as React from 'react';
import { TimePickerComponent, DatePickerComponent } from "@syncfusion/ej2-react-calendars";
import { ComboBoxComponent } from '@syncfusion/ej2-react-dropdowns';
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Grid,
  Button,
  Link,
  TextField
} from "@material-ui/core";
import  { useEffect, useState } from "react";
import { makeStyles } from "@material-ui/core/styles";

const SchedulePharmacistAppointment = ({date,time,duration,setDate,setTime,setDuration,setPharmacies}) => {

  const [durationList,setDurationList] = React.useState([
    5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90
  ])



  const [disableDates, setDisableDates] = useState([
    {
      before: new Date(),
    },
  ]);


  return (
        <div>
            Schedule pharmacist appointment
              <p>Select date and start time:</p>
              <DatePickerComponent 
                min={new Date()}
                id="datetimepicker"
                format="yyyy-MM-dd"
                placeholder='Select date'
                value={date}
                onChange={(e) => setDate(e.value)} />
              <TimePickerComponent
                id="timepicker"
                placeholder="Select start time"
                format="HH:mm"
                showClearButton={false}
                step={5}
                allowEdit={false}
                value={time}
                onChange={(e) => setTime(e.value)}
                
              />
               <ComboBoxComponent 
                id="diacritics"
                ignoreAccent={true}
                allowFiltering={true}
                dataSource={durationList}
                placeholder="duration(minutes)"
                value = {duration}
                change={(e) => setDuration(e.value)}  />
               
              
        </div>
    )
}

export default SchedulePharmacistAppointment
