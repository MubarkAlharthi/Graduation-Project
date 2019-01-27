// const API = "http://localhost:8080/ServletProjects";

$('#nameForm').submit(function(event) {
  let userName = document.querySelector(".userName").value;
  if (userName == "") {
    alert("places enter username")
    event.preventDefault();
  }
});

function toFloatingPoint(str){
  if(str.length > 6){
  	return (str/1000000).toFixed(1) + "M"
  }else if(str.length > 3){
  	return (str/1000).toFixed(2) + "K"
  }else{
  	return str
  }
}
$(window).on("load", function() {
  $.get(API + "/getStatistics", function(responseJson) {
    console.log(responseJson);
    $(".users_count").text(toFloatingPoint(responseJson["users_count"]))
    $(".label_count").text(toFloatingPoint(responseJson["label_count"]))
    $(".tweets_count").text(toFloatingPoint(responseJson["tweets_count"]))
    $(".fromKSA_count").text(toFloatingPoint(responseJson["fromKSA_count"]))

  }).done(function(data) {
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
  })
});

$(window).ready(function() {
  $.get(API + "/getNumbers", function(data) {
    console.log(data);
    $(".Tweets_not_labeled").text(data["Tweets_not_labeled"])
    $(".Tweets_are_labeled").text(data["Tweets_are_labeled"])
    $(".number_of_real").text(data["number_of_real"])
    $(".number_of_bot").text(data["number_of_bot"])
  });
});
