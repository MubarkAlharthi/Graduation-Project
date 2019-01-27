const API = "http://localhost:8080/ServletProjects";
const twitterPage = "https://twitter.com/";
const userName = window.location.search.slice(10);

$('#nameForm').submit(function(event) {
  let userName = document.querySelector(".userName").value;
  if (userName == "") {
    alert("places enter username")
    event.preventDefault();
  }
});

(function(){
  $(".twitter-follow-button").attr("href",twitterPage+userName+"?ref_src=twsrc%5Etfw")
  $(".twitter-follow-button").text("Follow @"+userName)
})();

$(window).on("load",function() {
  $.get(API + "/getUserProfile", {name: userName}, function(data) {
      console.log(data);
      // alert( "success" );
      let twitterFollowButton = document.querySelector(".twitter-follow-button");
      twitterFollowButton.href = twitterPage+userName

      let twPc_avatarLink = document.querySelector(".twPc-avatarLink")
      twPc_avatarLink.title = userName
      let twPc_avatarImg = document.querySelector(".twPc-avatarImg")
      twPc_avatarImg.alt = userName
      let twPc_divName = document.querySelector(".twPc-divName")
      twPc_divName.textContent = data["name"]
      let span_userName = document.querySelector(".span-userName")
      span_userName.textContent = userName

      let tweets = document.querySelector(".tweets");
      tweets.href = twitterPage + userName
      tweets.querySelector(".twPc-StatValue").textContent = data["statuses_count"]

      let following = document.querySelector(".following");
      following.href = twitterPage + userName + "/following"
      following.querySelector(".twPc-StatValue").textContent = data["friends_count"]

      let followers = document.querySelector(".followers")
      followers.href = twitterPage + userName + "/followers"
      followers.querySelector(".twPc-StatValue").textContent = data["followers_count"]

      let join = document.querySelector(".join")
      join.querySelector(".twPc-StatValue").textContent = data["created_at"].slice(4,10)+", "+data["created_at"].slice(24)

      let average = document.querySelector(".average")
      average.querySelector(".twPc-StatValue").textContent = "==average=="

      let labelUser = document.querySelector(".labelUser")
      labelUser.querySelector(".twPc-StatValue").textContent = data["Lable"]
      $(".Max_of_Tweets_in_DAY").append(data["Max_of_Tweets_in_DAY"])
      $(".avg_number_of_Hashtag").append(data["avg_number_of_Hashtag"])
      $(".location").append(data["location"])
      $(".url").append(data["url"])
    }).done(function(data) {
    $.get(API + "/getUserWithCategories", {name: userName}, function(data) {
      console.log(data);
      let userRealBot = data[0]
      let user4Gategories = data[1]
      let average = document.querySelector(".average")
      average.querySelector(".twPc-StatValue").textContent = userRealBot.avg_URL
      // $(".twPc-StatValue").text(userRealBot.avg_URL)
      var data = [{
        label: user4Gategories.Lable,
        data: user4Gategories.Label1Per
      }, {
        label: user4Gategories.Lable2,
        data: user4Gategories.Label2Per
      }, {
        label: user4Gategories.Lable3,
        data: user4Gategories.Label3Per
      }, {
        label: user4Gategories.Lable4,
        data: user4Gategories.Label4Per
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
  }).fail(function(message) {
    console.log(message);
    alert(message.responseText);
  }).always(function() {
    // alert( "complete" );
  });
});
