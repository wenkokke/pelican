package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0070 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0070() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_professor = word("N","professor",2);
			val t35_app = word("WHO_A","APP",35);
			val t03_john = word("$NPC_1$","John",3);
			val t04_c = word("$NPC_1$","C",4);
			val t05_harsanyi = word("NP_D","Harsanyi",5);
			val t06_won = word("V_2","won",6);
			val t07_the = word("THE","the",7);
			val t08_nobel = word("$NC_1$","Nobel",8);
			val t09_prize = word("N","Prize",9);
			val t10_for = word("P_I","for",10);
			val t11_a = word("A","a",11);
			val t12_study = word("N","study",12);
			val t13_about = word("P_R","about",13);
			val t14_game = word("$NPC_1$","Game",14);
			val t15_theory = word("NP_D","Theory",15);

			// create the vocabulary for the hypothesis;
			val h01_john = word("$NPC_1$","John",1);
			val h02_c = word("$NPC_1$","C",2);
			val h03_harsanyi = word("NP_D","Harsanyi",3);
			val h04_received = word("V_2","received",4);
			val h05_a = word("A","a",5);
			val h06_nobel = word("$NC_1$","Nobel",6);
			val h07_prize = word("N","Prize",7);
			val h08_for = word("P_I","for",8);
			val h09_a = word("A","a",9);
			val h10_study = word("N","study",10);
			val h11_about = word("P_R","about",11);
			val h12_game = word("$NPC_1$","Game",12);
			val h13_theory = word("NP_D","Theory",13);

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
						_(
							t03_john
							,
							_(
								t04_c
								,
								t05_harsanyi
								,
								30
							)
							,
							31
						)
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
							_(
								t08_nobel
								,
								t09_prize
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
									_(
										t14_game
										,
										t15_theory
										,
										21
									)
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
				_(
					h01_john
					,
					_(
						h02_c
						,
						h03_harsanyi
						,
						28
					)
					,
					29
				)
				,
				_(
					_(
						h04_received
						,
						_(
							h05_a
							,
							_(
								h06_nobel
								,
								h07_prize
								,
								23
							)
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
									_(
										h12_game
										,
										h13_theory
										,
										19
									)
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

