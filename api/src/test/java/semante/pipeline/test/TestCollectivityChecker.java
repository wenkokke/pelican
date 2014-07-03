package semante.pipeline.test;

import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTest;

public final class TestCollectivityChecker extends AbsPipelineTest {
	
	@Test
	public final void johnAndMaryAreTheDoctors() throws Exception {
		
		// create the vocabulary for the text:
		val john    = word("NP","john",1);
		val and     = word("AND","and",2);
		val mary    = word("NP","mary",3);
		val are     = word("IS","are",4);
		val the     = word("THE","the",5);
		val doctors = word("N","doctors",6);
		
		// create the vocabulary for the hypothesis:
		val hjohn    = word("NP","john",1);
		val his      = word("IS","is",2);
		val hthe     = word("THE","the",3);
		val hdoctors = word("N","doctors",4);

		// create the tree structure for the text;
		val tt = _(_(john,_(and,mary,8),9),_(are,_(the,doctors,10),11),12);
		
		// create the tree structure for the hypothesis;
		val th = _(hjohn,_(his,_(hthe,hdoctors,5),6),7);
		
		assertException(tt,th);
	}
	
	@Test
	public final void johnAndMaryWhoAreTheDoctorsRan() throws Exception {
		
		// create the vocabulary for the text:
		val john    = word("NP","john",1);
		val and     = word("AND","and",2);
		val mary    = word("NP","mary",3);
		val who     = word("WHO_A","who",4);
		val are     = word("IS","are",5);
		val the     = word("THE","the",6);
		val doctors = word("N","doctors",7);
		val ran     = word("V_1","ran",8);

		// create the vocabulary for the hypothesis:		
		val hjohn    = word("NP","john",1);
		val his      = word("IS","is",2);
		val hthe     = word("THE","the",3);
		val hdoctors = word("N","doctors",4);

		// create the tree structure for the text;
		val tt = _(_(_(john,_(and,mary,10),11),_(who,_(are,_(the,doctors,12),13),14),15),ran,16);
		
		// create the tree structure for the hypothesis;
		val th = _(hjohn,_(his,_(hthe,hdoctors,5),6),7);
		
		assertException(tt,th);
	}
}
