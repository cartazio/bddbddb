// Operation.java, created Jun 29, 2004 12:24:59 PM 2004 by jwhaley
// Copyright (C) 2004 John Whaley <jwhaley@alum.mit.edu>
// Licensed under the terms of the GNU LGPL; see COPYING for details.
package org.sf.bddbddb.ir;

import java.util.List;
import org.sf.bddbddb.IterationElement;
import org.sf.bddbddb.Relation;

/**
 * Operation
 * 
 * @author jwhaley
 * @version $Id$
 */
public abstract class Operation implements IterationElement {
    static int opNumber = 0;
    
    /**
     * @return
     */
    public static int getNumberOfOperations() { return opNumber+1; }

    /**
     * Comment for <code>id</code>
     */
    public final int id;

    /**
     * 
     */
    public Operation() {
        id = ++opNumber;
    }

    /**
     * @param i
     * @return
     */
    public abstract Object visit(OperationVisitor i);

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public abstract String toString();

    /**
     * @return
     */
    public abstract Relation getRelationDest();

    /**
     * @param r0
     */
    public abstract void setRelationDest(Relation r0);

    /**
     * @return
     */
    public abstract List/* <Relation> */getSrcs();

    /**
     * @param r_old
     * @param r_new
     */
    public abstract void replaceSrc(Relation r_old, Relation r_new);
    
    /**
     * @return
     */
    public abstract String getExpressionString();
}