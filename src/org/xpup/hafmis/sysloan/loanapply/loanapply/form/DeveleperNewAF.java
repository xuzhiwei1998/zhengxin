/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysloan.loanapply.loanapply.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.CriterionsAF;

public class DeveleperNewAF extends CriterionsAF {

  private String develpername;

  private List list = new ArrayList();

  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  public void reset(ActionMapping mapping, HttpServletRequest request) {
    // TODO Auto-generated method stub
  }

  /**
   * Returns the develpername.
   * 
   * @return String
   */
  public String getDevelpername() {
    return develpername;
  }

  /**
   * Set the develpername.
   * 
   * @param develpername The develpername to set
   */
  public void setDevelpername(String develpername) {
    this.develpername = develpername;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }
}