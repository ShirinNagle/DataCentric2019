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
	MongoDAO mDAO;
	ArrayList<HeadOffice>headOffices;
	//String headOffice;
	
	public HeadOfficeController() {
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
	
	/*public String addHeadOffice(HeadOffice hOffice) {
		try {
		mDAO.addHeadOffice(hOffice);
		
		} catch(Exception e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to Mongo Database");
            FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}
		return null;
	}*/

}//end of class
