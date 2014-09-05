package semante.pipeline.test.rte.shared2.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0083 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0083() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t29_security_council = word("NP_D","Security_Council",29);
			val t04_renewed = word("V_2","renewed",4);
			val t05_the = word("THE","the",5);
			val t06_immunity = word("N","immunity",6);
			val t07_which = word("WHO_R","which",7);
			val t08_protects = word("V_2","protects",8);
			val t33_det = word("EMPTYDET","DET",33);
			val t18_us_soldiers = word("N","US_soldiers",18);
			val t11_for = word("P_R","for",11);
			val t12_a = word("A","a",12);
			val t13_year = word("N","year",13);

			// create the vocabulary for the hypothesis;
			val h01_an = word("A","An",1);
			val h11_immunity_order = word("N","immunity_order",11);
			val h04_protects = word("V_2","protects",4);
			val h14_det = word("EMPTYDET","DET",14);
			val h05_soldiers = word("N","soldiers",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					t29_security_council
					,
					30
				)
				,
				_(
					t04_renewed
					,
					_(
						t05_the
						,
						_(
							t06_immunity
							,
							_(
								t07_which
								,
								_(
									_(
										t08_protects
										,
										_(
											t33_det
											,
											t18_us_soldiers
											,
											34
										)
										,
										27
									)
									,
									_(
										t11_for
										,
										_(
											t12_a
											,
											t13_year
											,
											19
										)
										,
										20
									)
									,
									28
								)
								,
								23
							)
							,
							16
						)
						,
						32
					)
					,
					25
				)
				,
				31
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_an
					,
					h11_immunity_order
					,
					12
				)
				,
				_(
					h04_protects
					,
					_(
						h14_det
						,
						h05_soldiers
						,
						15
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
		subs.add(new IPair<Integer,Integer>(6,11));
		subs.add(new IPair<Integer,Integer>(18,5));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
