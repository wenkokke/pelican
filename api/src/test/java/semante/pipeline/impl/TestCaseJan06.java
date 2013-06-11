package semante.pipeline.impl;

import semante.pipeline.Entailment;
import org.junit.Test;
import lombok.val;

public final class TestCaseJan06 extends ATestCase {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createJan06());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("Jan06",createJan06());
		}

		public final Entailment createJan06() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","the");
			val t01_largest = word("MOD_R","largest");
			val t02_search_engine = word("N_1","search_engine");
			val t03_on_the_web = word("MOD_I","on_the_web");
			val t04_who = word("WHO_A","who");
			val t05_is = word("IS","is");
			val t06_google = word("NP","google");
			val t07_is = word("IS","is");
			val t08_popular = word("MOD_R","popular");

			// create the vocabulary for the hypothesis;
			val h00_google = word("NP","google");
			val h01_is = word("IS","is");
			val h02_on_the_web = word("MOD_I","on_the_web");

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
								t02_search_engine
								,
								t03_on_the_web
							)
						)
					)
					,
					_(
						t04_who
						,
						_(
							t05_is
							,
							t06_google
						)
					)
				)
				,
				_(
					t07_is
					,
					t08_popular
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_google
				,
				_(
					h01_is
					,
					h02_on_the_web
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

