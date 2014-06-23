package semante.pipeline.test.rte.shared1.set0;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0033 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0033() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_visit = word("N","visit");
			val t02_comes = word("V_1","comes");
			val t03_amid = word("P_R","amid");
			val t04_rumors = word("N","rumors");
			val t05_about = word("P_R","about");
			val t06_china = word("NP_D","China");
			val t07_'s = word("GEN","'s");
			val t08_90yearold = word("MI","90YearOld");
			val t09_senior = word("MR","senior");
			val t10_leader = word("N","leader");
			val t11_app = word("WHO_A","APP");
			val t12_deng = word("$NPC_1$","Deng");
			val t13_xiaoping = word("NP_D","Xiaoping");
			val t14_who = word("WHO_A","who");
			val t15_is = word("IS","is");
			val t16_near = word("P_R","near");
			val t17_death = word("NP_D","death");

			// create the vocabulary for the hypothesis;
			val h00_deng = word("$NPC_1$","Deng");
			val h01_xiaoping = word("NP_D","Xiaoping");
			val h02_is = word("IS","is");
			val h03_90 = word("IGNORE","90");
			val h04_years = word("IGNORE","years");
			val h05_old = word("MI","old");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_the
					,
					t01_visit
				)
				,
				_(
					t02_comes
					,
					_(
						t03_amid
						,
						_(
							t04_rumors
							,
							_(
								t05_about
								,
								_(
									_(
										_(
											t06_china
											,
											t07_'s
										)
										,
										_(
											t08_90yearold
											,
											_(
												t09_senior
												,
												t10_leader
											)
										)
									)
									,
									_(
										t11_app
										,
										_(
											_(
												t12_deng
												,
												t13_xiaoping
											)
											,
											_(
												t14_who
												,
												_(
													t15_is
													,
													_(
														t16_near
														,
														t17_death
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
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h00_deng
					,
					h01_xiaoping
				)
				,
				_(
					h02_is
					,
					_(
						h03_90
						,
						_(
							h04_years
							,
							h05_old
						)
					)
				)
			)
			;

			// create the subsumption relations;
			val st000_90yearold = word("MI","90YearOld");
			val sh000_90yearold = word("MI","90YearOld");
val st0 = 
			st000_90yearold
			;
val sh0 = 
			sh000_90yearold
			;
val ss0 = subs(st0, sh0);
val subs = of(
			ss0
);

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, subs, Proof);
		}

}

