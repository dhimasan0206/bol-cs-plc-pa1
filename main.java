import java.util.*;

abstract class Expr {
    public abstract String toString();
    public abstract int eval(Map<String, Integer> env);
    public abstract Expr Simpli();
}

class CstI extends Expr {
    protected final int attr;
    
    public CstI(int n) {
        this.attr = n;
    }
    
    public String toString() {
        return Integer.toString(this.attr);
    }
    
    public int eval(Map<String, Integer> env) {
        return attr;
    }
    
    public Expr Simpli() {
        return this;
    }
}

class Var extends Expr {
    protected final String attr;
    
    public Var(String str) {
        this.attr = str;   
    }
    
    public String toString() {
        return this.attr;
    }
    
    public int eval(Map<String, Integer> env) {
        return env.get(this.attr);
    }
    
    public Expr Simpli() {
        return this;
    }
}

abstract class Binop extends Expr {}

class Add extends Binop {
    protected final Expr e1, e2;
    
    public Add(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
    
    public String toString() {
        return String.join(" ", this.e1.toString(), "+", this.e2.toString());
    }
    
    public int eval(Map<String, Integer> env) {
        return this.e1.eval(env) + this.e2.eval(env);
    }
    
    public Expr Simpli() {
        if (this.e1.toString() == "0") {
            return this.e2;
        } else if (this.e2.toString() == "0") {
            return this.e1;
        } else {
            return this;
        }
    }
}

class Mul extends Binop {
    protected final Expr e1, e2;
    
    public Mul(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
    
    public String toString() {
        return String.join(" ", this.e1.toString(), "*", this.e2.toString());
    }
    
    public int eval(Map<String, Integer> env) {
        return this.e1.eval(env) * this.e2.eval(env);
    }
    
    public Expr Simpli() {
        if (this.e1.toString() == "1") {
            return this.e2;
        } else if (this.e2.toString() == "1") {
            return this.e1;
        } else if (this.e1.toString() == "0" || this.e2.toString() == "0") {
            return new CstI(0);
        } else {
            return this;
        }
    }
}

class Sub extends Binop {
    protected final Expr e1, e2;
    
    public Sub(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
    
    public String toString() {
        return String.join(" ", this.e1.toString(), "-", this.e2.toString());
    }
    
    public int eval(Map<String, Integer> env) {
        return this.e1.eval(env) - this.e2.eval(env);
    }
    
    public Expr Simpli() {
        if (this.e2.toString() == "0") {
            return this.e1;
        } else if (this.e1.toString() == this.e2.toString()) {
            return new CstI(0);
        } else {
            return this;
        }
    }
}

public class MyClass {
    public static void main(String args[]) {
      Map<String, Integer> env = new HashMap<String, Integer>();
      env.put("z", 1);
      env.put("a", 0);
      env.put("b", 1);
      env.put("c", 17);
        
      Expr e = new Add(new CstI(17), new Var("z"));
      System.out.println(e.toString());
      System.out.println(e.eval(env));
      
      Expr f = new Mul(new CstI(17), new Var("z"));
      System.out.println(f.toString());
      System.out.println(f.eval(env));
      
      Expr g = new Sub(new CstI(17), new Var("z"));
      System.out.println(g.toString());
      System.out.println(g.eval(env));
      
      Expr h = new Add(new Var("a"), new CstI(17));
      Expr simpliH = h.Simpli();
      System.out.println(simpliH.toString());
      System.out.println(simpliH.eval(env));
      
      Expr i = new Add(new CstI(17), new Var("a"));
      Expr simpliI = i.Simpli();
      System.out.println(simpliI.toString());
      System.out.println(simpliI.eval(env));
      
      Expr j = new Mul(new Var("a"), new CstI(17));
      Expr simpliJ = j.Simpli();
      System.out.println(simpliJ.toString());
      System.out.println(simpliJ.eval(env));
      
      Expr k = new Mul(new CstI(17), new Var("a"));
      Expr simpliK = k.Simpli();
      System.out.println(simpliK.toString());
      System.out.println(simpliK.eval(env));
      
      Expr l = new Mul(new Var("b"), new CstI(17));
      Expr simpliL = l.Simpli();
      System.out.println(simpliL.toString());
      System.out.println(simpliL.eval(env));
      
      Expr m = new Mul(new CstI(17), new Var("b"));
      Expr simpliM = m.Simpli();
      System.out.println(simpliM.toString());
      System.out.println(simpliM.eval(env));
      
      Expr n = new Sub(new CstI(17), new Var("a"));
      Expr simpliN = n.Simpli();
      System.out.println(simpliN.toString());
      System.out.println(simpliN.eval(env));
      
      Expr o = new Sub(new CstI(17), new Var("c"));
      Expr simpliO = o.Simpli();
      System.out.println(simpliO.toString());
      System.out.println(simpliO.eval(env));
    }
}