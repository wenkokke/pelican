package semante.pipeline.test.rte.shared2.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0029 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0029() throws Exception {

			// create the vocabulary for the text;
			val t59_world_health_officials = word("NP_D","World_health_officials",59);
			val t04_warned = word("FACT","warned",4);
			val t05_that = word("IGNORE","that",5);
			val t63_west_africa_and_central_africa = word("NP_D","West_Africa_and_Central_Africa",63);
			val t12_which = word("WHO_A","which",12);
			val t13_face = word("V_2","face",13);
			val t14_the = word("THE","the",14);
			val t15_worst = word("MR","worst",15);
			val t52_polio_outbreak = word("N","polio_outbreak",52);
			val t18_in = word("P_R","in",18);
			val t19_years = word("N","years",19);
			val t21_must = word("V_AUX","must",21);
			val t22_vaccinate = word("V_2","vaccinate",22);
			val t70_det = word("THE","DET",70);
			val t23_children = word("N","children",23);
			val t24_against = word("P_R","against",24);
			val t25_the = word("THE","the",25);
			val t26_disease = word("N","disease",26);

			// create the vocabulary for the hypothesis;
			val h01_africa = word("NP_D","Africa",1);
			val h02_fears = word("V_2","fears",2);
			val h03_a = word("A","a",3);
			val h11_polio_outbreak = word("N","polio_outbreak",11);

			// create the tree structure for the text;
			val tt =
			_(
				t59_world_health_officials
				,
				_(
					t04_warned
					,
					_(
						t05_that
						,
						_(
							_(
								t63_west_africa_and_central_africa
								,
								_(
									t12_which
									,
									_(
										t13_face
										,
										_(
											t14_the
											,
											_(
												_(
													t15_worst
													,
													t52_polio_outbreak
													,
													53
												)
												,
												_(
													t18_in
													,
													t19_years
													,
													35
												)
												,
												54
											)
											,
											65
										)
										,
										37
									)
									,
									39
								)
								,
								64
							)
							,
							_(
								t21_must
								,
								_(
									_(
										t22_vaccinate
										,
										_(
											t70_det
											,
											t23_children
											,
											71
										)
										,
										45
									)
									,
									_(
										t24_against
										,
										_(
											t25_the
											,
											t26_disease
											,
											42
										)
										,
										43
									)
									,
									67
								)
								,
								68
							)
							,
							69
						)
						,
						47
					)
					,
					48
				)
				,
				60
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_africa
				,
				_(
					h02_fears
					,
					_(
						h03_a
						,
						h11_polio_outbreak
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
			List<Pair<Integer,Integer>> subs = Lists.newArrayList();
			subs.add(new IPair<Integer,Integer>(13,2));
			subs.add(new IPair<Integer,Integer>(63,1));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
