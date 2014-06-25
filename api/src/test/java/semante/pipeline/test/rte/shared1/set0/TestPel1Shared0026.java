package semante.pipeline.test.rte.shared1.set0;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTestWithLegacy24;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0026 extends AbsPipelineTestWithLegacy24 {

		@Test
		public final void testPel1Shared0026() throws Exception {

			// create the vocabulary for the text;
			val t00_two = word("NUMBER","Two");
			val t01_members = word("N","members");
			val t02_of = word("P_R","of");
			val t03_the = word("THE","the");
			val t04_num2000 = word("NUMBER","num2000");
			val t05_kanchenjunga = word("MR","Kanchenjunga");
			val t06_expedition = word("N","Expedition");
			val t07_of = word("P_R","of");
			val t08_south = word("$NPC_1$","South");
			val t09_korea = word("NP_D","Korea");
			val t10_successfully = word("MR","successfully");
			val t11_scaled = word("V_2","scaled");
			val t12_the = word("THE","the");
			val t13_num8586 = word("MR","num8586");
			val t14_meter = word("MR","meter");
			val t15_high = word("MI","high");
			val t16_mt = word("MI","Mt");
			val t17_kanchenjunga = word("NP_D","Kanchenjunga");
			val t18_via = word("P_R","via");
			val t19_the = word("THE","the");
			val t20_south = word("MR","south");
			val t21_west = word("MR","west");
			val t22_face = word("N","face");
			val t23_on = word("P_R","on");
			val t24_friday = word("NP_D","Friday");

			// create the vocabulary for the hypothesis;
			val h00_kanchenjunga = word("NP_D","Kanchenjunga");
			val h01_is = word("IS","is");
			val h02_high = word("MI","high");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_two
					,
					_(
						t01_members
						,
						_(
							t02_of
							,
							_(
								t03_the
								,
								_(
									t04_num2000
									,
									_(
										t05_kanchenjunga
										,
										_(
											t06_expedition
											,
											_(
												t07_of
												,
												_(
													t08_south
													,
													t09_korea
												)
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
					t10_successfully
					,
					_(
						_(
							_(
								t11_scaled
								,
								_(
									t12_the
									,
									_(
										t13_num8586
										,
										_(
											t14_meter
											,
											_(
												t15_high
												,
												_(
													t16_mt
													,
													t17_kanchenjunga
												)
											)
										)
									)
								)
							)
							,
							_(
								t18_via
								,
								_(
									t19_the
									,
									_(
										t20_south
										,
										_(
											t21_west
											,
											t22_face
										)
									)
								)
							)
						)
						,
						_(
							t23_on
							,
							t24_friday
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_kanchenjunga
				,
				_(
					h01_is
					,
					h02_high
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

