package cralwer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.User;

public class connDB {
	public Connection conn;

	//// connecting
	public connDB() {
		try {
			Class.forName("org.sqlite.JDBC");

			// db parameters
			String url = "jdbc:sqlite:/Users/Donat/Downloads/user_classfcatio_V5.0.db";//
			// String url
			// ="jdbc:mysql://localhost/user_classfcation?user=root&password=Mcsa@1234";
			// create a connection to the database
			conn = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");

			System.out.println("Done:)");
		} catch (Exception ex) {
			System.out.println("there is an Exception " + ex.toString());
		}
	}

	protected boolean checkforIDFollowing(long id) throws SQLException, ClassNotFoundException {

		Statement stmt = conn.createStatement();
		ResultSet myruslet = stmt.executeQuery("SELECT user_id FROM Following;"); // myruslet will be a list of all the
																					// ID'S Right Now

		long tmp2 = 0;
		while (myruslet.next()) {
			tmp2 = myruslet.getLong(1);
			if (id == tmp2)
				return true;
		}

		return false;
	}

	protected boolean checkforID_Users_with_thire_category(long id) throws SQLException, ClassNotFoundException {

		Statement stmt = conn.createStatement();
		ResultSet myruslet = stmt.executeQuery("SELECT id FROM Userswiththirecategory where id="+id+";"); // myruslet will be a list of
																							// all the
		// ID'S Right Now

		while (myruslet.next()) 
				return true;
		
		return false;
	}

	protected boolean checkforID_Users_with_thire_category(String screen_Name)
			throws SQLException, ClassNotFoundException {

		Statement stmt = conn.createStatement();
		ResultSet myruslet = stmt.executeQuery("SELECT screen_name FROM Userswiththirecategory;"); // myruslet will be a
																									// list of all the
		// ID'S Right Now

		String tmp2;
		while (myruslet.next()) {
			tmp2 = myruslet.getString(1);
			if (screen_Name.equals(tmp2))
				return true;
		}

		return false;
	}

	protected boolean checkforID(long id) throws SQLException, ClassNotFoundException {

		Statement stmt = conn.createStatement();
		ResultSet myruslet = stmt.executeQuery("SELECT id FROM Users where id="+id+";"); // myruslet will be a list of all the ID'S
																// Right Now
		while (myruslet.next())
				return true;
		

		return false;
	}

	protected boolean checkforID_Trning_Set_Real_Bot(long id) throws SQLException, ClassNotFoundException {

		Statement stmt = conn.createStatement();
		ResultSet myruslet = stmt.executeQuery("SELECT id FROM TrningSetRealBot;"); // myruslet will be a list of all
																					// the ID'S
		// Right Now

		long tmp2 = 0;
		while (myruslet.next()) {
			tmp2 = myruslet.getLong(1);
			if (id == tmp2)
				return true;
		}

		return false;
	}

	protected boolean checkforID_Trning_Set_Real_Bot(String screen_Name) throws SQLException, ClassNotFoundException {

		Statement stmt = conn.createStatement();
		ResultSet myruslet = stmt.executeQuery("SELECT screen_name FROM TrningSetRealBot;"); // myruslet will be a list
																								// of all the
		// ID'S Right Now

		String tmp2;
		while (myruslet.next()) {
			tmp2 = myruslet.getString(1);
			if (screen_Name.equals(tmp2))
				return true;
		}

		return false;
	}

	protected boolean checkforID_Userswiththirecategory(long id) throws SQLException, ClassNotFoundException {

		Statement stmt = conn.createStatement();
		ResultSet myruslet = stmt.executeQuery("SELECT id FROM Userswiththirecategory;"); // myruslet will be a list of all
																					// the ID'S
		// Right Now

		long tmp2 = 0;
		while (myruslet.next()) {
			tmp2 = myruslet.getLong(1);
			if (id == tmp2)
				return true;
		}

		return false;
	}
	
	protected boolean checkforID_User_Lable(long id) throws SQLException, ClassNotFoundException {

		Statement stmt = conn.createStatement();
		ResultSet myruslet = stmt.executeQuery("SELECT id FROM User_Lable;"); // myruslet will be a list of all
																					// the ID'S
		// Right Now

		long tmp2 = 0;
		while (myruslet.next()) {
			tmp2 = myruslet.getLong(1);
			if (id == tmp2)
				return true;
		}

		return false;
	}


	protected boolean checkforID_Userswiththirecategory(String screen_Name) throws SQLException, ClassNotFoundException {

		Statement stmt = conn.createStatement();
		ResultSet myruslet = stmt.executeQuery("SELECT screen_name FROM Userswiththirecategory;"); // myruslet will be a list
																								// of all the
		// ID'S Right Now

		String tmp2;
		while (myruslet.next()) {
			tmp2 = myruslet.getString(1);
			if (screen_Name.equals(tmp2))
				return true;
		}

		return false;
	}

