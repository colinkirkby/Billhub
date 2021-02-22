import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { FaBars, FaTimes, FaHome, FaMapMarkerAlt } from 'react-icons/fa';
import { RiMoneyDollarCircleFill, RiAccountCircleFill, RiLogoutBoxFill, RiDashboardFill } from 'react-icons/ri';
import './Navbar.css';

function Navbar()
{
    const [navbar, setNavbar] = useState(false);
    const showNavbar = () => setNavbar(!navbar);

    return (
        <>
            <div className = "navbar">
                <div className = "navbar-container container">
                    <Link to = '/' className = "navbar-logo">
                        <RiMoneyDollarCircleFill className = "navbar-icon" />
                        BillHub
                    </Link>
                    
                    <div className = "menu-icon" onClick = {showNavbar}>
                        {navbar ? <FaTimes /> : <FaBars />}
                    </div>
                    
                    <nav className = {navbar ? 'nav-menu active' : 'nav-menu'}>
                        <ul className = 'nav-menu-items' onClick = {showNavbar}>
                            <li className = 'nav-item'>
                                <Link to = '/homepage' className = 'nav-links'>
                                    <FaHome className = 'nav-icon' />
                                    BillHub Home
                                </Link>
                            </li>

                            <li className = 'nav-item'>
                                <Link to = '/maps' className = 'nav-links'>
                                    <FaMapMarkerAlt className = 'nav-icon' />
                                    Maps
                                </Link>
                            </li>

                            <li className = "nav-item">
                                <Link to = '/dashboard' className = 'nav-links'>
                                    <RiDashboardFill className = 'nav-icon' />
                                    Dashboard
                                </Link>
                            </li>

                            <li className = 'nav-item'>
                                <Link to = '/account' className = 'nav-links'>
                                    <RiAccountCircleFill className = 'nav-icon' />
                                    Account
                                </Link>
                            </li>

                            <li className = 'nav-item'>
                                <Link to = '/' className = 'nav-links'>
                                    <RiLogoutBoxFill className = 'nav-icon' />
                                    Sign Out
                                </Link>
                            </li>

                        </ul>
                    </nav>
                </div>
            </div>
        </>
    )
}

export default Navbar