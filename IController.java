package controllere;

import javax.swing.table.TableModel;

public interface IController {

	TableModel doQuery1();

	void delete(int id, String className);

	void add(Object object);

	void edit(int id, Object object);

	TableModel getModel(String className);


}
