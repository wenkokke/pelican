package lambdacalc;

import lombok.val;

public class Main {
	public static void main(String[] args) {
		
		val stl  = new STL();
		val exp1 = stl.parse("((\\A:et.A:et) walks:et) john:e");
		System.out.println(stl.format(exp1));
		val exp2 = stl.toDeBruijn(exp1);
		System.out.println(stl.format(exp2));
		val exp3 = stl.betaReduce(exp2);
		System.out.println(stl.format(exp3));
		
	}
}
