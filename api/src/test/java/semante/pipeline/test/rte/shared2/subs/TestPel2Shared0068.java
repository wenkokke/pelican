package semante.pipeline.test.rte.shared2.subs;

import lombok.val;
import org.junit.Test;
import semante.pipeline.AbsPipelineTest;
import static semante.pipeline.ResultType.*;
import java.util.List;
import com.google.common.collect.Lists;
import semante.pipeline.Pair;
import semante.pipeline.impl.IPair;

public final class TestPel2Shared0068 extends AbsPipelineTest {

		@Test
		public final void testPel2Shared0068() throws Exception {

			// create the vocabulary for the text;
			val t33_det = word("THE","DET",33);
			val t01_american = word("MI","American",1);
			val t02_company = word("N","company",2);
			val t35_app = word("WHO_A","APP",35);
			val t03_walmart = word("NP_D","WalMart",3);
			val t37_app = word("WHO_A","APP",37);
			val t05_the = word("THE","the",5);
			val t06_discount = word("$NC_1$","discount",6);
			val t07_department = word("$NC_1$","department",7);
			val t08_store = word("N","store",8);
			val t10_faces = word("V_2","faces",10);
			val t11_an = word("A","an",11);
			val t12_allegation = word("N","allegation",12);
			val t13_for = word("P_R","for",13);
			val t41_det = word("EMPTYDET","DET",41);
			val t14_underpayed = word("MR","underpayed",14);
			val t15_employees = word("N","employees",15);

			// create the vocabulary for the hypothesis;
			val h01_an = word("A","An",1);
			val h02_american = word("MI","American",2);
			val h03_store = word("N","store",3);
			val h04_faces = word("V_2","faces",4);
			val h05_an = word("A","an",5);
			val h06_allegation = word("N","allegation",6);
			val h07_for = word("P_R","for",7);
			val h21_det = word("EMPTYDET","DET",21);
			val h08_underpayed = word("MR","underpayed",8);
			val h09_employees = word("N","employees",9);

			// create the tree structure for the text;
			val tt =
			_(
				_(
					_(
						_(
							t33_det
							,
							_(
								t01_american
								,
								t02_company
								,
								27
							)
							,
							34
						)
						,
						_(
							t35_app
							,
							t03_walmart
							,
							36
						)
						,
						43
					)
					,
					_(
						t37_app
						,
						_(
							t05_the
							,
							_(
								t06_discount
								,
								_(
									t07_department
									,
									t08_store
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
						38
					)
					,
					39
				)
				,
				_(
					t10_faces
					,
					_(
						t11_an
						,
						_(
							t12_allegation
							,
							_(
								t13_for
								,
								_(
									t41_det
									,
									_(
										t14_underpayed
										,
										t15_employees
										,
										21
									)
									,
									42
								)
								,
								22
							)
							,
							20
						)
						,
						40
					)
					,
					24
				)
				,
				32
			)
			;

			// create the tree structure for the hypothesis;
			val th =
			_(
				_(
					h01_an
					,
					_(
						h02_american
						,
						h03_store
						,
						18
					)
					,
					19
				)
				,
				_(
					h04_faces
					,
					_(
						h05_an
						,
						_(
							h06_allegation
							,
							_(
								h07_for
								,
								_(
									h21_det
									,
									_(
										h08_underpayed
										,
										h09_employees
										,
										13
									)
									,
									22
								)
								,
								14
							)
							,
							12
						)
						,
						23
					)
					,
					16
				)
				,
				20
			)
			;

			// create the subsumption relations;
		List<Pair<Integer,Integer>> subs = Lists.newArrayList();
		subs.add(new IPair<Integer,Integer>(29,3));

			// test for a proof;
			assertProof(tt, th, subs);
			// test the testcasecreator;
			testTestCaseCreator(tt, th, Proof, subs);
		}

}

