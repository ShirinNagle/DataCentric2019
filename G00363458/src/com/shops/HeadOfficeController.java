package com.shops;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class HeadOfficeController {
	
	//DAO dao;
	private MongoDAO mDAO;
	private DAO dao;// may need to remove
	private Store s;//may need to remove
	private ArrayList<HeadOffice>headOffices;
	//String headOffice;
	
	public HeadOfficeController(){
		super();
		
		try {
			mDAO = new MongoDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//public ArrayList<HeadOffice> loadHeadOffices() {
	public String loadHeadOffices() {
		//System.out.println("In loadProducts()");
		
		try {
			headOffices = mDAO.loadHeadOffices();
		} catch (Exception e) {
			
			FacesMessage message = new FacesMessage("Error: Cannot connect to Mongo Database");
            FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}
		//return headOffices;
		return null;
	}
	
	public ArrayList<HeadOffice> getHeadOffices() {
		return headOffices;
		
	}
	
	//overloaded method to see if Id exists
	public ArrayList<HeadOffice> getHeadOffices(String sid) {
	 
		try {
			dao.getHeadOffices();
			
			/*store id is stored as a string in MongoDB but stored as an int in
			 * MySQL Database need to compare correctly*/ 
			int idExists = Integer.parseInt(sid);
			 
			 if(idExists == s.getStoreId())
			 {
			 } 
			 
			 
		} catch (Exception e) {
			
			e.printStackTrace();
		
		}
		
		
		return headOffices;
		//more to go here
		
	}
	
	//Method to check MySQL DB to see if store id already exists
	/*public ArrayList<HeadOffice> getHeadOffices(int sid) {
		
		try {
			dao.getHeadOffices(sid);
		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = 
					new FacesMessage("Error: ID " + sid + " does not exist");
					FacesContext.getCurrentInstance().addMessage(null, message);
					e.printStackTrace();
			
		} catch (SQLException e) {
			
			FacesMessage message = new FacesMessage("Error: Cannot connect to MySQL Database");
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}
		return headOffices;
	}*/
	
	public String addHeadOffice(HeadOffice hOffice) {
		try {
		mDAO.addHeadOffice(hOffice);
		
		} catch(Exception e) {
			FacesMessage message = new FacesMessage("Error Store ID: " + hOffice.get_id() +" already exists");
            FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}
		return null;
	}

}//end of class
