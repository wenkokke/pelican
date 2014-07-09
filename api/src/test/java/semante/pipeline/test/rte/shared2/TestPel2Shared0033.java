package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0033 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0033() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_train = word("N","train",2);
			val t03_from = word("P_R","from",3);
			val t29_union_pacific = word("NP_D","Union_Pacific",29);
			val t64_app = word("WHO_A","APP",64);
			val t06_ = word("IGNORE","",6);
			val t07_up = word("NP_D","UP",7);
			val t08_ = word("IGNORE","",8);
			val t10_which = word("WHO_A","which",10);
			val t11_lost = word("V_2","lost",11);
			val t62_det = word("EMPTYDET","DET",62);
			val t12_control = word("N","control",12);
			val t13_on = word("P_R","on",13);
			val t14_the = word("THE","the",14);
			val t15_way = word("N","way",15);
			val t16_from = word("P_R","from",16);
			val t17_kansas = word("NP_D","Kansas",17);
			val t19_got = word("V_1","got",19);
			val t20_into = word("P_R","into",20);
			val t21_an = word("A","an",21);
			val t22_accident = word("N","accident",22);
			val t24_killing = word("GER_2","killing",24);
			val t25_one = word("A","one",25);
			val t26_woman = word("N","woman",26);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h02_train = word("N","train",2);
			val h03_from = word("P_R","from",3);
			val h04_up = word("NP_D","UP",4);
			val h05_lost = word("V_2","lost",5);
			val h16_det = word("EMPTYDET","DET",16);
			val h06_control = word("N","control",6);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						_(
							t02_train
							,
							_(
								t03_from
								,
								_(
									t29_union_pacific
									,
									_(
										t64_app
										,
										_(
											_(
												t06_
												,
												t07_up
												,
												53
											)
											,
											t08_
											,
											54
										)
										,
										65
									)
									,
									32
								)
								,
								33
							)
							,
							28
						)
						,
						66
					)
					,
					_(
						t10_which
						,
						_(
							_(
								t11_lost
								,
								_(
									t62_det
									,
									t12_control
									,
									63
								)
								,
								56
							)
							,
							_(
								t13_on
								,
								_(
									t14_the
									,
									_(
										t15_way
										,
										_(
											t16_from
											,
											t17_kansas
											,
											39
										)
										,
										36
									)
									,
									61
								)
								,
								37
							)
							,
							57
						)
						,
						42
					)
					,
					59
				)
				,
				_(
					_(
						t19_got
						,
						_(
							t20_into
							,
							_(
								t21_an
								,
								t22_accident
								,
								44
							)
							,
							45
						)
						,
						51
					)
					,
					_(
						t24_killing
						,
						_(
							t25_one
							,
							t26_woman
							,
							46
						)
						,
						47
					)
					,
					52
				)
				,
				60
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					_(
						h02_train
						,
						_(
							h03_from
							,
							h04_up
							,
							10
						)
						,
						8
					)
					,
					18
				)
				,
				_(
					h05_lost
					,
					_(
						h16_det
						,
						h06_control
						,
						17
					)
					,
					13
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
