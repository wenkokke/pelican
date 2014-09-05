package semante.pipeline.test.rte.shared2.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0050 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0050() throws Exception {

			// create the vocabulary for the text;
			val t01_rome = word("NP_D","Rome",1);
			val t02_is = word("IS","is",2);
			val t03_situated = word("V_1","situated",3);
			val t04_in = word("P_R","in",4);
			val t05_italy = word("NP_D","Italy",5);
			val t06_s = word("POSS","s",6);
			val t07_lazio = word("MR","Lazio",7);
			val t08_province = word("N","province",8);
			val t10_which = word("WHO_A","which",10);
			val t11_is = word("IS","is",11);
			val t12_an = word("A","an",12);
			val t13_industrial = word("MI","industrial",13);
			val t14_area = word("N","area",14);

			// create the vocabulary for the hypothesis;
			val h01_rome = word("NP_D","Rome",1);
			val h02_is = word("IS","is",2);
			val h03_located = word("V_1","located",3);
			val h04_in = word("P_R","in",4);
			val h05_an = word("A","an",5);
			val h06_industrial = word("MI","industrial",6);
			val h07_province = word("N","province",7);

			// create the tree structure for the text;
			val tt =
			_(
				t01_rome
				,
				_(
					t02_is
					,
					_(
						t03_situated
						,
						_(
							t04_in
							,
							_(
								_(
									_(
										t05_italy
										,
										t06_s
										,
										17
									)
									,
									_(
										t07_lazio
										,
										t08_province
										,
										29
									)
									,
									30
								)
								,
								_(
									t10_which
									,
									_(
										t11_is
										,
										_(
											t12_an
											,
											_(
												t13_industrial
												,
												t14_area
												,
												31
											)
											,
											32
										)
										,
										21
									)
									,
									23
								)
								,
								33
							)
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
				34
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_rome
				,
				_(
					h02_is
					,
					_(
						h03_located
						,
						_(
							h04_in
							,
							_(
								h05_an
								,
								_(
									h06_industrial
									,
									h07_province
									,
									15
								)
								,
								16
							)
							,
							11
						)
						,
						12
					)
					,
					13
				)
				,
				17
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(3,3));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
