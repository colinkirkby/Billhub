import { Component, useState } from "react"
import styled from'styled-components'
import Modal from 'react-modal';
import axios from 'axios';
import CurrencyInput from "react-currency-input-field";
import Select from 'react-select';
import {useSpring, animated} from 'react-spring'
import "./Dialogstyle.css"

const TRANSACTION_API_BASE_URL = "http://localhost:8080/api/v1/newtrans";

const customStyles2 = {
  option: (provided, state) => ({
    ...provided,
    
    
  }),
  control: (provided) => ({
    ...provided,
    marginTop: "5%",
  })
}

const Container = styled.div`
display: flex;
flex-direction: column;
justify-content: center;
align-items: center;
line-height: 1.8;
color: #141414;

`;

const Button1 = styled.button`
  min-width: 100px;
  padding: 16px 39px;
  border-radius: 4px;
  border: none;
  background: #1c2237;
  color: #fff;
  font-size: 24px;
  cursor: pointer;
`;

const Button2 = styled.button`
  min-width: 20px;
  padding: 5px 5px;
  border-radius: 4px;
  border: none;
  background: #1c2237;
  color: #fff;
  font-size: 11px;
  cursor: pointer;
  transform: translate(840%, -1320%);
`;

const Header = styled.h1`
transform: translate(0%,-5%);
font-size: 45px;

`
const Subhead = styled.h1`
font-size: 24px;
transform: translate(0px,0px);
postion: relative;
text-align: left;
`
const customStyles = {
    content : {
      top: '50%',
      left: '50%',
      right: 'auto',
      bottom: 'auto',
      width: '370px',
      height: '450px',
      boxshadow: '0 5px 16px rgba(0, 0, 0, 0.2)',
      background: '#eee',
      color: '#eee',
      display: 'grid',
      gridtemplatecolumns : '1fr 1fr',
      zindex : '99',
      borderradius: '10px',
      marginRight           : '-50%',
      transform             : 'translate(-50%, -50%)'
    }
  };
const options = [
{value: 'food', label: 'Food'},
{value: 'entertainment', label: 'Entertainment'},
{value: 'travel', label: 'Travel'},
{value: 'other', label: 'Other'},
]

function Dialog (props) {

const handleSubmit = (e) => {
    e.preventDefault();
    
} 

var inputs = [] 
const [amount,setAmount] = useState();
const [name,setName] = useState();
const [catagory,setCatagory]= useState();
const [modalIsOpen , setModalIsOpen] = useState(false);  
const animation = useSpring({
  config: {
    duration: 250
  },
  opacity: modalIsOpen ? 1 : 0,
});



return( 
    <div>
      
        <Button1 onClick= {() => setModalIsOpen( true)}>Add Transaction</Button1>
       
          <Modal 
          isOpen= {modalIsOpen} 
          onRequestClose = {() => setModalIsOpen(false)}
          style = {customStyles}
          ariaHideApp={false}
          >
               <animated.div style = {animation}>
            <Container>
                <Header></Header>
                <div >
                <Subhead>Catagory</Subhead>
                <Select options = {options}
                  onChange={(value) => setCatagory(value.label)}
                  autoFocus = {true}
                  styles = {customStyles2}
                >
                </Select>
                <Subhead>Amount</Subhead>
                <div >
                <CurrencyInput className = "currency"
                id = "amount"
                name = "input-amount"
                placeholder = "enter amount"
                decimalsLimit = {2}
                onValueChange = {(value, name) => setAmount(value)}
                />
                </div>
                <Subhead>Name</Subhead>
                <div >
                  <form >                
                      
                      <input type='text'
                      onChange={e => setName(e.target.value)}
                      placeholder = "enter name"
                      className = "nameform" />
                  </form>
                </div>
                </div>
                <Button2 onClick= {() => setModalIsOpen( false)}>x</Button2>
                <Button1 onClick= {() => closeAndSubmit()}>Add Transaction</Button1>
            </Container>
            </animated.div>
          </Modal>
          
      </div>

);
function closeAndSubmit(){
  inputs[0] = amount;
  inputs[1] = name;
  inputs[2] = catagory;
    setModalIsOpen(false);
    console.log(inputs);
    axios.post(TRANSACTION_API_BASE_URL,inputs);
}
}
function handleChange(selected){
  console.log(selected);

}

    
export default Dialog



