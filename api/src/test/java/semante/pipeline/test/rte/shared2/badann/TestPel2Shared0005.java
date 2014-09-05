package semante.pipeline.test.rte.shared2.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0005 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0005() throws Exception {

			// create the vocabulary for the text;
			val t18_black_tigers = word("NP_D","Black_Tigers",18);
			val t03_are = word("IS","are",3);
			val t04_disciplined = word("V_1","disciplined",4);
			val t05_and = word("AND","and",5);
			val t06_serve = word("V_2","serve",6);
			val t07_prabhakaran = word("NP_D","Prabhakaran",7);
			val t38_app = word("WHO_A","APP",38);
			val t09_ltte = word("NP_D","LTTE",9);
			val t10_s = word("POSS","s",10);
			val t11_leader = word("N","leader",11);
			val t13_by = word("P_R","by",13);
			val t14_protecting = word("GER_2","protecting",14);
			val t40_det = word("THE","DET",40);
			val t15_civilian = word("MR","civilian",15);
			val t16_targets = word("N","targets",16);

			// create the vocabulary for the hypothesis;
			val h01_prabhakaran = word("NP_D","Prabhakaran",1);
			val h02_is = word("IS","is",2);
			val h03_a = word("A","a",3);
			val h04_member = word("N","member",4);
			val h05_of = word("P_R","of",5);
			val h06_the = word("THE","the",6);
			val h07_ltte = word("NP_D","LTTE",7);

			// create the tree structure for the text;
			val tt =
			_(
				t18_black_tigers
				,
				_(
					_(
						_(
							t03_are
							,
							t04_disciplined
							,
							20
						)
						,
						t05_and
						,
						32
					)
					,
					_(
						_(
							t06_serve
							,
							_(
								t07_prabhakaran
								,
								_(
									t38_app
									,
									_(
										_(
											t09_ltte
											,
											t10_s
											,
											22
										)
										,
										t11_leader
										,
										23
									)
									,
									39
								)
								,
								33
							)
							,
							34
						)
						,
						_(
							t13_by
							,
							_(
								t14_protecting
								,
								_(
									t40_det
									,
									_(
										t15_civilian
										,
										t16_targets
										,
										25
									)
									,
									41
								)
								,
								26
							)
							,
							28
						)
						,
						35
					)
					,
					36
				)
				,
				37
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_prabhakaran
				,
				_(
					h02_is
					,
					_(
						h03_a
						,
						_(
							h04_member
							,
							_(
								h05_of
								,
								_(
									h06_the
									,
									h07_ltte
									,
									10
								)
								,
								11
							)
							,
							9
						)
						,
						15
					)
					,
					13
				)
				,
				14
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(11,4));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
