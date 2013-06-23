package lambdacalc;

import static org.junit.Assert.assertEquals;
import lombok.val;

import org.junit.Test;

public final class TestBetaReducer extends TestLambdaCalc {
	
	@Test
	public final void jan2_1() {
		reducesTo(
			"(\\A0:et.(A0:et jan:e)) "
			+ "((\\P0:(et)t.(\\x0:e.(P0:(et)t (\\x1:e.((EQ:eet x1:e) x0:e))))) "
			+ "((\\A1:et.(\\A2:et.(EXISTS:(et)t (\\x2:e.((AND:ttt (A1:et x2:e)) (A2:et x2:e)))))) "
			+ "((\\A3:et.(\\x3:e.((AND:ttt (A3:et x3:e)) (black:et x3:e)))) "
			+ "((\\A4:et.(\\x4:e.((AND:ttt (A4:et x4:e)) (dutch:et x4:e)))) man:et))))",
			"EXISTS:(et)t (\\x2:e.AND:ttt (AND:ttt (AND:ttt (man:et x2:e) (dutch:et x2:e)) (black:et x2:e)) (EQ:eet x2:e jan:e))");
	}
	
	@Test
	public final void jan2_2() {
		reducesTo(
			"(\\A0:et.(A0:et jan:e)) (\\x0:e.EXISTS:(et)t (\\x2:e.AND:ttt (AND:ttt (AND:ttt (man:et x2:e) (dutch:et x2:e)) (black:et x2:e)) (EQ:eet x2:e x0:e)))",
			"EXISTS:(et)t (\\x2:e.AND:ttt (AND:ttt (AND:ttt (man:et x2:e) (dutch:et x2:e)) (black:et x2:e)) (EQ:eet x2:e jan:e))");
			
	}
	
	@Test
	public final void example1() {		
		reducesTo(
			"(\\A:et.\\B:et.\\x:e.AND:ttt (A:et x:e) (B:et x:e)) ate:et",
			"\\B:et.\\x:e.AND:ttt (ate:et x:e) (B:et x:e)");
	}
	
	@Test
	public final void example2() {
		reducesTo(
			"(\\f:et.f:et john:e) (\\x:e.x:e)",
			"john:e");
	}
	
	@Test
	public final void complex1() {
		reducesTo(
			"((\\x:e.\\f:et.f:et x:e) john:e) walks:et",
			"walks:et john:e");
	}
	
	@Test
	public final void identities1() {
		reducesTo(
			"(\\x:e.x:e) john:e",
			"john:e");
	}
	@Test
	public final void identities2() {
		reducesTo(
			"((\\A:et.A:et) walks:et) john:e",
			"walks:et john:e");
	}
	@Test
	public final void identities3() {
		reducesTo(
			"(((\\M:(et)et.M:(et)et) fast:(et)et) walks:et) john:e",
		    "(fast:(et)et walks:et) john:e");
	}
	
	@Test
	public final void irreducibles1() {
		reducesTo(
			"walks:et john:et",
		    "walks:et john:et");
	}
	@Test
	public final void irreducibles2() {
		reducesTo(
			"((fast:(et)et walks:et) john:e)",
		    "((fast:(et)et walks:et) john:e)");
	}
	@Test
	public final void irreducibles3() {
		reducesTo(
			"(((very:((et)et)(et)et fast:(et)et) walks:et) john:e)",
		    "(((very:((et)et)(et)et fast:(et)et) walks:et) john:e)");
	}

	private final void reducesTo(final String raw1, final String raw2) {
		val exp1 = stl.toDeBruijn(stl.parse(raw1));
		val exp2 = stl.toDeBruijn(stl.parse(raw2));
		val red1 = stl.format(stl.betaReduce(exp1));
		val red2 = stl.format(exp2);
		System.err.println(stl.format(stl.fromDeBruijn(exp1)));
		System.err.println(stl.format(stl.fromDeBruijn(stl.betaReduce(exp1))));
		assertEquals(red2,red1);
	}
	
}
