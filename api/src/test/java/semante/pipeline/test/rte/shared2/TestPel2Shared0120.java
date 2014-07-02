package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0120 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0120() throws Exception {

			// create the vocabulary for the text;
			val t01_arena = word("NP_D","ARENA",1);
			val t02_made = word("V_2","made",2);
			val t03_an = word("A","an",3);
			val t04_assassination = word("MR","assassination",4);
			val t05_attempt = word("N","attempt",5);
			val t06_against = word("P_R","against",6);
			val t07_mr = word("$NPC_1$","Mr",7);
			val t08_mason = word("NP_D","Mason",8);
			val t55_app = word("WHO_A","APP",55);
			val t10_the = word("THE","the",10);
			val t11_president = word("N","president",11);
			val t12_of = word("P_R","of",12);
			val t13_the = word("THE","the",13);
			val t14_mortgage = word("MR","Mortgage",14);
			val t15_bank = word("N","Bank",15);
			val t17_who = word("WHO_A","who",17);
			val t18_was = word("IS","was",18);
			val t19_not = word("MR","not",19);
			val t20_following = word("V_2","following",20);
			val t21_arena = word("NP_D","ARENA",21);
			val t22_s = word("POSS","s",22);
			val t23_orders = word("N","orders",23);

			// create the vocabulary for the hypothesis;
			val h01_arena = word("NP_D","ARENA",1);
			val h02_made = word("V_2","made",2);
			val h03_an = word("A","an",3);
			val h04_assassination = word("MR","assassination",4);
			val h05_attempt = word("N","attempt",5);
			val h06_against = word("P_R","against",6);
			val h07_the = word("THE","the",7);
			val h08_president = word("N","president",8);
			val h09_of = word("P_R","of",9);
			val h10_a = word("A","a",10);
			val h11_bank = word("N","bank",11);

			// create the tree structure for the text;
			val tt =
			_(
				t01_arena
				,
				_(
					_(
						t02_made
						,
						_(
							t03_an
							,
							_(
								t04_assassination
								,
								t05_attempt
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
					_(
						t06_against
						,
						_(
							_(
								_(
									t07_mr
									,
									t08_mason
									,
									27
								)
								,
								_(
									t55_app
									,
									_(
										t10_the
										,
										_(
											t11_president
											,
											_(
												t12_of
												,
												_(
													t13_the
													,
													_(
														t14_mortgage
														,
														t15_bank
														,
														46
													)
													,
													47
												)
												,
												30
											)
											,
											31
										)
										,
										54
									)
									,
									56
								)
								,
								48
							)
							,
							_(
								t17_who
								,
								_(
									t18_was
									,
									_(
										t19_not
										,
										_(
											t20_following
											,
											_(
												_(
													t21_arena
													,
													t22_s
													,
													33
												)
												,
												t23_orders
												,
												34
											)
											,
											35
										)
										,
										49
									)
									,
									50
								)
								,
								38
							)
							,
							51
						)
						,
						40
					)
					,
					52
				)
				,
				53
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_arena
				,
				_(
					_(
						h02_made
						,
						_(
							h03_an
							,
							_(
								h04_assassination
								,
								h05_attempt
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
					_(
						h06_against
						,
						_(
							h07_the
							,
							_(
								h08_president
								,
								_(
									h09_of
									,
									_(
										h10_a
										,
										h11_bank
										,
										16
									)
									,
									17
								)
								,
								15
							)
							,
							27
						)
						,
						19
					)
					,
					25
				)
				,
				26
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

