package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0036 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0036() throws Exception {

			// create the vocabulary for the text;
			val t01_an = word("A","An",1);
			val t02_aristocrat = word("N","aristocrat",2);
			val t49_app = word("WHO_A","APP",49);
			val t04_the = word("THE","the",4);
			val t05_esteemed = word("MR","esteemed",5);
			val t06_baron = word("N","Baron",6);
			val t51_app = word("WHO_A","APP",51);
			val t07_pierre = word("$NPC_1$","Pierre",7);
			val t08_de = word("$NPC_1$","de",8);
			val t09_coubertin = word("NP_D","Coubertin",9);
			val t11_founded = word("V_2","founded",11);
			val t12_the = word("THE","the",12);
			val t13_international = word("$NPC_1$","International",13);
			val t14_olympic = word("$NPC_1$","Olympic",14);
			val t15_committee = word("NP_D","Committee",15);
			val t17_earning = word("GER_2","earning",17);
			val t18_a = word("A","a",18);
			val t19_place = word("N","place",19);
			val t20_in = word("P_R","in",20);
			val t21_history = word("N","history",21);

			// create the vocabulary for the hypothesis;
			val h01_the = word("THE","The",1);
			val h02_aristocrat = word("N","aristocrat",2);
			val h19_app = word("WHO_A","APP",19);
			val h03_pierre = word("$NPC_1$","Pierre",3);
			val h04_de = word("$NPC_1$","de",4);
			val h05_coubertin = word("NP_D","Coubertin",5);
			val h06_was = word("IS","was",6);
			val h07_a = word("A","a",7);
			val h08_baron = word("N","baron",8);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_an
						,
						t02_aristocrat
						,
						23
					)
					,
					_(
						t49_app
						,
						_(
							_(
								t04_the
								,
								_(
									t05_esteemed
									,
									t06_baron
									,
									26
								)
								,
								27
							)
							,
							_(
								t51_app
								,
								_(
									t07_pierre
									,
									_(
										t08_de
										,
										t09_coubertin
										,
										44
									)
									,
									45
								)
								,
								52
							)
							,
							50
						)
						,
						53
					)
					,
					47
				)
				,
				_(
					_(
						t11_founded
						,
						_(
							t12_the
							,
							_(
								t13_international
								,
								_(
									t14_olympic
									,
									t15_committee
									,
									37
								)
								,
								38
							)
							,
							39
						)
						,
						40
					)
					,
					_(
						t17_earning
						,
						_(
							t18_a
							,
							_(
								t19_place
								,
								_(
									t20_in
									,
									t21_history
									,
									32
								)
								,
								30
							)
							,
							54
						)
						,
						41
					)
					,
					43
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
						h02_aristocrat
						,
						17
					)
					,
					_(
						h19_app
						,
						_(
							h03_pierre
							,
							_(
								h04_de
								,
								h05_coubertin
								,
								14
							)
							,
							15
						)
						,
						20
					)
					,
					18
				)
				,
				_(
					h06_was
					,
					_(
						h07_a
						,
						h08_baron
						,
						11
					)
					,
					12
				)
				,
				21
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

