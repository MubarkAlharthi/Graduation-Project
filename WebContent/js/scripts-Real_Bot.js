const API = "http://localhost:8080/ServletProjects";

$('#nameForm').submit(function(event) {
  let userName = document.querySelector(".userName").value;
  if (userName == "") {
    alert("places enter username")
    event.preventDefault();
  }
});

$(document).ready(function() {
  $.post(API + "/getTable_Real_Bot", {name: "news"}, function(data){
    console.log(data);
    $('#dataTables-example').DataTable({
      "responsive": true,
      // "processing": true,
      // "serverSide": true,
      // "deferRender": true,
      // "ajax": {
      //   "url": API + "/getTable",
      //   "deferLoading": 57 //important!
      // },
      data: data,
      "columns": [
        { "data": "name"},
        { "data": "screen_name"},
        { "data": "followers_count"},
        { "data": "source"},
        { "data": "Label"}
      ],
      error: function(err){
        alert(err)
      }
    });
  });
});
