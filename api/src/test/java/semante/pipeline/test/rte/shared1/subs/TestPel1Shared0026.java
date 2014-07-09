package semante.pipeline.test.rte.shared1.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel1Shared0026 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0026() throws Exception {

			// create the vocabulary for the text;
			val t01_two = word("NUMBER","Two",1);
			val t02_members = word("N","members",2);
			val t03_of = word("P_R","of",3);
			val t04_the = word("THE","the",4);
			val t05_num2000 = word("NUMBER","num2000",5);
			val t06_kanchenjunga = word("MR","Kanchenjunga",6);
			val t07_expedition = word("N","Expedition",7);
			val t08_of = word("P_R","of",8);
			val t27_south_korea = word("NP_D","South_Korea",27);
			val t11_successfully = word("MR","successfully",11);
			val t12_scaled = word("V_2","scaled",12);
			val t13_the = word("THE","the",13);
			val t14_famous = word("MI","famous",14);
			val t41_mt_kanchenjunga = word("NP_D","Mt_Kanchenjunga",41);
			val t17_via = word("P_R","via",17);
			val t18_the = word("THE","the",18);
			val t19_south = word("MR","south",19);
			val t20_west = word("MR","west",20);
			val t21_face = word("N","face",21);
			val t22_on = word("P_R","on",22);
			val t23_friday = word("NP_D","Friday",23);

			// create the vocabulary for the hypothesis;
			val h01_kanchenjunga = word("NP_D","Kanchenjunga",1);
			val h02_is = word("IS","is",2);
			val h03_famous = word("MI","famous",3);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					t01_two
					,
					_(
						t02_members
						,
						_(
							t03_of
							,
							_(
								t04_the
								,
								_(
									t05_num2000
									,
									_(
										t06_kanchenjunga
										,
										_(
											t07_expedition
											,
											_(
												t08_of
												,
												t27_south_korea
												,
												28
											)
											,
											50
										)
										,
										55
									)
									,
									51
								)
								,
								52
							)
							,
							30
						)
						,
						25
					)
					,
					54
				)
				,
				_(
					t11_successfully
					,
					_(
						_(
							_(
								t12_scaled
								,
								_(
									t13_the
									,
									_(
										t14_famous
										,
										t41_mt_kanchenjunga
										,
										42
									)
									,
									43
								)
								,
								47
							)
							,
							_(
								t17_via
								,
								_(
									t18_the
									,
									_(
										t19_south
										,
										_(
											t20_west
											,
											t21_face
											,
											44
										)
										,
										45
									)
									,
									46
								)
								,
								35
							)
							,
							56
						)
						,
						_(
							t22_on
							,
							t23_friday
							,
							38
						)
						,
						48
					)
					,
					49
				)
				,
				53
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h01_kanchenjunga
				,
				_(
					h02_is
					,
					h03_famous
					,
					7
				)
				,
				9
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(41,1));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
