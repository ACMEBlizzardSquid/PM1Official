/*
 * Copyright 1999,2004 The Apache Software Foundation.
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

package org.apache.catalina.cluster.tcp;


/**
 * @author Filip Hanik
 * @version $Revision: 303842 $ $Date: 2005-04-10 12:20:46 -0400 (Sun, 10 Apr 2005) $
 */
public class WorkerThread extends Thread
{
    private static org.apache.commons.logging.Log log =
        org.apache.commons.logging.LogFactory.getLog( WorkerThread.class );
    protected ThreadPool pool;
    protected boolean doRun = true;


    public void setPool(ThreadPool pool) {
        this.pool = pool;
    }
    
    public ThreadPool getPool() {
        return pool;
    }

    public void close()
    {
        doRun = false;
        notify();

    }
}
