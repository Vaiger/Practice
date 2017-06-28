package webview;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SelectOperation
 */
@WebServlet("/SelectOperation")
public class SelectOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static int operation;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectOperation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		operation = Integer.parseInt(request.getParameter("operation"));
		String tableClassName = SelectTable.className;
		String formName = "";
		if( operation == 4) formName = "getId.html";
		else if(tableClassName.equals("Tovar")){
			if(operation==1) formName = "addTovar.html";
			else if( operation==2) formName = "editTovar.html";
		}
		else if(tableClassName.equals("Buyer")){
			if(operation==1) formName = "addBuyer.html";
			else if( operation==2) formName = "editBuyer.html";
		}
		else if(tableClassName.equals("Order")){
			if(operation==1) formName = "addOrder.html";
			else if( operation==2) formName = "editOrder.html";
		}
		request.getRequestDispatcher(formName).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
