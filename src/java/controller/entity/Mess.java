/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.entity;

/**
 *
 * @author vernet
 */
public class Mess {

	private Long userId;
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
