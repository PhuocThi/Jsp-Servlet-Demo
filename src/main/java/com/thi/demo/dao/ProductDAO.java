package com.thi.demo.dao;

import java.sql.SQLException;
import java.util.List;

import com.thi.demo.dto.ProductDTO;

public interface ProductDAO {
	List<ProductDTO> getAllProducts() throws SQLException;
	boolean addNewProduct(ProductDTO newProduct) throws SQLException;
	boolean updateProduct(ProductDTO product) throws SQLException;
	boolean deleteProduct(ProductDTO product) throws SQLException;
	ProductDTO getProductById(int id) throws SQLException;
}
