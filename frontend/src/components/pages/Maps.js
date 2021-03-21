import React, { Component, useState } from 'react';
import { GoogleMap, useLoadScript, Marker, InfoWindow, OverlayView, } from '@react-google-maps/api';
import mapStyles from './map_style.json'
import Dialog from '../dialog/Dialog';
import usePlacesAutocomplete, {
  getGeocode,
  getLatLng,
} from "use-places-autocomplete";
import{
  Combobox,
  ComboboxInput,
  ComboboxPopover,
  ComboboxList,
  ComboboxOption,
} from "@reach/combobox";
import "@reach/combobox/styles.css";
import './Maps.css';
import axios from 'axios';

const MAP_GET_POINT = "http://localhost:8080/api/v1/getpoint";
const output = [];
output[0] = 0;
output[1] = 0;

// the array that will be used to show the locations values


const libraries = ["places"];
const mapContainerStyle = {
  width: "100vw",
  height: "100vh",
};
// the default that the map will center on when opened
const center = {
  lat: 49.20827,
  lng: -123.1207,
}
//settings for map
const options = {
  styles: mapStyles,
  disableDefaultUI: true, 
  clickableIcons: false,
}



function App() {


  //uses api key to access the google maps api
    const {isLoaded, loadError} = useLoadScript({
      googleMapsApiKey: "AIzaSyDACAHnoiwNIsS9S06XcaEjFz-3GQBmo8U",
      libraries,
    });

const [marker, setMarker] = React.useState([]);
const [selected, setSelected] = React.useState(null);
const [values,setValues] = React.useState([]);

const mapRef = React.useRef();
const onMapLoad = React.useCallback((map) => {
  mapRef.current = map;
}, );
// pans the mapt to the searched for location
const panTo = React.useCallback((target) => {
  
  mapRef.current.panTo(target);
  mapRef.current.setZoom(14);
  setMarker(current => [{
    lat: target.lat,
    lng: target.lng,
  }])
}, []);
 
 
  if (loadError) return "error loading maps";
  if (!isLoaded) return "loading Maps";
  return (
        <div>
          
          <Search panTo={panTo}/>



          <GoogleMap mapContainerStyle = {mapContainerStyle}
          zoom = {11}
          center = {center}
          options = {options}
          onLoad={onMapLoad}
          onClick = {(Event) =>{
            setMarker(current => [
              {
              lat: Event.latLng.lat(),
              lng: Event.latLng.lng(),
            }])
      
         

          }}> 
            {marker.map((marker) => (
          <Marker
            key={`${marker.lat}-${marker.lng}`}
            position={{ lat: marker.lat, lng: marker.lng }}
            onClick={() => {
              getinfo(marker.lat,marker.lng,setValues);
              setSelected(marker);
            }}
            />
            ))}
            {selected ? (
          <InfoWindow
            position={{ lat: selected.lat, lng: selected.lng }}
            onCloseClick={() => {
              setSelected(null);
            }}
          >
            
            <div className = "data">
              <p1>cost of living = {values.cost}</p1>
              <p2>average income = {values.income}</p2>
              <p3>average rent = {values.rent}</p3>
              <p4>data points = {values.datapoints}</p4>
              
            </div>
            
          </InfoWindow>
        ) : null}
          
          <h2>Cost Map</h2>
          </GoogleMap>
         
         
      
      </div>
  );
}


{
//search function that uses the google places api and the reach combo cox api 
}
function Search({ panTo }) {
  const {
    ready,
    value,
    suggestions: { status, data },
    setValue,
    clearSuggestions,
  } = usePlacesAutocomplete({
    requestOptions: {
      location: { lat: () =>  49.20827, lng: () => -123.1207 },
      radius: 100 * 1000,
    },
  });

  const handleInput = (e) => {
    setValue(e.target.value);
  };

  const handleSelect = async (address) => {
    setValue(address, false);
    clearSuggestions();

    try {
      const results = await getGeocode({ address });
      const target =  await getLatLng(results[0]);
      console.log(target)
      console.log(center)
      panTo(target)
    } catch (error) {
      console.log("Error: ", error);
    }
  };

  return (
    <div className="search">
      <Combobox onSelect={handleSelect}>
        <ComboboxInput
          value={value}
          onChange={handleInput}
          disabled={!ready}
          placeholder="Search your location"
        />
        <ComboboxPopover>
          <ComboboxList>
            {status === "OK" &&
              data.map(({ id, description }) => (
                <ComboboxOption key={id} value={description} />
              ))}
          </ComboboxList>
        </ComboboxPopover>
      </Combobox>
    </div>
  );
}

async function getinfo (lat,lng,setValues){
  
  
  output[0] = lat
  output[1] = lng
  console.log("coming from getinfo 1")
  console.log(lat)
  console.log(lng)
            
const res = await axios.get(MAP_GET_POINT,{
    params: {
      latitude: lat,
      longitude: lng 
    }})
    console.log("success") 
    console.log(res.data)
    console.log(res.data.income)
    
    setValues(res.data)
    

}



export default App;