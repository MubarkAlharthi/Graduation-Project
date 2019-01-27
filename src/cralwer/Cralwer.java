package cralwer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mainPkg.getJson;
import twitter4j.JSONArray;
import twitter4j.PagableResponseList;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

public class Cralwer {

	public static Twitter twitter;
	public connDB db;

	public Cralwer() {
		db = new connDB();
	}
//	String	consumer_key = "";
//	String	consumer_secret = "";
//	String	access_token = "15257539-";
//	String	access_secret = "";
//

  String consumer_key = "";
  String consumer_secret = "";
  String access_token = "";
  String access_secret = "";


//public void getAcessto(connDB db)throws Exception {
//	twitter = new TwitterFactory().getInstance();
//    twitter.setOAuthConsumer(consumer_key,consumer_secret );
//    twitter.setOAuthAccessToken(new AccessToken( access_token, access_secret));
//    db= new connDB();
//  //  db.Update_Number_Hashtag_Tweets();
//}
	public void getAcessto() {
		twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumer_key, consumer_secret);
		twitter.setOAuthAccessToken(new AccessToken(access_token, access_secret));
	}

	/*
	 * this method will add the user in database and also it Friend by using its
	 * screen name
	 */

	public void getFriend(String Screen_Name) {
		PagableResponseList<User> statuses = null;

		try {
			ResponseList<Status> userinfo = twitter.getUserTimeline(Screen_Name);
			statuses = twitter.getFriendsList(Screen_Name, -1, 200);
			for (User newUser : statuses) {
				db.insertuser(newUser); // return true if the User was inserted
				db.insert_User_Following(userinfo.get(0).getUser().getId(), newUser.getId());
			}
			db.updateisCompelet(userinfo.get(0).getUser().getId());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	/*
	 * this method will add the user in database also it Friend and its Tweets by
	 * using its screen name
	 */

	public void getFriend_and_tweet(String Screen_Name, String Label) {
		PagableResponseList<User> statuses = null;
		ResponseList<Status> userinfo = null;

		try {

			userinfo = twitter.getUserTimeline(Screen_Name);
			statuses = twitter.getFriendsList(Screen_Name, -1, 200);
			db.insertuser(userinfo.get(0).getUser()); // this line will insert the user given to table of Users
			db.insert_User_to_Trning_set(userinfo.get(0).getUser(), Label);// this line will insert the user given to
																			// table of User_to_Trning_set
			for (User newUser : statuses) {
				db.insertuser(newUser); // return true if the User was inserted
				db.insert_User_Following(userinfo.get(0).getUser().getId(), newUser.getId());
			}
			System.out.println("Done from FriendsList for " + Screen_Name);
			db.updateisCompelet(userinfo.get(0).getUser().getId());

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		List<ResponseList<Status>> tweets = get_tweet(userinfo.get(0).getUser().getId());

	}

	public String  getuserInfo_and_Tweet(String Screen_Name) {
		ResponseList<Status> userinfo = null;

		try {

			userinfo = twitter.getUserTimeline(Screen_Name);// may return two error not exist or protected
			if (!userinfo.isEmpty()) {

				db.insertuser(userinfo.get(0).getUser());//insert to User table
				get_tweet(userinfo.get(0).getUser().getId());
				System.out.println("Done Getting user Tweets");
				return "Done Getting user Tweets";
			}
		} catch (TwitterException e) {
			// TODO: handle exception
			return e.getMessage();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "there are no Tweets";

	}

	/*
	 * this method will take a user id and return a list of its Tweets or Retweets
	 * or Replies
	 */
	private List<ResponseList<Status>> get_tweet(long id) {
		List<ResponseList<Status>> tweets = new ArrayList(2);
		try {

			Paging paging = new Paging(1, 200);
			ResponseList<Status> tmp_Statues = twitter.getUserTimeline(id, paging); // get 200 Tweet
			tweets.add(0, tmp_Statues);
			paging = new Paging(2, 200);
			tmp_Statues = twitter.getUserTimeline(id, paging);// get another 200 Tweet
			tweets.add(1, tmp_Statues);

			System.out.println("insert Tweets...");
			System.out.println("path class");
			System.getProperty("java.class.path");

			for (Status s : tweets.get(0))
				db.insertTweets(s);
			System.out.println("tweets.get(1)");

			for (Status s : tweets.get(1))
				db.insertTweets(s);
			System.out.println("Update_Creat_At(id);");

			db.Update_Creat_At(id);
			System.out.println("update_Complete_Tweets");

			db.update_Complete_Tweets(id);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());

		}
		return tweets;
	}

	public void Feader() {

		try {
			ResultSet set = db.reed_all();
			while (set.next()) {
				System.out.println(set.getString("screen_name") + ",  " + set.getString("protected") + ",  "
						+ set.getString("location"));
				if (check_the_Location(set.getString("location"))) {
					System.out.println(getuserInfo_and_Tweet(set.getString("screen_name")));
					db.update_Complete_Tweets(get_id(set.getString("screen_name")));
				}
				// db.insert_Label_Tweet();
				// db.insert_Label_User(set.getLong("id"));
			}
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(e.getMessage());
		}

	}

	public void Cralwing() throws Exception {

		try {
			ResultSet set = db.reed_all();
			while (set.next()) {
				if (check_the_Location(set.getString("location"))) {
					getFriend(set.getString("screen_name"));
					db.updateisCompelet(get_id(set.getString("screen_name")));
				}
			}
		} catch (TwitterException e) {
			// TODO: handle exception

			System.out.println(e.getMessage());
		}

	}

//getFrind will take user id from the database than add his friend to Database

////getFrind will take user id from the database than add his friend to Database
//public void getFrind(String screen_name){
//	//PagableResponseList<User> statuses=null;
//	try {
//		ResponseList<Status> ss = twitter.getUserTimeline(screen_name);
//    //statuses = twitter.getFriendsList(id,-1,200); //statuses has all Friend of the user given in parameter
//    for (Status newUser : ss) {
//    	 db.insertuser(newUser.getUser());// return true if the User was inserted
//    	 newUser.
//        // db.insert_User_Following(id, newUser.getId());
//    }
//	db.updateisCompelet(id);
//    System.out.println("Done:)");
//	}
//	catch (Exception e) {
//		// TODO: handle exception
//		System.out.println(e.getMessage());
//	}
//}

	public boolean check_the_Location(String s) {

		final String REGEX = "Saudi|Riyadh|السعودية|سعودي|Al Khobar|الرياض|Dammam|Jeddah|KSA|ksa|Ksa|الدمام|جدة|المنورة|مكة|المكرمة";
		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(s); // get a matcher object

		return m.find();
	}
// is start from specific number of user
// to continue add new users that you may not see there Friends

	public long get_id(String user_name) {

		long id = 0;
		try {
			id = twitter.getUserTimeline(user_name).get(0).getUser().getId();

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(e.toString());
		}

		return id;
	}

	public JSONArray getInfo(String Name) throws Exception {

		long id = get_id(Name);
		JSONArray jsonArr = new JSONArray();

		if (db.checkforID_User_Lable(id)) {
			String sql = "select * from User_Lable where User_Lable.id = \"" + id + "\";";
			ResultSet result = getJson.getResultQuery(db, sql);
			getJson.appendToJsonArray(result, jsonArr);
		}

		if (db.checkforID_Userswiththirecategory(id)) {
			String sql = "select * from Userswiththirecategory where Userswiththirecategory.id = \"" + id
					+ "\";";
			ResultSet result = getJson.getResultQuery(db, sql);
			getJson.appendToJsonArray(result, jsonArr);
		}

		return jsonArr;
	}

	public String collectFromAPI(String Name) {
		String response = getuserInfo_and_Tweet(Name);// will collect the information about and its Tweets
		try {
		if (response.equals("Done Getting user Tweets")) {
			db.insert_Label_Tweet(get_id(Name));
			db.insert_Label_User(get_id(Name));
			return "success";
		} else {
			return response;
		}
		}catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}

	}
}
