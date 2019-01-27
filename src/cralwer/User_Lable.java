package cralwer;

public class User_Lable {
	
	private long id ;	
	private int followers_count ,friends_count, verified,geo_enabled,after_Midnight,morning,after_Noon,evening,Max_of_Tweets_in_DAY ;
	private double avg_number_of_Hashtag,avg_URL;
	private String Lable,source;
	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public String getLable() {
		return Lable;
	}


	public void setLable(String lable) {
		Lable = lable;
	}


	public int getMax_of_Tweets_in_DAY() {
		return Max_of_Tweets_in_DAY;
	}


	public void setMax_of_Tweets_in_DAY(int max_of_Tweets_in_DAY) {
		Max_of_Tweets_in_DAY = max_of_Tweets_in_DAY;
	}


	public long getId() {
		return id;
	}
	
	
	public void setId(long id) {
		this.id = id;
	}


	public void setFollowers_count(int followers_count) {
		this.followers_count = followers_count;
	}


	public void setFriends_count(int friends_count) {
		this.friends_count = friends_count;
	}


	public void setVerified(int verified) {
		this.verified = verified;
	}


	public void setGeo_enabled(int geo_enabled) {
		this.geo_enabled = geo_enabled;
	}


	public void setAfter_Midnight(int after_Midnight) {
		this.after_Midnight = after_Midnight;
	}


	public void setMorning(int morning) {
		this.morning = morning;
	}


	public void setAfter_Noon(int after_Noon) {
		this.after_Noon = after_Noon;
	}


	public void setEvening(int evening) {
		this.evening = evening;
	}




	public double getAvg_number_of_Hashtag() {
		return avg_number_of_Hashtag;
	}


	public void setAvg_number_of_Hashtag(double avg_number_of_Hashtag) {
		this.avg_number_of_Hashtag = avg_number_of_Hashtag;
	}


	public double getAvg_URL() {
		return avg_URL;
	}


	public void setAvg_URL(double avg_URL) {
		this.avg_URL = avg_URL;
	}


	public int getFollowers_count() {
		return followers_count;
	}
	public int getFriends_count() {
		return friends_count;
	}
	public int getVerified() {
		return verified;
	}
	public int getGeo_enabled() {
		return geo_enabled;
	}
	public int getAfter_Midnight() {
		return after_Midnight;
	}
	public int getMorning() {
		return morning;
	}
	public int getAfter_Noon() {
		return after_Noon;
	}
	public int getEvening() {
		return evening;
	}




}