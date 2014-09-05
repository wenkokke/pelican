package semante.pipeline.test.rte.shared2.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0012 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0012() throws Exception {

			// create the vocabulary for the text;
			val t61_george_bush = word("NP_D","George_Bush",61);
			val t03_s = word("POSS","s",3);
			val t04_cabinet = word("N","cabinet",4);
			val t76_app = word("WHO_A","APP",76);
			val t06_the = word("THE","the",6);
			val t07_oilhappy = word("MI","oilHappy",7);
			val t08_administration = word("N","administration",8);
			val t09_that = word("WHO_R","that",9);
			val t10_purchased = word("V_2","purchased",10);
			val t74_det = word("SOME","DET",74);
			val t11_num10 = word("MR","num10",11);
			val t12_million = word("MR","million",12);
			val t13_barrels = word("N","barrels",13);
			val t14_of = word("P_R","of",14);
			val t72_det = word("EMPTYDET","DET",72);
			val t15_oil = word("N","oil",15);
			val t16_in = word("P_R","in",16);
			val t17_num2007 = word("NP_D","num2007",17);
			val t19_unexpectedly = word("MR","unexpectedly",19);
			val t20_sold = word("V_2","sold",20);
			val t21_the = word("THE","the",21);
			val t22_crude = word("MR","crude",22);
			val t23_oil = word("N","oil",23);
			val t24_from = word("P_R","from",24);
			val t25_the = word("THE","the",25);
			val t26_us = word("MI","US",26);
			val t55_emergency_stockpile = word("N","emergency_stockpile",55);

			// create the vocabulary for the hypothesis;
			val h01_an = word("A","An",1);
			val h02_oilhappy = word("MI","oilHappy",2);
			val h18_presidential_cabinet = word("N","presidential_cabinet",18);
			val h05_released = word("V_2","released",5);
			val h06_the = word("THE","the",6);
			val h07_crude = word("MR","crude",7);
			val h08_oil = word("N","oil",8);
			val h09_from = word("P_R","from",9);
			val h25_det = word("THE","DET",25);
			val h10_us = word("MI","US",10);
			val h11_reserves = word("N","reserves",11);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						_(
							t61_george_bush
							,
							t03_s
							,
							31
						)
						,
						t04_cabinet
						,
						68
					)
					,
					_(
						t76_app
						,
						_(
							t06_the
							,
							_(
								t07_oilhappy
								,
								_(
									t08_administration
									,
									_(
										t09_that
										,
										_(
											_(
												t10_purchased
												,
												_(
													t74_det
													,
													_(
														t11_num10
														,
														_(
															t12_million
															,
															_(
																t13_barrels
																,
																_(
																	t14_of
																	,
																	_(
																		t72_det
																		,
																		t15_oil
																		,
																		73
																	)
																	,
																	37
																)
																,
																35
															)
															,
															34
														)
														,
														71
													)
													,
													75
												)
												,
												64
											)
											,
											_(
												t16_in
												,
												t17_num2007
												,
												40
											)
											,
											65
										)
										,
										43
									)
									,
									62
								)
								,
								69
							)
							,
							63
						)
						,
						77
					)
					,
					66
				)
				,
				_(
					t19_unexpectedly
					,
					_(
						_(
							t20_sold
							,
							_(
								t21_the
								,
								_(
									t22_crude
									,
									t23_oil
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
						_(
							t24_from
							,
							_(
								t25_the
								,
								_(
									t26_us
									,
									t55_emergency_stockpile
									,
									56
								)
								,
								57
							)
							,
							49
						)
						,
						58
					)
					,
					59
				)
				,
				67
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_an
					,
					_(
						h02_oilhappy
						,
						h18_presidential_cabinet
						,
						19
					)
					,
					20
				)
				,
				_(
					_(
						h05_released
						,
						_(
							h06_the
							,
							_(
								h07_crude
								,
								h08_oil
								,
								21
							)
							,
							22
						)
						,
						23
					)
					,
					_(
						h09_from
						,
						_(
							h25_det
							,
							_(
								h10_us
								,
								h11_reserves
								,
								14
							)
							,
							26
						)
						,
						15
					)
					,
					24
				)
				,
				17
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(4,18));
		subs.add(new IPair<Integer,Integer>(20,5));
		subs.add(new IPair<Integer,Integer>(55,11));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
