package semante.pipeline.gerunds;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class TestGerund2 extends APipelineTest {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createEntailment());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("TestCase02",createEntailment());
		}

		public final Entailment createEntailment() throws Exception {

			// create the vocabulary for the text;
			val t00_john         = word("NP","John");
			val t01_toured       = word("V_2","toured");
			val t02_brussels     = word("NP","Brussels");
			val t03_visiting     = word("GER_2","visiting");
			val t04_the          = word("THE","the");
			val t05_headquarters = word("N","headquarters");
			val t06_of           = word("P_R","of");
			val t07_the          = word("THE","the");
			val t08_eu           = word("N","EU");

			// create the vocabulary for the hypothesis;
			val h00_john         = word("NP","John");
			val h01_toured       = word("V_2","toured");
			val h02_brussels     = word("NP","Brussels");
			val h03_visiting     = word("GER_2","visiting");
			val h04_some         = word("SOME","some");
			val h05_headquarters = word("N","headquarters");

			// create the tree structure for the text;
			val tt =
			_(
				t00_john
				,
				_(
					_(
						t01_toured
						,
						t02_brussels
					)
					,
					_(
						t03_visiting
						,
						_(
							t04_the
							,
							_(
								t05_headquarters
								,
								_(
									t06_of
									,
									_(
										t07_the
										,
										t08_eu
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
				h00_john
				,
				_(
					_(
						h01_toured
						,
						h02_brussels
					)
					,
					_(
						h03_visiting
						,
						_(
							h04_some
							,
							h05_headquarters
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
