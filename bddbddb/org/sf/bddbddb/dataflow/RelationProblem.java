// RelationProblem.java, created Jul 3, 2004 1:44:46 PM by joewhaley
// Copyright (C) 2004 John Whaley <jwhaley@alum.mit.edu>
// Licensed under the terms of the GNU LGPL; see COPYING for details.
package org.sf.bddbddb.dataflow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.sf.bddbddb.IterationList;
import org.sf.bddbddb.Relation;

/**
 * RelationProblem
 * 
 * @author John Whaley
 * @version $Id$
 */
public abstract class RelationProblem extends Problem {
    public Fact getBoundary() {
        return new RelationFacts();
    }
    public static class RelationFacts implements Fact {
        Map/* <Relation,RelationFact> */relationFacts;
        IterationList location;

        public RelationFacts create() {
            return new RelationFacts();
        }

        public RelationFacts() {
            initialize();
        }

        public void initialize() {
            relationFacts = new HashMap();
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.sf.bddbddb.dataflow.Problem.Fact#join(org.sf.bddbddb.dataflow.Problem.Fact,
         *      org.sf.bddbddb.dataflow.Problem.Fact)
         */
        public Fact join(Fact fact) {
            RelationFacts result = create();
            result.relationFacts.putAll(this.relationFacts);
            RelationFacts that = (RelationFacts) fact;
            for (Iterator i = that.relationFacts.entrySet().iterator(); i.hasNext(); ) {
                Map.Entry e = (Map.Entry) i.next();
                Relation r = (Relation) e.getKey();
                RelationFact f = (RelationFact) e.getValue();
                RelationFact old = (RelationFact) result.relationFacts.put(r, f);
                if (old != null) {
                    f = (RelationFact) f.join(old);
                    result.relationFacts.put(r, f);
                }
            }
            result.location = location;
            return result;
        }

        public Fact copy(IterationList loc) {
            RelationFacts that = new RelationFacts();
            that.relationFacts.putAll(this.relationFacts);
            that.location = loc;
            return that;
        }

        public RelationFact getFact(Relation r) {
            return (RelationFact) relationFacts.get(r);
        }

        public int hashCode() {
            return relationFacts.hashCode();
        }

        public boolean equals(RelationFacts that) {
            return relationFacts.equals(that.relationFacts);
        }

        public boolean equals(Object o) {
            return equals((RelationFacts) o);
        }

        public void setLocation(IterationList loc) {
            this.location = loc;
        }

        public IterationList getLocation() {
            return location;
        }
    }
    public static abstract class RelationFact implements Fact {
    }
}