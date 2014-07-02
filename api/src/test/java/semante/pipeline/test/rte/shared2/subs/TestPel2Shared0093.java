package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0093 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0093() throws Exception {

			// create the vocabulary for the text;
			val t01_scripps = word("$NPC_1$","Scripps",1);
			val t02_memorial = word("$NPC_1$","Memorial",2);
			val t03_hospital = word("$NPC_1$","Hospital",3);
			val t04_encinitas = word("NP_D","Encinitas",4);
			val t29_app = word("WHO_A","APP",29);
			val t06_the = word("THE","the",6);
			val t07_nonprofit = word("MR","nonprofit",7);
			val t08_californian = word("MI","Californian",8);
			val t09_hospital = word("N","hospital",9);
			val t11_treats = word("V_2","treats",11);
			val t31_det = word("A","DET",31);
			val t12_injured = word("MR","injured",12);
			val t13_surfers = word("N","surfers",13);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_californian = word("MI","Californian",2);
			val h03_hospital = word("N","hospital",3);
			val h04_assists = word("V_2","assists",4);
			val h18_det = word("A","DET",18);
			val h05_surfing = word("$NC_1$","surfing",5);
			val h06_accident = word("$NC_1$","accident",6);
			val h07_victims = word("N","victims",7);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_scripps
						,
						_(
							t02_memorial
							,
							_(
								t03_hospital
								,
								t04_encinitas
								,
								21
							)
							,
							22
						)
						,
						23
					)
					,
					_(
						t29_app
						,
						_(
							t06_the
							,
							_(
								t07_nonprofit
								,
								_(
									t08_californian
									,
									t09_hospital
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
						30
					)
					,
					27
				)
				,
				_(
					t11_treats
					,
					_(
						t31_det
						,
						_(
							t12_injured
							,
							t13_surfers
							,
							18
						)
						,
						32
					)
					,
					19
				)
				,
				28
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					_(
						h02_californian
						,
						h03_hospital
						,
						15
					)
					,
					16
				)
				,
				_(
					h04_assists
					,
					_(
						h18_det
						,
						_(
							h05_surfing
							,
							_(
								h06_accident
								,
								h07_victims
								,
								10
							)
							,
							11
						)
						,
						19
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
		subs.add(new IPair<Integer,Integer>(18,11));
		subs.add(new IPair<Integer,Integer>(11,4));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

