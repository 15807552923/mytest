package test;

public class UserInfoExcel {
	private String username;  
    private String password;  
    private String infoTitle;
    
    
    
       
    public UserInfoExcel(String n, String c,String t){  
        this.username=n;  
        this.password=c;  
        this.infoTitle=t;
    }




	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInfoTitle() {
		return infoTitle;
	}

	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}




	@Override
	public String toString() {
		return "UserInfoExcel [username=" + username + ", password=" + password
				+ ", infoTitle=" + infoTitle + "]";
	}  
       
  
       

}
