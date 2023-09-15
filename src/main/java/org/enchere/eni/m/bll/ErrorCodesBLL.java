package org.enchere.eni.m.bll;

public abstract class ErrorCodesBLL {

	public final static int ALIAS_NOT_UNIQUE_ERROR = 20_000;
	public final static int EMAIL_NOT_UNIQUE_ERROR = 20_001;
	public final static int ALIAS_EMAIL_NOT_CORRESPONDING = 20_003;
	public final static int USER_DOESNT_EXIST = 20_004;

	public final static int FORMAT_BID_START_DATE_ERROR = 30_001;
	public final static int FORMAT_BID_END_DATE_ERROR = 30_002;
	public final static int BID_END_DATE_ERROR = 30_003;

	public final static int UNSUFFICIENT_CREDIT_AMOUNT = 50_000;

	public final static int ACCOUNT_DELETION_PENDING_SELLS = 60_000;
	public final static int ACCOUNT_DELETION_WINNING_BIDS = 60_001;

}
