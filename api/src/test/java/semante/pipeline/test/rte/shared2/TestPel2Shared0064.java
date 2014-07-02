package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0064 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0064() throws Exception {

			// create the vocabulary for the text;
			val t52_det = word("THE","DET",52);
			val t01_senator = word("N","Senator",1);
			val t50_app = word("WHO_A","APP",50);
			val t02_jader = word("$NPC_1$","Jader",2);
			val t03_barbalho = word("NP_D","Barbalho",3);
			val t55_app = word("WHO_A","APP",55);
			val t05_the = word("THE","the",5);
			val t06_recently = word("MR","recently",6);
			val t07_elected = word("MR","elected",7);
			val t08_senate = word("$NC_1$","senate",8);
			val t09_president = word("N","president",9);
			val t10_who = word("WHO_R","who",10);
			val t11_is = word("IS","is",11);
			val t12_heaped = word("V_1","heaped",12);
			val t13_with = word("P_R","with",13);
			val t43_det = word("EMPTYDET","DET",43);
			val t14_corruption = word("$NC_1$","corruption",14);
			val t15_charges = word("N","charges",15);
			val t17_led = word("V_2","led",17);
			val t18_the = word("THE","the",18);
			val t19_pmdb = word("NP_D","PMDB",19);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_senator = word("N","senator",2);
			val h03_is = word("IS","is",3);
			val h12_det = word("A","DET",12);
			val h04_senate = word("$NC_1$","senate",4);
			val h05_president = word("N","president",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						_(
							t52_det
							,
							t01_senator
							,
							53
						)
						,
						_(
							t50_app
							,
							_(
								t02_jader
								,
								t03_barbalho
								,
								36
							)
							,
							51
						)
						,
						41
					)
					,
					_(
						t55_app
						,
						_(
							t05_the
							,
							_(
								t06_recently
								,
								_(
									t07_elected
									,
									_(
										_(
											t08_senate
											,
											t09_president
											,
											38
										)
										,
										_(
											t10_who
											,
											_(
												t11_is
												,
												_(
													t12_heaped
													,
													_(
														t13_with
														,
														_(
															t43_det
															,
															_(
																t14_corruption
																,
																t15_charges
																,
																25
															)
															,
															44
														)
														,
														26
													)
													,
													27
												)
												,
												28
											)
											,
											30
										)
										,
										39
									)
									,
									49
								)
								,
								46
							)
							,
							48
						)
						,
						56
					)
					,
					54
				)
				,
				_(
					t17_led
					,
					_(
						t18_the
						,
						t19_pmdb
						,
						33
					)
					,
					34
				)
				,
				42
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					h02_senator
					,
					7
				)
				,
				_(
					h03_is
					,
					_(
						h12_det
						,
						_(
							h04_senate
							,
							h05_president
							,
							8
						)
						,
						13
					)
					,
					9
				)
				,
				11
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

