import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './Dashboard.css';
import './General.css';
import AvgSpending from '../charts/AvgSpending';
import CostBreakdown from '../charts/CostBreakdown';
import { Button } from '../button/Button';
import Dialog from '../dialog/Dialog';

function Dashboard()
{
    const [button, setButton] = useState(true);
    console.log(sessionStorage.getItem('email')) 
    return (
        <div className = 'page'>
            <div className = 'page-container container'>
                <div className = 'page-title'>
                    <h1>Welcome to your BillHub Dashboard!</h1>
                </div>

                <div className = 'dashboard-cards'>
                    
                        
                            <Dialog/>
                        
                    

                    <div className = 'card'>
                        <li className = 'card-button'>
                            <Link to = '/entries' className = 'button-link'>
                                    <Button buttonStyle = 'btn--outline'>View Existing Entries</Button>
                            </Link>
                            
                        </li>

                    </div>
                    
                </div>

                <div className = 'charts'>
                    <div className = 'charts-left'>
                        <AvgSpending />
                    </div>

                    <div className = 'charts-right'>
                        <CostBreakdown />
                    </div>
                </div>
            </div>
        </div>

    )
}
export default Dashboard