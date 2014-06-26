package semante.pipeline.test.rte.shared1.subs.fail.annotation;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTestWithLegacy24;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0027 extends AbsPipelineTestWithLegacy24 {

		@Test
		public final void testPel1Shared0027() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_largest = word("MR","largest");
			val t02_search = word("MR","search");
			val t03_engine = word("N","engine");
			val t04_app = word("WHO_A","APP");
			val t05_google = word("NP_D","Google");
			val t06_functions = word("V_1","functions");
			val t07_on = word("P_R","on");
			val t08_the = word("THE","the");
			val t09_web = word("N","web");
			val t10_and = word("AND","and");
			val t11_receives = word("V_2","receives");
			val t12_over = word("MR","over");
			val t13_num200 = word("MR","num200");
			val t14_million = word("MR","million");
			val t15_det = word("EMPTYDET","DET");
			val t16_queries = word("N","queries");
			val t17_through = word("P_R","through");
			val t18_the = word("THE","the");
			val t19_various = word("MR","various");
			val t20_services = word("N","services");

			// create the vocabulary for the hypothesis;
			val h00_google = word("NP_D","Google");
			val h01_operates = word("V_1","operates");
			val h02_on = word("P_R","on");
			val h03_the = word("THE","the");
			val h04_web = word("N","web");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_the
						,
						_(
							t01_largest
							,
							_(
								t02_search
								,
								t03_engine
							)
						)
					)
					,
					_(
						t04_app
						,
						t05_google
					)
				)
				,
				_(
					_(
						t06_functions
						,
						_(
							t07_on
							,
							_(
								t08_the
								,
								t09_web
							)
						)
					)
					,
					_(
						t10_and
						,
						_(
							_(
								t11_receives
								,
								_(
									t12_over
									,
									_(
										t13_num200
										,
										_(
											t14_million
											,
											_(
												t15_det
												,
												t16_queries
											)
										)
									)
								)
							)
							,
							_(
								t17_through
								,
								_(
									t18_the
									,
									_(
										t19_various
										,
										t20_services
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
				h00_google
				,
				_(
					h01_operates
					,
					_(
						h02_on
						,
						_(
							h03_the
							,
							h04_web
						)
					)
				)
			)
			;

			// create the subsumption relations;
			val st000_functions = word("V_1","functions");
			val sh000_operates = word("V_1","operates");
val st0 = 
			st000_functions
			;
val sh0 = 
			sh000_operates
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

