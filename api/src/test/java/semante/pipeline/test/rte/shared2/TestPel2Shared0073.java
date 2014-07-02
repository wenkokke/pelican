package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0073 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0073() throws Exception {

			// create the vocabulary for the text;
			val t45_det = word("THE","DET",45);
			val t01_american = word("MI","American",1);
			val t02_illusionist = word("N","illusionist",2);
			val t47_app = word("WHO_A","APP",47);
			val t04_james = word("$NPC_1$","James",4);
			val t05_randi = word("NP_D","Randi",5);
			val t07_offered = word("V_2","offered",7);
			val t08_a = word("A","a",8);
			val t09_million = word("MR","million",9);
			val t10_dollars = word("N","dollars",10);
			val t11_to = word("P_R","to",11);
			val t50_det = word("EMPTYDET","DET",50);
			val t12_anyone = word("N","anyone",12);
			val t13_who = word("WHO_A","who",13);
			val t14_could = word("V_AUX","could",14);
			val t15_cure = word("V_2","cure",15);
			val t52_det = word("THE","DET",52);
			val t16_people = word("N","people",16);
			val t17_with = word("P_R","with",17);
			val t54_det = word("SOME","DET",54);
			val t18_homeopathic = word("MR","homeopathic",18);
			val t19_remedies = word("N","remedies",19);

			// create the vocabulary for the hypothesis;
			val h01_american = word("MI","American",1);
			val h02_james = word("$NPC_1$","James",2);
			val h03_randi = word("NP_D","Randi",3);
			val h05_who = word("WHO_A","who",5);
			val h06_is = word("IS","is",6);
			val h07_an = word("A","an",7);
			val h08_illusionist = word("N","illusionist",8);
			val h10_offered = word("V_2","offered",10);
			val h11_a = word("A","a",11);
			val h12_million = word("MR","million",12);
			val h13_dollars = word("N","dollars",13);
			val h14_to = word("P_R","to",14);
			val h55_det = word("EMPTYDET","DET",55);
			val h15_anyone = word("N","anyone",15);
			val h16_who = word("WHO_A","who",16);
			val h17_could = word("V_AUX","could",17);
			val h18_cure = word("V_2","cure",18);
			val h58_det = word("THE","DET",58);
			val h19_people = word("N","people",19);
			val h20_with = word("P_R","with",20);
			val h60_det = word("SOME","DET",60);
			val h21_homeopathic = word("MR","homeopathic",21);
			val h22_remedies = word("N","remedies",22);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t45_det
						,
						_(
							t01_american
							,
							t02_illusionist
							,
							21
						)
						,
						46
					)
					,
					_(
						t47_app
						,
						_(
							t04_james
							,
							t05_randi
							,
							22
						)
						,
						48
					)
					,
					43
				)
				,
				_(
					_(
						t07_offered
						,
						_(
							t08_a
							,
							_(
								t09_million
								,
								t10_dollars
								,
								24
							)
							,
							49
						)
						,
						39
					)
					,
					_(
						t11_to
						,
						_(
							_(
								t50_det
								,
								t12_anyone
								,
								26
							)
							,
							_(
								t13_who
								,
								_(
									t14_could
									,
									_(
										_(
											t15_cure
											,
											_(
												t52_det
												,
												t16_people
												,
												53
											)
											,
											40
										)
										,
										_(
											t17_with
											,
											_(
												t54_det
												,
												_(
													t18_homeopathic
													,
													t19_remedies
													,
													29
												)
												,
												55
											)
											,
											30
										)
										,
										41
									)
									,
									32
								)
								,
								34
							)
							,
							35
						)
						,
						36
					)
					,
					42
				)
				,
				44
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					_(
						h01_american
						,
						_(
							h02_james
							,
							h03_randi
							,
							50
						)
						,
						51
					)
					,
					_(
						h05_who
						,
						_(
							h06_is
							,
							_(
								h07_an
								,
								h08_illusionist
								,
								26
							)
							,
							27
						)
						,
						29
					)
					,
					52
				)
				,
				_(
					_(
						h10_offered
						,
						_(
							h11_a
							,
							_(
								h12_million
								,
								h13_dollars
								,
								31
							)
							,
							54
						)
						,
						46
					)
					,
					_(
						h14_to
						,
						_(
							_(
								h55_det
								,
								h15_anyone
								,
								33
							)
							,
							_(
								h16_who
								,
								_(
									h17_could
									,
									_(
										_(
											h18_cure
											,
											_(
												h58_det
												,
												h19_people
												,
												59
											)
											,
											47
										)
										,
										_(
											h20_with
											,
											_(
												h60_det
												,
												_(
													h21_homeopathic
													,
													h22_remedies
													,
													65
												)
												,
												64
											)
											,
											67
										)
										,
										68
									)
									,
									39
								)
								,
								41
							)
							,
							42
						)
						,
						43
					)
					,
					49
				)
				,
				53
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

