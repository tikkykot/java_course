package ru.stqa.mantis.pft.model;

import biz.futureware.mantis.rpc.soap.client.ObjectRef;

public class Project {
  private int id;
  private String name;
  private ObjectRef refs[];

  public int getId() {
    return id;
  }

  public Project withId(int id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Project withName(String name) {
    this.name = name;
    return this;
  }

  public ObjectRef[] getRefs() {
    return refs;
  }

  public Project setRefs(ObjectRef[] refs) {
    this.refs = refs;
    return this;
  }
}
