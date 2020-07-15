package DataBaseConn;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class conn 
{ 
	public static List<Users> GetAllUsers()
	{
	try 
	{
		List<Users> allusers=null;
		Connection myConn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileoperatorapp","root","frizin12");
		
		Statement myStmt=myConn.createStatement();
		
		ResultSet myRs=myStmt.executeQuery("SELECT * FROM users");
		
		while(myRs.next())
		{  
			if(allusers==null)allusers =new ArrayList<>();
			Users u=new Users(myRs.getString("nickname"),myRs.getString("email"),myRs.getString("password"),myRs.getBoolean("isAdmin"),myRs.getString("phone"),myRs.getInt("id"));
			allusers.add(u);
		}
		return allusers;
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return null;
	}
	public static List<Plan> GetAllPlans()
	{
	try 
	{
		List<Plan> allplans=null;
		Connection myConn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileoperatorapp","root","frizin12");
		
		Statement myStmt=myConn.createStatement();
		
		ResultSet myRs=myStmt.executeQuery("SELECT * FROM plan");
		
		while(myRs.next())
		{  
			if(allplans==null)allplans =new ArrayList<>();
			Plan p=new Plan(myRs.getInt("id"), myRs.getInt("sms"), myRs.getInt("minutes"),myRs.getInt("mobile_data"),myRs.getDouble("price"),myRs.getString("name"));
			allplans.add(p);
		}
		return allplans;
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return null;
	}
	public static void InserIntoUsers(String nickname,String email,String phone,String password,boolean isAdmin) throws SQLException
	{
	
		
		Connection myConn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileoperatorapp","root","frizin12");
		
		Statement myStmt=myConn.createStatement();
		String admin=null;
		if(isAdmin) admin="1";
		if(!isAdmin) admin="0";
		String sql="insert into users "
				+"(nickname,email,phone,password,isAdmin) "
				+"values('"+nickname+"','"+email+"','"+phone+"','"+password+"','"+admin+"')";
		myStmt.executeUpdate(sql);
	
	}
	public static void InserIntousers_plan(String username,String plan) throws Exception
	{
	
		
		Connection myConn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileoperatorapp","root","frizin12");
		
		Statement myStmt=myConn.createStatement();
		int userid=-1,planid=-1,plansms=-1,planminutes =-1,plandata=-1;
		List<Users> userlist = conn.GetAllUsers();
		List<Plan> planlist=conn.GetAllPlans();
		for(Users u:userlist) 
		{
			if(u.getName().equals(username)||u.getEmail().equals(username))
			{
				userid=u.getId();
				
			}
		}
		for(Plan p:planlist) 
		{
			if(p.getName().equals(plan))
			{
				planid=p.getId();
				plansms=p.getSms();
				planminutes=p.getMinutes();
				plandata=p.getMobile_data();
			}
		}
		Statement myStmt3=myConn.createStatement();
		ResultSet myRs2=myStmt.executeQuery("SELECT user_id,plan_id FROM users_plan WHERE user_id="+"'"+userid+"'AND plan_id='"+planid +"'");
		int uid=-1,pid=-1;
		while(myRs2.next())
		{ 
			uid=myRs2.getInt("user_id");
			pid=myRs2.getInt("plan_id");
			
		}
		if((uid==-1)&&(pid==-1))
		{
		if((planid!=-1)&&(userid!=-1))
		{
			
			String sql="insert into users_plan "
					+"(user_id,plan_id,startdate,IsActivePlan) "
					+"values('"+userid+"','"+planid+"',now(),'1')";
			myStmt.executeUpdate(sql);
			ResultSet myRs=myStmt.executeQuery("SELECT id FROM users_plan WHERE user_id="+"'"+userid+"'AND plan_id='"+planid +"'");
			Statement myStmt2=myConn.createStatement();
			while(myRs.next())
			{  
				int planusageid=myRs.getInt("id");
				String sql2="insert into planusages "
						+"(fullplan_id,used_SMS,used_minutes,used_mobile_data,IsActiveSMS,IsActiveMinutes,IsActiveMobileData,IsPayed,month) "
						+"values('"+planusageid+"',0,0,0"+",'1','1','1','1',month(now()))";
				myStmt2.executeUpdate(sql2);
				
			}
			
		}
		}
		if((uid!=-1)&&(pid!=-1))
		{
			
			throw new SQLException("There is a user that has this plan") ;
		}
	}
	public static int GetUserid(String usernameOrEmail)
	{
		try 
		{
			int id = -1;
			Connection myConn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileoperatorapp","root","frizin12");
			
			Statement myStmt=myConn.createStatement();
			
			ResultSet myRs=myStmt.executeQuery("SELECT id FROM users Where nickname='"+usernameOrEmail+"'OR email='"+usernameOrEmail+"'");
			
			while(myRs.next())
			{  
				id=myRs.getInt("id");
			}
			return id;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	public static int[] GetLastMonthOfpayday(int userid,List<Integer>fullplan_id)
	{
		try 
		{
			int month[]=new int[3];
			month[0]=-1;
			month[1]=-1;
			month[2]=-1;
			List<Integer>plan_id=new ArrayList<>();
			Connection myConn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileoperatorapp","root","frizin12");
			
			Statement myStmt=myConn.createStatement();
			
			ResultSet myRs=myStmt.executeQuery("SELECT id,plan_id  FROM users_plan Where user_id='"+userid+"'");
			
			while(myRs.next())
			{ 	
				fullplan_id.add(myRs.getInt("id"));
				plan_id.add(myRs.getInt("plan_id"));
			}
			for(int i=0;i<fullplan_id.size();i++)
			{
			myRs=myStmt.executeQuery("Select month  from planusages where fullplan_id='"+fullplan_id.get(i)+"'");
			while(myRs.next())
			{ 	
				month[plan_id.get(i)-1]=myRs.getInt("month");
			}
			}
			
			
			return month;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static int getFullplanid(int userid,int plan_id)
	{
		int fullplan_id;
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileoperatorapp","root","frizin12");
			Statement myStmt=myConn.createStatement();
			ResultSet myRs=myStmt.executeQuery("SELECT id  FROM users_plan Where user_id='"+userid+"' AND plan_id='"+plan_id+"'");
			while(myRs.next())
			{ 	
				fullplan_id=myRs.getInt("id");
				return fullplan_id;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return -1;
	}
	public static Usage getUsage(int fullplanid)
	{
		 int id;
		 int fullplan_id;
		 int usedsms;
		 int usedminutes;
		 int useddata;
		 boolean isActivesms;
		 boolean isActiveminutes;
		 boolean isActivedata;
		 boolean isPayed;
		 int month;
		Connection myConn;
		try {
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileoperatorapp","root","frizin12");
			Statement myStmt=myConn.createStatement();
			ResultSet myRs=myStmt.executeQuery("SELECT * FROM planusages Where fullplan_id='"+fullplanid+"' AND month=month(now())");
			while(myRs.next())
			{ 	
				id=myRs.getInt("id");
				fullplan_id=myRs.getInt("fullplan_id");
				usedsms=myRs.getInt("used_SMS");
				usedminutes=myRs.getInt("used_minutes");
				useddata=myRs.getInt("used_mobile_data");
				isActivesms=myRs.getBoolean("IsActiveSMS");
				isActiveminutes=myRs.getBoolean("IsActiveMinutes");
				isActivedata=myRs.getBoolean("IsActiveMobileData");
				isPayed=myRs.getBoolean("isPayed");
				month=myRs.getInt("month");
				Usage use=new Usage(id,fullplan_id,usedsms,usedminutes,useddata,isActivesms,isActiveminutes,isActivedata,isPayed,month);
				return use;
			}

			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public static int getPlanId(String choosenPlan)
	{
		try 
		{
			int id = -1;
			Connection myConn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileoperatorapp","root","frizin12");
			
			Statement myStmt=myConn.createStatement();
			
			ResultSet myRs=myStmt.executeQuery("SELECT id FROM Plan Where name='"+choosenPlan+"'");
			
			while(myRs.next())
			{  
				id=myRs.getInt("id");
			}
			return id;
		

		}
		catch (Exception e)
		{
			e.getStackTrace();
		}
		return -1;

	}
	public static List<Users> GetAllUserswithPlan(int planid)
	{
		List<Users> allusers=conn.GetAllUsers();
		List<Users> resultusers=new ArrayList<>();
		int userid=-1;
		Connection myConn;
		try 
		{
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileoperatorapp","root","frizin12");
			Statement myStmt=myConn.createStatement();
			ResultSet myRs=myStmt.executeQuery("SELECT user_id FROM users_plan Where plan_id='"+planid+"'");
			while(myRs.next())
			{  
				userid=myRs.getInt("user_id");
				for(Users u:allusers)
				{
					if(u.getId()==userid)
					{
						resultusers.add(u);
					}
				}
			}
		return resultusers;
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return resultusers;
	}
	public static List<Plan> getUserPlans(int userid)
	{
		List<Plan> allplans=conn.GetAllPlans();
		List<Plan> resultplan=new ArrayList<>();
		int planid=-1;
		Connection myConn;
		try 
		{
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileoperatorapp","root","frizin12");
			Statement myStmt=myConn.createStatement();
			ResultSet myRs=myStmt.executeQuery("SELECT plan_id FROM users_plan Where user_id='"+userid+"'");
			while(myRs.next())
			{  
				planid=myRs.getInt("plan_id");
				for(Plan p:allplans)
				{
					if(p.getId()==planid)
					{
						resultplan.add(p);
					}
				}
			}
		return resultplan;
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultplan;
	}
	public static List<Users> notPaidUsers()
	{
		List <Users> returnusers=new ArrayList<>();
		Connection myConn;
		try 
		{
			
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileoperatorapp","root","frizin12");
			Statement myStmt=myConn.createStatement();
			List <Integer> fullidofnonpaid=conn.getFullplanidthathavenotpaid();
			for(Integer id:fullidofnonpaid)
			{
			ResultSet myRs=myStmt.executeQuery("Select DISTINCT users.nickname,users.email,users.phone from users_plan join users On users_plan.user_id=users.id Where users_plan.id='"+id+"'");
			while(myRs.next())
				{	
					boolean u_is_in_the_array=false;
					Users u=new Users(myRs.getString("users.nickname"),myRs.getString("users.email"),myRs.getString("users.phone"));
					for(Users ur:returnusers)
					{
						if((ur.getEmail().equals(u.getEmail()))&&(ur.getName().equals(u.getName()))&&(ur.getPhone().equals(u.getPhone())))
						{
							u_is_in_the_array=true;
							break;
						}
						
					}
					if(!u_is_in_the_array) returnusers.add(u);
				}
			}
			return returnusers;
		
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return returnusers;
	}
	public static List<Integer> getFullplanidthathavenotpaid()
	{
		Connection myConn;
		List<Integer> allfullplan_id= new ArrayList<>();
		try 
		{
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileoperatorapp","root","frizin12");
			Statement myStmt=myConn.createStatement();
			ResultSet myRs=myStmt.executeQuery("SELECT DISTINCT fullplan_id FROM planusages Where month<=month(now())");
			while(myRs.next())
			{  
				allfullplan_id.add(myRs.getInt("fullplan_id"));
			}
		return allfullplan_id;
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allfullplan_id;
		
	}
	public static boolean IsfuturepaidPlan(int userid,int planid)
	{		boolean isActive=false;
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileoperatorapp","root","frizin12");
			Statement myStmt=myConn.createStatement();
			int fullplanid=conn.getFullplanid(userid, planid);
			ResultSet myRs=myStmt.executeQuery("SELECT month FROM planusages Where fullplan_id='"+fullplanid+"'");
			while(myRs.next())
			{ 
			if(myRs.getInt("month")>=((Calendar.getInstance().get(Calendar.MONTH))+1))
			{
				isActive=true;
				break;
			}
			else 
			{
				Statement myStmt2=myConn.createStatement();
				String sql="Update users_plan "+
						"SET IsActivePlan=0 WHERE id='"+fullplanid+"'";
				myStmt2.executeUpdate(sql);
			}
			return isActive;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			return isActive;
		}
	}
		public static boolean IsActiveSMS(int userid,int planid)
		{
			boolean isActive=false;
			try {
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileoperatorapp","root","frizin12");
				Statement myStmt=myConn.createStatement();
				int fullplanid=conn.getFullplanid(userid, planid);
				ResultSet myRs=myStmt.executeQuery("SELECT IsActiveSMS FROM planusages Where fullplan_id='"+fullplanid+"'"+" AND month=month(now())");
				while(myRs.next())
				{ 
				if(myRs.getInt("IsActiveSMS")==1)
				{
					isActive=true;
					break;
				}
				}
				return isActive;
				}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				return isActive;
			}
		}
		public static boolean IsActiveMinutes(int userid,int planid)
		{
			boolean isActive=false;
			try {
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileoperatorapp","root","frizin12");
				Statement myStmt=myConn.createStatement();
				int fullplanid=conn.getFullplanid(userid, planid);
				ResultSet myRs=myStmt.executeQuery("SELECT IsActiveMinutes FROM planusages Where fullplan_id='"+fullplanid+"'"+" AND month=month(now())");
				while(myRs.next())
				{ 
				if(myRs.getInt("IsActiveMinutes")==1)
				{
					isActive=true;
					break;
				}
				}
				return isActive;
				}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				return isActive;
			}
		}
		public static boolean IsActiveData(int userid,int planid)
		{
			boolean isActive=false;
			try {
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileoperatorapp","root","frizin12");
				Statement myStmt=myConn.createStatement();
				int fullplanid=conn.getFullplanid(userid, planid);
				ResultSet myRs=myStmt.executeQuery("SELECT IsActiveMobileData FROM planusages Where fullplan_id='"+fullplanid+"'"+" AND month=month(now())");
				while(myRs.next())
				{ 
				if(myRs.getInt("IsActiveMobileData")==1)
				{
					isActive=true;
					break;
				}
				}
				return isActive;
				}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				return isActive;
			}
		}
		public static int getAllminususedMinutes(int userid,int planid)
		{
			int usedminutes=-1;
			int allminutes=-1;
			int idformonth=0;
			try {
				if((conn.IsActiveMinutes(userid, planid))&&conn.IsfuturepaidPlan(userid, planid))
				{
				int fullplanid=conn.getFullplanid(userid, planid);
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileoperatorapp","root","frizin12");
				Statement myStmt=myConn.createStatement();
				Statement myStmt2=myConn.createStatement();
				ResultSet myRs=myStmt.executeQuery("SELECT id,used_minutes FROM planusages Where fullplan_id='"+fullplanid+"'"+" AND month=month(now())");
				ResultSet myRs2=myStmt2.executeQuery("SELECT minutes FROM plan WHERE id='"+planid+"'");
				while(myRs.next())
				{
					usedminutes=myRs.getInt("used_minutes");
					idformonth=myRs.getInt("id");
				}
				while(myRs2.next())
				{
					allminutes=myRs2.getInt("minutes");
				}
				if(allminutes-usedminutes<=0)
				{
					Statement myStmt3=myConn.createStatement();
					String sql="Update planusages "+
							"SET IsActiveMinutes = '0' WHERE (id='"+idformonth+"')";
					myStmt3.executeUpdate(sql);
				}
				return allminutes-usedminutes;
				}
				else return -1;
				}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				return -1;
		}
		public static int getAllminususedSMS(int userid,int planid)
		{
			int usedSMS=-1;
			int allSMS=-1;
			int idformonth=0;
			try {
				if((conn.IsActiveSMS(userid, planid))&&conn.IsfuturepaidPlan(userid, planid))
				{
				int fullplanid=conn.getFullplanid(userid, planid);
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileoperatorapp","root","frizin12");
				Statement myStmt=myConn.createStatement();
				Statement myStmt2=myConn.createStatement();
				ResultSet myRs=myStmt.executeQuery("SELECT id,used_SMS FROM planusages Where fullplan_id='"+fullplanid+"'"+" AND month=month(now())");
				ResultSet myRs2=myStmt2.executeQuery("SELECT SMS FROM plan WHERE id='"+planid+"'");
				while(myRs.next())
				{
					usedSMS=myRs.getInt("used_SMS");
					idformonth=myRs.getInt("id");
				}
				while(myRs2.next())
				{
					allSMS=myRs2.getInt("SMS");
				}
				if(allSMS-usedSMS<=0)
				{
					Statement myStmt3=myConn.createStatement();
					String sql="Update planusages "+
							"SET IsActiveSMS = '0' WHERE (id='"+idformonth+"')";
					myStmt3.executeUpdate(sql);
				}
				return allSMS-usedSMS;
				}
				else return -1;
				}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				return -1;
		}
		public static int getAllminususedData(int userid,int planid)
		{
			int usedData=-1;
			int allData=-1;
			int idformonth=0;
			try {
				if((conn.IsActiveData(userid, planid))&&conn.IsfuturepaidPlan(userid, planid))
				{
				int fullplanid=conn.getFullplanid(userid, planid);
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileoperatorapp","root","frizin12");
				Statement myStmt=myConn.createStatement();
				Statement myStmt2=myConn.createStatement();
				ResultSet myRs=myStmt.executeQuery("SELECT id,used_mobile_data FROM planusages Where fullplan_id='"+fullplanid+"'"+" AND month=month(now())");
				ResultSet myRs2=myStmt2.executeQuery("SELECT mobile_data FROM plan WHERE id='"+planid+"'");
				while(myRs.next())
				{
					usedData=myRs.getInt("used_mobile_data");
					idformonth=myRs.getInt("id");
				}
				while(myRs2.next())
				{
					allData=myRs2.getInt("mobile_data");
				}
				if(allData-usedData<=0)
				{
					Statement myStmt3=myConn.createStatement();
					String sql="Update planusages "+
							"SET IsActiveMobileData = '0' WHERE (id='"+idformonth+"')";
					myStmt3.executeUpdate(sql);
				}
				return allData-usedData;
				}
				else return -1;
				}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				return -1;
		}
		public static void UpdateMinutes(int minutes,int fullplanid) 
		{
			Connection myConn;
			try {
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileoperatorapp","root","frizin12");
				Statement myStmt=myConn.createStatement();
				String sql="Update planusages "+
						"SET used_minutes=used_minutes +'"+minutes+"'"+"WHERE id='"+fullplanid+"'";
				myStmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void UpdateSMS(int SMS,int fullplanid) 
		{
			Connection myConn;
			try {
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileoperatorapp","root","frizin12");
				Statement myStmt=myConn.createStatement();
				String sql="Update planusages "+
						"SET used_SMS=used_SMS +'"+SMS+"'"+"WHERE id='"+fullplanid+"'";
				myStmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void UpdateMobileData(int mobiledata,int fullplanid) 
		{
			Connection myConn;
			try {
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobileoperatorapp","root","frizin12");
				Statement myStmt=myConn.createStatement();
				String sql="Update planusages "+
						"SET used_mobile_data=used_mobile_data +'"+mobiledata+"'"+"WHERE id='"+fullplanid+"'";
				myStmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}