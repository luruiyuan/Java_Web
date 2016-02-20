package example;
import java.sql.*;

public class DatabaseController {
	private String query;
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	
	public DatabaseController(String dbName) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/tirms","root","SonOfSparda945");
		st = conn.createStatement();
		
	}
	
	public DatabaseController() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated constructor stub
		Class.forName("com.mysql.jdbc.Driver");
		query = "";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/tirms","root","SonOfSparda945");
		st = conn.createStatement();
		rs = st.getResultSet();
	}

	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public void appendQuery(String query) {
		this.query += query;
	}
	
	public DatabaseController where(Employee e) {
		this.setQuery("SELECT * FROM employee WHERE");
		int count = 0;
		
		if(e.getId() != -1) {
			this.appendQuery(" id = "+e.getId());
			count++;
		}
		
		if(e.getName() != "") {
			if(count != 0) this.appendQuery(" AND"); 
			this.appendQuery(" name = "+e.getName());
			count++;
		}
		
		if(e.getPassword() != "") {
			if(count != 0) this.appendQuery(" AND"); 
			this.appendQuery(" password = "+e.getPassword());
			count++;
		}
		
		this.appendQuery(";");
		
		return this;
	}
	
	public DatabaseController where(Transaction t) {
		this.setQuery("SELECT * FROM transaction WHERE");
		if(t.getName()!=null)this.appendQuery(" AND name = "+t.getName());
		if(t.getSerialNum()!=null)this.appendQuery(" AND serialNum = "+t.getSerialNum());
		return this;
	}
	
	public Employee[] select(Employee e1) throws SQLException {
		rs = st.executeQuery(query);
		rs.last();
		int num=rs.getRow(),i=0;
		rs.beforeFirst();
//		rs.getString(1);
		Employee[] e = new Employee[num];
		while(rs.next()) {
			e[i].setName(rs.getString("name"));
			e[i].setId(rs.getInt("Id"));
			e[i].setPassword(rs.getString("password"));
		}
		return e;
	}
	
	public Transaction[] select(Transaction t) throws SQLException{
		rs = st.executeQuery(query);
		rs.last();
		int num=rs.getRow(),i=0;
		rs.beforeFirst();
		Transaction[] tran=new Transaction[num];
		while(rs.next()){
			tran[i].setSerialNum(((ResultSet) st).getString(1));
			tran[i].setWorkID(((ResultSet) st).getString(2));
			tran[i].setStockNum(((ResultSet) st).getString(3));
			tran[i].setStockType(((ResultSet) st).getString(4));
			tran[i].setCusState(((ResultSet) st).getInt(5));
			tran[i].setStockAmount(((ResultSet) st).getInt(6));
			tran[i].setUnitPrice(((ResultSet) st).getDouble(7));
			tran[i].setTotalAmount();
			tran[i].setInterestRate(((ResultSet) st).getDouble(9));
			tran[i].setTranState(((ResultSet) st).getString(10));
			tran[i].setPayMethod(((ResultSet) st).getString(11));
			tran[i].setName(((ResultSet) st).getString(12));
			tran[i].setIdCard(((ResultSet) st).getString(13));
			tran[i].setPhone(((ResultSet) st).getString(14));
			tran[i].setTranTime(((ResultSet) st).getTimestamp(15));
			i++;
		}
		return tran;
	}
	
	public void close(){
		try {
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
