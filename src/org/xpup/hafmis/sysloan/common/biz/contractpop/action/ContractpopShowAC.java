package org.xpup.hafmis.sysloan.common.biz.contractpop.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.biz.orgpop.bsinterface.IOrgpopBS;
import org.xpup.hafmis.sysloan.common.biz.contractpop.bsinterface.IContractpopBS;

/**
 * 
 * @author yuqf
 *
 */
public class ContractpopShowAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.common.biz.contractpop.action.ContractpopShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    ActionMessages messages =null;
//    String [] status = null;
    List list = new ArrayList();
    try{
      SecurityInfo securityInfo =(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String statuss=request.getParameter("status");
      String indexs=request.getParameter("indexs");
      String afterLoanaccord = request.getParameter("afterLoanaccord");
      if(indexs != null){
        request.getSession().setAttribute("indexs", indexs);
      }
      if(statuss==null){
        statuss = request.getSession().getAttribute("statuss").toString();
      }
      if(afterLoanaccord == null){
        afterLoanaccord = request.getSession().getAttribute("afterLoanaccord").toString();
      }
      
      List status = new ArrayList();
      StringTokenizer str = new StringTokenizer(statuss,",");
      while (str.hasMoreTokens()) {
        String temp_str = str.nextToken();
        status.add(temp_str);
      }
//      status = new String[statuss.length()];
//      for(int i=0;i<statuss.length();i++){
//        status[i]=statuss.substring(i,i+1);
//      }
      IContractpopBS contractpopBS = (IContractpopBS) BSUtils
      .getBusinessService("contractpopBS", this, mapping.getModuleConfig());
      Pagination pagination = getPagination(PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);
      request.getSession().setAttribute("statuss",statuss);
      request.getSession().setAttribute("afterLoanaccord", afterLoanaccord);
      pagination.getQueryCriterions().put("status", status);  
      pagination.getQueryCriterions().put("afterLoanaccord", afterLoanaccord);
      list = contractpopBS.findContractpopList(pagination, securityInfo);
      request.getSession().setAttribute("contractpopList", list);

    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("没有您要查询的信息！",
          false));
      saveErrors(request, messages);
  }catch(Exception e){
    e.printStackTrace();
  }
    return mapping.findForward("to_contractpop_list");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "p110.contract_id", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }   

    return pagination;
  }
}
