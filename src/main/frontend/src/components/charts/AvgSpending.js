import React, { Component } from 'react';
import CanvasJSReact from './canvasjs.react';

var CanvasJSChart = CanvasJSReact.CanvasJSChart;
var CanvasJS = CanvasJSReact.CanvasJS;

function AvgSpending()
{
    const options = {
        animationEnabled: true,
        exportEnabled: true,
        theme: "light2", // "light1", "dark1", "dark2"
        title:{
            text: "Average Spending"
        },
        axisY: {
            title: "Amount",
            suffix: "$"
        },
        axisX: {
            title: "Week",
            interval: 1
        },
        data: [{
            type: "line",
            toolTipContent: "{x}: {y}$",
            xValueFormatString: "MMM YYYY",
            dataPoints: [
                { x: new Date("2021- 01- 01"), y: 61 },
                { x: new Date("2021- 01- 08"), y: 64 },
                { x: new Date("2021- 01- 15"), y: 62 },
                { x: new Date("2021- 01- 22"), y: 64 },
                { x: new Date("2021- 01- 29"), y: 60 }
            ]
        }]
    }
  
     return (
        <div>
          <CanvasJSChart className = "charts" options = {options}/>
        </div>
      );
    }

export default AvgSpending





