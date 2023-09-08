package org.enchere.eni.m.dal;

import java.util.ArrayList;
import java.util.List;

public class DALException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private List<Integer> errorCodeList = new ArrayList<Integer>();

	public DALException() {
		super();
	}
	
	public DALException(int code) {
		super();
		errorCodeList.add(code);
	}
	
	public void addErrorCode(int code) {
		errorCodeList.add(code);
	}
	
	public List<Integer> getErrorCodeList() {
		return errorCodeList;
	}
	
	public boolean hasErreur() {
		return !errorCodeList.isEmpty();
	}
}
