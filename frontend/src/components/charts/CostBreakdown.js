import React, { Component,useState,useEffect } from 'react';
import CanvasJSReact from './canvasjs.react';
import UserService from '../../UserService';

var CanvasJSChart = CanvasJSReact.CanvasJSChart;
var CanvasJS = CanvasJSReact.CanvasJS;


function CostBreakdown()
{
    const [totals, setTotals] = useState([])
    let userEmail = sessionStorage.getItem("email");
    useEffect(() => {async function getTotals(){
        const res = await UserService.getTotals(userEmail)
        .then(res => {
            console.log(res.data);
            setTotals(res.data);
            console.log(totals);
           
        })
        
    }
    getTotals()}, [])
    let overalltotals = totals[0] + totals[1] + totals[2] + totals[3] + totals[4]
    
    const options = {
        exportEnabled: true,
        animationEnabled: true,
        title: {
            text: "Expenses Breakdown"
        },
        data: [{
            type: "pie",
            startAngle: 75,
            toolTipContent: "<b>{label}</b>: {y}%",
            showInLegend: "true",
            legendText: "{label}",
            indexLabelFontSize: 16,
            indexLabel: "{label} - {y}%",
            dataPoints: [
                { y: Math.round((totals[0]/overalltotals)*100), label: "Food" },
                { y: Math.round((totals[1]/overalltotals)*100), label: "Health" },
                { y: Math.round((totals[2]/overalltotals)*100), label: "entertaiment" },
                { y: Math.round((totals[3]/overalltotals)*100), label: "travel" },
                { y: Math.round((totals[4]/overalltotals)*100), label: "other" },
            ]
        }]
    }
    return (
    <div>
        <CanvasJSChart options = {options}
            /* onRef={ref => this.chart = ref} */
        />
        {/*You can get reference to the chart instance as shown above using onRef. This allows you to access all chart properties and methods*/}
    </div>
    );
}

export default CostBreakdown;