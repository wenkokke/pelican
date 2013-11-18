package semante.flattener.test;

import semante.pipeline.test.Entailment;
import semante.pipeline.test.impl.ATestCase;
import semante.pipeline.test.impl.IEntailment;

import org.junit.Test;
import lombok.val;

public final class RTE1dev668 extends ATestCase {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createRTE1dev668());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("RTE1dev668",createRTE1dev668());
		}

		public final Entailment createRTE1dev668() throws Exception {

			// create the vocabulary for the text;
			val t00_dan = word("NP_D","Dan");
			val t01_saw = word("V_2","saw");
			val t02_the = word("THE","the");
			val t03_hall = word("N","hall");
			val t04_which = word("WHO_A","which");
			val t05_is = word("IS","is");
			val t06_old = word("MR","old");
			val t07_and = word("AND","and");
			val t08_is = word("IS","is");
			val t09_the = word("THE","the");
			val t10_big = word("MR","big");
			val t11_municipal = word("MI","municipal");
			val t12_museum = word("N","museum");

			// create the vocabulary for the hypothesis;
			val h00_the = word("THE","The");
			val h01_hall = word("N","hall");
			val h02_is = word("IS","is");
			val h03_a = word("A","a");
			val h04_big = word("MR","big");
			val h05_museum = word("N","museum");

			// create the tree structure for the text;
			val tt =
			_(
				t00_dan
				,
				_(
					t01_saw
					,
					_(
						_(
							t02_the
							,
							t03_hall
						)
						,
						_(
							t04_which
							,
							_(
								_(
									t05_is
									,
									t06_old
								)
								,
								_(
									t07_and
									,
									_(
										t08_is
										,
										_(
											t09_the
											,
											_(
												t10_big
												,
												_(
													t11_municipal
													,
													t12_museum
												)
											)
										)
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
				_(
					h00_the
					,
					h01_hall
				)
				,
				_(
					h02_is
					,
					_(
						h03_a
						,
						_(
							h04_big
							,
							h05_museum
						)
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
