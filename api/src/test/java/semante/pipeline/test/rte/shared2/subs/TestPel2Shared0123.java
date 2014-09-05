package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0123 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0123() throws Exception {

			// create the vocabulary for the text;
			val t52_det = word("THE","DET",52);
			val t01_australian = word("MI","Australian",1);
			val t02_prime = word("MR","Prime",2);
			val t03_minister = word("N","Minister",3);
			val t54_app = word("WHO_A","APP",54);
			val t26_john_howard = word("NP_D","John_Howard",26);
			val t08_has = word("V_AUX","has",8);
			val t09_made = word("V_2","made",9);
			val t10_an = word("A","an",10);
			val t11_unannounced = word("MR","unannounced",11);
			val t12_visit = word("N","visit",12);
			val t13_to = word("P_R","to",13);
			val t14_the = word("THE","the",14);
			val t15_office = word("N","office",15);
			val t16_of = word("P_R","of",16);
			val t17_the = word("THE","the",17);
			val t18_iraqi = word("MI","Iraqi",18);
			val t19_transitional = word("MR","transitional",19);
			val t20_prime = word("MR","Prime",20);
			val t21_minister = word("N","Minister",21);
			val t58_app = word("WHO_A","APP",58);
			val t45_ibrahim_aljaafari = word("NP_D","Ibrahim_alJaafari",45);

			// create the vocabulary for the hypothesis;
			val h01_howard = word("NP_D","Howard",1);
			val h02_is = word("IS","is",2);
			val h03_a = word("A","a",3);
			val h04_political = word("MR","political",4);
			val h05_representative = word("N","representative",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t52_det
						,
						_(
							t01_australian
							,
							_(
								t02_prime
								,
								t03_minister
								,
								48
							)
							,
							49
						)
						,
						53
					)
					,
					_(
						t54_app
						,
						t26_john_howard
						,
						55
					)
					,
					50
				)
				,
				_(
					t08_has
					,
					_(
						_(
							t09_made
							,
							_(
								t10_an
								,
								_(
									t11_unannounced
									,
									t12_visit
									,
									39
								)
								,
								40
							)
							,
							41
						)
						,
						_(
							t13_to
							,
							_(
								t14_the
								,
								_(
									t15_office
									,
									_(
										t16_of
										,
										_(
											_(
												t17_the
												,
												_(
													t18_iraqi
													,
													_(
														t19_transitional
														,
														_(
															t20_prime
															,
															t21_minister
															,
															42
														)
														,
														57
													)
													,
													43
												)
												,
												44
											)
											,
											_(
												t58_app
												,
												t45_ibrahim_aljaafari
												,
												59
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
								56
							)
							,
							35
						)
						,
						47
					)
					,
					37
				)
				,
				51
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_howard
				,
				_(
					h02_is
					,
					_(
						h03_a
						,
						_(
							h04_political
							,
							h05_representative
							,
							11
						)
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
		subs.add(new IPair<Integer,Integer>(26,1));
		subs.add(new IPair<Integer,Integer>(48,11));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
