package semante.pipeline.test.rte.shared2.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0004 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0004() throws Exception {

			// create the vocabulary for the text;
			val t28_kevin_whitaker = word("NP_D","Kevin_Whitaker",28);
			val t04_who = word("WHO_A","who",4);
			val t05_is = word("IS","is",5);
			val t06_the = word("THE","the",6);
			val t07_head = word("N","head",7);
			val t08_of = word("P_R","of",8);
			val t09_the = word("THE","the",9);
			val t10_cuban = word("MR","Cuban",10);
			val t11_affairs = word("MR","affairs",11);
			val t12_office = word("N","office",12);
			val t13_at = word("P_R","at",13);
			val t14_the = word("THE","the",14);
			val t15_department = word("N","Department",15);
			val t16_of = word("P_R","of",16);
			val t17_state = word("NP_D","State",17);
			val t19_spoke = word("V_1","spoke",19);
			val t20_with = word("P_R","with",20);
			val t21_lazo = word("NP_D","Lazo",21);
			val t23_on = word("P_R","on",23);
			val t64_det = word("EMPTYDET","DET",64);
			val t24_occasion = word("N","occasion",24);
			val t25_about = word("P_R","about",25);
			val t62_det = word("EMPTYDET","DET",62);
			val t26_visas = word("N","visas",26);

			// create the vocabulary for the hypothesis;
			val h10_kevin_whitaker = word("NP_D","Kevin_Whitaker",10);
			val h03_is = word("IS","is",3);
			val h04_the = word("THE","the",4);
			val h05_manager = word("N","manager",5);
			val h06_of = word("P_R","of",6);
			val h07_a = word("A","a",7);
			val h08_department = word("N","department",8);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t28_kevin_whitaker
					,
					_(
						t04_who
						,
						_(
							t05_is
							,
							_(
								t06_the
								,
								_(
									t07_head
									,
									_(
										t08_of
										,
										_(
											t09_the
											,
											_(
												t10_cuban
												,
												_(
													t11_affairs
													,
													_(
														t12_office
														,
														_(
															t13_at
															,
															_(
																t14_the
																,
																_(
																	t15_department
																	,
																	_(
																		t16_of
																		,
																		t17_state
																		,
																		34
																	)
																	,
																	35
																)
																,
																61
															)
															,
															36
														)
														,
														55
													)
													,
													68
												)
												,
												56
											)
											,
											57
										)
										,
										38
									)
									,
									67
								)
								,
								60
							)
							,
							40
						)
						,
						42
					)
					,
					58
				)
				,
				_(
					_(
						t19_spoke
						,
						_(
							t20_with
							,
							t21_lazo
							,
							45
						)
						,
						53
					)
					,
					_(
						t23_on
						,
						_(
							t64_det
							,
							_(
								t24_occasion
								,
								_(
									t25_about
									,
									_(
										t62_det
										,
										t26_visas
										,
										63
									)
									,
									48
								)
								,
								65
							)
							,
							66
						)
						,
						50
					)
					,
					54
				)
				,
				59
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h10_kevin_whitaker
				,
				_(
					h03_is
					,
					_(
						h04_the
						,
						_(
							h05_manager
							,
							_(
								h06_of
								,
								_(
									h07_a
									,
									h08_department
									,
									12
								)
								,
								13
							)
							,
							11
						)
						,
						18
					)
					,
					15
				)
				,
				17
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(12,8));
		subs.add(new IPair<Integer,Integer>(7,5));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
