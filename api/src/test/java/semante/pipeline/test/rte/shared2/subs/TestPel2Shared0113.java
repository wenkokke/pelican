package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0113 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0113() throws Exception {

			// create the vocabulary for the text;
			val t16_shrek_num2 = word("NP_D","Shrek_num2",16);
			val t31_app = word("WHO_A","APP",31);
			val t04_the = word("THE","the",4);
			val t05_sequel = word("N","sequel",5);
			val t06_to = word("P_R","to",6);
			val t18_shrek_num1 = word("NP_D","Shrek_num1",18);
			val t27_rang_up = word("V_2","rang_up",27);
			val t34_det = word("EMPTYDET","DET",34);
			val t12_num92 = word("MR","num92",12);
			val t13_million = word("MR","million",13);
			val t14_dollars = word("N","dollars",14);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_sequel = word("N","sequel",2);
			val h03_to = word("P_R","to",3);
			val h12_shrek_num1 = word("NP_D","Shrek_num1",12);
			val h06_earned = word("V_2","earned",6);
			val h21_det = word("EMPTYDET","DET",21);
			val h07_num92 = word("MR","num92",7);
			val h08_million = word("MR","million",8);
			val h09_dollars = word("N","dollars",9);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t16_shrek_num2
					,
					_(
						t31_app
						,
						_(
							t04_the
							,
							_(
								t05_sequel
								,
								_(
									t06_to
									,
									t18_shrek_num1
									,
									19
								)
								,
								17
							)
							,
							33
						)
						,
						32
					)
					,
					29
				)
				,
				_(
					t27_rang_up
					,
					_(
						t34_det
						,
						_(
							t12_num92
							,
							_(
								t13_million
								,
								t14_dollars
								,
								23
							)
							,
							36
						)
						,
						35
					)
					,
					28
				)
				,
				30
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					_(
						h02_sequel
						,
						_(
							h03_to
							,
							h12_shrek_num1
							,
							13
						)
						,
						14
					)
					,
					20
				)
				,
				_(
					h06_earned
					,
					_(
						h21_det
						,
						_(
							h07_num92
							,
							_(
								h08_million
								,
								h09_dollars
								,
								15
							)
							,
							23
						)
						,
						22
					)
					,
					17
				)
				,
				19
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(27,6));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
