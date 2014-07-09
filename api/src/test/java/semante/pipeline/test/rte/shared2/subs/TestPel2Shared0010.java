package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0010 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0010() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_announcement = word("N","announcement",2);
			val t03_said = word("FACT","said",3);
			val t04_that = word("IGNORE","that",4);
			val t29_ghazi_yawar = word("NP_D","Ghazi_Yawar",29);
			val t59_app = word("WHO_A","APP",59);
			val t08_the = word("THE","the",8);
			val t09_sunni = word("MR","Sunni",9);
			val t10_muslim = word("N","Muslim",10);
			val t11_who = word("WHO_A","who",11);
			val t12_lived = word("V_1","lived",12);
			val t13_for = word("P_R","for",13);
			val t61_det = word("SOME","DET",61);
			val t14_years = word("N","years",14);
			val t15_in = word("P_R","in",15);
			val t34_saudi_arabia = word("NP_D","Saudi_Arabia",34);
			val t19_has = word("V_AUX","has",19);
			val t20_been = word("IS","been",20);
			val t21_picked = word("V_1","picked",21);
			val t22_as = word("P_R","as",22);
			val t23_the = word("THE","the",23);
			val t24_president = word("N","president",24);
			val t25_of = word("P_R","of",25);
			val t26_iraq = word("NP_D","Iraq",26);

			// create the vocabulary for the hypothesis;
			val h01_yawer = word("NP_D","Yawer",1);
			val h02_is = word("IS","is",2);
			val h03_a = word("A","a",3);
			val h04_sunni = word("MR","Sunni",4);
			val h05_muslim = word("N","Muslim",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					t02_announcement
					,
					28
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
								t29_ghazi_yawar
								,
								_(
									t59_app
									,
									_(
										_(
											t08_the
											,
											_(
												t09_sunni
												,
												t10_muslim
												,
												30
											)
											,
											58
										)
										,
										_(
											t11_who
											,
											_(
												_(
													t12_lived
													,
													_(
														t13_for
														,
														_(
															t61_det
															,
															t14_years
															,
															62
														)
														,
														33
													)
													,
													54
												)
												,
												_(
													t15_in
													,
													t34_saudi_arabia
													,
													35
												)
												,
												55
											)
											,
											38
										)
										,
										40
									)
									,
									60
								)
								,
								56
							)
							,
							_(
								t19_has
								,
								_(
									t20_been
									,
									_(
										t21_picked
										,
										_(
											t22_as
											,
											_(
												t23_the
												,
												_(
													t24_president
													,
													_(
														t25_of
														,
														t26_iraq
														,
														44
													)
													,
													42
												)
												,
												63
											)
											,
											46
										)
										,
										47
									)
									,
									48
								)
								,
								49
							)
							,
							50
						)
						,
						51
					)
					,
					52
				)
				,
				57
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_yawer
				,
				_(
					h02_is
					,
					_(
						h03_a
						,
						_(
							h04_sunni
							,
							h05_muslim
							,
							8
						)
						,
						13
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
		subs.add(new IPair<Integer,Integer>(29,1));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
