package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0030 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0030() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_largest = word("MR","largest",2);
			val t03_private = word("MR","private",3);
			val t04_employer = word("N","employer",4);
			val t05_of = word("P_R","of",5);
			val t06_the = word("THE","the",6);
			val t07_country = word("N","country",7);
			val t67_app = word("WHO_A","APP",67);
			val t09_wal = word("$NPC_1$","Wal",9);
			val t10_mart = word("NP_D","Mart",10);
			val t12_is = word("IS","is",12);
			val t13_being = word("IS","being",13);
			val t14_sued = word("V_1","sued",14);
			val t15_by = word("P_R","by",15);
			val t16_a = word("A","a",16);
			val t17_number = word("N","number",17);
			val t18_of = word("P_R","of",18);
			val t19_female = word("MR","female",19);
			val t20_employees = word("N","employees",20);
			val t21_who = word("WHO_A","who",21);
			val t22_claim = word("V_2","claim",22);
			val t23_they = word("NP_D","they",23);
			val t24_were = word("IS","were",24);
			val t25_refused = word("V_2","refused",25);
			val t71_det = word("EMPTYDET","DET",71);
			val t26_jobs = word("N","jobs",26);
			val t27_in = word("P_R","in",27);
			val t73_det = word("EMPTYDET","DET",73);
			val t28_management = word("N","management",28);

			// create the vocabulary for the hypothesis;
			val h01_wal = word("$NPC_1$","Wal",1);
			val h02_mart = word("NP_D","Mart",2);
			val h03_is = word("IS","is",3);
			val h04_being = word("IS","being",4);
			val h05_sued = word("V_1","sued",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						_(
							_(
								t02_largest
								,
								_(
									t03_private
									,
									t04_employer
									,
									60
								)
								,
								61
							)
							,
							_(
								t05_of
								,
								_(
									t06_the
									,
									t07_country
									,
									31
								)
								,
								34
							)
							,
							62
						)
						,
						65
					)
					,
					_(
						t67_app
						,
						_(
							t09_wal
							,
							t10_mart
							,
							32
						)
						,
						68
					)
					,
					35
				)
				,
				_(
					_(
						t12_is
						,
						_(
							t13_being
							,
							t14_sued
							,
							55
						)
						,
						56
					)
					,
					_(
						t15_by
						,
						_(
							_(
								t16_a
								,
								_(
									t17_number
									,
									_(
										t18_of
										,
										_(
											t19_female
											,
											t20_employees
											,
											37
										)
										,
										38
									)
									,
									36
								)
								,
								70
							)
							,
							_(
								t21_who
								,
								_(
									t22_claim
									,
									_(
										t23_they
										,
										_(
											t24_were
											,
											_(
												t25_refused
												,
												_(
													t71_det
													,
													_(
														t26_jobs
														,
														_(
															t27_in
															,
															_(
																t73_det
																,
																t28_management
																,
																74
															)
															,
															43
														)
														,
														44
													)
													,
													72
												)
												,
												45
											)
											,
											46
										)
										,
										47
									)
									,
									49
								)
								,
								51
							)
							,
							59
						)
						,
						53
					)
					,
					64
				)
				,
				69
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_wal
					,
					h02_mart
					,
					7
				)
				,
				_(
					h03_is
					,
					_(
						h04_being
						,
						h05_sued
						,
						9
					)
					,
					10
				)
				,
				12
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

