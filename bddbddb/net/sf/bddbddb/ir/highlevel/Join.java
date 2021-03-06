// Join.java, created Jun 29, 2004 12:25:51 PM 2004 by jwhaley
// Copyright (C) 2004 John Whaley <jwhaley@alum.mit.edu>
// Licensed under the terms of the GNU LGPL; see COPYING for details.
package net.sf.bddbddb.ir.highlevel;

import java.util.LinkedList;
import java.util.List;
import net.sf.bddbddb.Relation;
import net.sf.bddbddb.ir.Operation;
import net.sf.javabdd.BDDFactory;
import net.sf.javabdd.BDDFactory.BDDOp;

/**
 * Join
 * 
 * @author jwhaley
 * @version $Id$
 */
public class Join extends BooleanOperation {
    List/*<Attribute>*/ attributes;

    /**
     * @param r0
     * @param r1
     * @param r2
     */
    public Join(Relation r0, Relation r1, Relation r2) {
        super(r0, r1, r2);
        this.attributes = new LinkedList();
        this.attributes.addAll(r1.getAttributes());
        this.attributes.retainAll(r2.getAttributes());
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.sf.bddbddb.ir.BooleanOperation#getName()
     */
    public String getName() {
        return "join";
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.sf.bddbddb.ir.Operation#visit(net.sf.bddbddb.ir.HighLevelOperationVisitor)
     */
    public Object visit(HighLevelOperationVisitor i) {
        return i.visit(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.sf.bddbddb.ir.highlevel.BooleanOperation#getBDDOp()
     */
    public BDDOp getBDDOp() {
        return BDDFactory.and;
    }

    public Operation copy() {
        return new Join(r0, r1, r2);
    }
    
    
    /**
     * @return  the attributes that are being joined
     */
    public List getAttributes(){ return attributes; }
}
