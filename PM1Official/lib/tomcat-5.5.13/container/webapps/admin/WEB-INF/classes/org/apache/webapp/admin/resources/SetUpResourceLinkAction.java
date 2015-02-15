/*
 * Copyright 2002,2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.webapp.admin.resources;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Locale;
import javax.management.Attribute;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.QueryExp;
import javax.management.Query;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.JMException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanInfo;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.webapp.admin.ApplicationServlet;


/**
 * <p>Implementation of <strong>Action</strong> that sets up and stashes
 * a <code>ResourceLinkForm</code> bean in request scope.  The form bean will have
 * a null <code>objectName</code> property if this form represents a ResourceLink
 * being added, or a non-null value for an existing ResourceLink.</p>
 *
 * @author Amy Roh
 * @version $Revision: 303390 $ $Date: 2004-10-18 02:37:56 -0400 (Mon, 18 Oct 2004) $
 * @since 4.1
 */

public final class SetUpResourceLinkAction extends Action {

    // ----------------------------------------------------- Instance Variables


    /**
     * The MBeanServer we will be interacting with.
     */
    private MBeanServer mserver = null;


    // --------------------------------------------------------- Public Methods


    /**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     * Return an <code>ActionForward</code> instance describing where and how
     * control should be forwarded, or <code>null</code> if the response has
     * already been completed.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws IOException, ServletException {

        // Look up the components we will be using as needed
        if (mserver == null) {
            mserver = ((ApplicationServlet) getServlet()).getServer();
        }
        MessageResources resources = getResources(request);
        HttpSession session = request.getSession();
        Locale locale = getLocale(request);
        
        // Set up the form bean based on the creating or editing state
        String objectName = request.getParameter("objectName");
        String resourcetype = request.getParameter("resourcetype");
        String path = request.getParameter("path");
        String host = request.getParameter("host");
        String domain = request.getParameter("domain");
        
        ResourceLinkForm resourceLinkForm = new ResourceLinkForm();       
        resourceLinkForm.setResourcetype(resourcetype);
        resourceLinkForm.setPath(path);
        resourceLinkForm.setHost(host);
        resourceLinkForm.setDomain(domain);
            
        if (objectName == null) {
            resourceLinkForm.setNodeLabel
                (resources.getMessage(locale, "resources.actions.resourcelk.create"));
            resourceLinkForm.setObjectName(null);
            
        } else {
            resourceLinkForm.setNodeLabel
                (resources.getMessage(locale, "resources.actions.resourcelk.edit"));
            resourceLinkForm.setObjectName(objectName);
            
            String attribute = null;
            try {
                ObjectName oname = new ObjectName(objectName);
                attribute = "name";
                resourceLinkForm.setName
                    ((String) mserver.getAttribute(oname, attribute));
                attribute = "global";
                resourceLinkForm.setGlobal
                    ((String) mserver.getAttribute(oname, attribute));
                attribute = "type";
                resourceLinkForm.setType
                    ((String) mserver.getAttribute(oname, attribute));
                
            } catch (Exception e) {
                getServlet().log
                    (resources.getMessage(locale,
                        "users.error.attribute.get", attribute), e);
                response.sendError
                    (HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                     resources.getMessage
                         (locale, "users.error.attribute.get", attribute));
                return (null);
            } 
        }
            
        // Stash the form bean and forward to the display page
        saveToken(request);
        request.setAttribute("resourceLinkForm", resourceLinkForm);
        return (mapping.findForward("ResourceLink"));

    }
}
