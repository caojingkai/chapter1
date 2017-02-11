package com.cjk.fighting.springInject;

import org.springframework.beans.factory.FactoryBean;

public class ExtendsFactoryBean implements FactoryBean{

	public Object getObject() throws Exception {
		// TODO Auto-generated method stub
		return new InnerClass();
	}

	public Class getObjectType() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public class InnerClass{
		public String str = "wo shi ge InnerClass";

		public String getStr() {
			return str;
		}

		public void setStr(String str) {
			this.str = str;
		}
		
		
	}

}
