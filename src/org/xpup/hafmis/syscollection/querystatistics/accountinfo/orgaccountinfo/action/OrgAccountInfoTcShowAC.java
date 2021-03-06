package org.xpup.hafmis.syscollection.querystatistics.accountinfo.orgaccountinfo.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.orgaccountinfo.bsinterface.IOrgAccountInfoBS;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.orgaccountinfo.dto.OrgAccountInfoTotalDTO;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.orgaccountinfo.form.OrgAccountInfoAF;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.bsinterface.IEmpOperationFlowBS;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.dto.EmpOperationFlowTotalDTO;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.form.EmpOperationFlowAF;
import org.xpup.security.common.domain.Userslogincollbank;


public class OrgAccountInfoTcShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.accountinfo.orgaccountinfo.action.OrgAccountInfoTcShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages =null;
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    try{

      OrgAccountInfoAF af = new OrgAccountInfoAF();    
      String id=(String)request.getAttribute("id");
      String mode=request.getParameter("mode");
      String opTime=(String)request.getAttribute("opTime");
      Pagination pagination = getPagination(OrgAccountInfoTcShowAC.PAGINATION_KEY, request); 
      Pagination pagination1 =(Pagination) request.getSession().getAttribute(OrgAccountInfoTaShowAC.PAGINATION_KEY); 
      pagination.getQueryCriterions().put("opDate", pagination1.getQueryCriterions().get("opDate"));
      pagination.getQueryCriterions().put("inOpDate", pagination1.getQueryCriterions().get("inOpDate"));
      if(id!=null){
        pagination.getQueryCriterions().put("id", id);
      }
      if(opTime!=null){
        pagination.getQueryCriterions().put("opTime", opTime);
      }
      mode=(String)pagination1.getQueryCriterions().get("mode");
      pagination.getQueryCriterions().put("mode", pagination1.getQueryCriterions().get("mode"));
      PaginationUtils.updatePagination(pagination, request);
      IOrgAccountInfoBS orgAccountInfoBS = (IOrgAccountInfoBS) BSUtils
      .getBusinessService("orgAccountInfoBS", this, mapping.getModuleConfig());
      List officelist = securityInfo.getOfficeList();
      List officelist1 = new ArrayList();
      OfficeDto dto = null;
      Iterator itr = officelist.iterator();     
      while (itr.hasNext()) {
        dto = (OfficeDto) itr.next();   
        officelist1.add(new org.apache.struts.util.LabelValueBean(dto.getOfficeName().toString(), dto.getOfficeCode().toString()));
      }
      List bankList = securityInfo.getCollBankList();
      List bankList1 = new ArrayList();
      Userslogincollbank bankdto = null;   
      Iterator itr1 = bankList.iterator();    
      while (itr1.hasNext()) {
        bankdto = (Userslogincollbank) itr1.next();   
        bankList1.add(new org.apache.struts.util.LabelValueBean(bankdto.getCollBankName().toString(), bankdto.getCollBankId().toString()));
      }
      request.getSession(true).setAttribute("bankList1", bankList1);
      request.getSession(true).setAttribute("officelist1", officelist1);
      List list=orgAccountInfoBS.findOrgAccountInfoByDay(pagination, securityInfo);
      OrgAccountInfoTotalDTO totaldto=orgAccountInfoBS.findOrgAccountInfoDayTotal(pagination, securityInfo);
      request.setAttribute("orgAccountInfoTotalDTO",totaldto);
      af.setMode(pagination1.getQueryCriterions().get("mode")+"");
      request.setAttribute("orgAccountInfoAF",af);
      request.setAttribute("LIST",list);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "to_showOrgAccountInfo";
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      m.put("type", "0");
      pagination = new Pagination(0, 10, 1, "orgHAFAccountFlow.id", "DESC",
          m);
      request.getSession().setAttribute(paginationKey, pagination);
    }   

    return pagination;
  }
  
}


