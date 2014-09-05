package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0109 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0109() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_astronaut = word("N","astronaut",2);
			val t56_app = word("WHO_A","APP",56);
			val t28_neil_armstrong = word("NP_D","Neil_Armstrong",28);
			val t06_was = word("IS","was",6);
			val t07_named = word("V_2","named",7);
			val t46_ambassador_of_exploration = word("NP_D","Ambassador_of_Exploration",46);
			val t13_in = word("P_I","in",13);
			val t14_the = word("THE","the",14);
			val t15_ceremony = word("N","ceremony",15);
			val t16_by = word("P_R","by",16);
			val t17_nasa = word("NP_D","NASA",17);
			val t18_at = word("P_R","at",18);
			val t19_the = word("THE","the",19);
			val t61_smithsonian_national_air_and_space_museum = word("NP_D","Smithsonian_National_Air_and_Space_Museum",61);

			// create the vocabulary for the hypothesis;
			val h01_armstrong = word("NP_D","Armstrong",1);
			val h02_was = word("IS","was",2);
			val h03_given = word("V_2","given",3);
			val h04_a = word("A","a",4);
			val h05_title = word("N","title",5);
			val h06_of = word("P_R","of",6);
			val h31_det = word("EMPTYDET","DET",31);
			val h07_honor = word("N","honor",7);
			val h08_in = word("P_I","in",8);
			val h09_a = word("A","a",9);
			val h10_ceremony = word("N","ceremony",10);
			val h11_by = word("P_R","by",11);
			val h12_nasa = word("NP_D","NASA",12);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						t02_astronaut
						,
						27
					)
					,
					_(
						t56_app
						,
						t28_neil_armstrong
						,
						57
					)
					,
					29
				)
				,
				_(
					t06_was
					,
					_(
						_(
							_(
								t07_named
								,
								t46_ambassador_of_exploration
								,
								47
							)
							,
							_(
								t13_in
								,
								_(
									t14_the
									,
									_(
										t15_ceremony
										,
										_(
											t16_by
											,
											t17_nasa
											,
											38
										)
										,
										34
									)
									,
									58
								)
								,
								35
							)
							,
							48
						)
						,
						_(
							t18_at
							,
							_(
								t19_the
								,
								t61_smithsonian_national_air_and_space_museum
								,
								51
							)
							,
							42
						)
						,
						54
					)
					,
					44
				)
				,
				55
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_armstrong
				,
				_(
					h02_was
					,
					_(
						_(
							h03_given
							,
							_(
								h04_a
								,
								_(
									h05_title
									,
									_(
										h06_of
										,
										_(
											h31_det
											,
											h07_honor
											,
											32
										)
										,
										17
									)
									,
									15
								)
								,
								30
							)
							,
							26
						)
						,
						_(
							h08_in
							,
							_(
								h09_a
								,
								_(
									h10_ceremony
									,
									_(
										h11_by
										,
										h12_nasa
										,
										22
									)
									,
									19
								)
								,
								33
							)
							,
							20
						)
						,
						27
					)
					,
					24
				)
				,
				29
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(7,3));
		subs.add(new IPair<Integer,Integer>(28,1));
		subs.add(new IPair<Integer,Integer>(46,30));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
