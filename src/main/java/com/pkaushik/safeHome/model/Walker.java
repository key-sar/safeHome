package com.pkaushik.safeHome.model;

import java.util.HashMap;
import java.util.List;

public class Walker extends UserRole {

	//Attributes
	private int rating; 
	private boolean isWalksafe; 
	private boolean hasSchedule = false; 
	private Schedule schedule; 
	private SafeHome safeHome = SafeHome.getSafeHomeInstance(); 
	
	private HashMap<Integer, Walker> walkerMap = new HashMap<Integer, Walker>();

	public Walker(SafeHome safeHome, boolean isWalksafe) {
		super(safeHome);
		this.setSafeHome(safeHome);
		//always create a walker with an empty schedule. 
		rating = 0; 
		this.isWalksafe = isWalksafe; 
	}

	public static Walker getWalker(int mcgillID){
		User userWithID = User.getUser(mcgillID);
		UserRole walkerRole = null; 
		List<UserRole> userRoles = userWithID.getRoles();
		for(UserRole role : userRoles){
			if(role instanceof Walker){
				walkerRole = role; 
				break; 
			}
		}
		if(walkerRole == null) throw new IllegalAccessError("Walker with ID does not exist"); 
		else{
			return (Walker) walkerRole; 
		}
	}
	
	public HashMap<Integer, Walker> getWalkerMap() {
		return walkerMap;
	}

	public void setWalkerMap(HashMap<Integer, Walker> walkerMap) {
		this.walkerMap = walkerMap;
	}
	

	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * @return the isWalksafe
	 */
	public boolean isWalksafe() {
		return isWalksafe;
	}

	/**
	 * @param isWalksafe the isWalksafe to set
	 */
	public void setWalksafe(boolean isWalksafe) {
		this.isWalksafe = isWalksafe;
	}

	/**
	 * @return the schedule
	 */
	public Schedule getSchedule() {
		if(schedule == null && hasSchedule == false){
			throw new IllegalAccessError("This walker does not have a schedule. Please create one before trying to access it.");
		}
		else{
		hasSchedule = true; 
		return schedule;
	}
}

	/**
	 * @param schedule the schedule to set
	 */
	public void setSchedule(Schedule schedule) {
		hasSchedule = true; 
		this.schedule = schedule;
	}

	@Override
	public void setSafeHome(SafeHome safeHomeInput){
		SafeHome currSafeHome = safeHome; 
		if(currSafeHome != null && !currSafeHome.equals(safeHomeInput)){
			currSafeHome.removeWalker(this); 
		}
		safeHome = safeHomeInput; 
		safeHome.addWalker(this); 
	}

	public boolean hasSchedule() {
		return this.hasSchedule; 
	}
}
