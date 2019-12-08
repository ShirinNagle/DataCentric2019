package com.shops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;






public class DAO {

	private DataSource mysqlDS;
	
	private Connection myConn = null;
	private Statement myStmt = null;
	private ResultSet myRs = null;


	
	/* ======================================================================================================
	 * Constructor
	 * ====================================================================================================== */
	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/shops";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
	/* ======================================================================================================
	 * Store Methods
	 * ====================================================================================================== */
	//method to retrieve stores and store them in an array list called stores
	public ArrayList<Store> loadStores() throws SQLException {
					
		myConn = mysqlDS.getConnection();

		String sql = "select * from store";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);
		
		ArrayList<Store> stores = new ArrayList<Store>();
		
		
		// process result set
				while (myRs.next()) {
					Store s = new Store();
					
					s.setStoreId(myRs.getInt("id"));
				
					s.setStoreName(myRs.getString("name"));
					
					s.setFounded(myRs.getString("founded"));
					
					stores.add(s);
				}//while
				
				myConn.close();
				
			return stores;
	}//loadStores
	
	
	public void addStore(Store s) throws Exception {
		PreparedStatement myStmt = null;
		
		
		myConn = mysqlDS.getConnection();
		
		String sql = "insert into store values (?, ?,?);";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, s.getStoreId());
		myStmt.setString(2, s.getStoreName());
		myStmt.setString(3, s.getFounded());
		myStmt.executeUpdate();
		
		myConn.close();
		
		}//addStore
	
		public void deleteStore(int s) throws SQLException {
		PreparedStatement myStmt = null;
	
 		myConn = mysqlDS.getConnection();
 		String sql = "delete from store where id = ?;";
 		
 		myStmt = myConn.prepareStatement(sql);
 		myStmt.setInt(1,s);
 		myStmt.executeUpdate();	
 		
 		myConn.close();
		}//deleteStore

	
	
	
		/* ======================================================================================================
		 * Product Methods
		 * ====================================================================================================== */
	//method to retrieve the list of products and add them to an array list called products
	public ArrayList<Product> loadProducts() throws SQLException {
		
		myConn = mysqlDS.getConnection();

		String sql = "select * from product";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);
		
		ArrayList<Product> products = new ArrayList<Product>();
		// process result set
		while (myRs.next()) {
			Product p = new Product();
			
			p.setStoreId(myRs.getInt("sid"));
			
			p.setProdId(myRs.getInt("pid"));
		
			p.setProdName(myRs.getString("prodName"));
			
			p.setPrice(myRs.getFloat("price"));
			
			products.add(p);
			//System.out.println(myRs.getString("PRODID"));
		}
		return products;	
	}//load Products
	
	public void delete(int pid) throws SQLException {
		PreparedStatement myStmt = null;
	
 		myConn = mysqlDS.getConnection();
 		String sql = "delete from product where pid = ?";
 		
 		myStmt = myConn.prepareStatement(sql);
 		myStmt.setInt(1, pid);
 		myStmt.execute();		
	}//delete Products
	

	/* ======================================================================================================
	 * StoreProduct Methods
	 * ====================================================================================================== */
		public ArrayList<StoreProduct> loadStoreProducts(int storeId) throws SQLException{
			//PreparedStatement myStmt = null;
			Statement myStmt = null;
			myConn = mysqlDS.getConnection();
			//I know the problem is here - store products are not loading in. If I hard code a 1 in the sql string it works but isn't picking up the int variable.
			String sql = "select p.pid, p.sid, p.prodName, p.price, s.founded, s.name from product p inner join store s on p.sid = s.id where s.id like " + storeId;;
			//String sql = "select p.pid, p.sid, p.prodName, p.price, s.founded, s.name from product p inner join store s on p.sid = s.id where s.id = " +storeId;;
			
			myStmt = myConn.prepareStatement(sql);

			
			myRs = myStmt.executeQuery(sql);
			ArrayList<StoreProduct> storeProducts = new ArrayList<StoreProduct>();	
			
			// process result set
			while (myRs.next()) {
				StoreProduct sp = new StoreProduct();
				
				sp.setStoreId(myRs.getInt("sid"));
				
				sp.setProdId(myRs.getInt("pid"));
			
				sp.setProdName(myRs.getString("prodName"));
				
				sp.setPrice(myRs.getFloat("price"));
				
				sp.setFounded(myRs.getString("founded"));
				
				sp.setStoreName(myRs.getString("name"));
				
				storeProducts.add(sp);
				System.out.print(sp);

			
		}
			
		return storeProducts;
	}
		/* ======================================================================================================
		 * HeadOffice Methods
		 * ====================================================================================================== */
		/*public ArrayList<HeadOffice> getHeadOffices(int sid) throws SQLException {
			Statement myStmt = null;
			myConn = mysqlDS.getConnection();
			
			String sql ="select * from store where sid= ?;";
			myRs = myStmt.executeQuery(sql);
			return null;
			
		}*/
		

		
}//class
