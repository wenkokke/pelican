package semante.pipeline.test.rte.shared1.set0;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0007 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0007() throws Exception {

			// create the vocabulary for the text;
			val t00_det = word("A","DET");
			val t01_officials = word("N","Officials");
			val t02_said = word("FACT","said");
			val t03_the = word("THE","the");
			val t04_finnish = word("MI","Finnish");
			val t05_suspect = word("N","suspect");
			val t06_was = word("IS","was");
			val t07_among = word("P_R","among");
			val t08_the = word("THE","the");
			val t09_dead = word("N","dead");
			val t10_but = word("AND","but");
			val t11_did = word("V_AUX","did");
			val t12_not = word("MR","not");
			val t13_provide = word("V_2","provide");
			val t14_a = word("A","a");
			val t15_motive = word("N","motive");
			val t16_for = word("P_R","for");
			val t17_the = word("THE","the");
			val t18_attack = word("N","attack");

			// create the vocabulary for the hypothesis;
			val h00_the = word("THE","The");
			val h01_suspect = word("N","suspect");
			val h02_app = word("WHO_A","APP");
			val h03_a = word("A","a");
			val h04_finnish = word("MI","Finnish");
			val h05_citizen = word("N","citizen");
			val h06_was = word("IS","was");
			val h07_among = word("P_R","among");
			val h08_the = word("THE","the");
			val h09_dead = word("N","dead");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t00_det
					,
					t01_officials
				)
				,
				_(
					_(
						t02_said
						,
						_(
							_(
								t03_the
								,
								_(
									t04_finnish
									,
									t05_suspect
								)
							)
							,
							_(
								t06_was
								,
								_(
									t07_among
									,
									_(
										t08_the
										,
										t09_dead
									)
								)
							)
						)
					)
					,
					_(
						t10_but
						,
						_(
							t11_did
							,
							_(
								t12_not
								,
								_(
									t13_provide
									,
									_(
										t14_a
										,
										_(
											t15_motive
											,
											_(
												t16_for
												,
												_(
													t17_the
													,
													t18_attack
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
				_(
					_(
						h00_the
						,
						h01_suspect
					)
					,
					_(
						h02_app
						,
						_(
							h03_a
							,
							_(
								h04_finnish
								,
								h05_citizen
							)
						)
					)
				)
				,
				_(
					h06_was
					,
					_(
						h07_among
						,
						_(
							h08_the
							,
							h09_dead
						)
					)
				)
			)
			;

			// create the subsumption relations;
val subs = of(
);

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, subs, Proof);
		}

}

