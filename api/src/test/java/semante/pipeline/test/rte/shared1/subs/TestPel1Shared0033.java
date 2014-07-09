package semante.pipeline.test.rte.shared1.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel1Shared0033 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0033() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_visit = word("N","visit",2);
			val t03_comes = word("V_1","comes",3);
			val t04_amid = word("P_R","amid",4);
			val t05_rumors = word("N","rumors",5);
			val t06_about = word("P_R","about",6);
			val t07_china = word("NP_D","China",7);
			val t08_s = word("GEN","s",8);
			val t09_num90yearold = word("MI","num90YearOld",9);
			val t10_senior = word("MR","senior",10);
			val t11_leader = word("N","leader",11);
			val t43_app = word("WHO_A","APP",43);
			val t24_deng_xiaoping = word("NP_D","Deng_Xiaoping",24);
			val t16_who = word("WHO_A","who",16);
			val t17_is = word("IS","is",17);
			val t18_near = word("P_R","near",18);
			val t19_death = word("NP_D","death",19);

			// create the vocabulary for the hypothesis;
			val h07_deng_xiaoping = word("NP_D","Deng_Xiaoping",7);
			val h03_is = word("IS","is",3);
			val h04_num90 = word("IGNORE","num90",4);
			val h05_years = word("IGNORE","years",5);
			val h06_old = word("MI","old",6);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					t02_visit
					,
					20
				)
				,
				_(
					t03_comes
					,
					_(
						t04_amid
						,
						_(
							t05_rumors
							,
							_(
								t06_about
								,
								_(
									_(
										_(
											t07_china
											,
											t08_s
											,
											22
										)
										,
										_(
											t09_num90yearold
											,
											_(
												t10_senior
												,
												t11_leader
												,
												37
											)
											,
											38
										)
										,
										39
									)
									,
									_(
										t43_app
										,
										_(
											t24_deng_xiaoping
											,
											_(
												t16_who
												,
												_(
													t17_is
													,
													_(
														t18_near
														,
														t19_death
														,
														27
													)
													,
													28
												)
												,
												30
											)
											,
											44
										)
										,
										45
									)
									,
									40
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
					35
				)
				,
				36
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h07_deng_xiaoping
				,
				_(
					h03_is
					,
					_(
						h04_num90
						,
						_(
							h05_years
							,
							h06_old
							,
							8
						)
						,
						12
					)
					,
					10
				)
				,
				11
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(9,12));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
