import React, { useEffect, useState, seReducer } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import InputAdornment from '@mui/material/InputAdornment';
import {Container, Paper} from '@material-ui/core';
import Button from '@mui/material/Button';
import Chip from '@mui/material/Chip';

export default function Customer() {

    const paperStyle={padding:"50px 20px", width:800,margin:"20px auto"}
    const [firstName, setFirstName] = React.useState('');
    const [lastName, setLastName] = React.useState('');
    const [country, setCountry] = React.useState('');
    const [voivodeship, setVoivodeship] = React.useState('');
    const [city, setCity] = React.useState('');
    const [zipCode, setZipCode] = React.useState('');
    const [streetName, setStreetName] = React.useState('');
    const [houseNo, setHouseNo] = React.useState('');
    const [apartmentNo, setApartmentNo] = React.useState('');
    const [kwValue, setKwValue] = React.useState('');
    const [note, setNote] = React.useState('');
    const [idToDelete, setIdToDelete] = React.useState('');
    const [customers, setCustomers] = React.useState([]);
    const [valueUp, forceUpdate] = React.useState(x=>x+1, 0);

    function deleteCustomer(id){
    fetch("http://localhost:8080//deleteCustomer/"+id, {method:"delete"})
            .then(()=>{
            console.log("Customer deleted")
          });
    };

    const handleClick=(e)=>{
    e.preventDefault()
    const address={country, voivodeship, city, zipCode, streetName, houseNo, apartmentNo}
    const customer={firstName, lastName,  kwValue, address, note}
    console.log(customer)


     fetch("http://localhost:8080/addCustomer",{
          method:"POST",
          headers:{"Content-Type":"application/json"},
          body:JSON.stringify(customer)

      }).then(()=>{
        console.log("Użytkownik dodany")
      })

      fetch("http://localhost:8080/customers")
                          .then(res=>res.json())
                          .then((result)=>{
                            setCustomers(result);
                          }
                          )

    forceUpdate();
    }

    useEffect(()=>{
       fetch("http://localhost:8080/customers")
                .then(res=>res.json())
                .then((result)=>{
                  setCustomers(result);
                }
    )
    },[valueUp])

    const listCustomers = customers.map(customer=>(
                    <ul>
                    <li key={customer.id}>Id: {customer.id} Imię: {customer.firstName} Nazwisko: {customer.lastName} Użycie: {customer.kwValue}</li>
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


    <h1>Dodaj użytkownika</h1>
    <Paper elevation={3} style={paperStyle}>
      <div>
        <TextField
          required
          id="outlined-required"
          label="Imię"
          value={firstName}
          onChange={(e)=>setFirstName(e.target.value)}
          />

        <TextField
            required
            id="outlined-required"
            label="Nazwisko"
            value={lastName}
            onChange={(e)=>setLastName(e.target.value)}
            />
      </div>
      <div>
              <TextField
                  required
                  id="outlined-required"
                  label="Kraj"
                  value={country}
                  onChange={(e)=>setCountry(e.target.value)}/>


              <TextField
                  required
                  id="outlined-required"
                  label="Województwo"
                  value={voivodeship}
                  onChange={(e)=>setVoivodeship(e.target.value)}/>


              <TextField
                  required
                  id="outlined-required"
                  label="Miasto"
                  value={city}
                  onChange={(e)=>setCity(e.target.value)}/>
               </div>

      <div>
              <TextField
                  required
                  id="outlined-required"
                  label="Kod pocztowy"
                  value={zipCode}
                  onChange={(e)=>setZipCode(e.target.value)}/>

              <TextField
                  required
                  id="outlined-required"
                  label="Ulica"
                  value={streetName}
                  onChange={(e)=>setStreetName(e.target.value)}/>

              <TextField
                  required
                  id="outlined-required"
                  label="Numer domu"
                  type="number"
                  value={houseNo}
                  onChange={(e)=>setHouseNo(e.target.value)}/>
            </div>
      <div>
              <TextField
                  id="outlined"
                  label="Numer mieszkania"
                  type="number"
                  value={apartmentNo}
                  onChange={(e)=>setApartmentNo(e.target.value)}/>

      <TextField
                required
                type="number"
                label="Świadczona usługa"
                id="outlined-start-adornment"
                sx={{ m: 1, width: '25ch' }}
                InputProps={{
                  startAdornment: <InputAdornment position="start">kW</InputAdornment>,
                }}
                value={kwValue}
                onChange={(e)=>setKwValue(e.target.value)}/>


      <TextField
                id="note"
                label="Notatka"
                multiline
                maxRows={4}
                value={note}
                onChange={(e)=>setNote(e.target.value)}/>

 </div>

 <Button variant="outlined" onClick={handleClick} >Dodaj</Button>
      </Paper>
      </Container>

      <h1>Uzytkownicy</h1>

        <Paper elevation={3} style={paperStyle}>
        <div>{listCustomers}</div>
        </Paper>

    </Box>
  );
}


