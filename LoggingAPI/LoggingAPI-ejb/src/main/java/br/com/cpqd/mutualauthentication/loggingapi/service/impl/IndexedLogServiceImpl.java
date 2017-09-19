package br.com.cpqd.mutualauthentication.loggingapi.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cpqd.mutualauthentication.loggingapi.beans.entity.LoggingVO;
import br.com.cpqd.mutualauthentication.loggingapi.beans.to.FiltersTO;
import br.com.cpqd.mutualauthentication.loggingapi.beans.to.LoggingTO;
import br.com.cpqd.mutualauthentication.loggingapi.dao.api.IndexedLogDAO;
import br.com.cpqd.mutualauthentication.loggingapi.service.api.IndexedLogService;

@Stateless
public class IndexedLogServiceImpl implements IndexedLogService {

	@EJB
	private IndexedLogDAO indexedLogDAO;
	
	@Override
	public void save(LoggingVO vo) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(vo);
			indexedLogDAO.save(vo.getId(), json);
			
		} catch (JsonProcessingException e) {
			System.out.println("logging.save: Erro no parser do JSON.");
		}
	}

	@Override
	public List<LoggingTO> searchFilter(FiltersTO filters) {
		List<LoggingTO> finalArray = null;
		String field = new String();
		String value = new String();
		String level = filters.getType();
		if (filters.getComponent()!=null && !filters.getComponent().equals("")) {
			field = "component";
			value = filters.getComponent();
		} else if (filters.getNode()!=null && !filters.getNode().equals("")) {
			field = "node";
			value = filters.getNode();
		} else {
			field = "level";
			value = level;
		}
		HttpEntity entity = indexedLogDAO.searchFieldIndex(field, value);
		JSONObject json = convertHttpEntityJSONObject(entity);
        JSONObject hits = json.getJSONObject("hits");
        JSONArray array = hits.getJSONArray("hits");
        if (array != null) {
            finalArray = new ArrayList<LoggingTO>();
            for (int i = 0; i < array.length(); i++) {
            	JSONObject element = array.getJSONObject(i);
            	JSONObject elementJSON = element.getJSONObject("_source");
            	LoggingTO to = new LoggingTO();
            	to.setComponent(elementJSON.getString("component"));
            	to.setDetails(elementJSON.getString("details"));
            	to.setLevel(elementJSON.getString("level"));
            	to.setNode(elementJSON.getString("node"));
            	Long timestamp = new Long(elementJSON.getInt("date"));
            	Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
            	calendar.setTimeInMillis(timestamp);
            	Date date = new Date(calendar.getTimeInMillis());
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                to.setDate(df.format(date));
            	finalArray.add(to);
            }
        }
		return finalArray;
	}

    public JSONObject convertHttpEntityJSONObject(HttpEntity entity) {
        JSONObject json = null;
        if (entity != null) {
            try {
                String retSrc = EntityUtils.toString(entity);
                json = new JSONObject(retSrc);
            } catch (ParseException e) {
                // TODO: tratar exception
                e.printStackTrace();
            } catch (IOException e) {
                // TODO: tratar exception
                e.printStackTrace();
            }
        }
        return json;
    }
    
}
