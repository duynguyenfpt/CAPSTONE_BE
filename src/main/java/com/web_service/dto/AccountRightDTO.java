package com.web_service.dto;

public class AccountRightDTO extends AbstractDTO<AccountRightDTO>{
	private long accountId;
	private long rightId;
	private AccountDTO account;
	private RightDTO right;
	
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public long getRightId() {
		return rightId;
	}
	public void setRightId(long rightId) {
		this.rightId = rightId;
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
