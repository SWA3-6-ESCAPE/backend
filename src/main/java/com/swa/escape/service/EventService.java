package com.swa.escape.service;

import com.swa.escape.domain.Event;
import com.swa.escape.domain.EventStatus;
import com.swa.escape.dto.EventCreateRequest;
import com.swa.escape.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.System.getenv;

@Service
@RequiredArgsConstructor
public class EventService implements EventServiceImpl {

    private final EventRepository eventRepository;

    // 이벤트 생성
    @Override
    public Event createEvent(EventCreateRequest eventRequest) {
        Event newEvent = new Event();
        newEvent.setLatitude(eventRequest.getEvent_latitude());
        newEvent.setLongitude(eventRequest.getEvent_longitude());

        // api 를 사용해 위도/경도를 지역 이름으로 변경하여 eventAddress 으로 저장
        String region = addressConverter(eventRequest.getEvent_latitude(), eventRequest.getEvent_longitude());
        newEvent.setEventAddress(region);

        return eventRepository.save(newEvent);
    }

  
    // 전체 이벤트 조회
    @Override
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }
  

    // 개별 이벤트 조회
    @Override
    public Optional<Event> getEvent(int eventId) {
        return eventRepository.findById(eventId);
    }


    // 이벤트 삭제
    @Override
    public void deleteEvent(int eventId) {
        eventRepository.deleteById(eventId);
    }

  
    // 이벤트 활성화
    public void enableEvent(int eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event activeEvent = optionalEvent.get();
            activeEvent.setEventStatus(EventStatus.ACTIVE);

            eventRepository.save(activeEvent);

            // 활성화 후 알림을 ios 에게 전달해야 함
        }
    }


    // 주소 변환 메소드
    public String addressConverter(float latitude, float longitude) {

        String url = "https://dapi.kakao.com/v2/local/geo/coord2regioncode.json";
        Map<String, String> env = getenv(); // 환경 변수 가져오기 (KAKAO_API_KEY)

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "KakaoAK " + env.get("KAKAO_API_KEY"));
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam("x", String.valueOf(longitude)) // 경도
                .queryParam("y", String.valueOf(latitude)) // 위도
                .build();

        ResponseEntity<String> response = restTemplate.exchange(uriComponents.toString(), HttpMethod.GET, entity, String.class);

        try {
            String body = response.getBody();
            JSONParser parser = new JSONParser();
            JSONObject rootObject = (JSONObject) parser.parse(body);
            JSONObject document = ((JSONObject) ((JSONArray) rootObject.get("documents")).get(0));

            if (document.get("region_type").equals("B")) {
                return (String) document.get("address_name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new RuntimeException("json 변환 오류");
    }

  
    // 알림 생성
    @Override
    public void sendNotifications(Event event) {

    }


}