	protected boolean checkforID_for_Tweets(long Tweet_id, long User_id) throws SQLException, ClassNotFoundException {

		Statement stmt = conn.createStatement();
		String sql="SELECT id,users_id FROM Tweets where id="+Tweet_id+" And users_id="+User_id;
		ResultSet myruslet = stmt.executeQuery(sql); // myruslet will be a list of all the
																					// ID'
		while (myruslet.next()) 
				return true;
		
		return false;
	}

	protected boolean checkforID_for_Likes(long users_id, long Tweets_users_TweeterId, long Tweets_users_id)
			throws SQLException, ClassNotFoundException {

		Statement stmt = conn.createStatement();
		ResultSet myruslet = stmt.executeQuery("SELECT users_id ,Tweets_users_TweeterId ,Tweets_users_id FROM Likes;"); // myruslet
																														// will
																														// be
																														// a
																														// list
																														// of
																														// all
																														// the
																														// ID'S
		// Right Now
		long tmp1 = 0;
		long tmp2 = 0;
		long tmp3 = 0;
		while (myruslet.next()) {
			tmp1 = myruslet.getLong(1);
			tmp2 = myruslet.getLong(2);
			tmp3 = myruslet.getLong(3);
			if (users_id == tmp1 && tmp2 == Tweets_users_TweeterId && tmp3 == Tweets_users_id)
				return true;
		}

		return false;
	}

	protected boolean checkforplace(String id) throws SQLException, ClassNotFoundException {
System.out.println("Done place");
		Statement stmt = conn.createStatement();
		ResultSet myruslet = stmt.executeQuery("SELECT id FROM place where id= \""+id+"\";"); // myruslet will be a list of all the ID'S
																			// Right Now
		while (myruslet.next()) 
				return true;
	

		return false;
	}

	protected boolean checkfor_Hashtag(String Hashtag) throws SQLException, ClassNotFoundException {
		System.out.println("Done Hashtag");

		Statement stmt = conn.createStatement();
		ResultSet myruslet = stmt.executeQuery("SELECT text FROM Hashtag where text=\""+Hashtag+"\";"); // myruslet will be a list of all the ID'S

		while (myruslet.next()) 
				return true;
		

		return false;
	}

	protected boolean checkfor_Has_Hashtag(long tweet_id, long user_id, String Hashtag)
			throws SQLException, ClassNotFoundException {
		System.out.println("Done checkfor_Has_Hashtag");

		String sql="SELECT  Tweets_id ,Tweets_users_id,Hashtag_text FROM TweetshasHashtag where Tweets_id= "+tweet_id+" And Tweets_users_id="+user_id+" And Hashtag_text=\""+Hashtag+"\";";
		Statement stmt = conn.createStatement();
		ResultSet myruslet = stmt	
				.executeQuery(sql); // myruslet
																											
		while (myruslet.next()) {
				return true;
		}

		return false;
	}

