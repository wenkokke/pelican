package semante.pipeline.test.rte.shared2;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;

public final class TestPel2Shared0001 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0001() throws Exception {

			// create the vocabulary for the text;
			val t01_nrdc = word("NP_D","NRDC",1);
			val t02_states = word("FACT","states",2);
			val t03_that = word("IGNORE","that",3);
			val t04_the = word("THE","the",4);
			val t05_natural = word("MR","Natural",5);
			val t06_resources = word("MR","Resources",6);
			val t07_defense = word("MR","Defense",7);
			val t08_council = word("N","Council",8);
			val t48_app = word("WHO_A","APP",48);
			val t09_ = word("IGNORE","",9);
			val t10_an = word("A","an",10);
			val t11_international = word("MI","international",11);
			val t12_organization = word("N","organization",12);
			val t13_ = word("IGNORE","",13);
			val t14_safeguards = word("V_2","safeguards",14);
			val t15_the = word("THE","the",15);
			val t16_endangered = word("MR","endangered",16);
			val t17_planet = word("N","planet",17);
			val t46_app = word("WHO_A","APP",46);
			val t18_earth = word("NP_D","Earth",18);

			// create the vocabulary for the hypothesis;
			val h01_an = word("A","An",1);
			val h02_international = word("MI","international",2);
			val h03_council = word("N","council",3);
			val h33_app = word("WHO_A","APP",33);
			val h05_the = word("THE","the",5);
			val h06_natural = word("MR","Natural",6);
			val h07_resources = word("MR","Resources",7);
			val h08_defense = word("MR","Defense",8);
			val h09_council = word("N","Council",9);
			val h11_safeguards = word("V_2","safeguards",11);
			val h12_an = word("A","an",12);
			val h13_endangered = word("MR","endangered",13);
			val h14_planet = word("N","planet",14);

			// create the tree structure for the text;
			val tt =
			_(
				t01_nrdc
				,
				_(
					t02_states
					,
					_(
						t03_that
						,
						_(
							_(
								_(
									t04_the
									,
									_(
										t05_natural
										,
										_(
											t06_resources
											,
											_(
												t07_defense
												,
												t08_council
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
								_(
									t48_app
									,
									_(
										_(
											t09_
											,
											_(
												t10_an
												,
												_(
													t11_international
													,
													t12_organization
													,
													36
												)
												,
												37
											)
											,
											38
										)
										,
										t13_
										,
										39
									)
									,
									49
								)
								,
								40
							)
							,
							_(
								t14_safeguards
								,
								_(
									_(
										t15_the
										,
										_(
											t16_endangered
											,
											t17_planet
											,
											27
										)
										,
										42
									)
									,
									_(
										t46_app
										,
										t18_earth
										,
										47
									)
									,
									44
								)
								,
								24
							)
							,
							41
						)
						,
						29
					)
					,
					30
				)
				,
				43
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					_(
						h01_an
						,
						_(
							h02_international
							,
							h03_council
							,
							23
						)
						,
						24
					)
					,
					_(
						h33_app
						,
						_(
							h05_the
							,
							_(
								h06_natural
								,
								_(
									h07_resources
									,
									_(
										h08_defense
										,
										h09_council
										,
										25
									)
									,
									26
								)
								,
								27
							)
							,
							28
						)
						,
						34
					)
					,
					29
				)
				,
				_(
					h11_safeguards
					,
					_(
						h12_an
						,
						_(
							h13_endangered
							,
							h14_planet
							,
							30
						)
						,
						31
					)
					,
					21
				)
				,
				32
			)
			;

			// create the subsumption relations;

			// test for a proof;
			assertProof(tt, th);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof);
		}

}
