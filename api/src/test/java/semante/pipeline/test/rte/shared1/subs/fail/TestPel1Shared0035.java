package semante.pipeline.test.rte.shared1.subs.fail;

import lombok.val;
import org.junit.Test;
import semante.pipeline.Pair;
import semante.pipeline.SimpleBinaryTree;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import static com.google.common.collect.ImmutableList.of;

public final class TestPel1Shared0035 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0035() throws Exception {

			// create the vocabulary for the text;
			val t00_the = word("THE","The");
			val t01_chief = word("N","chief");
			val t02_of = word("P_R","of");
			val t03_the = word("THE","the");
			val t04_german = word("MR","German");
			val t05_central = word("$NPC_1$","Central");
			val t06_bank = word("NP_D","Bank");
			val t07_app = word("WHO_A","APP");
			val t08_ = word("IGNORE","");
			val t09_bundesbank = word("NP_D","Bundesbank");
			val t10_ = word("IGNORE","");
			val t11_app = word("WHO_A","APP");
			val t12_hans = word("$NPC_1$","Hans");
			val t13_tietmeyer = word("NP_D","Tietmeyer");
			val t14_justified = word("V_2","justified");
			val t15_this = word("THE","this");
			val t16_measure = word("N","measure");
			val t17_by = word("P_R","by");
			val t18_citing = word("GER_2","citing");
			val t19_the = word("THE","the");
			val t20_early = word("MR","early");
			val t21_signs = word("N","signs");
			val t22_of = word("P_R","of");
			val t23_economic = word("MR","economic");
			val t24_slowdown = word("N","slowdown");
			val t25_which = word("WHO_A","which");
			val t26_affect = word("V_2","affect");
			val t27_the = word("THE","the");
			val t28_countries = word("N","countries");
			val t29_that = word("WHO_R","that");
			val t30_will = word("IS","will");
			val t31_adopt = word("V_2","adopt");
			val t32_the = word("THE","the");
			val t33_euro = word("N","Euro");

			// create the vocabulary for the hypothesis;
			val h00_hans = word("$NPC_1$","Hans");
			val h01_tietmeyer = word("NP_D","Tietmeyer");
			val h02_app = word("WHO_A","APP");
			val h03_the = word("THE","the");
			val h04_head = word("N","head");
			val h05_of = word("P_R","of");
			val h06_bundesbank = word("NP_D","Bundesbank");
			val h07_justified = word("V_2","justified");
			val h08_the = word("THE","the");
			val h09_measures = word("N","measures");

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t00_the
						,
						_(
							t01_chief
							,
							_(
								t02_of
								,
								_(
									_(
										t03_the
										,
										_(
											t04_german
											,
											_(
												t05_central
												,
												t06_bank
											)
										)
									)
									,
									_(
										t07_app
										,
										_(
											_(
												t08_
												,
												t09_bundesbank
											)
											,
											t10_
										)
									)
								)
							)
						)
					)
					,
					_(
						t11_app
						,
						_(
							t12_hans
							,
							t13_tietmeyer
						)
					)
				)
				,
				_(
					_(
						t14_justified
						,
						_(
							t15_this
							,
							t16_measure
						)
					)
					,
					_(
						t17_by
						,
						_(
							t18_citing
							,
							_(
								_(
									t19_the
									,
									_(
										t20_early
										,
										_(
											t21_signs
											,
											_(
												t22_of
												,
												_(
													t23_economic
													,
													t24_slowdown
												)
											)
										)
									)
								)
								,
								_(
									t25_which
									,
									_(
										t26_affect
										,
										_(
											t27_the
											,
											_(
												t28_countries
												,
												_(
													t29_that
													,
													_(
														t30_will
														,
														_(
															t31_adopt
															,
															_(
																t32_the
																,
																t33_euro
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
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					_(
						h00_hans
						,
						h01_tietmeyer
					)
					,
					_(
						h02_app
						,
						_(
							h03_the
							,
							_(
								h04_head
								,
								_(
									h05_of
									,
									h06_bundesbank
								)
							)
						)
					)
				)
				,
				_(
					h07_justified
					,
					_(
						h08_the
						,
						h09_measures
					)
				)
			)
			;

			// create the subsumption relations;
			val st000_this = word("THE","this");
			val st001_measure = word("N","measure");
			val sh000_the = word("THE","the");
			val sh001_measures = word("N","measures");
val st0 = 
			_(
				st000_this
				,
				st001_measure
			)
			;
val sh0 = 
			_(
				sh000_the
				,
				sh001_measures
			)
			;
val ss0 = subs(st0, sh0);
Iterable<Pair<SimpleBinaryTree<Pair<String,String>>,SimpleBinaryTree<Pair<String,String>>>> subs = of(
			ss0
);

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

