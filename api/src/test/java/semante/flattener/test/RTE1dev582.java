package semante.flattener.test;

import semante.pipeline.test.Entailment;
import semante.pipeline.test.impl.ATestCase;
import semante.pipeline.test.impl.IEntailment;

import org.junit.Test;
import lombok.val;

public final class RTE1dev582 extends ATestCase {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createRTE1dev582());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("RTE1dev582",createRTE1dev582());
		}

		public final Entailment createRTE1dev582() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_visit = word("N","visit");
			val t02_comes = word("V_1","comes");
			val t03_amid = word("P_R","amid");
			val t04_the = word("THE","the");
			val t05_rumors = word("N","rumors");
			val t06_about = word("P_R","about");
			val t07_the = word("THE","the");
			val t08_90yearold = word("MI","90yearOld");
			val t09_senior = word("MR","senior");
			val t10_leader = word("N","leader");
			val t11_who = word("WHO_A","who");
			val t12_is = word("IS","is");
			val t13_deng = word("NP_D","Deng");

			// create the vocabulary for the hypothesis;
			val h00_deng = word("NP_D","Deng");
			val h01_is = word("IS","is");
			val h02_90yearold = word("MI","90yearold");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_the
					,
					t01_visit
				)
				,
				_(
					t02_comes
					,
					_(
						t03_amid
						,
						_(
							t04_the
							,
							_(
								t05_rumors
								,
								_(
									t06_about
									,
									_(
										_(
											t07_the
											,
											_(
												t08_90yearold
												,
												_(
													t09_senior
													,
													t10_leader
												)
											)
										)
										,
										_(
											t11_who
											,
											_(
												t12_is
												,
												t13_deng
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
				h00_deng
				,
				_(
					h01_is
					,
					h02_90yearold
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
