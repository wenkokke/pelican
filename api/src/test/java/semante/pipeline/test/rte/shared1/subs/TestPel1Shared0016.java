package semante.pipeline.test.rte.shared1.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel1Shared0016 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0016() throws Exception {

			// create the vocabulary for the text;
			val t01_toshiba = word("NP_D","Toshiba",1);
			val t56_app = word("WHO_A","APP",56);
			val t03_the = word("THE","the",3);
			val t61_det = word("EMPTYDET","DET",61);
			val t04_world = word("N","world",4);
			val t05_s = word("POSS","s",5);
			val t06_thirdlargest = word("MR","thirdLargest",6);
			val t07_notebook = word("$NC_1$","notebook",7);
			val t08_computer = word("$NC_1$","computer",8);
			val t09_maker = word("N","maker",9);
			val t10_behind = word("P_I","behind",10);
			val t11_dell = word("$NPC_1$","Dell",11);
			val t12_inc = word("NP_D","Inc",12);
			val t14_said = word("FACT","said",14);
			val t15_the = word("THE","the",15);
			val t16_pc = word("N","PC",16);
			val t17_would = word("V_AUX","would",17);
			val t18_be = word("IS","be",18);
			val t19_introduced = word("V_1","introduced",19);
			val t20_in = word("P_R","in",20);
			val t21_japan = word("NP_D","Japan",21);
			val t22_in = word("P_R","in",22);
			val t23_early = word("MI","early",23);
			val t24_num2006 = word("NP_D","num2006",24);

			// create the vocabulary for the hypothesis;
			val h01_toshiba = word("NP_D","Toshiba",1);
			val h02_is = word("IS","is",2);
			val h03_a = word("A","a",3);
			val h04_notebook = word("$NC_1$","notebook",4);
			val h05_computer = word("$NC_1$","computer",5);
			val h06_producer = word("N","producer",6);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_toshiba
					,
					_(
						t56_app
						,
						_(
							t03_the
							,
							_(
								_(
									_(
										t61_det
										,
										t04_world
										,
										50
									)
									,
									t05_s
									,
									63
								)
								,
								_(
									t06_thirdlargest
									,
									_(
										_(
											t07_notebook
											,
											_(
												t08_computer
												,
												t09_maker
												,
												55
											)
											,
											48
										)
										,
										_(
											t10_behind
											,
											_(
												t11_dell
												,
												t12_inc
												,
												29
											)
											,
											30
										)
										,
										49
									)
									,
									68
								)
								,
								60
							)
							,
							51
						)
						,
						57
					)
					,
					53
				)
				,
				_(
					t14_said
					,
					_(
						_(
							t15_the
							,
							t16_pc
							,
							33
						)
						,
						_(
							t17_would
							,
							_(
								t18_be
								,
								_(
									_(
										t19_introduced
										,
										_(
											t20_in
											,
											t21_japan
											,
											35
										)
										,
										45
									)
									,
									_(
										t22_in
										,
										_(
											t23_early
											,
											t24_num2006
											,
											36
										)
										,
										37
									)
									,
									46
								)
								,
								39
							)
							,
							40
						)
						,
						64
					)
					,
					43
				)
				,
				54
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_toshiba
				,
				_(
					h02_is
					,
					_(
						h03_a
						,
						_(
							h04_notebook
							,
							_(
								h05_computer
								,
								h06_producer
								,
								12
							)
							,
							13
						)
						,
						14
					)
					,
					10
				)
				,
				15
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(9,6));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

