package com.my.service;

import android.os.RemoteException;

import com.my.service.IPerson.Stub;

public class IPersonImpl extends Stub {

	String age;
	String name;

	@Override
	public void setAge(String age) throws RemoteException {

		this.age = age;

	}

	@Override
	public void setName(String name) throws RemoteException {
		this.name = name;

	}

	@Override
	public String display() throws RemoteException {

		return "name" + name + "  " + "age" + age;
	}

}
