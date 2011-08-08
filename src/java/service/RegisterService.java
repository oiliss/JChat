/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db_objects.User;
import hibernate.UserHelper;

/**
 *
 * @author vernet
 */
public class RegisterService {
	
	public void addUser(User user) {
		UserHelper userHelper = new UserHelper();
		userHelper.addUser(user);
		return;
	}
}
