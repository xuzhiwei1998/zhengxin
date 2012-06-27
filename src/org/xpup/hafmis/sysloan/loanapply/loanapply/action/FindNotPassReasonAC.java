/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysloan.loanapply.loanapply.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sysloan.loanapply.loanapply.bsinterface.ILoanapplyBS;

/** 
 * MyEclipse Struts
 * Creation date: 10-06-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class FindNotPassReasonAC extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	  String  contractId=(String)request.getParameter("contractId");
    String type=(String)request.getParameter("type"); 
    String reason="";
    ILoanapplyBS loanapplyBS = (ILoanapplyBS) BSUtils.getBusinessService(
        "loanapplyBS", this, mapping.getModuleConfig());
    try {
       reason=loanapplyBS.findNotPassReasons(contractId,type);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    request.setAttribute("reason", reason);
		return mapping.findForward("showreason_show");
	}
}