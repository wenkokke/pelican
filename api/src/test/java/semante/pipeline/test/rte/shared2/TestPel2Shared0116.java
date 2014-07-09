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
			val t30_john_bok = word("NP_D","John_Bok",30);
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
			val t47_stanislav_gross = word("NP_D","Stanislav_Gross",47);

			// create the vocabulary for the hypothesis;
			val h01_angered = word("MI","Angered",1);
			val h25_john_bok = word("NP_D","John_Bok",25);
			val h04_has = word("V_AUX","has",4);
			val h05_been = word("IS","been",5);
			val h06_on = word("P_R","on",6);
			val h07_a = word("A","a",7);
			val h21_hunger_strike = word("N","hunger_strike",21);
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
							t30_john_bok
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
										t47_stanislav_gross
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
					h25_john_bok
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
									h21_hunger_strike
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
