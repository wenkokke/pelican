package semante.pipeline.test.rte;

import lombok.val;

import org.junit.Test;

import semante.pipeline.test.Entailment;
import semante.pipeline.test.impl.ATestCase;
import semante.pipeline.test.impl.IEntailment;

public final class TestCaseRTE2test750 extends ATestCase {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createRTE2test750());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("RTE2test750",createRTE2test750());
		}

		public final Entailment createRTE2test750() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","the");
			val t01_zulu = word("N_1","zulu");
			val t02_who = word("WHO_R","who");
			val t03_live = word("V_1","live");
			val t04_in = word("P_R","in");
			val t05_the = word("THE","the");
			val t06_kwazulu_natal = word("MOD_R","kwazulu natal");
			val t07_province = word("N_1","province");
			val t08_of = word("P_R","of");
			val t09_south_africa = word("NP","south_africa");
			val t10_are = word("IS","are");
			val t11_an = word("A","an");
			val t12_african = word("MOD_R","african");
			val t13_ethnic = word("MOD_R","ethnic");
			val t14_group = word("N_1","group");
			val t15_of = word("P_R","of");
			val t16_about = word("MOD_R","about");
			val t17_11_million = word("NUMBER","11 million");
			val t18_people = word("N_1","people");

			// create the vocabulary for the hypothesis;
			val h00_zulus = word("NP","zulus");
			val h01_live = word("V_1","live");
			val h02_in = word("P_R","in");
			val h03_the = word("THE","the");
			val h04_kwazulu_natal = word("MOD_R","kwazulu natal");
			val h05_province = word("N_1","province");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_the
					,
					_(
						t01_zulu
						,
						_(
							t02_who
							,
							_(
								t03_live
								,
								_(
									t04_in
									,
									_(
										t05_the
										,
										_(
											_(
												t06_kwazulu_natal
												,
												t07_province
											)
											,
											_(
												t08_of
												,
												t09_south_africa
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
					t10_are
					,
					_(
						t11_an
						,
						_(
							_(
								t12_african
								,
								_(
									t13_ethnic
									,
									t14_group
								)
							)
							,
							_(
								t15_of
								,
								_(
									t16_about
									,
									_(
										t17_11_million
										,
										t18_people
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
				h00_zulus
				,
				_(
					h01_live
					,
					_(
						h02_in
						,
						_(
							h03_the
							,
							_(
								h04_kwazulu_natal
								,
								h05_province
							)
						)
					)
				)
			)
			;

			// create the subsumption relations;
			val ss =
			new String[] {
				"all x ((ethnic_group(x) & zulu(x)) -> x = zulus)."
			};

			// return the new entailment;
			return new IEntailment(tt, th, ss);
		}

}

