package com.thi.demo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thi.demo.dto.ProductDTO;
import com.thi.demo.service.ProductService;
import com.thi.demo.service.ProductServiceImpl;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService productService;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("dbURL");
		String jdbcUsername = getServletContext().getInitParameter("dbUsername");
		String jdbcPassword = getServletContext().getInitParameter("dbPassword");
		
		this.productService = new ProductServiceImpl(jdbcURL, jdbcUsername, jdbcPassword);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action") != null ? request.getParameter("action") : "none";
		
		try {
			switch (action) {
			case "new":
				RequestDispatcher dispatcher = request.getRequestDispatcher("newProductForm.jsp");
				dispatcher.forward(request, response);
				break;
				
			case "insert":
				String name = request.getParameter("name");
				String description = request.getParameter("description");
				float price = Float.parseFloat(request.getParameter("price"));
				
				ProductDTO product = new ProductDTO(name, description, price);
				this.productService.addNewProduct(product);
				response.sendRedirect("product");
					
				break;
				
			case "delete":
				this.deleteProduct(request, response);
				break;
			
			case "edit":
				this.showEditForm(request, response);
				break;
				
			case "update":
				this.updateProduct(request, response);
				break;
				
			default:
				this.getListProduct(request, response);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void getListProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<ProductDTO> listProd = this.productService.getAllProducts();
		request.setAttribute("listProducts", listProd);
		RequestDispatcher dispatcher = request.getRequestDispatcher("productList.jsp");
		dispatcher.forward(request, response);
	}
	
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ProductDTO product = this.productService.getProductById(id);
		this.productService.deleteProduct(product);
		
		response.sendRedirect("product");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ProductDTO product = this.productService.getProductById(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("newProductForm.jsp");
		request.setAttribute("product", product);
		dispatcher.forward(request, response);
	}
	
	private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		float price = Float.parseFloat(request.getParameter("price"));
		
		ProductDTO product = new ProductDTO(id, name, description, price);
		this.productService.updateProduct(product);

		response.sendRedirect("product");
	}
	
	

}
