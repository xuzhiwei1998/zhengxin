/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.particulargl.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 10-26-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class ParticularglForwardURLAC extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
    request.getSession().setAttribute(ParticularglTaShowAC.PAGINATION_KEY, null);
	  request.getSession().setAttribute(ParticularglTbShowAC.PAGINATION_KEY, null);
    request.getSession().setAttribute(ParticularglTcShowAC.PAGINATION_KEY, null);
		return mapping.findForward("particularglTaShowAC");
	}
}