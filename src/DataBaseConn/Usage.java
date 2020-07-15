package DataBaseConn;

public class Usage 
{	
	 private int id;
	 private int fullplanid;
	 private int usedsms;
	 private int usedminutes;
	 private int useddata;
	 private boolean isActivesms;
	 private boolean isActiveminutes;
	 private boolean isActivedata;
	 private boolean isPayed;
	 private int month;
	 
	 
	 public Usage(int id, int fullplanid, int usedsms, int usedminutes, int useddata, boolean isActivesms,
			boolean isActiveminutes, boolean isActivedata, boolean isPayed, int month) {
		this.id = id;
		this.fullplanid = fullplanid;
		this.usedsms = usedsms;
		this.usedminutes = usedminutes;
		this.useddata = useddata;
		this.isActivesms = isActivesms;
		this.isActiveminutes = isActiveminutes;
		this.isActivedata = isActivedata;
		this.isPayed = isPayed;
		this.month = month;
	}
	public int getFullplanid() {
			return fullplanid;
		}
	 public void setFullplanid(int fullplanid) {
			this.fullplanid = fullplanid;
		}
	 
	public int getUsedsms() {
		return usedsms;
	}
	public void setUsedsms(int usedsms) {
		this.usedsms = usedsms;
	}
	public int getUsedminutes() {
		return usedminutes;
	}
	public void setUsedminutes(int usedminutes) {
		this.usedminutes = usedminutes;
	}
	public int getUseddata() {
		return useddata;
	}
	public void setUseddata(int useddata) {
		this.useddata = useddata;
	}
	public boolean isActivesms() {
		return isActivesms;
	}
	public void setActivesms(boolean isActivesms) {
		this.isActivesms = isActivesms;
	}
	public boolean isActiveminutes() {
		return isActiveminutes;
	}
	public void setActiveminutes(boolean isActiveminutes) {
		this.isActiveminutes = isActiveminutes;
	}
	public boolean isActivedata() {
		return isActivedata;
	}
	public void setActivedata(boolean isActivedata) {
		this.isActivedata = isActivedata;
	}
	public boolean isPayed() {
		return isPayed;
	}
	public void setPayed(boolean isPayed) {
		this.isPayed = isPayed;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
