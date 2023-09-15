package org.enchere.eni.c;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	private List<Integer> errorCodeList = new ArrayList<Integer>();

	public BusinessException() {
		super();
	}

	public BusinessException(int code) {
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
