import React, { useMemo} from 'react';
import GlossaryTable from './GlossaryTable';
import { Button } from '@material-ui/core'
import './General.css';
import './Glossary.css';

function Glossary() {
        const columns = useMemo(
            () => [
            {
                Header: 'TERM',
                accessor: 'term',
                width: 250,
            },

            {
                Header: 'DEFINITION',
                accessor: 'definition',
                width: 700,
            },

            {
                Header: 'RESOURCE',
                accessor: 'resource',
                Cell: ({ row }) => (
                    <div style = {{ textAlign: "center" }}>
                        <Button onClick = {() => window.open(row.original.resource)}> Further Reading </Button>
                    </div>),
                width: 300,                
            },
        ],
        []
        );

        const data = useMemo(
            () => [
            {
                term: 'Budget',
                definition: 'an estimate of expected spending and income over a set period of time',
                resource: 'https://www.investopedia.com/terms/b/budget.asp',
            },

            {
                term: 'Compound Interest',
                definition: 'additional interest on top of the original amount of money loaned or deposited; interest on interest',
                resource: 'https://www.investopedia.com/terms/c/compoundinterest.asp',
            },

            {
                term: 'Credit',
                definition: 'allows for the purchase of items without immediate reimbursement, on the condition that the purchaser will pay the full amount at a later date (usually with interest)',
                resource: 'https://www.investopedia.com/terms/c/credit.asp',
            },

            {
                term: 'Credit',
                definition: 'allows for the purchase of items without immediate reimbursement, on the condition that the purchaser will pay the full amount at a later date (usually with interest)',
                resource: 'https://www.investopedia.com/terms/c/credit.asp',
            },

            {
                term: 'Duty',
                definition: 'a tax on good entering or leaving a country',
                resource: 'https://economictimes.indiatimes.com/definition/customs-duty',
            },

            {
                term: 'Exchange Rate',
                definition: 'the value of one currency versus another, for example, Euros and Canadian Dollars',
                resource: 'https://www.investopedia.com/terms/e/exchangerate.asp',
            },

            {
                term: 'Goods and Services Tax (GST)',
                definition: 'a tax on goods and services sold domestically',
                resource: 'https://www.investopedia.com/terms/g/gst.asp',
            },

            {
                term: 'Gross Income',
                definition: "an individual's total income before taxes and other deductions",
                resource: 'https://www.investopedia.com/terms/g/grossincome.asp',
            },

            {
                term: 'Income',
                definition: 'money that an individual or business receives',
                resource: 'https://www.investopedia.com/terms/i/income.asp',
            },

            {
                term: 'Income Tax',
                definition: "a tax on an individual's or business's income",
                resource: 'https://www.investopedia.com/terms/i/incometax.asp',
            },

            {
                term: 'Insurance',
                definition: 'a contract in which an individual gains financial protection against losses (ex. car damage or health issues)',
                resource: 'https://www.investopedia.com/terms/i/insurance.asp',
            },

            {
                term: 'Interest',
                definition: 'the monetary charge for borrowing money (the amount a lender receives for lending out money)',
                resource: 'https://www.investopedia.com/terms/i/interest.asp',
            },

            {
                term: 'Net Income',
                definition: "an individual's total income after taxes and other deductions",
                resource: 'https://www.investopedia.com/terms/n/netincome.asp',
            },

            {
                term: 'Tariff',
                definition: 'a tax on a country for goods and services imported from another',
                resource: 'https://www.investopedia.com/terms/t/tariff.asp',
            },

            {
                term: 'Taxes',
                definition: 'mandatory fees on individuals and businesses, used to help fund government activities',
                resource: 'https://www.investopedia.com/terms/t/taxes.asp',
            }
        ],
        []
        );

        return(
            <div className = 'page'>
                <div className = 'page-container container'>
                    <div className = 'page-title'>
                        <h1>Financial Glossary</h1>
                    </div>

                    <GlossaryTable className = 'table'
                        columns = {columns}
                        data = {data}
                    />
                </div>
            </div>
        )

}


export default Glossary;