package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0096 extends AbsPipelineTest<Integer> {

		@Test
		public final void testPel2Shared0096() throws Exception {

			// create the vocabulary for the text;
			val t22_peter_clarke = word("NP_D","Peter_Clarke",22);
			val t49_app = word("WHO_A","APP",49);
			val t04_the = word("THE","the",4);
			val t05_overworked = word("MR","overworked",5);
			val t06_head = word("N","head",6);
			val t07_of = word("P_R","of",7);
			val t08_the = word("THE","the",8);
			val t45_metropolitan_police_antiterrorist_branch = word("NP_D","Metropolitan_Police_antiTerrorist_branch",45);
			val t14_wanted = word("V_2","wanted",14);
			val t55_det = word("EMPTYDET","DET",55);
			val t15_information = word("N","information",15);
			val t16_about = word("P_R","about",16);
			val t53_det = word("EMPTYDET","DET",53);
			val t17_people = word("N","people",17);
			val t18_who = word("WHO_R","who",18);
			val t19_bought = word("V_2","bought",19);
			val t20_chloroform = word("NP_D","chloroform",20);

			// create the vocabulary for the hypothesis;
			val h01_a = word("A","A",1);
			val h26_police_chief = word("N","police_chief",26);
			val h04_wanted = word("V_2","wanted",4);
			val h29_det = word("EMPTYDET","DET",29);
			val h05_information = word("N","information",5);
			val h06_about = word("P_R","about",6);
			val h32_det = word("EMPTYDET","DET",32);
			val h07_people = word("N","people",7);
			val h08_who = word("WHO_R","who",8);
			val h09_bought = word("V_2","bought",9);
			val h10_chloroform = word("NP_D","chloroform",10);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t22_peter_clarke
					,
					_(
						t49_app
						,
						_(
							t04_the
							,
							_(
								t05_overworked
								,
								_(
									t06_head
									,
									_(
										t07_of
										,
										_(
											t08_the
											,
											t45_metropolitan_police_antiterrorist_branch
											,
											46
										)
										,
										25
									)
									,
									41
								)
								,
								51
							)
							,
							42
						)
						,
						50
					)
					,
					47
				)
				,
				_(
					t14_wanted
					,
					_(
						t55_det
						,
						_(
							t15_information
							,
							_(
								t16_about
								,
								_(
									t53_det
									,
									_(
										t17_people
										,
										_(
											t18_who
											,
											_(
												t19_bought
												,
												t20_chloroform
												,
												32
											)
											,
											34
										)
										,
										54
									)
									,
									58
								)
								,
								36
							)
							,
							56
						)
						,
						57
					)
					,
					39
				)
				,
				48
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_a
					,
					h26_police_chief
					,
					27
				)
				,
				_(
					h04_wanted
					,
					_(
						h29_det
						,
						_(
							h05_information
							,
							_(
								h06_about
								,
								_(
									h32_det
									,
									_(
										h07_people
										,
										_(
											h08_who
											,
											_(
												h09_bought
												,
												h10_chloroform
												,
												17
											)
											,
											19
										)
										,
										33
									)
									,
									34
								)
								,
								21
							)
							,
							30
						)
						,
						31
					)
					,
					24
				)
				,
				28
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(41,26));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
