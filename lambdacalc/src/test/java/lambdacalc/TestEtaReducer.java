package lambdacalc;

import static org.junit.Assert.assertEquals;
import lombok.val;

import org.junit.Test;

public final class TestEtaReducer extends TestLambdaCalc {
	
	@Test
	public final void example1() {		
		reducesTo(
			"\\x:e.walks:et x:e",
			"walks:et");
	}

	private final void reducesTo(final String exp1, final String exp2) {
		val red1 = stl.format(stl.etaReduce(stl.toDeBruijn(stl.parse(exp1))));
		val red2 = stl.format(stl.toDeBruijn(stl.parse(exp2)));
		assertEquals(red2,red1);
	}
	
}
