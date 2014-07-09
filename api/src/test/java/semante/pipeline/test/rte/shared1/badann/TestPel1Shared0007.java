package semante.pipeline.test.rte.shared1.badann;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel1Shared0007 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0007() throws Exception {

			// create the vocabulary for the text;
			val t43_det = word("A","DET",43);
			val t01_officials = word("N","Officials",1);
			val t02_said = word("FACT","said",2);
			val t03_the = word("THE","the",3);
			val t04_finnish = word("MI","Finnish",4);
			val t05_suspect = word("N","suspect",5);
			val t06_was = word("IS","was",6);
			val t07_among = word("P_R","among",7);
			val t08_the = word("THE","the",8);
			val t09_dead = word("N","dead",9);
			val t10_but = word("AND","but",10);
			val t11_did = word("V_AUX","did",11);
			val t12_not = word("MR","not",12);
			val t13_provide = word("V_2","provide",13);
			val t14_a = word("A","a",14);
			val t15_motive = word("N","motive",15);
			val t16_for = word("P_R","for",16);
			val t17_the = word("THE","the",17);
			val t18_attack = word("N","attack",18);

			// create the vocabulary for the hypothesis;
			val h01_the = word("THE","The",1);
			val h02_suspect = word("N","suspect",2);
			val h24_app = word("WHO_A","APP",24);
			val h04_a = word("A","a",4);
			val h05_finnish = word("MI","Finnish",5);
			val h06_citizen = word("N","citizen",6);
			val h08_was = word("IS","was",8);
			val h09_among = word("P_R","among",9);
			val h10_the = word("THE","the",10);
			val h11_dead = word("N","dead",11);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t43_det
					,
					t01_officials
					,
					44
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
									,
									36
								)
								,
								37
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
										,
										22
									)
									,
									23
								)
								,
								24
							)
							,
							25
						)
						,
						27
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
													,
													29
												)
												,
												30
											)
											,
											28
										)
										,
										46
									)
									,
									32
								)
								,
								39
							)
							,
							40
						)
						,
						38
					)
					,
					45
				)
				,
				42
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					_(
						h01_the
						,
						h02_suspect
						,
						13
					)
					,
					_(
						h24_app
						,
						_(
							h04_a
							,
							_(
								h05_finnish
								,
								h06_citizen
								,
								20
							)
							,
							21
						)
						,
						25
					)
					,
					22
				)
				,
				_(
					h08_was
					,
					_(
						h09_among
						,
						_(
							h10_the
							,
							h11_dead
							,
							16
						)
						,
						17
					)
					,
					18
				)
				,
				23
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
