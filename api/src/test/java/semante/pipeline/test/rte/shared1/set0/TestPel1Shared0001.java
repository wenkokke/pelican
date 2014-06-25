package semante.pipeline.test.rte.shared1.set0;

import static com.google.common.collect.ImmutableList.of;
import static semante.pipeline.ResultType.Proof;
import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTestWithLegacy24;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;

public final class TestPel1Shared0001 extends AbsPipelineTestWithLegacy24 {	
	
		@Test
		public final void testPel1Shared0001() throws Exception {

			// create the vocabulary for the text;
			val t00_jessica = word("$NPC_1$","Jessica");
			val t01_litman = word("NP_D","Litman");
			val t02_app = word("WHO_A","APP");
			val t03_a = word("A","a");
			val t04_law = word("$NC_1$","law");
			val t05_professor = word("N","professor");
			val t06_at = word("P_R","at");
			val t07_michigan = word("NP_D","Michigan");
			val t08_s = word("POSS","s");
			val t09_wayne = word("MR","Wayne");
			val t10_state = word("MR","State");
			val t11_university = word("N","University");
			val t12_has = word("V_AUX","has");
			val t13_specialized = word("V_1","specialized");
			val t14_in = word("P_R","in");
			val t15_det = word("A","DET");
			val t16_copyright = word("$NC_1$","copyright");
			val t17_law = word("N","law");
			val t18_for = word("P_R","for");
			val t19_det = word("A","DET");
			val t20_num20 = word("MR","num20");
			val t21_years = word("N","years");

			// create the vocabulary for the hypothesis;
			val h00_jessica = word("$NPC_1$","Jessica");
			val h01_litman = word("NP_D","Litman");
			val h02_is = word("IS","is");
			val h03_a = word("A","a");
			val h04_law = word("$NC_1$","law");
			val h05_professor = word("N","professor");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_jessica
						,
						t01_litman
					)
					,
					_(
						t02_app
						,
						_(
							t03_a
							,
							_(
								_(
									t04_law
									,
									t05_professor
								)
								,
								_(
									t06_at
									,
									_(
										_(
											t07_michigan
											,
											t08_s
										)
										,
										_(
											t09_wayne
											,
											_(
												t10_state
												,
												t11_university
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
					t12_has
					,
					_(
						_(
							t13_specialized
							,
							_(
								t14_in
								,
								_(
									t15_det
									,
									_(
										t16_copyright
										,
										t17_law
									)
								)
							)
						)
						,
						_(
							t18_for
							,
							_(
								t19_det
								,
								_(
									t20_num20
									,
									t21_years
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
					h00_jessica
					,
					h01_litman
				)
				,
				_(
					h02_is
					,
					_(
						h03_a
						,
						_(
							h04_law
							,
							h05_professor
						)
					)
				)
			)
			;

			// create the subsumption relations;
Iterable<Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>> subs = of(
);

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

