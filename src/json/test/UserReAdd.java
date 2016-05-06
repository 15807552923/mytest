package json.test;

public class UserReAdd implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5260907946486521499L;
	
	private String reName;
	private String rePhone;
	private String reAdd;
	private String defaultAp;
	private String uuid;
	
	public String getReName() {
		return reName;
	}
	public void setReName(String reName) {
		this.reName = reName;
	}
	public String getRePhone() {
		return rePhone;
	}
	public void setRePhone(String rePhone) {
		this.rePhone = rePhone;
	}
	public String getReAdd() {
		return reAdd;
	}
	public void setReAdd(String reAdd) {
		this.reAdd = reAdd;
	}
	public String getDefaultAp() {
		return defaultAp;
	}
	public void setDefaultAp(String defaultAp) {
		this.defaultAp = defaultAp;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Override
	public String toString() {
		return "UserReAdd [reName=" + reName + ", rePhone=" + rePhone
				+ ", reAdd=" + reAdd + ", defaultAp=" + defaultAp + ", uuid="
				+ uuid + "]";
	}
	
	
	
	
	
	

}
