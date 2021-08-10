package com.codestates.seb.StatesAirlineServer;

import com.codestates.seb.StatesAirlineServer.CodeStatesSubmit.Submit;
import com.codestates.seb.StatesAirlineServer.Data.BookData;
import com.codestates.seb.StatesAirlineServer.Data.FlightData;
import com.codestates.seb.StatesAirlineServer.Domain.BookDTO;
import com.codestates.seb.StatesAirlineServer.Domain.FlightDTO;
import com.codestates.seb.StatesAirlineServer.Repository.BookRepository;
import com.codestates.seb.StatesAirlineServer.Repository.FlightRepository;
import com.codestates.seb.StatesAirlineServer.Service.BookService;
import com.codestates.seb.StatesAirlineServer.Service.FlightService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AutoConfigureMockMvc
@NoArgsConstructor
@SpringBootTest
public class StatesAirlineRefactoringTest {

    private static Submit submit = new Submit();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRepository flightRepository;

    @AfterAll
    static void after() throws Exception {
        submit.SubmitJson("im-sprint-spring-statesairline-refactoring", 25);
        submit.ResultSubmit();
    }

    @BeforeEach
    public void beforEach() throws Exception{
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(MockMvcResultHandlers.print())
                .build();

        objectMapper = Jackson2ObjectMapperBuilder.json()
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .modules(new JavaTimeModule())
                .build();

        BookData.getInstance().getBookList().clear();
        FlightData.getInstacne().Reset();
        SaveUserData("/book"); // 테스트 용 더미 데이터 저장
    }

    // Book Controller Test
    @Test
    public void Controller_Book_항공편을_예약합니다() throws Exception {
        String url = "/book";
        String standard = "{\"flight_uuid\":\"af6fa55c-da65-47dd-af23-578fdba40bed\",\"name\":\"김재현\",\"phone\":\"01021106097\"}";

        BookDTO book = new BookDTO();
        book.setFlight_uuid("af6fa55c-da65-47dd-af23-578fdba40bed");
        book.setName("김재현");
        book.setPhone("01021106097");

        String content = objectMapper.writeValueAsString(book);
        PostTest(url, standard, content);
    }

    @Test
    public void Controller_BooK_예약된_시스템_전체_항공편을_조회합니다() throws Exception {

        String url = "/book";
        String standard = "[{\"flight_uuid\":\"af6fa55c-da65-47dd-af23-578fdba40bed\",\"name\":\"김코딩\",\"phone\":\"01012345678\"}," +
                          "{\"flight_uuid\":\"af6fa55c-da65-47dd-af23-578fdba40bod\",\"name\":\"최자바\",\"phone\":\"01043215678\"}," +
                          "{\"flight_uuid\":\"af6fa55c-da65-47dd-af23-578fdba41bed\",\"name\":\"박서버\",\"phone\":\"01056781234\"}]";

        GetTest(url, standard);
    }

    @Test
    public void Controller_BooK_flight_uuid_값으로_예약_내역을_조회합니다() throws Exception {

        String url = "/book?flight_uuid=af6fa55c-da65-47dd-af23-578fdba40bod";
        String standard = "[{\"flight_uuid\":\"af6fa55c-da65-47dd-af23-578fdba40bod\",\"name\":\"최자바\",\"phone\":\"01043215678\"}]";

        GetTest(url, standard);
    }

    @Test
    public void Controller_BooK_phone_값으로_예약_내역을_조회합니다() throws Exception {

        String url = "/book?phone=01056781234";
        String standard = "[{\"flight_uuid\":\"af6fa55c-da65-47dd-af23-578fdba41bed\",\"name\":\"박서버\",\"phone\":\"01056781234\"}]";

        GetTest(url, standard);
    }

    @Test
    public void Controller_Book_예약한_항공편을_삭제합니다() throws Exception{

        String url = "/book?phone=01043215678";
        String standard = "[{\"flight_uuid\":\"af6fa55c-da65-47dd-af23-578fdba40bed\",\"name\":\"김코딩\",\"phone\":\"01012345678\"}," +
                          "{\"flight_uuid\":\"af6fa55c-da65-47dd-af23-578fdba41bed\",\"name\":\"박서버\",\"phone\":\"01056781234\"}]";

        DeleteTest(url, standard);
    }

