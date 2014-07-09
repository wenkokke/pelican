package semante.pipeline.test.rte.shared1;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0010 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0010() throws Exception {

			// create the vocabulary for the text;
			val t37_foreign_affairs_minister = word("NP_D","Foreign_Affairs_Minister",37);
			val t40_app = word("WHO_A","APP",40);
			val t34_alexander_downer = word("NP_D","Alexander_Downer",34);
			val t06_will = word("V_AUX","will",6);
			val t07_host = word("V_2","host",7);
			val t08_the = word("THE","the",8);
			val t09_num20th = word("MR","num20th",9);
			val t10_annual = word("MR","annual",10);
			val t11_ausmin = word("MR","AUSMIN",11);
			val t12_conference = word("N","conference",12);
			val t13_at = word("P_R","at",13);
			val t14_the = word("THE","the",14);
			val t32_adelaide_town_hall = word("NP_D","Adelaide_Town_Hall",32);

			// create the vocabulary for the hypothesis;
			val h08_alexander_downer = word("NP_D","Alexander_Downer",8);
			val h03_will = word("V_AUX","will",3);
			val h04_host = word("V_2","host",4);
			val h05_a = word("A","a",5);
			val h06_conference = word("N","conference",6);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t37_foreign_affairs_minister
					,
					_(
						t40_app
						,
						t34_alexander_downer
						,
						41
					)
					,
					38
				)
				,
				_(
					t06_will
					,
					_(
						_(
							t07_host
							,
							_(
								t08_the
								,
								_(
									t09_num20th
									,
									_(
										t10_annual
										,
										_(
											t11_ausmin
											,
											t12_conference
											,
											27
										)
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
							24
						)
						,
						_(
							t13_at
							,
							_(
								t14_the
								,
								t32_adelaide_town_hall
								,
								33
							)
							,
							22
						)
						,
						25
					)
					,
					39
				)
				,
				42
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h08_alexander_downer
				,
				_(
					h03_will
					,
					_(
						h04_host
						,
						_(
							h05_a
							,
							h06_conference
							,
							9
						)
						,
						10
					)
					,
					11
				)
				,
				13
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
