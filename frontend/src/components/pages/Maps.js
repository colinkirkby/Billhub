import React, { useState } from 'react';
import { GoogleMap, useLoadScript, Marker, InfoWindow, } from '@react-google-maps/api';
import mapStyles from './map_style.json'
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
var  values = [];
values[0] = 0;
values[1] = 0;
values[2] = 0;
values[3] = 0;

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
  clickableIcons: false,
}



function App() {
    const {isLoaded, loadError} = useLoadScript({
      googleMapsApiKey: "AIzaSyDACAHnoiwNIsS9S06XcaEjFz-3GQBmo8U",
      libraries,
    });

const [marker, setMarker] = React.useState([]);
const [selected, setSelected] = React.useState(null);

const mapRef = React.useRef();
const onMapLoad = React.useCallback((map) => {
  mapRef.current = map;
}, );

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
    <div className="App-header">
      <header className= "App-header">
        
        <div className="map-info">
          
          <div className = "map">
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
              <p1>cost of living = {values[0]}</p1>
              <p2>average income = {values[1]}</p2>
              <p3>average rent = {values[2]}</p3>
              <p4>data points = {values[3]}</p4>
              {console.log(marker[0].lat)}
              {console.log(marker[0].lng)}
            
            </div>
            
          </InfoWindow>
        ) : null}
          
          <h2>Cost Map</h2>
          </GoogleMap>
          </div>
          {values[0] = 1} 
          {/* <div className = "info">
            <h3>info</h3>
            <div className = "data">
              <p1>cost of living = {values[0]}</p1>
              <p2>average income = {values[1]}</p2>
              <p3>average rent = {values[2]}</p3>
              <p4>data points = {values[3]}</p4>
            </div>
          </div> */}
        </div>


      </header>
    </div>
  );
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

export default App;