import React from 'react';
import { Button, Card, Typography, CardContent, CardActions, CardMedia } from '@material-ui/core'
import { makeStyles } from '@material-ui/core/styles';
import './General.css';
import './ResourcePage.css';

const useStyles = makeStyles({
    root: {
        width: '100%',
        height: 140,
        background: 'white',
        justifyContent: 'center',

    },

    title: {
        fontSize: 20,
        fontWeight: 'Bold',
        color: '#232a42',
    },

    button: {
        fontSize: 16,
        background: '#232a42',
        color: 'white',
        alignContent: 'center',
    },

    media: {
        height: 100,
        width: 1300,
        paddingTop: '10%',
        marginTop: 0,
        alignContent: 'center',
    },

});

function ResourcePage()
{
    const classes = useStyles();

    return (
        <div className = 'page'>
            <div className = 'page-container container'>
                <div className = 'page-title'>
                    <h1>Resources</h1>
                </div>

                <div className = 'page-body'>
                    <h2>Looking for more resources? Check out the links below.</h2>
                    <br/>
                    
                    <div className = 'resource-cards'>
                        <CardMedia 
                            className = {classes.media}
                            image = "./static/international.jpg"
                        />

                        <Card className = {classes.root}>
                            <CardContent>
                                <Typography className = {classes.title} variant = 'h5'>
                                    International Students:
                                </Typography>

                                <CardActions>
                                    <Button className = {classes.button} variant = "contained" color = "link" onClick = {() => window.open('https://moving2canada.com/help-for-international-students/')}>Moving to Canada Website</Button>
                                    <Button className = {classes.button} variant = "contained" color = "link" onClick = {() => window.open('https://www.cicic.ca/1347/get_information_on_financial_assistance_available_for_international_students.canada')}>Financial Assistance for International Students</Button>
                                    <Button className = {classes.button} variant = "contained" color = "link" onClick = {() => window.open('https://www.canada.ca/en/immigration-refugees-citizenship/services/study-canada/work.html')}>Government of Canada Website</Button>
                                </CardActions>
                            </CardContent>                            
                        </Card>

                        <Card className = {classes.root}>
                            <CardContent>
                                <Typography className = {classes.title} variant = 'h5'>
                                    Travellers:
                                </Typography>

                                <CardActions>
                                    <Button className = {classes.button} variant = "contained" color = "link" onClick = {() => window.open('https://www.forbes.com/sites/adriennejordan/2020/08/07/5-tips-to-manage-personal-finance-while-traveling/?sh=1fd518e5d832')}>Forbes - Tips for Managing Finances While Travelling</Button>
                                    <Button className = {classes.button} variant = "contained" color = "link" onClick = {() => window.open('https://www.cnbc.com/select/financial-tips-for-international-travel/')}>CNBC - Planning for International Travel</Button>
                                    <Button className = {classes.button} variant = "contained" color = "link" onClick = {() => window.open('https://www.huffpost.com/entry/15-ways-to-save-money-while-traveling_b_59661c1ce4b0deab7c646cf1')}>HuffPost - Saving Money While Travelling</Button>
                                </CardActions>
                            </CardContent>
                        </Card>

                        <Card className = {classes.root}>
                            <CardContent>
                                <Typography className = {classes.title} variant = 'h5'>
                                    General Financial Literacy:
                                </Typography>

                                <CardActions>
                                    <Button className = {classes.button} variant = "contained" color = "link" onClick = {() => window.open('https://www.investopedia.com/financial-term-dictionary-4769738')}>Investopedia Financial Dictionary</Button>
                                    <Button className = {classes.button} variant = "contained" color = "link" onClick = {() => window.open('https://www.canada.ca/en/services/finance/manage.html')}>Government of Canada Money Managing Tips</Button>
                                    <Button className = {classes.button} variant = "contained" color = "link" onClick = {() => window.open('https://www.nbc.ca/personal/advice/budget/financial-literacy.html')}>NBC - Financial Literacy</Button>
                                </CardActions>
                            </CardContent>
                        </Card>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default ResourcePage