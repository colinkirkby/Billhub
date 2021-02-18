import React from 'react';
import { Link } from 'react-router-dom';
import './NewEntry.css';
import './General.css';
import { Button } from '../button/Button';
import '../button/Button.css';


function NewEntry()
{
    return (
        <div className = 'page'>
            <div className = 'page-container container'>
                <div className = 'page-title'>
                    <h2>Add a New Entry</h2>
                </div>

                <form className = 'entry-form-dropdown'>
                    <label for = "type-of-spending" className = 'entry-form-label'>Type of Spending:</label>
                    <select id = "type-of-spending" name = "type-of-spending" className = 'dropdown-box'>
                        <option hidden>Select an option...</option>
                        <option value = "food">Food</option>
                        <option value = "utilities">Utilities</option>
                        <option value = "clothing">Clothing</option>
                        <option value = "personal">Personal</option>
                    </select>
                </form>

                <form className = 'entry-form-input'>
                    <label for = "amount-spent" className = 'entry-form-label'>Amount Spent ($): </label>
                    <input type = "number" id = "amount-spent" name = "amount-spent" min = "0.00" ></input>
                </form>

                <Link to = '/dashboard' className = 'button-link'>
                    <Button buttonStyle = 'btn--primary'>Submit</Button>
                </Link>
            </div>
        </div>
    )

}
export default NewEntry