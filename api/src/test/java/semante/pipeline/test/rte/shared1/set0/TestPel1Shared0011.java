package semante.pipeline.test.rte.shared1.set0;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0011 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0011() throws Exception {

			// create the vocabulary for the text;
			val t00_thomas = word("NP_D","Thomas");
			val t01_who = word("WHO_A","who");
			val t02_captained = word("V_2","captained");
			val t03_wales = word("NP_D","Wales");
			val t04_to = word("P_R","to");
			val t05_the = word("THE","the");
			val t06_num2005 = word("MI","num2005");
			val t07_grand = word("$NPC_1$","Grand");
			val t08_slam = word("NP_D","Slam");
			val t09_and = word("AND","and");
			val t10_skippered = word("V_2","skippered");
			val t11_the = word("THE","the");
			val t12_welsh = word("MI","Welsh");
			val t13_side = word("N","side");
			val t14_against = word("P_R","against");
			val t15_new = word("$NPC_1$","New");
			val t16_zealand = word("NP_D","Zealand");
			val t17_in = word("P_R","in");
			val t18_cardiff = word("NP_D","Cardiff");
			val t19_last = word("P_R","last");
			val t20_saturday = word("NP_D","Saturday");
			val t21_now = word("MR","now");
			val t22_plays = word("V_1","plays");
			val t23_for = word("P_R","for");
			val t24_det = word("A","DET");
			val t25_french = word("MI","French");
			val t26_champions = word("N","champions");
			val t27_app = word("WHO_A","APP");
			val t28_toulouse = word("NP_D","Toulouse");

			// create the vocabulary for the hypothesis;
			val h00_thomas = word("NP_D","Thomas");
			val h01_captained = word("V_2","captained");
			val h02_the = word("THE","the");
			val h03_welsh = word("MI","Welsh");
			val h04_side = word("N","side");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_thomas
					,
					_(
						t01_who
						,
						_(
							_(
								_(
									t02_captained
									,
									t03_wales
								)
								,
								_(
									t04_to
									,
									_(
										t05_the
										,
										_(
											t06_num2005
											,
											_(
												t07_grand
												,
												t08_slam
											)
										)
									)
								)
							)
							,
							_(
								t09_and
								,
								_(
									_(
										_(
											_(
												t10_skippered
												,
												_(
													t11_the
													,
													_(
														t12_welsh
														,
														t13_side
													)
												)
											)
											,
											_(
												t14_against
												,
												_(
													t15_new
													,
													t16_zealand
												)
											)
										)
										,
										_(
											t17_in
											,
											t18_cardiff
										)
									)
									,
									_(
										t19_last
										,
										t20_saturday
									)
								)
							)
						)
					)
				)
				,
				_(
					t21_now
					,
					_(
						t22_plays
						,
						_(
							t23_for
							,
							_(
								_(
									t24_det
									,
									_(
										t25_french
										,
										t26_champions
									)
								)
								,
								_(
									t27_app
									,
									t28_toulouse
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
				h00_thomas
				,
				_(
					h01_captained
					,
					_(
						h02_the
						,
						_(
							h03_welsh
							,
							h04_side
						)
					)
				)
			)
			;

			// create the subsumption relations;
			val st000_skippered = word("V_2","skippered");
			val sh000_skippered = word("V_2","skippered");
val st0 = 
			st000_skippered
			;
val sh0 = 
			sh000_skippered
			;
val ss0 = subs(st0, sh0);
Iterable<Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>> subs = of(
			ss0
);

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

