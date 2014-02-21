package semante.pipeline.rte;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class TestCaseRTE4test955 extends APipelineTest {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createRTE4test955());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("RTE4test955",createRTE4test955());
		}

		public final Entailment createRTE4test955() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_largest = word("MOD_R","largest");
			val t02_search = word("MOD_R","search");
			val t03_engine = word("N_1","engine");
			val t04_on = word("P_R","on");
			val t05_the = word("THE","The");
			val t06_web = word("N_1","web");
			val t07_which = word("WHO_A","which");
			val t08_is = word("IS","is");
			val t09_google = word("NP","google");
			val t10_receives = word("V_2","receives");
			val t11_over_200_million = word("NUMBER","over 200 million");
			val t12_queries = word("N_1","queries");

			// create the vocabulary for the hypothesis;
			val h00_google = word("NP","google");
			val h01_operates = word("V_1","operates");
			val h02_on = word("P_R","on");
			val h03_the = word("THE","The");
			val h04_web = word("N_1","web");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_the
						,
						_(
							t01_largest
							,
							_(
								_(
									t02_search
									,
									t03_engine
								)
								,
								_(
									t04_on
									,
									_(
										t05_the
										,
										t06_web
									)
								)
							)
						)
					)
					,
					_(
						t07_which
						,
						_(
							t08_is
							,
							t09_google
						)
					)
				)
				,
				_(
					t10_receives
					,
					_(
						t11_over_200_million
						,
						t12_queries
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_google
				,
				_(
					h01_operates
					,
					_(
						h02_on
						,
						_(
							h03_the
							,
							h04_web
						)
					)
				)
			)
			;

			// create the subsumption relations;
			val ss =
			new String[] {
				"all x (all y (on_operates(y,x) -> operates(x))).",
				"all x (all y ((web(y) & on_search_engine(y,x)) -> on_operates(y,x)))."
			};

			// return the new entailment;
			return new IEntailment(tt, th, ss);
		}

}

