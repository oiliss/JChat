/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author vernet
 */
public class LoginService {

	public boolean isValidUser(String name, String password) {
		return true;
	}

	public String userInfo(String name, String password) {
		return "name " + name + ", password " + password;
	}
}
