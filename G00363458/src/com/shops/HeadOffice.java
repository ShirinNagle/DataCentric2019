package com.shops;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class HeadOffice {
	
	///class variables
	private String _id;
	private String location;
	//HeadOffice ho = new HeadOffice();
	
	public HeadOffice() {
		super();
	}
	
	//Getters and setters
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	

}//end of class
