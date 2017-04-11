package fda;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.inject.Inject;

public class GsonFDARecordProvider implements FDARecordProvider {

	private final StreamProvider streamProvider;
	private static final Logger log = Logger.getLogger(GsonFDARecordProvider.class);
	private Gson gson = new GsonBuilder().create();
	private boolean initialized = false;
	private JsonReader reader;

	@Inject
	GsonFDARecordProvider(StreamProvider streamProvider) {
		this.streamProvider = streamProvider;
	}

	@Override
	public boolean hasNext() {
		
		try {
			return init() ? reader.hasNext() : false;
		} catch (IOException e) {
			log.error("Cannot get record from JSON", e);
			return false;
		}
	}
	
	@Override
	public FDARecord getRecord() {

		init();
		
		try {
			if(reader.hasNext()) {
				FDARecord record = gson.fromJson(reader, FDARecord.class);
				log.trace(gson.toJson(record));
				return record;
			}
			else {
				reader.close();
				return null;
			}
		} catch (JsonIOException | JsonSyntaxException | IOException e) {
			log.error("Cannot get record from JSON", e);
			return null;
		}
	}
	

	private boolean init()  {
		if(initialized) return true;
		
		// index through meta context to 510K record array
		try {
			InputStream stream = streamProvider.getStream();
			reader = new JsonReader(new InputStreamReader(stream, "UTF-8"));

			reader.beginObject();
			while (reader.hasNext()) {
				JsonToken tok = reader.peek();

				if (tok == JsonToken.NAME && "results".equals(reader.nextName()))
					break;
				else {
					log.debug("Skipping " + tok);
					reader.skipValue();
				}
			}
			
			reader.beginArray();
			initialized = true;
			return true;
		}
		catch(UnsupportedEncodingException e) {
			log.error("Cannot unmarshall JSON stream", e);
		} catch (IOException e) {
			log.error("Cannot unmarshall JSON stream", e);
		}		
		
		return false;
	}

}
