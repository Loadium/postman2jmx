package com.loadium.postman2jmx.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExecDeserializer extends JsonDeserializer<ExecDeserializer.ExecData> {

    public ExecDeserializer() {}

    @Override
    public ExecDeserializer.ExecData deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ExecDeserializer.ExecData execData = new ExecDeserializer.ExecData();
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
            execData.addValue(jsonParser.getValueAsString());
        } else if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
            ObjectMapper mapper = new ObjectMapper();
            List<String> values = mapper.readValue(jsonParser, List.class);
            execData.setValues(values);

        }
        return execData;
    }

    public class ExecData {
        private List<String> values = new ArrayList<>();

        public void addValue(String value) {
            this.values.add(value);
        }

        public List<String> getValues() {
            return this.values;
        }

        public void setValues(List<String> values) {
            this.values.addAll(values);
        }
    }
}
