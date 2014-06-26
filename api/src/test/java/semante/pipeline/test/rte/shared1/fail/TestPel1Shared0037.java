package semante.pipeline.test.rte.shared1.fail;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0037 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0037() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_grandpa = word("N","Grandpa");
			val t02_of = word("P_R","of");
			val t03_internet = word("$NPC_1$","Internet");
			val t04_explorer = word("NP_D","Explorer");
			val t05_was = word("IS","was");
			val t06_a = word("A","a");
			val t07_web = word("$NC_1$","web");
			val t08_browser = word("N","browser");
			val t09_app = word("WHO_A","APP");
			val t10_called = word("V_2","called");
			val t11_mosaic = word("NP_D","Mosaic");
			val t12_which = word("WHO_A","which");
			val t13_united = word("V_2","united");
			val t14_the = word("THE","the");
			val t15_national = word("$NC_1$","National");
			val t16_center = word("N","Center");
			val t17_for = word("P_R","for");
			val t18_super = word("$NPC_1$","Super");
			val t19_computing = word("$NPC_1$","Computing");
			val t20_applications = word("NP_D","Applications");
			val t21_app = word("WHO_A","APP");
			val t22_ = word("IGNORE","");
			val t23_det = word("A","DET");
			val t24_ncsa = word("N","NCSA");
			val t25_ = word("IGNORE","");
			val t26_in = word("P_R","in");
			val t27_num1987 = word("NP_D","num1987");

			// create the vocabulary for the hypothesis;
			val h00_the = word("THE","The");
			val h01_web = word("$NC_1$","web");
			val h02_broswer = word("N","broswer");
			val h03_app = word("WHO_A","APP");
			val h04_mosaic = word("NP_D","Mosaic");
			val h05_united = word("V_2","united");
			val h06_the = word("THE","the");
			val h07_ncsa = word("N","NCSA");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_the
					,
					_(
						t01_grandpa
						,
						_(
							t02_of
							,
							_(
								t03_internet
								,
								t04_explorer
							)
						)
					)
				)
				,
				_(
					t05_was
					,
					_(
						_(
							t06_a
							,
							_(
								t07_web
								,
								t08_browser
							)
						)
						,
						_(
							t09_app
							,
							_(
								t10_called
								,
								_(
									t11_mosaic
									,
									_(
										t12_which
										,
										_(
											_(
												t13_united
												,
												_(
													_(
														t14_the
														,
														_(
															_(
																t15_national
																,
																t16_center
															)
															,
															_(
																t17_for
																,
																_(
																	t18_super
																	,
																	_(
																		t19_computing
																		,
																		t20_applications
																	)
																)
															)
														)
													)
													,
													_(
														t21_app
														,
														_(
															_(
																t22_
																,
																_(
																	t23_det
																	,
																	t24_ncsa
																)
															)
															,
															t25_
														)
													)
												)
											)
											,
											_(
												t26_in
												,
												t27_num1987
											)
										)
									)
								)
							)
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					_(
						h00_the
						,
						_(
							h01_web
							,
							h02_broswer
						)
					)
					,
					_(
						h03_app
						,
						h04_mosaic
					)
				)
				,
				_(
					h05_united
					,
					_(
						h06_the
						,
						h07_ncsa
					)
				)
			)
			;

			// create the subsumption relations;
Iterable<Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>> subs = of(
);

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

