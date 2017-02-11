package com.cjk.fighting.model;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable{
    private Integer id;

    private Integer userid;

    private String content;

    private Date publishtime;

    private Integer cansee;
    
    private Users user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public Integer getCansee() {
        return cansee;
    }

    public void setCansee(Integer cansee) {
        this.cansee = cansee;
    }

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
    
    
}