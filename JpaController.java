package controllere;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import test.Buyer;
import test.IModel;
import test.Tovar;

public class JpaController implements IController {
	EntityManagerFactory emf =
			Persistence.createEntityManagerFactory("lab4");
		public Object get(int id, String classname) {
			EntityManager em = emf.createEntityManager();
			Object obj = null;
			try {
				Class clazz = Class.forName("lab4." + classname);
				obj = em.find(clazz, id);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				em.close();
			}
			return obj;
		}
		@Override
		public TableModel doQuery1() {
			EntityManager em = emf.createEntityManager();
			Query q = em.createQuery("select t from Tovar t where t.cost < ?1");
			List<Tovar> list = q.getResultList();
			String[][] arr = new String[list.size()][5];
			int i = 0;
			for (Tovar t : list) {
				arr[i][0] = String.valueOf(t.getIdTovara());
				arr[i][1] = t.getNameTovara();
				arr[i][2] = String.valueOf(t.getCost());
				arr[i++][3] = String.valueOf(t.getCount());
			}
			DefaultTableModel model = new DefaultTableModel(arr,
		 new String[] { "idTovara", "nameTovara", "count", "cost"});
			return model;

		}
		@Override
		public void delete(int id, String className) {
			EntityManager em = emf.createEntityManager();
			try {
				Class clazz = Class.forName("lab4." + className);
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
		public void add(Object obj) {
			Class clazz = obj.getClass();
			if (exist((IModel) obj))
				return;
			EntityManagerFactory emf = 
						Persistence.createEntityManagerFactory("lab4");
			EntityManager em = emf.createEntityManager();
			try {
				em.getTransaction().begin();
				em.persist(obj);
				em.getTransaction().commit();
			} catch (Exception e) {
				em.getTransaction().rollback();
				e.printStackTrace();
			} finally {
				em.close();
			}
 }

//		@Override
//		public void add(Object object) {
//			Class clazz = object.getClass();
//			if(exist((IModel) object)) return;
//			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Z");
//			EntityManager em = emf.createEntityManager();
//			Query q = null;
//			if (object instanceof Покупатель) {
//				q = em.createNativeQuery(
//		"insert into Покупатель(id_pokupki,fio,category,skidka,fiz_ur) values(?1,?2,?3,?4,?5)");
//				q.setParameter(1, ((Покупатель) object).getIdPokupki());
//				q.setParameter(2, ((Покупатель) object).getFio());
//				q.setParameter(3, ((Покупатель) object).getCategory());
//				q.setParameter(4, ((Покупатель) object).getSkidka());
//				q.setParameter(5, ((Покупатель) object).getFizUr());
////			} else if (object instanceof Корзина) {
////				q = em.createNativeQuery(
////		"insert into SUBJECTS(NAME,TEACHER) values(?1,?2,?3,?4,?5,?6,?7)");
////				q.setParameter(1, ((Коризна)object).getIdZakaza());
////				q.setParameter(2, ((Коризна)object).getIdPokupatel());
////				q.setParameter(3, ((Коризна)object).getName());
////				q.setParameter(4, ((Коризна)object).getTeacher());
////				q.setParameter(5, ((Коризна)object).getName());
////				q.setParameter(6, ((Коризна)object).getTeacher());
////				q.setParameter(7, ((Коризна)object).getTeacher());
////			} else if (object instanceof Поставка) {
////				q = em.createNativeQuery(
////		"insert into MARKS(IDSTUDENT, IDSUBJECT, MARK,DATE)"
////		+ "values(?1,?2,?3,?4,?5,?6,?7)");
////				q.setParameter(1, ((Mark)object).getStudent().getId());
////				q.setParameter(2, ((Mark)object).getSubject().getId());
////				q.setParameter(3, ((Mark)object).getMark());
////				q.setParameter(4, ((Mark)object).getDate());
////				q.setParameter(4, ((Mark)object).getDate());
////				q.setParameter(4, ((Mark)object).getDate());
////				q.setParameter(4, ((Mark)object).getDate());
////			} else if (object instanceof Товар) {
////				q = em.createNativeQuery(
////		"insert into SUBJECTS(NAME,TEACHER) values(?1,?2)");
////				q.setParameter(1, ((Subject)object).getName());
////				q.setParameter(2, ((Subject)object).getTeacher());
////			}
//			if(q==null)return;
//			em.getTransaction().begin();
//				q.executeUpdate();
//			em.getTransaction().commit();
//			}
//		}
		@Override
		public void edit(int id, Object object) {
			Class clazz = object.getClass();
			EntityManager em = emf.createEntityManager();
			try {
				em.getTransaction().begin();
				Object updObj = em.find(clazz, id);
				((IModel) updObj).updateWith(object);
				em.getTransaction().commit();
			} catch (Exception e) {
				em.getTransaction().rollback();
				e.printStackTrace();
			} finally {
				em.close();
			}

		}
		@Override
		public TableModel getModel(String className) {
			try {
				Class clazz = Class.forName("lab4." + className);
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
		public List getObjectList(Class clazz) {
			EntityManager em = emf.createEntityManager();
			// Формуємо ім'я іменованого запиту для заданого класу
			String queryName = clazz.getSimpleName() + "." + "findAll";
			// Отримуємо перелік записів таблиці заданого класу
			List list = em.createNamedQuery(queryName).getResultList();
			em.close();
			return list;

		}
		public boolean exist(IModel obj) {
			Class clazz = obj.getClass();
			// Отримуємо перелік записів таблиці заданого класу
			List list = getObjectList(clazz);
			if (list != null && list.size() != 0)
				for (Object current : list)
					if (current.equals(obj))
						return true;
			return false;

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
