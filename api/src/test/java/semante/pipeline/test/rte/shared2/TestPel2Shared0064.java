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
			val t36_jader_barbalho = word("NP_D","Jader_Barbalho",36);
			val t55_app = word("WHO_A","APP",55);
			val t05_the = word("THE","the",5);
			val t06_recently = word("MR","recently",6);
			val t07_elected = word("MR","elected",7);
			val t38_senate_president = word("N","senate_president",38);
			val t10_who = word("WHO_R","who",10);
			val t11_is = word("IS","is",11);
			val t12_heaped = word("V_1","heaped",12);
			val t13_with = word("P_R","with",13);
			val t43_det = word("EMPTYDET","DET",43);
			val t25_corruption_charges = word("N","corruption_charges",25);
			val t17_led = word("V_2","led",17);
			val t18_the = word("THE","the",18);
			val t19_pmdb = word("NP_D","PMDB",19);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_senator = word("N","senator",2);
			val h03_is = word("IS","is",3);
			val h12_det = word("A","DET",12);
			val h08_senate_president = word("N","senate_president",8);

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
							t36_jader_barbalho
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
										t38_senate_president
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
															t25_corruption_charges
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
						h08_senate_president
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
