package com.shops;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped
public class StoreController {
	
	private DAO dao;
	private ArrayList<Store>stores; 
	
	public StoreController() {//Managed bean must have 1 no args constructor
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		this.stores = new ArrayList<Store>();//may not need
	}

	public ArrayList<Store> getStores() {
		return stores;
	}
	
	public void  setStores(ArrayList<Store> stores) {
		this.stores = stores;
	}

	public String loadStores() {
		//System.out.println("In loadStores()");
		
		try {
			stores = dao.loadStores();
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to MySQL Database");
			FacesContext.getCurrentInstance().addMessage(null, message);
			
			e.printStackTrace();
		}
		return null;
	}
	
	public String addStore(Store store) {
		System.out.println(store);
		System.out.println(store.getStoreName() + " " + store.getFounded());
		
		try {
			dao.addStore(store);
			
		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = 
					new FacesMessage("Error: Store" + store.getStoreName() + " already exists");
					FacesContext.getCurrentInstance().addMessage(null, message);
					e.printStackTrace();
					return null;
			
		
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to MySQL Database");
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
			
			
			return null;
		}
		
		
		return "list_stores.xhtml";
	}
	
	/*public void deleteStore(int sid) {
		try {
			dao.deleteStore(sid);
		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage("Error: Store: " + sid + "has not been deleted from MySQL DB, it contains products");//need to change to store name
            FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to MySQL Database");
            FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}
				
	}//deleteStore*/
	
	//Try passing Store as variable??
	  public void deleteStore(Store s) {
		try {
			dao.deleteStore(s);
		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage("Error: Store: " + s.getStoreName() + " has not been deleted from MySQL DB, it contains products");//need to change to store name
            FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to MySQL Database");
            FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}
			
	}

}//end of class
