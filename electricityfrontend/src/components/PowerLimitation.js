import React, { useEffect, useState } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import InputAdornment from '@mui/material/InputAdornment';
import {Container, Paper} from '@material-ui/core';
import Button from '@mui/material/Button';
import Chip from '@mui/material/Chip';

export default function Customer() {

    const paperStyle={padding:"50px 20px", width:800,margin:"20px auto"}

    const [zipCode, setZipCode] = React.useState('');
    const [powerLimit, setPowerLimit] = React.useState('');
    const [powerLimitations, setPowerLimitations] = React.useState([]);


    const deleteCustomer=(id)=>{
    fetch("http://localhost:8080//deleteCustomer/"+id, {method:"delete"})
            .then(()=>{
            console.log("Customer deleted")
          });
    };

    const handleClick=(e)=>{
    e.preventDefault()
    const powerLimitation={powerLimit, zipCode}
    console.log(powerLimitation)

     fetch("http://localhost:8080/addPowerLimitation",{
          method:"POST",
          headers:{"Content-Type":"application/json"},
          body:JSON.stringify(powerLimitation)

      }).then(()=>{
        console.log("Limit dodany")
      })

    }
    useEffect(()=>{
       fetch("http://localhost:8080/powerlimitations")
                .then(res=>res.json())
                .then((result)=>{
                  setPowerLimitations(result);
                }
    )
    },[powerLimitations])

    const listPowerLimitations = powerLimitations.map(powerLimitation=>(
                    <ul>
                    <li key={powerLimitation.id}>Id: {powerLimitation.id} Limit: {powerLimitation.powerLimit} Używane: {powerLimitation.usedPower} Kod pocztowy: {powerLimitation.zipCode}</li>
                    </ul>
                  ));



  return (
    <Box
      component="form"
      sx={{
        '& .MuiTextField-root': { m: 1, width: '25ch' },
      }}
      noValidate
      autoComplete="off"
    >

    <Container>

    <h1>Dodaj limit</h1>
    <Paper elevation={3} style={paperStyle}>

      <div>
       <TextField
       required
       type="number"
       label="Świadczona usługa"
       id="outlined-start-adornment"
       sx={{ m: 1, width: '25ch' }}
       InputProps={{
       startAdornment: <InputAdornment position="start">kW</InputAdornment>,
       }}
       value={powerLimit}
       onChange={(e)=>setPowerLimit(e.target.value)}/>


        <TextField
            required
            id="outlined-required"
            label="Kod pocztowy"
            value={zipCode}
            onChange={(e)=>setZipCode(e.target.value)}
            />


      </div>

 <Button variant="outlined" onClick={handleClick} >Dodaj</Button>
      </Paper>
      </Container>

      <h1>Limity</h1>

        <Paper elevation={3} style={paperStyle}>
        <div>{listPowerLimitations}</div>
        </Paper>
    </Box>
  );
}


