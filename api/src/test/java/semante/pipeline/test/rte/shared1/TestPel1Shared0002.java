package semante.pipeline.test.rte.shared1;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0002 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0002() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_controversyracked = word("MR","controversyRacked",2);
			val t03_oil = word("$NC_1$","oil",3);
			val t04_giant = word("N","giant",4);
			val t52_app = word("WHO_A","APP",52);
			val t05_shell = word("NP_D","Shell",5);
			val t06_has = word("V_AUX","has",6);
			val t07_named = word("V_2","named",7);
			val t08_a = word("A","a",8);
			val t09_new = word("MR","new",9);
			val t10_head = word("N","head",10);
			val t11_of = word("P_R","of",11);
			val t46_det = word("A","DET",46);
			val t12_finance = word("N","finance",12);
			val t13_in = word("P_R","in",13);
			val t14_an = word("A","an",14);
			val t15_effort = word("N","effort",15);
			val t16_which = word("WHO_R","which",16);
			val t17_calmed = word("V_2","calmed",17);
			val t49_det = word("A","DET",49);
			val t18_nervous = word("MR","nervous",18);
			val t19_shareholders = word("N","shareholders",19);

			// create the vocabulary for the hypothesis;
			val h01_shell = word("NP_D","Shell",1);
			val h02_is = word("IS","is",2);
			val h03_an = word("A","an",3);
			val h04_oil = word("$NC_1$","oil",4);
			val h05_giant = word("N","giant",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						_(
							t02_controversyracked
							,
							_(
								t03_oil
								,
								t04_giant
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
					_(
						t52_app
						,
						t05_shell
						,
						53
					)
					,
					45
				)
				,
				_(
					t06_has
					,
					_(
						_(
							t07_named
							,
							_(
								t08_a
								,
								_(
									t09_new
									,
									_(
										t10_head
										,
										_(
											t11_of
											,
											_(
												t46_det
												,
												t12_finance
												,
												47
											)
											,
											24
										)
										,
										37
									)
									,
									48
								)
								,
								38
							)
							,
							39
						)
						,
						_(
							t13_in
							,
							_(
								t14_an
								,
								_(
									t15_effort
									,
									_(
										t16_which
										,
										_(
											t17_calmed
											,
											_(
												t49_det
												,
												_(
													t18_nervous
													,
													t19_shareholders
													,
													28
												)
												,
												50
											)
											,
											29
										)
										,
										31
									)
									,
									26
								)
								,
								51
							)
							,
							33
						)
						,
						40
					)
					,
					35
				)
				,
				54
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_shell
				,
				_(
					h02_is
					,
					_(
						h03_an
						,
						_(
							h04_oil
							,
							h05_giant
							,
							11
						)
						,
						12
					)
					,
					9
				)
				,
				13
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

