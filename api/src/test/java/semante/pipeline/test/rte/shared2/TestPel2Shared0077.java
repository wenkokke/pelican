package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0077 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0077() throws Exception {

			// create the vocabulary for the text;
			val t25_esker_hills = word("NP_D","Esker_Hills",25);
			val t03_which = word("WHO_A","which",3);
			val t04_is = word("IS","is",4);
			val t05_near = word("P_R","near",5);
			val t06_the = word("THE","the",6);
			val t48_k_club = word("NP_D","K_Club",48);
			val t55_app = word("WHO_A","APP",55);
			val t58_det = word("THE","DET",58);
			val t10_venue = word("N","venue",10);
			val t11_of = word("P_R","of",11);
			val t12_the = word("THE","the",12);
			val t13_num2006 = word("MR","num2006",13);
			val t50_ryder_cup = word("N","Ryder_Cup",50);
			val t17_is = word("IS","is",17);
			val t60_det = word("EMPTYDET","DET",60);
			val t18_irish = word("MR","Irish",18);
			val t19_golf = word("N","golf",19);
			val t20_s = word("GEN","s",20);
			val t21_best = word("MR","best",21);
			val t22_kept = word("MR","kept",22);
			val t23_secret = word("N","secret",23);

			// create the vocabulary for the hypothesis;
			val h12_esker_hills = word("NP_D","Esker_Hills",12);
			val h03_is = word("IS","is",3);
			val h04_near = word("P_R","near",4);
			val h05_the = word("THE","the",5);
			val h06_venue = word("N","venue",6);
			val h07_of = word("P_R","of",7);
			val h08_a = word("A","a",8);
			val h20_ryder_cup = word("N","Ryder_Cup",20);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t25_esker_hills
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
										t48_k_club
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
															t50_ryder_cup
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
				h12_esker_hills
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
										h20_ryder_cup
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
