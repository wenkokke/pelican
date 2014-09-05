package semante.pipeline.test.rte.shared2.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0112 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0112() throws Exception {

			// create the vocabulary for the text;
			val t01_a = word("A","A",1);
			val t02_famous = word("MI","famous",2);
			val t03_nonastronaut = word("N","nonastronaut",3);
			val t41_app = word("WHO_A","APP",41);
			val t05_the = word("THE","the",5);
			val t06_former = word("MR","former",6);
			val t07_cbs = word("MR","CBS",7);
			val t08_newsman = word("N","newsman",8);
			val t39_app = word("WHO_A","APP",39);
			val t21_walter_cronkite = word("NP_D","Walter_Cronkite",21);
			val t11_also = word("MR","also",11);
			val t12_received = word("V_2","received",12);
			val t13_the = word("THE","the",13);
			val t14_ambassador = word("MR","Ambassador",14);
			val t15_of = word("MR","of",15);
			val t16_exploration = word("MR","Exploration",16);
			val t17_awards = word("N","awards",17);

			// create the vocabulary for the hypothesis;
			val h01_the = word("THE","The",1);
			val h02_famous = word("MI","famous",2);
			val h03_cbs = word("MR","CBS",3);
			val h04_news = word("MR","News",4);
			val h05_anchor = word("N","anchor",5);
			val h29_app = word("WHO_A","APP",29);
			val h22_walter_cronkite = word("NP_D","Walter_Cronkite",22);
			val h08_also = word("MR","also",8);
			val h09_received = word("V_2","received",9);
			val h10_an = word("A","an",10);
			val h11_ambassador = word("MR","ambassador",11);
			val h12_honor = word("N","honor",12);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_a
						,
						_(
							t02_famous
							,
							t03_nonastronaut
							,
							32
						)
						,
						33
					)
					,
					_(
						t41_app
						,
						_(
							_(
								t05_the
								,
								_(
									t06_former
									,
									_(
										t07_cbs
										,
										t08_newsman
										,
										34
									)
									,
									35
								)
								,
								36
							)
							,
							_(
								t39_app
								,
								t21_walter_cronkite
								,
								40
							)
							,
							22
						)
						,
						42
					)
					,
					37
				)
				,
				_(
					t11_also
					,
					_(
						t12_received
						,
						_(
							t13_the
							,
							_(
								t14_ambassador
								,
								_(
									t15_of
									,
									_(
										t16_exploration
										,
										t17_awards
										,
										26
									)
									,
									27
								)
								,
								25
							)
							,
							43
						)
						,
						29
					)
					,
					31
				)
				,
				38
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					_(
						h01_the
						,
						_(
							h02_famous
							,
							_(
								h03_cbs
								,
								_(
									h04_news
									,
									h05_anchor
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
					_(
						h29_app
						,
						h22_walter_cronkite
						,
						30
					)
					,
					28
				)
				,
				_(
					h08_also
					,
					_(
						h09_received
						,
						_(
							h10_an
							,
							_(
								h11_ambassador
								,
								h12_honor
								,
								19
							)
							,
							20
						)
						,
						17
					)
					,
					21
				)
				,
				31
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(34,25));
		subs.add(new IPair<Integer,Integer>(43,20));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
