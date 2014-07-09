package semante.pipeline.test.rte.shared2.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0020 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0020() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_directive = word("N","directive",2);
			val t61_app = word("WHO_A","APP",61);
			val t04_issued = word("V_1","issued",4);
			val t05_by = word("P_R","by",5);
			val t06_vietnam = word("NP_D","Vietnam",6);
			val t07_s = word("POSS","s",7);
			val t08_minister = word("N","Minister",8);
			val t09_of = word("P_R","of",9);
			val t10_post = word("NP_D","Post",10);
			val t66_app = word("WHO_A","APP",66);
			val t37_do_trung_ta = word("NP_D","Do_Trung_Ta",37);
			val t14_on = word("P_I","on",14);
			val t15_monday = word("NP_D","Monday",15);
			val t17_is = word("IS","is",17);
			val t18_the = word("THE","the",18);
			val t19_latest = word("MI","latest",19);
			val t20_measure = word("N","measure",20);
			val t71_app = word("WHO_R","APP",71);
			val t21_aimed = word("V_1","aimed",21);
			val t22_at = word("P_R","at",22);
			val t23_preventing = word("GER_2","preventing",23);
			val t69_det = word("EMPTYDET","DET",69);
			val t25_poisonous = word("MR","poisonous",25);
			val t26_information = word("N","information",26);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_recent = word("MI","recent",2);
			val h03_measure = word("N","measure",3);
			val h41_app = word("WHO_R","APP",41);
			val h04_aimed = word("V_1","aimed",4);
			val h05_at = word("P_R","at",5);
			val h06_constraining = word("GER_2","constraining",6);
			val h43_det = word("EMPTYDET","DET",43);
			val h08_poisonous = word("MR","poisonous",8);
			val h09_information = word("N","information",9);
			val h11_was = word("V_AUX","was",11);
			val h12_issued = word("V_1","issued",12);
			val h13_by = word("P_R","by",13);
			val h14_vietnam = word("NP_D","Vietnam",14);
			val h15_s = word("POSS","s",15);
			val h16_minister = word("N","Minister",16);
			val h17_of = word("P_R","of",17);
			val h18_post = word("NP_D","Post",18);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						t02_directive
						,
						29
					)
					,
					_(
						t61_app
						,
						_(
							_(
								t04_issued
								,
								_(
									t05_by
									,
									_(
										_(
											_(
												t06_vietnam
												,
												t07_s
												,
												30
											)
											,
											_(
												t08_minister
												,
												_(
													t09_of
													,
													t10_post
													,
													33
												)
												,
												31
											)
											,
											63
										)
										,
										_(
											t66_app
											,
											t37_do_trung_ta
											,
											67
										)
										,
										58
									)
									,
									42
								)
								,
								43
							)
							,
							_(
								t14_on
								,
								t15_monday
								,
								36
							)
							,
							62
						)
						,
						65
					)
					,
					59
				)
				,
				_(
					t17_is
					,
					_(
						t18_the
						,
						_(
							t19_latest
							,
							_(
								t20_measure
								,
								_(
									t71_app
									,
									_(
										t21_aimed
										,
										_(
											t22_at
											,
											_(
												t23_preventing
												,
												_(
													t69_det
													,
													_(
														t25_poisonous
														,
														t26_information
														,
														56
													)
													,
													70
												)
												,
												47
											)
											,
											49
										)
										,
										50
									)
									,
									72
								)
								,
								54
							)
							,
							68
						)
						,
						55
					)
					,
					52
				)
				,
				60
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					_(
						h02_recent
						,
						_(
							h03_measure
							,
							_(
								h41_app
								,
								_(
									h04_aimed
									,
									_(
										h05_at
										,
										_(
											h06_constraining
											,
											_(
												h43_det
												,
												_(
													h08_poisonous
													,
													h09_information
													,
													38
												)
												,
												44
											)
											,
											22
										)
										,
										24
									)
									,
									25
								)
								,
								42
							)
							,
							36
						)
						,
						45
					)
					,
					37
				)
				,
				_(
					h11_was
					,
					_(
						h12_issued
						,
						_(
							h13_by
							,
							_(
								_(
									h14_vietnam
									,
									h15_s
									,
									27
								)
								,
								_(
									h16_minister
									,
									_(
										h17_of
										,
										h18_post
										,
										30
									)
									,
									28
								)
								,
								40
							)
							,
							32
						)
						,
						33
					)
					,
					34
				)
				,
				39
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(19,2));
		subs.add(new IPair<Integer,Integer>(23,6));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
