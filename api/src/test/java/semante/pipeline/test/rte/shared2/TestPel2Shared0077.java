package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0077 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0077() throws Exception {

			// create the vocabulary for the text;
			val t01_esker = word("$NPC_1$","Esker",1);
			val t02_hills = word("NP_D","Hills",2);
			val t03_which = word("WHO_A","which",3);
			val t04_is = word("IS","is",4);
			val t05_near = word("P_R","near",5);
			val t06_the = word("THE","the",6);
			val t07_k = word("$NPC_1$","K",7);
			val t08_club = word("NP_D","Club",8);
			val t55_app = word("WHO_A","APP",55);
			val t58_det = word("THE","DET",58);
			val t10_venue = word("N","venue",10);
			val t11_of = word("P_R","of",11);
			val t12_the = word("THE","the",12);
			val t13_num2006 = word("MR","num2006",13);
			val t14_ryder = word("$NC_1$","Ryder",14);
			val t15_cup = word("N","Cup",15);
			val t17_is = word("IS","is",17);
			val t60_det = word("EMPTYDET","DET",60);
			val t18_irish = word("MR","Irish",18);
			val t19_golf = word("N","golf",19);
			val t20_s = word("GEN","s",20);
			val t21_best = word("MR","best",21);
			val t22_kept = word("MR","kept",22);
			val t23_secret = word("N","secret",23);

			// create the vocabulary for the hypothesis;
			val h01_esker = word("$NPC_1$","Esker",1);
			val h02_hills = word("NP_D","Hills",2);
			val h03_is = word("IS","is",3);
			val h04_near = word("P_R","near",4);
			val h05_the = word("THE","the",5);
			val h06_venue = word("N","venue",6);
			val h07_of = word("P_R","of",7);
			val h08_a = word("A","a",8);
			val h09_ryder = word("$NC_1$","Ryder",9);
			val h10_cup = word("N","Cup",10);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_esker
						,
						t02_hills
						,
						25
					)
					,
					_(
						t03_which
						,
						_(
							t04_is
							,
							_(
								t05_near
								,
								_(
									_(
										t06_the
										,
										_(
											t07_k
											,
											t08_club
											,
											48
										)
										,
										49
									)
									,
									_(
										t55_app
										,
										_(
											t58_det
											,
											_(
												t10_venue
												,
												_(
													t11_of
													,
													_(
														t12_the
														,
														_(
															t13_num2006
															,
															_(
																t14_ryder
																,
																t15_cup
																,
																50
															)
															,
															51
														)
														,
														52
													)
													,
													35
												)
												,
												36
											)
											,
											59
										)
										,
										56
									)
									,
									28
								)
								,
								57
							)
							,
							29
						)
						,
						31
					)
					,
					32
				)
				,
				_(
					t17_is
					,
					_(
						_(
							_(
								t60_det
								,
								_(
									t18_irish
									,
									t19_golf
									,
									47
								)
								,
								61
							)
							,
							t20_s
							,
							39
						)
						,
						_(
							t21_best
							,
							_(
								t22_kept
								,
								t23_secret
								,
								42
							)
							,
							64
						)
						,
						65
					)
					,
					44
				)
				,
				54
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_esker
					,
					h02_hills
					,
					12
				)
				,
				_(
					h03_is
					,
					_(
						h04_near
						,
						_(
							h05_the
							,
							_(
								h06_venue
								,
								_(
									h07_of
									,
									_(
										h08_a
										,
										_(
											h09_ryder
											,
											h10_cup
											,
											20
										)
										,
										21
									)
									,
									15
								)
								,
								13
							)
							,
							23
						)
						,
						17
					)
					,
					18
				)
				,
				22
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

