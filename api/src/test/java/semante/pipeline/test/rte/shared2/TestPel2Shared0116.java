package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0116 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0116() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_angered = word("MI","angered",2);
			val t03_dissident = word("N","dissident",3);
			val t60_app = word("WHO_A","APP",60);
			val t05_john = word("$NPC_1$","John",5);
			val t06_bok = word("NP_D","Bok",6);
			val t08_who = word("WHO_A","who",8);
			val t09_has = word("V_AUX","has",9);
			val t10_been = word("IS","been",10);
			val t11_on = word("P_R","on",11);
			val t12_a = word("A","a",12);
			val t13_hunger = word("MR","hunger",13);
			val t14_strike = word("N","strike",14);
			val t15_since = word("P_I","since",15);
			val t16_monday = word("NP_D","Monday",16);
			val t18_wants = word("V_2","wants",18);
			val t19_the = word("THE","the",19);
			val t20_resignation = word("N","resignation",20);
			val t21_of = word("P_R","of",21);
			val t22_the = word("THE","the",22);
			val t23_prime = word("MR","prime",23);
			val t24_minister = word("N","minister",24);
			val t63_app = word("WHO_A","APP",63);
			val t25_stanislav = word("$NPC_1$","Stanislav",25);
			val t26_gross = word("NP_D","Gross",26);

			// create the vocabulary for the hypothesis;
			val h01_angered = word("MI","Angered",1);
			val h02_john = word("$NPC_1$","John",2);
			val h03_bok = word("NP_D","Bok",3);
			val h04_has = word("V_AUX","has",4);
			val h05_been = word("IS","been",5);
			val h06_on = word("P_R","on",6);
			val h07_a = word("A","a",7);
			val h08_hunger = word("$NC_1$","hunger",8);
			val h09_strike = word("N","strike",9);
			val h10_since = word("P_I","since",10);
			val h11_monday = word("NP_D","Monday",11);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						_(
							t01_the
							,
							_(
								t02_angered
								,
								t03_dissident
								,
								51
							)
							,
							52
						)
						,
						_(
							t60_app
							,
							_(
								t05_john
								,
								t06_bok
								,
								30
							)
							,
							61
						)
						,
						53
					)
					,
					_(
						t08_who
						,
						_(
							t09_has
							,
							_(
								_(
									t10_been
									,
									_(
										t11_on
										,
										_(
											t12_a
											,
											_(
												t13_hunger
												,
												t14_strike
												,
												54
											)
											,
											55
										)
										,
										33
									)
									,
									56
								)
								,
								_(
									t15_since
									,
									t16_monday
									,
									35
								)
								,
								57
							)
							,
							37
						)
						,
						39
					)
					,
					58
				)
				,
				_(
					t18_wants
					,
					_(
						t19_the
						,
						_(
							t20_resignation
							,
							_(
								t21_of
								,
								_(
									_(
										t22_the
										,
										_(
											t23_prime
											,
											t24_minister
											,
											49
										)
										,
										50
									)
									,
									_(
										t63_app
										,
										_(
											t25_stanislav
											,
											t26_gross
											,
											47
										)
										,
										64
									)
									,
									43
								)
								,
								65
							)
							,
							41
						)
						,
						62
					)
					,
					45
				)
				,
				59
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_angered
					,
					_(
						h02_john
						,
						h03_bok
						,
						25
					)
					,
					26
				)
				,
				_(
					h04_has
					,
					_(
						_(
							h05_been
							,
							_(
								h06_on
								,
								_(
									h07_a
									,
									_(
										h08_hunger
										,
										h09_strike
										,
										21
									)
									,
									22
								)
								,
								15
							)
							,
							23
						)
						,
						_(
							h10_since
							,
							h11_monday
							,
							17
						)
						,
						24
					)
					,
					19
				)
				,
				27
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

