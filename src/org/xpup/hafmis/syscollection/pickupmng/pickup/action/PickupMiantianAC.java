/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.syscollection.pickupmng.pickup.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jcifs.smb.PictureUpload;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.PickTail;
import org.xpup.hafmis.syscollection.pickupmng.pickup.bsinterface.IPickupBS;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickConvertAF;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickupAddAF;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickupMaintainAF;

public class PickupMiantianAC extends LookupDispatchAction {
  public ActionForward add(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    PickupMaintainAF pickupMaintainAF = (PickupMaintainAF) form;// TODO

    request.getSession().removeAttribute("special");
    request.getSession().removeAttribute("distoryPick");
    try {
      PickConvertAF convert = new PickConvertAF();
      convert.setPickupType(BusiTools.listBusiProperty(BusiConst.PICKUPTYPE));
      convert.setSomeList(BusiTools.listBusiProperty(BusiConst.SOMEPICK));
      convert.setDistoryList(BusiTools.listBusiProperty(BusiConst.DISTROYPICK));
      // ����ط�������R/ֻ����S
      request.getSession().setAttribute("pickup", convert);
      request.getSession().setAttribute("somePick", "some");
    } catch (Exception s) {
      // s.printStackTrace();
    }
    request.getSession().setAttribute("noteNumber",
        pickupMaintainAF.getNoteNumber());
    if (pickupMaintainAF.getOther_card_num() == null
        || pickupMaintainAF.getOther_card_num() == "") {
      request.getSession().setAttribute("other_card_num", "");
    } else {
      request.getSession().setAttribute("other_card_num",
          pickupMaintainAF.getOther_card_num());
    }

    request.setAttribute("result", new PickupAddAF());
    request.getSession().setAttribute("addpage", "three");

    return mapping.findForward("add");
  }

