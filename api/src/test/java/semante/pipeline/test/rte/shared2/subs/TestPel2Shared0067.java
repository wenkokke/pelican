package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0067 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0067() throws Exception {

			// create the vocabulary for the text;
			val t52_det = word("EMPTYDET","DET",52);
			val t01_research = word("N","Research",1);
			val t02_suggests = word("FACT","suggests",2);
			val t03_that = word("IGNORE","that",3);
			val t54_det = word("EMPTYDET","DET",54);
			val t04_fiber = word("N","fiber",4);
			val t57_app = word("WHO_A","APP",57);
			val t06_an = word("A","an",6);
			val t07_indigestible = word("MI","indigestible",7);
			val t08_food = word("$NC_1$","food",8);
			val t09_group = word("N","group",9);
			val t10_which = word("WHO_A","which",10);
			val t11_is = word("IS","is",11);
			val t12_found = word("V_1","found",12);
			val t13_in = word("P_R","in",13);
			val t14_many = word("MR","many",14);
			val t15_foods = word("N","foods",15);
			val t17_may = word("V_AUX","may",17);
			val t18_keep = word("V_2","keep",18);
			val t59_det = word("EMPTYDET","DET",59);
			val t19_blood = word("$NC_1$","blood",19);
			val t20_glucose = word("N","glucose",20);
			val t21_from = word("P_R","from",21);
			val t22_rising = word("V_1","rising",22);

			// create the vocabulary for the hypothesis;
			val h10_det = word("EMPTYDET","DET",10);
			val h01_fiber = word("N","Fiber",1);
			val h02_is = word("IS","is",2);
			val h03_indigestible = word("MI","indigestible",3);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t52_det
					,
					t01_research
					,
					53
				)
				,
				_(
					t02_suggests
					,
					_(
						t03_that
						,
						_(
							_(
								_(
									t54_det
									,
									t04_fiber
									,
									25
								)
								,
								_(
									t57_app
									,
									_(
										_(
											t06_an
											,
											_(
												t07_indigestible
												,
												_(
													t08_food
													,
													t09_group
													,
													45
												)
												,
												46
											)
											,
											47
										)
										,
										_(
											t10_which
											,
											_(
												t11_is
												,
												_(
													t12_found
													,
													_(
														t13_in
														,
														_(
															t14_many
															,
															t15_foods
															,
															28
														)
														,
														29
													)
													,
													30
												)
												,
												31
											)
											,
											33
										)
										,
										34
									)
									,
									58
								)
								,
								48
							)
							,
							_(
								t17_may
								,
								_(
									_(
										t18_keep
										,
										_(
											t59_det
											,
											_(
												t19_blood
												,
												t20_glucose
												,
												36
											)
											,
											60
										)
										,
										49
									)
									,
									_(
										t21_from
										,
										t22_rising
										,
										38
									)
									,
									50
								)
								,
								40
							)
							,
							41
						)
						,
						42
					)
					,
					43
				)
				,
				51
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h10_det
					,
					h01_fiber
					,
					11
				)
				,
				_(
					h02_is
					,
					h03_indigestible
					,
					7
				)
				,
				9
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(4,1));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

