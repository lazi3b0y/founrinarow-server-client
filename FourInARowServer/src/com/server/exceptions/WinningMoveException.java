package com.server.exceptions;

public class WinningMoveException extends Exception {
	private static final long serialVersionUID = 7497095158234648717L;

	public WinningMoveException(String message) {
        super(message);
    }
}