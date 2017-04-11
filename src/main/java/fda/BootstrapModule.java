package fda;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class BootstrapModule extends AbstractModule {
	public final String[] args;
	
	public BootstrapModule(String[] args) {
		this.args = args;
	}
	
	@Override
	protected void configure() {
		bind(StreamProvider.class).to(UrlStreamProvider.class);
		bind(FDARecordProvider.class).to(GsonFDARecordProvider.class);
		bind(IngesterService.class).to(ThreadedIngesterService.class);
		
		// TODO pull from configuration file or command line args
		bind(String.class).annotatedWith(Names.named("FDA 510K Url"))
				.toInstance("https://download.open.fda.gov/device/510k/device-510k-0001-of-0001.json.zip");
		bind(String[].class).annotatedWith(Names.named("args")).toInstance(args);
		bind(String.class).annotatedWith(Names.named("Target Table")).toInstance("510k");

	}
}
