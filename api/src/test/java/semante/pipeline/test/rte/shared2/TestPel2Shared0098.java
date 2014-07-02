package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0098 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0098() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_ukrainian = word("MR","Ukrainian",2);
			val t03_election = word("$NC_1$","election",3);
			val t04_commission = word("N","commission",4);
			val t05_declared = word("FACT","declared",5);
			val t06_that = word("IGNORE","that",6);
			val t07_the = word("THE","the",7);
			val t08_candidate = word("N","candidate",8);
			val t47_app = word("WHO_A","APP",47);
			val t10_the = word("THE","the",10);
			val t11_jubilant = word("MI","jubilant",11);
			val t12_viktor = word("$NPC_1$","Viktor",12);
			val t13_yanukovych = word("NP_D","Yanukovych",13);
			val t15_had = word("V_AUX","had",15);
			val t16_won = word("V_2","won",16);
			val t17_the = word("THE","the",17);
			val t18_bitterly = word("MR","bitterly",18);
			val t19_disputed = word("MR","disputed",19);
			val t20_presidential = word("MR","presidential",20);
			val t21_election = word("N","election",21);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_jubilant = word("MI","jubilant",2);
			val h03_candidate = word("N","candidate",3);
			val h04_won = word("V_2","won",4);
			val h05_the = word("THE","the",5);
			val h06_election = word("N","election",6);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					_(
						t02_ukrainian
						,
						_(
							t03_election
							,
							t04_commission
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
					t05_declared
					,
					_(
						t06_that
						,
						_(
							_(
								_(
									t07_the
									,
									t08_candidate
									,
									24
								)
								,
								_(
									t47_app
									,
									_(
										t10_the
										,
										_(
											t11_jubilant
											,
											_(
												t12_viktor
												,
												t13_yanukovych
												,
												36
											)
											,
											37
										)
										,
										38
									)
									,
									48
								)
								,
								39
							)
							,
							_(
								_(
									t15_had
									,
									t16_won
									,
									31
								)
								,
								_(
									t17_the
									,
									_(
										t18_bitterly
										,
										_(
											t19_disputed
											,
											_(
												t20_presidential
												,
												t21_election
												,
												40
											)
											,
											28
										)
										,
										50
									)
									,
									42
								)
								,
								49
							)
							,
							32
						)
						,
						33
					)
					,
					34
				)
				,
				46
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					_(
						h02_jubilant
						,
						h03_candidate
						,
						13
					)
					,
					14
				)
				,
				_(
					h04_won
					,
					_(
						h05_the
						,
						h06_election
						,
						10
					)
					,
					11
				)
				,
				15
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

