package webview;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.table.TableModel;

import controllere.JpaController;

/**
 * Servlet implementation class SelectTable
 */
@WebServlet("/SelectTable")
public class SelectTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static JpaController controller ;
	public static String className;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectTable() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public static JpaController getController() {
 		if(controller==null)
 			controller = new JpaController();
 		return controller;
 	}
 	
 	public static TableModel getTableModel() {
 		return getController().getModel(className);
 	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {String tableName = request.getParameter("tableName");
	HttpSession session = request.getSession();
	JpaController controller = (JpaController) session.getAttribute("controller");
	TableModel tableModel = controller.getModel(tableName);
	session.setAttribute("tableName", tableName);
	session.setAttribute("tableModel", tableModel);
	request.getRequestDispatcher("showTable.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
