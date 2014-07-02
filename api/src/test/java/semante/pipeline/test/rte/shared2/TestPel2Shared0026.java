package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0026 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0026() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_wait = word("$NC_1$","wait",2);
			val t03_time = word("N","time",3);
			val t04_for = word("P_R","for",4);
			val t05_a = word("A","a",5);
			val t06_green = word("MR","green",6);
			val t07_card = word("N","card",7);
			val t48_app = word("WHO_A","APP",48);
			val t09_the = word("THE","the",9);
			val t10_american = word("MR","American",10);
			val t11_permanent = word("MR","permanent",11);
			val t12_resident = word("MR","resident",12);
			val t13_card = word("N","card",13);
			val t33_went_up = word("V_1","went_up",33);
			val t17_in = word("P_R","in",17);
			val t18_the = word("THE","the",18);
			val t19_same = word("MR","same",19);
			val t20_regions = word("N","regions",20);

			// create the vocabulary for the hypothesis;
			val h01_the = word("THE","The",1);
			val h02_wait = word("$NC_1$","wait",2);
			val h03_time = word("N","time",3);
			val h04_for = word("P_R","for",4);
			val h05_an = word("A","an",5);
			val h06_american = word("MR","American",6);
			val h07_permanent = word("MR","permanent",7);
			val h08_resident = word("MR","resident",8);
			val h09_card = word("N","card",9);
			val h18_went_up = word("V_1","went_up",18);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					_(
						_(
							t02_wait
							,
							t03_time
							,
							37
						)
						,
						_(
							t04_for
							,
							_(
								_(
									t05_a
									,
									_(
										t06_green
										,
										t07_card
										,
										39
									)
									,
									40
								)
								,
								_(
									t48_app
									,
									_(
										t09_the
										,
										_(
											t10_american
											,
											_(
												t11_permanent
												,
												_(
													t12_resident
													,
													t13_card
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
										44
									)
									,
									49
								)
								,
								45
							)
							,
							26
						)
						,
						38
					)
					,
					47
				)
				,
				_(
					t33_went_up
					,
					_(
						t17_in
						,
						_(
							t18_the
							,
							_(
								t19_same
								,
								t20_regions
								,
								34
							)
							,
							35
						)
						,
						30
					)
					,
					36
				)
				,
				46
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_the
					,
					_(
						_(
							h02_wait
							,
							h03_time
							,
							20
						)
						,
						_(
							h04_for
							,
							_(
								h05_an
								,
								_(
									h06_american
									,
									_(
										h07_permanent
										,
										_(
											h08_resident
											,
											h09_card
											,
											22
										)
										,
										23
									)
									,
									24
								)
								,
								25
							)
							,
							15
						)
						,
						21
					)
					,
					27
				)
				,
				h18_went_up
				,
				26
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

