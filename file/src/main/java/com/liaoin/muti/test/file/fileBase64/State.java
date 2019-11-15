package com.liaoin.muti.test.file.fileBase64;

public interface State {
	
	public boolean isSuccess();

	public void putInfo(String name, String val);

	public void putInfo(String name, long val);

	public String toJSONString();

}