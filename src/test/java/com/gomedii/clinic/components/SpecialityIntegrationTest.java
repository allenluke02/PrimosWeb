/*package com.gomedii.clinic.components;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.gm.clinic.ClinicApplication;
import com.gm.clinic.model.Clinic;
import com.gm.clinic.model.Speciality;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClinicApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpecialityIntegrationTest {
	
	@LocalServerPort
	private int port;
	
	org.hamcrest.Matcher<Boolean> matchesTrue = org.hamcrest.core.IsEqual.equalTo(true);
	TestRestTemplate testRestTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	HttpEntity<Speciality> entity;
	ObjectMapper mapper = new ObjectMapper();
	int counter = 1;	
    
	@Rule
	public ErrorCollector collector = new ErrorCollector();
	
	@Before public void initialize() {	
		// create seed data
		database("changes.sql");	
		// setting jwt token in header		
		headers.add("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiYXBpIl0sInVzZXJfbmFtZSI6IjEiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwicm9sZXMiOlsiQURNSU4iXSwiZXhwIjoxNTMwMDk2NjczLCJhdXRob3JpdGllcyI6WyJVUERBVEVfVVNFUiIsIlJFQURfVVNFUiIsIkNSRUFURV9VU0VSIiwiREVMRVRFX1VTRVIiXSwianRpIjoiZGYxNmUxYTctYTA3OS00YzdjLWFjOTgtYmUyYmFiYmFiZmRmIiwiY2xpZW50X2lkIjoiZ21jLWNsaWVudCJ9.SBHxUOfwXXLZgptVZlMfDn6Qj2a1Dqwo97spvxd-n3o");
		headers.add("Content-Type", "application/json");
	}
	
	
	@After
    public void tearDown() throws Exception {
		database("droptable.sql");
    }

	*//**
	 * Test delete(s) facility on seed data
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 *//*
	@Test
	public void testDeleteSpeciality() {
		entity = new HttpEntity<Speciality>(null, headers);
		// incorrect clinic, correct facility
		int actualValue = testDeletes(entity, 2, "1").getStatusCodeValue(); 
		collector.checkThat("testDeleteSpeciality -> 1", 404, org.hamcrest.core.IsEqual.equalTo(actualValue));
		// correct clinic, incorrect facility
		actualValue = testDeletes(entity, 1, "2").getStatusCodeValue();
		collector.checkThat("testDeleteSpeciality -> 2", 404, org.hamcrest.core.IsEqual.equalTo(actualValue));
		// correct clinic, incorrect facilities
		actualValue = testDeletes(entity, 1, "1, 2").getStatusCodeValue();
		collector.checkThat("testDeleteSpeciality -> 3", 404, org.hamcrest.core.IsEqual.equalTo(actualValue));
		// correct clinic, correct facility
		actualValue = testDeletes(entity, 1, "1").getStatusCodeValue();
		collector.checkThat("testDeleteSpeciality -> 4", 200, org.hamcrest.core.IsEqual.equalTo(actualValue));
	}

	*//**
	 * Test update facility on seed data
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 *//*
	@Test
	public void testPutSpeciality()
			throws JsonParseException, JsonMappingException, IOException {
		resetCounter();
		// prepare data
		entity = new HttpEntity<Speciality>(null, headers);
		String seedEntityString = testGet(entity, 1, 1).getBody();
		Speciality seedSpeciality = createUpdateSpecialityEntity(1l, seedEntityString);
		// start testing
		entity = new HttpEntity<Speciality>(seedSpeciality, headers);
		// incorrect clinic, correct facility
		int actualValue = testUpdate(entity, 2, 1).getStatusCodeValue();
		collector.checkThat("testPutSpeciality -> 1", 400, org.hamcrest.core.IsEqual.equalTo(actualValue));
		// correct clinic, correct facility
		actualValue = testUpdate(entity, 1, 1).getStatusCodeValue();
		collector.checkThat("testPutSpeciality -> 2", 200, org.hamcrest.core.IsEqual.equalTo(actualValue));
		// correct clinic, incorrect facility
		entity = new HttpEntity<Speciality>(createUpdateSpecialityEntity(2l, seedEntityString), headers);
		actualValue = testUpdate(entity, 1, 2).getStatusCodeValue();
		collector.checkThat("testPutSpeciality -> 3", 404, org.hamcrest.core.IsEqual.equalTo(actualValue));
	}


	*//**
	 * Test insert facility
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 *//*
	@Test
	public void testPostSpeciality()
			throws JsonParseException, JsonMappingException, IOException {
		resetCounter();
		// prepare data
		entity = new HttpEntity<Speciality>(null, headers);
		String seedEntityString = testGet(entity, 1, 1).getBody();
		Speciality seedSpeciality = createUpdateSpecialityEntity(null, seedEntityString);
		entity = new HttpEntity<Speciality>(seedSpeciality, headers);
		// start testing
		// incorrect clinic
		int actualValue = testCreate(entity, 2).getStatusCodeValue();
		collector.checkThat("testPostSpeciality -> 1", 400, org.hamcrest.core.IsEqual.equalTo(actualValue));
		// correct clinic
		actualValue = testCreate(entity, 1).getStatusCodeValue();
		collector.checkThat("testPostSpeciality -> 2", 200, org.hamcrest.core.IsEqual.equalTo(actualValue));
	}

	*//**
	 * Test GetAll on existing seed data
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 *//*
	@Test
	public void testGetFacilities() throws JsonParseException, JsonMappingException, IOException {
		entity = new HttpEntity<Speciality>(null, headers);
		// incorrect clinic
		int actualValue = testGetAll(entity, 2).getStatusCodeValue();
		collector.checkThat("testGetFacilities -> 1", 404, org.hamcrest.core.IsEqual.equalTo(actualValue));
		// correct clinic
		ResponseEntity<String> responseEntity = testGetAll(entity, 1);
		actualValue = responseEntity.getStatusCodeValue();
		collector.checkThat("testGetFacilities -> 2", 200, org.hamcrest.core.IsEqual.equalTo(actualValue));
		List<Object> fList = convertJsonToEntityList(responseEntity.getBody(), Speciality.class);
		actualValue = fList.size();
		collector.checkThat("testGetFacilities -> 3", 1, org.hamcrest.core.IsEqual.equalTo(actualValue));
		collector.checkThat("testGetFacilities -> 4", "facility1", org.hamcrest.core.IsEqual.equalTo(((Speciality)fList.get(0)).getName()));
		collector.checkThat("testGetFacilities -> 5", "address 1", org.hamcrest.core.IsEqual.equalTo(((Speciality)fList.get(0)).getAddress().getAddress1()));

	}

	*//**
	 * Test Get on existing seed data
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 *//*
	@Test
	public void testGetSpeciality() throws JsonParseException, JsonMappingException, IOException {
		entity = new HttpEntity<Speciality>(null, headers);
		// incorrect clinic, correct facility
		int actualValue = testGet(entity, 2, 1).getStatusCodeValue();
		collector.checkThat("testGetSpeciality -> 1", 404, org.hamcrest.core.IsEqual.equalTo(actualValue));
		// correct clinic, incorrect facility
		actualValue = testGet(entity, 1, 2).getStatusCodeValue();
		collector.checkThat("testGetSpeciality -> 2", 404, org.hamcrest.core.IsEqual.equalTo(actualValue));
		// correct clinic, correct facility
		ResponseEntity<String> responseEntity = testGet(entity, 1, 1);
		actualValue = responseEntity.getStatusCodeValue();
		collector.checkThat("testGetSpeciality -> 3", 200, org.hamcrest.core.IsEqual.equalTo(actualValue));
		Speciality f = (Speciality) convertJsonToEntity(responseEntity.getBody(), Speciality.class);
		collector.checkThat("testGetSpeciality -> 4", "facility1", org.hamcrest.core.IsEqual.equalTo(f.getName()));
		collector.checkThat("testGetSpeciality -> 5", "address 1", org.hamcrest.core.IsEqual.equalTo(f.getAddress().getAddress1()));
	}
	
	*//**
	 * Test Scenario, if there is clinic and facility operation is performed for 
	 * such clinic. This is positive test case
	 * 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 *//*
	@Test
	public void testScenarioExisting() throws JsonParseException, JsonMappingException, IOException {
		// order of called methods matter
		// first create three entities, update one, get one, 
		// get all(shall return 4 including seed data), then delete newly created one
		// delete newly created two
		entity = new HttpEntity<Speciality>(null, headers);
		String seedSpecialityString = testGet(entity, 1, 1).getBody();		
		// create 3 new Entities
		existingCreateEntity(3, seedSpecialityString);
		// update Second Entity
		existingUpdateEntity(2, seedSpecialityString);		
		// GET 4th Entity
		existingGetEntity(4);				
		// get Entities
		existingGetEntities();		
		// delete 2nd Entity
		existingDeleteEntity("2");		
		// delete 3rd and 4th Entities
		existingDeleteEntities("3, 4");
	}

	private void existingDeleteEntities(String entityIds) throws JsonParseException, JsonMappingException, IOException {
		resetCounter();
		entity = new HttpEntity<Speciality>(null, headers);
		// successful delete
		int actualValue = testDeletes(entity, 1, entityIds).getStatusCodeValue();
		collector.checkThat("existingDeleteEntities -> 1", 200, org.hamcrest.core.IsEqual.equalTo(actualValue));
		ResponseEntity<String> responseEntity = testGetAll(entity, 1);
		List<Object> fList = convertJsonToEntityList(responseEntity.getBody(), Speciality.class);
		// 1 facility shall be present
		actualValue = fList.size();
		collector.checkThat("existingDeleteEntities -> 2", 1, org.hamcrest.core.IsEqual.equalTo(actualValue));
	}

	private void existingDeleteEntity(String entityIds) throws JsonParseException, JsonMappingException, IOException {
		resetCounter();
		entity = new HttpEntity<Speciality>(null, headers);
		// successful delete
		int actualValue = testDeletes(entity, 1, entityIds).getStatusCodeValue();
		collector.checkThat("existingDeleteEntity -> 1", 200, org.hamcrest.core.IsEqual.equalTo(actualValue));
		List<Object> fList = convertJsonToEntityList(testGetAll(entity, 1).getBody(), Speciality.class);
		// 3 facilities shall be present
		actualValue = fList.size();
		collector.checkThat("existingDeleteEntity -> 2", 3, org.hamcrest.core.IsEqual.equalTo(actualValue));
		// deleted facility shall be absent
		ResponseEntity<String> responseEntity = testGet(entity, 1, 2);
		actualValue = responseEntity.getStatusCodeValue();
		collector.checkThat("existingDeleteEntity -> 3", 404, org.hamcrest.core.IsEqual.equalTo(actualValue));
		// clinic should not get deleted
		HttpEntity<Clinic> entityC = new HttpEntity<Clinic>(null, headers);
		ResponseEntity<String> response = testRestTemplate.exchange(
				createURLWithPort("/api/clinics/1"),
				HttpMethod.GET, entityC, String.class);
		actualValue = response.getStatusCodeValue();
		collector.checkThat("existingDeleteEntity -> 4", 200, org.hamcrest.core.IsEqual.equalTo(actualValue));
	}

	private void existingGetEntities() throws JsonParseException, JsonMappingException, IOException {
		resetCounter();
		entity = new HttpEntity<Speciality>(null, headers);
		ResponseEntity<String> responseEntity = testGetAll(entity, 1);
		int actualValue = responseEntity.getStatusCodeValue();
		collector.checkThat("existingGetEntities -> 1", 200, org.hamcrest.core.IsEqual.equalTo(actualValue));
		List<Object> fList = convertJsonToEntityList(responseEntity.getBody(), Speciality.class);
		actualValue = fList.size();
		collector.checkThat("existingGetEntities -> 2", 4, org.hamcrest.core.IsEqual.equalTo(actualValue));
		collector.checkThat("existingGetEntities -> 3", "facility3", org.hamcrest.core.IsEqual.equalTo(((Speciality)fList.get(2)).getName()));
		collector.checkThat("existingGetEntities -> 4", "address3", org.hamcrest.core.IsEqual.equalTo(((Speciality)fList.get(2)).getAddress().getAddress1()));
	}

	private void existingGetEntity(long entityId) throws JsonParseException, JsonMappingException, IOException {
		resetCounter();
		entity = new HttpEntity<Speciality>(null, headers);
		ResponseEntity<String> responseEntity = testGet(entity, 1, entityId);
		int actualValue = responseEntity.getStatusCodeValue();
		collector.checkThat("existingGetEntity -> 1", 200, org.hamcrest.core.IsEqual.equalTo(actualValue));
		Speciality f = (Speciality) convertJsonToEntity(responseEntity.getBody(), Speciality.class);
		collector.checkThat("existingGetEntity -> 2", "facility"+entityId, org.hamcrest.core.IsEqual.equalTo(f.getName()));
		collector.checkThat("existingGetEntity -> 3", "address"+entityId, org.hamcrest.core.IsEqual.equalTo(f.getAddress().getAddress1()));
	}

	private void existingUpdateEntity(long entityId, String seedSpecialityString)
			throws JsonParseException, JsonMappingException, IOException {
		resetCounter();
		// update newly created second Entity
		entity = new HttpEntity<Speciality>(createUpdateSpecialityEntity(entityId, seedSpecialityString), headers);
		int actualValue = testUpdate(entity, 1, entityId).getStatusCodeValue();
		collector.checkThat("existingUpdateSpeciality -> 1", 200, org.hamcrest.core.IsEqual.equalTo(actualValue));
		// fetch updated entity from database
		// ensure that name of new facility and address name is also changed
		entity = new HttpEntity<Speciality>(null, headers);
		Speciality f = (Speciality) convertJsonToEntity(testGet(entity, 1, 2).getBody(), Speciality.class);		
		collector.checkThat("existingUpdateSpeciality -> 2", "facility"+entityId+"_"+entityId, org.hamcrest.core.IsEqual.equalTo(f.getName()));
		collector.checkThat("existingUpdateSpeciality -> 3", "address"+entityId+"_"+entityId, org.hamcrest.core.IsEqual.equalTo(f.getAddress().getAddress1()));
	}

	private void existingCreateEntity(int noOfEntitiesToBeCreated, String seedSpecialityString)
			throws JsonParseException, JsonMappingException, IOException {
		resetCounter();
		int i = 0;
		int testCaseNum = 1;
		while (i<noOfEntitiesToBeCreated) {
			entity = new HttpEntity<Speciality>(createUpdateSpecialityEntity(null, seedSpecialityString), headers);
			int actualValue = testCreate(entity, 1).getStatusCodeValue();
			collector.checkThat("existingCreateSpeciality -> "+testCaseNum, 200, org.hamcrest.core.IsEqual.equalTo(actualValue));
			testCaseNum++;
			// fetch newly created entity from database
			// ensure that name of new facility and address name is also changed
			entity = new HttpEntity<Speciality>(null, headers);
			Speciality f = (Speciality) convertJsonToEntity(testGet(entity, 1, i+2).getBody(), Speciality.class);
			collector.checkThat("existingCreateSpeciality -> "+testCaseNum, "facility"+(i+2), org.hamcrest.core.IsEqual.equalTo(f.getName()));
			testCaseNum++;
			collector.checkThat("existingCreateSpeciality -> "+testCaseNum, "address"+(i+2), org.hamcrest.core.IsEqual.equalTo(f.getAddress().getAddress1()));
			testCaseNum++;
			i++;
		}
	}

	
	 * Get Entity
	 
	private ResponseEntity<String> testGet(HttpEntity entity, long clinicId, long facilityId) {
		ResponseEntity<String> response = testRestTemplate.exchange(
				createURLWithPort("/api/clinics/"+clinicId+"/facilities/"+facilityId),
				HttpMethod.GET, entity, String.class);
		return response;
	}
	
	 * Get All Entities
	 
	private ResponseEntity<String> testGetAll(HttpEntity entity, long clinicId) {
		ResponseEntity<String> response = testRestTemplate.exchange(
				createURLWithPort("/api/clinics/"+clinicId+"/facilities"),
				HttpMethod.GET, entity, String.class);
		return response;
	}
	
	 * Create Entity
	 
	private ResponseEntity<String> testCreate(HttpEntity entity, long clinicId) {
		ResponseEntity<String> response = testRestTemplate.exchange(
				createURLWithPort("/api/clinics/"+clinicId+"/facilities"),
				HttpMethod.POST, entity, String.class);
		return response;
	}
	
	 * Update Entity
	 
	private ResponseEntity<String> testUpdate(HttpEntity entity, long clinicId, long facilityId) {
		ResponseEntity<String> response = testRestTemplate.exchange(
				createURLWithPort("/api/clinics/"+clinicId+"/facilities/"+facilityId),
				HttpMethod.PUT, entity, String.class);
		return response;
	}
	
	 * Delete Multiple Entities
	 * facilityIds = "1,2"
	 
	private ResponseEntity<String> testDeletes(HttpEntity entity, long clinicId, String facilityIds) {
		ResponseEntity<String> response = testRestTemplate.exchange(
				createURLWithPort("/api/clinics/"+clinicId+"/facilities/"+facilityIds),
				HttpMethod.DELETE, entity, String.class);
		return response;
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	*//**
	 * Call only once before a controller request, 
	 * till assertions related to that request testing is over.
	 * Sets counter to 1 which is supposed to be id of seed data
	 *//*
	private void resetCounter() {
		counter = 1;
	}
	
	private Object convertJsonToEntity(String entityJsonString, Class clazz) throws JsonParseException, JsonMappingException, IOException {
		Object obj = mapper.readValue(entityJsonString, clazz);
		return obj;
	}
	
	private List<Object> convertJsonToEntityList(String entityListJsonString, Class clazz) throws JsonParseException, JsonMappingException, IOException {
		CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
	    List<Object> objList = mapper.readValue(entityListJsonString, listType);
//		List<Object> objList = mapper.readValue(entityListJsonString, new TypeReference<List<Object>>(){});
		return objList;
	}
	
	public EmbeddedDatabase database(String... dataScript) {
	    return new EmbeddedDatabaseBuilder()
	            .setType(EmbeddedDatabaseType.H2)
	            .addScripts(dataScript) //.addScripts("user_data.sql", "country_data.sql")
	            .build();
	}
	
	*//**
	 * Creates/Updates new {@link Speciality} object by getting the {@link Speciality}
	 * from passed faclity json string returned from getSpeciality(1)
	 * and changing name of facility and address1 of corresponding address accordingly 
	 * for same {@link Clinic}. Name and Address1 is expected to be incremented by 1
	 * and in case of update is expected to be further appended by "_{Id}"
	 * 
	 * @return {@link Speciality}
	 * 
	 * @param entityId null for create and some value for update
	 * @param seedEntityString Json String of entity seed data
	 * 
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 *//*
	private Speciality createUpdateSpecialityEntity(Long entityId, String seedEntityString) throws JsonParseException, JsonMappingException, IOException {
		counter++;
		Speciality f = (Speciality) convertJsonToEntity(seedEntityString, Speciality.class);
		f.setId(entityId);
		if(entityId == null)// create
			f.setName("facility"+counter);
		else // update
			f.setName("facility"+counter+"_"+entityId);
		// new facility, then create new address
		if(entityId == null)
			f.getAddress().setId(null);
		// change address line 1 anyway
		if(entityId == null)// create
			f.getAddress().setAddress1("address"+counter);
		else // update
			f.getAddress().setAddress1("address"+counter+"_"+entityId);		
		return f;
	}
	
//	GET http://localhost:8080/public/test/1,2,3,4
//
//		@RequestMapping(value="/test/{firstNameIds}", method=RequestMethod.GET)
//		@ResponseBody
//		public String test(@PathVariable String[] firstNameIds)
//		{
//		    // firstNameIds: [1,2,3,4]
//		    return "Dummy"; 
//		}

}
*/