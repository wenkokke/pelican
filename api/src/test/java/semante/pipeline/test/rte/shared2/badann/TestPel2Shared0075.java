package semante.pipeline.test.rte.shared2.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0075 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0075() throws Exception {

			// create the vocabulary for the text;
			val t58_det = word("EMPTYDET","DET",58);
			val t01_representatives = word("N","Representatives",1);
			val t02_from = word("P_R","from",2);
			val t03_several = word("SOME","several",3);
			val t04_nations = word("N","nations",4);
			val t05_will = word("V_AUX","will",5);
			val t45_sit_down = word("V_1","sit_down",45);
			val t08_in = word("P_R","in",8);
			val t09_doha = word("NP_D","Doha",9);
			val t60_app = word("WHO_A","APP",60);
			val t11_qatar = word("NP_D","Qatar",11);
			val t13_for = word("P_R","for",13);
			val t14_the = word("THE","the",14);
			val t15_fourth = word("MR","fourth",15);
			val t16_ministerial = word("IGNORE","Ministerial",16);
			val t17_conference = word("N","Conference",17);
			val t18_of = word("P_R","of",18);
			val t19_the = word("THE","the",19);
			val t52_world_trade_organization = word("NP_D","World_Trade_Organization",52);
			val t63_app = word("WHO_A","APP",63);
			val t23_ = word("IGNORE","",23);
			val t24_wto = word("NP_D","WTO",24);
			val t25_ = word("IGNORE","",25);

			// create the vocabulary for the hypothesis;
			val h39_det = word("EMPTYDET","DET",39);
			val h01_representatives = word("N","Representatives",1);
			val h02_of = word("P_R","of",2);
			val h03_the = word("THE","the",3);
			val h36_member_countries = word("N","member_countries",36);
			val h06_will = word("V_AUX","will",6);
			val h07_gather = word("V_1","gather",7);
			val h08_in = word("P_R","in",8);
			val h09_qatar = word("NP_D","Qatar",9);
			val h10_for = word("P_R","for",10);
			val h11_the = word("THE","the",11);
			val h12_fourth = word("MR","fourth",12);
			val h13_conference = word("N","conference",13);
			val h14_of = word("P_R","of",14);
			val h15_the = word("THE","the",15);
			val h16_wto = word("NP_D","WTO",16);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t58_det
					,
					_(
						t01_representatives
						,
						_(
							t02_from
							,
							_(
								t03_several
								,
								t04_nations
								,
								28
							)
							,
							29
						)
						,
						30
					)
					,
					59
				)
				,
				_(
					t05_will
					,
					_(
						_(
							t45_sit_down
							,
							_(
								t08_in
								,
								_(
									t09_doha
									,
									_(
										t60_app
										,
										t11_qatar
										,
										61
									)
									,
									46
								)
								,
								33
							)
							,
							47
						)
						,
						_(
							t13_for
							,
							_(
								t14_the
								,
								_(
									t15_fourth
									,
									_(
										t16_ministerial
										,
										_(
											t17_conference
											,
											_(
												t18_of
												,
												_(
													t19_the
													,
													_(
														t52_world_trade_organization
														,
														_(
															t63_app
															,
															_(
																t23_
																,
																_(
																	t24_wto
																	,
																	t25_
																	,
																	55
																)
																,
																65
															)
															,
															64
														)
														,
														53
													)
													,
													66
												)
												,
												39
											)
											,
											48
										)
										,
										62
									)
									,
									49
								)
								,
								50
							)
							,
							41
						)
						,
						56
					)
					,
					43
				)
				,
				57
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h39_det
					,
					_(
						h01_representatives
						,
						_(
							h02_of
							,
							_(
								h03_the
								,
								h36_member_countries
								,
								37
							)
							,
							20
						)
						,
						21
					)
					,
					40
				)
				,
				_(
					h06_will
					,
					_(
						_(
							h07_gather
							,
							_(
								h08_in
								,
								h09_qatar
								,
								23
							)
							,
							32
						)
						,
						_(
							h10_for
							,
							_(
								h11_the
								,
								_(
									h12_fourth
									,
									_(
										h13_conference
										,
										_(
											h14_of
											,
											_(
												h15_the
												,
												h16_wto
												,
												25
											)
											,
											26
										)
										,
										33
									)
									,
									41
								)
								,
								34
							)
							,
							28
						)
						,
						35
					)
					,
					30
				)
				,
				38
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(45,7));
		subs.add(new IPair<Integer,Integer>(2,2));
		subs.add(new IPair<Integer,Integer>(4,36));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
