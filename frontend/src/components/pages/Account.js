import React, {Component} from 'react';

import './Account.css';
import './General.css';
import UserService from '../../UserService';

class Account extends Component
{
    constructor(props) {
        super(props);
        this.state = {
            firstName: '',
            lastName: '',
        };
    }

    getAccountInfo() {
        let userEmail = sessionStorage.getItem("email");
        UserService.getAccount(userEmail)
            .then(res => {
                console.log("Success");
                this.setState({firstName: res.data.firstName});
                this.setState({lastName: res.data.lastName});
            })
            .catch((error) => {
                window.alert("Sorry, something went wrong. Please check back later.");
                window.location.replace("/dashboard");
            });
    }

    componentDidMount() {
        this.getAccountInfo();
    }

    render() {
        return (
            <div className = 'page'>
                <div className = 'page-container container'>
                    <div className = 'page-title'>
                        <h1>BillHub Account Details</h1>
                    </div>

                    <table className = 'table'>
                        <tr>
                            <th row className = 'table-header'>First Name: </th>
                            <td className = 'table-data'>{this.state.firstName}</td>
                        </tr>

                        <tr className = 'separating-line'>
                            <th row className = 'table-header'>Last Name: </th>
                            <td className = 'table-data'>{this.state.lastName}</td>
                        </tr>

                        <tr className = 'separating-line'>
                            <th row className = 'table-header'>Email Address: </th>
                            <td className = 'table-data'>{sessionStorage.getItem("email")}</td>
                        </tr>
                    </table>
                </div>
            </div>
        )
    }
}
export default Account