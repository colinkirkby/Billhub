import React, { useState } from 'react';
import { GoogleMap, useLoadScript, Marker, InfoWindow, InfoBox } from '@react-google-maps/api';
import mapStyles from './map_style.json'
import './Maps.css';

const libraries = ["places"];
const mapContainerStyle = {
  width: "100vw",
  height: "100vh",
};
const center = {
  lat: 49.20827,
  lng: -123.1207,
}
const options = {
  styles: mapStyles,
  disableDefaultUI: true, 
}

function App() {
    const {isLoaded, loadError} = useLoadScript({
      googleMapsApiKey: "AIzaSyDACAHnoiwNIsS9S06XcaEjFz-3GQBmo8U",
      libraries,
    });

const [marker, setMarker] = React.useState([]);
 
  if (loadError) return "error loading maps";
  if (!isLoaded) return "loading Maps";
  return (
    <div className="App-header">
      <header className= "App-header">
        
        <h1>BillHub</h1>
        <div className="map-info">

          <GoogleMap mapContainerStyle = {mapContainerStyle}
          zoom = {11}
          center = {center}
          options = {options}
          onClick = {(Event) =>{
            setMarker(current => [
              {
              lat: Event.latLng.lat(),
              lng: Event.latLng.lng(),
              
            }])
      
         console.log(marker.lat)
         console.log(marker.lng)
          }}> 
       
            {marker.map(marker => <Marker
            position = {{lat: marker.lat, lng: marker.lng}} />)
            }
            
            
            <h2>Cost map</h2>
           
          </GoogleMap>
          <div className = "info">
            <h3>info</h3>
            <div className = "data">
              <p1>cost of living = </p1>
              <p2>average income = </p2>
              <p3>average rent = </p3>
            </div>
          </div>
        </div>


      </header>
    </div>
  );
}

export default App;