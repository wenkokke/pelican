package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0070 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0070() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_professor = word("N","professor",2);
			val t35_app = word("WHO_A","APP",35);
			val t31_john_c_harsanyi = word("NP_D","John_C_Harsanyi",31);
			val t06_won = word("V_2","won",6);
			val t07_the = word("THE","the",7);
			val t25_nobel_prize = word("N","Nobel_Prize",25);
			val t10_for = word("P_I","for",10);
			val t11_a = word("A","a",11);
			val t12_study = word("N","study",12);
			val t13_about = word("P_R","about",13);
			val t21_game_theory = word("NP_D","Game_Theory",21);

			// create the vocabulary for the hypothesis;
			val h29_john_c_harsanyi = word("NP_D","John_C_Harsanyi",29);
			val h04_received = word("V_2","received",4);
			val h05_a = word("A","a",5);
			val h23_nobel_prize = word("N","Nobel_Prize",23);
			val h08_for = word("P_I","for",8);
			val h09_a = word("A","a",9);
			val h10_study = word("N","study",10);
			val h11_about = word("P_R","about",11);
			val h19_game_theory = word("NP_D","Game_Theory",19);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						t02_professor
						,
						33
					)
					,
					_(
						t35_app
						,
						t31_john_c_harsanyi
						,
						36
					)
					,
					34
				)
				,
				_(
					_(
						t06_won
						,
						_(
							t07_the
							,
							t25_nobel_prize
							,
							26
						)
						,
						27
					)
					,
					_(
						t10_for
						,
						_(
							t11_a
							,
							_(
								t12_study
								,
								_(
									t13_about
									,
									t21_game_theory
									,
									22
								)
								,
								19
							)
							,
							38
						)
						,
						20
					)
					,
					28
				)
				,
				37
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h29_john_c_harsanyi
				,
				_(
					_(
						h04_received
						,
						_(
							h05_a
							,
							h23_nobel_prize
							,
							24
						)
						,
						25
					)
					,
					_(
						h08_for
						,
						_(
							h09_a
							,
							_(
								h10_study
								,
								_(
									h11_about
									,
									h19_game_theory
									,
									20
								)
								,
								17
							)
							,
							31
						)
						,
						18
					)
					,
					26
				)
				,
				30
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(6,4));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
