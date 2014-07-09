package semante.pipeline.test.rte.shared1.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel1Shared0009 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0009() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_ace = word("N","ace",2);
			val t03_of = word("P_R","of",3);
			val t04_the = word("THE","the",4);
			val t42_san_diego_padres = word("NP_D","San_Diego_Padres",42);
			val t48_app = word("WHO_A","APP",48);
			val t27_jake_peavy = word("NP_D","Jake_Peavy",27);
			val t12_was = word("IS","was",12);
			val t13_hurt = word("V_1","hurt",13);
			val t14_in = word("P_R","in",14);
			val t15_a = word("A","a",15);
			val t16_loss = word("N","loss",16);
			val t17_to = word("P_R","to",17);
			val t18_the = word("THE","the",18);
			val t38_saint_louis_cardinals = word("NP_D","Saint_Louis_Cardinals",38);

			// create the vocabulary for the hypothesis;
			val h12_jake_peavy = word("NP_D","Jake_Peavy",12);
			val h03_is = word("IS","is",3);
			val h04_a = word("A","a",4);
			val h05_player = word("N","player",5);
			val h06_of = word("P_R","of",6);
			val h07_the = word("THE","the",7);
			val h20_san_diego_padres = word("NP_D","San_Diego_Padres",20);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						_(
							t02_ace
							,
							_(
								t03_of
								,
								_(
									t04_the
									,
									t42_san_diego_padres
									,
									43
								)
								,
								25
							)
							,
							23
						)
						,
						47
					)
					,
					_(
						t48_app
						,
						t27_jake_peavy
						,
						49
					)
					,
					44
				)
				,
				_(
					t12_was
					,
					_(
						t13_hurt
						,
						_(
							t14_in
							,
							_(
								t15_a
								,
								_(
									t16_loss
									,
									_(
										t17_to
										,
										_(
											t18_the
											,
											t38_saint_louis_cardinals
											,
											39
										)
										,
										32
									)
									,
									29
								)
								,
								46
							)
							,
							30
						)
						,
						36
					)
					,
					34
				)
				,
				45
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h12_jake_peavy
				,
				_(
					h03_is
					,
					_(
						h04_a
						,
						_(
							h05_player
							,
							_(
								h06_of
								,
								_(
									h07_the
									,
									h20_san_diego_padres
									,
									21
								)
								,
								15
							)
							,
							13
						)
						,
						23
					)
					,
					17
				)
				,
				22
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(2,5));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
