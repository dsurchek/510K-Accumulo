package fda;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.name.Named;


public class UrlStreamProvider implements StreamProvider {

	private static final Logger log = Logger.getLogger(Ingester.class);

	private final String url;
	
	@Inject
	UrlStreamProvider(@Named("FDA 510K Url") String url) {
		this.url = url;
	}
	
	public InputStream getStream() {
		try {
			log.info("Downloading FDA 510K dat aset");
			String text = IOUtils.toString(new URL(url), Charset.forName("UTF-8"));
			ZipInputStream zipStream = new ZipInputStream((new URL(url)).openStream());
			ZipEntry zipEntry = zipStream.getNextEntry();
			return zipStream;
		} catch (IOException e) {
			log.error("Cannot download FDA 510K data set", e);
			return null;
		}
	}

}
