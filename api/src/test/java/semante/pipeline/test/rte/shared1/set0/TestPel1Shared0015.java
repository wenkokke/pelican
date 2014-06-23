package semante.pipeline.test.rte.shared1.set0;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0015 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0015() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_young = word("MI","young");
			val t02_leader = word("N","leader");
			val t03_of = word("P_R","of");
			val t04_the = word("THE","the");
			val t05_coup = word("N","coup");
			val t06_app = word("WHO_A","APP");
			val t07_pibul = word("$NPC_1$","Pibul");
			val t08_songgram = word("NP_D","Songgram");
			val t09_app = word("WHO_A","APP");
			val t10_educated = word("V_1","educated");
			val t11_in = word("P_R","in");
			val t12_europe = word("NP_D","Europe");
			val t13_and = word("AND","and");
			val t14_influenced = word("V_1","influenced");
			val t15_by = word("P_R","by");
			val t16_det = word("EMPTYDET","DET");
			val t17_western = word("MR","Western");
			val t18_ideas = word("N","ideas");
			val t19_will = word("V_AUX","will");
			val t20_dominate = word("V_2","dominate");
			val t21_det = word("EMPTYDET","DET");
			val t22_thai = word("MI","Thai");
			val t23_politics = word("N","politics");
			val t24_in = word("P_R","in");
			val t25_the = word("THE","the");
			val t26_ensuing = word("MR","ensuing");
			val t27_years = word("N","years");

			// create the vocabulary for the hypothesis;
			val h00_pibul = word("$NPC_1$","Pibul");
			val h01_songgram = word("NP_D","Songgram");
			val h02_was = word("IS","was");
			val h03_a = word("A","a");
			val h04_young = word("MI","young");
			val h05_leader = word("N","leader");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						_(
							t00_the
							,
							_(
								t01_young
								,
								_(
									t02_leader
									,
									_(
										t03_of
										,
										_(
											t04_the
											,
											t05_coup
										)
									)
								)
							)
						)
						,
						_(
							t06_app
							,
							_(
								t07_pibul
								,
								t08_songgram
							)
						)
					)
					,
					_(
						t09_app
						,
						_(
							_(
								t10_educated
								,
								_(
									t11_in
									,
									t12_europe
								)
							)
							,
							_(
								t13_and
								,
								_(
									t14_influenced
									,
									_(
										t15_by
										,
										_(
											t16_det
											,
											_(
												t17_western
												,
												t18_ideas
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
					_(
						t19_will
						,
						_(
							t20_dominate
							,
							_(
								t21_det
								,
								_(
									t22_thai
									,
									t23_politics
								)
							)
						)
					)
					,
					_(
						t24_in
						,
						_(
							t25_the
							,
							_(
								t26_ensuing
								,
								t27_years
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
					h00_pibul
					,
					h01_songgram
				)
				,
				_(
					h02_was
					,
					_(
						h03_a
						,
						_(
							h04_young
							,
							h05_leader
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

