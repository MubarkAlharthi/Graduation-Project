package cralwer;
public class Tweet_Lable {

	
	private long user_id, id;
	private String text ,source,Lable;
	
	
	Tweet_Lable(long id,long user_id,String Text,String Lable){
		this.id=id;
		this.user_id=user_id;
		this.text=Text;
		this.Lable=Lable;	
	}
	
	Tweet_Lable(long id,long user_id,String Text){
		this.id=id;
		this.user_id=user_id;
		this.text=Text;
	}

	public long getUser_id() {
		return user_id;
	}



	public long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public String getLable() {
		return Lable;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setLable(String lable) {
		Lable = lable;
	}


	
}