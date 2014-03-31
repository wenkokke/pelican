package semante.pipeline.factives;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class TestCase01 extends APipelineTest {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createEntailment());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("TestCase01",createEntailment());
		}

		public final Entailment createEntailment() throws Exception {

			// create the vocabulary for the text;
			val t00_john    = word("NP","John");
			val t01_said    = word("FACT","said");
			val t02_that    = word("IGNORE","that");
			val t03_the     = word("THE","the");
			val t04_car     = word("N","car");
			val t05_crashed = word("V_1","crashed");
			val t06_into    = word("P_R","into");
			val t07_the     = word("THE","the");
			val t08_dom     = word("$NC_1$","Dom");
			val t09_tower   = word("N","Tower");

			// create the vocabulary for the hypothesis;
			val h00_the     = word("THE","The");
			val h01_car     = word("N","car");
			val h02_crashed = word("V_1","crashed");
			val h03_into    = word("P_R","into");
			val h04_the     = word("THE","the");
			val h05_dom     = word("$NC_1$","Dom");
			val h06_tower   = word("N","Tower");

			// create the tree structure for the text;
			val tt =
			_(
				t00_john
				,
				_(
					t01_said
					,
					_(
						t02_that
						,
						_(
							_(
								t03_the
								,
								t04_car
							)
							,
							_(
								t05_crashed
								,
								_(
									t06_into
									,
									_(
										t07_the
										,
										_(
											t08_dom
											,
											t09_tower
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
					h01_car
				)
				,
				_(
					h02_crashed
					,
					_(
						h03_into
						,
						_(
							h04_the
							,
							_(
								h05_dom
								,
								h06_tower
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
