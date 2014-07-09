package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0071 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0071() throws Exception {

			// create the vocabulary for the text;
			val t01_ahern = word("NP_D","Ahern",1);
			val t03_who = word("WHO_A","who",3);
			val t04_was = word("IS","was",4);
			val t05_traveling = word("V_1","traveling",5);
			val t06_to = word("P_R","to",6);
			val t07_tokyo = word("NP_D","Tokyo",7);
			val t08_for = word("P_R","for",8);
			val t09_the = word("THE","the",9);
			val t10_eujapan = word("MR","EUJapan",10);
			val t11_summit = word("N","summit",11);
			val t12_yesterday = word("MR","yesterday",12);
			val t14_will = word("V_AUX","will",14);
			val t15_consult = word("V_2","consult",15);
			val t66_det = word("EMPTYDET","DET",66);
			val t16_other = word("MR","other",16);
			val t17_eu = word("MR","EU",17);
			val t18_leaders = word("N","leaders",18);
			val t19_by = word("P_R","by",19);
			val t70_det = word("EMPTYDET","DET",70);
			val t20_telephone = word("N","telephone",20);
			val t21_about = word("P_R","about",21);
			val t22_the = word("THE","the",22);
			val t23_decision = word("N","decision",23);
			val t24_for = word("P_R","for",24);
			val t25_an = word("A","an",25);
			val t26_agreed = word("MR","agreed",26);
			val t27_candidate = word("N","candidate",27);

			// create the vocabulary for the hypothesis;
			val h01_ahern = word("NP_D","Ahern",1);
			val h02_was = word("V_AUX","was",2);
			val h03_traveling = word("V_1","traveling",3);
			val h04_to = word("P_R","to",4);
			val h05_the = word("THE","the",5);
			val h06_japanese = word("MI","Japanese",6);
			val h07_capital = word("N","capital",7);
			val h08_for = word("P_R","for",8);
			val h09_a = word("A","a",9);
			val h10_summit = word("N","summit",10);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_ahern
					,
					_(
						t03_who
						,
						_(
							t04_was
							,
							_(
								_(
									_(
										t05_traveling
										,
										_(
											t06_to
											,
											t07_tokyo
											,
											35
										)
										,
										62
									)
									,
									_(
										t08_for
										,
										_(
											t09_the
											,
											_(
												t10_eujapan
												,
												t11_summit
												,
												60
											)
											,
											61
										)
										,
										33
									)
									,
									63
								)
								,
								t12_yesterday
								,
								74
							)
							,
							38
						)
						,
						40
					)
					,
					64
				)
				,
				_(
					t14_will
					,
					_(
						_(
							t15_consult
							,
							_(
								t66_det
								,
								_(
									t16_other
									,
									_(
										t17_eu
										,
										_(
											t18_leaders
											,
											_(
												t19_by
												,
												_(
													t70_det
													,
													t20_telephone
													,
													71
												)
												,
												50
											)
											,
											54
										)
										,
										69
									)
									,
									67
								)
								,
								68
							)
							,
							56
						)
						,
						_(
							t21_about
							,
							_(
								t22_the
								,
								_(
									t23_decision
									,
									_(
										t24_for
										,
										_(
											t25_an
											,
											_(
												t26_agreed
												,
												t27_candidate
												,
												57
											)
											,
											58
										)
										,
										46
									)
									,
									44
								)
								,
								73
							)
							,
							48
						)
						,
						59
					)
					,
					52
				)
				,
				65
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_ahern
				,
				_(
					h02_was
					,
					_(
						h03_traveling
						,
						_(
							h04_to
							,
							_(
								h05_the
								,
								_(
									h06_japanese
									,
									_(
										h07_capital
										,
										_(
											h08_for
											,
											_(
												h09_a
												,
												h10_summit
												,
												14
											)
											,
											15
										)
										,
										24
									)
									,
									27
								)
								,
								26
							)
							,
							17
						)
						,
						18
					)
					,
					19
				)
				,
				23
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(7,26));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