  public ActionForward exports(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = new ActionMessages();
    try {
      // System.out.println("����");
      PickupMaintainAF pickupMaintainAF = (PickupMaintainAF) form;
      IPickupBS pbs = (IPickupBS) BSUtils.getBusinessService("pickupBS", this,
          mapping.getModuleConfig());
      Integer orgId = (Integer) request.getSession().getAttribute("orgId");
      // System.out.println("expoerts:"+orgId);
      // ���IP...���������Ĺ̶�д��
      SecurityInfo sInfo = (SecurityInfo) request.getSession().getAttribute(
          "SecurityInfo");
      String ip = sInfo.getUserIp();
      List list = pbs.getExportData(orgId.intValue(), ip, sInfo.getUserName());
      /*
       * if(list!=null && list.isEmpty()){//������Ա�㵼����ʱ��,����û������('���ǲ�����û������
       * ����˵��..') messages.add(ActionMessages.GLOBAL_MESSAGE,new
       * ActionMessage("���ݿ���û�����ݿ��Ե���",false)); saveErrors(request, messages);
       * PickupGetCompanyIdAF af = new PickupGetCompanyIdAF();
       * request.setAttribute("af", af); return new
       * ActionForward("/pickup_transactionlist.jsp"); }
       */
      request.getSession().setAttribute("explist", list);// β����ֵ......��Ϊ����β�����Ե�����ͷ��
      // EXP_NAME��ֵ�����Ǹ�xml�ļ�����ֵ
      response.sendRedirect(request.getContextPath()
          + "/ExportServlet?ISCANSHU=false&EXP_NAME=pickup_exp");
      return null;// ����д..��Ϊ����Ƿ��͵�...
    } catch (Exception s) {
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
          "���ִ���,����ʧ��", false));
    }
    return null;
  }

  public ActionForward imports(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    // System.out.println("����");
    PickupMaintainAF pickupMaintainAF = (PickupMaintainAF) form;
    System.out.println("pickupMaintainAF.getOther_card_num()="
        + pickupMaintainAF.getOther_card_num());
    request.getSession().setAttribute("noteNumber",
        pickupMaintainAF.getNoteNumber());
    if (pickupMaintainAF.getOther_card_num() == null
        || pickupMaintainAF.getOther_card_num() == "") {
      request.getSession().setAttribute("other_card_num", "");
    } else {
      request.getSession().setAttribute("other_card_num",
          pickupMaintainAF.getOther_card_num());
    }
    return new ActionForward("/pick_import.jsp");
  }

  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    PickupMaintainAF pickupMaintainAF = (PickupMaintainAF) form;// TODO
    // Auto-generated
    // method stubt
    ActionMessages messages = null;
    try {
      Pagination paginaction = (Pagination) request.getSession().getAttribute(
          PickupShowAC.PAGINACTION_KEY);
      IPickupBS pbs = (IPickupBS) BSUtils.getBusinessService("pickupBS", this,
          mapping.getModuleConfig());
      // String empId = request.getParameter("eemmppid");//���Ҫɾ����ְ��id;
      String empId = request.getParameter("eeemmmpppid");// ���Ҫɾ����ְ��id;
      String headId = pickupMaintainAF.getTailHeadId();// ��ô�β����Ӧͷ����id
      // ��idһ������.. ���list��������������һ����¼
      List list = pbs.findDataByHeadId(Integer.parseInt(headId));
      String result = "ɾ��һ����";
      if (list.size() == 1) {// ���ֻʣ��һ����¼��ʱ��
        result = "ɾ��ͷβ��";
        paginaction.setNrOfElements(0);
      }
      SecurityInfo sInfo = (SecurityInfo) request.getSession().getAttribute(
          "SecurityInfo");
      String ip = sInfo.getUserIp();
      pbs.deleteEmployee(Integer.parseInt(headId), Integer.parseInt(empId),
          result, ip, sInfo.getUserName(), sInfo);
    } catch (BusinessException e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }
    return new ActionForward("/pickupShowAC.do");
  }

  public ActionForward allDelete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    PickupMaintainAF pickupMaintainAF = (PickupMaintainAF) form;// TODO
    // Auto-generated
    // method stub
    ActionMessages messages = null;
    try {
      IPickupBS pbs = (IPickupBS) BSUtils.getBusinessService("pickupBS", this,
          mapping.getModuleConfig());
      String headId = pickupMaintainAF.getTailHeadId();
      SecurityInfo sInfo = (SecurityInfo) request.getSession().getAttribute(
          "SecurityInfo");
      String ip = sInfo.getUserIp();
      pbs.allDelete(Integer.parseInt(headId), ip, sInfo.getUserName(), sInfo);
      Pagination paginaction = (Pagination) request.getSession().getAttribute(
          PickupShowAC.PAGINACTION_KEY);
      paginaction.setNrOfElements(0);
    } catch (BusinessException be) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(be
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception s) {
      PickTail t = null;
      s.printStackTrace();
    }
    return new ActionForward("/pickupShowAC.do");
  }

  public ActionForward overPickup(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    PickupMaintainAF pickupMaintainAF = (PickupMaintainAF) form;
    String notenum = request.getParameter("notenum");
    ActionMessages messages = new ActionMessages();
    try {
      // System.out.println("���������ȡ�ķ���................");
      String report = request.getParameter("report");
      Integer orgId = (Integer) request.getSession().getAttribute("orgId");
      // System.out.println("report->"+report);
      IPickupBS pbs = (IPickupBS) BSUtils.getBusinessService("pickupBS", this,
          mapping.getModuleConfig());
      String headId = null;
      try {
        headId = pbs.findPickHeadSOneByOrgId(orgId.intValue());// ��仰�������overPick����������ϱ�..��Ȼ�����ȡ�Ժ���п��ܰ�״̬Ϊ1�ĳ�״̬Ϊ3����..��ô�����ݿ��вⲻ����..�����nullָ���쳣
      } catch (Exception e) {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
            "���������ȡ���ñ�ҵ���Ѿ������ȡ��", false));
        saveErrors(request, messages);
        return new ActionForward("/pickupShowAC.do");
      }
      SecurityInfo s = (SecurityInfo) request.getSession().getAttribute(
          "SecurityInfo");
      pbs.overPick(orgId.intValue(), s.getUserIp(), s.getUserName(), s
          .getUserInfo().getBizDate(), notenum, s);

      Pagination paginaction = (Pagination) request.getSession().getAttribute(
          PickupShowAC.PAGINACTION_KEY);
      paginaction.setNrOfElements(0);

      // if(report.equals("print")){
      // // System.out.println("...��ӡ..");
      // // System.out.println("head--->"+headId);
      // NameAF nameAF = new NameAF();
      // NameAF othernameAF = new NameAF();
      // nameAF = pbs.findName(orgId.toString());
      // // othernameAF = pbs.findpickTail(orgId.toString());
      //  
      // List list = pbs.getPrintAllEmployeeList(headId);//���Ҫ��ӡ�ļ���..
      // // System.out.println("��ӡ�ļ��ϳ�����......."+list.size());
      //
      // PickTail pick=null;
      // if(list.size()>0){
      // pick = (PickTail) list.get(0);
      // }
      // ApplyBookDTO apply = new ApplyBookDTO();//��ȡƾ֤..
      // apply.setSOrgName(nameAF.getOrgName());//�տλ����
      // apply.setSOrgNumber(nameAF.getPayBankNum());//�տλ�˺�(��н����)
      // apply.setSOrgBank(nameAF.getPayBank());//�տλ����
      // apply.setFOrgName(nameAF.getOrganizatinUnitName());//���λ����(����)
      // apply.setFOrgNumber(" ");//���λ�˺�(����)
      // apply.setFOrgBank(nameAF.getCentercollBankName());//���λ(���Ĺ鼯����)
      // apply.setPickBalance(pick.getPickHead().getPickBalance().add(pick.getPickHead().getDistroyInterest()).divide(new
      // BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));//����ת������λС��
      // request.setAttribute("apply", apply);
      // request.setAttribute("employee", list);
      // request.setAttribute("PRINT", "pickupShowAC.do");
      //          
      // // wuht
      //
      //          
      // 
      //      
      //     
      // ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
      // .getBusinessService("loanDocNumDesignBS", this,
      // mapping.getModuleConfig());
      // String userName="";
      //        
      // SecurityInfo
      // securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      //          
      // try {
      // String name = loanDocNumDesignBS .getNamePara();
      //
      // if (name.equals("1")) {
      // userName = securityInfo.getUserName();
      // } else {
      // userName = securityInfo.getRealName();
      // }
      //
      // } catch (Exception e) {
      // // TODO Auto-generated catch block
      // e.printStackTrace();
      // }
      // String collectionBankName="";
      //       
      // if(orgId!=null && !orgId.equals("")){
      // collectionBankName=pbs.queryCollectionBankNameById(orgId.toString(),
      // securityInfo);
      // }
      // String bizDate = securityInfo.getUserInfo().getBizDate();
      // request.setAttribute("userName", userName);
      // request.setAttribute("bizDate", bizDate);
      // request.setAttribute("collectionBankName", collectionBankName);
      // //wuht
      //          
      //          
      // // request.getSession().removeAttribute("orgidCollnbank");
      // // request.getSession().setAttribute("orgidCollnbank",
      // orgId.toString());
      // // System.out.println("-----------++++++++++++++++++++++++++++-----");
      // //��ô�ӡƾ֤
      // return new ActionForward("/printDocAndList.jsp");
      // }

    } catch (Exception s) {
      s.printStackTrace();
    }
    return new ActionForward("/pickupShowAC.do");
  }

  public ActionForward referringdata(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    PickupMaintainAF pickupMaintainAF = (PickupMaintainAF) form;
    String headId = pickupMaintainAF.getTailHeadId();
    IPickupBS pbs = (IPickupBS) BSUtils.getBusinessService("pickupBS", this,
        mapping.getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    ActionMessages messages = null;
    String temp_p = "1";// ����
    try {
      pbs.referringData(headId, securityInfo, temp_p);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("�ύ�ɹ�",
          false));
      saveErrors(request, messages);
    } catch (BusinessException be) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(be
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }
    return new ActionForward("/pickupShowAC.do");
  }

  public ActionForward pprovaldata(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {// �����ύ
    PickupMaintainAF pickupMaintainAF = (PickupMaintainAF) form;
    String headId = pickupMaintainAF.getTailHeadId();
    IPickupBS pbs = (IPickupBS) BSUtils.getBusinessService("pickupBS", this,
        mapping.getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    ActionMessages messages = null;
    String temp_p = "1";// ����
    try {
      pbs.pprovalData(headId, securityInfo, temp_p);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("�����ύ�ɹ�",
          false));
      saveErrors(request, messages);
    } catch (BusinessException be) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(be
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }
    return new ActionForward("/pickupShowAC.do");
  }

  public ActionForward pickupdata(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    String headId = (String) request.getParameter("id");
    IPickupBS pbs = (IPickupBS) BSUtils.getBusinessService("pickupBS", this,
        mapping.getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    ActionMessages messages = null;
    try {
      pbs.pickUpData(headId, securityInfo);
    } catch (BusinessException be) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(be
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }
    return new ActionForward("/pickupShowAC.do");
  }

  public ActionForward scan(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    PictureUpload pu = new PictureUpload();
    String path;
    ActionMessages messages = null;

    try {
      PickupMaintainAF pickupMaintainAF = (PickupMaintainAF) form;// TODO

      String headId = pickupMaintainAF.getTailHeadId();

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String pzempid = (String) request.getParameter("eeemmmpppid");// ���Ҫɾ����ְ��id;
      String serverPath = BusiConst.UPLOAD_SERVER_PATH;
      path = pu.upload(securityInfo.getUserInfo().getUserIp(), "picture",
          serverPath);
      pu.delete(securityInfo.getUserInfo().getUserIp(), "picture");
      IPickupBS pbs = (IPickupBS) BSUtils.getBusinessService("pickupBS", this,
          mapping.getModuleConfig());
      pbs.updatePickHead(headId, pzempid, path);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("ɨ�����",
          false));
      saveErrors(request, messages);
    } catch (IOException e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("ɨ��ʧ��",
          false));
      saveErrors(request, messages);
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return new ActionForward("/pickupShowAC.do");
  }

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.imports", "imports");
    map.put("button.exports", "exports");
    map.put("button.add", "add");
    map.put("button.delete", "delete");
    map.put("button.deleteall", "allDelete");
    map.put("button.over.pickup", "overPickup");
    map.put("button.referring.data", "referringdata");
    map.put("button.pproval.data", "pprovaldata");
    map.put("button.pickup.data", "pickupdata");
    map.put("button.scan", "scan");
    return map;
  }
}