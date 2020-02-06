package com.example.moviesbk.exceptions;

public class UserNotExist extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotExist(String errorMessage){ super(errorMessage); }
}