	public void close() {
		try {
			conn.close();
			System.out.println("the connecation is close ");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	protected boolean insert_Place(String plice_id, String place_type, String name, String country) throws Exception {

		// user
		if (!checkforplace(plice_id)) {
			PreparedStatement p;
			p = (PreparedStatement) conn
					.prepareStatement("insert into Place(id ,place_type,name,country) values((?),(?),(?),(?))");

			p.setString(1, plice_id);
			p.setString(2, place_type);
			p.setString(3, name);
			p.setString(4, country);
			p.execute();
			return true;
		}

		return false;

	}

	protected boolean insert_Hashtag(String Hashtag) throws Exception {

		// user
		if (!checkfor_Hashtag(Hashtag)) {
			PreparedStatement p;
			p = (PreparedStatement) conn.prepareStatement("insert into Hashtag(text) values(?)");
			p.setString(1, Hashtag);
			p.execute();
			return true;

		}

		return false;

	}

	protected void insert_Tweets_has_Hashtag(long Tweets_id, long Tweets_users_id, String Hashtag_text)
			throws Exception {
		PreparedStatement p;
		if (!checkfor_Has_Hashtag(Tweets_id, Tweets_users_id, Hashtag_text)) {
			p = (PreparedStatement) conn.prepareStatement(
					"insert into TweetshasHashtag(Tweets_id ,Tweets_users_id,Hashtag_text) values((?),(?),(?))");
			p.setLong(1, Tweets_id);
			p.setLong(2, Tweets_users_id);
			p.setString(3, Hashtag_text);
			p.execute();
		}
	}

	protected boolean insertTweets(Status Tweets) throws Exception {
		// Tweets

		if (!checkforID_for_Tweets(Tweets.getId(), Tweets.getUser().getId())) {
			PreparedStatement p;
			p = (PreparedStatement) conn
					.prepareStatement("insert into Tweets(id ,retweet_count,favorite_count,created_at,"
							+ "source,possibly_sensitive,text,place_id,users_id,in_reply_to_status_id,"
							+ "quoted_status_id,favorited,retweeted,lang,in_reply_to_screen_name,url_in_tweet,Number_Hashtag,URLs_Number)"
							+ " values((?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?))");
			Date sd = new java.sql.Date((Tweets.getCreatedAt()).getTime());
			p.setLong(1, Tweets.getId());
			p.setInt(2, Tweets.getRetweetCount());
			p.setInt(3, Tweets.getFavoriteCount());
			p.setDate(4, sd);
			p.setString(5, Tweets.getSource());
			p.setBoolean(6, Tweets.isPossiblySensitive());
			p.setString(7, Tweets.getText());
			if (Tweets.getPlace() != null)
				p.setString(8, Tweets.getPlace().getId());
			else
				p.setString(8, "NULL");

			p.setLong(9, Tweets.getUser().getId());
			p.setLong(10, Tweets.getInReplyToStatusId());
			p.setLong(11, Tweets.getQuotedStatusId());
			p.setBoolean(12, Tweets.isFavorited());
			p.setBoolean(13, Tweets.isRetweet());
			p.setString(14, Tweets.getLang());
			p.setString(15, Tweets.getInReplyToScreenName());
			p.setString(16, Tweets.getURLEntities().toString());

			p.setInt(18, Tweets.getFavoriteCount());

			if (Tweets.getPlace() != null)
				insert_Place(Tweets.getPlace().getId(), Tweets.getPlace().getPlaceType(), Tweets.getPlace().getName(),
						Tweets.getPlace().getCountry());

			HashtagEntity[] hashtags = Tweets.getHashtagEntities();
			String tmp;

			p.setInt(17, hashtags.length);
			p.setInt(18, Tweets.getURLEntities().length);
			p.execute();
			
			for (int i = 0; i < hashtags.length; i++) {
				tmp = hashtags[i].getText();
				insert_Hashtag(tmp);
				insert_Tweets_has_Hashtag(Tweets.getId(), Tweets.getUser().getId(), tmp);
			}

			return true;

		}

		return false;
	}

	protected boolean checkforID_for_Label_Tweet(long Tweet_id, long User_id)
			throws SQLException, ClassNotFoundException {

		Statement stmt = conn.createStatement();
		ResultSet myruslet = stmt
				.executeQuery("select tweet_Lable.Tweet_id , tweet_Lable.Tweet_user_id from tweet_Lable;");
		long tmp1 = 0;
		long tmp2 = 0;
		while (myruslet.next()) {
			tmp1 = myruslet.getLong(1);
			tmp2 = myruslet.getLong(2);
			if (Tweet_id == tmp1 && tmp2 == User_id)
				return true;
		}

		return false;
	}

	//
	protected void insert_Label_Tweet(long Tweets_users_id) throws Exception {
		System.out.println("in insert_Label_Tweet ");
		ResultSet tweets = null;
		Weka_Api weka_api = new Weka_Api();// give path of modle
		LinkedList<Tweet_Lable> tweets_Lable = new LinkedList<>();
		Userswiththirecategory User;
		PreparedStatement p;
		p = (PreparedStatement) conn.prepareStatement(
				"select Tweets.id,Tweets.users_id,Tweets.text,Tweets.lang from Tweets where Tweets.users_id= ? ;");
		p.setLong(1, Tweets_users_id);
		tweets = p.executeQuery();
		
		while (tweets.next()) {
			if(tweets.getString("lang").equals("ar"))
				tweets_Lable.add(new Tweet_Lable(tweets.getLong(1), tweets.getLong(2), tweets.getString(3)));		
		}
		if(tweets_Lable.isEmpty())
			throw new Exception("Not avaliable to categorize this user");
			
		tweets_Lable = weka_api.categorize(tweets_Lable);
		Tweet_Lable tmp = null;
		
		for (int i = 0; i < tweets_Lable.size(); i++) {
			tmp = tweets_Lable.get(i);
			if (!checkforID_for_Label_Tweet(tmp.getId(), tmp.getUser_id())) {
				p = (PreparedStatement) conn.prepareStatement(
						"insert into tweet_Lable(Tweet_id ,Tweet_user_id,Tweets,Lable) values((?),(?),(?),(?))");
				p.setLong(1, tmp.getId());
				p.setLong(2, tmp.getUser_id());
				p.setString(3, tmp.getText());
				p.setString(4, tmp.getLable());
				p.execute();

			}
		}

		befor_insert_Userswiththirecategory(Tweets_users_id);
		
		System.out.println("Done");
	}

	 private  void befor_insert_Userswiththirecategory(long  user_id) throws Exception {
		 	ResultSet tweets = null;
			double tweet_number;
			PreparedStatement p;
			Userswiththirecategory User;
			String sql;
			String name="",screen_name="";			
			// insert with table into User_category
		
				sql = "select Users.name,Users.screen_name,tweet_Lable.Lable, count(tweet_Lable.Lable) as w from Users inner join tweet_Lable on Users.id = tweet_Lable.Tweet_user_id   where Users.id= ? AND tweet_Lable.Lable!=\"\"  group by  tweet_Lable.Lable  order by count(tweet_Lable.Lable) desc limit 4";
				tweet_number = number_Tweet(user_id);
				p = (PreparedStatement) conn.prepareStatement(sql);
				p.setLong(1,user_id);
				tweets = p.executeQuery();	
				User = new Userswiththirecategory();
				
				while (tweets.next()) {
					name=tweets.getString(1); screen_name=tweets.getString(2);
					User.labels.add(new LabelCount(tweets.getString("Lable"), (tweets.getInt("w") / tweet_number)));
				}
				User.setId(user_id);
				User.setName(name);User.setScreenName(screen_name);		
				User.setNumberOfTweet((int)tweet_number);
				insert_Userswiththirecategory(User);
		 
	 }
	 
	 private void insert_Userswiththirecategory(Userswiththirecategory user) throws Exception{
		String []Label= {"","","",""};
		double []perLabel={-1,-1,-1,-1}; // -1 means that does not have label# 
			
		
		 for(int i=0;i<user.labels.size();i++) {
			 Label[i]=user.labels.get(i).label;
			 perLabel[i]=user.labels.get(i).weight;
			 System.out.println(Label[i]+"   "+perLabel[i]);
		 }
		 PreparedStatement p;
		 if (!checkforID_Userswiththirecategory(user.getId())) {
				p = (PreparedStatement) conn.prepareStatement(
						"insert into Userswiththirecategory(id ,name,screen_name,Lable,Label1Per,Lable2,Label2Per,Lable3,Label3Per,Lable4,Label4Per,Number_Tweets) values((?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?))");
				p.setLong(1, user.getId());
				p.setString(2, user.getName());
				p.setString(3, user.getScreenName());
				p.setString(4,Label[0]);
				p.setDouble(5, perLabel[0]);
				p.setString(6,Label[1]);
				p.setDouble(7, perLabel[1]);
				p.setString(8,Label[2]);
				p.setDouble(9, perLabel[2]);
				p.setString(10,Label[3]);
				p.setDouble(11, perLabel[3]);
				p.setInt(12, user.getNumberOfTweet());
				p.execute();

			}
		 
		 
		 
	 }
	 

		private int number_Tweet(long id) throws Exception {
			PreparedStatement p;
			String sql = "select count(*) from tweet_Lable where tweet_Lable.Tweet_user_id= ? ;";
			p = (PreparedStatement) conn.prepareStatement(sql);
			p.setString(1, Long.toString(id));
			return p.executeQuery().getInt(1);
		}

		protected void insert_Label_User(long user_id) throws Exception {
			ResultSet user_info = null;
			Weka_Api weka_api = new Weka_Api();// give path of modle
			User_Lable user = new User_Lable();
			user.setId(user_id);
			PreparedStatement p;
			p = (PreparedStatement) conn.prepareStatement(
					"select Users.verified,Users.followers_count,Users.friends_count,Users.geo_enabled from Users where Users.id= ? ;");
			p.setLong(1, user_id);
			user_info = p.executeQuery();
			user.setVerified(user_info.getInt(1));
			user.setFollowers_count(user_info.getInt(2));
			user.setFriends_count(user_info.getInt(3));
			user.setGeo_enabled(user_info.getInt(4));
			user = ubdate_Max_Tweet(user);
			user = Update_avrage_of_URLs(user);
			user = Update_avrage_of_Hashtags(user);
			user = Update_time_Tweet(user);
			user = ubdate_source(user);
			user = weka_api.classify(user);
			
			System.out.println("the lABLE IS "+user.getLable());
			if(!checkforID_User_Lable(user.getId())) {
			p = (PreparedStatement) conn.prepareStatement(
					"insert into User_Lable(id,followers_count ,friends_count,verified,geo_enabled,Max_of_Tweets_in_DAY,avg_number_of_Hashtag,avg_URL,after_Midnight,morning,after_Noon,evening,Lable) values((?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?));");
			p.setLong(1, user.getId());
			p.setInt(2, user.getFollowers_count());
			p.setInt(3, user.getFriends_count());
			p.setInt(4, user.getVerified());
			p.setInt(5, user.getGeo_enabled());
			p.setInt(6, user.getMax_of_Tweets_in_DAY());
			p.setDouble(7, user.getAvg_number_of_Hashtag());
			p.setDouble(8, user.getAvg_URL());
			p.setInt(9, user.getAfter_Midnight());
			p.setInt(10, user.getMorning());
			p.setInt(11, user.getAfter_Noon());
			p.setInt(12, user.getEvening());
			p.setString(13, user.getLable());
			p.execute();
			}
		}

		protected boolean insertuser(User user) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			// user
			try {
				if (!checkforID(user.getId())) {
					PreparedStatement p;
					p = (PreparedStatement) conn.prepareStatement(
							"insert into Users(id ,followers_count,friends_count,statuses_count,favourites_count,description,created_at,location,lang,name,screen_name,url,protected,verified,listed_count,utc_offset,time_zone,geo_enabled,contributors_enabled,last_updated) values((?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?))");
					p.setLong(1, user.getId());
					p.setInt(2, user.getFollowersCount());
					p.setInt(3, user.getFriendsCount());
					p.setInt(4, user.getStatusesCount());
					p.setInt(5, user.getFavouritesCount());
					p.setString(6, user.getDescription());
					p.setString(7, user.getCreatedAt().toString());
					p.setString(8, user.getLocation());
					p.setString(9, user.getLang());
					p.setString(10, user.getName());
					p.setString(11, user.getScreenName());
					p.setString(12, user.getURL());
					p.setBoolean(13, user.isProtected());
					p.setBoolean(14, user.isVerified());
					p.setInt(15, user.getListedCount());
					p.setInt(16, user.getUtcOffset());
					p.setString(17, user.getTimeZone());
					p.setBoolean(18, user.isGeoEnabled());
					p.setBoolean(19, user.isContributorsEnabled());
					p.setString(20, dtf.format(now));
					p.execute();
					return true;
				}
			} catch (SQLException e) {
				// TODO: handle exception
			} catch (ClassNotFoundException e) {
				// TODO: handle exception
			}

			return false;

		}

		protected boolean insert_User_to_Trning_set(User user, String str) throws Exception {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			// user
			if (!checkforID_Trning_Set_Real_Bot(user.getId())) {
				PreparedStatement p;
				p = (PreparedStatement) conn.prepareStatement(
						"insert into TrningSetRealBot(id ,followers_count,friends_count,statuses_count,favourites_count,description,created_at,location,lang,name,screen_name,url,protected,verified,listed_count,utc_offset,time_zone,geo_enabled,contributors_enabled,last_updated,Label) values((?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?),(?))");
				p.setLong(1, user.getId());
				p.setInt(2, user.getFollowersCount());
				p.setInt(3, user.getFriendsCount());
				p.setInt(4, user.getStatusesCount());
				p.setInt(5, user.getFavouritesCount());
				p.setString(6, user.getDescription());
				p.setString(7, user.getCreatedAt().toString());
				p.setString(8, user.getLocation());
				p.setString(9, user.getLang());
				p.setString(10, user.getName());
				p.setString(11, user.getScreenName());
				p.setString(12, user.getURL());
				p.setBoolean(13, user.isProtected());
				p.setBoolean(14, user.isVerified());
				p.setInt(15, user.getListedCount());
				p.setInt(16, user.getUtcOffset());
				p.setString(17, user.getTimeZone());
				p.setBoolean(18, user.isGeoEnabled());
				p.setBoolean(19, user.isContributorsEnabled());
				p.setString(20, dtf.format(now));
				p.setString(21, str);
				p.execute();
				return true;

			}

			return false;

		}

		protected void insert_User_Following(long user, long user_Following) throws Exception {
			// user
			PreparedStatement p;
			p = (PreparedStatement) conn.prepareStatement("insert into Following(user_id ,users_id) values((?),(?))");
			p.setLong(1, user);
			p.setLong(2, user_Following);
			p.execute();

		}

		protected boolean insert_User_Followers(long user, long user_Followers) throws Exception {

			// user
			if (!checkforID(user_Followers)) {
				PreparedStatement p;
				p = (PreparedStatement) conn.prepareStatement("insert into Followers(user_id ,users_id) values((?),(?))");
				p.setLong(1, user);
				p.setLong(2, user_Followers);
				p.execute();
				return true;

			}

			return false;

		}

		protected void insert_training_set(long Tweet_id, long user_id, String text) throws Exception {
			// user

			PreparedStatement p;
			p = (PreparedStatement) conn
					.prepareStatement("insert into trainingset(Tweet_id,Tweet_user_id,Tweet_text) values((?),(?),(?))");
			p.setLong(1, Tweet_id);
			p.setLong(2, user_id);
			p.setString(3, text);
			p.execute();

		}

		protected void insert_User_Likes(long users_id, long Tweets_users_TweeterId, long Tweets_users_id)
				throws Exception {
			// user
			if (!checkforID_for_Likes(users_id, Tweets_users_TweeterId, Tweets_users_id)) {
				PreparedStatement p;
				p = (PreparedStatement) conn.prepareStatement(
						"insert into Likes(users_id ,Tweets_users_TweeterId,Tweets_users_id) values((?),(?),(?))");
				p.setLong(1, users_id);
				p.setLong(2, Tweets_users_TweeterId);
				p.setLong(3, Tweets_users_id);
				p.execute();
			}
		}

		//
		protected ResultSet reed_all() throws Exception {
			// reed all
			Statement stmt = conn.createStatement();
			ResultSet myruslet = stmt.executeQuery("SELECT * FROM Users where Users.Complete_Tweets=0 and  Users.followers_count>200 And protected=0;");
			return myruslet;
		}
		
		//
		protected ResultSet reed_all_forclassfcation() throws Exception {
			// reed all
			Statement stmt = conn.createStatement();
			ResultSet myruslet = stmt.executeQuery("SELECT * FROM Users where Users.Complete_Label=0 and  Users.followers_count>200 And protected=0;");
			return myruslet;
		}

		protected void updateisCompelet(long id) throws Exception {
			// CNAME
			Statement stmt = conn.createStatement();
			stmt.execute("UPDATE Users SET isComplete =1 where id==" + id + ";");
		}

		protected void update_Complete_Tweets(long id) throws Exception {
			// CNAME
			Statement stmt = conn.createStatement();
			stmt.execute("UPDATE Users SET Complete_Tweets =1 where id==" + id + ";");

		}
		
		protected void update_Complete_Label(long id) throws Exception {
			// CNAME
			Statement stmt = conn.createStatement();
			stmt.execute("UPDATE Users SET Complete_Label =1 where id==" + id + ";");

		}

		protected void update_Complete_Likes(long id) throws Exception {
			// CNAME
			Statement stmt = conn.createStatement();
			stmt.execute("UPDATE Users SET Complete_Likes =1 where id==" + id + ";");
		}

		// chose where from start Collecting data
		protected ResultSet reed_from(int isComplete) throws Exception {
			// CNAME
			Statement stmt = conn.createStatement();
			String sql = "SELECT Screen_Name,id,location  FROM Users WHERE Users.isComplete=" + isComplete
					+ " and Users.followers_count>=200 and Users.protected=0 ORDER BY Users.followers_count  DESC";
			ResultSet myruslet = stmt.executeQuery(sql);
			return myruslet;
		}

		// chose where from start Collecting data
		protected ResultSet reed_from_for_Tweets(int Complete_Tweets) throws Exception {
			// CNAME

			Statement stmt = conn.createStatement();
			String sql = "SELECT Screen_Name,id,location  FROM Users WHERE Users.Complete_Tweets==" + Complete_Tweets
					+ " and Users.isComplete==1"// the isComplete mean the user already there Friend was taking
					+ " and Users.protected==0 ORDER BY Users.followers_count  DESC";
			ResultSet myruslet = stmt.executeQuery(sql);
			return myruslet;
		}

		// chose where from start Collecting data
		protected ResultSet reed_from_for_Likes(int Complete_Likes) throws Exception {
			// CNAME

			Statement stmt = conn.createStatement();
			String sql = "SELECT Screen_Name,id,location  FROM Users WHERE Users.Complete_Likes==" + Complete_Likes
					+ " and Users.isComplete==1 and Users.Complete_Tweets==1"// the isComplete mean the user already there
																				// Friend was taking
					+ " and Users.protected==0 ORDER BY Users.followers_count  DESC";
			ResultSet myruslet = stmt.executeQuery(sql);
			return myruslet;
		}

		protected ResultSet reed_from_Tweet(long users_id) throws Exception {
			// CNAME

			Statement stmt = conn.createStatement();
			String sql = "SELECT id,users_id,text FROM Tweets WHERE users_id==" + users_id;
			ResultSet myruslet = stmt.executeQuery(sql);
			return myruslet;
		}

		// Update Users number_Tweets attribute
		protected void Update_Number_Tweet_User() throws Exception {
			Statement stmt = conn.createStatement();
			String sql = "select id from TrningSetRealBot;";
			ResultSet myruslet2, myruslet = stmt.executeQuery(sql); // myruslet for the ids and myruslet2 for the count(*)
			List<Long> arr = new ArrayList<Long>(769);

			while (myruslet.next())
				arr.add(myruslet.getLong(1));
			System.out.println("Done add");

			for (int i = 0; i < arr.size(); i++) {
				System.out.println("the i= " + i);
				String sql2 = "select count(*) from Tweets where Tweets.users_id=" + arr.get(i) + ";";
				myruslet2 = stmt.executeQuery(sql2);
				stmt.execute("update TrningSetRealBot  Set Number_Tweets=" + myruslet2.getInt(1) + " where id=" + arr.get(i)
						+ ";");
				System.out.println("Done");
			}

		}



		// Update
		protected User_Lable Update_avrage_of_URLs(User_Lable user) {

			ResultSet myruslet;

			try {
				Statement stmt = conn.createStatement();
				String sql;

				sql = "SELECT AVG(Tweets.URLs_Number)  FROM Tweets where Tweets.users_id= " + user.getId() + " ;";
				myruslet = stmt.executeQuery(sql);

				while (myruslet.next())
					user.setAvg_URL(myruslet.getDouble(1));

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}

			return user;

		}

		protected User_Lable Update_time_Tweet(User_Lable user) {

			ResultSet myruslet;
			PreparedStatement p;
			int c = 0;

			try {
				Statement stmt = conn.createStatement();

				String sql2 = "", sql3 = "";

				sql2 = "select Tweets.hour_of_Tweet,count(Tweets.users_id) from Tweets where Tweets.users_id= ? group by  Tweets.hour_of_Tweet order by Tweets.hour_of_Tweet";
				p = (PreparedStatement) conn.prepareStatement(sql2);
				p.setLong(1, user.getId());
				myruslet = p.executeQuery();

				while (myruslet.next()) {

					if (myruslet.getString(1) == null)
						continue;

					switch (myruslet.getString(1)) {

					case "0-6":
						user.setAfter_Midnight(myruslet.getInt(2));
						break;

					case "7-12":
						user.setMorning(myruslet.getInt(2));
						break;
					case "13-18":
						user.setAfter_Noon(myruslet.getInt(2));
						break;
					case "19-23":
						user.setEvening(myruslet.getInt(2));
						break;
					default:
						break;
					}

				}

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}

			return user;
		}
		
		
		
		protected User_Lable ubdate_source(User_Lable user) {
			
			
			ResultSet myruslet;
			PreparedStatement p;

			try {
				Statement stmt = conn.createStatement();
				String sql = "";
				String  source="";
				sql = "select Tweets.source,count(*) as count from Tweets  where Tweets.users_id= ? group by Tweets.source order by count desc limit 1";

				p = (PreparedStatement) conn.prepareStatement(sql);
				p.setLong(1, user.getId());
				myruslet = p.executeQuery();

				while (myruslet.next())
					source = get_the_source(myruslet.getString(1)); // must not take the all text in source 

				user.setSource(source);

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}

			
			
			
			return user;
		}
		
		private String  get_the_source(String s) {
			String []arr=s.split(">");
			arr=arr[1].split("<");
			return arr[0];		
		}

		protected User_Lable ubdate_Max_Tweet(User_Lable user) {

			ResultSet myruslet;
			PreparedStatement p;

			try {
				Statement stmt = conn.createStatement();
				String sql = "";
				int count = 0;
				sql = "select  count(Tweets.users_id)\n"
						+ "           from Tweets where Tweets.users_id= ? group by  Tweets.users_id,Tweets.Day_of_Tweet\n"
						+ "           order by count(Tweets.users_id) desc limit 1";

				p = (PreparedStatement) conn.prepareStatement(sql);
				p.setLong(1, user.getId());
				myruslet = p.executeQuery();

				while (myruslet.next())
					count = myruslet.getInt(1);

				user.setMax_of_Tweets_in_DAY(count);

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}

			return user;

		}

		protected void Update_table_user() {
			ResultSet myruslet;

			String sql = "select  TrningSetTweets.Tweet_user_id FROM TrningSetTweets group by   TrningSetTweets.Tweet_user_id;";
			try {
				PreparedStatement p;
				Statement stmt = conn.createStatement();

				myruslet = stmt.executeQuery(sql); // myruslet for the ids and myruslet2 for the count(*)
				List<Long> arr = new ArrayList<Long>(884);

				while (myruslet.next())
					arr.add(myruslet.getLong(1));

				stmt.close();

				System.out.println("Done add");
				int i = 0;

			   // Tweet_Lable t = new Tweet_Lable();

				for (int j = 0; j < 884; j++) {
					try {
						System.out.println("i=" + (++i));
						p = (PreparedStatement) conn.prepareStatement(
								"select Users.id,Users.name,Users.screen_name from Users where Users.id= ?");
						p.setLong(1, arr.get(j));
						p.execute();

					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e.getMessage());
						continue;
					}

				}

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}

		}

