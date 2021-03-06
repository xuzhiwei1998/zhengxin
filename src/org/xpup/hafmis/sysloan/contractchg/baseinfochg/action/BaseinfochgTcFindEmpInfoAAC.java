/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysloan.contractchg.baseinfochg.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sysloan.contractchg.baseinfochg.bsinterface.IBaseinfochgBS;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTbNewAF;

/** 
 * MyEclipse Struts
 * Creation date: 10-10-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class BaseinfochgTcFindEmpInfoAAC extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    
    String empId = (String) request.getParameter("empId");
    String orgId=(String)request.getParameter("orgId");
    String contractaId=(String)request.getParameter("contractaId");
    IBaseinfochgBS baseinfochgBS = (IBaseinfochgBS) BSUtils.getBusinessService("baseinfochgBS", this, mapping.getModuleConfig());
    try {
      LoanapplyTbNewAF loanapplytbnewAF=baseinfochgBS.findAssistantBorrowerInfo(empId,orgId,contractaId);
       String text = "";
     
      text = "displayIn('" +loanapplytbnewAF.getEmpId() + "','"
      + loanapplytbnewAF.getName() + "','"
      + loanapplytbnewAF.getRelation() + "','"
      + loanapplytbnewAF.getSex() + "','"
      + loanapplytbnewAF.getCardKind() + "','"
      + loanapplytbnewAF.getCardNum() + "','"
      + loanapplytbnewAF.getBirthday() + "','"
      + loanapplytbnewAF.getNation() + "','"
      + loanapplytbnewAF.getNativePlace() + "','"
      + loanapplytbnewAF.getBusiness() + "','"
      + loanapplytbnewAF.getTitle() + "','"
      + loanapplytbnewAF.getMarriageSt() + "','"
      + loanapplytbnewAF.getDegree() + "','"
      + loanapplytbnewAF.getHomeAddr() + "','"
      + loanapplytbnewAF.getHomeMail() + "','"
      + loanapplytbnewAF.getHomeMobile() + "','"
      + loanapplytbnewAF.getHouseTel() + "','"
      + loanapplytbnewAF.getOrgId() + "','"
      + loanapplytbnewAF.getOrgName() + "','"
      + loanapplytbnewAF.getOrgTel() + "','"
      + loanapplytbnewAF.getOrgAddr() + "','"
      + loanapplytbnewAF.getOrgMail() + "','"
      + loanapplytbnewAF.getAccBlnce() + "','"
      + loanapplytbnewAF.getMonthSalary() + "','"
      + loanapplytbnewAF.getMonthPay() + "','"
      + loanapplytbnewAF.getEmpSt() + "','"
      + loanapplytbnewAF.getBgnDate() + "','"
      + loanapplytbnewAF.getBgnDate() + "','"
      + loanapplytbnewAF.getSexhidden() + "','"
      + loanapplytbnewAF.getCardKindhidden()+ "')";
      response.getWriter().write(text);
      response.getWriter().close();
      
    } catch (BusinessException e) {
      String text = "";
      text = "displayInerror('"+e.getLocalizedMessage().toString()+"')";
      try {
        response.getWriter().write(text);
        response.getWriter().close();
      } catch (IOException e1) {
        e1.printStackTrace();
      }
      
    }catch(Exception be){
      be.printStackTrace();
    }
    return null;
  }
}