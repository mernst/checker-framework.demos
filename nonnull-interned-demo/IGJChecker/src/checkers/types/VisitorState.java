package checkers.types;

import java.util.HashMap;

import com.sun.source.tree.ClassTree;
import com.sun.source.tree.MethodTree;

import checkers.types.AnnotatedTypeMirror.AnnotatedDeclaredType;

/**
 * Represents the state of a visitor.  Stores the relevant information to find
 * the type of 'this' in the visitor.
 */
public class VisitorState {
    /** The type of the enclosing class tree**/
    private AnnotatedDeclaredType act;
    /** The enclosing class tree **/
    private ClassTree ct;
    
    /** The receiver type of the enclosing class tree **/
    private AnnotatedDeclaredType mrt;
    /** The enclosing method tree **/
    private MethodTree mt;

    /**
     * Updates the type of the current class currently visited
     */
    public void setClassType(AnnotatedDeclaredType act) {
        this.act = act;
    }
    
    /**
     * Updates the tree of the current class currently visited
     */
    public void setClassTree(ClassTree ct) {
        this.ct = ct;
    }
    
    /**
     * Updates the method receiver type currently visited
     */
    public void setMethodReceiver(AnnotatedDeclaredType mrt) {
        this.mrt = mrt;
    }

    /**
     * Updates the mehtod currently visited
     */
    public void setMethodTree(MethodTree mt) {
        this.mt = mt;
    }
    
    /**
     * @return the type of the enclosing class
     */
    public AnnotatedDeclaredType getClassType() {
        if (act == null) return null;
        return (AnnotatedDeclaredType) 
        act.substitute(new HashMap<AnnotatedTypeMirror, AnnotatedTypeMirror>());
    }

    /**
     * @return the class tree currently visiting
     */
    public ClassTree getClassTree() {
        return this.ct;
    }
    
    /**
     * @return the method receiver type of the enclosing method
     */
    public AnnotatedDeclaredType getMethodReceiver() {
        if (mrt == null) return null;
        return (AnnotatedDeclaredType) 
        mrt.substitute(new HashMap<AnnotatedTypeMirror, AnnotatedTypeMirror>());
    }
    
    /**
     * @return the method tree currently visiting
     */
    public MethodTree getMethodTree() {
        return this.mt;
    }
    
    @Override
    public String toString() {
        return String.format("method %s (%s) / class %s (%s)\n",
                (mt != null ? mt.getName() : "null"),
                mrt,
                (ct != null ? ct.getSimpleName() : "null"),
                act);
    }
}
