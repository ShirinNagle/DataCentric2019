package com.shops;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class StoreProductController {
	
	DAO dao;
	ArrayList<StoreProduct> storeProducts;
	
	public StoreProductController() {//Managed bean must have 1 no args constructor
		 super();
		 try {
			dao = new DAO();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public ArrayList<StoreProduct> getStoreProducts() {
		return storeProducts;
	}
	
	public String loadStoreProducts(int sid) {
		//System.out.println("In loadStoreProducts()");
		try {
			storeProducts = dao.loadStoreProducts(sid);
			return "storeProducts";
		} catch (SQLException e) {
			
			FacesMessage message = new FacesMessage("Error: Cannot connect to MySQL Database");
            FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}
		
		return null;
	}
		
}//class
