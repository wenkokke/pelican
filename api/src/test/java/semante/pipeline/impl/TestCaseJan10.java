package semante.pipeline.impl;

import semante.pipeline.Entailment;
import org.junit.Test;
import lombok.val;

public final class TestCaseJan10 extends ATestCase {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createJan10());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("Jan10",createJan10());
		}

		public final Entailment createJan10() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","the");
			val t01_man = word("N_1","man");
			val t02_who = word("WHO_R","who");
			val t03_killed = word("V_2","killed");
			val t04_john = word("NP","john");
			val t05_loves = word("V_2","loves");
			val t06_the = word("THE","the");
			val t07_woman = word("N_1","woman");
			val t08_who = word("WHO_R","who");
			val t09_killed = word("V_2","killed");
			val t10_mary = word("NP","mary");

			// create the vocabulary for the hypothesis;
			val h00_the = word("THE","the");
			val h01_man = word("N_1","man");
			val h02_loves = word("V_2","loves");
			val h03_the = word("THE","the");
			val h04_woman = word("N_1","woman");
			val h05_who = word("WHO_R","who");
			val h06_killed = word("V_2","killed");
			val h07_mary = word("NP","mary");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_the
					,
					_(
						t01_man
						,
						_(
							t02_who
							,
							_(
								t03_killed
								,
								t04_john
							)
						)
					)
				)
				,
				_(
					t05_loves
					,
					_(
						t06_the
						,
						_(
							t07_woman
							,
							_(
								t08_who
								,
								_(
									t09_killed
									,
									t10_mary
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
					h01_man
				)
				,
				_(
					h02_loves
					,
					_(
						h03_the
						,
						_(
							h04_woman
							,
							_(
								h05_who
								,
								_(
									h06_killed
									,
									h07_mary
								)
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

