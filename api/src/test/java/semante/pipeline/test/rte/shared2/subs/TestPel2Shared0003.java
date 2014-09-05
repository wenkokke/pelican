package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0003 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0003() throws Exception {

			// create the vocabulary for the text;
			val t01_i = word("NP_D","I",1);
			val t33_met_up = word("V_1","met_up",33);
			val t04_with = word("P_R","with",4);
			val t05_a = word("A","a",5);
			val t06_microsoft = word("MI","Microsoft",6);
			val t07_guy = word("N","guy",7);
			val t43_app = word("WHO_A","APP",43);
			val t23_john_traynor = word("NP_D","John_Traynor",23);
			val t45_app = word("WHO_A","APP",45);
			val t47_det = word("THE","DET",47);
			val t12_senior = word("MR","senior",12);
			val t13_director = word("N","director",13);
			val t14_of = word("P_R","of",14);
			val t15_the = word("THE","the",15);
			val t16_mobile = word("MR","mobile",16);
			val t17_devices = word("MR","devices",17);
			val t18_division = word("N","division",18);

			// create the vocabulary for the hypothesis;
			val h08_john_traynor = word("NP_D","John_Traynor",8);
			val h03_is = word("IS","is",3);
			val h04_a = word("A","a",4);
			val h05_microsoft = word("MI","Microsoft",5);
			val h06_executive = word("N","executive",6);

			// create the tree structure for the text;
			val tt =
			_(
				t01_i
				,
				_(
					t33_met_up
					,
					_(
						t04_with
						,
						_(
							_(
								t05_a
								,
								_(
									t06_microsoft
									,
									t07_guy
									,
									34
								)
								,
								35
							)
							,
							_(
								t43_app
								,
								_(
									t23_john_traynor
									,
									_(
										t45_app
										,
										_(
											t47_det
											,
											_(
												t12_senior
												,
												_(
													t13_director
													,
													_(
														t14_of
														,
														_(
															t15_the
															,
															_(
																t16_mobile
																,
																_(
																	t17_devices
																	,
																	t18_division
																	,
																	36
																)
																,
																37
															)
															,
															38
														)
														,
														26
													)
													,
													24
												)
												,
												49
											)
											,
											48
										)
										,
										46
									)
									,
									39
								)
								,
								44
							)
							,
							40
						)
						,
						30
					)
					,
					41
				)
				,
				42
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h08_john_traynor
				,
				_(
					h03_is
					,
					_(
						h04_a
						,
						_(
							h05_microsoft
							,
							h06_executive
							,
							12
						)
						,
						13
					)
					,
					10
				)
				,
				14
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(13,6));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
