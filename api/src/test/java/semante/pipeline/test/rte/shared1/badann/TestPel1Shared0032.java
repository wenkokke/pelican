package semante.pipeline.test.rte.shared1.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel1Shared0032 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0032() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_administration = word("N","Administration",2);
			val t03_is = word("IS","is",3);
			val t04_weary = word("NP_D","weary",4);
			val t05_of = word("P_R","of",5);
			val t06_aristide = word("NP_D","Aristide",6);
			val t66_app = word("WHO_A","APP",66);
			val t08_a = word("A","a",8);
			val t09_populist = word("MR","populist",9);
			val t52_roman_catholic_priest = word("N","Roman_Catholic_priest",52);
			val t13_who = word("WHO_A","who",13);
			val t14_in = word("P_R","in",14);
			val t55_december_num1990 = word("NP_D","December_num1990",55);
			val t19_won = word("V_2","won",19);
			val t20_an = word("A","an",20);
			val t21_overwhelming = word("MR","overwhelming",21);
			val t22_victory = word("N","victory",22);
			val t23_in = word("P_R","in",23);
			val t24_haiti = word("NP_D","Haiti",24);
			val t25_s = word("GEN","s",25);
			val t26_only = word("MR","only",26);
			val t27_democratic = word("MR","democratic",27);
			val t28_presidential = word("MR","presidential",28);
			val t29_election = word("N","election",29);

			// create the vocabulary for the hypothesis;
			val h01_aristide = word("NP_D","Aristide",1);
			val h02_became = word("V_2","became",2);
			val h03_president = word("NP_D","president",3);
			val h04_in = word("P_R","in",4);
			val h05_num1990 = word("NP_D","num1990",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					t02_administration
					,
					30
				)
				,
				_(
					_(
						t03_is
						,
						t04_weary
						,
						49
					)
					,
					_(
						t05_of
						,
						_(
							t06_aristide
							,
							_(
								t66_app
								,
								_(
									_(
										t08_a
										,
										_(
											t09_populist
											,
											t52_roman_catholic_priest
											,
											53
										)
										,
										54
									)
									,
									_(
										t13_who
										,
										_(
											_(
												t14_in
												,
												t55_december_num1990
												,
												37
											)
											,
											_(
												t19_won
												,
												_(
													t20_an
													,
													_(
														_(
															t21_overwhelming
															,
															t22_victory
															,
															56
														)
														,
														_(
															t23_in
															,
															_(
																_(
																	t24_haiti
																	,
																	t25_s
																	,
																	39
																)
																,
																_(
																	t26_only
																	,
																	_(
																		t27_democratic
																		,
																		_(
																			t28_presidential
																			,
																			t29_election
																			,
																			59
																		)
																		,
																		60
																	)
																	,
																	61
																)
																,
																62
															)
															,
															41
														)
														,
														57
													)
													,
													69
												)
												,
												58
											)
											,
											63
										)
										,
										44
									)
									,
									45
								)
								,
								67
							)
							,
							64
						)
						,
						47
					)
					,
					50
				)
				,
				65
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_aristide
				,
				_(
					_(
						h02_became
						,
						h03_president
						,
						13
					)
					,
					_(
						h04_in
						,
						h05_num1990
						,
						10
					)
					,
					14
				)
				,
				15
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(58,13));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
