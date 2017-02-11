package com.cjk.fighting.interceptor.mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cjk.fighting.utils.Page;
import com.cjk.fighting.utils.ReRowBounds;

import static java.lang.System.out;



@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PagePlugin implements Interceptor {

    protected ObjectFactory objectFactory;
    
    protected ObjectWrapperFactory objectWrapperFactory;
	private Properties properties;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PagePlugin.class);

	
	
	public Object intercept(Invocation arg0) throws Throwable {
		// TODO Auto-generated method stub
		LOGGER.debug("执行分页插件:");
		objectFactory = new DefaultObjectFactory();
        objectWrapperFactory = new DefaultObjectWrapperFactory();
        
		StatementHandler statementHandler = (StatementHandler)arg0.getTarget();
		MetaObject metaObj = MetaObject.forObject(statementHandler, objectFactory, objectWrapperFactory);
		
		while(metaObj.hasGetter("h"))
		{
			if (metaObj.hasGetter("h"))
			{
				metaObj = MetaObject.forObject(metaObj.getValue("h"), objectFactory, objectWrapperFactory);
				Object obj = metaObj.getValue("target");
				metaObj = MetaObject.forObject(obj, objectFactory, objectWrapperFactory);
			}
		}
		Configuration configuration = (Configuration)metaObj.getValue("delegate.configuration");
		//获取配置数据库类型
		String dialect = configuration.getVariables().getProperty("dialect");
		String isPageEnd = configuration.getVariables().getProperty("isPageEnd");
		if(StringUtils.isBlank(isPageEnd))
		{
			isPageEnd = ".*Page$";
		}
		//获得sql id,判断是否为分页
		MappedStatement mappedStatement = (MappedStatement)metaObj.getValue("delegate.mappedStatement");
		BoundSql boundSql = (BoundSql)metaObj.getValue("delegate.boundSql");
		if (mappedStatement.getId().matches(isPageEnd))
		{
			Object parameterObject = boundSql.getParameterObject();
			
			
			if (null == parameterObject || null == metaObj.getValue("delegate.boundSql.parameterObject.page") )
			{
				//该方式重写的RowBounds 是将page 放到RowBounds 中。
				
				ReRowBounds reRowBounds= (ReRowBounds)metaObj.getValue("delegate.rowBounds");
				Page page = reRowBounds.getPage();
				page.setBeginSize(reRowBounds.getOffset());
				page.setEndSize(reRowBounds.getOffset()+ reRowBounds.getLimit());
				
				//清除rowBounds
				metaObj.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
				metaObj.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
				
				String sql = boundSql.getSql();
			    String busql = bulidPageSql(sql, page);
			    metaObj.setValue("delegate.boundSql.sql", busql);
			    
			    Connection connection = (Connection)arg0.getArgs()[0];
			    
				// 重设分页参数里的总页数等
				setPageParamer(connection,mappedStatement, page, sql, boundSql);
			    
			}
			else
			{
				//该方式是将page 当做参数传进来的
				
				//清除rowBounds
				metaObj.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
				metaObj.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
				
				Page page = (Page)metaObj.getValue("delegate.boundSql.parameterObject.page");
				String sql = boundSql.getSql();
			    String busql = bulidPageSql(sql, page);
			    metaObj.setValue("delegate.boundSql.sql", busql);
			    
			    Connection connection = (Connection)arg0.getArgs()[0];
			    
			   // 重设分页参数里的总页数等
			    setPageParamer(connection,mappedStatement, page, sql, boundSql);
			}
			
		
		
		}
		
		return arg0.proceed();
	}

	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		if (target instanceof StatementHandler)
		{
			return Plugin.wrap(target, this);
		}
		else
		{
			return target;
		}
		
	}

	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub
		this.properties = arg0;
	}
	
	  public Properties getProperties() {
		    return properties;
		  }

	  public  String bulidPageSql(String sql, Page page)
	  {
		  StringBuffer sb = new StringBuffer("select * from ( select ss.*, rownum as rr from (");
		  sb.append(sql);
		  sb.append(") ss where rownum <= ").append(page.getEndSize());
		  sb.append(") where rr >= ").append(page.getBeginSize());
		  LOGGER.debug("cjksql:"+sb.toString());
		  return sb.toString();
	  }
	  
	  //使用ParamerHandler 填充参数
	  public void setPageParamer(Connection connection,MappedStatement mappedStatement, Page page, String sql, BoundSql boundSql)
	  {
		 String sqlCount = "select count(*) as total from (" + sql +")";
		  
		  PreparedStatement countStemt = null;
		  ResultSet rs = null;
		  
		  try {
			countStemt = connection.prepareStatement(sqlCount);
			
			 //创建查询总数的BoundSql, 因为一个BoundSql 对应一个sql，所以要从新建立它
			  BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), sqlCount, boundSql.getParameterMappings(), boundSql.getParameterObject());
			  
			  //创建ParameterHandler
			  DefaultParameterHandler parameterHandler  = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), newBoundSql);
			  
			  parameterHandler.setParameters(countStemt);
			  
			  rs = countStemt.executeQuery(); 
			  
			  int tatolCount = 0;
			  if (rs.next())
			  {
				  tatolCount = rs.getInt(1);
			  }
			  out.println(tatolCount);
			  page.setCount(tatolCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		  
		  
		  
		  
		  
	  }
}
