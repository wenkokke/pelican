package semante.pipeline.test.rte.shared2.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0082 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0082() throws Exception {

			// create the vocabulary for the text;
			val t01_some = word("SOME","Some",1);
			val t02_republicans = word("N","Republicans",2);
			val t03_resent = word("FACT","resent",3);
			val t04_that = word("IGNORE","that",4);
			val t05_the = word("THE","the",5);
			val t06_massachusetts = word("MI","Massachusetts",6);
			val t32_supreme_judicial_court = word("NP_D","Supreme_Judicial_Court",32);
			val t38_app = word("WHO_A","APP",38);
			val t10_ = word("IGNORE","",10);
			val t11_the = word("THE","the",11);
			val t12_sjc = word("N","SJC",12);
			val t13_ = word("IGNORE","",13);
			val t14_has = word("V_AUX","has",14);
			val t15_legalized = word("V_2","legalized",15);
			val t42_det = word("EMPTYDET","DET",42);
			val t16_gay = word("MR","gay",16);
			val t17_marriage = word("N","marriage",17);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_us = word("MI","US",2);
			val h03_sjc = word("N","SJC",3);
			val h04_legalized = word("V_2","legalized",4);
			val h15_det = word("EMPTYDET","DET",15);
			val h05_samesex = word("MR","sameSex",5);
			val h06_marriage = word("N","marriage",6);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_some
					,
					t02_republicans
					,
					19
				)
				,
				_(
					t03_resent
					,
					_(
						t04_that
						,
						_(
							_(
								t05_the
								,
								_(
									t06_massachusetts
									,
									_(
										t32_supreme_judicial_court
										,
										_(
											t38_app
											,
											_(
												_(
													t10_
													,
													_(
														t11_the
														,
														t12_sjc
														,
														21
													)
													,
													35
												)
												,
												t13_
												,
												36
											)
											,
											39
										)
										,
										33
									)
									,
									40
								)
								,
								34
							)
							,
							_(
								_(
									t14_has
									,
									t15_legalized
									,
									25
								)
								,
								_(
									t42_det
									,
									_(
										t16_gay
										,
										t17_marriage
										,
										24
									)
									,
									43
								)
								,
								41
							)
							,
							27
						)
						,
						28
					)
					,
					29
				)
				,
				37
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					_(
						h02_us
						,
						h03_sjc
						,
						12
					)
					,
					13
				)
				,
				_(
					h04_legalized
					,
					_(
						h15_det
						,
						_(
							h05_samesex
							,
							h06_marriage
							,
							9
						)
						,
						16
					)
					,
					10
				)
				,
				14
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(6,2));
		subs.add(new IPair<Integer,Integer>(16,5));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
