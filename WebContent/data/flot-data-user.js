
//Flot Pie Chart
// $(function() {
//   const userName = window.location.search.slice(10);
//   $.get(API + "/getUserWithCategories", {name: userName}, function(data) {
//
//   }).done(function(data) {
//     data = data[0]
//     console.log(data);
//     var data = [{
//       label: data.Lable,
//       data: data.Label1Per
//     }, {
//       label: data.Lable2,
//       data: data.Label2Per
//     }, {
//       label: data.Lable3,
//       data: data.Label3Per
//     }, {
//       label: data.Lable4,
//       data: data.Label4Per
//     }];
//
//     var plotObj = $.plot($("#flot-pie-chart"), data, {
//       series: {
//         pie: {
//           show: true
//         }
//       },
//       grid: {
//         hoverable: true
//       },
//       tooltip: true,
//       tooltipOpts: {
//         content: "%p.0%, %s", // show percentages, rounding to 2 decimal places
//         shifts: {
//           x: 20,
//           y: 0
//         },
//         defaultTheme: false
//       }
//     });
//   }).fail(function(message) {
//     console.log(message);
//     alert(message);
//   });
// });

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
            [1538629387000, 2000],
            [1538629309000, 3000],
            [1538628434000, 4000],
            [1538604735000, 1000],
            [1538604656000, 4000],
            [1538596109000, 1000],
            [1538593669000, 2000],
            [1538591503000, 3000],
            [1538585780000, 4000],
            [1538580489000, 4000],
            [1538579632000, 4000],
            [1538578670000, 4000],
            [1538572581000, 4000],
            [1538572500000, 5000],
            [1538572470000, 1000],
            [1538572234000, 2000],
            [1538568293000, 2000],
            [1538568083000, 3000],
            [1538562017000, 4000],
            [1538553284000, 5000],
            [1538547090000, 6000]
        ]
    };
    $.plot($("#flot-bar-chart"), [barData], barOptions);

});
