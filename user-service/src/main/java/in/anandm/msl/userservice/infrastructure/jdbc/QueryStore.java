package in.anandm.msl.userservice.infrastructure.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class QueryStore {

	private List<String> queryFiles;
	private Map<String, String> queryCache = new HashMap<>();

	public QueryStore(List<String> queryFiles) {
		super();

		if (queryFiles == null) {
			queryFiles = Collections.emptyList();
		}

		this.queryFiles = queryFiles;
		buidlQueryCache();
	}

	private static class QueryElementHandler extends DefaultHandler {

		private Map<String, String> queries = new HashMap<>();
		private String queryName;
		private StringBuilder queryBuilder;
		private boolean inQuery = false;

		public QueryElementHandler() {
			super();

		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes)
				throws SAXException {
			if (!qName.equalsIgnoreCase("queries")) {
				// start of a query
				queryName = qName.trim();
				inQuery = true;
				queryBuilder = new StringBuilder();
			}

		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			if (qName.equalsIgnoreCase(queryName)) {
				//end of current query
				queries.put(queryName.trim(), queryBuilder.toString().trim());
				queryName = null;
				queryBuilder = null;
				inQuery = false;
			}

		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			if (inQuery) {
				queryBuilder.append(new String(ch, start, length));
			}
		}

	}

	private void buidlQueryCache() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();

			for (String queryFile : queryFiles) {
				InputStream is = QueryStore.class.getClassLoader().getResourceAsStream(queryFile);
				QueryElementHandler handler = new QueryElementHandler();
				parser.parse(is, handler);
				for (Entry<String, String> entry : handler.queries.entrySet()) {
					queryCache.put(entry.getKey(), entry.getValue());
				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new RuntimeException(e);
		}

	}

	public String getQuery(String queryTag) {
		return queryCache.get(queryTag);
	}
}
