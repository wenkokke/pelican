package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0083 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0083() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_security = word("$NPC_1$","Security",2);
			val t03_council = word("NP_D","Council",3);
			val t04_renewed = word("V_2","renewed",4);
			val t05_the = word("THE","the",5);
			val t06_immunity = word("N","immunity",6);
			val t07_which = word("WHO_R","which",7);
			val t08_protects = word("V_2","protects",8);
			val t33_det = word("EMPTYDET","DET",33);
			val t09_us = word("$NC_1$","US",9);
			val t10_soldiers = word("N","soldiers",10);
			val t11_for = word("P_R","for",11);
			val t12_a = word("A","a",12);
			val t13_year = word("N","year",13);

			// create the vocabulary for the hypothesis;
			val h01_an = word("A","An",1);
			val h02_immunity = word("$NC_1$","immunity",2);
			val h03_order = word("N","order",3);
			val h04_protects = word("V_2","protects",4);
			val h14_det = word("EMPTYDET","DET",14);
			val h05_soldiers = word("N","soldiers",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					_(
						t02_security
						,
						t03_council
						,
						29
					)
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
											_(
												t09_us
												,
												t10_soldiers
												,
												18
											)
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
					_(
						h02_immunity
						,
						h03_order
						,
						11
					)
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

