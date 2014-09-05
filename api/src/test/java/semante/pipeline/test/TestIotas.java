package semante.pipeline.test;

import static semante.pipeline.ResultType.NoProof;
import static semante.pipeline.ResultType.Proof;
import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTest;

public final class TestIotas extends AbsPipelineTest<Integer> {

	@Test
	public final void TestCase01() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_kissed = word("V_2","kissed",2);
		val t02_the = word("THE","the",3);
		val t03_tall = word("MR","tall",4);
		val t04_boy = word("N","boy",5);

		// create the vocabulary for the hypothesis;
		val h00_john = word("NP_D","John",1);
		val h01_kissed = word("V_2","kissed",2);
		val h02_the = word("THE","the",3);
		val h03_short = word("MR","short",4);
		val h04_boy = word("N","boy",5);

		// create the tree structure for the text;
		val tt =
		_(
			t00_john
			,
			_(
				t01_kissed
				,
				_(
					t02_the
					,
					_(
						t03_tall
						,
						t04_boy
					,6)
				,7)
			,8)
		,9)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_john
			,
			_(
				h01_kissed
				,
				_(
					h02_the
					,
					_(
						h03_short
						,
						h04_boy
					,6)
				,7)
			,8)
		,9)
		;

		// test for a proof;
		assertNoProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, NoProof);
	}

	@Test
	public final void TestCase02() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_kissed = word("V_2","kissed",2);
		val t02_the = word("THE","the",3);
		val t03_tall = word("MR","tall",4);
		val t04_boy = word("N","boy",5);

		// create the vocabulary for the hypothesis;
		val h00_john = word("NP_D","John",1);
		val h01_kissed = word("V_2","kissed",2);
		val h02_the = word("THE","the",3);
		val h03_boy = word("N","boy",4);

		// create the tree structure for the text;
		val tt =
		_(
			t00_john
			,
			_(
				t01_kissed
				,
				_(
					t02_the
					,
					_(
						t03_tall
						,
						t04_boy
					,6)
				,7)
			,8)
		,9)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_john
			,
			_(
				h01_kissed
				,
				_(
					h02_the
					,
					h03_boy
				,5)
			,6)
		,7)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}
	
	
	
	
	@Test
	public final void TestCase03() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_kissed = word("V_2","kissed",2);
		val t02_the = word("THE","the",3);
		val t03_tall = word("MR","tall",4);
		val t04_boy = word("N","boy",5);

		// create the vocabulary for the hypothesis;
		val h00_john = word("NP_D","John",1);
		val h01_kissed = word("V_2","kissed",2);
		val h02_a = word("A","a",3);
		val h03_tall = word("MR","tall",4);
		val h04_boy = word("N","boy",5);

		// create the tree structure for the text;
		val tt =
		_(
			t00_john
			,
			_(
				t01_kissed
				,
				_(
					t02_the
					,
					_(
						t03_tall
						,
						t04_boy
					,6)
				,7)
			,8)
		,9)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_john
			,
			_(
				h01_kissed
				,
				_(
					h02_a
					,
					_(
						h03_tall
						,
						h04_boy
					,6)
				,7)
			,8)
		,9)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}

	
	@Test
	public final void TestCase04() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_kissed = word("V_2","kissed",2);
		val t02_a = word("A","a",3);
		val t03_tall = word("MR","tall",4);
		val t04_boy = word("N","boy",5);

		// create the vocabulary for the hypothesis;
		val h00_john = word("NP_D","John",1);
		val h01_kissed = word("V_2","kissed",2);
		val h02_the = word("THE","the",3);
		val h03_tall = word("MR","tall",4);
		val h04_boy = word("N","boy",5);

		// create the tree structure for the text;
		val tt =
		_(
			t00_john
			,
			_(
				t01_kissed
				,
				_(
					t02_a
					,
					_(
						t03_tall
						,
						t04_boy
					,6)
				,7)
			,8)
		,9)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_john
			,
			_(
				h01_kissed
				,
				_(
					h02_the
					,
					_(
						h03_tall
						,
						h04_boy
					,6)
				,7)
			,8)
		,9)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}

	@Test
	public final void TestCase05() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_kissed = word("V_2","kissed",2);
		val t02_a = word("A","a",3);
		val t03_tall = word("MR","tall",4);
		val t04_boy = word("N","boy",5);

		// create the vocabulary for the hypothesis;
		val h00_john = word("NP_D","John",1);
		val h01_kissed = word("V_2","kissed",2);
		val h02_the = word("THE","the",3);
		val h03_boy = word("N","boy",4);

		// create the tree structure for the text;
		val tt =
		_(
			t00_john
			,
			_(
				t01_kissed
				,
				_(
					t02_a
					,
					_(
						t03_tall
						,
						t04_boy
					,6)
				,7)
			,8)
		,9)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_john
			,
			_(
				h01_kissed
				,
				_(
					h02_the
					,
					h03_boy
				,5)
			,6)
		,7)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}
	
	@Test
	public final void TestCase06() throws Exception {

		// create the vocabulary for the text;
		val t00_the = word("THE","The",1);
		val t01_tall = word("MR","tall",2);
		val t02_boy = word("N","boy",3);
		val t03_app = word("WHO_A","APP",4);
		val t04_john = word("NP_D","John",5);
		val t05_smiled = word("V_1","smiled",6);

		// create the vocabulary for the hypothesis;
		val h00_the = word("THE","The",1);
		val h01_short = word("MR","short",2);
		val h02_boy = word("N","boy",3);
		val h03_app = word("WHO_A","APP",4);
		val h04_john = word("NP_D","John",5);
		val h05_smiled = word("V_1","smiled",6);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				_(
					t00_the
					,
					_(
						t01_tall
						,
						t02_boy
					,7)
				,8)
				,
				_(
					t03_app
					,
					t04_john
				,9)
			,10)
			,
			t05_smiled
		,11)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			_(
				_(
					h00_the
					,
					_(
						h01_short
						,
						h02_boy
					,7)
				,8)
				,
				_(
					h03_app
					,
					h04_john
				,9)
			,10)
			,
			h05_smiled
		,11)
		;
		// test for a proof;
		assertNoProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, NoProof);
	}


	@Test
	public final void TestCase07() throws Exception {

		// create the vocabulary for the text;
		val t00_the = word("THE","The",1);
		val t01_tall = word("MR","tall",2);
		val t02_boy = word("N","boy",3);
		val t03_app = word("WHO_A","APP",4);
		val t04_john = word("NP_D","John",5);
		val t05_smiled = word("V_1","smiled",6);

		// create the vocabulary for the hypothesis;
		val h00_the = word("THE","The",1);
		val h01_boy = word("N","boy",2);
		val h02_app = word("WHO_A","APP",3);
		val h03_john = word("NP_D","John",4);
		val h04_smiled = word("V_1","smiled",5);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				_(
					t00_the
					,
					_(
						t01_tall
						,
						t02_boy
					,7)
				,8)
				,
				_(
					t03_app
					,
					t04_john
				,9)
			,10)
			,
			t05_smiled
		,11)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			_(
				_(
					h00_the
					,
					h01_boy
				,6)
				,
				_(
					h02_app
					,
					h03_john
				,7)
			,8)
			,
			h04_smiled
		,9)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}
	
	@Test
	public final void TestCase08() throws Exception {

		// create the vocabulary for the text;
		val t00_the = word("THE","The",1);
		val t01_tall = word("MR","tall",2);
		val t02_boy = word("N","boy",3);
		val t03_app = word("WHO_A","APP",4);
		val t04_john = word("NP_D","John",5);
		val t05_smiled = word("V_1","smiled",6);

		// create the vocabulary for the hypothesis;
		val h00_a = word("A","A",1);
		val h01_tall = word("MR","tall",2);
		val h02_boy = word("N","boy",3);
		val h03_app = word("WHO_A","APP",4);
		val h04_john = word("NP_D","John",5);
		val h05_smiled = word("V_1","smiled",6);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				_(
					t00_the
					,
					_(
						t01_tall
						,
						t02_boy
					,7)
				,8)
				,
				_(
					t03_app
					,
					t04_john
				,9)
			,10)
			,
			t05_smiled
		,11)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			_(
				_(
					h00_a
					,
					_(
						h01_tall
						,
						h02_boy
					,7)
				,8)
				,
				_(
					h03_app
					,
					h04_john
				,9)
			,10)
			,
			h05_smiled
		,11)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}

	@Test
	public final void TestCase09() throws Exception {

		// create the vocabulary for the text;
		val t00_a = word("A","A",1);
		val t01_tall = word("MR","tall",2);
		val t02_boy = word("N","boy",3);
		val t03_app = word("WHO_A","APP",4);
		val t04_john = word("NP_D","John",5);
		val t05_smiled = word("V_1","smiled",6);

		// create the vocabulary for the hypothesis;
		val h00_the = word("THE","The",1);
		val h01_tall = word("MR","tall",2);
		val h02_boy = word("N","boy",3);
		val h03_app = word("WHO_A","APP",4);
		val h04_john = word("NP_D","John",5);
		val h05_smiled = word("V_1","smiled",6);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				_(
					t00_a
					,
					_(
						t01_tall
						,
						t02_boy
					,7)
				,8)
				,
				_(
					t03_app
					,
					t04_john
				,9)
			,10)
			,
			t05_smiled
		,11)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			_(
				_(
					h00_the
					,
					_(
						h01_tall
						,
						h02_boy
					,7)
				,8)
				,
				_(
					h03_app
					,
					h04_john
				,9)
			,10)
			,
			h05_smiled
		,11)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}

	@Test
	public final void TestCase10() throws Exception {

		// create the vocabulary for the text;
		val t00_a = word("A","A",1);
		val t01_tall = word("MR","tall",2);
		val t02_boy = word("N","boy",3);
		val t03_app = word("WHO_A","APP",4);
		val t04_john = word("NP_D","John",5);
		val t05_smiled = word("V_1","smiled",6);

		// create the vocabulary for the hypothesis;
		val h00_the = word("THE","The",1);
		val h01_boy = word("N","boy",2);
		val h02_app = word("WHO_A","APP",3);
		val h03_john = word("NP_D","John",4);
		val h04_smiled = word("V_1","smiled",5);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				_(
					t00_a
					,
					_(
						t01_tall
						,
						t02_boy
					,7)
				,8)
				,
				_(
					t03_app
					,
					t04_john
				,9)
			,10)
			,
			t05_smiled
		,11)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			_(
				_(
					h00_the
					,
					h01_boy
				,6)
				,
				_(
					h02_app
					,
					h03_john
				,7)
			,8)
			,
			h04_smiled
		,9)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}

	@Test
	public final void TestCase11() throws Exception {

		// create the vocabulary for the text;
		val t00_bowie = word("NP_D","Bowie",1);
		val t01_has = word("V_2","has",2);
		val t02_a = word("A","a",3);
		val t03_new = word("MR","new",4);
		val t04_song = word("N","song",5);

		// create the vocabulary for the hypothesis;
		val h00_bowie = word("NP_D","Bowie",1);
		val h01_who = word("WHO_A","who",2);
		val h02_wrote = word("V_1","wrote",3);
		val h03_with = word("P_R","with",4);
		val h04_lou = word("NP_D","Lou",5);
		val h05_has = word("V_2","has",6);
		val h06_a = word("A","a",7);
		val h07_new = word("MR","new",8);
		val h08_song = word("N","song",9);

		// create the tree structure for the text;
		val tt =
		_(
			t00_bowie
			,
			_(
				t01_has
				,
				_(
					t02_a
					,
					_(
						t03_new
						,
						t04_song
					,6)
				,7)
			,8)
		,9)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			_(
				h00_bowie
				,
				_(
					h01_who
					,
					_(
						h02_wrote
						,
						_(
							h03_with
							,
							h04_lou
						,10)
					,11)
				,12)
			,13)
			,
			_(
				h05_has
				,
				_(
					h06_a
					,
					_(
						h07_new
						,
						h08_song
					,14)
				,15)
			,16)
		,17)
		;

		// test for a proof;
		assertNoProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, NoProof);
	}
	
	@Test
	public final void TestCase12() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_is = word("IS","is",2);
		val t02_the = word("THE","the",3);
		val t03_man = word("N","man",4);
		val t04_who = word("WHO_R","who",5);
		val t05_kissed = word("V_2","kissed",6);
		val t06_sue = word("NP_D","Sue",7);
		val t07_and = word("AND","and",8);
		val t08_mary = word("NP_D","Mary",9);
		val t09_talked = word("V_1","talked",10);
		val t10_to = word("P_R","to",11);
		val t11_a = word("A","a",12);
		val t12_man = word("N","man",13);

		// create the vocabulary for the hypothesis;
		val h00_mary = word("NP_D","Mary",1);
		val h01_talked = word("V_1","talked",2);
		val h02_to = word("P_R","to",3);
		val h03_the = word("THE","the",4);
		val h04_man = word("N","man",5);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				t00_john
				,
				_(
					t01_is
					,
					_(
						t02_the
						,
						_(
							t03_man
							,
							_(
								t04_who
								,
								_(
									t05_kissed
									,
									t06_sue
								,14)
							,15)
						,16)
					,17)
				,18)
			,19)
			,
			_(
				t07_and
				,
				_(
					t08_mary
					,
					_(
						t09_talked
						,
						_(
							t10_to
							,
							_(
								t11_a
								,
								t12_man
							,20)
						,21)
					,22)
				,23)
			,24)
		,25)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_mary
			,
			_(
				h01_talked
				,
				_(
					h02_to
					,
					_(
						h03_the
						,
						h04_man
					,6)
				,7)
			,8)
		,9)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}

	@Test
	public final void TestCase13() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_is = word("IS","is",2);
		val t02_the = word("THE","the",3);
		val t03_man = word("N","man",4);
		val t04_who = word("WHO_R","who",5);
		val t05_kissed = word("V_2","kissed",6);
		val t06_sue = word("NP_D","Sue",7);
		val t07_and = word("AND","and",8);
		val t08_mary = word("NP_D","Mary",9);
		val t09_talked = word("V_1","talked",10);
		val t10_to = word("P_R","to",11);
		val t11_a = word("A","a",12);
		val t12_man = word("N","man",13);

		// create the vocabulary for the hypothesis;
		val h00_mary = word("NP_D","Mary",1);
		val h01_talked = word("V_1","talked",2);
		val h02_to = word("P_R","to",3);
		val h03_john = word("NP_D","John",4);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				t00_john
				,
				_(
					t01_is
					,
					_(
						t02_the
						,
						_(
							t03_man
							,
							_(
								t04_who
								,
								_(
									t05_kissed
									,
									t06_sue
								,14)
							,15)
						,16)
					,17)
				,18)
			,19)
			,
			_(
				t07_and
				,
				_(
					t08_mary
					,
					_(
						t09_talked
						,
						_(
							t10_to
							,
							_(
								t11_a
								,
								t12_man
							,20)
						,21)
					,22)
				,23)
			,24)
		,25)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_mary
			,
			_(
				h01_talked
				,
				_(
					h02_to
					,
					h03_john
				,5)
			,6)
		,7)
		;
		// test for a proof;
		assertNoProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, NoProof);
	}
	
	@Test
	public final void TestCase14() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_is = word("IS","is",2);
		val t02_the = word("THE","the",3);
		val t03_man = word("N","man",4);
		val t04_who = word("WHO_R","who",5);
		val t05_kissed = word("V_2","kissed",6);
		val t06_sue = word("NP_D","Sue",7);
		val t07_and = word("AND","and",8);
		val t08_mary = word("NP_D","Mary",9);
		val t09_talked = word("V_1","talked",10);
		val t10_to = word("P_R","to",11);
		val t11_a = word("A","a",12);
		val t12_man = word("N","man",13);
		val t13_who = word("WHO_R","who",14);
		val t14_kissed = word("V_2","kissed",15);
		val t15_sue = word("NP_D","Sue",16);

		// create the vocabulary for the hypothesis;
		val h00_mary = word("NP_D","Mary",1);
		val h01_talked = word("V_1","talked",2);
		val h02_to = word("P_R","to",3);
		val h03_the = word("THE","the",4);
		val h04_man = word("N","man",5);
		val h05_who = word("WHO_R","who",6);
		val h06_kissed = word("V_2","kissed",7);
		val h07_sue = word("NP_D","Sue",8);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				t00_john
				,
				_(
					t01_is
					,
					_(
						t02_the
						,
						_(
							t03_man
							,
							_(
								t04_who
								,
								_(
									t05_kissed
									,
									t06_sue
								,17)
							,18)
						,19)
					,20)
				,21)
			,22)
			,
			_(
				t07_and
				,
				_(
					t08_mary
					,
					_(
						t09_talked
						,
						_(
							t10_to
							,
							_(
								t11_a
								,
								_(
									t12_man
									,
									_(
										t13_who
										,
										_(
											t14_kissed
											,
											t15_sue
										,23)
									,24)
								,25)
							,26)
						,27)
					,28)
				,29)
			,30)
		,31)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_mary
			,
			_(
				h01_talked
				,
				_(
					h02_to
					,
					_(
						h03_the
						,
						_(
							h04_man
							,
							_(
								h05_who
								,
								_(
									h06_kissed
									,
									h07_sue
								,9)
							,10)
						,11)
					,12)
				,13)
			,14)
		,15)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}

	
	@Test
	public final void TestCase15() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_is = word("IS","is",2);
		val t02_the = word("THE","the",3);
		val t03_man = word("N","man",4);
		val t04_who = word("WHO_R","who",5);
		val t05_kissed = word("V_2","kissed",6);
		val t06_sue = word("NP_D","Sue",7);
		val t07_and = word("AND","and",8);
		val t08_mary = word("NP_D","Mary",9);
		val t09_talked = word("V_1","talked",10);
		val t10_to = word("P_R","to",11);
		val t11_a = word("A","a",12);
		val t12_man = word("N","man",13);
		val t13_who = word("WHO_R","who",14);
		val t14_kissed = word("V_2","kissed",15);
		val t15_sue = word("NP_D","Sue",16);

		// create the vocabulary for the hypothesis;
		val h00_mary = word("NP_D","Mary",1);
		val h01_talked = word("V_1","talked",2);
		val h02_to = word("P_R","to",3);
		val h03_john = word("NP_D","John",4);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				t00_john
				,
				_(
					t01_is
					,
					_(
						t02_the
						,
						_(
							t03_man
							,
							_(
								t04_who
								,
								_(
									t05_kissed
									,
									t06_sue
								,17)
							,18)
						,19)
					,20)
				,21)
			,22)
			,
			_(
				t07_and
				,
				_(
					t08_mary
					,
					_(
						t09_talked
						,
						_(
							t10_to
							,
							_(
								t11_a
								,
								_(
									t12_man
									,
									_(
										t13_who
										,
										_(
											t14_kissed
											,
											t15_sue
										,23)
									,24)
								,25)
							,26)
						,27)
					,28)
				,29)
			,30)
		,31)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_mary
			,
			_(
				h01_talked
				,
				_(
					h02_to
					,
					h03_john
				,5)
			,6)
		,7)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}

	@Test
	public final void TestCase16() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_is = word("IS","is",2);
		val t02_the = word("THE","the",3);
		val t03_man = word("N","man",4);
		val t04_who = word("WHO_R","who",5);
		val t05_kissed = word("V_2","kissed",6);
		val t06_sue = word("NP_D","Sue",7);
		val t07_and = word("AND","and",8);
		val t08_mary = word("NP_D","Mary",9);
		val t09_talked = word("V_1","talked",10);
		val t10_to = word("P_R","to",11);
		val t11_a = word("A","a",12);
		val t12_man = word("N","man",13);
		val t13_who = word("WHO_R","who",14);
		val t14_kissed = word("V_2","kissed",15);
		val t15_sue = word("NP_D","Sue",16);

		// create the vocabulary for the hypothesis;
		val h00_mary = word("NP_D","Mary",1);
		val h01_talked = word("V_1","talked",2);
		val h02_to = word("P_R","to",3);
		val h03_the = word("THE","the",4);
		val h04_man = word("N","man",5);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				t00_john
				,
				_(
					t01_is
					,
					_(
						t02_the
						,
						_(
							t03_man
							,
							_(
								t04_who
								,
								_(
									t05_kissed
									,
									t06_sue
								,17)
							,18)
						,19)
					,20)
				,21)
			,22)
			,
			_(
				t07_and
				,
				_(
					t08_mary
					,
					_(
						t09_talked
						,
						_(
							t10_to
							,
							_(
								t11_a
								,
								_(
									t12_man
									,
									_(
										t13_who
										,
										_(
											t14_kissed
											,
											t15_sue
										,23)
									,24)
								,25)
							,26)
						,27)
					,28)
				,29)
			,30)
		,31)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_mary
			,
			_(
				h01_talked
				,
				_(
					h02_to
					,
					_(
						h03_the
						,
						h04_man
					,6)
				,7)
			,8)
		,9)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}
	
	@Test
	public final void TestCase17() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_is = word("IS","is",2);
		val t02_the = word("THE","the",3);
		val t03_man = word("N","man",4);
		val t04_who = word("WHO_R","who",5);
		val t05_kissed = word("V_2","kissed",6);
		val t06_sue = word("NP_D","Sue",7);
		val t07_and = word("AND","and",8);
		val t08_mary = word("NP_D","Mary",9);
		val t09_talked = word("V_1","talked",10);
		val t10_to = word("P_R","to",11);
		val t11_the = word("THE","the",12);
		val t12_man = word("N","man",13);

		// create the vocabulary for the hypothesis;
		val h00_mary = word("NP_D","Mary",1);
		val h01_talked = word("V_1","talked",2);
		val h02_to = word("P_R","to",3);
		val h03_the = word("THE","the",4);
		val h04_man = word("N","man",5);
		val h05_who = word("WHO_R","who",6);
		val h06_kissed = word("V_2","kissed",7);
		val h07_sue = word("NP_D","Sue",8);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				t00_john
				,
				_(
					t01_is
					,
					_(
						t02_the
						,
						_(
							t03_man
							,
							_(
								t04_who
								,
								_(
									t05_kissed
									,
									t06_sue
								,14)
							,15)
						,16)
					,17)
				,18)
			,19)
			,
			_(
				t07_and
				,
				_(
					t08_mary
					,
					_(
						t09_talked
						,
						_(
							t10_to
							,
							_(
								t11_the
								,
								t12_man
							,20)
						,21)
					,22)
				,23)
			,24)
		,25)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_mary
			,
			_(
				h01_talked
				,
				_(
					h02_to
					,
					_(
						h03_the
						,
						_(
							h04_man
							,
							_(
								h05_who
								,
								_(
									h06_kissed
									,
									h07_sue
								,9)
							,10)
						,11)
					,12)
				,13)
			,14)
		,15)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}
	
	@Test
	public final void TestCase18() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_is = word("IS","is",2);
		val t02_the = word("THE","the",3);
		val t03_man = word("N","man",4);
		val t04_who = word("WHO_R","who",5);
		val t05_kissed = word("V_2","kissed",6);
		val t06_sue = word("NP_D","Sue",7);
		val t07_and = word("AND","and",8);
		val t08_mary = word("NP_D","Mary",9);
		val t09_talked = word("V_1","talked",10);
		val t10_to = word("P_R","to",11);
		val t11_the = word("THE","the",12);
		val t12_man = word("N","man",13);

		// create the vocabulary for the hypothesis;
		val h00_mary = word("NP_D","Mary",1);
		val h01_talked = word("V_1","talked",2);
		val h02_to = word("P_R","to",3);
		val h03_john = word("NP_D","John",4);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				t00_john
				,
				_(
					t01_is
					,
					_(
						t02_the
						,
						_(
							t03_man
							,
							_(
								t04_who
								,
								_(
									t05_kissed
									,
									t06_sue
								,14)
							,15)
						,16)
					,17)
				,18)
			,19)
			,
			_(
				t07_and
				,
				_(
					t08_mary
					,
					_(
						t09_talked
						,
						_(
							t10_to
							,
							_(
								t11_the
								,
								t12_man
							,20)
						,21)
					,22)
				,23)
			,24)
		,25)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_mary
			,
			_(
				h01_talked
				,
				_(
					h02_to
					,
					h03_john
				,5)
			,6)
		,7)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}

	@Test
	public final void TestCase19() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_is = word("IS","is",2);
		val t02_the = word("THE","the",3);
		val t03_man = word("N","man",4);
		val t04_who = word("WHO_R","who",5);
		val t05_kissed = word("V_2","kissed",6);
		val t06_sue = word("NP_D","Sue",7);
		val t07_and = word("AND","and",8);
		val t08_mary = word("NP_D","Mary",9);
		val t09_talked = word("V_1","talked",10);
		val t10_to = word("P_R","to",11);
		val t11_the = word("THE","the",12);
		val t12_man = word("N","man",13);

		// create the vocabulary for the hypothesis;
		val h00_mary = word("NP_D","Mary",1);
		val h01_talked = word("V_1","talked",2);
		val h02_to = word("P_R","to",3);
		val h03_the = word("THE","the",4);
		val h04_man = word("N","man",5);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				t00_john
				,
				_(
					t01_is
					,
					_(
						t02_the
						,
						_(
							t03_man
							,
							_(
								t04_who
								,
								_(
									t05_kissed
									,
									t06_sue
								,14)
							,15)
						,16)
					,17)
				,18)
			,19)
			,
			_(
				t07_and
				,
				_(
					t08_mary
					,
					_(
						t09_talked
						,
						_(
							t10_to
							,
							_(
								t11_the
								,
								t12_man
							,20)
						,21)
					,22)
				,23)
			,24)
		,25)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_mary
			,
			_(
				h01_talked
				,
				_(
					h02_to
					,
					_(
						h03_the
						,
						h04_man
					,6)
				,7)
			,8)
		,9)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}

	
	@Test
	public final void TestCase20() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_kissed = word("V_2","kissed",2);
		val t02_the = word("THE","the",3);
		val t03_girl = word("N","girl",4);
		val t04_who = word("WHO_R","who",5);
		val t05_loves = word("V_2","loves",6);
		val t06_the = word("THE","the",7);
		val t07_boy = word("N","boy",8);
		val t08_and = word("AND","and",9);
		val t09_bill = word("NP_D","Bill",10);
		val t10_is = word("IS","is",11);
		val t11_a = word("A","a",12);
		val t12_boy = word("N","boy",13);
		val t13_and = word("AND","and",14);
		val t14_mary = word("NP_D","Mary",15);
		val t15_loves = word("V_2","loves",16);
		val t16_bill = word("NP_D","Bill",17);

		// create the vocabulary for the hypothesis;
		val h00_john = word("NP_D","John",1);
		val h01_kissed = word("V_2","kissed",2);
		val h02_the = word("THE","the",3);
		val h03_girl = word("N","girl",4);
		val h04_who = word("WHO_R","who",5);
		val h05_loves = word("V_2","loves",6);
		val h06_bill = word("NP_D","Bill",7);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				_(
					t00_john
					,
					_(
						t01_kissed
						,
						_(
							t02_the
							,
							_(
								t03_girl
								,
								_(
									t04_who
									,
									_(
										t05_loves
										,
										_(
											t06_the
											,
											t07_boy
										,18)
									,19)
								,20)
							,21)
						,22)
					,23)
				,24)
				,
				_(
					t08_and
					,
					_(
						t09_bill
						,
						_(
							t10_is
							,
							_(
								t11_a
								,
								t12_boy
							,25)
						,26)
					,27)
				,28)
			,29)
			,
			_(
				t13_and
				,
				_(
					t14_mary
					,
					_(
						t15_loves
						,
						t16_bill
					,30)
				,31)
			,32)
		,33)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_john
			,
			_(
				h01_kissed
				,
				_(
					h02_the
					,
					_(
						h03_girl
						,
						_(
							h04_who
							,
							_(
								h05_loves
								,
								h06_bill
							,8)
						,9)
					,10)
				,11)
			,12)
		,13)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}
	
	@Test
	public final void TestCase21() throws Exception {

		// create the vocabulary for the text;
		val t00_john = word("NP_D","John",1);
		val t01_kissed = word("V_2","kissed",2);
		val t02_the = word("THE","the",3);
		val t03_girl = word("N","girl",4);
		val t04_who = word("WHO_R","who",5);
		val t05_loves = word("V_2","loves",6);
		val t06_the = word("THE","the",7);
		val t07_boy = word("N","boy",8);
		val t08_and = word("AND","and",9);
		val t09_mary = word("NP_D","Mary",10);
		val t10_is = word("IS","is",11);
		val t11_a = word("A","a",12);
		val t12_girl = word("N","girl",13);
		val t13_and = word("AND","and",14);
		val t14_bill = word("NP_D","Bill",15);
		val t15_is = word("IS","is",16);
		val t16_a = word("A","a",17);
		val t17_boy = word("N","boy",18);
		val t18_and = word("AND","and",19);
		val t19_mary = word("NP_D","Mary",20);
		val t20_loves = word("V_2","loves",21);
		val t21_bill = word("NP_D","Bill",22);

		// create the vocabulary for the hypothesis;
		val h00_john = word("NP_D","John",1);
		val h01_kissed = word("V_2","kissed",2);
		val h02_mary = word("NP_D","Mary",3);

		// create the tree structure for the text;
		val tt =
		_(
			_(
				_(
					_(
						t00_john
						,
						_(
							t01_kissed
							,
							_(
								t02_the
								,
								_(
									t03_girl
									,
									_(
										t04_who
										,
										_(
											t05_loves
											,
											_(
												t06_the
												,
												t07_boy
											,23)
										,24)
									,25)
								,26)
							,27)
						,28)
					,29)
					,
					_(
						t08_and
						,
						_(
							t09_mary
							,
							_(
								t10_is
								,
								_(
									t11_a
									,
									t12_girl
								,30)
							,31)
						,32)
					,33)
				,34)
				,
				_(
					t13_and
					,
					_(
						t14_bill
						,
						_(
							t15_is
							,
							_(
								t16_a
								,
								t17_boy
							,35)
						,36)
					,37)
				,38)
			,39)
			,
			_(
				t18_and
				,
				_(
					t19_mary
					,
					_(
						t20_loves
						,
						t21_bill
					,40)
				,41)
			,42)
		,43)
		;

		// create the tree structure for the hypothesis;
		val th =
		_(
			h00_john
			,
			_(
				h01_kissed
				,
				h02_mary
			,4)
		,5)
		;

		// test for a proof;
		assertProof(tt, th);
		// test the testcasecreator;
		testTestCaseCreator(tt, th, Proof);
	}
	
}
