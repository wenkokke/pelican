package semante.pipeline.test.rte.shared1.set0;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTestWithLegacy24;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0005 extends AbsPipelineTestWithLegacy24 {

		@Test
		public final void testPel1Shared0005() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_inquiries = word("N","inquiries");
			val t02_app = word("WHO_R","APP");
			val t03_spearheaded = word("V_1","spearheaded");
			val t04_by = word("P_R","by");
			val t05_det = word("A","DET");
			val t06_milan = word("MI","Milan");
			val t07_magistrate = word("N","magistrate");
			val t08_app = word("WHO_A","APP");
			val t09_antonio = word("$NPC_1$","Antonio");
			val t10_di = word("$NPC_1$","di");
			val t11_pietro = word("NP_D","Pietro");
			val t12_focused_on = word("V_2","focused_on");
			val t13_italy = word("NP_D","Italy");
			val t14_s = word("POSS","s");
			val t15_private = word("MI","private");
			val t16_business = word("$NC_1$","business");
			val t17_sectors = word("N","sectors");

			// create the vocabulary for the hypothesis;
			val h00_antonio = word("$NPC_1$","Antonio");
			val h01_di = word("$NPC_1$","di");
			val h02_pietro = word("NP_D","Pietro");
			val h03_is = word("IS","is");
			val h04_a = word("A","a");
			val h05_magistrate = word("N","magistrate");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_the
					,
					_(
						t01_inquiries
						,
						_(
							t02_app
							,
							_(
								t03_spearheaded
								,
								_(
									t04_by
									,
									_(
										_(
											t05_det
											,
											_(
												t06_milan
												,
												t07_magistrate
											)
										)
										,
										_(
											t08_app
											,
											_(
												t09_antonio
												,
												_(
													t10_di
													,
													t11_pietro
												)
											)
										)
									)
								)
							)
						)
					)
				)
				,
				_(
					t12_focused_on
					,
					_(
						_(
							t13_italy
							,
							t14_s
						)
						,
						_(
							t15_private
							,
							_(
								t16_business
								,
								t17_sectors
							)
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h00_antonio
					,
					_(
						h01_di
						,
						h02_pietro
					)
				)
				,
				_(
					h03_is
					,
					_(
						h04_a
						,
						h05_magistrate
					)
				)
			)
			;

			// create the subsumption relations;
Iterable<Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>> subs = of(
);

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

