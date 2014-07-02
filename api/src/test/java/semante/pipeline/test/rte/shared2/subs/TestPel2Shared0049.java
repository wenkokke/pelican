package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0049 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0049() throws Exception {

			// create the vocabulary for the text;
			val t01_sirhan = word("$NPC_1$","Sirhan",1);
			val t02_sirhan = word("NP_D","Sirhan",2);
			val t74_app = word("WHO_A","APP",74);
			val t04_a = word("A","a",4);
			val t05_jordanian = word("MI","Jordanian",5);
			val t06_immigrant = word("N","immigrant",6);
			val t08_murdered = word("V_2","murdered",8);
			val t09_robert = word("$NPC_1$","Robert",9);
			val t10_f = word("$NPC_1$","F",10);
			val t11_kennedy = word("NP_D","Kennedy",11);
			val t72_app = word("WHO_A","APP",72);
			val t13_the = word("THE","the",13);
			val t14_american = word("MI","American",14);
			val t15_senator = word("N","senator",15);
			val t16_who = word("WHO_R","who",16);
			val t17_was = word("IS","was",17);
			val t18_a = word("A","a",18);
			val t19_brother = word("N","brother",19);
			val t20_of = word("P_R","of",20);
			val t21_john = word("$NPC_1$","John",21);
			val t22_f = word("$NPC_1$","F",22);
			val t23_kennedy = word("NP_D","Kennedy",23);
			val t25_serving = word("GER_2","serving",25);
			val t26_a = word("A","a",26);
			val t27_life = word("$NC_1$","life",27);
			val t28_term = word("N","term",28);
			val t29_at = word("P_R","at",29);
			val t30_soledad = word("$NPC_1$","Soledad",30);
			val t31_prison = word("NP_D","Prison",31);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_jordanian = word("MI","Jordanian",2);
			val h03_immigrant = word("N","immigrant",3);
			val h04_killed = word("V_2","killed",4);
			val h05_an = word("A","an",5);
			val h06_american = word("MI","American",6);
			val h07_senator = word("N","senator",7);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_sirhan
						,
						t02_sirhan
						,
						32
					)
					,
					_(
						t74_app
						,
						_(
							t04_a
							,
							_(
								t05_jordanian
								,
								t06_immigrant
								,
								55
							)
							,
							56
						)
						,
						75
					)
					,
					57
				)
				,
				_(
					_(
						t08_murdered
						,
						_(
							_(
								t09_robert
								,
								_(
									t10_f
									,
									t11_kennedy
									,
									58
								)
								,
								59
							)
							,
							_(
								t72_app
								,
								_(
									t13_the
									,
									_(
										t14_american
										,
										_(
											t15_senator
											,
											_(
												t16_who
												,
												_(
													t17_was
													,
													_(
														t18_a
														,
														_(
															t19_brother
															,
															_(
																t20_of
																,
																_(
																	t21_john
																	,
																	_(
																		t22_f
																		,
																		t23_kennedy
																		,
																		62
																	)
																	,
																	63
																)
																,
																40
															)
															,
															38
														)
														,
														69
													)
													,
													42
												)
												,
												44
											)
											,
											60
										)
										,
										71
									)
									,
									61
								)
								,
								73
							)
							,
							64
						)
						,
						65
					)
					,
					_(
						t25_serving
						,
						_(
							t26_a
							,
							_(
								_(
									t27_life
									,
									t28_term
									,
									66
								)
								,
								_(
									t29_at
									,
									_(
										t30_soledad
										,
										t31_prison
										,
										52
									)
									,
									53
								)
								,
								67
							)
							,
							70
						)
						,
						48
					)
					,
					68
				)
				,
				51
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					_(
						h02_jordanian
						,
						h03_immigrant
						,
						12
					)
					,
					13
				)
				,
				_(
					h04_killed
					,
					_(
						h05_an
						,
						_(
							h06_american
							,
							h07_senator
							,
							14
						)
						,
						15
					)
					,
					10
				)
				,
				11
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(8,4));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

