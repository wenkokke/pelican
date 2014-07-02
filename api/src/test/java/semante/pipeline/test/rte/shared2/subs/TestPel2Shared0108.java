package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0108 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0108() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_turks = word("NP_D","Turks",2);
			val t03_took = word("V_2","took",3);
			val t04_the = word("THE","the",4);
			val t05_former = word("MR","former",5);
			val t06_capital = word("N","capital",6);
			val t07_of = word("P_R","of",7);
			val t08_hungary = word("NP_D","Hungary",8);
			val t45_app = word("WHO_A","APP",45);
			val t10_buda = word("NP_D","Buda",10);
			val t12_in = word("P_R","in",12);
			val t13_num1541 = word("NP_D","num1541",13);
			val t14_and = word("AND","and",14);
			val t15_held = word("V_2","held",15);
			val t16_it = word("NP_D","it",16);
			val t17_until = word("P_R","until",17);
			val t18_num1686 = word("NP_D","num1686",18);

			// create the vocabulary for the hypothesis;
			val h15_det = word("SOME","DET",15);
			val h01_invaders = word("N","Invaders",1);
			val h02_took = word("V_2","took",2);
			val h03_buda = word("NP_D","Buda",3);
			val h04_in = word("P_R","in",4);
			val h05_num1541 = word("NP_D","num1541",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					t02_turks
					,
					20
				)
				,
				_(
					_(
						_(
							_(
								t03_took
								,
								_(
									_(
										t04_the
										,
										_(
											t05_former
											,
											_(
												t06_capital
												,
												_(
													t07_of
													,
													t08_hungary
													,
													23
												)
												,
												34
											)
											,
											44
										)
										,
										35
									)
									,
									_(
										t45_app
										,
										t10_buda
										,
										46
									)
									,
									24
								)
								,
								37
							)
							,
							_(
								t12_in
								,
								t13_num1541
								,
								26
							)
							,
							38
						)
						,
						t14_and
						,
						39
					)
					,
					_(
						_(
							t15_held
							,
							t16_it
							,
							40
						)
						,
						_(
							t17_until
							,
							t18_num1686
							,
							30
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
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h15_det
					,
					h01_invaders
					,
					16
				)
				,
				_(
					_(
						h02_took
						,
						h03_buda
						,
						12
					)
					,
					_(
						h04_in
						,
						h05_num1541
						,
						10
					)
					,
					14
				)
				,
				17
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(20,16));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

