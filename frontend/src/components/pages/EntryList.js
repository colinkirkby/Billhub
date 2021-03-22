import React, { Component, useEffect, useState } from 'react';
import moment from 'moment';
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
                if (res.data == 204)
                {
                    window.alert("You currently have no transaction entries to display.");
                    window.location.replace("/dashboard");
                }
                setEntries(res.data);
            })
            .catch((error) => {
                window.alert("Sorry, something went wrong. Please check back later!");
                window.location.replace("/dashboard");
            });
        }

    getEntryList()
    }, [])

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
                                            <p>{moment(entry.date, 'YYYY-DD-MMThh:mm:ss').format('MM/DD/YYYY')}</p>
                                        </h4>
                                        <h4>Category: 
                                            <p>{entry.type}</p>
                                        </h4>
                                        <h4>Amount: 
                                            <p>${entry.amount}</p>
                                        </h4>
                                        <h4>Description: 
                                            <p>{entry.name}</p>
                                        </h4>
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