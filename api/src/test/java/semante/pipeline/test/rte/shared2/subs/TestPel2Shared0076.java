package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0076 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0076() throws Exception {

			// create the vocabulary for the text;
			val t44_det = word("THE","DET",44);
			val t42_hollywood_actress = word("N","Hollywood_actress",42);
			val t46_app = word("WHO_A","APP",46);
			val t47_minnie_driver = word("NP_D","Minnie_Driver",47);
			val t05_is = word("IS","is",5);
			val t06_taking = word("V_2","taking",6);
			val t07_a = word("A","a",7);
			val t08_break = word("N","break",8);
			val t09_from = word("P_R","from",9);
			val t52_det = word("EMPTYDET","DET",52);
			val t10_films = word("N","films",10);
			val t11_and = word("AND","and",11);
			val t12_is = word("V_AUX","is",12);
			val t13_working = word("V_1","working",13);
			val t14_in = word("P_R","in",14);
			val t15_a = word("A","a",15);
			val t16_sweatshop = word("N","sweatshop",16);
			val t17_in = word("P_R","in",17);
			val t18_cambodia = word("NP_D","Cambodia",18);

			// create the vocabulary for the hypothesis;
			val h07_minnie_driver = word("NP_D","Minnie_Driver",7);
			val h03_is = word("IS","is",3);
			val h04_an = word("A","an",4);
			val h05_actress = word("N","actress",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t44_det
						,
						t42_hollywood_actress
						,
						45
					)
					,
					_(
						t46_app
						,
						t47_minnie_driver
						,
						49
					)
					,
					22
				)
				,
				_(
					_(
						t05_is
						,
						_(
							t06_taking
							,
							_(
								t07_a
								,
								_(
									t08_break
									,
									_(
										t09_from
										,
										_(
											t52_det
											,
											t10_films
											,
											53
										)
										,
										25
									)
									,
									23
								)
								,
								51
							)
							,
							37
						)
						,
						27
					)
					,
					_(
						t11_and
						,
						_(
							t12_is
							,
							_(
								t13_working
								,
								_(
									t14_in
									,
									_(
										t15_a
										,
										_(
											t16_sweatshop
											,
											_(
												t17_in
												,
												t18_cambodia
												,
												30
											)
											,
											28
										)
										,
										55
									)
									,
									32
								)
								,
								33
							)
							,
							34
						)
						,
						40
					)
					,
					54
				)
				,
				50
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h07_minnie_driver
				,
				_(
					h03_is
					,
					_(
						h04_an
						,
						h05_actress
						,
						8
					)
					,
					9
				)
				,
				11
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(42,5));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
