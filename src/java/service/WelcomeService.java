/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javaoinlibrary.FileIO;

/**
 *
 * @author vernet
 */
public class WelcomeService {

	public String version() {
		String fileName = FileIO.programmFileName(getClass(), true);
		String dateStr =
						FileIO.fileLastModifiedDate(fileName);
		return dateStr + " " + fileName;
	}
}
