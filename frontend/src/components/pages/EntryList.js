import React, { useEffect, useState } from 'react';
import moment from 'moment';
import { Button } from '@material-ui/core'; 
import DeleteIcon from '@material-ui/icons/Delete';
import './EntryList.css';
import './General.css';
import UserService from '../../UserService';

const EntryList = () => 
{
    const [entries, setEntries] = useState(null);
    let userEmail = sessionStorage.getItem("email");
    useEffect(() => {
        async function getEntryList() {
            UserService.getEntries(userEmail)
            .then(res => {
                console.log("success");
                setEntries(res.data);
            })
            .catch((error) => {
                window.alert("Sorry, something went wrong. Please check back later!");
                window.location.replace("/dashboard");
            });
        }

    getEntryList()
    }, [])

    function deleteEntry(id) {
        window.alert("Sorry, this featured hasn't been implemented yet.");
    }

    return (
        <div className = 'page'>
            <div className = 'page-container container'>
                <div className = 'page-title'>
                    <h1>Existing Entries:</h1>
                </div>

                <div className = 'entries'>
                    {entries &&
                        entries.map((entry) => {
                            return (
                                <div className = 'entry' key = {entry.id}>
                                    <div className = 'details'>
                                        <h4>Date: 
                                            <span>{moment(entry.date).format(moment.HTML5_FMT.DATE)}</span>
                                        </h4>
                                        <h4>Category: 
                                            <span>{entry.type}</span>
                                        </h4>
                                        <h4>Amount: 
                                            <span>${entry.amount}</span>
                                        </h4>
                                        <h4>Description: 
                                            <span>{entry.name}</span>
                                        </h4>
                                        <Button 
                                            startIcon={<DeleteIcon />}
                                            variant = "contained"
                                            size = "small"
                                            onClick={() => { deleteEntry(entry.id) }}
                                        >
                                            Delete Entry    
                                        </Button>
                                    </div> 
                                </div>
                            );
                        })}
                </div>

            </div>
        </div>
    );
}


export default EntryList