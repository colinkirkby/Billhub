import React, {useEffect, useState} from 'react';
import axios from 'axios';

import './Account.css';
import './General.css';

function Account()
{
    const [data, setData] = useState({ hits: [] });
    useEffect(() => {
        const fetchData = async () => {
            const result = await axios(
                'https://api/v1',
            );
            setData(result.data);
        };
        fetchData();
    }, []);

    return (
        <div className = 'page'>
            <div className = 'page-container container'>
                <div className = 'page-title'>
                    <h1>BillHub Account Details</h1>
                </div>

                <table className = 'table'>
                    <tr>
                        <th row className = 'table-header'>Name:</th>
                        <td className = 'table-data'></td>
                    </tr>

                    <tr className = 'separating-line'>
                        <th row className = 'table-header'>Phone Number:</th>
                        <td className = 'table-data'></td>
                    </tr>

                    <tr className = 'separating-line'>
                        <th row className = 'table-header'>Email Address:</th>
                        <td className = 'table-data'></td>
                    </tr>
                </table>
            </div>
        </div>
    )
}
export default Account