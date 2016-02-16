package Test_java;

import java.sql.*;

public class dbProcess {
	//�����������
	private final static String url="jdbc:mysql://localhost:3306/trims";
	//����URLΪ   jdbc:mysql//��������ַ/���ݿ���  �������2�������ֱ��ǵ�½�û���������
	private final static String userName="root";
	private final static String password="123456";
	protected Connection conn=null;
	protected PreparedStatement stateTransaction=null,stateEmployee=null;
	//�ֱ�ָ��transaction������Ӻ�employee�������
	protected ResultSet rsTransaction=null,rsEmployee=null;
	//protected int id;//�����id�����ݿ��е�pk id �Ƿ����ã�����
	
	public dbProcess(){
		init();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("���Դ���ҵ�������ݿ�����в���");
		
	}
	
	public synchronized void setConnection(){
		try {
			conn=DriverManager.getConnection(url,userName,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Create connection failed");
			e.printStackTrace();
		}
	}
	
	public synchronized void setPreparedStatement(){
		if(conn==null)this.setConnection();
		try {
			stateTransaction=conn.prepareStatement("insert into tramsaction values(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			stateEmployee=conn.prepareStatement("insert into employee values(?,?,?)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Prepare Statement failed");
			e.printStackTrace();
		}
	}
	
	//�����ݿ����Ա����¼���߽��׼�¼
	public synchronized void setTranInsert(transaction tran){
		try {
			this.stateTransaction.setString(1,tran.getSerialNum());
			this.stateTransaction.setString(2, tran.getWorkID());
			this.stateTransaction.setString(3,tran.getStockNum());
			this.stateTransaction.setString(4,tran.getStockType());
			this.stateTransaction.setString(5, tran.getCusState());
			this.stateTransaction.setInt(6, tran.getStockAmount());
			this.stateTransaction.setDouble(7, tran.getUnitPrice());
			this.stateTransaction.setDouble(8, tran.getTotalAmount());
			this.stateTransaction.setDouble(9, tran.getInterestRate());
			this.stateTransaction.setString(10,tran.getTranState());
			this.stateTransaction.setString(11, tran.getPayMethod());
			this.stateTransaction.setString(12, tran.getName());
			this.stateTransaction.setString(13, tran.getIdCard());
			this.stateTransaction.setString(14, tran.getPhone());
			this.stateTransaction.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public synchronized void setEmployee(Employee e){
		try {
			this.stateEmployee.setString(1, e.getWorkId());
			this.stateEmployee.setString(2, e.getPassword());
			this.stateEmployee.setString(3, e.getIdentity());
			this.stateEmployee.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("Set Employee ID Failed");
			e1.printStackTrace();
		}
	}
	
	//���¹�ʱ������
	//sΪ��Ҫ�ı�����ݣ�tableΪĿ�����ݿ�ı�����ƣ�nameΪ�������ƣ��磬employeeId���
	//�����ݿ�Ҫһ��,conNameΪƥ����������ƣ�conditionΪƥ����������ݣ�����where�Ӿ�
	public synchronized void updateString(String s,String table,String name,String conName,String condition){
		if(s!=null){
			try {
				PreparedStatement pstmt=conn.prepareStatement("update "+table+" set "+name+"="+s+" where "+conName+"="+condition);
				pstmt.executeUpdate();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Update DataBase for String Type Info Failed!");
				e.printStackTrace();
			}
		}
	}
	public synchronized void updateInt(int s,String table,String name,String conName,int condition){
		try {
			PreparedStatement pstmt=conn.prepareStatement("update "+table+" set "+name+"="+s+" where "+conName+"="+condition);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Update DataBase for Integer Type Info Failed!");
			e.printStackTrace();
		}
	}
	public synchronized void updateDouble(double s,String table,String name,String conName,double condition){
		try {
			PreparedStatement pstmt=conn.prepareStatement("update "+table+" set "+name+"="+s+" where "+conName+"="+condition);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Update DataBase for Double Type Info Failed!");
			e.printStackTrace();
		}
	}
	
	//ɾ�����ݿ��в���Ҫ�Ľ��׼�¼
	public synchronized void delTransaction(){
		try {
			this.stateTransaction.executeQuery("delete from transaction where serialNum!=null");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Delete All Transaction Info Failed!");
			e.printStackTrace();
		}
	}
	public synchronized void delTransaction(String Serial){
		try {
			this.stateTransaction.executeQuery("delete from transaction where serialNum="+Serial);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Delete Transaction Info with Serial Number Failed!");
			e.printStackTrace();
		}
	}
	public synchronized void delTransaction(transaction tran){
		try {
			this.stateTransaction.executeQuery("delete from transaction where serialNum="+tran.getSerialNum());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Delete Transaction Info with Serial Number Failed!");
			e.printStackTrace();
		}
	}
	//ɾ�����ݿ��в���Ҫ��Ա����Ϣ
	public synchronized void delEmployee(){
		try {
			this.stateEmployee.executeQuery("delete from employee where employeeId!=null");
			this.stateEmployee.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Delete All Employee Info Failed!");
			e.printStackTrace();
		}
	}
	public synchronized void delEmployee(String e){
		try {
			this.stateEmployee.executeQuery("delete from employee where employeeId="+e);
			this.stateEmployee.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("Delete Employee Info with WrokID Failed!");
			e1.printStackTrace();
		}
	}
	public synchronized void delEmployee(Employee e){
		try {
			this.stateEmployee.executeQuery("delete from employee where employeeId="+e.getWorkId());
			this.stateEmployee.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	//Ϊ�����ݿ���ResultSet����ʼ������
	public synchronized ResultSet getRsTransaction(){
		try {
			this.rsTransaction=stateTransaction.executeQuery("select * from transaction");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Get Transaction Result Set Failed");
			e.printStackTrace();
		}
		return this.rsTransaction;
	}
	public synchronized ResultSet getRsEmployee(){
		try {
			this.rsEmployee=stateEmployee.executeQuery("select * from employee");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Get Employee Info Failed");
			e.printStackTrace();
		}
		return this.rsEmployee;
	}
	public synchronized ResultSet getRsTranTodayInfo(){
		ResultSet rs=null;
		try {
			rs=this.stateTransaction.executeQuery("select * from transaction where date(time) = curdate();");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Get Today Transaction Information Failed");
			e.printStackTrace();
		}
		return rs;
	}
	private synchronized ResultSet getRsTranWeekInfo(){
		ResultSet rs=null;
		try {
			rs=this.stateTransaction.executeQuery("SELECT * FROM transaction WHERE YEARWEEK(date_format(time,'%Y-%m-%d')) = YEARWEEK(now());");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Get Week Transaction Information rs Failed");
			e.printStackTrace();
		}
		return rs;
	}
	private synchronized ResultSet getRsTranMonthInfo(){
		ResultSet rs=null;
		try {
			rs=this.stateTransaction.executeQuery("select * from transaction where date_format(time,'%Y-%m')=date_format(now(),'%Y-%m')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Get Month Transaction Information Failed");
			e.printStackTrace();
		}
		return rs;
	}
	private synchronized ResultSet getRsTranSerialNumORNameInfo(String str){
		ResultSet rs=null;
		try {
			rs=this.stateTransaction.executeQuery("select * from employee "+str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Get RS about TranSerial Number OR Name Info Failed!");
			e.printStackTrace();
		}
		return rs;
	}
	//�����ݿ������л�ȡ���轻�׼�¼
	//������Ĳ���
	public synchronized transaction[] getTransaction(ResultSet rst){
		if(rst==null)rst=this.getRsTransaction();
		int num=0;
		transaction[] tran=null;
		try {
			//��ȡ������ļ�¼��
			rst.last();
			num=rst.getRow();
			rst.beforeFirst();
			//����������Ϣ,���ȶ��巵�ص�����
			tran=new transaction[num];
			int i=0;
			while(rst.next()){
				tran[i].setSerialNum(rst.getString(1));
				tran[i].setWorkID(rst.getString(2));
				tran[i].setStockNum(rst.getString(3));
				tran[i].setStockType(rst.getString(4));
				tran[i].setCusState(rst.getInt(5));
				tran[i].setStockAmount(rst.getInt(6));
				tran[i].setUnitPrice(rst.getDouble(7));
				tran[i].setTotalAmount();
				tran[i].setInterestRate(rst.getDouble(9));
				tran[i].setTranState(rst.getString(10));
				tran[i].setPayMethod(rst.getString(11));
				tran[i].setName(rst.getString(12));
				tran[i].setIdCard(rst.getString(13));
				tran[i].setPhone(rst.getString(14));
				tran[i].setTranTime(rst.getTimestamp(15));
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tran;
	}
	public synchronized transaction[] getAllTranInfo(){
		transaction[] t=getTransaction(this.rsTransaction);
		return t;
	}
	public synchronized transaction[] getTodayTranInfo(){
		ResultSet rs=this.getRsTranTodayInfo();
		transaction[] t=this.getTransaction(rs);
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Release ToDayInfo rs Failed!");
			e.printStackTrace();
		}
		return t;
	}
	public synchronized transaction[] getWeekTranInfo(){
		ResultSet rs=this.getRsTranWeekInfo();
		transaction[] t=this.getTransaction(rs);
		return t;
	}
	public synchronized transaction[] getMonthTranInfo(){
		ResultSet rs=this.getRsTranMonthInfo();
		transaction[] t=this.getTransaction(rs);
		return t;
	}
	public synchronized transaction[] getSerialNumORNameTranInfo(String str){
		transaction[] tran=null;
		ResultSet rs=null;
		//���ȫ�����֣���Ϊ������ţ�����Ϊ�û�����
		if(str.matches("[0-9]+"))
			rs=this.getRsTranSerialNumORNameInfo("where serialNum="+str);
		else rs=this.getRsTranSerialNumORNameInfo("where name="+str);
		tran=this.getTransaction(rs);
		return tran;
	}
	
	//Sort ��������������������������η���
	
	private synchronized void timeQuickSort(transaction[] tran,int left,int right){
		if(right<=left)return;
		Timestamp temp=tran[left].getTranTime();
		int i=left,j=right;
		while(i!=j){
			while(i<j&&tran[j].getTranTime().compareTo(temp)>=0)j--;
			while(i<j&&tran[i].getTranTime().compareTo(temp)<=0)i++;
			if(i!=j){transaction t=new transaction();t=tran[i];tran[i]=tran[j];tran[j]=t;}
		}
		timeQuickSort(tran,0,left-1);
		timeQuickSort(tran,left+1,right);
	}
	
	private synchronized void serialNumQuickSort(transaction[] tran,int left,int right){
		if(right<=left)return;
		String temp=tran[left].getSerialNum();
		int i=left,j=right;
		while(i!=j){
			while(i<j&&tran[j].getSerialNum().compareTo(temp)>=0)j--;
			while(i<j&&tran[i].getSerialNum().compareTo(temp)<=0)i++;
			if(i!=j){transaction t=new transaction();t=tran[i];tran[i]=tran[j];tran[j]=t;}
		}
		timeQuickSort(tran,0,left-1);
		timeQuickSort(tran,left+1,right);
	}
	//���������ݷ���
	private synchronized void getReverse(transaction[] tran){
		for(int i=0;i<tran.length;i++){
			transaction tranTemp=new transaction();
			tranTemp=tran[i];
			tran[i]=tran[tran.length-1-i];
			tran[tran.length-1-i]=tranTemp;
		}
	}
	//����ʱ��˳������
	public synchronized transaction[] timeSort(transaction[] tran){
		timeQuickSort(tran,0,tran.length-1);
		return tran;
	}
	//����ʱ�䵹������
	public synchronized transaction[] timeSortReverse(transaction[] tran){
		timeQuickSort(tran,0,tran.length-1);
		getReverse(tran);
		return tran;
	}
	//���ݶ������˳������
	public synchronized transaction[] serialNumSort(transaction[] tran){
		serialNumQuickSort(tran,0,tran.length-1);
		return tran;
	}
	//���ݶ�����ŵ�������
	public synchronized transaction[] serialNumSortReverse(transaction[] tran){
		serialNumQuickSort(tran,0,tran.length-1);
		getReverse(tran);
		return tran;
	}
	
	public void init(){
		this.setConnection();
		this.setPreparedStatement();
	}

	public void destroy(){
		try {
			if(this.conn!=null)this.conn.close();
			if(this.stateEmployee!=null)this.stateEmployee.close();
			if(this.stateTransaction!=null)this.stateTransaction.close();
			if(this.rsTransaction!=null)rsTransaction.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Destroy failed");
			e.printStackTrace();
		}
	}
}