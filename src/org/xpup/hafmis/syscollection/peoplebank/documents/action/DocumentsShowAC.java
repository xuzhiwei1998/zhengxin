package org.xpup.hafmis.syscollection.peoplebank.documents.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DocumentsShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.peoplebank.documents.action.DocumentsShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    return mapping.findForward("to_documents_show");
  }

}
