/******************************************************************
 * File:        StandardValidityReport.java
 * Created by:  Dave Reynolds
 * Created on:  09-Feb-03
 * 
 * (c) Copyright 2003, Hewlett-Packard Development Company, LP
 * [See end of file]
 * $Id: StandardValidityReport.java,v 1.7 2004-06-01 08:11:01 der Exp $
 *****************************************************************/
package com.hp.hpl.jena.reasoner;

import java.util.*;

/**
 * Default implementation of ValidityReport which simply stores a list
 * of precomputed Report records.
 * 
 * @author <a href="mailto:der@hplb.hpl.hp.com">Dave Reynolds</a>
 * @version $Revision: 1.7 $ on $Date: 2004-06-01 08:11:01 $
 */
public class StandardValidityReport implements ValidityReport {

    /** The total set of error reports */
    protected List reports = new ArrayList();
    
    /** Flag to indicate if there are any error reports so far */
    protected boolean isError;
    
    /**
     * Add a new error report
     * @param error true if the report is an error, false if it is just a warning
     * @param type a string giving a reasoner-dependent classification for the report
     * @param description a textual description of the problem
     */
    public void add(boolean error, String type, String description) {
        add(error, type, description, null);
    }
    
    /**
     * Add a new error report
     * @param error true if the report is an error, false if it is just a warning
     * @param type a string giving a reasoner-dependent classification for the report
     * @param description a textual description of the problem
     * @param extension Optional argument with extension data about the reported error
     */
    public void add(boolean error, String type, String description, Object extension) {
        reports.add(new Report(error, type, description, extension));
        if (error) {
            isError = true;
        }
    }
    
    /**
     * Add a new error report
     * @param report a ValidityReport.Report to add, can be null 
     */
    public void add(ValidityReport.Report report) {
        if (report == null) return;
        reports.add(report);
        if (report.isError) {
            isError = true;
        }
    }
    
    /**
     * Return true if there are no problems reported by the validation
     */
    public boolean isValid() {
        return !isError;
    }
    
    /**
     * Return a count of the number of warning or error reports
     * generated by the validation.
     */
    public int size() {
        return reports.size();
    }
    
    
    /**
     * Return an iterator over the separate ValidityReport.Report records.
     */
    public Iterator getReports() {
        return reports.iterator();
    }


}

/*
 * (c) Copyright 2003 Hewlett-Packard Development Company, LP
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

