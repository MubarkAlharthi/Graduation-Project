package cralwer;
import java.util.ArrayList;

public class Userswiththirecategory {

	
	
	private long id;
	private String name,ScreenName;
	private int numberOfTweet;
public int getNumberOfTweet() {
		return numberOfTweet;
	}
	public void setNumberOfTweet(int numberOfTweet) {
		this.numberOfTweet = numberOfTweet;
	}
	//	double Label1per,Label2per,Label3per,Label4per;
	ArrayList<LabelCount> labels;
//	Userswiththirecategory(long id,String n,String sn,String l1,double p1,String l2,double p2,String l3,double p3,String l4,double p4){		
//		this.id=id;
//		name=n;
//		ScreenName=sn;
//		Label1=l1;
//		Label2=l2;
//		Label3=l3;
//		Label4=l4;
//		Label1per=p1;
//		Label2per=p2;
//		Label3per=p3;
//		Label4per=p4;		
//	}
	
	Userswiththirecategory(){
		this.labels = new ArrayList();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScreenName() {
		return ScreenName;
	}
	public void setScreenName(String screenName) {
		ScreenName = screenName;
	}
}