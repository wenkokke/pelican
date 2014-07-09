package semante.pipeline.test.rte.shared2.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0111 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0111() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_gdp = word("NP_D","GDP",2);
			val t70_app = word("WHO_A","APP",70);
			val t03_ = word("IGNORE","",3);
			val t36_gross_domestic_product = word("NP_D","Gross_domestic_product",36);
			val t07_ = word("IGNORE","",7);
			val t09_which = word("WHO_A","which",9);
			val t10_measures = word("V_2","measures",10);
			val t11_the = word("THE","the",11);
			val t12_value = word("N","value",12);
			val t13_of = word("P_R","of",13);
			val t14_all = word("EVERY","all",14);
			val t15_goods = word("N","goods",15);
			val t16_in = word("P_R","in",16);
			val t17_the = word("THE","the",17);
			val t18_nation = word("N","nation",18);
			val t20_rose = word("V_1","rose",20);
			val t21_at = word("P_R","at",21);
			val t22_an = word("A","an",22);
			val t23_annual = word("MR","annual",23);
			val t24_rate = word("N","rate",24);
			val t25_of = word("P_R","of",25);
			val t26_num3 = word("MR","num3",26);
			val t27_percent = word("N","percent",27);
			val t28_in = word("P_R","in",28);
			val t29_the = word("THE","the",29);
			val t30_second = word("MR","second",30);
			val t31_quarter = word("N","quarter",31);

			// create the vocabulary for the hypothesis;
			val h19_gross_domestic_product = word("NP_D","Gross_domestic_product",19);
			val h04_measures = word("V_2","measures",4);
			val h05_the = word("THE","the",5);
			val h06_value = word("N","value",6);
			val h07_of = word("P_R","of",7);
			val h08_a = word("A","a",8);
			val h09_product = word("N","product",9);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						_(
							t01_the
							,
							t02_gdp
							,
							33
						)
						,
						_(
							t70_app
							,
							_(
								_(
									t03_
									,
									t36_gross_domestic_product
									,
									65
								)
								,
								t07_
								,
								66
							)
							,
							71
						)
						,
						67
					)
					,
					_(
						t09_which
						,
						_(
							t10_measures
							,
							_(
								t11_the
								,
								_(
									t12_value
									,
									_(
										t13_of
										,
										_(
											t14_all
											,
											_(
												t15_goods
												,
												_(
													t16_in
													,
													_(
														t17_the
														,
														t18_nation
														,
														41
													)
													,
													42
												)
												,
												40
											)
											,
											73
										)
										,
										44
									)
									,
									45
								)
								,
								72
							)
							,
							46
						)
						,
						48
					)
					,
					68
				)
				,
				_(
					_(
						t20_rose
						,
						_(
							t21_at
							,
							_(
								t22_an
								,
								_(
									t23_annual
									,
									_(
										t24_rate
										,
										_(
											t25_of
											,
											_(
												t26_num3
												,
												t27_percent
												,
												51
											)
											,
											52
										)
										,
										59
									)
									,
									74
								)
								,
								60
							)
							,
							54
						)
						,
						61
					)
					,
					_(
						t28_in
						,
						_(
							t29_the
							,
							_(
								t30_second
								,
								t31_quarter
								,
								62
							)
							,
							63
						)
						,
						56
					)
					,
					64
				)
				,
				69
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h19_gross_domestic_product
				,
				_(
					h04_measures
					,
					_(
						h05_the
						,
						_(
							h06_value
							,
							_(
								h07_of
								,
								_(
									h08_a
									,
									h09_product
									,
									13
								)
								,
								14
							)
							,
							12
						)
						,
						21
					)
					,
					16
				)
				,
				20
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(15,9));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
