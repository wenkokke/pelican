package semante.pipeline.test;

import static org.junit.Assert.assertEquals;
import lambdacalc.DeBruijn;
import lambdacalc.STL;
import lombok.SneakyThrows;

import org.junit.Before;
import org.junit.Test;

import semante.pipeline.HigherOrderImplication;
import semante.pipeline.HigherOrderImplication.HOIException;
import semante.pipeline.impl.IHigherOrderImplication;

import com.google.common.collect.ImmutableList;

public final class TestHOI {
	
	STL stl;
	HigherOrderImplication hoi;
	
	@Before
	public final void setUp() {
		stl = new STL();
		hoi = new IHigherOrderImplication(stl);
	}
	
	private final DeBruijn arg(String expr) {
		return stl.toDeBruijn(stl.parse(expr));
	}
	
	private final String expected(String expr) {
		return
			stl.format(
				stl.fromDeBruijn(arg(expr)));
	}
	
	@SneakyThrows(HOIException.class)
	private final String actual(DeBruijn t, DeBruijn h, DeBruijn... cx) {
		return
			stl.format(
				stl.fromDeBruijn(
					hoi.higherOrderImplication(t, h, ImmutableList.copyOf(cx))));
	}
	
	@Test
	public final void example1() {
		assertEquals(expected("IMPLIES:ttt F:t T:t")
					,actual(arg("F:t"),arg("T:t")));
	}
	
	@Test
	public final void example2() {
		assertEquals(expected("FORALL:(et)t (\\x:e.IMPLIES:ttt (bird:et x:e) (flies:et x:e))")
				    ,actual(arg("bird:et"),arg("flies:et")));
	}
	
	@Test
	public final void example3() {
		assertEquals(expected("FORALL:(et)t (\\x:e.FORALL:(et)t (\\y:e.IMPLIES:ttt (gives:eet y:e x:e) (bestows:eet y:e x:e)))")
				    ,actual(arg("gives:eet"),arg("bestows:eet")));
	}
	
	@Test
	public final void example4() {
		assertEquals(expected("FORALL:(et)t (\\x:e.IMPLIES:ttt (last:(et)et president:et x:e) (former:(et)et president:et x:e))")
					,actual(arg("last:(et)et"),arg("former:(et)et"),arg("president:et")));
	}
	
	@Test
	public final void example5() {
		System.out.println(actual(arg("last:(et)et"),arg("former:(et)et"),arg("president:et"),arg("man:et")));
		
		assertEquals(expected("FORALL:(et)t (\\x:e.AND:ttt "
								+ "(IMPLIES:ttt (last:(et)et president:et x:e) (former:(et)et president:et x:e)) "
								+ "(IMPLIES:ttt (last:(et)et man:et x:e) (former:(et)et man:et x:e))"
								+ ")")
					,actual(arg("last:(et)et"),arg("former:(et)et"),arg("president:et"),arg("man:et")));
	}
	
}
