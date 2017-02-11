package com.cjk.fighting.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Users implements Serializable{
    private int userid;

    private String username;

    private String address;

    private int grade;

    private String realname;

    private String password;

    private String phone;

    private int sex;

    private Date registertime;

    private String emil;

    private String headimg;
    
    private List<Post> postList;
    
    private String loginName;
    
    private byte[] image;
    
    private List<Role> roleList;
    
    private String userSalt;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }


    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }


    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public String getEmil() {
        return emil;
    }

    public void setEmil(String emil) {
        this.emil = emil == null ? null : emil.trim();
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg == null ? null : headimg.trim();
    }

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}
	

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	

	public String getUserSalt() {
		return userSalt;
	}

	public void setUserSalt(String userSalt) {
		this.userSalt = userSalt;
	}

	@Override
	public String toString() {
		return "Users [userid=" + userid + ", username=" + username
				+ ", address=" + address + ", grade=" + grade + ", realname="
				+ realname + ", password=" + password + ", phone=" + phone
				+ ", sex=" + sex + ", registertime=" + registertime + ", emil="
				+ emil + ", headimg=" + headimg + ", postList=" + postList +"image.length"+ image==null ? "ds" : image.length
				+ "]";
	}
    
    
    
}