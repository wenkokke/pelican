package semante.pipeline.test.rte.shared2.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0040 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0040() throws Exception {

			// create the vocabulary for the text;
			val t01_everybody = word("NP_D","Everybody",1);
			val t02_knows = word("FACT","knows",2);
			val t03_that = word("IGNORE","that",3);
			val t04_the = word("THE","the",4);
			val t49_mona_lisa = word("NP_D","Mona_Lisa",49);
			val t64_app = word("WHO_A","APP",64);
			val t08_a = word("A","a",8);
			val t09_famous = word("MI","famous",9);
			val t10_painting = word("N","painting",10);
			val t11_which = word("WHO_R","which",11);
			val t12_was = word("IS","was",12);
			val t13_painted = word("V_1","painted",13);
			val t14_by = word("P_R","by",14);
			val t54_leonardo_da_vinci = word("NP_D","Leonardo_da_Vinci",54);
			val t19_hangs = word("V_1","hangs",19);
			val t20_in = word("P_R","in",20);
			val t21_the = word("THE","the",21);
			val t22_louvre = word("MR","Louvre",22);
			val t23_museum = word("N","Museum",23);
			val t61_app = word("WHO_A","APP",61);
			val t25_a = word("A","a",25);
			val t26_parisian = word("MI","Parisian",26);
			val t27_landmark = word("N","landmark",27);

			// create the vocabulary for the hypothesis;
			val h01_the = word("THE","The",1);
			val h26_mona_lisa = word("NP_D","Mona_Lisa",26);
			val h31_app = word("WHO_A","APP",31);
			val h05_painted = word("V_1","painted",5);
			val h06_by = word("P_R","by",6);
			val h17_da_vinci = word("NP_D","da_Vinci",17);
			val h10_is = word("IS","is",10);
			val h11_displayed = word("V_1","displayed",11);
			val h12_in = word("P_R","in",12);
			val h13_a = word("A","a",13);
			val h14_parisian = word("MI","Parisian",14);
			val h15_museum = word("N","museum",15);

			// create the tree structure for the text;
			val tt =
			_(
				t01_everybody
				,
				_(
					t02_knows
					,
					_(
						t03_that
						,
						_(
							_(
								_(
									t04_the
									,
									t49_mona_lisa
									,
									50
								)
								,
								_(
									t64_app
									,
									_(
										t08_a
										,
										_(
											t09_famous
											,
											_(
												t10_painting
												,
												_(
													t11_which
													,
													_(
														t12_was
														,
														_(
															t13_painted
															,
															_(
																t14_by
																,
																t54_leonardo_da_vinci
																,
																33
															)
															,
															34
														)
														,
														35
													)
													,
													37
												)
												,
												51
											)
											,
											63
										)
										,
										52
									)
									,
									65
								)
								,
								55
							)
							,
							_(
								t19_hangs
								,
								_(
									t20_in
									,
									_(
										_(
											t21_the
											,
											_(
												t22_louvre
												,
												t23_museum
												,
												56
											)
											,
											57
										)
										,
										_(
											t61_app
											,
											_(
												t25_a
												,
												_(
													t26_parisian
													,
													t27_landmark
													,
													58
												)
												,
												59
											)
											,
											62
										)
										,
										60
									)
									,
									43
								)
								,
								44
							)
							,
							45
						)
						,
						46
					)
					,
					47
				)
				,
				48
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					_(
						h01_the
						,
						h26_mona_lisa
						,
						27
					)
					,
					_(
						h31_app
						,
						_(
							h05_painted
							,
							_(
								h06_by
								,
								h17_da_vinci
								,
								18
							)
							,
							19
						)
						,
						32
					)
					,
					28
				)
				,
				_(
					h10_is
					,
					_(
						h11_displayed
						,
						_(
							h12_in
							,
							_(
								h13_a
								,
								_(
									h14_parisian
									,
									h15_museum
									,
									29
								)
								,
								30
							)
							,
							22
						)
						,
						23
					)
					,
					24
				)
				,
				25
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(54,17));
		subs.add(new IPair<Integer,Integer>(19,11));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
