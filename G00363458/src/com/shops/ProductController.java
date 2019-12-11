package com.shops;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ProductController {
	
	private DAO dao;
	private ArrayList<Product>products; 
	
	public ProductController() {//Managed bean must have 1 no args constructor
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		//this.setProducts(new ArrayList<Product>());
	}

	public ArrayList<Product> getProducts() {
		return products;
	}
	
	public String loadProducts() {
		//System.out.println("In loadProducts()");
		
		try {
			products = dao.loadProducts();
			//return "products";
		} catch (SQLException e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to MySQL Database");
            FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}
		return null;
	}
	
	/*public String loadStoreProducts(int storeId) {
		//System.out.println("In loadProducts()");
		
		try {
			products = dao.loadStoreProducts(storeId);
			return "storeProducts";
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}//loadStoreProducts*/
	
	
	/*public ArrayList<Product> getStoreProducts1(int storeId) {
		return products;
	}*/
	
	public void delete(int pid) {
		System.out.println(pid);
		try {
			dao.delete(pid);
		}catch (SQLException e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to MySQL Database");
            FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}
	
	}
}
