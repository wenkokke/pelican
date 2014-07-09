package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0023 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0023() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_director = word("N","director",2);
			val t03_said = word("FACT","said",3);
			val t04_that = word("IGNORE","that",4);
			val t05_teddy = word("NP_D","Teddy",5);
			val t47_app = word("WHO_A","APP",47);
			val t07_a = word("A","a",7);
			val t08_male = word("MR","male",8);
			val t09_gorilla = word("N","gorilla",9);
			val t11_escaped = word("V_1","escaped",11);
			val t12_from = word("P_R","from",12);
			val t13_the = word("THE","the",13);
			val t40_berlin_zoo = word("N","Berlin_zoo",40);
			val t16_and = word("AND","and",16);
			val t17_terrified = word("V_2","terrified",17);
			val t18_the = word("THE","the",18);
			val t19_visitors = word("N","visitors",19);
			val t20_yesterday = word("MR","yesterday",20);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_gorilla = word("N","gorilla",2);
			val h03_escaped = word("V_1","escaped",3);
			val h04_from = word("P_R","from",4);
			val h05_a = word("A","a",5);
			val h06_zoo = word("N","zoo",6);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					t02_director
					,
					22
				)
				,
				_(
					t03_said
					,
					_(
						t04_that
						,
						_(
							_(
								t05_teddy
								,
								_(
									t47_app
									,
									_(
										t07_a
										,
										_(
											t08_male
											,
											t09_gorilla
											,
											37
										)
										,
										38
									)
									,
									48
								)
								,
								39
							)
							,
							_(
								_(
									_(
										t11_escaped
										,
										_(
											t12_from
											,
											_(
												t13_the
												,
												t40_berlin_zoo
												,
												41
											)
											,
											27
										)
										,
										28
									)
									,
									_(
										t16_and
										,
										_(
											t17_terrified
											,
											_(
												t18_the
												,
												t19_visitors
												,
												29
											)
											,
											43
										)
										,
										42
									)
									,
									49
								)
								,
								t20_yesterday
								,
								45
							)
							,
							33
						)
						,
						34
					)
					,
					35
				)
				,
				46
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					h02_gorilla
					,
					8
				)
				,
				_(
					h03_escaped
					,
					_(
						h04_from
						,
						_(
							h05_a
							,
							h06_zoo
							,
							9
						)
						,
						10
					)
					,
					11
				)
				,
				13
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(40,6));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
