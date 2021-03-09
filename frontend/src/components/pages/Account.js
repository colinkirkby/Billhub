import React, {useEffect, useState, Component} from 'react';
import axios from 'axios';

import './Account.css';
import './General.css';
import UserService from '../../UserService';

class Account extends Component
{
    constructor(props) {
        super(props);
        this.state = {
            name: '',
            email: '',
        }
    }


    // getAccountInfo = (e) => {
        // this.setState({name: UserService.getAccount(user).name});
        // this.setState({email: UserService.getAccount(user).email});
    // }

    render() {
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

                        // <tr className = 'separating-line'>
                        //     <th row className = 'table-header'>Phone Number:</th>
                        //     <td className = 'table-data'></td>
                        // </tr>

                        <tr className = 'separating-line'>
                            <th row className = 'table-header'>Email Address:</th>
                            <td className = 'table-data'></td>
                        </tr>
                    </table>
                </div>
            </div>
        )
    }
}
export default Account