    // Flight Controller Test
    @Test
    public void Controller_Flight_출발시간과_도착시간으로_항공편을_조회합니다() throws Exception{

        String url = "/flight?departure_times=2021-12-02T12:00:00&arrival_times=2021-12-03T12:00:00";
        String standard = "" +
                "[{\"uuid\":\"af6fa55c-da65-47dd-af23-578fdba40bed\",\"departure\":\"ICN\",\"destination\":\"CJU\",\"departure_times\":\"2021-12-02T12:00:00\",\"arrival_times\":\"2021-12-03T12:00:00\"}," +
                "{\"uuid\":\"af6fa55c-da65-47dd-af23-578fd7a40bed\",\"departure\":\"ICN\",\"destination\":\"CJU\",\"departure_times\":\"2021-12-02T12:00:00\",\"arrival_times\":\"2021-12-03T12:00:00\"}," +
                "{\"uuid\":\"af6fa55c-da65-47dd-af23-578fdba40bod\",\"departure\":\"ICN\",\"destination\":\"CJU\",\"departure_times\":\"2021-12-02T12:00:00\",\"arrival_times\":\"2021-12-03T12:00:00\"}," +
                "{\"uuid\":\"af6fa55c-da65-47dd-af23-578fdba50bed\",\"departure\":\"CJU\",\"destination\":\"ICN\",\"departure_times\":\"2021-12-02T12:00:00\",\"arrival_times\":\"2021-12-03T12:00:00\"}]";

        GetTest(url, standard);
    }

    @Test
    public void Controller_Flight_출발지와_도착지로_항공편을_조회합니다() throws Exception{

        String url = "/flight?departure=ICN&destination=CJU";
        String standard = "" +
                "[{\"uuid\":\"af6fa55c-da65-47dd-af23-578fdba40bed\",\"departure\":\"ICN\",\"destination\":\"CJU\",\"departure_times\":\"2021-12-02T12:00:00\",\"arrival_times\":\"2021-12-03T12:00:00\"}," +
                "{\"uuid\":\"af6fa55c-da65-47dd-af23-578fdba40byd\",\"departure\":\"ICN\",\"destination\":\"CJU\",\"departure_times\":\"2021-12-03T12:00:00\",\"arrival_times\":\"2021-12-03T12:00:00\"}," +
                "{\"uuid\":\"af6fa55c-da65-47dd-af23-578fdba48bed\",\"departure\":\"ICN\",\"destination\":\"CJU\",\"departure_times\":\"2021-12-03T12:00:00\",\"arrival_times\":\"2021-12-04T12:00:00\"}," +
                "{\"uuid\":\"af6fa55c-da65-47dd-af23-578fdbr40bed\",\"departure\":\"ICN\",\"destination\":\"CJU\",\"departure_times\":\"2021-12-03T12:00:00\",\"arrival_times\":\"2021-12-04T12:00:00\"}," +
                "{\"uuid\":\"af6fa55c-da65-47dd-af23-578fd7a40bed\",\"departure\":\"ICN\",\"destination\":\"CJU\",\"departure_times\":\"2021-12-02T12:00:00\",\"arrival_times\":\"2021-12-03T12:00:00\"}," +
                "{\"uuid\":\"af6fa55c-da65-47dd-af23-578fdba40bod\",\"departure\":\"ICN\",\"destination\":\"CJU\",\"departure_times\":\"2021-12-02T12:00:00\",\"arrival_times\":\"2021-12-03T12:00:00\"}," +
                "{\"uuid\":\"af6fa55c-da65-47dd-af23-578fdba44bed\",\"departure\":\"ICN\",\"destination\":\"CJU\",\"departure_times\":\"2021-12-03T12:00:00\",\"arrival_times\":\"2021-12-03T12:00:00\"}]";

        GetTest(url, standard);
    }