		// Update
		protected User_Lable Update_avrage_of_Hashtags(User_Lable user) {
			ResultSet myruslet;

			try {
				Statement stmt = conn.createStatement();
				String sql2;

				sql2 = "SELECT AVG(Tweets.Number_Hashtag)  FROM Tweets where Tweets.users_id= " + user.getId() + " ;";
				myruslet = stmt.executeQuery(sql2);

				while (myruslet.next())
					user.setAvg_number_of_Hashtag(myruslet.getDouble(1));

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}

			return user;
		}

		protected void delete(String table, Long id) {

			try {
				System.out.println("Delete  " + id);
				PreparedStatement p;
				p = (PreparedStatement) conn.prepareStatement("DELETE FROM " + table + "  WHERE id = ? ;");
				// p.setString(1, table);
				p.setLong(1, id);
				p.execute();
				System.out.println("Done: " + table);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}

		}
		//
		// protected void delete_Trning_SetـTweets(String table,Long id) {
		//
		// try {
		// System.out.println("Delete "+id);
		// PreparedStatement p;
		// p= (PreparedStatement) conn.prepareStatement("DELETE FROM "+table+" WHERE
		// Tweet_id = ? ;");
		// //p.setString(1, table);
		// p.setLong(1, id);
		// p.execute();
		// System.out.println("Done: "+table);
		// }catch (Exception e) {
		// // TODO: handle exception
		// System.out.println(e.getMessage());
		// }
		//
		//
		// }
		// protected void delete_Tweets_has_Hashtag(String table,Long id) {
		//
		// try {
		// System.out.println("Delete "+id);
		// PreparedStatement p;
		// p= (PreparedStatement) conn.prepareStatement("DELETE FROM "+table+" WHERE
		// Tweets_id = ? ;");
		// //p.setString(1, table);
		// p.setLong(1, id);
		// p.execute();
		// System.out.println("Done: "+table);
		// }catch (Exception e) {
		// // TODO: handle exception
		// System.out.println(e.getMessage());
		// }
		//
		//
		// }

