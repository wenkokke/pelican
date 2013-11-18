package semante.flattener.test;

import semante.pipeline.test.Entailment;
import semante.pipeline.test.impl.ATestCase;
import semante.pipeline.test.impl.IEntailment;

import org.junit.Test;
import lombok.val;

public final class RTE1dev176 extends ATestCase {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createRTE1dev176());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("RTE1dev176",createRTE1dev176());
		}

		public final Entailment createRTE1dev176() throws Exception {

			// create the vocabulary for the text;
			val t00_a = word("A","A");
			val t01_korean = word("N","Korean");
			val t02_in = word("P_R","in");
			val t03_iraq = word("NP_D","Iraq");
			val t04_who = word("WHO_R","who");
			val t05_later = word("MR","later");
			val t06_ate = word("V_2","ate");
			val t07_the = word("THE","the");
			val t08_dutch = word("MR","Dutch");
			val t09_meatballs = word("N","meatballs");
			val t10_prayed = word("V_1","prayed");

			// create the vocabulary for the hypothesis;
			val h00_a = word("A","A");
			val h01_korean = word("N","Korean");
			val h02_in = word("P_R","in");
			val h03_iraq = word("NP_D","Iraq");
			val h04_ate = word("V_2","ate");
			val h05_the = word("THE","the");
			val h06_dutch = word("MR","Dutch");
			val h07_meatballs = word("N","meatballs");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_a
					,
					_(
						_(
							t01_korean
							,
							_(
								t02_in
								,
								t03_iraq
							)
						)
						,
						_(
							t04_who
							,
							_(
								t05_later
								,
								_(
									t06_ate
									,
									_(
										t07_the
										,
										_(
											t08_dutch
											,
											t09_meatballs
										)
									)
								)
							)
						)
					)
				)
				,
				t10_prayed
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h00_a
					,
					_(
						h01_korean
						,
						_(
							h02_in
							,
							h03_iraq
						)
					)
				)
				,
				_(
					h04_ate
					,
					_(
						h05_the
						,
						_(
							h06_dutch
							,
							h07_meatballs
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
