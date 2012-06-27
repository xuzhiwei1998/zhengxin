/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysloan.dataready.assistantorg.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.assistantorg.bsinterface.INotarialBS;
import org.xpup.hafmis.sysloan.dataready.assistantorg.dto.NotarialAFDTO;
import org.xpup.hafmis.sysloan.dataready.assistantorg.form.NotarialAF;


/**Doclet definition:
 * @struts.action validate="true"
 */
public class NotarialAddUpdateAC extends LookupDispatchAction {
  public static final String PAGINATION_KEY =
    "org.xpup.hafmis.sysloan.dataready.assistantorg.action.SurrogateShowAC";
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.sure", "sure");
    map.put("button.scan", "scan");
    map.put("button.back", "back");
    return map;
  }
  
  public ActionForward sure(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages message=null;
    NotarialAF AF = (NotarialAF) form;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    String error="����ʧ��!";
    
    NotarialAFDTO notarialAFDTO = null;
    
    try{
      INotarialBS notarialBS=(INotarialBS)BSUtils
      .getBusinessService("notarialBS", this, mapping.getModuleConfig());
      if(AF.getType().equals("����"))
      {
        //���и� ͷ
        notarialAFDTO = new NotarialAFDTO();
        BeanUtils.copyProperties(notarialAFDTO, AF);
        notarialBS.insertNotarialList(notarialAFDTO,securityInfo);
        //���и� β
        
        error="���ӳɹ�!";
        return mapping.findForward("notarialShowAC"); //���ӳɹ�
      }
      if(AF.getType().equals("�޸�"))
      {  
        //���и� ͷ
        notarialAFDTO = new NotarialAFDTO();
        BeanUtils.copyProperties(notarialAFDTO, AF);
        notarialBS.updateNotarial(notarialAFDTO,securityInfo);
        //���и� β
        
        error="�޸ĳɹ�!";
        return mapping.findForward("notarialShowAC");
      }
    }
    catch(Exception e)
    {
      error="����ʧ��";
      e.printStackTrace();  
    }finally{
      message= new ActionMessages();
      message.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(error,false));
      saveErrors(request,message);
    }
    return mapping.findForward("notarialShowAC"); //���ӳɹ�
  }
  
  public ActionForward scan(ActionMapping mapping, ActionForm form,      
      HttpServletRequest request, HttpServletResponse response) {
//    ActionMessages message=null;

    return mapping.findForward("notarialShowAC"); //���ӳɹ�
  }
  public ActionForward back(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    return mapping.findForward("notarialShowAC");
  }
}