		protected void do_ubdate() {

			ResultSet ids;

			try {
				Statement stmt = conn.createStatement();
				ids = stmt.executeQuery("select  TrningSetRealBot.id  from TrningSetRealBot;");

				while (ids.next())
					Update_Creat_At(ids.getLong(1));

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}

		}

		// Update Tweets Creat_At from time in second to readable
		protected void Update_Creat_At(long user_id) throws Exception {
			PreparedStatement p;
			String sql_tweet = "select  Tweets.id ,datetime(substr(created_at,1,10), 'unixepoch', 'localtime')  from Tweets where Tweets.users_id= ? ;";
			p = (PreparedStatement) conn.prepareStatement(sql_tweet);
			p.setLong(1, user_id);
			ResultSet tweet = p.executeQuery();
			String ubdate_Hure = "", char_Hour;
			int hour = 0;
			while (tweet.next()) {
				System.out.println("the date is " + tweet.getString(2));
				if (tweet.getString(2) == null || tweet.getString(2).length() <= 10) {
					System.out.println("the data is Null!!!!!!!!!");
					continue;
				}
				char_Hour = tweet.getString(2).charAt(11) + "" + tweet.getString(2).charAt(12);
				hour = Integer.parseInt(char_Hour);
				String time = "";
				if (hour >= 0 && hour <= 6) {
					ubdate_Hure = "UPDATE Tweets SET hour_of_Tweet = ? " + " WHERE Tweets.id= " + tweet.getLong(1) + ";";
					time = "0-6";
				} else if (hour >= 7 && hour <= 12) {
					ubdate_Hure = "UPDATE Tweets SET hour_of_Tweet = ? " + " WHERE Tweets.id= " + tweet.getLong(1) + ";";
					time = "7-12";
				}

				else if (hour >= 13 && hour <= 18) {
					ubdate_Hure = "UPDATE Tweets SET hour_of_Tweet = ? " + " WHERE Tweets.id= " + tweet.getLong(1) + ";";
					time = "13-18";
				} else if (hour >= 19 && hour <= 23) {
					ubdate_Hure = "UPDATE Tweets SET hour_of_Tweet = ? " + " WHERE Tweets.id= " + tweet.getLong(1) + ";";
					time = "19-23";
				}
				p = (PreparedStatement) conn.prepareStatement(ubdate_Hure);
				p.setString(1, time);
				p.execute();
			}

			String sql1 = "UPDATE  Tweets SET Day_of_Tweet = substr((datetime(substr(created_at,1,10), 'unixepoch', 'localtime')),1,10)  WHERE Tweets.users_id= ? ;";
			p = (PreparedStatement) conn.prepareStatement(sql1);
			p.setLong(1, user_id);
			p.execute();
		}
				
			
		// Update
		protected void tmp() {
			String sql = "select  TrningSetRealBot. from TrningSetRealBot";
			ResultSet myruslet2, myruslet;
			try {
				Statement stmt = conn.createStatement();
				myruslet = stmt.executeQuery(sql); // myruslet for the ids and myruslet2 for the count(*)
				List<Long> arr = new ArrayList<Long>(52);
				
				while (myruslet.next())
					arr.add(myruslet.getLong(1));
				
				int i = 0;
				for (Long item : arr) {
					System.out.println(++i);
					String sql2 = "select Tweets.source,count(*) as count from Tweets  where Tweets.users_id= "+item+" group by Tweets.source order by count desc limit 1";
					myruslet2 = stmt.executeQuery(sql2);
			    	stmt.execute("update TrningSetRealBot set source= \"" +get_the_source( myruslet2.getString(1)) + "\" where TrningSetRealBot.id=" + item + ";");

				}

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			
		}

	}