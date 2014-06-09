package semante.pipeline.test;

import lombok.val;

import org.junit.Test;

import semante.pipeline.AbsPipelineTest;

public final class TestCollectivityChecker extends AbsPipelineTest {
	
	@Test
	public final void johnAndMaryAreTheDoctors() throws Exception {
		
		val john    = word("NP","john");
		val and     = word("AND","and");
		val mary    = word("NP","mary");
		val are     = word("IS","are");
		val the     = word("THE","the");
		val doctors = word("N","doctors");
		
		val is      = word("IS","is");
		
		val tt = _(_(john,_(and,mary)),_(are,_(the,doctors)));
		val th = _(john,_(is,_(the,doctors)));
		
		assertException(tt,th);
	}
	
	@Test
	public final void johnAndMaryWhoAreTheDoctorsRan() throws Exception {
		
		val john    = word("NP","john");
		val and     = word("AND","and");
		val mary    = word("NP","mary");
		val who     = word("WHO_A","who");
		val are     = word("IS","are");
		val the     = word("THE","the");
		val doctors = word("N","doctors");
		val ran     = word("V_1","ran");
		
		val is      = word("IS","is");
		
		val tt = _(_(_(john,_(and,mary)),_(who,_(are,_(the,doctors)))),ran);
		val th = _(john,_(is,_(the,doctors)));
		
		assertException(tt,th);
	}
	
}
