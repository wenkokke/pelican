package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0058 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0058() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_international = word("$NPC_1$","International",2);
			val t03_atomic = word("$NPC_1$","Atomic",3);
			val t04_energy = word("$NPC_1$","Energy",4);
			val t05_agency = word("NP_D","Agency",5);
			val t70_app = word("WHO_A","APP",70);
			val t06_ = word("IGNORE","",6);
			val t07_iaea = word("NP_D","IAEA",7);
			val t08_ = word("IGNORE","",8);
			val t09_has = word("V_2","has",9);
			val t10_a = word("A","a",10);
			val t11_meeting = word("N","meeting",11);
			val t12_in = word("P_R","in",12);
			val t13_the = word("THE","the",13);
			val t14_hall = word("N","hall",14);
			val t15_with = word("P_R","with",15);
			val t77_det = word("EMPTYDET","DET",77);
			val t16_beautiful = word("MI","beautiful",16);
			val t17_wooden = word("MI","wooden",17);
			val t18_paneling = word("N","paneling",18);
			val t19_in = word("P_R","in",19);
			val t20_vienna = word("NP_D","Vienna",20);
			val t74_app = word("WHO_A","APP",74);
			val t22_austria = word("NP_D","Austria",22);
			val t24_on = word("P_R","on",24);
			val t25_september = word("$NPC_1$","September",25);
			val t26_num19 = word("NP_D","num19",26);
			val t28_inviting = word("GER_2","inviting",28);
			val t79_det = word("EMPTYDET","DET",79);
			val t29_members = word("N","members",29);
			val t30_across = word("P_R","across",30);
			val t31_the = word("THE","the",31);
			val t32_globe = word("N","globe",32);

			// create the vocabulary for the hypothesis;
			val h01_the = word("THE","The",1);
			val h02_iaea = word("NP_D","IAEA",2);
			val h03_holds = word("V_2","holds",3);
			val h04_a = word("A","a",4);
			val h05_meeting = word("N","meeting",5);
			val h06_in = word("P_R","in",6);
			val h07_a = word("A","a",7);
			val h08_hall = word("N","hall",8);
			val h09_with = word("P_R","with",9);
			val h34_det = word("EMPTYDET","DET",34);
			val h10_beautiful = word("MI","beautiful",10);
			val h11_wooden = word("MI","wooden",11);
			val h12_paneling = word("N","paneling",12);
			val h13_in = word("P_R","in",13);
			val h14_austria = word("NP_D","Austria",14);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						_(
							t02_international
							,
							_(
								t03_atomic
								,
								_(
									t04_energy
									,
									t05_agency
									,
									56
								)
								,
								57
							)
							,
							58
						)
						,
						59
					)
					,
					_(
						t70_app
						,
						_(
							_(
								t06_
								,
								t07_iaea
								,
								60
							)
							,
							t08_
							,
							61
						)
						,
						71
					)
					,
					36
				)
				,
				_(
					_(
						_(
							t09_has
							,
							_(
								t10_a
								,
								_(
									t11_meeting
									,
									_(
										t12_in
										,
										_(
											t13_the
											,
											_(
												_(
													t14_hall
													,
													_(
														t15_with
														,
														_(
															t77_det
															,
															_(
																t16_beautiful
																,
																_(
																	t17_wooden
																	,
																	t18_paneling
																	,
																	62
																)
																,
																63
															)
															,
															78
														)
														,
														43
													)
													,
													38
												)
												,
												_(
													t19_in
													,
													_(
														t20_vienna
														,
														_(
															t74_app
															,
															t22_austria
															,
															75
														)
														,
														64
													)
													,
													41
												)
												,
												73
											)
											,
											76
										)
										,
										45
									)
									,
									37
								)
								,
								72
							)
							,
							65
						)
						,
						_(
							t24_on
							,
							_(
								t25_september
								,
								t26_num19
								,
								47
							)
							,
							48
						)
						,
						66
					)
					,
					_(
						t28_inviting
						,
						_(
							t79_det
							,
							_(
								t29_members
								,
								_(
									t30_across
									,
									_(
										t31_the
										,
										t32_globe
										,
										50
									)
									,
									51
								)
								,
								80
							)
							,
							81
						)
						,
						67
					)
					,
					69
				)
				,
				55
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_the
					,
					h02_iaea
					,
					16
				)
				,
				_(
					h03_holds
					,
					_(
						h04_a
						,
						_(
							h05_meeting
							,
							_(
								h06_in
								,
								_(
									h07_a
									,
									_(
										_(
											h08_hall
											,
											_(
												h09_with
												,
												_(
													h34_det
													,
													_(
														h10_beautiful
														,
														_(
															h11_wooden
															,
															h12_paneling
															,
															29
														)
														,
														30
													)
													,
													35
												)
												,
												23
											)
											,
											18
										)
										,
										_(
											h13_in
											,
											h14_austria
											,
											21
										)
										,
										32
									)
									,
									36
								)
								,
								25
							)
							,
							17
						)
						,
						33
					)
					,
					27
				)
				,
				31
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(9,3));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

