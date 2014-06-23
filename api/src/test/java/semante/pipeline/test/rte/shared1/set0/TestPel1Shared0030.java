package semante.pipeline.test.rte.shared1.set0;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0030 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0030() throws Exception {

			// create the vocabulary for the text;
			val t00_a = word("A","A");
			val t01_senior = word("MR","senior");
			val t02_coalition = word("$NC_1$","coalition");
			val t03_official = word("N","official");
			val t04_in = word("P_R","in");
			val t05_iraq = word("NP_D","Iraq");
			val t06_said = word("FACT","said");
			val t07_the = word("THE","the");
			val t08_body = word("N","body");
			val t09_which = word("WHO_A","which");
			val t10_was = word("V_AUX","was");
			val t11_found = word("V_1","found");
			val t12_by = word("P_R","by");
			val t13_us = word("$NPC_1$","US");
			val t14_military = word("$NPC_1$","military");
			val t15_police = word("NP_D","police");
			val t16_in = word("P_R","in");
			val t17_the = word("THE","the");
			val t18_west = word("N","west");
			val t19_of = word("P_R","of");
			val t20_baghdad = word("NP_D","Baghdad");
			val t21_was = word("V_AUX","was");
			val t22_thrown = word("V_1","thrown");
			val t23_from = word("P_R","from");
			val t24_a = word("A","a");
			val t25_vehicle = word("N","vehicle");

			// create the vocabulary for the hypothesis;
			val h00_a = word("A","A");
			val h01_body = word("N","body");
			val h02_has = word("V_AUX","has");
			val h03_been = word("V_AUX","been");
			val h04_found = word("V_1","found");
			val h05_by = word("P_R","by");
			val h06_us = word("$NPC_1$","US");
			val h07_military = word("$NPC_1$","military");
			val h08_police = word("NP_D","police");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_a
					,
					_(
						_(
							t01_senior
							,
							_(
								t02_coalition
								,
								t03_official
							)
						)
						,
						_(
							t04_in
							,
							t05_iraq
						)
					)
				)
				,
				_(
					t06_said
					,
					_(
						_(
							_(
								t07_the
								,
								t08_body
							)
							,
							_(
								t09_which
								,
								_(
									_(
										_(
											t10_was
											,
											t11_found
										)
										,
										_(
											t12_by
											,
											_(
												t13_us
												,
												_(
													t14_military
													,
													t15_police
												)
											)
										)
									)
									,
									_(
										t16_in
										,
										_(
											t17_the
											,
											_(
												t18_west
												,
												_(
													t19_of
													,
													t20_baghdad
												)
											)
										)
									)
								)
							)
						)
						,
						_(
							_(
								t21_was
								,
								t22_thrown
							)
							,
							_(
								t23_from
								,
								_(
									t24_a
									,
									t25_vehicle
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
					h00_a
					,
					h01_body
				)
				,
				_(
					_(
						h02_has
						,
						_(
							h03_been
							,
							h04_found
						)
					)
					,
					_(
						h05_by
						,
						_(
							h06_us
							,
							_(
								h07_military
								,
								h08_police
							)
						)
					)
				)
			)
			;

			// create the subsumption relations;
val subs = of(
);

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, subs, Proof);
		}

}

