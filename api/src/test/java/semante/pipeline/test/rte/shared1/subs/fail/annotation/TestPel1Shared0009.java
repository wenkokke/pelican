package semante.pipeline.test.rte.shared1.subs.fail.annotation;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0009 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0009() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_ace = word("N","ace");
			val t02_of = word("P_R","of");
			val t03_the = word("THE","the");
			val t04_san = word("$NPC_1$","San");
			val t05_diego = word("$NPC_1$","Diego");
			val t06_padres = word("NP_D","Padres");
			val t07_app = word("WHO_A","APP");
			val t08_jake = word("$NPC_1$","Jake");
			val t09_peavy = word("NP_D","Peavy");
			val t10_was = word("IS","was");
			val t11_hurt = word("V_1","hurt");
			val t12_in = word("P_R","in");
			val t13_a = word("A","a");
			val t14_loss = word("N","loss");
			val t15_to = word("P_R","to");
			val t16_the = word("THE","the");
			val t17_saint = word("$NPC_1$","Saint");
			val t18_louis = word("$NPC_1$","Louis");
			val t19_cardinals = word("NP_D","Cardinals");

			// create the vocabulary for the hypothesis;
			val h00_jake = word("$NPC_1$","Jake");
			val h01_peavy = word("NP_D","Peavy");
			val h02_is = word("IS","is");
			val h03_a = word("A","a");
			val h04_player = word("N","player");
			val h05_of = word("P_R","of");
			val h06_the = word("THE","the");
			val h07_san = word("$NPC_1$","San");
			val h08_diego = word("$NPC_1$","Diego");
			val h09_padres = word("NP_D","Padres");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_the
						,
						_(
							t01_ace
							,
							_(
								t02_of
								,
								_(
									t03_the
									,
									_(
										t04_san
										,
										_(
											t05_diego
											,
											t06_padres
										)
									)
								)
							)
						)
					)
					,
					_(
						t07_app
						,
						_(
							t08_jake
							,
							t09_peavy
						)
					)
				)
				,
				_(
					t10_was
					,
					_(
						t11_hurt
						,
						_(
							t12_in
							,
							_(
								t13_a
								,
								_(
									t14_loss
									,
									_(
										t15_to
										,
										_(
											t16_the
											,
											_(
												t17_saint
												,
												_(
													t18_louis
													,
													t19_cardinals
												)
											)
										)
									)
								)
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
					h00_jake
					,
					h01_peavy
				)
				,
				_(
					h02_is
					,
					_(
						h03_a
						,
						_(
							h04_player
							,
							_(
								h05_of
								,
								_(
									h06_the
									,
									_(
										h07_san
										,
										_(
											h08_diego
											,
											h09_padres
										)
									)
								)
							)
						)
					)
				)
			)
			;

			// create the subsumption relations;
			val st000_ace = word("N","ace");
			val sh000_player = word("N","player");
val st0 = 
			st000_ace
			;
val sh0 = 
			sh000_player
			;
val ss0 = subs(st0, sh0);
Iterable<Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>> subs = of(
			ss0
);

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

