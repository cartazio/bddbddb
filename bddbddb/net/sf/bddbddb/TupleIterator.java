// TupleIterator.java, created May 4, 2004 7:54:39 PM 2004 by jwhaley
// Copyright (C) 2004 John Whaley <jwhaley@alum.mit.edu>
// Licensed under the terms of the GNU LGPL; see COPYING for details.
package net.sf.bddbddb;

import java.util.Iterator;
import java.math.BigInteger;

/**
 * TupleIterator
 * 
 * @author jwhaley
 * @version $Id$
 */
public abstract class TupleIterator implements Iterator {
    public abstract BigInteger[] nextTuple();

    public Object next() {
        return nextTuple();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
    
    //public abstract long count();
    
}
