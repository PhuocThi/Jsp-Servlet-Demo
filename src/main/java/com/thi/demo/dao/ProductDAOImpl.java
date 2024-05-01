package com.thi.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.thi.demo.dto.ProductDTO;

public class ProductDAOImpl implements ProductDAO {
	
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	

	public ProductDAOImpl(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	protected void connect() throws SQLException{
		if(jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (Exception e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}
	
	protected void disconnect() throws SQLException{
		if(jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	@Override
	public List<ProductDTO> getAllProducts() throws SQLException {
		List<ProductDTO> listProd = new ArrayList<ProductDTO>();
		String sql = "select * from product";
		connect();
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {
			int id = resultSet.getInt("product_id");
			String name = resultSet.getString("name");
			String description = resultSet.getString("description");
			float price = resultSet.getFloat("price");
			ProductDTO product = new ProductDTO(id, name, description, price);
			listProd.add(product);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return listProd;
	}

	@Override
	public boolean addNewProduct(ProductDTO newProduct) throws SQLException {
		String sql = "insert into product(name, description, price) values (?,?,?)";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, newProduct.getName());
		statement.setString(2, newProduct.getDescription());
		statement.setFloat(3, newProduct.getPrice());
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	@Override
	public boolean updateProduct(ProductDTO product) throws SQLException {
		String sql = "update product set name = ?, description = ?, price = ? where product_id = ?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, product.getName());
		statement.setString(2, product.getDescription());
		statement.setFloat(3, product.getPrice());
		statement.setInt(4, product.getId());
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	@Override
	public boolean deleteProduct(ProductDTO product) throws SQLException {
		String sql = "delete from product where product_id = ?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, product.getId());
		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	@Override
	public ProductDTO getProductById(int id) throws SQLException {
		ProductDTO prod = null;
		String sql = "select * from product where product_id = ?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()) {
			String name = resultSet.getString("name");
			String description = resultSet.getString("description");
			float price = resultSet.getFloat("price");
			
			prod = new ProductDTO(id, name, description, price);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return prod;
	}

}
