const API = "http://localhost:8080/ServletProjects";

$('#nameForm').submit(function(event) {
  let userName = document.querySelector(".userName").value;
  if (userName == "") {
    alert("places enter username")
    event.preventDefault();
  }
});

$(document).ready(function() {
  $.post(API + "/getTable", {
    name: "news"
  }, function(data) {
    console.log(data);
    $('#dataTables-example').DataTable({
      "responsive": true,
      data: data,
      "columns": [
        {
          "data": "name"
        }, {
          "data": "screen_name"
        }, {
          "data": "followers_count"
        }, {
          "data": "source"
        }, {
          "data": "Lable"
        }
      ],
      error: function(err) {
        alert(err)
      }
    });
  });
});

$(function() {
  $.get(API + "/getStatisticsCategories", function(data) {
    var ctx = document.getElementById("myChart").getContext('2d');
    var myChart = new Chart(ctx, {
      type: 'pie',
      data: {
        labels: [
          "Entertainment",
          "Advertisement",
          "Economy",
          "Health",
          "News",
          "Politics",
          "Religion",
          "Social",
          "Sport",
          "Technology"
        ],
        datasets: [
          {
            label: '# of Categories',
            data: [
              data["Entertainment"],
              data["advertisement"],
              data["economy"],
              data["health"],
              data["news"],
              data["politics"],
              data["religion"],
              data["social"],
              data["sport"],
              data["technology"]
            ],
            backgroundColor: [
              '#55E1CF',
              '#55AEE2',
              '#5567E2',
              '#8955E1',
              '#CF55E2',
              '#E156AE',
              '#E25668',
              '#E28956',
              '#E1CE56',
              '#AEE155'
            ],
            borderWidth: 1
          }
        ]
      },
      options: {
        scales: {
          yAxes: [
            {
              ticks: {
                beginAtZero: true
              }
            }
          ]
        }
      }
    });
  });
});
