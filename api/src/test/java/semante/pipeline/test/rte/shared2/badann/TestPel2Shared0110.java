package semante.pipeline.test.rte.shared2.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0110 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0110() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_news = word("N","news",2);
			val t03_said = word("FACT","said",3);
			val t04_the = word("THE","the",4);
			val t05_hostage = word("N","hostage",5);
			val t32_app = word("WHO_A","APP",32);
			val t07_an = word("A","an",7);
			val t08_filipino = word("MI","Filipino",8);
			val t09_tourist = word("N","tourist",9);
			val t11_was = word("IS","was",11);
			val t12_released = word("V_1","released",12);
			val t13_in = word("P_R","in",13);
			val t14_iraq = word("NP_D","Iraq",14);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_filipino = word("MI","Filipino",2);
			val h03_hostage = word("N","hostage",3);
			val h04_was = word("IS","was",4);
			val h05_freed = word("V_1","freed",5);
			val h06_in = word("P_R","in",6);
			val h07_iraq = word("NP_D","Iraq",7);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					t02_news
					,
					16
				)
				,
				_(
					t03_said
					,
					_(
						_(
							_(
								t04_the
								,
								t05_hostage
								,
								17
							)
							,
							_(
								t32_app
								,
								_(
									t07_an
									,
									_(
										t08_filipino
										,
										t09_tourist
										,
										28
									)
									,
									29
								)
								,
								33
							)
							,
							30
						)
						,
						_(
							_(
								t11_was
								,
								t12_released
								,
								23
							)
							,
							_(
								t13_in
								,
								t14_iraq
								,
								21
							)
							,
							24
						)
						,
						34
					)
					,
					26
				)
				,
				31
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					_(
						h02_filipino
						,
						h03_hostage
						,
						15
					)
					,
					16
				)
				,
				_(
					_(
						h04_was
						,
						h05_freed
						,
						13
					)
					,
					_(
						h06_in
						,
						h07_iraq
						,
						11
					)
					,
					17
				)
				,
				18
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(12,5));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
