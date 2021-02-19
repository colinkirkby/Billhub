import React, { Component } from 'react';
import './EntryList.css';
import './General.css';


class EntryList extends Component
{
    constructor(props)
    {
        super(props);
        this.state = 
        {
            isFetching: false,
            entries: []
        };
    }

    render() {
        return (
            <div className = 'page'>
                <div className = 'page-container container'>
                    <div className = 'page-title'>
                        <h2>Existing Entries:</h2>
                    </div>
    
                </div>
            </div>
        )
    }

}
export default EntryList