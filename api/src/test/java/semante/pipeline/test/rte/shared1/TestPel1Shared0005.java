package semante.pipeline.test.rte.shared1;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0005 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0005() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_inquiries = word("N","inquiries",2);
			val t39_app = word("WHO_R","APP",39);
			val t03_spearheaded = word("V_1","spearheaded",3);
			val t04_by = word("P_R","by",4);
			val t42_det = word("A","DET",42);
			val t05_milan = word("MI","Milan",5);
			val t06_magistrate = word("N","magistrate",6);
			val t37_app = word("WHO_A","APP",37);
			val t07_antonio = word("$NPC_1$","Antonio",7);
			val t08_di = word("$NPC_1$","di",8);
			val t09_pietro = word("NP_D","Pietro",9);
			val t26_focused_on = word("V_2","focused_on",26);
			val t12_italy = word("NP_D","Italy",12);
			val t13_s = word("POSS","s",13);
			val t14_private = word("MI","private",14);
			val t15_business = word("$NC_1$","business",15);
			val t16_sectors = word("N","sectors",16);

			// create the vocabulary for the hypothesis;
			val h01_antonio = word("$NPC_1$","Antonio",1);
			val h02_di = word("$NPC_1$","di",2);
			val h03_pietro = word("NP_D","Pietro",3);
			val h04_is = word("IS","is",4);
			val h05_a = word("A","a",5);
			val h06_magistrate = word("N","magistrate",6);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_the
					,
					_(
						t02_inquiries
						,
						_(
							t39_app
							,
							_(
								t03_spearheaded
								,
								_(
									t04_by
									,
									_(
										_(
											t42_det
											,
											_(
												t05_milan
												,
												t06_magistrate
												,
												34
											)
											,
											43
										)
										,
										_(
											t37_app
											,
											_(
												t07_antonio
												,
												_(
													t08_di
													,
													t09_pietro
													,
													31
												)
												,
												32
											)
											,
											38
										)
										,
										20
									)
									,
									41
								)
								,
								21
							)
							,
							40
						)
						,
						18
					)
					,
					44
				)
				,
				_(
					t26_focused_on
					,
					_(
						_(
							t12_italy
							,
							t13_s
							,
							23
						)
						,
						_(
							t14_private
							,
							_(
								t15_business
								,
								t16_sectors
								,
								28
							)
							,
							29
						)
						,
						30
					)
					,
					36
				)
				,
				35
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_antonio
					,
					_(
						h02_di
						,
						h03_pietro
						,
						12
					)
					,
					13
				)
				,
				_(
					h04_is
					,
					_(
						h05_a
						,
						h06_magistrate
						,
						9
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

