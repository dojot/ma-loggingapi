package br.com.cpqd.mutualauthentication.loggingapi.dao.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import br.com.cpqd.mutualauthentication.loggingapi.beans.entity.LoggingVO;
import br.com.cpqd.mutualauthentication.loggingapi.dao.api.IndexedLogDAO;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.ConfigService;

@Singleton
public class IndexedLogDAOImpl implements IndexedLogDAO { 

	@EJB
	private ConfigService configService;
	
	int SEARCH_RESULT = 10000;
	
	@Override
	public void save(Long id, String json) {
		RestClient restClient = null;
        try {
        	restClient = RestClient.builder(getHostnamesElasticSearch()).build();
			HttpEntity entity = new NStringEntity(json, ContentType.APPLICATION_JSON);
			restClient.performRequest("PUT", "/logging/log/" + id, Collections.<String, String>emptyMap(), entity);
        } catch (Exception e) {
            System.out.println("Erro ao persistir no ELK: " + e.getMessage());
        } finally {
			if (restClient != null) {
				try {
					restClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
        }
	}
	
    @Override
    public HttpEntity searchLevel(String level) {
        HttpEntity entity = null;
        RestClient restClient = null;
        try {
        	System.out.println("LEVEL: " + level);
			restClient = RestClient.builder(getHostnamesElasticSearch()).build();
			String query = "{\"query\" : {\"match\" : {\"level\" : \"" + level + "\"}}}";
            Response response = restClient.performRequest("GET", "/logging/_search?size=" + SEARCH_RESULT, new Hashtable<>(), new StringEntity(query));
            entity = response.getEntity();
            System.out.println("entity: " + entity.toString());
            
        } catch (Exception e) {
            System.out.println("Erro na consulta de logging: " + e.getMessage());
        } finally {
        	if(restClient != null) {
				try {
					restClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        return entity;
    }	
	
    @Override
    public HttpEntity searchFieldIndex(String field, String value) {
        HttpEntity entity = null;
        RestClient restClient = null;
        try {
			restClient = RestClient.builder(getHostnamesElasticSearch()).build();
			String query = "{\"query\" : {\"match\" : {\"" + field + "\" : \"" + value + "\"}}}";
            Response response = restClient.performRequest("GET", "/logging/_search?size=" + SEARCH_RESULT, new Hashtable<>(), new StringEntity(query));
            entity = response.getEntity();
            
        } catch (Exception e) {
            System.out.println("Erro na consulta de logging: " + e.getMessage());
        } finally {
        	if(restClient != null) {
				try {
					restClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        return entity;
    }
	
	@Override
	public List<LoggingVO> searchLoggingPeriod(Date init, Date end) {
		return null;
	}
	
	private HttpHost[] getHostnamesElasticSearch() {
    	List<String> nodes = configService.searchElasticSearchNodes();
    	HttpHost[] hosts = new HttpHost[nodes.size()];
    	int index = 0;
    	for (String node: nodes) {
    		hosts[index] = new HttpHost(node, 9200, "http");
    		index++;
    	}
		return hosts;
	}

}
