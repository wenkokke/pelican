package semante.pipeline.test.rte.shared1;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0038 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0038() throws Exception {

			// create the vocabulary for the text;
			val t01_mugabe = word("NP_D","Mugabe",1);
			val t02_arrived = word("V_1","arrived",2);
			val t03_in = word("P_R","in",3);
			val t04_beijing = word("NP_D","Beijing",4);
			val t05_on = word("P_R","on",5);
			val t06_saturday = word("NP_D","Saturday",6);
			val t07_and = word("AND","and",7);
			val t08_toured = word("V_2","toured",8);
			val t09_the = word("THE","the",9);
			val t10_northeastern = word("MR","northeastern",10);
			val t11_province = word("N","province",11);
			val t12_of = word("P_R","of",12);
			val t13_jilin = word("NP_D","Jilin",13);
			val t15_visiting = word("GER_2","visiting",15);
			val t16_the = word("THE","the",16);
			val t17_headquarters = word("N","headquarters",17);
			val t18_of = word("P_R","of",18);
			val t19_first = word("$NPC_1$","First",19);
			val t20_automotive = word("$NPC_1$","Automotive",20);
			val t21_works = word("$NPC_1$","Works",21);
			val t22_group = word("NP_D","Group",22);
			val t69_app = word("WHO_A","APP",69);
			val t24_a = word("A","a",24);
			val t25_top = word("MR","top",25);
			val t26_chinese = word("MI","Chinese",26);
			val t27_vehicle = word("$NC_1$","vehicle",27);
			val t28_maker = word("N","maker",28);

			// create the vocabulary for the hypothesis;
			val h01_first = word("$NPC_1$","First",1);
			val h02_automotive = word("$NPC_1$","Automotive",2);
			val h03_works = word("$NPC_1$","Works",3);
			val h04_group = word("NP_D","Group",4);
			val h05_is = word("IS","is",5);
			val h06_chinese = word("MI","Chinese",6);

			// create the tree structure for the text;
			val tt =
			_(
				t01_mugabe
				,
				_(
					_(
						_(
							_(
								_(
									t02_arrived
									,
									_(
										t03_in
										,
										t04_beijing
										,
										32
									)
									,
									51
								)
								,
								_(
									t05_on
									,
									t06_saturday
									,
									34
								)
								,
								52
							)
							,
							t07_and
							,
							53
						)
						,
						_(
							t08_toured
							,
							_(
								t09_the
								,
								_(
									_(
										t10_northeastern
										,
										t11_province
										,
										54
									)
									,
									_(
										t12_of
										,
										t13_jilin
										,
										38
									)
									,
									55
								)
								,
								67
							)
							,
							40
						)
						,
						56
					)
					,
					_(
						t15_visiting
						,
						_(
							t16_the
							,
							_(
								t17_headquarters
								,
								_(
									t18_of
									,
									_(
										_(
											t19_first
											,
											_(
												t20_automotive
												,
												_(
													t21_works
													,
													t22_group
													,
													57
												)
												,
												58
											)
											,
											59
										)
										,
										_(
											t69_app
											,
											_(
												t24_a
												,
												_(
													t25_top
													,
													_(
														t26_chinese
														,
														_(
															t27_vehicle
															,
															t28_maker
															,
															60
														)
														,
														61
													)
													,
													62
												)
												,
												63
											)
											,
											70
										)
										,
										64
									)
									,
									45
								)
								,
								41
							)
							,
							68
						)
						,
						47
					)
					,
					65
				)
				,
				66
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_first
					,
					_(
						h02_automotive
						,
						_(
							h03_works
							,
							h04_group
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
				_(
					h05_is
					,
					h06_chinese
					,
					10
				)
				,
				15
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

