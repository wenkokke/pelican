package lambdacalc;

import static org.junit.Assert.assertEquals;
import lombok.val;

import org.junit.Test;

public final class TestBetaReducer$Jan2 extends TestLambdaCalc {
	
	@Test
	public final void fromStep1() {
		reduce("(\\A0:et.A0:et jan:e) ((\\P0:(et)t.\\x0:e.P0:(et)t (\\x1:e.EQ:eet x1:e x0:e)) ((\\A1:et.\\A2:et.EXISTS:(et)t (\\x2:e.AND:ttt (A1:et x2:e) (A2:et x2:e))) ((\\A3:et.\\x3:e.AND:ttt (A3:et x3:e) (black:et x3:e)) ((\\A4:et.\\x4:e.AND:ttt (A4:et x4:e) (dutch:et x4:e)) man:et))))");
	}

	@Test
	public final void fromStep2() {
		reduce("(\\P0:(et)t.\\x0:e.P0:(et)t (\\x1:e.EQ:eet x1:e x0:e)) ((\\A1:et.\\A2:et.EXISTS:(et)t (\\x2:e.AND:ttt (A1:et x2:e) (A2:et x2:e))) ((\\A3:et.\\x3:e.AND:ttt (A3:et x3:e) (black:et x3:e)) ((\\A4:et.\\x4:e.AND:ttt (A4:et x4:e) (dutch:et x4:e)) man:et))) jan:e");
	}

	@Test
	public final void fromStep3() {
		reduce("(\\x0:e.(\\A1:et.\\A2:et.EXISTS:(et)t (\\x2:e.AND:ttt (A1:et x2:e) (A2:et x2:e))) ((\\A3:et.\\x3:e.AND:ttt (A3:et x3:e) (black:et x3:e)) ((\\A4:et.\\x4:e.AND:ttt (A4:et x4:e) (dutch:et x4:e)) man:et)) (\\x1:e.EQ:eet x1:e x0:e)) (jan:e)");
	}

	@Test
	public final void fromStep4() {
		reduce("(\\A1:et.\\A2:et.EXISTS:(et)t (\\x2:e.AND:ttt (A1:et x2:e) (A2:et x2:e))) ((\\A3:et.\\x3:e.AND:ttt (A3:et x3:e) (black:et x3:e)) ((\\A4:et.\\x4:e.AND:ttt (A4:et x4:e) (dutch:et x4:e)) man:et)) (\\x1:e.EQ:eet x1:e jan:e)");
	}

	@Test
	public final void fromStep5() {
		reduce("(\\A2:et.EXISTS:(et)t (\\x2:e.AND:ttt ((\\A3:et.\\x3:e.AND:ttt (A3:et x3:e) (black:et x3:e)) ((\\A4:et.\\x4:e.AND:ttt (A4:et x4:e) (dutch:et x4:e)) man:et) x2:e) (A2:et x2:e))) (\\x1:e.EQ:eet x1:e jan:e)");
	}

	@Test
	public final void fromStep6() {
		reduce("EXISTS:(et)t (\\x2:e.AND:ttt ((\\A3:et.\\x3:e.AND:ttt (A3:et x3:e) (black:et x3:e)) ((\\A4:et.\\x4:e.AND:ttt (A4:et x4:e) (dutch:et x4:e)) man:et) x2:e) ((\\x1:e.EQ:eet x1:e jan:e) x2:e))");
	}

	@Test
	public final void fromStep7() {
		reduce("EXISTS:(et)t (\\x2:e.AND:ttt ((\\x3:e.AND:ttt ((\\A4:et.\\x4:e.AND:ttt (A4:et x4:e) (dutch:et x4:e)) man:et x3:e) (black:et x3:e)) (x2:e)) ((\\x1:e.EQ:eet x1:e jan:e) x2:e))");
	}

	@Test
	public final void fromStep8() {
		reduce("EXISTS:(et)t (\\x2:e.AND:ttt (AND:ttt ((\\A4:et.\\x4:e.AND:ttt (A4:et x4:e) (dutch:et x4:e)) (man:et) x2:e) (black:et x2:e)) ((\\x1:e.EQ:eet x1:e jan:e) x2:e))");
	}

	@Test
	public final void fromStep9() {
		reduce("EXISTS:(et)t (\\x2:e.AND:ttt (AND:ttt ((\\x4:e.AND:ttt (man:et x4:e) (dutch:et x4:e)) (x2:e)) (black:et x2:e)) ((\\x1:e.EQ:eet x1:e jan:e) x2:e))");
	}

	@Test
	public final void fromStep10() {
		reduce("EXISTS:(et)t (\\x2:e.AND:ttt (AND:ttt (AND:ttt (man:et x2:e) (dutch:et x2:e)) (black:et x2:e)) ((\\x1:e.EQ:eet x1:e jan:e) (x2:e)))");
	}

	@Test
	public final void fromStep11() {
		reduce("EXISTS:(et)t (\\x2:e.AND:ttt (AND:ttt (AND:ttt (man:et x2:e) (dutch:et x2:e)) (black:et x2:e)) (EQ:eet x2:e jan:e))");
	}
	
	private final void reduce(final String raw) {
		reducesTo(raw,
			"EXISTS:(et)t (\\x2:e.AND:ttt (AND:ttt (AND:ttt (man:et x2:e) (dutch:et x2:e)) (black:et x2:e)) (EQ:eet x2:e jan:e))");
	}

	private final void reducesTo(final String raw1, final String raw2) {
		val exp1 = stl.format(stl.betaReduce(stl.toDeBruijn(stl.parse(raw1))));
		System.err.println(stl.format(stl.fromDeBruijn(stl.betaReduce(stl.toDeBruijn(stl.parse(raw1))))));
		val exp2 = stl.format(stl.toDeBruijn(stl.parse(raw2)));
		System.err.println(stl.format(stl.fromDeBruijn(stl.toDeBruijn(stl.parse(raw1)))));
		assertEquals(exp2,exp1);
	}
	
}
