package semante.pipeline.rte;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class TestCaseRTE1dev63 extends APipelineTest {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createRTE1dev63());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("RTE1dev63",createRTE1dev63());
		}

		public final Entailment createRTE1dev63() throws Exception {

			// create the vocabulary for the text;
			val t00_iran = word("NP","iran");
			val t01_will = word("V_AUX","will");
			val t02_release = word("V_2","release");
			val t03_eight = word("NUMBER","eight");
			val t04_british = word("MOD_R","british");
			val t05_servicemen = word("N_1","servicemen");
			val t06_who = word("WHO_R","who");
			val t07_were = word("V_AUX","were");
			val t08_detained = word("V_1","detained");
			val t09_along_with = word("P_R","along with");
			val t10_three = word("NUMBER","three");
			val t11_vessels = word("N_1","vessels");

			// create the vocabulary for the hypothesis;
			val h00_some = word("SOME","some");
			val h01_british = word("MOD_R","british");
			val h02_servicemen = word("N_1","servicemen");
			val h03_were = word("V_AUX","were");
			val h04_detained = word("V_1","detained");

			// create the tree structure for the text;
			val tt =
			_(
				t00_iran
				,
				_(
					t01_will
					,
					_(
						t02_release
						,
						_(
							t03_eight
							,
							_(
								_(
									t04_british
									,
									t05_servicemen
								)
								,
								_(
									t06_who
									,
									_(
										t07_were
										,
										_(
											t08_detained
											,
											_(
												t09_along_with
												,
												_(
													t10_three
													,
													t11_vessels
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
					h00_some
					,
					_(
						h01_british
						,
						h02_servicemen
					)
				)
				,
				_(
					h03_were
					,
					h04_detained
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

