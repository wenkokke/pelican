package semante.pipeline.test.rte.shared1.set0;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0016 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0016() throws Exception {

			// create the vocabulary for the text;
			val t00_toshiba = word("NP_D","Toshiba");
			val t01_app = word("WHO_A","APP");
			val t02_the = word("THE","the");
			val t03_det = word("EMPTYDET","DET");
			val t04_world = word("N","world");
			val t05_s = word("POSS","s");
			val t06_thirdlargest = word("MR","thirdLargest");
			val t07_notebook = word("$NC_1$","notebook");
			val t08_computer = word("$NC_1$","computer");
			val t09_maker = word("N","maker");
			val t10_behind = word("P_I","behind");
			val t11_dell = word("$NPC_1$","Dell");
			val t12_inc = word("NP_D","Inc");
			val t13_said = word("FACT","said");
			val t14_the = word("THE","the");
			val t15_pc = word("N","PC");
			val t16_would = word("V_AUX","would");
			val t17_be = word("IS","be");
			val t18_introduced = word("V_1","introduced");
			val t19_in = word("P_R","in");
			val t20_japan = word("NP_D","Japan");
			val t21_in = word("P_R","in");
			val t22_early = word("MI","early");
			val t23_num2006 = word("NP_D","num2006");

			// create the vocabulary for the hypothesis;
			val h00_toshiba = word("NP_D","Toshiba");
			val h01_is = word("IS","is");
			val h02_a = word("A","a");
			val h03_notebook = word("$NC_1$","notebook");
			val h04_computer = word("$NC_1$","computer");
			val h05_producer = word("N","producer");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_toshiba
					,
					_(
						t01_app
						,
						_(
							t02_the
							,
							_(
								_(
									_(
										t03_det
										,
										t04_world
									)
									,
									t05_s
								)
								,
								_(
									t06_thirdlargest
									,
									_(
										_(
											t07_notebook
											,
											_(
												t08_computer
												,
												t09_maker
											)
										)
										,
										_(
											t10_behind
											,
											_(
												t11_dell
												,
												t12_inc
											)
										)
									)
								)
							)
						)
					)
				)
				,
				_(
					t13_said
					,
					_(
						_(
							t14_the
							,
							t15_pc
						)
						,
						_(
							t16_would
							,
							_(
								t17_be
								,
								_(
									_(
										t18_introduced
										,
										_(
											t19_in
											,
											t20_japan
										)
									)
									,
									_(
										t21_in
										,
										_(
											t22_early
											,
											t23_num2006
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
				h00_toshiba
				,
				_(
					h01_is
					,
					_(
						h02_a
						,
						_(
							h03_notebook
							,
							_(
								h04_computer
								,
								h05_producer
							)
						)
					)
				)
			)
			;

			// create the subsumption relations;
			val st000_maker = word("N","maker");
			val sh000_maker = word("N","maker");
val st0 = 
			st000_maker
			;
val sh0 = 
			sh000_maker
			;
val ss0 = subs(st0, sh0);
Iterable<Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>> subs = of(
			ss0
);

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

