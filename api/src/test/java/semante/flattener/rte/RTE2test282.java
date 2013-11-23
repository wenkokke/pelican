package semante.flattener.rte;

import lombok.val;

import org.junit.Test;

import semante.Entailment;
import semante.IEntailment;
import semante.pipeline.APipelineTest;

public final class RTE2test282 extends APipelineTest {

		@Test
		public final void prove() throws Exception {
			proveEntailment(createRTE2test282());
		}

		@Test
		public final void createTestCase() throws Exception {
			createTestCase("RTE2test282",createRTE2test282());
		}

		public final Entailment createRTE2test282() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_minister = word("N","minister");
			val t02_app = word("WHO_A","APP");
			val t03_downer = word("NP_D","Downer");
			val t04_will = word("IS","will");
			val t05_host = word("V_2","host");
			val t06_the = word("THE","the");
			val t07_20th = word("MR","20th");
			val t08_annual = word("MR","annual");
			val t09_ausmin = word("NP_D","AUSMIN");
			val t10_app = word("WHO_A","APP");
			val t11_a = word("A","a");
			val t12_conference = word("N","conference");
			val t13_at = word("P_R","at");
			val t14_the = word("THE","the");
			val t15_adelaide = word("NP_D","Adelaide");
			val t16_app = word("WHO_A","APP");
			val t17_a = word("A","a");
			val t18_townhall = word("N","townHall");

			// create the vocabulary for the hypothesis;
			val h00_downer = word("NP_D","Downer");
			val h01_will = word("IS","will");
			val h02_host = word("V_2","host");
			val h03_a = word("A","a");
			val h04_conference = word("N","conference");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_the
						,
						t01_minister
					)
					,
					_(
						t02_app
						,
						t03_downer
					)
				)
				,
				_(
					t04_will
					,
					_(
						t05_host
						,
						_(
							_(
								t06_the
								,
								_(
									t07_20th
									,
									_(
										t08_annual
										,
										t09_ausmin
									)
								)
							)
							,
							_(
								t10_app
								,
								_(
									t11_a
									,
									_(
										t12_conference
										,
										_(
											t13_at
											,
											_(
												t14_the
												,
												_(
													t15_adelaide
													,
													_(
														t16_app
														,
														_(
															t17_a
															,
															t18_townhall
														)
													)
												)
											)
										)
									)
								)
							)
						)
					)
				)
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h00_downer
				,
				_(
					h01_will
					,
					_(
						h02_host
						,
						_(
							h03_a
							,
							h04_conference
						)
					)
				)
			)
			;

			// create the subsumption relations;
			val ss =
			new String[] {
				""
			};

			// return the new entailment;
			return new IEntailment(tt, th, ss);
		}

}
