package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0097 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0097() throws Exception {

			// create the vocabulary for the text;
			val t01_de = word("$NPC_1$","De",1);
			val t02_klerk = word("NP_D","Klerk",2);
			val t03_was = word("IS","was",3);
			val t04_deputy = word("$NPC_1$","Deputy",4);
			val t05_president = word("$NPC_1$","President",5);
			val t06_of = word("$NPC_1$","of",6);
			val t07_south = word("$NPC_1$","South",7);
			val t08_africa = word("NP_D","Africa",8);
			val t09_during = word("P_R","during",9);
			val t10_the = word("THE","the",10);
			val t11_term = word("N","term",11);
			val t12_of = word("P_R","of",12);
			val t13_the = word("THE","the",13);
			val t14_former = word("MR","former",14);
			val t15_president = word("N","president",15);
			val t44_app = word("WHO_A","APP",44);
			val t16_nelson = word("$NPC_1$","Nelson",16);
			val t17_mandela = word("NP_D","Mandela",17);
			val t18_until = word("P_R","until",18);
			val t19_num1996 = word("NP_D","num1996",19);

			// create the vocabulary for the hypothesis;
			val h01_nelson = word("$NPC_1$","Nelson",1);
			val h02_mandela = word("NP_D","Mandela",2);
			val h03_is = word("IS","is",3);
			val h04_a = word("A","a",4);
			val h05_former = word("MR","former",5);
			val h06_president = word("N","president",6);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_de
					,
					t02_klerk
					,
					21
				)
				,
				_(
					_(
						_(
							t03_was
							,
							_(
								t04_deputy
								,
								_(
									t05_president
									,
									_(
										t06_of
										,
										_(
											t07_south
											,
											t08_africa
											,
											23
										)
										,
										24
									)
									,
									22
								)
								,
								46
							)
							,
							35
						)
						,
						_(
							t09_during
							,
							_(
								t10_the
								,
								_(
									t11_term
									,
									_(
										t12_of
										,
										_(
											_(
												t13_the
												,
												_(
													t14_former
													,
													t15_president
													,
													38
												)
												,
												39
											)
											,
											_(
												t44_app
												,
												_(
													t16_nelson
													,
													t17_mandela
													,
													36
												)
												,
												45
											)
											,
											28
										)
										,
										47
									)
									,
									26
								)
								,
								43
							)
							,
							30
						)
						,
						40
					)
					,
					_(
						t18_until
						,
						t19_num1996
						,
						32
					)
					,
					41
				)
				,
				42
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_nelson
					,
					h02_mandela
					,
					8
				)
				,
				_(
					h03_is
					,
					_(
						h04_a
						,
						_(
							h05_former
							,
							h06_president
							,
							12
						)
						,
						13
					)
					,
					10
				)
				,
				14
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}

