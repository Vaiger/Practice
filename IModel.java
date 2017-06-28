package test;

public interface IModel {
	public String[] getTableHeaders();
	public Object[] getTableRowData();
	int getId();
	void updateWith(Object mask);
		public void setObjectId(int id);
	public int getObjectId();
}
