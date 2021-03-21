import React, { Component, useState } from 'react';
import './EntryList.css';
import './General.css';
import UserService from '../../UserService';

// class EntryList extends Component
// {
//     constructor(props)
//     {
//         super(props);
//         this.state = 
//         {
//             date: '',
//             category: '',
//             amount: '',
//             description: '',
//         };
//     }

//     getEntryList() {
//         let userEmail = sessionStorage.getItem("email");
//         UserService.getEntries(userEmail)
//             .then(res => {
//                 console.log(res);
//             });
//     }

//     componentDidMount() {
//         this.getEntryList();
//     }
    
//     render() {
//         return (
//             <div className = 'page'>
//                 <div className = 'page-container container'>
//                     <div className = 'page-title'>
//                         <h1>Existing Entries:</h1>
//                     </div>
    
//                 </div>
//             </div>
//         )
//     }

// }


function EntryList() {
    const [entries, setEntries] = useState(null);

    const getEntryList = async () => {
        let userEmail = sessionStorage.getItem("email");
        UserService.getEntries(userEmail)
            .then(res => {
                console.log("success");
                setEntries(res.data);
            })
            .catch((error) => {
                window.alert("Sorry, something went wrong. Please check back later!");
                window.location.replace("/dashboard");
            });
    };


    return (
        <div className = 'page'>
            <div className = 'page-container container'>
                <div className = 'page-title'>
                    <h1>Existing Entries:</h1>
                </div>

                {/* Fetch data from API */}
                <div>
                    <button className="fetch-button" onClick={getEntryList}>
                        Fetch Data
                    </button>
                    <br />
                </div>

                <div className = 'entries'>
                    {entries &&
                        entries.map((entry) => {
                            return (
                                <div className = 'entry' key = {entry.id}>
                                    <div className = 'details'>
                                        <p>Date: {entry.date}</p>
                                        <p>Category: {entry.catagory}</p>
                                        <p>Amount: ${entry.amount}</p>
                                        <p>Description: {entry.name}</p>
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