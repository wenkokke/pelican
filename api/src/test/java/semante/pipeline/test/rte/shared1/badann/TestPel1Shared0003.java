package semante.pipeline.test.rte.shared1.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel1Shared0003 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel1Shared0003() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_president = word("N","president",2);
			val t03_of = word("P_R","of",3);
			val t04_congress = word("NP_D","Congress",4);
			val t61_app = word("WHO_A","APP",61);
			val t56_sonia_gandhi = word("NP_D","Sonia_Gandhi",56);
			val t07_has = word("V_AUX","has",7);
			val t08_been = word("IS","been",8);
			val t09_allotted = word("V_2","allotted",9);
			val t10_a = word("A","a",10);
			val t11_separate = word("MR","separate",11);
			val t12_office = word("N","office",12);
			val t13_here = word("MR","here",13);
			val t14_as = word("P_R","as",14);
			val t64_det = word("A","DET",64);
			val t15_head = word("N","head",15);
			val t16_of = word("P_R","of",16);
			val t17_the = word("THE","the",17);
			val t18_national = word("MI","national",18);
			val t19_advisory = word("MR","advisory",19);
			val t20_panel = word("N","panel",20);
			val t21_for = word("P_R","for",21);
			val t22_india = word("NP_D","India",22);
			val t23_s = word("GEN","s",23);
			val t53_ruling_coalition = word("N","ruling_coalition",53);

			// create the vocabulary for the hypothesis;
			val h09_ms_gandhi = word("NP_D","Ms_Gandhi",9);
			val h03_is = word("IS","is",3);
			val h04_a = word("A","a",4);
			val h05_member = word("N","member",5);
			val h06_of = word("P_R","of",6);
			val h07_congress = word("NP_D","Congress",7);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						_(
							t02_president
							,
							_(
								t03_of
								,
								t04_congress
								,
								29
							)
							,
							27
						)
						,
						59
					)
					,
					_(
						t61_app
						,
						t56_sonia_gandhi
						,
						62
					)
					,
					30
				)
				,
				_(
					t07_has
					,
					_(
						t08_been
						,
						_(
							_(
								_(
									t09_allotted
									,
									_(
										t10_a
										,
										_(
											t11_separate
											,
											t12_office
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
								t13_here
								,
								49
							)
							,
							_(
								t14_as
								,
								_(
									t64_det
									,
									_(
										t15_head
										,
										_(
											t16_of
											,
											_(
												t17_the
												,
												_(
													t18_national
													,
													_(
														t19_advisory
														,
														_(
															t20_panel
															,
															_(
																t21_for
																,
																_(
																	_(
																		t22_india
																		,
																		t23_s
																		,
																		35
																	)
																	,
																	t53_ruling_coalition
																	,
																	54
																)
																,
																37
															)
															,
															50
														)
														,
														63
													)
													,
													51
												)
												,
												52
											)
											,
											39
										)
										,
										40
									)
									,
									65
								)
								,
								41
							)
							,
							55
						)
						,
						43
					)
					,
					44
				)
				,
				58
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h09_ms_gandhi
				,
				_(
					h03_is
					,
					_(
						h04_a
						,
						_(
							h05_member
							,
							_(
								h06_of
								,
								h07_congress
								,
								12
							)
							,
							10
						)
						,
						17
					)
					,
					14
				)
				,
				16
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(2,5));
		subs.add(new IPair<Integer,Integer>(56,9));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
