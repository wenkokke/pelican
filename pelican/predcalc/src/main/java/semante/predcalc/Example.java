package semante.predcalc;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import lombok.val;
import semante.lambdacalc.Expr;
import semante.lambdacalc.TSymbol;
import semante.lambdacalc.impl.IT;
import semante.predcalc.FOLExpr.Formula;
import semante.predcalc.impl.IPredCalc;
import semante.predcalc.util.ILambda2Pred;

public class Example {
	public static void main(String[] args) throws FileNotFoundException {
		val pcalc = new IPredCalc();
		val lcalc = IT.LambdaCalc();
		ILambda2Pred<TSymbol> l2p = new ILambda2Pred<TSymbol>(pcalc, lcalc);
		
		try {
			Formula t = l2p.smash(lcalc.parse("((AND:ttt (man:et john:e)) (nice:et john:e))"));
			Formula h = l2p.smash(lcalc.parse("(man:et john:e)"));
			System.out.println("Proving " + pcalc.format(t) + " implies " +  pcalc.format(h) + ":");	
			System.out.println(pcalc.prove(t, h));
			System.out.println();
			
			Formula t2 = l2p.smash(lcalc.parse("(man:et john:e)"));
			Formula h2 = l2p.smash(lcalc.parse("(woman:et john:e)"));
			System.out.println("Proving " + pcalc.format(t2) + " implies " +  pcalc.format(h2) + ":");	
			System.out.println(pcalc.prove(t2, h2));
			System.out.println();
			
			
			ArrayList<Expr<TSymbol>> l = new ArrayList<Expr<TSymbol>>();
			/* EXAMPLES */
//			l.add(lcalc.parse("((AND:ttt (kind:et dan:e)) (EXISTS:(et)t (\\x:e.(AND:ttt ((brand_new:(et)et book:et) x:e)) (((gave:eeet x:e) dan:e) mary:e))))"));
//			l.add(lcalc.parse("(EXISTS:(et)t (\\x:e.((AND:ttt (book:et x:e)) (((gave:eeet mary:e) x:e) dan:e))))"));
//			l.add(lcalc.parse("((fat:(et)et man:et) john:e)"));
//
//			// John is a tall dutch fat man
//			// tall: (\\P:et.(\\x:e.((AND:ttt (P:et x:e)) ((tall':(et)et P:et) x:e))))
//			l.add(lcalc.parse("((AND:ttt (kind:et dan:e)) (EXISTS:(et)t (\\x:e.(AND:ttt ((AND:ttt (book:et x:e)) ((new:(et)et book:et) x:e))) (((gave:eeet x:e) dan:e) mary:e))))"));
//			l.add(lcalc.parse("((AND:ttt (kind:et dan:e)) (((\\P:et.(\\x:e.((AND:ttt (P:et x:e)) ((fat:(et)et P:et) x:e)))) man:et) john:e))"));
//			
//			// the man is fat
//			l.add(lcalc.parse("(fat:et (IOTA:(et)e man:et))"));
//			// the fat man is happy
//			l.add(lcalc.parse("(happy:et (IOTA:(et)e (fat:(et)et man:et)))"));
//			
//			l.add(lcalc.parse("(((moderatly:((et)et)(et)et fat:(et)et) (dutch:(et)et man:et)) wim:e)"));
//			l.add(lcalc.parse("((likes:eet wim:e) (mother:ee john:e))"));
//			l.add(lcalc.parse("((actually:((et)et)(et)et secretly:(et)et) (likes:eet mary:e)) john:e"));
//			l.add(lcalc.parse("((actually:((et)et)(et)et secretly:(et)et) (likes:eet mary:e)) (IOTA:(et)e ((moderatly:((et)et)(et)et fat:(et)et) (dutch:(et)et man:et)))"));
//			
			// The happy man loves the nice woman
			l.add(lcalc.parse("((loves:eet (IOTA:(et)e (happy:(et)et man:et))) (IOTA:(et)e (nice:(et)et woman:et)))"));
			// The man gave the woman the book
			l.add(lcalc.parse("(((gave:eeet (IOTA:(et)e book:et)) (IOTA:(et)e woman:et)) (IOTA:(et)e man:et))"));
			
			for (Expr<TSymbol> exp : l) {
				System.out.println("in:   " + lcalc.format(exp));			
				val l2n = lcalc.betaReduce(exp);
				System.out.println("beta: " + lcalc.format(l2n));
				val p = l2p.smash(l2n);
				System.out.println("pred: " + pcalc.format(p));
				System.out.println();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
