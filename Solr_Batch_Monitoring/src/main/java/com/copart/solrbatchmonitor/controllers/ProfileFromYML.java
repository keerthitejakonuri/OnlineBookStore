package com.copart.solrbatchmonitor.controllers;


public class ProfileFromYML {
	
	public String profileFromYML;
	public boolean ukRenoMariadb_conf_flag;
	public boolean usRenoMariadb_conf_flag;
	public boolean usDb2_conf_flag;
	public boolean usColoMariadb_conf_flag;
	public boolean ukColoMariadb_conf_flag;
	
	public ProfileFromYML(String profileFromYML, boolean ukRenoMariadb_conf_flag, boolean usRenoMariadb_conf_flag, boolean usDb2_conf_flag, boolean usColoMariadb_conf_flag, boolean ukColoMariadb_conf_flag){
		this.profileFromYML = profileFromYML;
		this.ukRenoMariadb_conf_flag = ukRenoMariadb_conf_flag;
		this.usRenoMariadb_conf_flag = usRenoMariadb_conf_flag;
		this.usDb2_conf_flag = usDb2_conf_flag;
		this.usColoMariadb_conf_flag = usColoMariadb_conf_flag;
		this.ukColoMariadb_conf_flag = ukColoMariadb_conf_flag;
	}
	
	public void setProfileFromYML(String profileFromYML){
		this.profileFromYML = profileFromYML;
	}
	public String getProfileFromYML(){
		return profileFromYML;
	}
	
	public void setukRenoMariadb_conf_flag(boolean ukRenoMariadb_conf_flag){
		this.ukRenoMariadb_conf_flag = ukRenoMariadb_conf_flag;
	}
	public boolean getukRenoMariadb_conf_flag(){
		return ukRenoMariadb_conf_flag;
	}
	
	public void setusRenoMariadb_conf_flag(boolean usRenoMariadb_conf_flag){
		this.usRenoMariadb_conf_flag = usRenoMariadb_conf_flag;
	}
	public boolean getusRenoMariadb_conf_flag(){
		return usRenoMariadb_conf_flag;
	}
	
	public void setusDb2_conf_flag(boolean usDb2_conf_flag){
		this.usDb2_conf_flag = usDb2_conf_flag;
	}
	public boolean getusDb2_conf_flag(){
		return usDb2_conf_flag;
	}
	
	public void setusColoMariadb_conf_flag(boolean usColoMariadb_conf_flag){
		this.usColoMariadb_conf_flag = usColoMariadb_conf_flag;
	}
	public boolean getusColoMariadb_conf_flag(){
		return usColoMariadb_conf_flag;
	}
	
	public void setukColoMariadb_conf_flag(boolean ukColoMariadb_conf_flag){
		this.ukColoMariadb_conf_flag = ukColoMariadb_conf_flag;
	}
	public boolean getukColoMariadb_conf_flag(){
		return ukColoMariadb_conf_flag;
	}
	
}
