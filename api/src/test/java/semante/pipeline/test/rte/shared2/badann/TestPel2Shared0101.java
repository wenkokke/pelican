package semante.pipeline.test.rte.shared2.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0101 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0101() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_manufacturers = word("N","manufacturers",2);
			val t03_of = word("P_R","of",3);
			val t04_the = word("THE","the",4);
			val t05_first = word("MR","first",5);
			val t06_privately = word("MR","privately",6);
			val t07_owned = word("MR","owned",7);
			val t08_spacecraft = word("N","spacecraft",8);
			val t09_which = word("WHO_A","which",9);
			val t10_actually = word("MR","actually",10);
			val t11_launched = word("V_1","launched",11);
			val t12_are = word("IS","are",12);
			val t13_proud = word("MR","proud",13);
			val t14_of = word("P_R","of",14);
			val t15_this = word("THE","this",15);
			val t16_achievement = word("N","achievement",16);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_privately = word("MR","privately",2);
			val h03_owned = word("MR","owned",3);
			val h04_spaceship = word("N","spaceship",4);
			val h05_launched = word("V_1","launched",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					_(
						t02_manufacturers
						,
						_(
							t03_of
							,
							_(
								_(
									t04_the
									,
									_(
										t05_first
										,
										_(
											t06_privately
											,
											_(
												t07_owned
												,
												t08_spacecraft
												,
												19
											)
											,
											39
										)
										,
										35
									)
									,
									36
								)
								,
								_(
									t09_which
									,
									_(
										t10_actually
										,
										t11_launched
										,
										24
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
						18
					)
					,
					38
				)
				,
				_(
					_(
						t12_are
						,
						t13_proud
						,
						32
					)
					,
					_(
						t14_of
						,
						_(
							t15_this
							,
							t16_achievement
							,
							29
						)
						,
						30
					)
					,
					37
				)
				,
				40
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					_(
						h02_privately
						,
						_(
							h03_owned
							,
							h04_spaceship
							,
							7
						)
						,
						14
					)
					,
					12
				)
				,
				h05_launched
				,
				13
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
