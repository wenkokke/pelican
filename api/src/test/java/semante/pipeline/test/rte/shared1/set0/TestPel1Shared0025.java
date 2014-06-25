package semante.pipeline.test.rte.shared1.set0;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTestWithLegacy24;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0025 extends AbsPipelineTestWithLegacy24 {

		@Test
		public final void testPel1Shared0025() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_famous = word("MR","famous");
			val t02_greek = word("MI","Greek");
			val t03_cinema = word("IGNORE","cinema");
			val t04_and = word("IGNORE","and");
			val t05_theater = word("IGNORE","theater");
			val t06_actor = word("N","actor");
			val t07_app = word("WHO_A","APP");
			val t08_nikos = word("$NPC_1$","Nikos");
			val t09_kourkoulos = word("NP_D","Kourkoulos");
			val t10_died = word("V_1","died");
			val t11_at = word("P_R","at");
			val t12_the = word("THE","the");
			val t13_ericos = word("MR","Ericos");
			val t14_dynan = word("MR","Dynan");
			val t15_hospital = word("N","Hospital");
			val t16_of = word("P_R","of");
			val t17_athens = word("NP_D","Athens");
			val t18_after = word("P_R","after");
			val t19_a = word("A","a");
			val t20_longstanding = word("MR","longStanding");
			val t21_fight = word("N","fight");
			val t22_with = word("P_R","with");
			val t23_cancer = word("N","cancer");

			// create the vocabulary for the hypothesis;
			val h00_nikos = word("$NPC_1$","Nikos");
			val h01_kourkoulos = word("NP_D","Kourkoulos");
			val h02_was = word("IS","was");
			val h03_a = word("A","a");
			val h04_greek = word("MI","Greek");
			val h05_actor = word("N","actor");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_the
						,
						_(
							t01_famous
							,
							_(
								t02_greek
								,
								_(
									t03_cinema
									,
									_(
										t04_and
										,
										_(
											t05_theater
											,
											t06_actor
										)
									)
								)
							)
						)
					)
					,
					_(
						t07_app
						,
						_(
							t08_nikos
							,
							t09_kourkoulos
						)
					)
				)
				,
				_(
					_(
						t10_died
						,
						_(
							t11_at
							,
							_(
								t12_the
								,
								_(
									t13_ericos
									,
									_(
										t14_dynan
										,
										_(
											t15_hospital
											,
											_(
												t16_of
												,
												t17_athens
											)
										)
									)
								)
							)
						)
					)
					,
					_(
						t18_after
						,
						_(
							t19_a
							,
							_(
								t20_longstanding
								,
								_(
									t21_fight
									,
									_(
										t22_with
										,
										t23_cancer
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
					h00_nikos
					,
					h01_kourkoulos
				)
				,
				_(
					h02_was
					,
					_(
						h03_a
						,
						_(
							h04_greek
							,
							h05_actor
						)
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