    @Test
    public void Controller_Flight_항공편_ID로_조회합니다() throws Exception{
        String url = "/flight/af6fa55c-da65-47dd-af23-578fdba40bed";
        String standard = "{\"uuid\":\"af6fa55c-da65-47dd-af23-578fdba40bed\",\"departure\":\"ICN\",\"destination\":\"CJU\",\"departure_times\":\"2021-12-02T12:00:00\",\"arrival_times\":\"2021-12-03T12:00:00\"}";

        GetTest(url, standard);
    }

    @Test
    public void Controller_Flight_항공편을_수정합니다() throws Exception{
        String url = "/flight/af6fa55c-da65-47dd-af23-578fdba99bed";
        String standard = "{\"uuid\":\"af6fa55c-da65-47dd-af23-578fdba99bed\",\"departure\":\"ICN\",\"destination\":\"CJU\",\"departure_times\":\"2021-12-02T11:00:00\",\"arrival_times\":\"2021-12-04T15:00:00\"}";

        FlightDTO.Request flight = new FlightDTO.Request();
        flight.setDeparture("ICN");
        flight.setDestination("CJU");
        flight.setDeparture_times("2021-12-02T11:00:00");
        flight.setArrival_times("2021-12-04T15:00:00");

        String content = objectMapper.writeValueAsString(flight);
        PutTest(url, standard, content);
    }

    // Book Service Test
    @Test
    public void Service_Book_flight_uuid_값으로_예약_내역을_조회합니다(){
        List<BookDTO> result = new ArrayList<>();
        List<BookDTO> standard = new ArrayList<>();

        try{
            result = bookService.SearchByFlightUuid("af6fa55c-da65-47dd-af23-578fdba40bed");
            standard = BookData.getInstance().getBookList().stream()
                    .filter(itme -> itme.getFlight_uuid().equals("af6fa55c-da65-47dd-af23-578fdba40bed"))
                    .collect(Collectors.toList());

            submit.ResultSave(result.equals(standard));
        }catch (Exception e){
            System.out.println(e);
        }finally {
            Assertions.assertEquals(result ,standard);
        }
    }

    @Test
    public void Service_Book_Phone_값으로_예약_내역을_조회합니다(){
        List<BookDTO> result = new ArrayList<>();
        List<BookDTO> standard = new ArrayList<>();

        try{
            result = bookService.SearchByPhone("01043215678");
            standard = BookData.getInstance().getBookList().stream()
                    .filter(item -> item.getPhone().equals("01043215678"))
                    .collect(Collectors.toList());

            submit.ResultSave(result.equals(standard));
        }catch (Exception e){
            System.out.println(e);
        }finally {
            Assertions.assertEquals(result ,standard);
        }
    }

    @Test
    public void Service_Book_항공편을_예약합니다(){
        BookDTO data = new BookDTO();
        data.setFlight_uuid("af6fa55c-da65-47dd-af23-578fdba40bed");
        data.setName("김액트");
        data.setPhone("01012123434");

        BookDTO result = new BookDTO();
        BookDTO standard = new BookDTO();

        try{
            result = bookService.SaveBook(data);
            standard = BookData.getInstance().getBookList().stream()
                    .filter(item -> item.getPhone().equals("01012123434"))
                    .findAny()
                    .get();

            submit.ResultSave(result.equals(standard));
        }catch (Exception e){
            System.out.println(e);
        }finally {
            Assertions.assertEquals(result ,standard);
        }
    }

    @Test
    public void Service_Book_예약한_항공편을_삭제합니다(){
        List<BookDTO> result = new ArrayList<>();
        List<BookDTO> standard = new ArrayList<>();

        try{
            result = bookService.DeleteByPhone("01043215678");
            standard = BookData.getInstance().getBookList().stream()
                    .filter(item -> !item.getPhone().equals("01043215678"))
                    .collect(Collectors.toList());

            submit.ResultSave(result.equals(standard));
        }catch (Exception e){
            System.out.println(e);
        }finally {
            Assertions.assertEquals(result ,standard);
        }
    }

