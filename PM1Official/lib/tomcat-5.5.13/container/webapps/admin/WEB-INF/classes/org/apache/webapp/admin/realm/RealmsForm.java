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

package org.apache.webapp.admin.realm;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


/**
 * Form bean for deleting realms.
 *
 * @author Manveen Kaur
 * @version $Revision: 302726 $ $Date: 2004-02-27 09:59:07 -0500 (Fri, 27 Feb 2004) $
 */

public class RealmsForm extends ActionForm {


    // ------------------------------------------------------------- Properties


    /**
     * The object names of the realms to be deleted.
     */
    private String realms[] = new String[0];

    public String[] getRealms() {
        return (this.realms);
    }

    public void setRealms(String realms[]) {
        this.realms = realms;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Reset all properties to their default values.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {

        this.realms = new String[0];

    }
        

}
