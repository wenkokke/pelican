package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0002 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0002() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_abode = word("N","abode",2);
			val t03_of = word("P_R","of",3);
			val t04_the = word("THE","the",4);
			val t05_greek = word("MI","Greek",5);
			val t06_gods = word("N","gods",6);
			val t07_was = word("IS","was",7);
			val t08_on = word("P_R","on",8);
			val t09_the = word("THE","the",9);
			val t10_summit = word("N","summit",10);
			val t11_of = word("P_R","of",11);
			val t33_mount_olympus = word("NP_D","Mount_Olympus",33);
			val t61_app = word("WHO_A","APP",61);
			val t15_the = word("THE","the",15);
			val t16_mountain = word("N","mountain",16);
			val t17_which = word("WHO_A","which",17);
			val t37_served_as = word("V_2","served_as",37);
			val t20_a = word("A","a",20);
			val t21_greek = word("MI","Greek",21);
			val t22_battle = word("MR","battle",22);
			val t23_site = word("N","site",23);
			val t25_in = word("P_I","in",25);
			val t26_thessaly = word("NP_D","Thessaly",26);

			// create the vocabulary for the hypothesis;
			val h01_olympus = word("NP_D","Olympus",1);
			val h02_is = word("IS","is",2);
			val h03_a = word("A","a",3);
			val h04_greek = word("MI","Greek",4);
			val h05_mountain = word("N","mountain",5);
			val h06_in = word("P_I","in",6);
			val h07_thessaly = word("NP_D","Thessaly",7);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					_(
						t02_abode
						,
						_(
							t03_of
							,
							_(
								t04_the
								,
								_(
									t05_greek
									,
									t06_gods
									,
									56
								)
								,
								57
							)
							,
							30
						)
						,
						28
					)
					,
					59
				)
				,
				_(
					t07_was
					,
					_(
						t08_on
						,
						_(
							t09_the
							,
							_(
								t10_summit
								,
								_(
									t11_of
									,
									_(
										t33_mount_olympus
										,
										_(
											t61_app
											,
											_(
												_(
													t15_the
													,
													t16_mountain
													,
													34
												)
												,
												_(
													t17_which
													,
													_(
														t37_served_as
														,
														_(
															t20_a
															,
															_(
																t21_greek
																,
																_(
																	t22_battle
																	,
																	_(
																		t23_site
																		,
																		_(
																			t25_in
																			,
																			t26_thessaly
																			,
																			39
																		)
																		,
																		50
																	)
																	,
																	64
																)
																,
																51
															)
															,
															52
														)
														,
														63
													)
													,
													42
												)
												,
												43
											)
											,
											62
										)
										,
										55
									)
									,
									45
								)
								,
								32
							)
							,
							60
						)
						,
						47
					)
					,
					48
				)
				,
				58
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_olympus
				,
				_(
					h02_is
					,
					_(
						h03_a
						,
						_(
							h04_greek
							,
							_(
								h05_mountain
								,
								_(
									h06_in
									,
									h07_thessaly
									,
									12
								)
								,
								16
							)
							,
							19
						)
						,
						17
					)
					,
					14
				)
				,
				18
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(37,2));
		subs.add(new IPair<Integer,Integer>(33,1));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