    // Book Repository Test
    @Test
    public void Repository_Book_flight_uuid_값으로_예약_내역을_조회합니다(){
        List<BookDTO> result = new ArrayList<>();
        List<BookDTO> standard = new ArrayList<>();

        try{
            result = bookRepository.FindByUuid("af6fa55c-da65-47dd-af23-578fdba40bed");
            standard = BookData.getInstance().getBookList().stream()
                    .filter(itme -> itme.getFlight_uuid().equals("af6fa55c-da65-47dd-af23-578fdba40bed"))
                    .collect(Collectors.toList());

            submit.ResultSave(result.equals(standard));
        }catch (Exception e){
            System.out.println(e);
        }finally {
            Assertions.assertEquals(result ,standard);
        }
    }

    @Test
    public void Repository_Book_phone_값으로_예약_내역을_조회합니다(){
        List<BookDTO> result = new ArrayList<>();
        List<BookDTO> standard = new ArrayList<>();

        try{
            result = bookRepository.FindByPhone("01043215678");
            standard = BookData.getInstance().getBookList().stream()
                    .filter(item -> item.getPhone().equals("01043215678"))
                    .collect(Collectors.toList());

            submit.ResultSave(result.equals(standard));
        }catch (Exception e){
            System.out.println(e);
        }finally {
            Assertions.assertEquals(result ,standard);
        }
    }

    @Test
    public void Repository_항공편을_예약합니다(){
        BookDTO data = new BookDTO();
        data.setFlight_uuid("af6fa55c-da65-47dd-af23-578fdba40bed");
        data.setName("김액트");
        data.setPhone("01012123434");

        BookDTO result = new BookDTO();
        BookDTO standard = new BookDTO();

        try{
            result = bookRepository.Save(data);
            standard = BookData.getInstance().getBookList().stream()
                    .filter(item -> item.getPhone().equals("01012123434"))
                    .findAny()
                    .get();

            submit.ResultSave(result.equals(standard));
        }catch (Exception e){
            System.out.println(e);
        }finally {
            Assertions.assertEquals(result ,standard);
        }
    }

    @Test
    public void Repository_예약한_항공편을_삭제합니다(){
        List<BookDTO> result = new ArrayList<>();
        List<BookDTO> standard = new ArrayList<>();

        try{
            result = bookRepository.Delete("01043215678");
            standard = BookData.getInstance().getBookList().stream()
                    .filter(item -> !item.getPhone().equals("01043215678"))
                    .collect(Collectors.toList());

            submit.ResultSave(result.equals(standard));
        }catch (Exception e){
            System.out.println(e);
        }finally {
            Assertions.assertEquals(result ,standard);
        }
    }

    // Flight Service Test
    @Test
    public void Service_Flight_출발시간과_도착시간으로_항공편을_조회합니다(){
        List<FlightDTO.Info> result = new ArrayList<>();
        List<FlightDTO.Info> standard = new ArrayList<>();

        try{
            result = flightService.SreachByTime("2021-12-02T12:00:00", "2021-12-03T12:00:00");
            standard = FlightData.getInstacne().getFlightList().stream()
                    .filter(item -> item.getDeparture_times().equals("2021-12-02T12:00:00"))
                    .filter(item -> item.getArrival_times().equals("2021-12-03T12:00:00"))
                    .collect(Collectors.toList());

            submit.ResultSave(result.equals(standard));
        }catch (Exception e){
            System.out.println(e);
        }finally {
            Assertions.assertEquals(result ,standard);
        }
    }

    @Test
    public void Service_Flight_출발지와_도착지로_항공편을_조회합니다(){
        List<FlightDTO.Info> result = new ArrayList<>();
        List<FlightDTO.Info> standard = new ArrayList<>();

        try{
            result = flightService.SreachByRoute("ICN", "CJU");
            standard = FlightData.getInstacne().getFlightList().stream()
                    .filter(item -> item.getDeparture().equals("ICN"))
                    .filter(item -> item.getDestination().equals("CJU"))
                    .collect(Collectors.toList());

            submit.ResultSave(result.equals(standard));
        }catch (Exception e){
            System.out.println(e);
        }finally {
            Assertions.assertEquals(result ,standard);
        }
    }

