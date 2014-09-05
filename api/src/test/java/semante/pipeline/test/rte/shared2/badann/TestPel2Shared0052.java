package semante.pipeline.test.rte.shared2.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0052 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0052() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_internetborne = word("MR","InternetBorne",2);
			val t03_infection = word("N","infection",3);
			val t04_which = word("WHO_A","which",4);
			val t05_spread = word("V_1","spread",5);
			val t06_across = word("P_R","across",6);
			val t07_the = word("THE","the",7);
			val t08_globe = word("N","globe",8);
			val t09_incapacitated = word("V_2","incapacitated",9);
			val t10_the = word("THE","the",10);
			val t11_computers = word("N","computers",11);
			val t12_at = word("P_R","at",12);
			val t13_microsoft = word("NP_D","Microsoft",13);
			val t14_on = word("P_R","on",14);
			val t15_tuesday = word("NP_D","Tuesday",15);
			val t17_snarling = word("GER_2","snarling",17);
			val t45_det = word("SOME","DET",45);
			val t33_company_networks = word("N","company_networks",33);

			// create the vocabulary for the hypothesis;
			val h01_an = word("A","An",1);
			val h02_internetborne = word("MR","InternetBorne",2);
			val h03_virus = word("N","virus",3);
			val h04_spread = word("V_1","spread",4);
			val h05_across = word("P_R","across",5);
			val h06_the = word("THE","the",6);
			val h07_globe = word("N","globe",7);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						_(
							t02_internetborne
							,
							t03_infection
							,
							38
						)
						,
						39
					)
					,
					_(
						t04_which
						,
						_(
							t05_spread
							,
							_(
								t06_across
								,
								_(
									t07_the
									,
									t08_globe
									,
									22
								)
								,
								23
							)
							,
							24
						)
						,
						26
					)
					,
					27
				)
				,
				_(
					_(
						_(
							t09_incapacitated
							,
							_(
								t10_the
								,
								_(
									t11_computers
									,
									_(
										t12_at
										,
										t13_microsoft
										,
										30
									)
									,
									28
								)
								,
								44
							)
							,
							40
						)
						,
						_(
							t14_on
							,
							t15_tuesday
							,
							32
						)
						,
						42
					)
					,
					_(
						t17_snarling
						,
						_(
							t45_det
							,
							t33_company_networks
							,
							46
						)
						,
						34
					)
					,
					43
				)
				,
				37
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_an
					,
					_(
						h02_internetborne
						,
						h03_virus
						,
						14
					)
					,
					15
				)
				,
				_(
					h04_spread
					,
					_(
						h05_across
						,
						_(
							h06_the
							,
							h07_globe
							,
							10
						)
						,
						11
					)
					,
					12
				)
				,
				13
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(3,3));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
