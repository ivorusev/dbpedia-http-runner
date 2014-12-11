package org.semantic;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

/**
 * 
 */

/**
 * Test class for funning a semantic query against DBPedia.
 * 
 * @author Ivo Rusev
 *
 */
public class SemanticTest {

	private static final String DBPEDIA_HTTP_ENDPOINT = "http://dbpedia.org/sparql";

	/**
	 * Runs a semantic query against DBPedia. The query returns first 100 first
	 * person shooter games.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		final String prefix = "PREFIX foaf: <http://xmlns.com/foaf/0.1/> PREFIX categ: <http://dbpedia.org/resource/Category>";

		final String sparqlQueryString = "SELECT ?title "
				+ "WHERE { "
				+ "?game <http://purl.org/dc/terms/subject> "
				+ "<http://dbpedia.org/resource/Category:First-person_shooters> . "
				+ "?game foaf:name ?title ." + "} ORDER by ?title";

		Query query = QueryFactory.create(prefix + sparqlQueryString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(
				DBPEDIA_HTTP_ENDPOINT, query);

		ResultSet results = qexec.execSelect();
		ResultSetFormatter.out(System.out, results, query);

		qexec.close();
	}
}
