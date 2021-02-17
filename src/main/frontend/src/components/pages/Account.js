import React from 'react';

import './Account.css';
import './General.css';

function Account()
{
    return (
        <div className = 'page'>
            <div className = 'page-container container'>
                <div className = 'page-title'>
                    <h1>BillHub Account Details</h1>
                </div>

                <div className = 'info-table'>
                    <h2>Name:</h2>
                    <hr/>
                    <h2>Email Address:</h2>
                    <hr/>
                    <h2>Phone Number:</h2>
                </div>
            </div>
        </div>
    )
}
export default Account