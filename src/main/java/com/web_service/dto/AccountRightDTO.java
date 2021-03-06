package com.web_service.dto;

public class AccountRightDTO extends AbstractDTO<AccountRightDTO>{
	private Long accountId;
	private Long[] rightIds;
	private AccountDTO account;
	private RightDTO right;
	private String rightOption;
	
	
	public String getRightOption() {
		return rightOption;
	}
	
	public void setRightOption(String rightOption) {
		this.rightOption = rightOption;
	}
	public void setRightIds(Long[] rightIds) {
		this.rightIds = rightIds;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Long[] getRightIds() {
		return rightIds;
	}
	public void setRightId(Long[] rightIds) {
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
