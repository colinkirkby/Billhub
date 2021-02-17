import React, { Component } from 'react';
import CanvasJSReact from './canvasjs.react';

var CanvasJSChart = CanvasJSReact.CanvasJSChart;
var CanvasJS = CanvasJSReact.CanvasJS;

function CostBreakdown()
{
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
                { y: 49, label: "Food" },
                { y: 18, label: "Clothing" },
                { y: 9, label: "Utilities" },
                { y: 5, label: "Personal" },
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