package semante.pipeline.test.rte.shared1.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel1Shared0027 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0027() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_largest = word("MR","largest",2);
			val t03_search = word("MR","search",3);
			val t04_engine = word("N","engine",4);
			val t49_app = word("WHO_A","APP",49);
			val t06_google = word("NP_D","Google",6);
			val t07_functions = word("V_1","functions",7);
			val t08_on = word("P_R","on",8);
			val t09_the = word("THE","the",9);
			val t10_web = word("N","web",10);
			val t11_and = word("AND","and",11);
			val t12_receives = word("V_2","receives",12);
			val t13_over = word("MR","over",13);
			val t14_num200 = word("MR","num200",14);
			val t15_million = word("MR","million",15);
			val t51_det = word("EMPTYDET","DET",51);
			val t16_queries = word("N","queries",16);
			val t17_through = word("P_R","through",17);
			val t18_the = word("THE","the",18);
			val t19_various = word("MR","various",19);
			val t20_services = word("N","services",20);

			// create the vocabulary for the hypothesis;
			val h01_google = word("NP_D","Google",1);
			val h02_operates = word("V_1","operates",2);
			val h03_on = word("P_R","on",3);
			val h04_the = word("THE","the",4);
			val h05_web = word("N","web",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						_(
							t02_largest
							,
							_(
								t03_search
								,
								t04_engine
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
						t49_app
						,
						t06_google
						,
						50
					)
					,
					45
				)
				,
				_(
					_(
						t07_functions
						,
						_(
							t08_on
							,
							_(
								t09_the
								,
								t10_web
								,
								25
							)
							,
							26
						)
						,
						27
					)
					,
					_(
						t11_and
						,
						_(
							_(
								t12_receives
								,
								_(
									t13_over
									,
									_(
										t14_num200
										,
										_(
											t15_million
											,
											_(
												t51_det
												,
												t16_queries
												,
												52
											)
											,
											28
										)
										,
										47
									)
									,
									30
								)
								,
								37
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
										,
										38
									)
									,
									39
								)
								,
								32
							)
							,
							40
						)
						,
						36
					)
					,
					48
				)
				,
				46
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_google
				,
				_(
					h02_operates
					,
					_(
						h03_on
						,
						_(
							h04_the
							,
							h05_web
							,
							8
						)
						,
						9
					)
					,
					10
				)
				,
				12
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(7,2));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