    @Test
    public void Service_Flight_항공편_ID로_조회합니다(){
        FlightDTO.Info result = FlightDTO.Info.builder().build();
        FlightDTO.Info standard = FlightDTO.Info.builder().build();

        try{
            result = flightService.SreachById("af6fa55c-da65-47dd-af23-578fdba40byd");
            standard = FlightData.getInstacne().getFlightList().stream()
                    .filter(item -> item.getUuid().equals("af6fa55c-da65-47dd-af23-578fdba40byd"))
                    .findFirst()
                    .get();

            submit.ResultSave(result.equals(standard));
        }catch (Exception e){
            System.out.println(e);
        }finally {
            Assertions.assertEquals(result ,standard);
        }
    }

    @Test
    public void Service_Flight_항공편을_수정합니다(){
        FlightDTO.Request data = new FlightDTO.Request();
        data.setDeparture("TNC");
        data.setDestination("RFD");
        data.setArrival_times(null);
        data.setDeparture_times("2021-12-03T12:12:33");

        FlightDTO.Info result = FlightDTO.Info.builder().build();
        FlightDTO.Info standard = FlightDTO.Info.builder().build();

        try{
            result = flightService.UpdateFlight("af6fa55c-da65-47dd-af23-578fd7a40bed", data);
            standard = FlightData.getInstacne().getFlightList().stream()
                    .filter(item -> item.getDeparture().equals("TNC"))
                    .filter(item -> item.getDestination().equals("RFD"))
                    .filter(item -> item.getArrival_times().equals("2021-12-03T12:00:00"))
                    .filter(item -> item.getDeparture_times().equals("2021-12-03T12:12:33"))
                    .findAny()
                    .get();

            submit.ResultSave(result.equals(standard));
        }catch (Exception e){
            System.out.println(e);
        }finally {
            Assertions.assertEquals(result ,standard);
        }
    }

    // Flight Repository Test
    @Test
    public void Repository_Flight_출발시간과_도착시간으로_항공편을_조회합니다(){
        List<FlightDTO.Info> result = new ArrayList<>();
        List<FlightDTO.Info> standard = new ArrayList<>();

        try{
            result = flightRepository.FindByTime("2021-12-02T12:00:00", "2021-12-03T12:00:00");
            standard = FlightData.getInstacne().getFlightList().stream()
                    .filter(item -> item.getDeparture_times().equals("2021-12-02T12:00:00"))
                    .filter(item -> item.getArrival_times().equals("2021-12-03T12:00:00"))
                    .collect(Collectors.toList());

            submit.ResultSave(result.equals(standard));
        }catch (Exception e){
            System.out.println(e);
        }finally {
            Assertions.assertEquals(result ,standard);
        }
    }

    @Test
    public void Repository_Flight_출발지와_도착지로_항공편을_조회합니다(){
        List<FlightDTO.Info> result = new ArrayList<>();
        List<FlightDTO.Info> standard = new ArrayList<>();

        try{
            result = flightRepository.FindByRoute("ICN", "CJU");
            standard = FlightData.getInstacne().getFlightList().stream()
                    .filter(item -> item.getDeparture().equals("ICN"))
                    .filter(item -> item.getDestination().equals("CJU"))
                    .collect(Collectors.toList());

            submit.ResultSave(result.equals(standard));
        }catch (Exception e){
            System.out.println(e);
        }finally {
            Assertions.assertEquals(result ,standard);
        }
    }

