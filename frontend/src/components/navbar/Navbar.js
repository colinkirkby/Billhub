import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { AiFillPlusCircle } from 'react-icons/ai';
import { FaBars, FaTimes, FaHome, FaMapMarkerAlt, FaBook, FaSearch } from 'react-icons/fa';
import { RiMoneyDollarCircleFill, RiAccountCircleFill, RiLoginBoxFill, RiLogoutBoxFill, RiDashboardFill } from 'react-icons/ri';
import './Navbar.css';

function Navbar(props)
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

                            {props.isLoggedIn ?
                            null :
                            <li className = 'nav-item'>
                                <Link to = '/login' className = 'nav-links'>
                                    <RiLoginBoxFill className = 'nav-icon' />
                                    Sign In
                                </Link>
                            </li>}

                            {props.isLoggedIn ?
                            <li className = "nav-item">
                                <Link to = '/dashboard' className = 'nav-links'>
                                    <RiDashboardFill className = 'nav-icon' />
                                    Dashboard
                                </Link>
                            </li>
                            :
                            null}

                            {props.isLoggedIn ?
                            <li className = 'nav-item'>
                                <Link to = '/account' className = 'nav-links'>
                                    <RiAccountCircleFill className = 'nav-icon' />
                                    Account
                                </Link>
                            </li>
                            :
                            null}

                            {props.isLoggedIn ?
                            null :
                            <li className = 'nav-item'>
                                <Link to = '/register' className = 'nav-links'>
                                    <AiFillPlusCircle className = 'nav-icon' />
                                    Register
                                </Link>
                            </li>}

                            <li className = 'nav-item'>
                                <Link to = '/maps' className = 'nav-links'>
                                    <FaMapMarkerAlt className = 'nav-icon' />
                                    Maps
                                </Link>
                            </li>

                            <li className = 'nav-item'>
                                <Link to = '/financial-glossary' className = 'nav-links'>
                                    <FaBook className = 'nav-icon' />
                                    Glossary
                                </Link>
                            </li>

                            <li className = 'nav-item'>
                                <Link to = '/resources' className = 'nav-links'>
                                    <FaSearch className = 'nav-icon' />
                                    Resources
                                </Link>
                            </li>

                            {props.isLoggedIn ?
                            <li className = 'nav-item'>
                                <Link to = '/logout' className = 'nav-links'>
                                    <RiLogoutBoxFill className = 'nav-icon' />
                                    Sign Out
                                </Link>
                            </li>
                            :
                            null}
                            
                        </ul>
                    </nav>
                </div>
            </div>
        </>
    )
}

export default Navbar