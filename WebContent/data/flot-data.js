
//Flot Pie Chart
$(function() {
    $.get(API + "/getStatisticsSources", function(responseJson) {
      console.log(responseJson);
      let iphone = parseInt(responseJson["iphone"]);
      let android = parseInt(responseJson["android"]);
      let webClient = parseInt(responseJson["webClient"]);
      let all = parseInt(responseJson["all"]);
      let others = all - (iphone + android + webClient);
    var data = [{
        label: "iPhone",
        data: iphone
    }, {
        label: "Android",
        data: android
    }, {
        label: "Web Client",
        data: webClient
    }, {
        label: "Others",
        data: others
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
            defaultTheme: true
        }
    });
  });
});

//Flot Bar Chart

$(function() {

    var barOptions = {
        series: {
            bars: {
                show: true,
                barWidth: 43200000
            }
        },
        xaxis: {
            mode: "time",
            timeformat: "%m/%d",
            minTickSize: [1, "day"]
        },
        grid: {
            hoverable: true
        },
        legend: {
            show: false
        },
        tooltip: true,
        tooltipOpts: {
            content: "x: %x, y: %y"
        }
    };
    var barData = {
        label: "bar",
        data: [
            [1354521600000, 1000],
            [1355040000000, 2000],
            [1355223600000, 3000],
            [1355306400000, 4000],
            [1355487300000, 5000],
            [1355571900000, 6000]
        ]
    };
    $.plot($("#flot-bar-chart"), [barData], barOptions);

});
