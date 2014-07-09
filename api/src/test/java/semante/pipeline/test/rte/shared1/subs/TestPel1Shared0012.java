package semante.pipeline.test.rte.shared1.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel1Shared0012 extends AbsPipelineTest {

		@Test
		public final void testPel1Shared0012() throws Exception {

			// create the vocabulary for the text;
			val t01_the = word("THE","The",1);
			val t02_head = word("N","head",2);
			val t03_of = word("P_R","of",3);
			val t04_the = word("THE","the",4);
			val t05_italian = word("MR","Italian",5);
			val t06_opposition = word("N","opposition",6);
			val t43_app = word("WHO_A","APP",43);
			val t22_romano_prodi = word("NP_D","Romano_Prodi",22);
			val t11_was = word("IS","was",11);
			val t12_the = word("THE","the",12);
			val t13_last = word("MR","last",13);
			val t14_president = word("N","president",14);
			val t15_of = word("P_R","of",15);
			val t16_the = word("THE","the",16);
			val t17_european = word("MR","European",17);
			val t18_commission = word("N","Commission",18);

			// create the vocabulary for the hypothesis;
			val h12_romano_prodi = word("NP_D","Romano_Prodi",12);
			val h03_is = word("IS","is",3);
			val h04_a = word("A","a",4);
			val h05_former = word("MR","former",5);
			val h06_president = word("N","president",6);
			val h07_of = word("P_R","of",7);
			val h08_the = word("THE","the",8);
			val h09_european = word("MR","European",9);
			val h10_commission = word("N","Commission",10);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						t01_the
						,
						_(
							t02_head
							,
							_(
								t03_of
								,
								_(
									t04_the
									,
									_(
										t05_italian
										,
										t06_opposition
										,
										36
									)
									,
									37
								)
								,
								24
							)
							,
							20
						)
						,
						40
					)
					,
					_(
						t43_app
						,
						t22_romano_prodi
						,
						44
					)
					,
					25
				)
				,
				_(
					t11_was
					,
					_(
						t12_the
						,
						_(
							t13_last
							,
							_(
								t14_president
								,
								_(
									t15_of
									,
									_(
										t16_the
										,
										_(
											t17_european
											,
											t18_commission
											,
											34
										)
										,
										35
									)
									,
									28
								)
								,
								32
							)
							,
							42
						)
						,
						33
					)
					,
					30
				)
				,
				39
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				h12_romano_prodi
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
							_(
								h06_president
								,
								_(
									h07_of
									,
									_(
										h08_the
										,
										_(
											h09_european
											,
											h10_commission
											,
											21
										)
										,
										22
									)
									,
									15
								)
								,
								19
							)
							,
							24
						)
						,
						20
					)
					,
					17
				)
				,
				23
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(13,5));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}
