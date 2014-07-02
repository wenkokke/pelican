package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0027 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0027() throws Exception {

			// create the vocabulary for the text;
			val t01_an = word("A","An",1);
			val t02_article = word("N","article",2);
			val t03_in = word("P_R","in",3);
			val t04_barron = word("NP_D","Barron",4);
			val t47_app = word("WHO_A","APP",47);
			val t06_the = word("THE","the",6);
			val t07_american = word("MI","American",7);
			val t08_weekly = word("MR","weekly",8);
			val t09_newspaper = word("N","newspaper",9);
			val t11_said = word("V_2","said",11);
			val t12_the = word("THE","the",12);
			val t13_industry = word("N","industry",13);
			val t14_was = word("IS","was",14);
			val t15_benefiting = word("V_1","benefiting",15);
			val t16_from = word("P_R","from",16);
			val t50_det = word("THE","DET",50);
			val t17_soaring = word("MR","soaring",17);
			val t18_natural = word("MR","natural",18);
			val t19_gas = word("$NC_1$","gas",19);
			val t20_prices = word("N","prices",20);

			// create the vocabulary for the hypothesis;
			val h01_barron = word("NP_D","Barron",1);
			val h02_is = word("IS","is",2);
			val h03_an = word("A","an",3);
			val h04_american = word("MI","American",4);
			val h05_newspaper = word("N","newspaper",5);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_an
					,
					_(
						t02_article
						,
						_(
							t03_in
							,
							_(
								t04_barron
								,
								_(
									t47_app
									,
									_(
										t06_the
										,
										_(
											t07_american
											,
											_(
												t08_weekly
												,
												t09_newspaper
												,
												41
											)
											,
											42
										)
										,
										43
									)
									,
									48
								)
								,
								23
							)
							,
							24
						)
						,
						49
					)
					,
					46
				)
				,
				_(
					t11_said
					,
					_(
						_(
							t12_the
							,
							t13_industry
							,
							28
						)
						,
						_(
							t14_was
							,
							_(
								t15_benefiting
								,
								_(
									t16_from
									,
									_(
										t50_det
										,
										_(
											t17_soaring
											,
											_(
												t18_natural
												,
												_(
													t19_gas
													,
													t20_prices
													,
													39
												)
												,
												40
											)
											,
											30
										)
										,
										51
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
					37
				)
				,
				45
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_barron
				,
				_(
					h02_is
					,
					_(
						h03_an
						,
						_(
							h04_american
							,
							h05_newspaper
							,
							11
						)
						,
						12
					)
					,
					9
				)
				,
				13
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

