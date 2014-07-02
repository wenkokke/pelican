package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0080 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0080() throws Exception {

			// create the vocabulary for the text;
			val t01_norway = word("NP_D","Norway",1);
			val t02_s = word("POSS","s",2);
			val t03_famous = word("MI","famous",3);
			val t04_painting = word("N","painting",4);
			val t49_app = word("WHO_A","APP",49);
			val t07_the = word("THE","The",7);
			val t08_scream = word("NP_D","Scream",8);
			val t10_which = word("WHO_A","which",10);
			val t11_is = word("IS","is",11);
			val t12_by = word("P_R","by",12);
			val t51_det = word("THE","DET",51);
			val t13_painter = word("N","painter",13);
			val t53_app = word("WHO_A","APP",53);
			val t14_edvard = word("$NPC_1$","Edvard",14);
			val t15_munch = word("NP_D","Munch",15);
			val t17_was = word("IS","was",17);
			val t18_stolen = word("V_1","stolen",18);
			val t19_from = word("P_R","from",19);
			val t20_an = word("A","an",20);
			val t21_oslo = word("MR","Oslo",21);
			val t22_museum = word("N","museum",22);

			// create the vocabulary for the hypothesis;
			val h02_the = word("THE","The",2);
			val h03_scream = word("NP_D","Scream",3);
			val h06_which = word("WHO_A","which",6);
			val h07_is = word("IS","is",7);
			val h08_by = word("P_R","by",8);
			val h09_edvard = word("$NPC_1$","Edvard",9);
			val h10_munch = word("NP_D","Munch",10);
			val h12_is = word("IS","is",12);
			val h13_famous = word("MI","famous",13);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						_(
							t01_norway
							,
							t02_s
							,
							24
						)
						,
						_(
							t03_famous
							,
							t04_painting
							,
							42
						)
						,
						43
					)
					,
					_(
						t49_app
						,
						_(
							_(
								t07_the
								,
								t08_scream
								,
								26
							)
							,
							_(
								t10_which
								,
								_(
									t11_is
									,
									_(
										t12_by
										,
										_(
											_(
												t51_det
												,
												t13_painter
												,
												52
											)
											,
											_(
												t53_app
												,
												_(
													t14_edvard
													,
													t15_munch
													,
													44
												)
												,
												54
											)
											,
											29
										)
										,
										55
									)
									,
									30
								)
								,
								32
							)
							,
							46
						)
						,
						50
					)
					,
					47
				)
				,
				_(
					t17_was
					,
					_(
						t18_stolen
						,
						_(
							t19_from
							,
							_(
								t20_an
								,
								_(
									t21_oslo
									,
									t22_museum
									,
									40
								)
								,
								41
							)
							,
							36
						)
						,
						37
					)
					,
					38
				)
				,
				48
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					_(
						h02_the
						,
						h03_scream
						,
						26
					)
					,
					_(
						h06_which
						,
						_(
							h07_is
							,
							_(
								h08_by
								,
								_(
									h09_edvard
									,
									h10_munch
									,
									17
								)
								,
								18
							)
							,
							19
						)
						,
						21
					)
					,
					27
				)
				,
				_(
					h12_is
					,
					h13_famous
					,
					24
				)
				,
				28
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

