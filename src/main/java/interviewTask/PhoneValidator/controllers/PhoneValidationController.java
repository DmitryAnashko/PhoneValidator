package interviewTask.PhoneValidator.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import interviewTask.PhoneValidator.ExtractCountryCodeFromPhone;
import interviewTask.PhoneValidator.PhoneObject;
import interviewTask.PhoneValidator.PhoneValidatorFormater;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@RestController
@RequestMapping("phone")
public class PhoneValidationController {

    public static final String NODE_ERROR_PHONE_FORMAT = "\"Wrong phone number format\"";
    public static final String NODE_ERROR_NO_COUNTRY = "\"Country not found\"";
    PhoneValidatorFormater validator = new PhoneValidatorFormater();

    @PostMapping
    public List<JsonNode> validatePhone(@RequestBody PhoneObject phoneObject) throws IOException {

        return getPhoneCountryCodes((phoneObject.getPhone()));
    }

    private List<JsonNode> getPhoneCountryCodes(String phoneNumber) throws JsonProcessingException {
        List<JsonNode> countryNames = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        phoneNumber = validator.formatPhoneNumber(phoneNumber);

        try {
            if (validator.isPhoneNumberValid(phoneNumber)) {
                String countryCode = String.valueOf(ExtractCountryCodeFromPhone.Instance.getCtryCode(phoneNumber));
                final String uri = "https://www.wikitable2json.com/api/List_of_country_calling_codes?table=1";
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

                JsonNode wikipediaTable = mapper.readValue(response.getBody(), JsonNode.class);
                JsonNode trimmedWikipediaTable = wikipediaTable.get("tables").get(0).get("rows");

                if (trimmedWikipediaTable.isObject() || trimmedWikipediaTable.isArray()) {
                    countryNames = StreamSupport.stream(trimmedWikipediaTable.spliterator(), true)
                            .filter(json -> json.get("columns").get("1").asText().replaceAll(" ", "").equalsIgnoreCase("+" + countryCode))
                            .map(json -> json.get("columns").get("0"))
                            .collect(Collectors.toList());
                }
                if (countryNames.size() <= 0) {
                    countryNames.add(errors(NODE_ERROR_NO_COUNTRY, mapper));
                }

            } else {
                countryNames.add(errors(NODE_ERROR_PHONE_FORMAT, mapper));
            }
        } catch (Exception ex) {

            countryNames.add(errors(NODE_ERROR_NO_COUNTRY, mapper));
        }

        return countryNames;

    }

    private JsonNode errors(String error, ObjectMapper mapper) throws JsonProcessingException {
        JsonNode errorNode = mapper.readTree(error);
        return errorNode;
    }

}
