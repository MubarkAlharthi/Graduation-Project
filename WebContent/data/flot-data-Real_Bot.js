//Flot Line Chart
// $(document).ready(function() {
//
//     var offset = 0;
//     plot();
//
//     function plot() {
//         var sin = [],
//             cos = [];
//         for (var i = 0; i < 12; i += 0.2) {
//             sin.push([i, Math.sin(i + offset)]);
//             cos.push([i, Math.cos(i + offset)]);
//         }
//
//         var options = {
//             series: {
//                 lines: {
//                     show: true
//                 },
//                 points: {
//                     show: true
//                 }
//             },
//             grid: {
//                 hoverable: true //IMPORTANT! this is needed for tooltip to work
//             },
//             yaxis: {
//                 min: -1.2,
//                 max: 1.2
//             },
//             tooltip: true,
//             tooltipOpts: {
//                 content: "'%s' of %x.1 is %y.4",
//                 shifts: {
//                     x: -60,
//                     y: 25
//                 }
//             }
//         };
//
//         var plotObj = $.plot($("#flot-line-chart"), [{
//                 data: sin,
//                 label: "sin(x)"
//             }, {
//                 data: cos,
//                 label: "cos(x)"
//             }],
//             options);
//     }
// });

//Flot Pie Chart
$(function() {

    var data = [{
        label: "Series 0",
        data: 1
    }, {
        label: "Series 1",
        data: 3
    }, {
        label: "Series 2",
        data: 9
    }, {
        label: "Series 3",
        data: 20
    }];

    var plotObj = $.plot($("#flot-pie-chart"), data, {
        series: {
            pie: {
                show: true
            }
        },
        grid: {
            hoverable: true
        },
        tooltip: true,
        tooltipOpts: {
            content: "%p.0%, %s", // show percentages, rounding to 2 decimal places
            shifts: {
                x: 20,
                y: 0
            },
            defaultTheme: false
        }
    });

});


//Flot Bar Chart

$(function() {

    var barOptions = {
        series: {
            bars: {
                show: true,
                barWidth: 4000000,
            }
        },
        xaxis: {
            // axisLabel: "Popular time",
            mode: "time",
            timezone: "browser",
            timeformat: "%H%p",
            tickSize: [1, "hour"]
        },
        yaxis: {
          // axisLabel: "number of tweets"
        },
        grid: {
            hoverable: true
        },
        legend: {
            show: true
        },
        tooltip: true,
        tooltipOpts: {
            content: "x: %x, y: %y"
        }
    };
    var barData = {
        label: "bar",
        data: [
            [1544403600000, 8273642],
            [1544407200000, 1092834],
            [1544410800000, 8726343],
            [1544414400000, 8723649],
            [1544418000000, 8238172],
            [1544421600000, 9746829],
            [1544425200000, 1082723],
            [1544428800000, 9287342],
            [1544432400000, 7928373],
            [1544436000000, 3628734],
            [1544439600000, 9823498],
            [1544443200000, 8172369],
            [1544446800000, 9827346],
            [1544450400000, 2837423],
            [1544454000000, 2342323],
            [1544457600000, 8787623],
            [1544461200000, 2347687],
            [1544464800000, 2376482],
            [1544468400000, 2836482],
            [1544472000000, 8276322],
            [1544475600000, 2873623],
            [1544479200000, 4444422],
            [1544482800000, 9287323]
        ]
    };
    $.plot($("#flot-bar-chart"), [barData], barOptions);

});
