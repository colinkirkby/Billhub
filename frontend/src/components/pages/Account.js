import React, {useState} from 'react';

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

                <table className = 'table'>
                    <tr>
                        <th row className = 'table-header'>Name:</th>
                        <td className = 'table-data'>Tester Tester</td>
                    </tr>

                    <tr className = 'separating-line'>
                        <th row className = 'table-header'>Phone Number:</th>
                        <td className = 'table-data'>604.123.4567</td>
                    </tr>

                    <tr className = 'separating-line'>
                        <th row className = 'table-header'>Email Address:</th>
                        <td className = 'table-data'>123abc@gmail.com</td>
                    </tr>
                </table>
            </div>
        </div>
    )
}
export default Account