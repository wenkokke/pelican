package semante.pipeline.test.rte.shared1.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel1Shared0031 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0031() throws Exception {

			// create the vocabulary for the text;
			val t21_peter_voser = word("NP_D","Peter_Voser",21);
			val t46_app = word("WHO_A","APP",46);
			val t04_a = word("A","a",4);
			val t05_num46yearold = word("N","num46YearOld",5);
			val t06_who = word("WHO_R","who",6);
			val t07_joined = word("V_2","joined",7);
			val t08_abb = word("NP_D","ABB",8);
			val t09_from = word("P_R","from",9);
			val t10_shell = word("NP_D","Shell",10);
			val t12_will = word("IS","will",12);
			val t13_rejoin = word("V_2","rejoin",13);
			val t14_the = word("THE","the",14);
			val t15_anglodutch = word("MR","AngloDutch",15);
			val t16_company = word("N","company",16);
			val t17_on = word("P_R","on",17);
			val t33_october_num4 = word("NP_D","October_num4",33);

			// create the vocabulary for the hypothesis;
			val h11_peter_voser = word("NP_D","Peter_Voser",11);
			val h03_has = word("V_AUX","has",3);
			val h04_worked = word("V_1","worked",4);
			val h05_for = word("P_R","for",5);
			val h06_shell = word("NP_D","Shell",6);
			val h07_in = word("P_R","in",7);
			val h08_the = word("THE","the",8);
			val h09_past = word("N","past",9);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t21_peter_voser
					,
					_(
						t46_app
						,
						_(
							t04_a
							,
							_(
								t05_num46yearold
								,
								_(
									t06_who
									,
									_(
										_(
											t07_joined
											,
											t08_abb
											,
											42
										)
										,
										_(
											t09_from
											,
											t10_shell
											,
											26
										)
										,
										43
									)
									,
									29
								)
								,
								30
							)
							,
							48
						)
						,
						47
					)
					,
					44
				)
				,
				_(
					t12_will
					,
					_(
						_(
							t13_rejoin
							,
							_(
								t14_the
								,
								_(
									t15_anglodutch
									,
									t16_company
									,
									38
								)
								,
								39
							)
							,
							40
						)
						,
						_(
							t17_on
							,
							t33_october_num4
							,
							34
						)
						,
						41
					)
					,
					36
				)
				,
				45
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h11_peter_voser
				,
				_(
					_(
						_(
							h03_has
							,
							h04_worked
							,
							17
						)
						,
						_(
							h05_for
							,
							h06_shell
							,
							13
						)
						,
						22
					)
					,
					_(
						h07_in
						,
						_(
							h08_the
							,
							h09_past
							,
							14
						)
						,
						15
					)
					,
					21
				)
				,
				23
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(43,21));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
