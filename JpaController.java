package controllere;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import test.IModel;
import test.Tovar;



public class JpaController implements IController {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

	public List getObjectList(Class clazz) {
		EntityManager em = emf.createEntityManager();
		// Формуємо ім'я іменованого запиту для заданого класу
		String queryName = clazz.getSimpleName() + "." + "findAll";
		// Отримуємо перелік записів таблиці заданого класу
		List list = em.createNamedQuery(queryName).getResultList();
		em.close();
		return list;
	}
	
	public DefaultTableModel getModel(String className) {
		try {
			Class clazz = Class.forName("test." + className);
			// Отримуємо заголовок таблиці
			IModel obj = (IModel) clazz.newInstance();
			String[] header = obj.getTableHeaders();
			// Отримуємо список об'єктів
			List list = getObjectList(clazz);
			if (list == null || list.size() == 0)
				return new DefaultTableModel(null, header);
			// Створюємо масив потрібного розміру
			Object[][] array = new Object[list.size()][header.length];
			// Наповнюємо масив
			int i = 0;
			for (Object s : list)
				array[i++] = ((IModel) s).getTableRowData();
			// Повертаємо модель
			return new DefaultTableModel(array, header);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean exist(IModel obj) {
		Class clazz = obj.getClass();
		// Отримуємо перелік записів таблиці заданого класу
		List list = getObjectList(clazz);
		if (list != null&&list.size() != 0)
			for (Object current : list)
				if (current.equals(obj))
					return true;
		return false;
	}



	@Override
	public void add(Object obj) {
		Class clazz = obj.getClass();
		if(exist((IModel) obj)) return;
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
	}

	@Override
	public void edit(int id, Object obj) {
		Class clazz = obj.getClass();
		if(exist((IModel) obj)) return;
		EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			IModel model = (IModel) em.find(clazz, id);
			model.updateWith(obj); 
			em.getTransaction().commit();
	}

	@Override
	public void delete(int id, String className) {
		EntityManager em = emf.createEntityManager();
		try {
			Class clazz = Class.forName("test." + className);
			Object delObj = em.find(clazz, id);
			em.getTransaction().begin();
			em.remove(delObj);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {		
			em.close();
		}

	}

	@Override
	public TableModel doQuery1() {
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select t from Tovar t");
		List<Tovar> list = q.getResultList();
		String[][] arr = new String[list.size()][4];
		int i = 0;
		for (Tovar t : list) {
			arr[i][0] = String.valueOf(t.getIdTovara());
			arr[i][1] = t.getNameTovara();
			arr[i][2] = String.valueOf(t.getCount());
			arr[i++][3] = String.valueOf(t.getCost());
		}
		DefaultTableModel model = new DefaultTableModel(arr,
	 new String[] { "Buyer", "Order", "Tovar"});
		return model;

	}
	
public void operateObject(IModel obj, int operation) {
		if(operation==1){
			add(obj);
		}
		else if(operation==2){
			edit(obj.getId(),obj);
		}
		else if(operation==4){
			delete(obj.getId(),obj.getClass().getSimpleName());
		}
	}

}
