package com.sample;


public class Diagnostic {
  private String patient_Name;
  private String feifen;
  private String problem_Id;
  private String reply;
  private String reply_Time;
  private String appraise;
  private String acttime;

  public String getActtime() {
    return acttime;
  }

  public void setActtime(String acttime) {
    this.acttime = acttime;
  }

  public Diagnostic() {
  }

  public String getPatient_Name() {
    return patient_Name;
  }

  public void setPatient_Name(String patient_Name) {
    this.patient_Name = patient_Name;
  }

  public String getFeifen() {
    return feifen;
  }

  public void setFeifen(String feifen) {
    this.feifen = feifen;
  }

  public String getProblem_Id() {
    return problem_Id;
  }

  public void setProblem_Id(String problem_Id) {
    this.problem_Id = problem_Id;
  }

  public String getReply() {
    return reply;
  }

  public void setReply(String reply) {
    this.reply = reply;
  }

  public String getReply_Time() {
    return reply_Time;
  }

  public void setReply_Time(String reply_Time) {
    this.reply_Time = reply_Time;
  }

  public String getAppraise() {
    return appraise;
  }

  public void setAppraise(String appraise) {
    this.appraise = appraise;
  }
}