    @Test
    public void Repository_Flight_항공편_ID로_조회합니다(){
        Optional<FlightDTO.Info> result = Optional.ofNullable(FlightDTO.Info.builder().build());
        Optional<FlightDTO.Info> standard = Optional.ofNullable(FlightDTO.Info.builder().build());

        try{
            result = flightRepository.FindById("af6fa55c-da65-47dd-af23-578fdba40byd");
            standard = FlightData.getInstacne().getFlightList().stream()
                    .filter(item -> item.getUuid().equals("af6fa55c-da65-47dd-af23-578fdba40byd"))
                    .findFirst();

            submit.ResultSave(result.equals(standard));
        }catch (Exception e){
            System.out.println(e);
        }finally {
            Assertions.assertEquals(result ,standard);
        }
    }

    @Test
    public void Repository_항공편을_수정합니다(){
        FlightDTO.Request data = new FlightDTO.Request();
        data.setDeparture("TNC");
        data.setDestination("RFD");
        data.setArrival_times(null);
        data.setDeparture_times("2021-12-03T12:12:33");

        FlightDTO.Info result = FlightDTO.Info.builder().build();
        FlightDTO.Info standard = FlightDTO.Info.builder().build();

        try{
            result = flightRepository.Update("af6fa55c-da65-47dd-af23-578fd7a40bed", data);
            standard = FlightData.getInstacne().getFlightList().stream()
                    .filter(item -> item.getDeparture().equals("TNC"))
                    .filter(item -> item.getDestination().equals("RFD"))
                    .filter(item -> item.getArrival_times().equals("2021-12-03T12:00:00"))
                    .filter(item -> item.getDeparture_times().equals("2021-12-03T12:12:33"))
                    .findAny()
                    .get();

            submit.ResultSave(result.equals(standard));
        }catch (Exception e){
            System.out.println(e);
        }finally {
            Assertions.assertEquals(result ,standard);
        }
    }

    // Test Code
    public void SaveUserData(String url){
        BookDTO user1 = new BookDTO();
        user1.setFlight_uuid("af6fa55c-da65-47dd-af23-578fdba40bed");
        user1.setName("김코딩");
        user1.setPhone("01012345678");

        BookDTO user2 = new BookDTO();
        user2.setFlight_uuid("af6fa55c-da65-47dd-af23-578fdba40bod");
        user2.setName("최자바");
        user2.setPhone("01043215678");

        BookDTO user3 = new BookDTO();
        user3.setFlight_uuid("af6fa55c-da65-47dd-af23-578fdba41bed");
        user3.setName("박서버");
        user3.setPhone("01056781234");

        BookData.getInstance().getBookList().add(user1);
        BookData.getInstance().getBookList().add(user2);
        BookData.getInstance().getBookList().add(user3);
    }

    public void GetTest(String url, String standard) throws Exception{
        MvcResult result = null;
        try{
            result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();

            submit.ResultSave(result.getResponse().getContentAsString().equals(standard));
        }catch (Exception e){
            System.out.println(e);
        }finally {
            Assertions.assertEquals(result.getResponse().getContentAsString(),standard);
        }
    }

    public void PostTest(String url, String standard, String content) throws Exception{
        MvcResult result = null;
        try{
            result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .content(content)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            submit.ResultSave(result.getResponse().getContentAsString().equals(standard));
        }catch (Exception e){
            System.out.println(e);
        }finally {
            Assertions.assertEquals(result.getResponse().getContentAsString(),standard);
        }
    }

    public void PutTest(String url, String standard, String content) throws Exception{
        MvcResult result = null;
        try{
            result = mockMvc.perform(MockMvcRequestBuilders.put(url)
                    .content(content)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            submit.ResultSave(result.getResponse().getContentAsString().equals(standard));
        }catch (Exception e){
            System.out.println(e);
        }finally {
            Assertions.assertEquals(result.getResponse().getContentAsString(),standard);
        }
    }

    public void DeleteTest(String url, String standard) throws Exception{
        MvcResult result = null;
        try{
            result = mockMvc.perform(MockMvcRequestBuilders.delete(url)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();

            submit.ResultSave(result.getResponse().getContentAsString().equals(standard));
        }catch (Exception e){
            System.out.println(e);
        }finally {
            Assertions.assertEquals(result.getResponse().getContentAsString(),standard);
        }
    }
}
