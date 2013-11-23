package semante.pipeline.rte;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class TestCaseRTE3dev118 extends APipelineTest {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createRTE3dev118());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("RTE3dev118",createRTE3dev118());
		}

		public final Entailment createRTE3dev118() throws Exception {

			// create the vocabulary for the text;
			val t00_beavers = word("NP","Beavers");
			val t01_who = word("WHO_A","who");
			val t02_is = word("IS","is");
			val t03_a = word("A","a");
			val t04_conowner = word("N_1","coNowner");
			val t05_of = word("P_R","of");
			val t06_the = word("THE","the");
			val t07_current = word("MOD_R","current");
			val t08_company = word("N_1","company");
			val t09_which = word("WHO_A","which");
			val t10_is = word("IS","is");
			val t11_carolina = word("NP","Carolina");
			val t12_which = word("WHO_A","which");
			val t13_is = word("IS","is");
			val t14_llc = word("NP","LLC");
			val t15_and = word("AND","and");
			val t16_has = word("V_2","has");
			val t17_a = word("A","a");
			val t18_history = word("N_1","history");
			val t19_with = word("P_R","with");
			val t20_woodson = word("NP","Woodson");
			val t21_was = word("IS","was");
			val t22_installed = word("V_1","installed");
			val t23_in = word("P_R","in");
			val t24_the = word("THE","the");
			val t25_early = word("MOD_R","early");
			val t26_1990s = word("N_1","1990s");

			// create the vocabulary for the hypothesis;
			val h00_beavers = word("NP","Beavers");
			val h01_is = word("IS","is");
			val h02_a = word("A","a");
			val h03_conowner = word("N_1","coNowner");
			val h04_of = word("P_R","of");
			val h05_carolina = word("NP","Carolina");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_beavers
					,
					_(
						t01_who
						,
						_(
							_(
								t02_is
								,
								_(
									t03_a
									,
									_(
										t04_conowner
										,
										_(t05_of,_(_(t06_the,_(t07_current,t08_company)),_(t09_which,_(t10_is,_(t11_carolina,_(t12_which,_(t13_is,t14_llc)))))))
									)
								)
							)
							,
							_(
								t15_and
								,
								_(
									t16_has
									,
									_(
										t17_a
										,
										_(
											t18_history
											,
											_(
												t19_with
												,
												t20_woodson
											)
										)
									)
								)
							)
						)
					)
				)
				,
				_(
					t21_was
					,
					_(
						t22_installed
						,
						_(
							t23_in
							,
							_(
								t24_the
								,
								_(
									t25_early
									,
									t26_1990s
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
				h00_beavers
				,
				_(
					h01_is
					,
					_(
						h02_a
						,
						_(
							h03_conowner
							,
							_(
								h04_of
								,
								h05_carolina
							)
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
