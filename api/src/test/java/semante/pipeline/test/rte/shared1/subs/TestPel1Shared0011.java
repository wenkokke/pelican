package semante.pipeline.test.rte.shared1.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel1Shared0011 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel1Shared0011() throws Exception {

			// create the vocabulary for the text;
			val t01_thomas = word("NP_D","Thomas",1);
			val t03_who = word("WHO_A","who",3);
			val t04_captained = word("V_2","captained",4);
			val t05_wales = word("NP_D","Wales",5);
			val t06_to = word("P_R","to",6);
			val t07_the = word("THE","the",7);
			val t08_num2005 = word("MI","num2005",8);
			val t58_grand_slam = word("NP_D","Grand_Slam",58);
			val t11_and = word("AND","and",11);
			val t12_skippered = word("V_2","skippered",12);
			val t13_the = word("THE","the",13);
			val t14_welsh = word("MI","Welsh",14);
			val t15_side = word("N","side",15);
			val t16_against = word("P_R","against",16);
			val t38_new_zealand = word("NP_D","New_Zealand",38);
			val t19_in = word("P_R","in",19);
			val t20_cardiff = word("NP_D","Cardiff",20);
			val t21_last = word("P_R","last",21);
			val t22_saturday = word("NP_D","Saturday",22);
			val t24_now = word("MR","now",24);
			val t25_plays = word("V_1","plays",25);
			val t26_for = word("P_R","for",26);
			val t75_det = word("A","DET",75);
			val t27_french = word("MI","French",27);
			val t28_champions = word("N","champions",28);
			val t77_app = word("WHO_A","APP",77);
			val t29_toulouse = word("NP_D","Toulouse",29);

			// create the vocabulary for the hypothesis;
			val h01_thomas = word("NP_D","Thomas",1);
			val h02_captained = word("V_2","captained",2);
			val h03_the = word("THE","the",3);
			val h04_welsh = word("MI","Welsh",4);
			val h05_side = word("N","side",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_thomas
					,
					_(
						t03_who
						,
						_(
							_(
								_(
									t04_captained
									,
									t05_wales
									,
									57
								)
								,
								_(
									t06_to
									,
									_(
										t07_the
										,
										_(
											t08_num2005
											,
											t58_grand_slam
											,
											59
										)
										,
										60
									)
									,
									35
								)
								,
								61
							)
							,
							_(
								t11_and
								,
								_(
									_(
										_(
											_(
												t12_skippered
												,
												_(
													t13_the
													,
													_(
														t14_welsh
														,
														t15_side
														,
														63
													)
													,
													64
												)
												,
												65
											)
											,
											_(
												t16_against
												,
												t38_new_zealand
												,
												43
											)
											,
											68
										)
										,
										_(
											t19_in
											,
											t20_cardiff
											,
											40
										)
										,
										62
									)
									,
									_(
										t21_last
										,
										t22_saturday
										,
										41
									)
									,
									74
								)
								,
								73
							)
							,
							72
						)
						,
						47
					)
					,
					70
				)
				,
				_(
					t24_now
					,
					_(
						t25_plays
						,
						_(
							t26_for
							,
							_(
								_(
									t75_det
									,
									_(
										t27_french
										,
										t28_champions
										,
										50
									)
									,
									76
								)
								,
								_(
									t77_app
									,
									t29_toulouse
									,
									78
								)
								,
								52
							)
							,
							53
						)
						,
						54
					)
					,
					56
				)
				,
				71
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_thomas
				,
				_(
					h02_captained
					,
					_(
						h03_the
						,
						_(
							h04_welsh
							,
							h05_side
							,
							11
						)
						,
						12
					)
					,
					9
				)
				,
				13
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(12,2));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
