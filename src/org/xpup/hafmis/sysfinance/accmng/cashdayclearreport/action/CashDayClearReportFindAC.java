package org.xpup.hafmis.sysfinance.accmng.cashdayclearreport.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTcFindDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.form.CashDayClearTcAF;

public class CashDayClearReportFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) throws Exception {
    try{
      CashDayClearTcAF cashDayClearTcAF=(CashDayClearTcAF)form;
      CashDayClearTcFindDTO cashDayClearTcFindDTO=cashDayClearTcAF.getCashDayClearTcFindDTO();
      Pagination pagination = new Pagination(0, 10, 1, "fn210.credence_id", "DESC",
          new HashMap(0));
      pagination.getQueryCriterions().put("cashDayClearTcFindDTO", cashDayClearTcFindDTO);
      String paginationKey = getPaginationKey();
      request.getSession().setAttribute(paginationKey, pagination);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("cashdayclearreport_show");
  }
  protected String getPaginationKey() {
    return CashDayClearReportShowAC.PAGINATION_KEY;
  }
}
