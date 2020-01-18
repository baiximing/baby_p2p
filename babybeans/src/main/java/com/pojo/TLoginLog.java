package com.pojo;


import java.util.Date;

public class TLoginLog {

  private long id;
  private String ip;
  private Date loginTime;
  private String username;
  private long accountType;
  private long loginResult;
  private Date createTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }


  public Date getLoginTime() {
    return loginTime;
  }

  public void setLoginTime(Date loginTime) {
    this.loginTime = loginTime;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public long getAccountType() {
    return accountType;
  }

  public void setAccountType(long accountType) {
    this.accountType = accountType;
  }


  public long getLoginResult() {
    return loginResult;
  }

  public void setLoginResult(long loginResult) {
    this.loginResult = loginResult;
  }


  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

}
