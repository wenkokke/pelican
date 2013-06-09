package lambdacalc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import lambdacalc.IType;

import org.junit.Test;

public final class TestParser extends TestLambdaCalc {
	
	@Test
	public final void primitiveTypes() {
		assertSame(IType.E, stl.parseType("e"));
		assertSame(IType.T, stl.parseType("t"));
	}
	
	@Test
	public final void functionTypes() {
		parseAndPrintType("et");
		parseAndPrintType("eet");
		parseAndPrintType("eeet");
		parseAndPrintType("(et)t");
		parseAndPrintType("(et)et");
	}
	
	@Test
	public final void adjoinedParens() {
		parseAndPrintType("((et)et)(et)et");
	}
	
	@Test
	public final void symbols() {
		parseAndPrintSymbol("x:e");
		parseAndPrintSymbol("A:et");
		parseAndPrintSymbol("P:(et)t");
	}
	
	@Test
	public final void terms() {
		parseAndPrintExpr("x:e");
		parseAndPrintExpr("\\x:e.x:e");
		parseAndPrintExpr("\\x:e.(f:et x:e)");
		parseAndPrintExpr("(\\x:e.x:e) x:e");
	}
	
	private final void parseAndPrintType(String type) {
		assertEquals(type,stl.format(stl.parseType(type)));
	}
	
	private final void parseAndPrintSymbol(String symbol) {
		assertEquals(symbol,stl.format(stl.parseSymbol(symbol)));
	}
	
	private final void parseAndPrintExpr(String expr) {
		assertEquals(expr,stl.format(stl.parseExpr(expr)));
	}
}
