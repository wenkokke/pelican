package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0103 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0103() throws Exception {

			// create the vocabulary for the text;
			val t52_det = word("THE","DET",52);
			val t01_nurse = word("N","Nurse",1);
			val t54_app = word("WHO_A","APP",54);
			val t49_jinny_resor = word("NP_D","Jinny_Resor",49);
			val t04_said = word("FACT","said",4);
			val t05_martin = word("NP_D","Martin",5);
			val t06_was = word("IS","was",6);
			val t07_taken = word("V_1","taken",7);
			val t08_to = word("P_R","to",8);
			val t45_ochsner_foundation_hospital = word("NP_D","Ochsner_Foundation_Hospital",45);
			val t56_app = word("WHO_A","APP",56);
			val t13_a = word("A","a",13);
			val t14_hospital = word("N","hospital",14);
			val t15_in = word("P_I","in",15);
			val t28_new_orleans = word("NP_D","New_Orleans",28);
			val t18_and = word("AND","and",18);
			val t19_was = word("IS","was",19);
			val t20_treated = word("V_1","treated",20);
			val t21_for = word("P_R","for",21);
			val t60_det = word("EMPTYDET","DET",60);
			val t22_dehydration = word("N","dehydration",22);

			// create the vocabulary for the hypothesis;
			val h15_ochsner_foundation_hospital = word("NP_D","Ochsner_Foundation_Hospital",15);
			val h04_is = word("IS","is",4);
			val h05_in = word("P_I","in",5);
			val h10_new_orleans = word("NP_D","New_Orleans",10);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t52_det
						,
						t01_nurse
						,
						53
					)
					,
					_(
						t54_app
						,
						t49_jinny_resor
						,
						55
					)
					,
					50
				)
				,
				_(
					t04_said
					,
					_(
						t05_martin
						,
						_(
							_(
								t06_was
								,
								_(
									t07_taken
									,
									_(
										t08_to
										,
										_(
											t45_ochsner_foundation_hospital
											,
											_(
												t56_app
												,
												_(
													t13_a
													,
													_(
														t14_hospital
														,
														_(
															t15_in
															,
															t28_new_orleans
															,
															29
														)
														,
														30
													)
													,
													62
												)
												,
												57
											)
											,
											46
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
							_(
								t18_and
								,
								_(
									t19_was
									,
									_(
										t20_treated
										,
										_(
											t21_for
											,
											_(
												t60_det
												,
												t22_dehydration
												,
												61
											)
											,
											39
										)
										,
										40
									)
									,
									41
								)
								,
								47
							)
							,
							35
						)
						,
						59
					)
					,
					37
				)
				,
				51
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h15_ochsner_foundation_hospital
				,
				_(
					h04_is
					,
					_(
						h05_in
						,
						h10_new_orleans
						,
						11
					)
					,
					12
				)
				,
				16
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
