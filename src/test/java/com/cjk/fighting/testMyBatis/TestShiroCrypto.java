package com.cjk.fighting.testMyBatis;
import static java.lang.System.out;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.crypto.hash.format.DefaultHashFormatFactory;
import org.apache.shiro.crypto.hash.format.HashFormat;
import org.apache.shiro.crypto.hash.format.ParsableHashFormat;
import org.apache.shiro.crypto.hash.format.Shiro1CryptFormat;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cjk.fighting.springInject.ExtendsFactoryBean;
import com.cjk.fighting.springInject.ExtendsFactoryBean.InnerClass;

public class TestShiroCrypto {
	
	private static ApplicationContext act;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomNumberGenerator sd1 = defaultHashService();
		String sd2 = defaultHashService2();
		hashFormatFactoryGetInstance();
		//testSha512Hash();
		//testSha512Hash();
		//base64();
		//testSimpleHash();
		getShiroFilterBean ();
	}

	public static RandomNumberGenerator defaultHashService()
	{
		DefaultHashService hashService = new DefaultHashService();
		hashService.setGeneratePublicSalt(false);
		hashService.setHashAlgorithmName("SHA-256");
		hashService.setPrivateSalt(new SimpleByteSource("zhm"));
		RandomNumberGenerator random = hashService.getRandomNumberGenerator();
		
		HashRequest hashRequest =new HashRequest.Builder().setSource(new SimpleByteSource("cjk")).setSalt(new SimpleByteSource("123")).build();
		String st = hashService.computeHash(hashRequest).toHex();
		System.out.println(st);
		//showRandomNumber(random);
		return random;
	}
	
	public static String defaultHashService2()
	{
		DefaultHashService hashService = new DefaultHashService();
		
		hashService.setGeneratePublicSalt(false);
		hashService.setHashAlgorithmName("SHA-512");
		hashService.setPrivateSalt(new SimpleByteSource("zhm"));
		HashRequest hashRequest =new HashRequest.Builder().setSource(new SimpleByteSource("cjk")).setSalt(new SimpleByteSource("123")).build();
		String st=hashService.computeHash(hashRequest).toHex();
		System.out.println(st);
		return st;
	}
	
	public static void showRandomNumber(RandomNumberGenerator random)
	{
		ByteSource bytes = random.nextBytes();
		ByteSource bytes2 = random.nextBytes();
		System.out.println("random::"+bytes.toHex());
		System.out.println("random2::"+bytes2.toHex());
	}
	
	public static void testSha512Hash()
	{
		Sha512Hash sha512Hash = new Sha512Hash("cjk","123",2);
		out.println(sha512Hash.toHex());
	}
	
	public static void testPsswordServiceEncryptPassword(String userPass)
	{
		PasswordService hashService = new DefaultPasswordService();
		String str = hashService.encryptPassword(userPass);
		
		//CredentialsMatcher matcher = 
		
	}
	
	
	public static void hashFormatFactoryGetInstance()
	{
		String saved = "$shiro1$SHA-256$500000$C7NXycYJgZ0hsTzk1au3cg==$Pbpl0lDrGXD2gDVKTeva6gkzKoHP0AXHmPEPMUymR9U=";
		DefaultHashFormatFactory hashFormatFactory = new DefaultHashFormatFactory();
		HashFormat discoveredFormat = hashFormatFactory.getInstance(saved);
		if(discoveredFormat != null && (discoveredFormat instanceof ParsableHashFormat))
		{
			ParsableHashFormat parsableHashFormat = (ParsableHashFormat)discoveredFormat;
            Hash savedHash = parsableHashFormat.parse(saved);
		}
		else
		{
			out.println("fds111");	
		}
	}
	
	public static void base64()
	{
		String str ="fs";
		String stsr=Base64.encodeToString(str.getBytes());
		out.println(stsr);
	}
	
	public static void testSimpleHash()
	{
		String source ="StringMVC";
		
		Hash computed = new SimpleHash("SHA-512",ByteSource.Util.bytes(source), ByteSource.Util.bytes("salt"), 2);
		HashFormat hashFormat = new Shiro1CryptFormat();
		out.println(hashFormat.format(computed).toString());
		out.println(computed.toHex());
	}
	
	public static void TestWildcardPermissionResolver()
	{
		Permission permission = new WildcardPermissionResolver().resolvePermission("user:login");

	}
	
	public static void before() 
	{
		out.println("begin");
		act = new ClassPathXmlApplicationContext(new String[]{"spring-shiro.xml","spring-mybatis.xml"});
	}
	
	public static void getShiroFilterBean()
	{
		before();
		Object shiroFilter = act.getBean("shiroFilter");
		if (shiroFilter == null)
		{
			out.println("kong1");
		}
		
		if(shiroFilter instanceof AbstractShiroFilter)
		{
			out.println(shiroFilter.getClass());
		}
		ExtendsFactoryBean.InnerClass InnerClass = (InnerClass)act.getBean("innerClass");
		out.println(InnerClass.getStr());
	}
}
