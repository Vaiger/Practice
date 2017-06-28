package webview;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.table.TableModel;

import org.postgresql.core.Provider;

import controllere.JpaController;
import test.Buyer;
import test.IModel;
import test.Order;
import test.Tovar;

/**
 * Servlet implementation class ExecuteOperation
 */
@WebServlet("/ExecuteOperation")
public class ExecuteOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteOperation() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private IModel findById(int id, JpaController controller, String className) {
    	try {
    		IModel obj = null;
    		Class clazz = Class.forName("model."+className);
    		for (Object x : controller.getObjectList(clazz)) {
    			obj = (IModel) x;
    			if (obj.getId() == id)
    				return obj;
    		}
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	return null;
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String className = SelectTable.className;
		int operation = SelectOperation.operation;
		IModel obj = null;
		try {
			obj = (IModel) Class.forName("test."+className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (operation == 4) {
			String id = request.getParameter("Id");
			obj.setObjectId(Integer.parseInt(id));
		} else if (obj instanceof Buyer) {
			switch (operation) {
			case 2:
				int id = Integer.parseInt(request.getParameter("idPokupatel"));
				obj.setObjectId(id);
			case 1:
				String fio = request.getParameter("fio");
				((Buyer) obj).setFio(fio);
				String fizUr = request.getParameter("fiz|ur");
				((Buyer) obj).setFizUr(fizUr);
				float skidka = Float.parseFloat(request.getParameter("skidka"));
				((Buyer) obj).setSkidka(skidka);
			}
		} else if (obj instanceof Order) {
			switch (operation) {
			case 2:
				int id = Integer.parseInt(request.getParameter("idPokupatel"));
				obj.setObjectId(id);
			case 1:
				int idTovara = Integer.parseInt(request.getParameter("idTovara"));
				((Order) obj).setIdTovara(idTovara);
				int idZakaza = Integer.parseInt(request.getParameter("idZakaz"));
				((Order) obj).setIdZakaza(idZakaza);
				boolean status = Boolean.parseBoolean(request.getParameter("status"));
				((Order) obj).setStatus(status);
				int sumZakaza = Integer.parseInt(request.getParameter("sum_zakaza"));
				((Order) obj).setSumZakaza(sumZakaza);
				int count2 = Integer.parseInt(request.getParameter("count"));
				((Order) obj).setCount2(count2);
			}
		} else if (obj instanceof Tovar) {
			int cost = Integer.parseInt(request.getParameter("cost"));
			((Tovar) obj).setCost(cost);
			int idTovara = Integer.parseInt(request.getParameter("idTovara"));
			((Tovar) obj).setIdTovara(idTovara);
			String nameTovara = request.getParameter("name_tovar");
			((Tovar) obj).setNameTovara(nameTovara);
			int count = Integer.parseInt(request.getParameter("count"));
			((Tovar) obj).setCount(count);
			if (operation == 2) {
				//Для оновлення потрібен Id
				int id = Integer.parseInt(request.getParameter("vehicleId"));
				obj.setObjectId(id);
			} else if (operation == 1) {
				//Для створення нового запису потрібні студент та предмет
//				int idTovara = Integer.parseInt(request.getParameter("studId"));
//				Tovar stud = (Tovar)(findById(Tovar.class, idTovara));
//				((Buyer) obj).setIdPokupatel(idPokupatel);

			}

		}
		SelectTable.getController().operateObject(obj, SelectOperation.operation);
		request.setAttribute("test", SelectTable.getTableModel());
		request.setAttribute("className", SelectTable.className);
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
