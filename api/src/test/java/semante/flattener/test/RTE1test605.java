package semante.flattener.test;

import semante.pipeline.test.Entailment;
import semante.pipeline.test.impl.ATestCase;
import semante.pipeline.test.impl.IEntailment;

import org.junit.Test;
import lombok.val;

public final class RTE1test605 extends ATestCase {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createRTE1test605());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("RTE1test605",createRTE1test605());
		}

		public final Entailment createRTE1test605() throws Exception {

			// create the vocabulary for the text;
			val t00_indonesia = word("NP_D","Indonesia");
			val t01_is = word("IS","is");
			val t02_the = word("THE","the");
			val t03_largest = word("MR","largest");
			val t04_nation = word("N","nation");
			val t05_in = word("P_R","in");
			val t06_the = word("THE","the");
			val t07_world = word("N","world");
			val t08_and = word("AND","and");
			val t09_has = word("V_2","has");
			val t10_some = word("SOME","some");
			val t11_nice = word("MR","nice");
			val t12_islands = word("N","islands");
			val t13_for = word("P_R","for");
			val t14_the = word("THE","the");
			val t15_retires = word("N","retires");

			// create the vocabulary for the hypothesis;
			val h00_indonesia = word("NP_D","Indonesia");
			val h01_has = word("V_2","has");
			val h02_some = word("SOME","some");
			val h03_islands = word("N","islands");

			// create the tree structure for the text;
			val tt =
			_(
				t00_indonesia
				,
				_(
					_(
						_(
							t01_is
							,
							_(
								t02_the
								,
								_(
									t03_largest
									,
									_(
										t04_nation
										,
										_(
											t05_in
											,
											_(
												t06_the
												,
												t07_world
											)
										)
									)
								)
							)
						)
						,
						t08_and
					)
					,
					_(
						t09_has
						,
						_(
							t10_some
							,
							_(
								_(
									t11_nice
									,
									t12_islands
								)
								,
								_(
									t13_for
									,
									_(
										t14_the
										,
										t15_retires
									)
								)
							)
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_indonesia
				,
				_(
					h01_has
					,
					_(
						h02_some
						,
						h03_islands
					)
				)
			)
			;

			// create the subsumption relations;
			val ss =
			new String[] {
				""
			};

			// return the new entailment;
			return new IEntailment(tt, th, ss);
		}

}
