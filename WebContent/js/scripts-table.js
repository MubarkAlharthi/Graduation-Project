
// $(window).on("load", function() {
//   $.get(API + "/getStatistics", function(responseJson) {
//     console.log(responseJson);
//     $(".users_count").text(responseJson["users_count"].slice(0,-3)+"K")
//     $(".label_count").text(responseJson["label_count"].slice(0,-3)+"K")
//     $(".tweets_count").text(responseJson["tweets_count"].slice(0,-3)+"K")
//     $(".fromKSA_count").text(responseJson["fromKSA_count"].slice(0,-3)+"K")
//     let iphone = parseInt(responseJson["iphone"]);
//     let android = parseInt(responseJson["android"]);
//     let webClient = parseInt(responseJson["webClient"]);
//     let all = parseInt(responseJson["all"]);
//     let others = all - (iphone + android + webClient);
//     $(".iphone").attr("data-percent",(iphone/all)*100);
//     $(".android").attr("data-percent",(android/all)*100);
//     $(".webClient").attr("data-percent",(webClient/all)*100);
//     $(".others").attr("data-percent",(others/all)*100);
//   }).done(function(){
//
//   })
// });

const API = "http://localhost:8080/ServletProjects";// link of server
$(document).ready(function() {//if document is laod and ready to manipulate on it
  $.post(API + "/getTable", function(data){//make request to server
    console.log(data);
    $('#dataTables-example').DataTable({
      "responsive": true,
      //render the data in the HTML elements
      data: data,
      "columns": [
        { "data": "name"},
        { "data": "screen_name"},
        { "data": "followers_count"},
        { "data": "source"},
        { "data": "Lable"}
      ],
      error: function(err){//handle Errors
        alert(err)
      }
    });
  });
});
