package com.web_service.dto;

public class AccountRightDTO extends AbstractDTO<AccountRightDTO>{
	private long accountId;
	private long[] rightIds;
	private AccountDTO account;
	private RightDTO right;
	
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public long[] getRightIds() {
		return rightIds;
	}
	public void setRightId(long[] rightIds) {
		this.rightIds = rightIds;
	}
	public AccountDTO getAccount() {
		return account;
	}
	public void setAccount(AccountDTO account) {
		this.account = account;
	}
	public RightDTO getRight() {
		return right;
	}
	public void setRight(RightDTO right) {
		this.right = right;
	}
}
